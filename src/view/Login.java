package view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.ClientEntity;
import model.DAO;
import util.DockerValidation;
import view.configuration.ConfigurationDialog;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.ObjectInputFilter.Status;
import java.awt.Cursor;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Principal principal = new Principal();

	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private JLabel dbicon;
	private JLabel lblEsqueciMinhaSenha;

	private ClientEntity clientEntity;

	private static boolean windowVisible = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(windowVisible);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void logar() {
		// Criar uam variavel objeto para caputrar a senha

		String capturaSenha = new String(txtSenha.getPassword());

		String read = "select * from usuarios where login = ? and senha = md5(?)";

		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "PREENCHA O LOGIN");
			txtLogin.requestFocus();
		} else if (capturaSenha.isEmpty()) {
			JOptionPane.showMessageDialog(null, "PREENCHA A SENHA");
			txtSenha.requestFocus();
		} else {
			try {
				// Logica principal
				con = dao.conectar();
				pst = con.prepareStatement(read);

				pst.setString(1, txtLogin.getText());
				pst.setString(2, capturaSenha);
				rs = pst.executeQuery();
				if (rs.next()) {
					// Capturar o perfil do usuario
					String perfil = rs.getString(5);
					// Condição Admin

					if (perfil.equals("admin")) {
						// Principal Window visibility
						principal.setVisible(true);
						// Setting Usuario and Cargo fields from data
						principal.lblUsuario.setText(rs.getString(2));
						principal.lblCargo.setText(rs.getString(5));

						principal.btnUsuarios.setEnabled(true);
						principal.btnClientes.setEnabled(true);
						principal.btnRelatorios.setEnabled(true);
						principal.btnServicos.setEnabled(true);
						principal.btnFornecedor.setEnabled(true);

						// Fechar banco
						con.close();
						// Fechar janela
						this.dispose();
					} else if (perfil.equals("user")) {
						principal.setVisible(true);
						principal.lblUsuario.setText(rs.getString(2));
						principal.lblCargo.setText(rs.getString(5));

						con.close();
						principal.btnRelatorios.setEnabled(true);
//						principal.btnServicos.setEnabled(true);
						this.dispose();
					} else {

					}
				} else {
					JOptionPane.showInternalMessageDialog(null, "Login ou senha invalida");
				}
			} catch (java.lang.NullPointerException Nulle) {
				JOptionPane.showMessageDialog(null, "Falha no banco de dados, Por favor, reinicie o aplicativo.");
			} catch (SQLException SQLe) {
				SQLe.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setTitle("Login");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/ConsoleIcon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 667, 336);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(91, 92, 93));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		dbicon = new JLabel("");
		dbicon.setIcon(new ImageIcon(Login.class.getResource("/img/dboff.png")));
		dbicon.setBounds(594, 202, 57, 48);
		contentPane.add(dbicon);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(186, 67, 293, 33);
		contentPane.add(lblLogin);

		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		txtLogin.setBounds(186, 111, 293, 23);
		contentPane.add(txtLogin);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSenha.setBounds(262, 159, 130, 28);
		contentPane.add(lblSenha);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(262, 198, 130, 14);
		contentPane.add(txtSenha);

		JButton bttnAcessar = new JButton("Acessar");
		getContentPane().add(bttnAcessar);
		getRootPane().setDefaultButton(bttnAcessar);
		bttnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}

		});

		bttnAcessar.setBounds(470, 202, 114, 33);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(36, 37, 38));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(0, 223, 674, 27);
		contentPane.add(lblNewLabel);

		lblEsqueciMinhaSenha = new JLabel("Esqueci minha senha");
		lblEsqueciMinhaSenha.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblEsqueciMinhaSenha.setForeground(Color.WHITE);
		lblEsqueciMinhaSenha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String name = JOptionPane.showInputDialog("Por favor, insira o nome de usuario com administrador:");
				String pass;
				if (name == null) {
				} else {
					pass = JOptionPane.showInputDialog("Insira a senha:");
					try {
						String comando = "SELECT * FROM usuarios where nome=? and senha=md5(?)";
						pst = dao.conectar().prepareStatement(comando);

						pst.setString(1, name);
						pst.setString(2, pass);

						rs = pst.executeQuery();
						/**
						 * rs.next tem função de checar o perfil do usuario para a mudança de senha
						 */
						if (rs.next()) {
							// rs.getString 5 = Perfil, admin ou usuario
							if (rs.getString(5).equals("admin")) {

								String nam = txtLogin.getText();
								var esquecisenha = new EsqueciMinhaSenha();
								esquecisenha.setVisible(true);
								esquecisenha.txtNome.setText(nam);
							} else {
								JOptionPane.showMessageDialog(null, "Login não autorizado");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Erro no login");
						}

					} catch (SQLException SQLe) {
						// TODO: handle exception
						SQLe.printStackTrace();
					} catch (Exception ex) {
						// TODO: handle exception
						ex.printStackTrace();
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblEsqueciMinhaSenha.setForeground(Color.BLUE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblEsqueciMinhaSenha.setForeground(Color.WHITE);
			}
		});
		lblEsqueciMinhaSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblEsqueciMinhaSenha.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblEsqueciMinhaSenha.setBounds(139, 197, 114, 17);
		contentPane.add(lblEsqueciMinhaSenha);

		setLocationRelativeTo(null);
		status();
	}

	private void status() {
		// tentar se conectar, caso não tenha, verificar se há docker, verificar se há windows
		DockerValidation docker = new DockerValidation();
			try {
				if (dao.conectar() == null) {
					if (
							//tira essa para dps tlg
							
							docker.isDockerInstalled()) {
						int c = JOptionPane.showConfirmDialog(null, "Parece que o docker esta instalado, gostaria de inicializar o banco de dados?");
								if(c==0) {
									docker.InicializeDockerCompose();
								}
					} else {
						if (!docker.isWindows()) {
//							JOptionPane.showInternalOptionDialog(contentPane, con, getTitle(), ALLBITS, ABORT, null, getComponentListeners(), clientEntity);
							JOptionPane.showOptionDialog(null,
									"Database não encontrada, para instalar no windows, é necessario seguir o link a seguir :\nhttps://docs.docker.com/engine/install/",
									getTitle(), ALLBITS, ABORT, null, getComponentListeners(), clientEntity);
//							JOptionPane.showMessageDialog(null, "Database não encontrada, para instalar no windows, é necessario que voce siga o seguinte link:\nhttps://docs.docker.com/engine/install/");
						} else {
							JOptionPane.showMessageDialog(null, "linux");
						}
					}
					dbicon.setIcon(new ImageIcon(Principal.class.getResource("/img/dboff.png")));
				} else {
					dbicon.setIcon(new ImageIcon(Principal.class.getResource("/img/dbon.png")));
				}
				dao.conectar().close();
			} catch (Exception SQLe) {
				int c;
				// pedido para conectar
				c = JOptionPane.showConfirmDialog(contentPane,
						"Conexão padrão não encontrada, gostaria de configura-la?");
				System.out.println(c);
				if (c == 0) {
					ConfigurationDialog CD = new ConfigurationDialog();
					CD.setVisible(true);
					windowVisible = false;
				} else {
					// Setar na configuração, ler e salvar o arquivo configurado
					// Confirmação do erro de conexão
					// ??
					JOptionPane.showMessageDialog(null,
							"Erro ao se conectar ao Banco de dados (MySQL), por favor, cheque sua conexão");
				}
				SQLe.printStackTrace();

			}
		
	}//
}

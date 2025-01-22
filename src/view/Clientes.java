package view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import model.DAO;
import util.ClearFields;
import util.Validator;
import util.JListTextValidate;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Cursor;

public class Clientes extends JDialog {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	
	private JTextField txtCep;
	private JTextField txtNome;
	private JTextField txtFone;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtBairro;
	private JTextField txtComplemento;
	private JTextField txtCidade;
	private JTextField txtId;
	@SuppressWarnings("rawtypes")
	private JComboBox cboUf;
	private JScrollPane scrollPane;
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;
	@SuppressWarnings("rawtypes")
	private JList listClientes;
	private JButton btnExcluir_1;
	private JTextField txtCPF;
	
	private List<JTextField> listTxt = new ArrayList<JTextField>();
	private List<JComboBox> listCb = new ArrayList<JComboBox>();
	private JListTextValidate jlistvalidate;
	private ClearFields limparcampos;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Clientes dialog = new Clientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Clientes() {
		setTitle("Clientes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/img/UsersIcon2.png")));
		setResizable(false);
		setBounds(100, 100, 591, 374);
		getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBounds(61, 61, 302, 67);
		getContentPane().add(scrollPane);

		listClientes = new JList();
		listClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ItensClientesLista();
			}
		});
		scrollPane.setViewportView(listClientes);

		txtCep = new JTextField();
		txtCep.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				onlyNum(e);
			}
		});

		txtCep.setColumns(10);
		txtCep.setBounds(61, 107, 158, 21);
		getContentPane().add(txtCep);

		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setBounds(10, 11, 27, 21);
		getContentPane().add(lblNewLabel);

		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setColumns(10);
		txtId.setBounds(30, 11, 80, 21);
		getContentPane().add(txtId);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 43, 41, 21);
		getContentPane().add(lblNome);

		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				listarClientes();
			}

		});
		txtNome.setColumns(10);
		txtNome.setBounds(61, 43, 302, 21);
		txtNome.setDocument(new Validator(50));
		getContentPane().add(txtNome);

		JLabel lblNewLabel_1_1 = new JLabel("CEP");
		lblNewLabel_1_1.setBounds(10, 107, 41, 21);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblFone = new JLabel("Fone");
		lblFone.setBounds(10, 75, 41, 21);
		getContentPane().add(lblFone);

		txtFone = new JTextField();
		txtFone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				onlyNum(e);
			}
		});
		txtFone.setColumns(10);
		txtFone.setBounds(61, 75, 112, 21);
		getContentPane().add(txtFone);
		txtFone.setDocument(new Validator(15));

		JButton btnCep = new JButton("Buscar CEP");
		btnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();
			}
		});
		btnCep.setBounds(244, 106, 145, 23);
		getContentPane().add(btnCep);
		txtCep.setDocument(new Validator(10));

		JLabel lblNewLabel_1_1_1 = new JLabel("Endereço");
		lblNewLabel_1_1_1.setBounds(10, 139, 75, 21);
		getContentPane().add(lblNewLabel_1_1_1);

		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(81, 139, 282, 21);
		getContentPane().add(txtEndereco);
		txtEndereco.setDocument(new Validator(50));

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Numero");
		lblNewLabel_1_1_1_1.setBounds(411, 139, 56, 21);
		getContentPane().add(lblNewLabel_1_1_1_1);

		txtNumero = new JTextField();
		txtNumero.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				onlyNum(e);
			}
		});
		txtNumero.setColumns(10);
		txtNumero.setBounds(466, 139, 86, 20);
		getContentPane().add(txtNumero);
		txtNumero.setDocument(new Validator(10));

		JLabel lblNewLabel_1_1_1_2 = new JLabel("Bairro");
		lblNewLabel_1_1_1_2.setBounds(10, 172, 65, 21);
		getContentPane().add(lblNewLabel_1_1_1_2);

		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(81, 172, 148, 21);
		getContentPane().add(txtBairro);
		txtBairro.setDocument(new Validator(30));

		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("Complemento");
		lblNewLabel_1_1_1_2_1.setBounds(291, 172, 112, 21);
		getContentPane().add(lblNewLabel_1_1_1_2_1);

		txtComplemento = new JTextField();
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(404, 172, 148, 21);
		getContentPane().add(txtComplemento);
		txtComplemento.setDocument(new Validator(20));

		JLabel lblNewLabel_1_1_1_2_2 = new JLabel("Cidade");
		lblNewLabel_1_1_1_2_2.setBounds(61, 224, 65, 21);
		getContentPane().add(lblNewLabel_1_1_1_2_2);

		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(132, 224, 148, 21);
		getContentPane().add(txtCidade);
		txtCidade.setDocument(new Validator(30));

		JLabel lblNewLabel_1 = new JLabel("UF");
		lblNewLabel_1.setBounds(305, 227, 27, 14);
		getContentPane().add(lblNewLabel_1);

		btnAdicionar = new JButton("");
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			if(!btnAdicionar.isEnabled()) {
				JOptionPane.showMessageDialog(null, "Por favor, clique para apagar campos para adicionar");
				System.out.println("Desativado");
			}
			}
		});
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(Clientes.class.getResource("/img/cliAdd.png")));
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnAdicionar.setBounds(30, 256, 64, 64);
		getContentPane().add(btnAdicionar);

		btnEditar = new JButton("");
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!btnEditar.isEnabled()) {
					JOptionPane.showMessageDialog(null, "Por favor, insira um cliente para poder editar");
				}
			}
		});
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		btnEditar.setIcon(new ImageIcon(Clientes.class.getResource("/img/cliEdit.png")));
		btnEditar.setToolTipText("Editar");
		btnEditar.setEnabled(false);
		btnEditar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnEditar.setBounds(132, 256, 64, 64);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!btnExcluir.isEnabled()) {
					JOptionPane.showMessageDialog(null, "Por favor, insira um cliente para poder editar");
				}
			}
		});
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove();
			}
		});
		btnExcluir.setIcon(new ImageIcon(Clientes.class.getResource("/img/cliRemove.png")));
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnExcluir.setBounds(244, 256, 64, 64);
		getContentPane().add(btnExcluir);

		cboUf = new JComboBox();
		cboUf.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUf.setBounds(342, 223, 47, 22);
		getContentPane().add(cboUf);

		btnExcluir_1 = new JButton("");
		btnExcluir_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				listTxt.add(txtId);
				listTxt.add(txtComplemento);
				
				limparcampos.clear(listTxt, listCb);
				
				listTxt.remove(txtId);
				listTxt.remove(txtComplemento);
				btnAdicionar.setEnabled(true);
			}
		});
		btnExcluir_1.setIcon(new ImageIcon(Clientes.class.getResource("/img/erase.png")));
		btnExcluir_1.setToolTipText("Apagar campos");
		btnExcluir_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnExcluir_1.setBounds(437, 256, 64, 64);
		getContentPane().add(btnExcluir_1);

		txtCPF = new JTextField();
		txtCPF.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				onlyNum(e);
			}
		});
		txtCPF.setBounds(440, 43, 112, 20);
		getContentPane().add(txtCPF);
		txtCPF.setColumns(10);
		txtCPF.setDocument(new Validator(15));

		JLabel lblNewLabel_2 = new JLabel("CPF");
		lblNewLabel_2.setBounds(399, 46, 41, 14);
		getContentPane().add(lblNewLabel_2);
		setLocationRelativeTo(null);
	
		listTxt.add(txtCep);
		listTxt.add(txtBairro);
		listTxt.add(txtCidade);
		listTxt.add(txtCPF);
		listTxt.add(txtEndereco);
		listTxt.add(txtFone);
		listTxt.add(txtNome);
		listTxt.add(txtNumero);
//		listTxt.add(txtComplemento);
		//		listTxt.add(txtId);
		
		listCb.add(cboUf);
		
		limparcampos = new ClearFields(listTxt, listCb);
	}

	/**
	 * Method to find CEP (ZIP code) via "republica virtual"
	 */
	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCep.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboUf.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					if (resultado.equals("1")) {
						System.out.println("Ok");
						txtComplemento.setText(null);
						txtNumero.setText(null);

					} else {
						JOptionPane.showMessageDialog(null, "CEP nÃ£o encontrado");
					}
				}
			}
			txtEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void limparcamposmtd() {
		limparcampos = new ClearFields(listTxt, listCb);
		
		listTxt.add(txtId);
		listTxt.add(txtComplemento);
		limparcampos.clear(listTxt, listCb);
		listTxt.remove(txtId);
		listTxt.remove(txtComplemento);
	}

	/**
	 * Method to validate every JtextField and submit to database
	 */
	public void adicionar() {
		String comando = "insert into clientes(nome,fone,cep,endereco,numero,complemento,bairro,cidade,uf,cpf) values(?,?,?,?,?,?,?,?,?,?)";

		jlistvalidate = new JListTextValidate(listTxt, listCb);
		if(jlistvalidate.IsEmpty(listTxt, listCb)){
			
			try {
				con = dao.conectar();
				pst = con.prepareStatement(comando);
				
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtFone.getText());
				pst.setString(3, txtCep.getText());
				pst.setString(4, txtEndereco.getText());
				pst.setString(5, txtNumero.getText());
				pst.setString(6, txtComplemento.getText());
				pst.setString(7, txtBairro.getText());
				pst.setString(8, txtCidade.getText());
				pst.setString(9, String.valueOf(cboUf.getSelectedItem()));
				pst.setString(10, txtCPF.getText());

				pst.executeUpdate();

				JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso!");
				limparcamposmtd();
				con.close();
			} catch (SQLIntegrityConstraintViolationException SQLIntegry) {
				JOptionPane.showInternalMessageDialog(null, "CPF já em uso");
			} catch (SQLException SQLIntegry) {SQLIntegry.printStackTrace();} 
			catch (Exception e) {e.printStackTrace();};
		}
	}
	
	
	/**
	 * Method to remove Client from Id
	 */
	private void remove() {
		con = dao.conectar();
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste Cliente?", "Atenção!",JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			try {
				String delete = "delete from clientes where idcli = ?;";
				con = dao.conectar();

				pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				pst.executeUpdate();

				con.close();
				JOptionPane.showInternalConfirmDialog(null, "Cliente removidos com sucesso");
				limparcamposmtd();
				btnAdicionar.setEnabled(true);
				btnEditar.setEnabled(false);
				btnExcluir.setEnabled(false);
				
			} catch (java.sql.SQLIntegrityConstraintViolationException SQLInteg) {
				JOptionPane.showInternalMessageDialog(null, "Não pode excluir o cliente, pois Tem registro OS");
				System.out.println(SQLInteg);
			} catch (Exception e) {e.printStackTrace();}
		}
	}
	/**
	 * Method to list clients by name in DefaultListModel
	 */
	@SuppressWarnings("unchecked")
	private void listarClientes() {
		DefaultListModel<String> modelo = new DefaultListModel<>();
		listClientes.setModel(modelo);
		String type = "Select * from clientes where nome like '" + txtNome.getText() + "%'" + " order by nome ";
		try {

			con = dao.conectar();
			pst = con.prepareStatement(type);
			rs = pst.executeQuery();
			System.out.println("Conexão");
			while (rs.next()) {
				listClientes.setVisible(true);
				scrollPane.setVisible(true);
				modelo.addElement(rs.getString(2));
				if (txtNome.getText().isEmpty()) {
					listClientes.setVisible(false);
					scrollPane.setVisible(false);
				}
			}
			con.close();
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void ItensClientesLista() {
		int linha = listClientes.getSelectedIndex();
		String comando = "Select * from clientes where nome like '" + txtNome.getText() + "%'" + " order by nome limit "
				+ (linha) + ", 1";
		if (linha >= 0) {
			try {
				con = dao.conectar();
				pst = con.prepareStatement(comando);
				rs = pst.executeQuery();

				if (rs.next()) {
					
					scrollPane.setVisible(false);
					txtId.setText(rs.getString(1));
					txtNome.setText(rs.getString(2));
					txtFone.setText(rs.getString(3));
					txtCep.setText(rs.getString(4));
					txtEndereco.setText(rs.getString(5));
					txtNumero.setText(rs.getString(6));
					txtComplemento.setText(rs.getString(7));
					txtBairro.setText(rs.getString(8));
					txtCidade.setText(rs.getString(9));
					cboUf.setSelectedItem(rs.getString(10));
					txtCPF.setText(rs.getString(11));
					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);
					btnAdicionar.setEnabled(false);
				}
			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			scrollPane.setVisible(false);
		}
	}
	/**
	 * Method to update an entire data by id
	 */
	public void editar() {
		
		jlistvalidate = new JListTextValidate(listTxt, listCb);
		if (jlistvalidate.IsEmpty(listTxt, listCb)) {
			String update = "update clientes set nome=?,fone=?,cep=?,endereco=?,numero=?,complemento=?,bairro=?,cidade=?,uf=?,cpf=? where idcli=?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(update);
				
				pst.setString(11, txtId.getText());
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtFone.getText());
				pst.setString(3, txtCep.getText());
				pst.setString(4, txtEndereco.getText());
				pst.setString(5, txtNumero.getText());
				pst.setString(6, txtComplemento.getText());
				pst.setString(7, txtBairro.getText());
				pst.setString(8, txtCidade.getText());
				pst.setString(9, String.valueOf(cboUf.getSelectedItem()));
				pst.setString(10, txtCPF.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Dados contato editados com sucesso.");
				
				limparcamposmtd();
				
				btnAdicionar.setEnabled(true);
				btnEditar.setEnabled(false);
				btnExcluir.setEnabled(false);
				con.close();
			}catch (SQLIntegrityConstraintViolationException se1) {
				JOptionPane.showInternalMessageDialog(null, "CPF já em uso");
			}  catch (SQLException se) {
				JOptionPane.showMessageDialog(null, se);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1);
			}
		}
	}
	/**
	 * Method to define only Number chars
	 * @param only
	 */
	public void onlyNum(KeyEvent e) {
		char c = e.getKeyChar();
		if (Character.isLetter(c)) {
			e.consume();
		}
	}
}

package view.didatex;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Iterator;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.border.EtchedBorder;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import model.DAO;
import util.Validador;

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

public class FornecedorProf extends JDialog {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	private JTextField txtCep;
	private JTextField txtFone;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtBairro;
	private JTextField txtComplemento;
	private JTextField txtCidade;
	private JTextField txtId;
	private JComboBox cboUf;
	private JScrollPane scrollPane;
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JList listFornecedores;
	private JButton btnExcluir_1;
	private JTextField txtCPFCNPJ;
	private JLabel lblNewLabel_1_1_1_3;
	private JTextField txtIe;
	private JLabel lblNewLabel_3;
	private JTextField txtFantasia;
	private JTextField txtVendedor;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField txtEmail;
	private JLabel lblNewLabel_6;
	private JTextField txtRazao;
	private JTextField txtSite;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FornecedorProf dialog = new FornecedorProf();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("unchecked")
	public FornecedorProf() {
		setTitle("Fornecedor");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FornecedorProf.class.getResource("/img/UsersIcon2.png")));
		setResizable(false);
		setBounds(100, 100, 591, 468);
		getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBounds(330, 169, 235, 67);
		getContentPane().add(scrollPane);

		listFornecedores = new JList();
		scrollPane.setViewportView(listFornecedores);
		listFornecedores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ItensFornecedoresLista();
			}
		});

		txtCep = new JTextField();
		txtCep.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				onlyNum(e);
			}
		});

		txtCep.setColumns(10);
		txtCep.setBounds(41, 214, 149, 21);
		getContentPane().add(txtCep);

		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setBounds(10, 11, 27, 21);
		getContentPane().add(lblNewLabel);

		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setColumns(10);
		txtId.setBounds(30, 11, 80, 21);
		getContentPane().add(txtId);

		JLabel lblNewLabel_1_1 = new JLabel("CEP");
		lblNewLabel_1_1.setBounds(10, 214, 33, 21);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblFone = new JLabel("Telefone");
		lblFone.setBounds(226, 75, 56, 21);
		getContentPane().add(lblFone);

		txtFone = new JTextField();
		txtFone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				onlyNum(e);
			}
		});
		txtFone.setColumns(10);
		txtFone.setBounds(277, 75, 112, 21);
		getContentPane().add(txtFone);
		txtFone.setDocument(new Validador(15));

		JButton btnCep = new JButton("Buscar CEP");
		btnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();
			}
		});
		btnCep.setBounds(200, 213, 123, 23);
		getContentPane().add(btnCep);
		txtCep.setDocument(new Validador(10));

		JLabel lblNewLabel_1_1_1 = new JLabel("Endereço");
		lblNewLabel_1_1_1.setBounds(10, 256, 56, 21);
		getContentPane().add(lblNewLabel_1_1_1);

		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(71, 256, 292, 21);
		getContentPane().add(txtEndereco);
		txtEndereco.setDocument(new Validador(50));

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Numero");
		lblNewLabel_1_1_1_1.setBounds(373, 256, 56, 21);
		getContentPane().add(lblNewLabel_1_1_1_1);

		txtNumero = new JTextField();
		txtNumero.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				onlyNum(e);
			}
		});
		txtNumero.setColumns(10);
		txtNumero.setBounds(421, 256, 43, 20);
		getContentPane().add(txtNumero);
		txtNumero.setDocument(new Validador(10));

		JLabel lblNewLabel_1_1_1_2 = new JLabel("Bairro");
		lblNewLabel_1_1_1_2.setBounds(10, 289, 65, 21);
		getContentPane().add(lblNewLabel_1_1_1_2);

		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(81, 289, 148, 21);
		getContentPane().add(txtBairro);
		txtBairro.setDocument(new Validador(30));

		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("Complemento");
		lblNewLabel_1_1_1_2_1.setBounds(239, 289, 86, 21);
		getContentPane().add(lblNewLabel_1_1_1_2_1);

		txtComplemento = new JTextField();
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(316, 289, 148, 21);
		getContentPane().add(txtComplemento);
		txtComplemento.setDocument(new Validador(20));

		JLabel lblNewLabel_1_1_1_2_2 = new JLabel("Cidade");
		lblNewLabel_1_1_1_2_2.setBounds(61, 322, 65, 21);
		getContentPane().add(lblNewLabel_1_1_1_2_2);

		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(132, 322, 148, 21);
		getContentPane().add(txtCidade);
		txtCidade.setDocument(new Validador(30));

		JLabel lblNewLabel_1 = new JLabel("UF");
		lblNewLabel_1.setBounds(305, 322, 27, 14);
		getContentPane().add(lblNewLabel_1);

		btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(FornecedorProf.class.getResource("/img/cliAdd.png")));
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnAdicionar.setBounds(30, 354, 64, 64);
		getContentPane().add(btnAdicionar);

		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		btnEditar.setIcon(new ImageIcon(FornecedorProf.class.getResource("/img/cliEdit.png")));
		btnEditar.setToolTipText("Editar");
		btnEditar.setEnabled(false);
		btnEditar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnEditar.setBounds(132, 354, 64, 64);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove();
			}
		});
		btnExcluir.setIcon(new ImageIcon(FornecedorProf.class.getResource("/img/cliRemove.png")));
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnExcluir.setBounds(244, 354, 64, 64);
		getContentPane().add(btnExcluir);

		cboUf = new JComboBox();
		cboUf.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUf.setBounds(342, 318, 47, 22);
		getContentPane().add(cboUf);

		btnExcluir_1 = new JButton("");
		btnExcluir_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparcampos();
				btnAdicionar.setEnabled(true);
			}
		});
		btnExcluir_1.setIcon(new ImageIcon(FornecedorProf.class.getResource("/img/erase.png")));
		btnExcluir_1.setToolTipText("Excluir");
		btnExcluir_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnExcluir_1.setBounds(437, 354, 64, 64);
		getContentPane().add(btnExcluir_1);

		txtCPFCNPJ = new JTextField();
		txtCPFCNPJ.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				onlyNum(e);
			}
		});
		txtCPFCNPJ.setBounds(464, 43, 101, 20);
		getContentPane().add(txtCPFCNPJ);
		txtCPFCNPJ.setColumns(10);
		txtCPFCNPJ.setDocument(new Validador(15));

		lblNewLabel_1_1_1_3 = new JLabel("CPF/CNPJ");
		lblNewLabel_1_1_1_3.setBounds(399, 43, 56, 21);
		getContentPane().add(lblNewLabel_1_1_1_3);

		txtIe = new JTextField();
		txtIe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				onlyNum(e);
			}
		});
		txtIe.setColumns(10);
		txtIe.setBounds(466, 75, 86, 20);
		getContentPane().add(txtIe);
		txtCPFCNPJ.setDocument(new Validador(14));

		lblNewLabel_3 = new JLabel("Nome fantasia");
		lblNewLabel_3.setBounds(10, 78, 86, 14);
		getContentPane().add(lblNewLabel_3);

		txtFantasia = new JTextField();
		txtFantasia.setBounds(96, 75, 123, 20);
		getContentPane().add(txtFantasia);
		txtFantasia.setColumns(10);
		txtFantasia.setDocument(new Validador(50));

		txtVendedor = new JTextField();
		txtVendedor.setColumns(10);
		txtVendedor.setBounds(296, 107, 132, 20);
		txtVendedor.setDocument(new Validador(20));
		getContentPane().add(txtVendedor);

		lblNewLabel_4 = new JLabel("vendedor");
		lblNewLabel_4.setBounds(229, 110, 65, 14);
		getContentPane().add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("Email");
		lblNewLabel_5.setBounds(10, 109, 46, 14);
		getContentPane().add(lblNewLabel_5);

		txtEmail = new JTextField();
		txtEmail.setBounds(61, 107, 158, 20);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		txtEmail.setDocument(new Validador(50));

		lblNewLabel_6 = new JLabel("Razão social");
		lblNewLabel_6.setBounds(10, 46, 65, 14);
		getContentPane().add(lblNewLabel_6);

		txtRazao = new JTextField();
		txtRazao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DefaultListModel<String> model = new DefaultListModel<>();
				listFornecedores.setModel(model);
				String keyRelease = "Select * from fornecedoresDida where razao like'" + txtRazao.getText() + "%'"
						+ "order by razao";

				try {
					con = dao.conectar();
					pst = con.prepareStatement(keyRelease);
					rs = pst.executeQuery();
					while (rs.next()) {
						listFornecedores.setVisible(true);
						scrollPane.setVisible(true);
						model.addElement(rs.getString(2));
						if (txtRazao.getText().isEmpty()) {
							listFornecedores.setVisible(false);
							scrollPane.setVisible(false);
						}
					}
				} catch (SQLException SQLe) {
					// TODO: handle exception
					SQLe.printStackTrace();
				} catch (Exception se) {
					// TODO: handle exception
					se.printStackTrace();
				}
//				try {
//
//					con = dao.conectar();
//					pst = con.prepareStatement(type);
//					rs = pst.executeQuery();
//					System.out.println("Conexão");
//					while (rs.next()) {
//						listClientes.setVisible(true);
//						scrollPane.setVisible(true);
//						modelo.addElement(rs.getString(2));
//						if (txtNome.getText().isEmpty()) {
////							System.out.println("Condição");
//							listClientes.setVisible(false);
//							scrollPane.setVisible(false);
//						}
//					}
//					con.close();
//				} catch (SQLException se) {
//					System.out.println(se);
//				} catch (Exception e) {
//					System.out.println(e);
//				}
			}
		});
		txtRazao.setBounds(87, 43, 302, 20);
		getContentPane().add(txtRazao);
		txtRazao.setColumns(10);

		txtSite = new JTextField();
		txtSite.setColumns(10);
		txtSite.setBounds(61, 169, 185, 20);
		getContentPane().add(txtSite);

		lblNewLabel_7 = new JLabel("Site");
		lblNewLabel_7.setBounds(10, 171, 46, 14);
		getContentPane().add(lblNewLabel_7);

		lblNewLabel_2 = new JLabel("IE");
		lblNewLabel_2.setBounds(409, 75, 46, 20);
		getContentPane().add(lblNewLabel_2);

	}

	private void limparcampos() {
		txtId.setText(null);
//		txtNome.setText(null);

		txtRazao.setText(null);
		txtFantasia.setText(null);
		txtVendedor.setText(null);
		txtEmail.setText(null);
		txtSite.setText(null);
		txtIe.setText(null);

		txtFone.setText(null);
		txtCep.setText(null);
		txtCPFCNPJ.setText(null);

		txtEndereco.setText(null);
		txtNumero.setText(null);
		txtBairro.setText(null);
		txtComplemento.setText(null);
		txtCidade.setText(null);
		txtNumero.setText(null);

		cboUf.setSelectedItem("");

		scrollPane.setVisible(false);
	}

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
			System.out.println(e);
		}
	}

	public void adicionar() {
		String comando = "insert into fornecedoresDida(razao,fantasia,fone,"
				+ "vendedor,email,site,cep,cpfcnpj,ie,endereco,numero,complemento" + ",bairro,cidade,uf)"
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//		} else if (txtId.getText().isEmpty()) {
//			
//		}
		if (txtRazao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "A Razao deve ser preenchido");
		} else if (txtCPFCNPJ.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O CPF/CNPJ deve ser preenchido");
		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O cep deve ser preenchido");
		} else if (txtIe.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O Ie deve ser preenchido");
		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O telefone deve ser preenchido");
		} else if (txtFantasia.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "A Cidade deve ser preenchida");
		} else if (txtVendedor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O vendedor deve ser preenchido");
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O email deve ser preenchida");
		} else if (txtSite.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O site deve ser preenchida");
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O endereço deve ser preenchida");
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O numero deve ser preenchida");
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O bairro deve ser preenchida");
		} else if (String.valueOf(cboUf.getSelectedItem()) == "") {
			JOptionPane.showMessageDialog(null, "O UF deve ser selecionado");
		} else {
			try {
				con = dao.conectar();
				pst = con.prepareStatement(comando);

				/**
				 * 
				 */
//				pst.setString(1, txtNome.getText());

				pst.setString(1, txtRazao.getText());
				pst.setString(2, txtFantasia.getText());
				pst.setString(3, txtFone.getText());
				pst.setString(4, txtVendedor.getText());
				pst.setString(5, txtEmail.getText());
				pst.setString(6, txtSite.getText());
				pst.setString(7, txtCep.getText());
				pst.setString(8, txtCPFCNPJ.getText());
				pst.setString(9, txtIe.getText());
				pst.setString(10, txtEndereco.getText());
				pst.setString(11, txtNumero.getText());
				pst.setString(12, txtComplemento.getText());
				pst.setString(13, txtBairro.getText());
				pst.setString(14, txtCidade.getText());
				pst.setString(15, String.valueOf(cboUf.getSelectedItem()));

				pst.executeUpdate();

				JOptionPane.showMessageDialog(null, "Fornecedor adicionado com sucesso!");
				limparcampos();
				con.close();
			} catch (SQLIntegrityConstraintViolationException se1) {
				JOptionPane.showInternalMessageDialog(null, "CPF já em uso");
			} catch (SQLException se) {
				System.out.println(se);

			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}

	private void remove() {
		con = dao.conectar();
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste Cliente?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			try {
				String delete = "delete from fornecedoresDida where idfornecedores = ?;";
				con = dao.conectar();

				pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				pst.executeUpdate();

				con.close();
				JOptionPane.showMessageDialog(null, "removido com sucesso");
				limparcampos();
				btnAdicionar.setEnabled(true);
				btnEditar.setEnabled(false);
				btnExcluir.setEnabled(false);

//			limparCampos();
			} catch (java.sql.SQLIntegrityConstraintViolationException se) {
				//

				System.out.println(se);
			} catch (Exception e) {
				//
				System.out.println(e);
			}
		}
	}// fim do remove

	private void ItensFornecedoresLista() {
		int linha = listFornecedores.getSelectedIndex();
		String comando = "Select * from fornecedoresDida where razao like '" + txtRazao.getText() + "%'"
				+ " order by razao limit " + (linha) + ", 1";
		if (linha >= 0) {
			try {
				con = dao.conectar();
				pst = con.prepareStatement(comando);
				rs = pst.executeQuery();

				if (rs.next()) {
					scrollPane.setVisible(false);

					txtId.setText(rs.getString(1));
//					txtNome.setText(rs.getString(2));
					txtRazao.setText(rs.getString(2));
					txtFantasia.setText(rs.getString(3));
					txtFone.setText(rs.getString(4));
					txtVendedor.setText(rs.getString(5));
					txtEmail.setText(rs.getString(6));
					txtSite.setText(rs.getString(7));
					txtCep.setText(rs.getString(8));
					txtCPFCNPJ.setText(rs.getString(9));
					txtIe.setText(rs.getString(10));
					txtEndereco.setText(rs.getString(11));
					txtNumero.setText(rs.getString(12));
					txtComplemento.setText(rs.getString(13));
					txtBairro.setText(rs.getString(14));
					txtCidade.setText(rs.getString(15));
					cboUf.setSelectedItem(rs.getString(16));

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

		System.out.println(linha);
	}

	public void editar() {
		if (txtRazao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "A Razao deve ser preenchido");
		} else if (txtCPFCNPJ.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O CPF/CNPJ deve ser preenchido");
		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O cep deve ser preenchido");
		} else if (txtIe.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O Ie deve ser preenchido");
		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O telefone deve ser preenchido");
		} else if (txtFantasia.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "A Cidade deve ser preenchida");
		} else if (txtVendedor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O vendedor deve ser preenchido");
		} else if (txtEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O email deve ser preenchida");
		} else if (txtSite.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O site deve ser preenchida");
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O endereço deve ser preenchida");
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O numero deve ser preenchida");
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O bairro deve ser preenchida");
		} else if (String.valueOf(cboUf.getSelectedItem()) == "") {
			JOptionPane.showMessageDialog(null, "O UF deve ser selecionado");
		} else {
			String update = "update fornecedoresDida set razao=?,fantasia=?,fone=?,vendedor=?,email=?,site=?,cep=?,cpfcnpj=?,ie=?,endereco=?,numero=?,complemento=?,bairro=?,cidade=?,uf=? where idfornecedores=?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(update);

				pst.setString(16, txtId.getText());

				pst.setString(1, txtRazao.getText());
				pst.setString(2, txtFantasia.getText());
				pst.setString(3, txtFone.getText());
				pst.setString(4, txtVendedor.getText());
				pst.setString(5, txtEmail.getText());
				pst.setString(6, txtSite.getText());
				pst.setString(7, txtCep.getText());
				pst.setString(8, txtCPFCNPJ.getText());
				pst.setString(9, txtIe.getText());
				pst.setString(10, txtEndereco.getText());
				pst.setString(11, txtNumero.getText());
				pst.setString(12, txtComplemento.getText());
				pst.setString(13, txtBairro.getText());
				pst.setString(14, txtCidade.getText());
				pst.setString(15, String.valueOf(cboUf.getSelectedItem()));

				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Dados do fornecedor contato editados com sucesso.");
				limparcampos();
				btnAdicionar.setEnabled(true);
				btnEditar.setEnabled(false);
				btnExcluir.setEnabled(false);
				con.close();
			} catch (SQLIntegrityConstraintViolationException se1) {
				JOptionPane.showInternalMessageDialog(null, "CPF já em uso");
			} catch (SQLException se) {
				JOptionPane.showMessageDialog(null, se);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1);
			}
		}
	}

	public void onlyNum(KeyEvent e) {
		char c = e.getKeyChar();
		if (Character.isLetter(c)) {
			e.consume();
		}
	}
}// Fim do codigo
/**
 * buscarCep
 */

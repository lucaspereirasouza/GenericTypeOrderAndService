package view;

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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.border.EtchedBorder;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import model.DAO;
import util.JListTextValidate;
import util.ClearFields;
import util.Validator;

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

public class Fornecedor extends JDialog {
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
	private JComboBox cboUf;
	private JScrollPane scrollPane;
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JList listClientes;
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
	
	private List<JTextField> listTxt = new ArrayList<JTextField>();
	private List<JComboBox> listCb = new ArrayList<JComboBox>();
	private JListTextValidate jlistvalidate;
	private ClearFields limparcampos;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Fornecedor dialog = new Fornecedor();
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
	public Fornecedor() {
		setTitle("Fornecedor");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Fornecedor.class.getResource("/img/UsersIcon2.png")));
		setResizable(false);
		setBounds(100, 100, 591, 468);
		getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBounds(142, 60, 247, 67);
		getContentPane().add(scrollPane);

		listClientes = new JList();
		scrollPane.setViewportView(listClientes);
		listClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ItensClientesLista();
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

		JLabel lblNome = new JLabel("Fornecedor");
		lblNome.setBounds(10, 43, 132, 21);
		getContentPane().add(lblNome);

		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				listarClientes();
			}

		});
		txtNome.setColumns(10);
		txtNome.setBounds(142, 43, 247, 21);
		txtNome.setDocument(new Validator(50));
		getContentPane().add(txtNome);

		JLabel lblNewLabel_1_1 = new JLabel("CEP");
		lblNewLabel_1_1.setBounds(10, 214, 33, 21);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblFone = new JLabel("Telefone");
		lblFone.setBounds(10, 106, 65, 21);
		getContentPane().add(lblFone);

		txtFone = new JTextField();
		txtFone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				onlyNum(e);
			}
		});
		txtFone.setColumns(10);
		txtFone.setBounds(93, 106, 80, 21);
		getContentPane().add(txtFone);
		txtFone.setDocument(new Validator(15));

		JButton btnCep = new JButton("Buscar CEP");
		btnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();
			}
		});
		btnCep.setBounds(200, 213, 123, 23);
		getContentPane().add(btnCep);
		txtCep.setDocument(new Validator(10));

		JLabel lblNewLabel_1_1_1 = new JLabel("Endereço");
		lblNewLabel_1_1_1.setBounds(10, 256, 56, 21);
		getContentPane().add(lblNewLabel_1_1_1);

		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(71, 256, 292, 21);
		getContentPane().add(txtEndereco);
		txtEndereco.setDocument(new Validator(50));

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
		txtNumero.setBounds(437, 257, 43, 20);
		getContentPane().add(txtNumero);
		txtNumero.setDocument(new Validator(10));

		JLabel lblNewLabel_1_1_1_2 = new JLabel("Bairro");
		lblNewLabel_1_1_1_2.setBounds(10, 289, 65, 21);
		getContentPane().add(lblNewLabel_1_1_1_2);

		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(81, 289, 148, 21);
		getContentPane().add(txtBairro);
		txtBairro.setDocument(new Validator(30));

		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("Complemento");
		lblNewLabel_1_1_1_2_1.setBounds(239, 289, 124, 21);
		getContentPane().add(lblNewLabel_1_1_1_2_1);

		txtComplemento = new JTextField();
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(354, 289, 148, 21);
		getContentPane().add(txtComplemento);
		txtComplemento.setDocument(new Validator(20));

		JLabel lblNewLabel_1_1_1_2_2 = new JLabel("Cidade");
		lblNewLabel_1_1_1_2_2.setBounds(61, 322, 65, 21);
		getContentPane().add(lblNewLabel_1_1_1_2_2);

		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(132, 322, 148, 21);
		getContentPane().add(txtCidade);
		txtCidade.setDocument(new Validator(30));

		JLabel lblNewLabel_1 = new JLabel("UF");
		lblNewLabel_1.setBounds(305, 322, 27, 14);
		getContentPane().add(lblNewLabel_1);

		btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(Fornecedor.class.getResource("/img/cliAdd.png")));
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
		btnEditar.setIcon(new ImageIcon(Fornecedor.class.getResource("/img/cliEdit.png")));
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
		btnExcluir.setIcon(new ImageIcon(Fornecedor.class.getResource("/img/cliRemove.png")));
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
				limparcampos = new ClearFields(listTxt, listCb);
				limparcampos.clear(listTxt, listCb);
				
			}
		});
		btnExcluir_1.setIcon(new ImageIcon(Fornecedor.class.getResource("/img/erase.png")));
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
		txtCPFCNPJ.setDocument(new Validator(15));

		lblNewLabel_1_1_1_3 = new JLabel("CPF/CNPJ");
		lblNewLabel_1_1_1_3.setBounds(390, 43, 65, 21);
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
		txtCPFCNPJ.setDocument(new Validator(14));

		lblNewLabel_3 = new JLabel("Nome fantasia");
		lblNewLabel_3.setBounds(180, 109, 143, 14);
		getContentPane().add(lblNewLabel_3);

		txtFantasia = new JTextField();
		txtFantasia.setBounds(332, 107, 233, 20);
		getContentPane().add(txtFantasia);
		txtFantasia.setColumns(10);
		txtFantasia.setDocument(new Validator(50));

		txtVendedor = new JTextField();
		txtVendedor.setColumns(10);
		txtVendedor.setBounds(332, 139, 132, 20);
		txtVendedor.setDocument(new Validator(20));
		getContentPane().add(txtVendedor);

		lblNewLabel_4 = new JLabel("vendedor");
		lblNewLabel_4.setBounds(229, 141, 94, 14);
		getContentPane().add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("Email");
		lblNewLabel_5.setBounds(10, 140, 46, 14);
		getContentPane().add(lblNewLabel_5);

		txtEmail = new JTextField();
		txtEmail.setBounds(61, 138, 158, 20);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		txtEmail.setDocument(new Validator(50));

		lblNewLabel_6 = new JLabel("Razão social");
		lblNewLabel_6.setBounds(10, 78, 100, 14);
		getContentPane().add(lblNewLabel_6);

		txtRazao = new JTextField();
		txtRazao.setBounds(120, 75, 269, 20);
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
		lblNewLabel_2.setBounds(400, 75, 55, 20);
		getContentPane().add(lblNewLabel_2);
		setLocationRelativeTo(null);
	
	
		listTxt.add(txtCep);
		listTxt.add(txtBairro);
		listTxt.add(txtCidade);
		listTxt.add(txtComplemento);
		listTxt.add(txtCPFCNPJ);
		listTxt.add(txtEmail);
		listTxt.add(txtEndereco);
		listTxt.add(txtFantasia);
		listTxt.add(txtFone);
		listTxt.add(txtIe);
		listTxt.add(txtNome);
		listTxt.add(txtNumero);
		listTxt.add(txtRazao);
		listTxt.add(txtSite);
		listTxt.add(txtVendedor);

		listCb.add(cboUf);
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
		String comando = "insert into fornecedores(nome,razao,fantasia,fone,vendedor,email,site,cep,cpfcnpj,ie,endereco,numero,complemento,bairro,cidade,uf) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String comandoTeste = "insert into fornecedores(nome,razao,fantasia,fone,vendedor,email,site,cep,cpfcnpj,ie,endereco,numero,complemento,bairro,cidade,uf) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		
		jlistvalidate = new JListTextValidate(listTxt, listCb);
		limparcampos = new ClearFields(listTxt, listCb);
		
		if (jlistvalidate.IsEmpty(listTxt, listCb)) {
			try {
				con = dao.conectar();
				pst = con.prepareStatement(comandoTeste);

				pst.setString(1, txtNome.getText());
				pst.setString(2, txtRazao.getText());
				pst.setString(3, txtFantasia.getText());
				pst.setString(4, txtFone.getText());
				pst.setString(5, txtVendedor.getText());
				pst.setString(6, txtEmail.getText());
				pst.setString(7, txtSite.getText());
				pst.setString(8, txtCep.getText());
				pst.setString(9, txtCPFCNPJ.getText());
				pst.setString(10, txtIe.getText());
				pst.setString(11, txtEndereco.getText());
				pst.setString(12, txtNumero.getText());
				pst.setString(13, txtComplemento.getText());
				pst.setString(14, txtBairro.getText());
				pst.setString(15, txtCidade.getText());
				pst.setString(16, String.valueOf(cboUf.getSelectedItem()));
				
				pst.executeUpdate();

				JOptionPane.showMessageDialog(null, "Fornecedor adicionado com sucesso!");
				limparcampos.clear(listTxt,listCb);
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
				String delete = "delete from fornecedores where idfornecedores = ?;";
				con = dao.conectar();

				pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				pst.executeUpdate();

				con.close();
				JOptionPane.showInternalMessageDialog(null, "Fornecedor removido com sucesso");
				limparcampos = new ClearFields(listTxt, listCb);
				limparcampos.clear(listTxt,listCb);
				btnAdicionar.setEnabled(true);
				btnEditar.setEnabled(false);
				btnExcluir.setEnabled(false);

			} catch (java.sql.SQLIntegrityConstraintViolationException se) {
				//
				JOptionPane.showInternalMessageDialog(null, "removido com sucesso");
				se.printStackTrace();
			} catch (Exception e) {
				//
				e.printStackTrace();
			}
		}
	}// fim do remove

//	private void pesquisar() {
//		String comando = "select * from clientes where nome = ?";
//
//		try {
//			con = dao.conectar();
//			pst = con.prepareStatement(comando);
//			pst.setString(1, txtNome.getText());
//			rs = pst.executeQuery();
//
//			if (rs.next()) {
//				txtId.setText(rs.getString(1));
//				txtNome.setText(rs.getString(2));
//				txtFone.setText(rs.getString(3));
//				txtCep.setText(rs.getString(4));
//				txtEndereco.setText(rs.getString(5));
//				txtNumero.setText(rs.getString(6));
//				txtBairro.setText(rs.getString(7));
//				txtComplemento.setText(rs.getString(8));
//				txtCidade.setText(rs.getString(7));
//				cboUf.setSelectedItem(rs.getString(9));
//			}
//
//		} catch (SQLException se) {
//			System.out.println(se);
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}

	private void listarClientes() {
		DefaultListModel<String> modelo = new DefaultListModel<>();
		listClientes.setModel(modelo);
		String type = "Select * from fornecedores where nome like '" + txtNome.getText() + "%'" + " order by nome ";
		try {

			con = dao.conectar();
			pst = con.prepareStatement(type);
			rs = pst.executeQuery();
			while (rs.next()) {
				listClientes.setVisible(true);
				scrollPane.setVisible(true);
				modelo.addElement(rs.getString(2));
				if (txtNome.getText().isEmpty()) {
//					System.out.println("Condição");
					listClientes.setVisible(false);
					scrollPane.setVisible(false);
				}
			}
			con.close();
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void ItensClientesLista() {
		int linha = listClientes.getSelectedIndex();
		String comando = "Select * from fornecedores where nome like '" + txtNome.getText() + "%'"
				+ " order by nome limit " + (linha) + ", 1";
		if (linha >= 0) {
			try {
				con = dao.conectar();
				pst = con.prepareStatement(comando);
				rs = pst.executeQuery();

				if (rs.next()) {
					scrollPane.setVisible(false);

					txtId.setText(rs.getString(1));
					txtNome.setText(rs.getString(2));
					txtRazao.setText(rs.getString(3));
					txtFantasia.setText(rs.getString(4));
					txtFone.setText(rs.getString(5));
					txtVendedor.setText(rs.getString(6));
					txtEmail.setText(rs.getString(7));
					txtSite.setText(rs.getString(8));
					txtCep.setText(rs.getString(9));
					txtCPFCNPJ.setText(rs.getString(10));
					txtIe.setText(rs.getString(11));
					txtEndereco.setText(rs.getString(12));
					txtNumero.setText(rs.getString(13));
					txtComplemento.setText(rs.getString(14));
					txtBairro.setText(rs.getString(15));
					txtCidade.setText(rs.getString(16));
					cboUf.setSelectedItem(rs.getString(17));

					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);
					btnAdicionar.setEnabled(false);
				}
			} catch (SQLException se) {

			} catch (Exception e) {

			}
		} else {
			scrollPane.setVisible(false);
		}

		System.out.println(linha);
	}

	public void editar() {
		jlistvalidate = new JListTextValidate(listTxt, listCb);
		if(jlistvalidate.IsEmpty(listTxt, listCb)) {
			String update = "update fornecedores set nome=?,razao=?,fantasia=?,fone=?,vendedor=?,email=?,site=?,cep=?,cpfcnpj=?,ie=?,endereco=?,numero=?,complemento=?,bairro=?,cidade=?,uf=? where idfornecedores=?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(update);

				pst.setString(17, txtId.getText());
					
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtRazao.getText());
				pst.setString(3, txtFantasia.getText());
				pst.setString(4, txtFone.getText());
				pst.setString(5, txtVendedor.getText());
				pst.setString(6, txtEmail.getText());
				pst.setString(7, txtSite.getText());
				pst.setString(8, txtCep.getText());
				pst.setString(9, txtCPFCNPJ.getText());
				pst.setString(10, txtIe.getText());
				pst.setString(11, txtEndereco.getText());
				pst.setString(12, txtNumero.getText());
				pst.setString(13, txtComplemento.getText());
				pst.setString(14, txtBairro.getText());
				pst.setString(15, txtCidade.getText());
				pst.setString(16, String.valueOf(cboUf.getSelectedItem()));

				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Dados do fornecedor contato editados com sucesso.");
				
				btnAdicionar.setEnabled(true);
				btnEditar.setEnabled(false);
				btnExcluir.setEnabled(false);
				con.close();
				
				limparcampos = new ClearFields(listTxt, listCb);
				limparcampos.clear(listTxt, listCb);
				
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

package view.configuration;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.configuration.ConfigurationModel;
import util.ClearFields;
import util.JListTextValidate;
import util.configuration.ConfigOperation;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ConfigurationDialog extends JDialog {

	
	private ConfigOperation Operation = new ConfigOperation();
	ConfigurationModel model = new ConfigurationModel();
	
	private List<JTextField> listTxt = new ArrayList<JTextField>();
	private List<JComboBox> listCb = new ArrayList<JComboBox>();
	private JListTextValidate jlistvalidate;
	private ClearFields limparcampos;
	
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_password;
	private JTextField textField_usuario;
	private JTextField textField_ip;
	
	private static boolean visibleWindow = true;
	// private List<JTextField> JtextFieldList = new ArrayList<JTextField>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConfigurationDialog dialog = new ConfigurationDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(visibleWindow);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ConfigurationDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTeste = new JLabel("Senha");
		lblTeste.setBounds(27, 57, 70, 15);
		contentPanel.add(lblTeste);
		{
			JLabel lblTeste_1 = new JLabel("Usuario");
			lblTeste_1.setBounds(27, 28, 70, 15);
			contentPanel.add(lblTeste_1);
		}
		
		textField_password = new JTextField();
		textField_password.setBounds(92, 55, 210, 19);
		contentPanel.add(textField_password);
		textField_password.setColumns(10);
		
		textField_usuario = new JTextField();
		textField_usuario.setColumns(10);
		textField_usuario.setBounds(92, 26, 210, 19);
		contentPanel.add(textField_usuario);
		
		JLabel lblIp = new JLabel("IP");
		lblIp.setBounds(27, 88, 70, 15);
		contentPanel.add(lblIp);
		
		textField_ip = new JTextField();
		textField_ip.setColumns(10);
		textField_ip.setBounds(92, 86, 210, 19);
		contentPanel.add(textField_ip);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"NoDatabase", "MySQL"}));
		comboBox.setBounds(157, 120, 145, 24);
		contentPanel.add(comboBox);
		
		JButton btnLoad = new JButton("LOAD");
		btnLoad.setBounds(105, 233, 72, 25);
		contentPanel.add(btnLoad);
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					model.setDriver(Operation.read("driver"));
					model.setIp(Operation.read("ip"));
					model.setPassword(Operation.read("password"));
					model.setUser(Operation.read("user"));
				
					textField_ip.setText(model.getDriver());
					textField_usuario.setText(model.getUser());
					textField_password.setText(model.getPassword());
					} catch (Exception IOE) {
						IOE.printStackTrace();
					}
			
		}});
		btnLoad.setActionCommand("OK");
		{
			JButton okButton = new JButton("OK");
			okButton.setBounds(288, 233, 54, 25);
			contentPanel.add(okButton);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					ConfigurationModel conf = new ConfigurationModel();
					
					conf.setUser(textField_usuario.getText());
					conf.setPassword(textField_password.getText());
					conf.setIp(textField_ip.getText());
					
					
					
					System.out.println(conf.getUser());
					
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setBounds(347, 233, 81, 25);
			contentPanel.add(cancelButton);
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// validate if there's configured content
					// close
					
				}
			});
			cancelButton.setActionCommand("Cancel");
		}
		
		JButton btnRestaurar = new JButton("RESTAURAR");
		btnRestaurar.setActionCommand("OK");
		btnRestaurar.setBounds(12, 195, 104, 25);
		contentPanel.add(btnRestaurar);
		
		JButton btnAplicar = new JButton("APLICAR");
		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		btnAplicar.setActionCommand("OK");
		btnAplicar.setBounds(182, 233, 94, 25);
		contentPanel.add(btnAplicar);
	}
}

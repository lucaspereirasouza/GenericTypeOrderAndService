package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URL;
import java.awt.Cursor;

public class Sobre extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Sobre dialog = new Sobre();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Sobre() {
		getContentPane().setBackground(new Color(54, 54, 54));
		getContentPane().setForeground(new Color(54, 54, 54));
		setTitle("Sobre");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/AboutIcon.png")));
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 349, 403);
		getContentPane().setLayout(null);
		contentPanel.setBackground(new Color(35, 36, 37));
		contentPanel.setForeground(new Color(53, 54, 54));
		contentPanel.setBounds(0, 0, 479, 364);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setBackground(new Color(35, 36, 37));
		lblNewLabel_2_1.setBounds(20, 142, 135, 119);
		contentPanel.add(lblNewLabel_2_1);
		lblNewLabel_2_1.setIcon(new ImageIcon(Sobre.class.getResource("/img/ConsoleIcon.png")));
		
		JTextArea txtrEsseAplicativoTem = new JTextArea();
		txtrEsseAplicativoTem.setForeground(Color.LIGHT_GRAY);
		txtrEsseAplicativoTem.setFont(new Font("MS Gothic", Font.BOLD, 13));
		txtrEsseAplicativoTem.setBackground(new Color(35, 36, 37));
		txtrEsseAplicativoTem.setEditable(false);
		txtrEsseAplicativoTem.setText("Esse aplicativo tem função de administrar \r\nordens de serviço pela criação, remoção, \r\nedição e pesquisa do Usuario, cliente,\r\nfornecedor e produtos. Com funções\r\nfundamentais como impressão do sistema,\r\ncompatibilidade com barcode scanner, etc.\r\n");
		txtrEsseAplicativoTem.setBounds(10, 11, 329, 119);
		contentPanel.add(txtrEsseAplicativoTem);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setToolTipText("sob licença mit");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				link("https://github.com/lucaspereirasouza/ConsoleX/blob/master/LICENSE");
			}
		});
		lblNewLabel_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_2.setBounds(166, 132, 128, 129);
		contentPanel.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(new ImageIcon(Sobre.class.getResource("/img/mit-license.png")));
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Lucas pereira");
		lblNewLabel_1_1_1.setForeground(new Color(177, 178, 179));
		lblNewLabel_1_1_1.setBounds(10, 305, 164, 23);
		contentPanel.add(lblNewLabel_1_1_1);
		lblNewLabel_1_1_1.setFont(new Font("Square721 BT", Font.BOLD | Font.ITALIC, 18));
		
		JLabel lblNewLabel = new JLabel("github.com/lucaspereirasouza");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(45, 318, 281, 46);
		contentPanel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				link("https://github.com/lucaspereirasouza");
				
//					https://pnrtscr.com/gz5n0s
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel.setForeground(Color.BLUE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel.setForeground(Color.WHITE);
			}
		});
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		
		JLabel lblNewLabel_1_1 = new JLabel("Feito por:");
		lblNewLabel_1_1.setForeground(new Color(177, 178, 179));
		lblNewLabel_1_1.setBackground(new Color(95, 96, 97));
		lblNewLabel_1_1.setBounds(10, 268, 126, 46);
		contentPanel.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Square721 BT", Font.BOLD | Font.ITALIC, 18));
		setLocationRelativeTo(null);
	}
	private void link(String url) {
		Desktop desktop = Desktop.getDesktop();
		try {
			URI URI = new URI(url);
			desktop.browse(URI);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
		}
	}
}

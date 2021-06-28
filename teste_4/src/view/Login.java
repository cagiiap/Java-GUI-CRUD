package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ProcessaPatrimonios;
import model.dao.ConexaoDAO;

public class Login extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel painel;
	private JButton cancelar, login;
	private JLabel nome, password;
	private JTextField txtUsername, txtSenha;
	static ConexaoDAO conecta;
	private ArrayList<String> dados = new ArrayList<>();

	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(420, 420);
		painel = new JPanel();
		setContentPane(painel);
		setLocationRelativeTo(null);
		setLayout(null);
		Color color = new Color(21, 55, 66);
		painel.setBackground(color);

		nome = new JLabel("Username: ");
		nome.setBounds(60, 100, 120, 20);
		nome.setForeground(Color.white);
		painel.add(nome);

		password = new JLabel("Password: ");
		password.setBounds(60, 160, 120, 20);
		password.setForeground(Color.white);
		painel.add(password);

		txtUsername = new JTextField();
		txtUsername.setBounds(150, 100, 160, 20);
		painel.add(txtUsername);

		txtSenha = new JTextField();
		txtSenha.setBounds(150, 160, 160, 20);
		painel.add(txtSenha);

		cancelar = new JButton("Cancel");
		cancelar.setBounds(150, 200, 75, 20);
		cancelar.setFocusable(false);
		cancelar.addActionListener(this);
		painel.add(cancelar);

		login = new JButton("Login");
		login.setBounds(235, 200, 75, 20);
		login.setFocusable(false);
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ResultSet rs = conecta.executaBusca("select * from login");
					try {
						while (rs.next()) {
							dados.add(rs.getString(2) + " " + rs.getString(3));
						}
					} catch (Exception error) {
						error.printStackTrace();
					}
					
					for (String teste : dados) {
						if (teste.equals(txtUsername.getText() + " " + txtSenha.getText())) {
							LaunchPage tela = new LaunchPage();
							tela.setVisible(true);
							dispose();
						}
					}
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "view :" + erro);
				}
			}
		});
		painel.add(login);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					conecta = new ConexaoDAO();
					ProcessaPatrimonios.abrir();
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancelar) {
			dispose();
		}
	}
}

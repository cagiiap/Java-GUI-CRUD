package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.ProcessaPatrimonios;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class LaunchPage extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel painel;
	private JButton myButton, documentation, btnTxt, btnCsv;
	private JLabel Documentation, salvarTxt, salvarCsv;

	LaunchPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 401);
		painel = new JPanel();
		painel.setBackground(Color.WHITE);
		setContentPane(painel);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setIconImage(FormPatrimonios.img.getImage());

		myButton = new JButton("Patrimonios moveis");
		myButton.setBounds(48, 268, 161, 40);
		myButton.setFocusable(false);
		myButton.addActionListener(this);
		painel.add(myButton);

		documentation = new JButton("Documentação");
		documentation.setBounds(675, 268, 161, 40);
		documentation.addActionListener(this);
		documentation.setFocusable(false);
		painel.add(documentation);

		JLabel PatrimonioPanda = new JLabel("New label");
		PatrimonioPanda.setBounds(40, 35, 200, 200);
		PatrimonioPanda.setIcon(new ImageIcon(
				"C:\\Users\\Carlos\\Documents\\GitHub\\Java-GUI-CRUD\\DesignJaqueline\\assets\\panda_carrinho.png"));
		painel.add(PatrimonioPanda);

		Documentation = new JLabel("New label");
		Documentation.setBounds(651, 35, 200, 200);
		Documentation.setIcon(
				new ImageIcon("C:\\Users\\Carlos\\Documents\\GitHub\\Java-GUI-CRUD\\DesignJaqueline\\assets\\google-docs.png"));
		painel.add(Documentation);

		btnTxt = new JButton("Salvar em txt");
		btnTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ProcessaPatrimonios.salvarTxt();
					JOptionPane.showMessageDialog(null, "Salvo com sucesso, verifique o diretório!");
				} catch (Exception err) {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro ao salvar, tente salvar novamente!");
				}
			}
		});
		btnTxt.setBounds(257, 268, 161, 40);
		btnTxt.setFocusable(false);
		painel.add(btnTxt);

		btnCsv = new JButton("Salvar em csv");
		btnCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ProcessaPatrimonios.salvar();
					JOptionPane.showMessageDialog(null, "Salvo com sucesso, verifique o diretório!");
				} catch (Exception err) {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro ao salvar, tente salvar novamente!");
				}
			}
		});
		btnCsv.setFocusable(false);
		btnCsv.setBounds(466, 268, 161, 40);
		painel.add(btnCsv);

		salvarTxt = new JLabel("");
		salvarTxt.setIcon(new ImageIcon("C:\\Users\\Carlos\\Documents\\GitHub\\Java-GUI-CRUD\\DesignJaqueline\\assets\\5_Sem_Titulo_20210701084848.png"));
		salvarTxt.setBounds(466, 57, 140, 156);
		painel.add(salvarTxt);

		salvarCsv = new JLabel("");
		salvarCsv.setIcon(new ImageIcon("C:\\Users\\Carlos\\Documents\\GitHub\\Java-GUI-CRUD\\DesignJaqueline\\assets\\3_Sem_Titulo_20210701090815.png"));
		salvarCsv.setBounds(270, 57, 140, 156);
		painel.add(salvarCsv);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == myButton) {
			dispose();
			new FormPatrimonios().setVisible(true);
		} else if (e.getSource() == documentation) {
			dispose();
			new Documentation().setVisible(true);
		}
	}
}

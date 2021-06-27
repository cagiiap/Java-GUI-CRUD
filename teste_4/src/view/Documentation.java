package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Documentation extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel painel;
	private JLabel imagem, text;
	private JButton back;
	Documentation(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(642, 1050);
		painel = new JPanel();
		setContentPane(painel);
		setLocationRelativeTo(null);
		setLayout(null);
		
		
		back = new JButton();
		back.setBounds(45, 20, 50, 50);
		back.setIcon(new ImageIcon("./assets/back.png"));
		back.setBorderPainted(false);
		back.setFocusable(false);
		back.addActionListener(this);
		painel.add(back);
		 
		text = new JLabel("Clique para voltar");
		text.setBounds(20, 30, 100, 100);
		painel.add(text);
		
		imagem = new JLabel();
		imagem.setIcon(new ImageIcon("./assets/projetoEstacionamento.png"));
		imagem.setBounds(0, 0, 642, 1000);
		
		painel.add(imagem);
	
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == back) {
			dispose();
			new LaunchPage().setVisible(true);
		}
		
	}

}

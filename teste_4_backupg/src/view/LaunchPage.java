package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LaunchPage extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel painel;
	private JButton myButton, documentation;
	

	LaunchPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(420, 420);
		painel = new JPanel();
		setContentPane(painel);
		setLocationRelativeTo(null);
		setLayout(null);
		
		myButton = new JButton("New Window");
		myButton.setBounds(100, 160, 200, 40);
		myButton.setFocusable(false);
		myButton.addActionListener(this);

		painel.add(myButton);
		
		documentation = new JButton();
		documentation.setIcon(new ImageIcon("./assets/google-docs.png"));
		documentation.setBounds(0, 0, 50, 50);
		documentation.addActionListener(this);
		painel.add(documentation);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == myButton) {
			dispose();
			new FormPatrimonios().setVisible(true);
		}else if(e.getSource() == documentation) {
			dispose();
			new Documentation().setVisible(true);
		}
	}
}

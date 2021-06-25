import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class leozinho extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					leozinho frame = new leozinho();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public leozinho() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String [] colunas = {"ID", "Data", "Equipamento", "Valor"};
		Object[][] dados = {
				{"290", "28/08/2003", "Jaqueline", "1290,2"},
				{"232", "28/08/2003", "Jaqueline", "1290,2"},
				{"231", "28/08/2003", "Jaqueline", "1290,2"},
				{"296", "28/08/2003", "Jaqueline", "1290,2"},
				{"123", "28/08/2003", "Jaqueline", "1290,2"},
		};
		
		JTable tabela = new JTable(dados, colunas);
		JScrollPane barraRolagem = new JScrollPane(tabela);
		tabela.setBounds(400, 500, 500, 500);
		contentPane.add(tabela);
		contentPane.add(barraRolagem);
		
	}
}

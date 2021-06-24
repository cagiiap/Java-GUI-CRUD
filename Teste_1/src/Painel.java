import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;

public class Painel extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDate;
	private JLabel lblNewLabel;
	private JTextField textFieldID;
	private JLabel lblNewLabel_1;
	private JTextField textFieldEquipa;
	private JLabel lblNewLabel_2;
	private JTextField textFieldValue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Painel frame = new Painel();
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
	public Painel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Date = new JLabel("Data");
		Date.setBounds(10, 25, 46, 14);
		contentPane.add(Date);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(50, 22, 140, 20);
		contentPane.add(textFieldDate);
		textFieldDate.setColumns(10);
		
		lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(200, 25, 21, 14);
		contentPane.add(lblNewLabel);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(225, 22, 140, 20);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Equipamento");
		lblNewLabel_1.setBounds(375, 25, 70, 14);
		contentPane.add(lblNewLabel_1);
		
		textFieldEquipa = new JTextField();
		textFieldEquipa.setBounds(455, 22, 140, 20);
		contentPane.add(textFieldEquipa);
		textFieldEquipa.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Valor");
		lblNewLabel_2.setBounds(605, 25, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		textFieldValue = new JTextField();
		textFieldValue.setBounds(651, 22, 86, 20);
		contentPane.add(textFieldValue);
		textFieldValue.setColumns(10);
		
		JButton AdicionarAqui = new JButton("Adicionar");
		AdicionarAqui.setBounds(50, 64, 89, 23);
		contentPane.add(AdicionarAqui);
		
		JButton Remove = new JButton("Remover");
		Remove.setBounds(248, 64, 89, 23);
		contentPane.add(Remove);
		
		JButton Alterar = new JButton("Alterar");
		Alterar.setBounds(347, 64, 89, 23);
		contentPane.add(Alterar);
		
		JButton Reset = new JButton("Resetar");
		Reset.setBounds(149, 64, 89, 23);
		contentPane.add(Reset);
		
		JDateChooser dtInicial = new JDateChooser();
		dtInicial.setBounds(50, 147, 140, 20);
		//dtInicial.setDate(new Date());
		dtInicial.getDateEditor().getUiComponent().addFocusListener((FocusListener) new FocusAdapter() {
			public void focusGained(FocusEvent e) {
			((JTextFieldDateEditor)e.getSource()).selectAll();
			}
			});
		contentPane.add(dtInicial);
		
		System.out.println(dtInicial.getDate());
	}
	
}

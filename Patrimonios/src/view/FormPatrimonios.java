package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.ProcessaPatrimonios;

public class FormPatrimonios extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JPanel painel;
	private JTextArea texto;
	private JTextField id, data, equipamento, valor;
	private JButton Salvar, Cancelar, adicionar, alterar, excluir, listarPeriodo; 

	FormPatrimonios(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Registro de Patrimonio");
		setSize(800, 800);
		painel = new JPanel();
		setContentPane(painel);
		setLocationRelativeTo(null);
		setLayout(null);
		
		JLabel labelData = new JLabel("Data: ");
		labelData.setBounds(50,160,100,20);
		MaskFormatter mascaraData = null;      
		try {
			mascaraData = new MaskFormatter("##/##/####");
			mascaraData.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JFormattedTextField jFormattedTextData = new JFormattedTextField(mascaraData);
		jFormattedTextData.setBounds(150,160,100,20);
		painel.add(jFormattedTextData);
	}

	public void actionPerformed(ActionEvent e) {
		
	}
	
	public static void main(String[] args) {
		
		ProcessaPatrimonios.abrir();
		new FormPatrimonios().setVisible(true);
		
	}
}

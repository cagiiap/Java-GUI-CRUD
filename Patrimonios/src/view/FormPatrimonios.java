package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.ProcessaPatrimonios;
import model.ItemPatrimonio;
import model.dao.PatrimonioDAO;

public class FormPatrimonios extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel painel;
	private JTextArea texto;
	private JTextField id, equipamento;
	private JFormattedTextField data, valor;
	private JButton salvar, Cancelar, adicionar, alterar, excluir, listarPeriodo;

	FormPatrimonios() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Registro de Patrimonio");
		setSize(800, 800);
		painel = new JPanel();
		setContentPane(painel);
		setLocationRelativeTo(null);
		setLayout(null);
		
		//texto = new JTextArea(listar());
		//texto.setEditable(false);
		//texto.setBounds(20, 20, 400, 400);
		//texto.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		//painel.add(texto);

		salvar = new JButton("Salvar");
		salvar.setBounds(20, 430, 120, 30);
		painel.add(salvar);	
		salvar.addActionListener(this);

		JLabel labelID = new JLabel("ID: ");
		labelID.setBounds(40, 120, 100, 20);
		painel.add(labelID);
		
	
		JLabel labelData = new JLabel("Data: ");
		labelData.setBounds(40, 160, 100, 20);
		painel.add(labelData);
		
		JLabel labelEquipamento = new JLabel("Equipamento: ");
		labelEquipamento.setBounds(40, 200, 100, 20);
		painel.add(labelEquipamento);
		
		JLabel labelValor = new JLabel("Valor: ");
		labelValor.setBounds(40, 240, 100, 20);
		painel.add(labelValor);
		
		id = new JTextField();
		id.setBounds(130, 120, 100, 20);
		painel.add(id);
		
		MaskFormatter mascaraData = null;
		try {
			mascaraData = new MaskFormatter("##/##/####");
			mascaraData.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			e.printStackTrace();
		}
		data = new JFormattedTextField(mascaraData);
		data.setBounds(130, 160, 100, 20);
		painel.add(data);
		
		equipamento = new JTextField();
		equipamento.setBounds(130, 200, 100, 20);
		painel.add(equipamento);
		
		MaskFormatter mascaraValor = null;
		try {
			mascaraValor = new MaskFormatter("R$######");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		valor = new JFormattedTextField(mascaraValor);
		valor.setBounds(130, 240, 100, 20);
		painel.add(valor);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == salvar) {
			adicionar();
		} 
	}
	
	private void adicionar() {
		ProcessaPatrimonios.patrimonios.add(new ItemPatrimonio(Integer.parseInt(id.getText()), data.getText(), equipamento.getText(), Double.parseDouble(valor.getText())));
		JOptionPane.showMessageDialog(this, "Salvo com sucesso");
		texto.setText(listar());
	}
	
	private static String listar() {
		String acumulador = "ID\tData\tEquipamento\tValor\n";
		for (ItemPatrimonio it: ProcessaPatrimonios.patrimonios) {
			acumulador += it.toString();
		}
		return acumulador;
	}

	public static void main(String[] args) {
		ProcessaPatrimonios.preencherTeste();
		new FormPatrimonios().setVisible(true);
		PatrimonioDAO.salvar(ProcessaPatrimonios.patrimonios);
	}
}

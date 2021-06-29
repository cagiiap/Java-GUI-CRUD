package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import controller.ProcessaPatrimonios;
import model.ItemPatrimonio;
import model.dao.PatrimonioDAO;

public class FormPatrimonios extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel painel;
	private JTextField id, equipamento, valor;
	private JButton salvarAlteracoes, excluir, adicionar, alterar, limpar, listarPeriodo;
	private static JDateChooser date_chooser;
	private DefaultTableModel model, total;
	private JTable table, tabelaTotal; 
	private JScrollPane pane, paneTotal;
	private Object[] row;

	FormPatrimonios() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Registro de Patrimonio");
		setSize(800, 800);

		painel = new JPanel();
		setContentPane(painel);
		setLocationRelativeTo(null);
		setLayout(null);

		// Labels;
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

		// Campos de texto e o campo de data;
		id = new JTextField();
		id.setBounds(130, 120, 100, 20);
		painel.add(id);
		
		date_chooser = new JDateChooser();
		date_chooser.setFont(new Font("Tahoma", Font.PLAIN, 12));
		date_chooser.setBounds(130, 160, 400, 20);
		date_chooser.setDateFormatString("dd/MM/yyyy");
		painel.add(date_chooser);

		equipamento = new JTextField();
		equipamento.setBounds(130, 200, 100, 20);
		painel.add(equipamento);

		valor = new JTextField();
		valor.setBounds(130, 240, 100, 20);
		valor.setText("R$");
		painel.add(valor);

		//Tabela Principal;
		
		model = new DefaultTableModel();
		table = new JTable();
		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(Color.black);
		table.setSelectionBackground(Color.red);
		table.setSelectionForeground(Color.LIGHT_GRAY);
		table.setGridColor(Color.BLACK);

		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table.setRowHeight(30);
		table.setAutoCreateRowSorter(true);
		table.setModel(model);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				try {
					id.setText(table.getValueAt(index, 0).toString());
					Date date = new SimpleDateFormat("dd/MM/yyyy").parse((String) model.getValueAt(index, 1));
					date_chooser.setDate(date);
					equipamento.setText(table.getValueAt(index, 2).toString());
					valor.setText(table.getValueAt(index, 3).toString());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		pane = new JScrollPane(table);
		pane.setForeground(Color.RED);
		pane.setBackground(Color.PINK);
		pane.setBounds(50, 550, 733, 153);
		painel.add(pane);
		
		//Tabela valor total;
		total = new DefaultTableModel();
		tabelaTotal = new JTable();
		tabelaTotal.setBackground(Color.LIGHT_GRAY);
		tabelaTotal.setForeground(Color.black);
		tabelaTotal.setSelectionBackground(Color.red);
		tabelaTotal.setSelectionForeground(Color.LIGHT_GRAY);
		tabelaTotal.setGridColor(Color.BLACK);
		
		tabelaTotal.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tabelaTotal.setRowHeight(30);
		tabelaTotal.setAutoCreateRowSorter(true);
		tabelaTotal.setModel(total);
		tabelaTotal.setForeground(Color.RED);
		tabelaTotal.setBounds(0, 300, 800, 100);
		painel.add(tabelaTotal);
		
		
		paneTotal = new JScrollPane(tabelaTotal);
		paneTotal.setForeground(Color.RED);
		paneTotal.setBackground(Color.PINK);
		paneTotal.setBounds(0, 300, 800, 100);
		painel.add(paneTotal);
		
		
		//Formatando colunas
		row = new Object[4];
		Object[] columns = { "id", "data", "Equipamentos", "Valor" };
		model.setColumnIdentifiers(columns);
		total.setColumnIdentifiers(columns);
		
		row[3] = ProcessaPatrimonios.getValorTotal();
		total.addRow(row);
		
		// Adicionando dados do arquivo csv na tela;
		for (ItemPatrimonio it: ProcessaPatrimonios.patrimonios) {
			row[0] = it.getId();
			row[1] = it.getData();
			row[2] = it.getEquipamento();
			row[3] = it.getValor();
			model.addRow(row);
		}
			
		// Botões;
		salvarAlteracoes = new JButton("Salvar alterações");
		salvarAlteracoes.setBounds(60, 470, 120, 30);
		salvarAlteracoes.setFocusable(false);
		painel.add(salvarAlteracoes);
		salvarAlteracoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int i = table.getSelectedRow();

					table.setValueAt(id.getText(), i, 0);
					String theDate = new SimpleDateFormat("dd/MM/yyyy").format(date_chooser.getDate());
					table.setValueAt(theDate, i, 1);
					table.setValueAt(equipamento.getText(), i, 2);
					table.setValueAt(valor.getText(), i, 3);
					
					ProcessaPatrimonios.patrimonios.remove(i);
					ProcessaPatrimonios.patrimonios.add(new ItemPatrimonio(Integer.parseInt(id.getText()), theDate, equipamento.getText(), Double.parseDouble(valor.getText())));
					ProcessaPatrimonios.salvar();
					
					id.setText(null);
					date_chooser.setDate(null);
					equipamento.setText(null);
					valor.setText("R$");
					JOptionPane.showMessageDialog(null, "Item atualizado com sucesso!");
				} catch (Exception err) {

				}
			}
		});

		excluir = new JButton("Excluir");
		excluir.setBounds(300, 430, 120, 30);
		excluir.setFocusable(false);
		painel.add(excluir);
		excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ProcessaPatrimonios.patrimonios.remove(table.getSelectedRow());
					model.removeRow(table.getSelectedRow());
					ProcessaPatrimonios.salvar();
					id.setText(null);
					date_chooser.setDate(null);
					equipamento.setText(null);
					valor.setText("R$");
				
					JOptionPane.showMessageDialog(null, "Item removido com sucesso");
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir os dados");
				}
			}
		});

		adicionar = new JButton("Salvar");
		adicionar.setBounds(150, 430, 120, 20);
		adicionar.setFocusable(false);
		painel.add(adicionar);
		adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				row[0] = id.getText();
				row[1] = new SimpleDateFormat("dd/MM/yyyy").format(date_chooser.getDate());
				row[2] = equipamento.getText();
				row[3] = valor.getText();

				ProcessaPatrimonios.patrimonios.add(new ItemPatrimonio(Integer.valueOf(id.getText()),
						new SimpleDateFormat("dd/MM/yyyy").format(date_chooser.getDate()), equipamento.getText(),
						Double.parseDouble(valor.getText().replace("R$", ""))));
				PatrimonioDAO.salvar(ProcessaPatrimonios.patrimonios);
				model.addRow(row);
				
				id.setText(null);
				date_chooser.setDate(null);
				equipamento.setText(null);
				valor.setText("R$");
				JOptionPane.showMessageDialog(null, "Dados Adicionados com sucesso");
			}
		});

		limpar = new JButton("Limpar");
		limpar.setBounds(0, 0, 120, 20);
		limpar.setFocusable(false);
		painel.add(limpar);
		limpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id.setText(null);
				date_chooser.setDate(null);
				equipamento.setText(null);
				valor.setText("R$");
				table.removeRowSelectionInterval(0, ProcessaPatrimonios.patrimonios.size() - 1);
			}
		});

		alterar = new JButton("Atualizar");
		alterar.setBounds(450, 430, 120, 20);
		alterar.setFocusable(false);
		painel.add(alterar);
		alterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();

				id.setText(table.getValueAt(i, 0).toString());
				// date_chooser.setDate(table.getValueAt(i, 1).toString());
				equipamento.setText(table.getValueAt(i, 2).toString());
				valor.setText(table.getValueAt(i, 3).toString());
			}
		});
	}

	public void actionPerformed(ActionEvent e) {

	}
}

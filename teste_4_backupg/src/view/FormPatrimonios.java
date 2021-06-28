package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
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
	private JButton salvar, excluir, adicionar, limpar, listarPeriodo, alterar;
	private static JDateChooser date_chooser;
	private DefaultTableModel model;
	private Object[] row;

	FormPatrimonios() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Registro de Patrimonio");
		setSize(800, 800);

		painel = new JPanel();
		setContentPane(painel);
		setLocationRelativeTo(null);
		setLayout(null);

		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable();
		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(Color.black);
		table.setSelectionBackground(Color.red);
		table.setGridColor(Color.BLACK);
		table.setSelectionForeground(Color.white);
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table.setRowHeight(30);
		table.setAutoCreateRowSorter(true);
		table.setModel(model);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				int indice = table.getSelectedRow();

				id.setText(table.getValueAt(indice, 0).toString());
				date_chooser.setDateFormatString(table.getValueAt(indice, 1).toString());
				equipamento.setText(table.getValueAt(i, 2).toString());
				valor.setText(table.getValueAt(i, 3).toString());
			}
		});

		JScrollPane pane = new JScrollPane(table);
		pane.setForeground(Color.RED);
		pane.setBackground(Color.PINK);
		pane.setBounds(50, 550, 733, 153);
		painel.add(pane);

		row = new Object[4];
		Object[] columns = { "id", "data", "Equipamentos", "Valor" };
		model.setColumnIdentifiers(columns);

		// Adicionando dados do arquivo csv na tela;
		for (ItemPatrimonio it : ProcessaPatrimonios.patrimonios) {
			row[0] = it.getId();
			row[1] = it.getData();
			row[2] = it.getEquipamento();
			row[3] = it.getValor();
			model.addRow(row);
		}

		// Botões;
		salvar = new JButton("Salvar");
		salvar.setBounds(20, 430, 120, 30);
		salvar.setFocusable(false);
		painel.add(salvar);
		salvar.addActionListener(this);

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
					JOptionPane.showMessageDialog(null, "Item removido com sucesso");
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir os dados");
				}
			}
		});

		adicionar = new JButton("Adicionar");
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

		limpar = new JButton();
		limpar.setBounds(300, 430, 120, 20);
		limpar.setFocusable(false);
		painel.add(limpar);
		limpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id.setText(null);
				date_chooser.setDate(null);
				equipamento.setText(null);
				valor.setText("R$");
			}
		});

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
		date_chooser.setBounds(130, 160, 100, 20);
		date_chooser.setDateFormatString("dd/MM/yyyy");
		painel.add(date_chooser);

		equipamento = new JTextField();
		equipamento.setBounds(130, 200, 100, 20);
		painel.add(equipamento);

		valor = new JTextField();
		valor.setBounds(130, 240, 100, 20);
		valor.setText("R$");
		painel.add(valor);

	}

	public void actionPerformed(ActionEvent e) {
	}
}

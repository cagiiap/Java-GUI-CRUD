package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;
import controller.ProcessaPatrimonios;
import model.ItemPatrimonio;
import model.dao.PatrimonioDAO;

public class FormPatrimonios extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel painel;
	private JTextField id, equipamento, valor, procurar;
	private JButton excluir, adicionar, alterar, limpar, voltar;
	private static JDateChooser date_chooser;
	private DefaultTableModel model, total;
	private JTable table, tabelaTotal;
	private JLabel labelID, labelData, labelEquipamento, labelValor, labelProcurar;
	private JScrollPane pane, paneTotal;
	private Object[] row, row_total;
	public static ImageIcon img = new ImageIcon("./assets/Pinguim_logo.png");

	FormPatrimonios() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Registro de Patrimonio");
		setSize(900, 540);
		painel = new JPanel();
		setContentPane(painel);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setIconImage(img.getImage());
		Color color = new Color(0, 53, 102);
		painel.setBackground(color);

		// Tabela principal;
		model = new DefaultTableModel();
		table = new JTable();
		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(Color.black);
		table.setSelectionBackground(Color.GRAY);
		table.setSelectionForeground(Color.BLACK);
		table.setGridColor(Color.BLACK);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setRowHeight(30);
		table.setAutoCreateRowSorter(true);
		table.getTableHeader().setReorderingAllowed(false);
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
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		pane = new JScrollPane(table);
		pane.setForeground(Color.RED);
		pane.setBackground(Color.PINK);
		pane.setBounds(320, 30, 550, 370);
		painel.add(pane);

		// Tabela total;
		total = new DefaultTableModel();
		tabelaTotal = new JTable();
		tabelaTotal.setBackground(Color.LIGHT_GRAY);
		tabelaTotal.setForeground(Color.black);
		tabelaTotal.setSelectionBackground(Color.GRAY);
		tabelaTotal.setSelectionForeground(Color.BLACK);
		tabelaTotal.setGridColor(Color.BLACK);
		tabelaTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tabelaTotal.setRowHeight(30);
		tabelaTotal.setAutoCreateRowSorter(true);
		tabelaTotal.setModel(total);
		tabelaTotal.setForeground(Color.BLACK);
		painel.add(tabelaTotal);

		paneTotal = new JScrollPane(tabelaTotal);
		paneTotal.setForeground(Color.RED);
		paneTotal.setBackground(Color.PINK);
		paneTotal.setBounds(320, 420, 550, 55);
		painel.add(paneTotal);

		row = new Object[4];
		Object[] columns = { "ID", "Data", "Equipamentos", "Valor" };
		model.setColumnIdentifiers(columns);

		row_total = new Object[1];
		Object[] columns_nova = { "Valor total" };
		total.setColumnIdentifiers(columns_nova);

		// Alinhar o conteudo da cell no meio
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);
		tabelaTotal.getColumnModel().getColumn(0).setCellRenderer(centralizado);

		// Assim q abrir o programa, vai carregar o valor total;
		row_total[0] = "R$" + ProcessaPatrimonios.getValorTotal();
		total.addRow(row_total);

		// Adicionando dados do arquivo csv na tela;
		for (ItemPatrimonio it : ProcessaPatrimonios.patrimonios) {
			row[0] = it.getId();
			row[1] = it.getData();
			row[2] = it.getEquipamento();
			row[3] = it.getValor();
			model.addRow(row);
		}

		// Botões;
		voltar = new JButton("<==");
		voltar.setFocusable(false);
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LaunchPage newPage = new LaunchPage();
				newPage.setVisible(true);
			}
		});
		voltar.setBounds(10, 11, 57, 23);
		painel.add(voltar);

		adicionar = new JButton("Adicionar");
		adicionar.setBounds(40, 310, 120, 30);
		adicionar.setFocusable(false);
		painel.add(adicionar);
		adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					row[0] = id.getText();
					row[1] = new SimpleDateFormat("dd/MM/yyyy").format(date_chooser.getDate());
					row[2] = equipamento.getText();
					row[3] = valor.getText();

					ProcessaPatrimonios.patrimonios.add(new ItemPatrimonio(Integer.valueOf(id.getText()),new SimpleDateFormat("dd/MM/yyyy").format(date_chooser.getDate()), equipamento.getText(),
							Double.parseDouble(valor.getText())));
					PatrimonioDAO.salvar(ProcessaPatrimonios.patrimonios);
					model.addRow(row);

					setValueTotal();
					limparCampos();
					JOptionPane.showMessageDialog(null, "Dados adicionados com sucesso");
				} catch (Exception err) {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro ao adicionar os dados");
				}
			}

		});

		excluir = new JButton("Excluir");
		excluir.setBounds(170, 310, 120, 30);
		excluir.setFocusable(false);
		painel.add(excluir);
		excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ProcessaPatrimonios.patrimonios.remove(table.getSelectedRow());
					model.removeRow(table.getSelectedRow());
					ProcessaPatrimonios.salvar();

					setValueTotal();
					limparCampos();
					JOptionPane.showMessageDialog(null, "Item removido com sucesso");
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir os dados");
				}
			}
		});

		alterar = new JButton("Atualizar");
		alterar.setBounds(40, 370, 120, 30);
		alterar.setFocusable(false);
		painel.add(alterar);
		alterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verificarCampos();
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

					setValueTotal();
					limparCampos();
					JOptionPane.showMessageDialog(null, "Item atualizado com sucesso!");
				} catch (Exception err) {
					JOptionPane.showMessageDialog(null, "Selecione uma linha e altere os dados para salvar!");
				}
			}
		});

		limpar = new JButton("Limpar");
		limpar.setBounds(170, 370, 120, 30);
		limpar.setFocusable(false);
		painel.add(limpar);
		limpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					limparCampos();
					table.removeRowSelectionInterval(0, table.getSelectedRow());
				} catch (Exception errr) {
					JOptionPane.showMessageDialog(null, "Os campos já estão limpos!");
				}
			}
		});

		// Labels;
		labelID = new JLabel("ID: ");
		labelID.setBounds(40, 50, 100, 30);
		labelID.setForeground(Color.white);
		painel.add(labelID);

		labelData = new JLabel("Data: ");
		labelData.setBounds(40, 100, 100, 30);
		labelData.setForeground(Color.white);
		painel.add(labelData);

		labelEquipamento = new JLabel("Equipamento: ");
		labelEquipamento.setBounds(40, 150, 100, 30);
		labelEquipamento.setForeground(Color.white);
		painel.add(labelEquipamento);

		labelValor = new JLabel("Valor: ");
		labelValor.setBounds(40, 200, 100, 30);
		labelValor.setForeground(Color.white);
		painel.add(labelValor);

		labelProcurar = new JLabel("Procurar: ");
		labelProcurar.setBounds(40, 415, 100, 30);
		labelProcurar.setForeground(Color.white);
		painel.add(labelProcurar);

		// Campos de texto e o campo de data;
		id = new JTextField();
		id.setBounds(128, 50, 160, 30);
		painel.add(id);

		date_chooser = new JDateChooser();
		date_chooser.setFont(new Font("Tahoma", Font.PLAIN, 12));
		date_chooser.setBounds(128, 100, 160, 30);
		date_chooser.setDateFormatString("dd/MM/yyyy");
		painel.add(date_chooser);

		equipamento = new JTextField();
		equipamento.setBounds(128, 150, 160, 30);
		painel.add(equipamento);

		valor = new JTextField();
		valor.setBounds(128, 200, 160, 30);
		painel.add(valor);

		procurar = new JTextField();
		procurar.setBounds(40, 445, 250, 30);
		painel.add(procurar);
		procurar.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
				table.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(procurar.getText().trim()));
			}
		});
	}

	public void setValueTotal() {
		row_total[0] = "R$" + ProcessaPatrimonios.getValorTotal();
		total.setValueAt(row_total[0], 0, 0);

	}

	public void limparCampos() {
		id.setText(null);
		date_chooser.setDate(null);
		equipamento.setText(null);
		valor.setText(null);
	}

	public boolean verificarCampos() {
		if (id.getText().equals("") || date_chooser.getDate() == null || equipamento.getText().equals("")
				|| valor.getText().equals("")) {
			return false;
		} else {
			return true;
		}
	}

	public void actionPerformed(ActionEvent e) {
	}
}
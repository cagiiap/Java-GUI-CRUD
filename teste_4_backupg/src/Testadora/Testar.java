package Testadora;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import controller.ProcessaPatrimonios;
import model.ItemPatrimonio;

public class Testar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private Object[] row;

	public static void main(String[] args) {
		Testar novo = new Testar();
		novo.setVisible(true);
		ProcessaPatrimonios.abrir();
		for(ItemPatrimonio teste: ProcessaPatrimonios.patrimonios) {
			String data_teste;
			try {
				
				data_teste =  teste.getData();
				System.out.println(data_teste);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	Testar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setTitle("Registro de Patrimonio");
		setSize(800, 800);

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
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(60, 156, 108, 20);
		dateChooser.setDateFormatString("dd/MM/yyyy");
		contentPane.add(dateChooser);
		
		JDateChooser dateChooser2 = new JDateChooser();
		dateChooser2.setBounds(300, 250, 108, 20);
		dateChooser2.setDateFormatString("dd/MM/yyyy");
		contentPane.add(dateChooser2);

		row = new Object[4];
		Object[] columns = { "id"};
		model.setColumnIdentifiers(columns);

		/*
		row[1] = "dei";
		row[2] = "sei";
		row[3] = "dou";
		model.addRow(row);
		*/

		JScrollPane pane = new JScrollPane(table);
		pane.setForeground(Color.RED);
		pane.setBackground(Color.PINK);
		pane.setBounds(50,200, 200, 500);
		contentPane.add(pane);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int index = table.getSelectedRow();
				try {
					Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(index, 0));
					dateChooser.setDate(date);
					Date data_nova = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(index+1, 0));
					dateChooser2.setDate(data_nova);
					System.out.println(date.after(data_nova));
					
				
					
					while(date.after(data_nova) == true) {
						
					}
					
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});

	

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(166, 98, 89, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String date =  new SimpleDateFormat("yyyy-MM-dd").format(dateChooser.getDate());
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.addRow(new Object[] {date});
					
				} catch (Exception err) {

				}
			}
		});
	}
}

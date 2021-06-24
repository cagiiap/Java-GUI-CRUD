package model.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import model.ItemPatrimonio;

public class PatrimonioDAO {
	private ItemPatrimonio item;
	private ArrayList<ItemPatrimonio> itens;
	private BufferedReader br;
	private BufferedWriter bw;
	private String arquivo = "./bd/patrimonio.csv";

	public ArrayList<ItemPatrimonio> abrir() {
		itens = new ArrayList<>();

		try {
			br = new BufferedReader(new FileReader(arquivo));
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] line_true = line.split(";");
				item = new ItemPatrimonio(line_true[0], line_true[1], Integer.valueOf(line_true[2]),
						Double.parseDouble(line_true[3]));
				itens.add(item);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Erro: " + e);
		}
		return itens;
	}

	public boolean salvar(ArrayList<ItemPatrimonio> it) {
		try {
			bw = new BufferedWriter(new FileWriter(arquivo, false));
			for (ItemPatrimonio i : it) {
				bw.write(i.toString());
			}
			bw.close();
			return true;
		} catch (Exception e) {
			System.out.println("Erro: " + e);
			return false;
		}
	}
}

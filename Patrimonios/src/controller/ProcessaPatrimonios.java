package controller;

import java.util.ArrayList;
import model.ItemPatrimonio;
import model.dao.PatrimonioDAO;

public class ProcessaPatrimonios {
	public static ArrayList<ItemPatrimonio> patrimonios = new ArrayList<>();
	private static PatrimonioDAO pd = new PatrimonioDAO();
	
	public static double getValorTotal() {
		double acumulador = 0;
		for(ItemPatrimonio v: patrimonios) {
			acumulador += v.getValor();
		}
		return acumulador;
	}
	
	public static boolean salvar() {
		if (PatrimonioDAO.salvar(patrimonios)) {
			return true;
		}else{
			return false;
		}
	}
	
	public static void abrir() {
		patrimonios = pd.abrir();	
	}
	
	public static void preencherTeste() {
		try {
			patrimonios.add(new ItemPatrimonio(1,"10/01/2021","Abacate",1200f));
			patrimonios.add(new ItemPatrimonio(2,"08/01/2021","Manga",2200));
			patrimonios.add(new ItemPatrimonio(3,"08/01/2021","Mamão",2100));
			patrimonios.add(new ItemPatrimonio(4,"06/01/2021","Uva",800));
			patrimonios.add(new ItemPatrimonio(5,"04/01/2021","Banana",1500));
			patrimonios.add(new ItemPatrimonio(6,"04/01/2021","Maçã",2000));
			patrimonios.add(new ItemPatrimonio(1,"02/01/2021","Caju",2200f));
			patrimonios.add(new ItemPatrimonio(2,"29/12/2020","Pêssego",3200));
			patrimonios.add(new ItemPatrimonio(3,"29/12/2020","Castanha",2600));
			patrimonios.add(new ItemPatrimonio(4,"29/12/2020","Passas",700));
			patrimonios.add(new ItemPatrimonio(5,"29/12/2020","Mirtilo",100));
			patrimonios.add(new ItemPatrimonio(6,"27/12/2020","Jaca",2200));
		} catch (Exception e) {
			System.out.println("Erro ao converter"+e);
		}
	}
}
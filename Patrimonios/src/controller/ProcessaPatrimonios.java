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
			patrimonios.add(new ItemPatrimonio(1,"10/01/2021","Campinas",1200f));
			patrimonios.add(new ItemPatrimonio(2,"Campinas","Natal",2200));
			patrimonios.add(new ItemPatrimonio(3,"Campinas","Maceió",2100));
			patrimonios.add(new ItemPatrimonio(4,"Campinas","Rio de Janeiro",800));
			patrimonios.add(new ItemPatrimonio(5,"Campinas","Salvador",1500));
			patrimonios.add(new ItemPatrimonio(6,"Campinas","Fortaleza",2000));
		} catch (Exception e) {
			System.out.println("Erro ao converter datas "+e);
		}
	}
}
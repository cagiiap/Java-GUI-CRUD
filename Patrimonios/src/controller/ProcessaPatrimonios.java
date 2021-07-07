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
		return PatrimonioDAO.salvar(patrimonios);
	}
	
	public static boolean salvarTxt() {
		return PatrimonioDAO.salvarTxt(patrimonios);
	}
	
	public static void abrir() {
		patrimonios = pd.abrir();	
	}	
}
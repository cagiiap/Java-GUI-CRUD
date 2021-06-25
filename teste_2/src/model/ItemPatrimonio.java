package model;

public class ItemPatrimonio {
	private String data;
	private String equipamento;
	private int id;
	private double valor;
	
	
	public ItemPatrimonio(){	
	}

	public ItemPatrimonio(int id, String data, String equipamento, double valor){
		this.id = id;
		this.data = data;
		this.equipamento = equipamento;
		this.valor = valor;
	}

	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getEquipamento() {
		return equipamento;
	}
	
	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return id + "\t" + data + "\t" + equipamento + "\t" + "R$" +  valor + "\n";
	}
	
	public String toCsv() {
		return id + ";" + data + ";" + equipamento + ";" + valor + ";\n";
	}
}

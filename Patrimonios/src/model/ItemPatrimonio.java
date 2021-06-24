package model;

public class ItemPatrimonio {
	String data;
	String equipamento;
	int id;
	double valor;
	
	public ItemPatrimonio(){	
	}

	public ItemPatrimonio(String data, String equipamento, int id, double valor){
		this.data = data;
		this.equipamento = equipamento;
		this.id = id;
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
		return "Patrimonio [data=" + data + ", equipamento=" + equipamento + ", id=" + id + ", valor=" + valor + "]";
	}
}

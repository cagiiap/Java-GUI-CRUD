package model.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class ConexaoDAO {
	private String url;
	private String usuario;
	private String senha;
	private Connection con;
	
	public ConexaoDAO(){
		url = "jdbc:postgresql://ec2-35-170-85-206.compute-1.amazonaws.com:5432/d5mka0s4ikomrq";
		usuario = "kjzevqklgfrvoi";	
		senha = "bdb8aa2e6f52392eaef6cb56fae6a93df90ba83cfaf4c26cc51f5c4f284e87ee";
		
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, usuario, senha);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int executaSQL(String sql) {
		try {
			
			Statement stm = con.createStatement();
			int res = stm.executeUpdate(sql);
			con.close();
			return res;
			
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public ResultSet executaBusca(String sql) {
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			//con.close();
			return rs;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

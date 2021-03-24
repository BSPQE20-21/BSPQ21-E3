package es.deusto.spq.donaciones.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Locale;

import es.deusto.spq.util.dao.DataBaseManager;

public class GestorDonativosBD extends DataBaseManager  {


	public GestorDonativosBD(DBType dbType, String dbName) {		
		super(dbType, dbName);
	}

	private void desconectarBD() {
		super.disconnect();
	}

	public ArrayList<Donativo> getListaDonativos() {
		ArrayList<Donativo> donativos = new ArrayList<Donativo>();	
		try {		
			String Query = "select * from Donaciones order by fecha asc";
			Statement statement = super.getStatement(); 
			ResultSet rs = statement.executeQuery(Query);
			
			while (rs.next()) {
				donativos.add(new Donativo (rs.getInt("Donativo"), 
				                            rs.getString("Fecha"), 
											rs.getString("Hora"), 
											rs.getInt("Acumulado")
											)
							  );
			}
			
			rs.close();
			super.releaseStatement(statement);
		} catch (SQLException ex) {
			System.err.println("# SQLException: " + ex.getMessage());
		}
		return donativos;
	}

	public void finalizar() {
		this.desconectarBD();
	}

	public void almacenarDonativo(int donativo, int total) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());		
		String fecha = formatter.format(Calendar.getInstance().getTime());		
		formatter = new SimpleDateFormat("HH:mm", Locale.getDefault());		
		String hora = formatter.format(Calendar.getInstance().getTime());
		
		try {
			String Insert = "insert into Donaciones values ('" + 
			  				fecha + "','" + hora + "'," + donativo + "," + 
			                (total+donativo) + ")";
			Statement statement = super.getStatement();
			statement.executeUpdate(Insert);
			super.releaseStatement(statement);
		} catch (SQLException ex) {
			System.err.println("# SQLException: " + ex.getMessage());
			ex.printStackTrace();
		}	
	}

	public int obtenerTotalAcumulado() {
		int totalAcumulado = 0;
		try {
			String Query = "select max(Acumulado) from Donaciones";
			Statement statement = super.getStatement();
			ResultSet rs = statement.executeQuery(Query);
			
			if (rs.next()) {									
				totalAcumulado = rs.getInt(1);
			}
			
			rs.close();
			super.releaseStatement(statement);
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
		
		return totalAcumulado;
	}

	public Donativo obtenerUltimoDonativo() {
		Donativo donativo = null;
		
		try {
			int total = obtenerTotalAcumulado();
			String Query = "select * from Donaciones " + "where Acumulado = " + total;
			Statement statement = super.getStatement();
			ResultSet rs = statement.executeQuery(Query);
			
			// Habra 0 filas si la tabla estaba vacia y 1 la tabla tenia datos
			if (rs.next()) {
				String fecha = rs.getString(1);
				String hora = rs.getString(2);
				int donacion = rs.getInt(3);
				int acumulado = rs.getInt(4);
				donativo = new Donativo(donacion, fecha, hora, acumulado);		
			} 			
			rs.close();
			super.releaseStatement(statement);
		} catch (SQLException ex) {
			System.err.println("# SQLException: " + ex.getMessage());
		}
		
		return donativo;
	}
}
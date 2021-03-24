package es.deusto.spq.donaciones.dao;


public class Donativo  {
	private int valor;
	private String fecha;
	private String hora;
	private int acumulado;

	public Donativo(int n, String fecha, String hora, int acumulado){
		this.valor = n;
		this.fecha = fecha;
		this.hora = hora;
		this.acumulado = acumulado;
	}

	public int getValor(){
		return this.valor;
	}
	
	public String getFecha(){
		return this.fecha;
	}
	
	public String getHora(){
		return this.hora;
	}
	
	public int getAcumulado(){
		return this.acumulado;
	}
	
	public String toString() {
		return "Donativo[ valor --> " + this.valor + ", fecha --> "  + this.fecha + ", hora --> " + this.hora + ", acumulado --> " + this.acumulado +"]";
	}
}
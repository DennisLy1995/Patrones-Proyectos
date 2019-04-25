package cenfotec.proyecto.artefactos;

import cenfotec.proyecto.artefactos.Piezas.Pieza;

public class PiezaAjedrez  {
	
	String movimiento;
	String color;
	int cantidadMovimientos=0;
	
	public PiezaAjedrez(String nombre, String jugador, String pieza, String color) {
		//super(nombre, jugador, pieza);
		this.color=color;
		//this.movimiento=PiezaAjedrez.retornarMovimiento(pieza);
	}
	
	
	public void sumarMovimiento() {
		cantidadMovimientos++;
	}

	public void restarMovimiento() {
		cantidadMovimientos--;
	}


	public String getMovimiento() {
		return movimiento;
	}


	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public int getCantidadMovimientos() {
		return cantidadMovimientos;
	}


	public void setCantidadMovimientos(int cantidadMovimientos) {
		this.cantidadMovimientos = cantidadMovimientos;
	}


}

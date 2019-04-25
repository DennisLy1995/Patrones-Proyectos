package cenfotec.proyecto.artefactos.Piezas;

public abstract class Pieza {

	private String jugador;

	public Pieza(String jugador) {
		this.jugador = jugador;
	}

	public abstract String retornarMovimiento();
	public abstract String getNombre();
	public String getJugador(){
		return this.jugador;
	}
	public void setJugador(String tempJugador){
		this.jugador = tempJugador;
	}
}

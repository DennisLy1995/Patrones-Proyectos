package cenfotec.proyecto.artefactos;

public class PiezaAjedrez extends Pieza{
	
	String movimiento;
	String color;
	
	public PiezaAjedrez(String nombre, String jugador, String pieza, String color) {
		super(nombre, jugador, pieza);
		this.color=color;
		this.movimiento=PiezaAjedrez.retornarMovimiento(pieza);
	}
	
	
	public static String retornarMovimiento(String nombrePieza) {
		String movimiento="";
		switch(nombrePieza) {
		case "peon":
			movimiento = "frente/consume diagnonal";
			break;
		case "torre":
			movimiento = "recto ilimitado";
			break;
		case "alfil":
			movimiento = "Diagonal ilimitado";
			break;
		case "caballo":
			movimiento = "movimiento en L";
			break;
		case "reina":
			movimiento = "recto y diagonal ilimitado";
			break;
		case "rey":
			movimiento = "recto 1 posicion";
			break;
		case "*":
			movimiento = "Ninguno";
			break;
		}
		
		
		return movimiento;
	}
	
	
}

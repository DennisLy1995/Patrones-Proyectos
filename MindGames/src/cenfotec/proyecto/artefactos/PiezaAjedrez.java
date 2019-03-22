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
		String movimientoPieza="";
		switch(nombrePieza) {
		case "peon":
			movimientoPieza = "frente/consume diagnonal";
			break;
		case "torre":
			movimientoPieza = "recto ilimitado";
			break;
		case "alfil":
			movimientoPieza = "Diagonal ilimitado";
			break;
		case "caballo":
			movimientoPieza = "movimiento en L";
			break;
		case "reina":
			movimientoPieza = "recto y diagonal ilimitado";
			break;
		case "rey":
			movimientoPieza = "recto 1 posicion";
			break;
		case "*":
			movimientoPieza = "Ninguno";
			break;
		}
		
		
		return movimientoPieza;
	}

	public static boolean movimientoPeon(String posicionInicial, String posicionFinal, String[][] posiciones, PiezaAjedrez[][] piezas) {
		boolean checker = false;
		String reExp = "{2}^[abcdefgh][12345678]$";
		/*if() {//Cuando el peon es nuevo y quiere avanzar una sola posicion al frente.
			
			checker = true;
		}else if() {//Cuando el peon es nuevo y quiere avanzar dos posicines al frente.
			checker = true;
		}else if() {//Cuando el peon no nuevo y quiere consumir una posicion en diagonal.
			checker = true;
		}else if() {////Cuando el peon no nuevo y quiere avanzar una sola posicion al frente.
			checker = true;
		}
		*/
		return checker;
	}
	
	public static boolean movimientoRey(String posicionInicial, String posicionFinal) {
		boolean checker = false;
		
		return checker;
	}
	
	public static boolean movimientoReina(String posicionInicial, String posicionFinal) {
		boolean checker = false;
		
		return checker;
	}
	
	public static boolean movimientoCaballo(String posicionInicial, String posicionFinal) {
		boolean checker = false;
		
		return checker;
	}
	
	public static boolean movimientoTorre(String posicionInicial, String posicionFinal) {
		boolean checker = false;
		
		return checker;
	}
	
	public static boolean movimientoAlfil(String posicionInicial, String posicionFinal) {
		boolean checker = false;
		
		return checker;
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
	
	
}

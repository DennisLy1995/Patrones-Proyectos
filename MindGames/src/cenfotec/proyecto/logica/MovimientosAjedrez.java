package cenfotec.proyecto.logica;

import cenfotec.proyecto.artefactos.PiezaAjedrez;

public interface MovimientosAjedrez {


	public static boolean CalcularMovimiento() {

		return false;
	}


	public static boolean verificarPosicion(String posicionInicial) {
		boolean checker = verificarPosicionTablero(posicionInicial);
		if(posicionInicial.equals("") || posicionInicial.length() > 2) {
			checker = false;
		}
		return checker;
	}


	public static boolean verificarPosicionesEnMedio() {

		return false;
	}


	public static void moverPieza(PiezaAjedrez pieza) {

	}
	
	//Need to refactor the if statement with regexp.
	public static boolean verificarPosicionTablero(String x) {
		boolean checker = false;
		if(x.equals("a1") || x.equals("b1") || x.equals("c1") || x.equals("d1") || x.equals("e1") || x.equals("f1") || x.equals("g1") ||x.equals("h1") || 
		   x.equals("a2") || x.equals("b2") || x.equals("c2") || x.equals("d2") || x.equals("e2") || x.equals("f2") || x.equals("g2") ||x.equals("h2") || 
		   x.equals("a3") || x.equals("b3") || x.equals("c3") || x.equals("d3") || x.equals("e3") || x.equals("f3") || x.equals("g3") ||x.equals("h3") || 
		   x.equals("a4") || x.equals("b4") || x.equals("c4") || x.equals("d4") || x.equals("e4") || x.equals("f4") || x.equals("g4") ||x.equals("h4") || 
		   x.equals("a5") || x.equals("b5") || x.equals("c5") || x.equals("d5") || x.equals("e5") || x.equals("f5") || x.equals("g5") ||x.equals("h5") || 
		   x.equals("a6") || x.equals("b6") || x.equals("c6") || x.equals("d6") || x.equals("e6") || x.equals("f6") || x.equals("g6") ||x.equals("h6") || 
		   x.equals("a7") || x.equals("b7") || x.equals("c7") || x.equals("d7") || x.equals("e7") || x.equals("f7") || x.equals("g7") ||x.equals("h7") || 
		   x.equals("a8") || x.equals("b8") || x.equals("c8") || x.equals("d8") || x.equals("e8") || x.equals("f8") || x.equals("g8") ||x.equals("h8") ) {
			
			checker = true;
			
		}
		return checker;
	}
	
}

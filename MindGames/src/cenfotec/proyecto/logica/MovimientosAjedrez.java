package cenfotec.proyecto.logica;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		Pattern pattern = Pattern.compile("{2}^[abcdefgh][12345678]$");
		Matcher matcher = pattern.matcher(x);
		return matcher.matches();		 
	}
	
}

package cenfotec.proyecto.gestores;

import java.io.FileNotFoundException;
import cenfotec.proyecto.juegos.JuegoDamas;

public class GestorDamas {

	public static void imprimirEstadoJuego() {
		JuegoDamas.ImprimirEstadoJuego();
	}

	public static void guardarPartida() throws FileNotFoundException {
		JuegoDamas.guardarPartida();
	}
	
}

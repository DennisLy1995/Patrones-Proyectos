package cenfotec.proyecto.gestores;

import java.io.FileNotFoundException;
import java.io.IOException;
import cenfotec.proyecto.juegos.JuegoDamas;

public class GestorDamas {

	public static void iniciarPartida() {
		JuegoDamas.iniciarJuego();
	}
	
	public static void continuarPartida() {
		
	}
	
	public static void imprimirEstadoJuego() {
		JuegoDamas.ImprimirEstadoJuego();
	}

	public static void guardarPartida() throws FileNotFoundException {
		JuegoDamas.guardarPartida();
	}
	
	public static void cargarPartida() throws IOException {
		JuegoDamas.cargarPartidaArchivoTexto("Damas");
		
	}
	
}

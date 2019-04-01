package cenfotec.proyecto.gestores;

import java.io.FileNotFoundException;
import java.io.IOException;

import cenfotec.proyecto.juegos.JuegoAjedrez;

public class GestorAjedrez {

	public static void imprimirEstadoJuego() {
		JuegoAjedrez.ImprimirEstadoJuego();
	}

	public static void iniciarPartida() {
		String ganador = JuegoAjedrez.EvaluarGanador();
		if(ganador.contentEquals("Ninguno")) {
			JuegoAjedrez.iniciarPartida();
		}else {
			System.out.println("Juego terminado");
			JuegoAjedrez.ImprimirEstadoJuego();
		}
		
	}
	
	public static void guardarPartida() throws FileNotFoundException {
		JuegoAjedrez.guardarPartida();
	}

	public static void cargarPartidaArchivoTexto() throws IOException {
		JuegoAjedrez.cargarPartidaArchivoTexto("Ajedrez");
		
	}

}

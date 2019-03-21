package cenfotec.proyecto.gestores;

import java.io.FileNotFoundException;
import java.io.IOException;
import cenfotec.proyecto.juegos.JuegoGo;

public class GestorGo {

	public static void imprimirEstadoJuego() {
		JuegoGo.ImprimirEstadoJuego();
	}
	
	public static void guardarPartida() throws FileNotFoundException {
		JuegoGo.guardarPartida();
	}
	
	public static void cargarPartida() throws IOException {
		JuegoGo.cargarPartidaArchivoTexto("Go");
		
	}
	
}

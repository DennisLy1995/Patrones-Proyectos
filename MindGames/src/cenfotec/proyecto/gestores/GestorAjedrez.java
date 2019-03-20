package cenfotec.proyecto.gestores;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import cenfotec.proyecto.juegos.JuegoAjedrez;

public class GestorAjedrez {

	public static void imprimirEstadoJuego() {
		JuegoAjedrez.ImprimirEstadoJuego();
	}

	public static void iniciarPartida() {
		JuegoAjedrez.iniciarPartida();
	}
	
	public static void guardarPartida() throws FileNotFoundException {
		JuegoAjedrez.guardarPartida();
	}

	public static void cargarPartida() throws IOException {
		JuegoAjedrez.cargarPartida();
		
	}

}

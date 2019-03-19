package cenfotec.proyecto.juegos;

import java.util.Scanner;

import cenfotec.proyecto.artefactos.PartidaAjedrez;
import cenfotec.proyecto.artefactos.PiezaAjedrez;
import cenfotec.proyecto.logica.GeneralesJuego;
import cenfotec.proyecto.logica.MovimientosAjedrez;

public class JuegoAjedrez extends Juego implements MovimientosAjedrez {

	private static PartidaAjedrez partida = new PartidaAjedrez();
	private static Scanner in = new Scanner(System.in);

	public JuegoAjedrez(String jugador1, String jugador2, String ganador, String perdedor) {
		super(jugador1, jugador2, ganador, perdedor);

	}

	public static void iniciarPartida() {

		boolean breaker = false;
		while (breaker==false) {
			ImprimirEstadoJuego();
			switch (lecturaOpcionMenu()) {
			case "1":
				moverPieza();
				breaker = false;
				break;
			case "2":
				breaker = false;
				break;
			case "3":
				breaker = true;
				break;

			}
		}

	}

	public static void imprimirOpcionesJuego() {
		System.out.println("");
		System.out.println("1.Mover Pieza.");
		System.out.println("2.Guardar partida.");
		System.out.println("3.Terminar partida.");

	}

	public static String lecturaOpcionMenu() {
		imprimirOpcionesJuego();
		String temp = "";
		temp = in.nextLine();
		if (temp.equals("1") || temp.equals("2") || temp.equals("3")) {
			
		}else {
			temp = "3";
		}
		return temp;
	}

	public static void moverPieza() {
		String coordenadaInicial;
		String coordenadaFinal;
		System.out.println("Ingrese la coordenada donde la pieza se encuentra ubicada:");
		coordenadaInicial = in.nextLine();
		System.out.println("Ingrese la coordenada final:");
		coordenadaFinal = in.nextLine();
		System.out.println(MovimientosAjedrez.verificarPosicion(coordenadaInicial));
		System.out.println(MovimientosAjedrez.verificarPosicion(coordenadaFinal));
		
	}
	
	
	
	
	
	public static PartidaAjedrez getPartida() {
		return partida;
	}

	public static void setPartida(PartidaAjedrez partida) {
		JuegoAjedrez.partida = partida;
	}

	public static PiezaAjedrez[][] retornarTablerojuego() {
		return partida.getTableroPosiciones();
	}

	public static void ImprimirEstadoJuego() {
		System.out.println("|-------------------------------|   |-------------------------------|");
		System.out.println("|          Coordenadas          |   |         Partida actual        |");
		System.out.println("|-------------------------------|   |-------------------------------|");
		System.out.println();

		for (int i = 0; i < 8; i++) {
			System.out.print("     ");
			for (int e = 0; e < 8; e++) {
				System.out.print(partida.tablero[i][e] + " ");
			}
			System.out.print("            ");
			for (int e = 0; e < 8; e++) {
				System.out.print(partida.tableroPosiciones[i][e].nombre + " ");
			}
			System.out.println();
		}
	}


}

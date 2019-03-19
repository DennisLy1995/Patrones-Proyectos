package cenfotec.proyecto.juegos;

import java.util.Scanner;

import cenfotec.proyecto.artefactos.PartidaAjedrez;
import cenfotec.proyecto.artefactos.PiezaAjedrez;
import cenfotec.proyecto.logica.MovimientosAjedrez;

public class JuegoAjedrez extends Juego implements MovimientosAjedrez {

	private static int contador;
	
	private static PartidaAjedrez partida = new PartidaAjedrez();
	private static Scanner in = new Scanner(System.in);

	public JuegoAjedrez(String jugador1, String jugador2, String ganador, String perdedor) {
		super(jugador1, jugador2, ganador, perdedor);

	}

	public static void iniciarPartida() {

		System.out.println("Inician las piezas Negras.");
		
		contador = 2;
		boolean breaker = false;
		while (breaker==false) {
			ImprimirEstadoJuego();
			switch (lecturaOpcionMenu()) {
			case "1":
				if(contador%2 == 0) {
					System.out.println("Mueven las piezas negras.");
					moverPieza("N");
				}else {
					System.out.println("Mueven las piezas blancas.");
					moverPieza("B");
				}
				
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

	public static void moverPieza(String color) {
		String coordenadaInicial;
		String coordenadaFinal;
		String piezaRetorno="";
		
		System.out.println("Ingrese la coordenada donde la pieza se encuentra ubicada:");
		coordenadaInicial = in.nextLine();
		System.out.println("Ingrese la coordenada final:");
		coordenadaFinal = in.nextLine();
		if(MovimientosAjedrez.verificarPosicion(coordenadaInicial) == true && 
				MovimientosAjedrez.verificarPosicion(coordenadaFinal) == true) {
			piezaRetorno = retornarObjetoEnPosicion(coordenadaInicial);
			if(color.charAt(0) == piezaRetorno.charAt(1) ) {
				
				System.out.println("Se puede proceder a mover la pieza.");
				contador++;
				
			}else {
				System.out.println("No puedes mover la pieza en la posicion " + coordenadaInicial + " porque no te pertenece.");
			}
			
		}else {
			System.out.println("Coordenadas incorrectas.");
		}
		
	}
	
	public static String retornarObjetoEnPosicion(String posicion) {
		String retorno="";
		for (int i = 0; i < 8; i++) {
			for (int e = 0; e < 8; e++) {
				if(posicion.equals(partida.tablero[i][e])) {
					retorno = partida.tableroPosiciones[i][e].nombre;
				}
			}
		}
		
		return retorno;
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

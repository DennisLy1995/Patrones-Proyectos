package cenfotec.proyecto.juegos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import cenfotec.proyecto.artefactos.PartidaAjedrez;
import cenfotec.proyecto.artefactos.PiezaAjedrez;
import cenfotec.proyecto.artefactos.Tablero;
import cenfotec.proyecto.logica.MovimientosAjedrez;
import cenfotec.proyecto.utiles.PersistenciaTexto;
import cenfotec.proyecto.utiles.Serializer;

public class JuegoAjedrez extends Juego implements MovimientosAjedrez {

	private static int contador;

	private static PartidaAjedrez partida = new PartidaAjedrez();
	private static Scanner in = new Scanner(System.in);

	public JuegoAjedrez(String jugador1, String jugador2, String ganador, String perdedor) {
		super(jugador1, jugador2, ganador, perdedor);
	}

	public static void iniciarPartida() {

		System.out.println("Inician las piezas Negras.");
		partida = new PartidaAjedrez();

		contador = 2;
		boolean breaker = false;
		while (breaker == false) {
			ImprimirEstadoJuego();
			switch (lecturaOpcionMenu()) {
			case "1":
				if (contador % 2 == 0) {
					System.out.println("Mueven las piezas negras.");
					moverPieza("N");
				} else {
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
			default:
				System.out.println("Opcion no valida.");
				breaker = false;
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

		} else {
			temp = "repita";
		}
		return temp;
	}

	public static void moverPieza(String color) {
		String coordenadaInicial;
		String coordenadaFinal;
		String piezaRetorno = "";

		System.out.println("Ingrese la coordenada donde la pieza se encuentra ubicada:");
		coordenadaInicial = in.nextLine();
		System.out.println("Ingrese la coordenada final:");
		coordenadaFinal = in.nextLine();
		if (MovimientosAjedrez.verificarPosicion(coordenadaInicial) == true
				&& MovimientosAjedrez.verificarPosicion(coordenadaFinal) == true) {
			piezaRetorno = retornarObjetoEnPosicion(coordenadaInicial);
			if (color.charAt(0) == piezaRetorno.charAt(1)) {
				colocarPieza(coordenadaInicial, coordenadaFinal, color);
				contador++;

			} else {
				System.out.println(
						"No puedes mover la pieza en la posicion " + coordenadaInicial + " porque no te pertenece.");
			}

		} else {
			System.out.println("Coordenadas incorrectas.");
		}

	}

	private static void colocarPieza(String coordenadaInicial, String coordenadaFinal, String color) {
		PiezaAjedrez temp = null;
		// Validar que el movimiento sea valido segun la pieza.

		PiezaAjedrez piezaTemp = retornarPiezaPosicion(coordenadaInicial);
		boolean checker = validarMovimiento(piezaTemp, coordenadaInicial, coordenadaFinal);

		if (checker == true) {
			// Remover pieza de posicion inicial.
			for (int i = 0; i < 8; i++) {
				for (int e = 0; e < 8; e++) {
					if (coordenadaInicial.equals(partida.tablero[i][e])) {
						temp = partida.tableroPosiciones[i][e];
						partida.tableroPosiciones[i][e] = new PiezaAjedrez("--", "*", "*", "*");
					}
				}
			}

			// Recolocacion de la pieza.
			for (int i = 0; i < 8; i++) {
				for (int e = 0; e < 8; e++) {
					if (coordenadaFinal.equals(partida.tablero[i][e])) {
						partida.tableroPosiciones[i][e] = temp;
					}
				}
			}
		} else if (checker == false) {
			System.out.println("No puedes realizar ese movimiento, vuelvelo a intentar.");
		}

	}

	public static boolean validarMovimiento(PiezaAjedrez pieza, String coordenadaInicial, String coordenadaFinal) {
		String codigo = pieza.nombre.charAt(0) + "";
		boolean checker = true;
		switch (codigo) {
		case "G":

			checker = movimientoPeon(coordenadaInicial, coordenadaFinal, JuegoAjedrez.getPartida().tablero,
					JuegoAjedrez.getPartida().tableroPosiciones, pieza);
			break;
		}
		return checker;

	}

	public static String retornarObjetoEnPosicion(String posicion) {
		String retorno = "";
		for (int i = 0; i < 8; i++) {
			for (int e = 0; e < 8; e++) {
				if (posicion.equals(partida.tablero[i][e])) {
					retorno = partida.tableroPosiciones[i][e].nombre;
				}
			}
		}

		return retorno;
	}

	public static PiezaAjedrez[][] retornarTablerojuego() {
		return partida.getTableroPosiciones();
	}

	public static boolean movimientoPeon(String posicionInicial, String posicionFinal, String[][] posiciones,
			PiezaAjedrez[][] piezas, PiezaAjedrez peon) {
		boolean checker = false;

		if (contador % 2 == 0) {// Si pieza es negra.

			if (posicionInicial.charAt(0) == posicionFinal.charAt(0)) {// Cuando el peon es nuevo y quiere avanzar una
																		// sola posicion al frente.
				if (Character.getNumericValue(posicionInicial.charAt(1)) + 1 == Character
						.getNumericValue(posicionFinal.charAt(1))) {
					if (retornarPiezaPosicion(posicionFinal).nombre.equals("--")) {
						checker = true;
					}
				}
				if (Character.getNumericValue(posicionInicial.charAt(1)) + 2 == Character
						.getNumericValue(posicionFinal.charAt(1)) && peon.getCantidadMovimientos() == 0) {
					if (retornarPiezaPosicion(posicionFinal).nombre.equals("--")) {
						checker = true;
					}
				}

			} /*
				 * else if (true) {// Cuando el peon es nuevo y quiere avanzar dos posicines al
				 * frente. checker = true; } else if (true) {// Cuando el peon no nuevo yquiere
				 * consumir una posicion en diagonal.
				 * 
				 * checker = true; } else if (true) {//// Cuando el peon no nuevo y quiere
				 * avanzar una sola posicion al frente. checker = true; }
				 */

		} else if (contador % 2 != 0) {// Si pieza es blanca.

//			  if() {//Cuando el peon es nuevo y quiere avanzar una sola posicion al frente.
//			  
//			  checker = true; 
//			  }else if() {//Cuando el peon es nuevo y quiere avanzar dos posicines al frente. 
//			  
//			  
//			  checker = true; 
//			  }else if() {//Cuando el peon no nuevo y quiere consumir una posicion en diagonal. 
//			  
//			  checker = true; }else if()
//			  
//			  {////Cuando el peon no nuevo y quiere avanzar una sola posicion al frente.
//			  checker = true; }

		}

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

	public static PiezaAjedrez retornarPiezaPosicion(String posicionInicial) {

		PiezaAjedrez piezaTemp = new PiezaAjedrez("--", "*", "*", "*");

		for (int i = 0; i < 8; i++) {
			for (int e = 0; e < 8; e++) {
				if (posicionInicial.equals(partida.tablero[i][e])) {
					piezaTemp = partida.tableroPosiciones[i][e];
				}
			}
		}

		return piezaTemp;

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

	public static void guardarPartida() throws FileNotFoundException {

		String json = Serializer.convertirPartidaJSON(1);
		String nombrePartida = "";
		if (json.equals("Default")) {
			System.out.println("Upps, no se ha logrado convertir la partida en formato JSON.");
		} else {
			System.out.println("Ingrese el nombre de la partida:");
			nombrePartida = in.nextLine();
			PersistenciaTexto.guardarArchivo(nombrePartida, json);
			System.out.println("Partida guardada en la siguiente direccion: C:\\Users\\Public\\Documents\\"
					+ nombrePartida + ".txt");
		}
	}

	public static boolean cargarPartidaArchivoTexto(String tipo) throws IOException {

		boolean checker = false;
		checker = PersistenciaTexto.compararJSONTipoSolicitado(partida, tipo);

		if (checker) {
			ImprimirEstadoJuego();
			return true;
		} else {
			return false;
		}

	}

	// Metodos Get y Set de la clase.

	public static PartidaAjedrez getPartida() {
		return partida;
	}

	public static void setPartida(PartidaAjedrez partida) {
		JuegoAjedrez.partida = partida;
	}

}

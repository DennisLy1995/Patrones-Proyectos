package cenfotec.proyecto.juegos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cenfotec.proyecto.artefactos.PartidaDamas;
import cenfotec.proyecto.artefactos.PiezaAjedrez;
import cenfotec.proyecto.artefactos.PiezaDamas;
import cenfotec.proyecto.artefactos.Tablero;
import cenfotec.proyecto.utiles.PersistenciaTexto;
import cenfotec.proyecto.utiles.Serializer;

public class JuegoDamas extends Juego {

	private static PartidaDamas partida = new PartidaDamas();
	private static Scanner in = new Scanner(System.in);

	public JuegoDamas(String jugador1, String jugador2, String ganador, String perdedor) {
		super(jugador1, jugador2, ganador, perdedor);
	}

	public static void iniciarJuego() {
		boolean breaker = false;
		String lector = "";

		while (breaker == false) {
			int cantNegras = contadorPiezasNegras();
			int cantBlancas = contadorPiezasBlancas();

			if (cantNegras <= 0) {
				breaker = true;
				ImprimirEstadoJuego();
				System.out.println("Ganan los peones Blancos.");
			} else if (cantBlancas <= 0) {
				breaker = true;
				ImprimirEstadoJuego();
				System.out.println("Ganan los peones Blancos.");
			} else {

				if (cantNegras == 1 && cantBlancas == 1) {
					breaker = true;
					ImprimirEstadoJuego();
					System.out.println("Empate, ninguno ha ganado.");
				} else {
					ImprimirEstadoJuego();
					imprimirOpcionesJuego();
					// Menu y juego.
					lector = in.nextLine();
					switch (lector) {
					case "1":// mover.
						moverPieza();
						break;
					case "2":// terminar turno
						break;
					case "3":// Guardar partida.
						break;
					case "4":// salir.
						breaker = true;
						break;
					default:
						break;
					}
				}
			}
		}

	}

	public static void moverPieza() {

		String coordenadaInicial;
		String coordenadaFinal;
		PiezaDamas pieza = null;
		boolean checker = false;
		boolean checkerColor = false;

		if (partida.getContador() % 2 != 0) {
			// Mueven piezas negras.
			System.out.println("Mueven las piezas Negras");
		} else {
			// Mueven piezas blancas.
			System.out.println("Mueven las piezas Blancas");
		}
		
		
		System.out.println("Ingrese la coordenada de inicio.");
		coordenadaInicial = in.nextLine();
		System.out.println("Ingrese la coordenada final.");
		coordenadaFinal = in.nextLine();

		if (verificarPosicionTablero(coordenadaInicial) == true && verificarPosicionTablero(coordenadaFinal) == true) {
			// Aqui ingreso la validacion del movimiento segun el tipo de pieza y la pieza
			// que mueva.
			pieza = retornarPiezaPosicion(coordenadaInicial);

			if (partida.getContador() % 2 != 0) {
				// Mueven piezas negras.
				if (pieza.getColor().contentEquals("N")) {
					checkerColor = true;
				}
			} else {
				// Mueven piezas blancas.
				if (pieza.getColor().contentEquals("B")) {
					checkerColor = true;
				}
			}

			if (checkerColor == true) {
				checker = validarMovimientoSegunPieza(pieza.nombre, coordenadaInicial, coordenadaFinal);
				if (checker == true) {
					partida.sumarContador();
					intercambiarPiezas(coordenadaInicial, coordenadaFinal);
				}
				
			} else {
				System.out.println("La pieza no te pertenece.");
			}

		} else {
			System.out.println("Movimiento invalido.");
		}
	}

	private static void intercambiarPiezas(String coordenadaInicial, String coordenadaFinal) {
		PiezaDamas temp = null;

		// Remover pieza de posicion inicial.
		for (int i = 0; i < 10; i++) {
			for (int e = 0; e < 10; e++) {
				if (coordenadaInicial.equals(partida.tablero[i][e])) {
					temp = partida.tableroPiezas[i][e];
					partida.tableroPiezas[i][e] = new PiezaDamas("-", "-", "-", "-");
				}
			}
		}

		// Recolocacion de la pieza.
		for (int i = 0; i < 10; i++) {
			for (int e = 0; e < 10; e++) {
				if (coordenadaFinal.equals(partida.tablero[i][e])) {
					partida.tableroPiezas[i][e] = temp;
				}
			}
		}

	}

	public static boolean validarMovimientoSegunPieza(String pieza, String inicio, String Final) {
		boolean checker = false;

		switch (Character.toString(pieza.charAt(0))) {

		case "P":
			checker = validarMovimientoPeon(inicio, Final);
			break;

		case "R":
			checker = validarMovimientoReina(inicio, Final);
			break;

		default:
			checker = false;
			break;
		}

		return checker;
	}

	public static boolean validarMovimientoPeon(String inicial, String Final) {
		return validarMovimientoSimple(inicial, Final);
	}
	
	public static boolean validarMovimientoSimple(String inicial, String Final) {
		boolean checker = false;
		String movimiento = "";
		PiezaDamas piezaTemp = null;

		// piezas Negras.

		if (partida.getContador() % 2 != 0) {
			
			//Movimiento derecho frontal de una posicion
			movimiento = retornarSiguienteColumna(Character.toString(inicial.charAt(0)))
					+ retornarSiguienteColumna(Character.toString(inicial.charAt(1)));
			
			if (Final.contentEquals(movimiento)) {

				piezaTemp = retornarPiezaPosicion(Final);
				if (piezaTemp.nombre.contentEquals("-")) {
					checker = true;
				}

			} else {
				//Movimiento izquierdo frontal de una posicion
				movimiento = retornarAnteriorColumna(Character.toString(inicial.charAt(0)))
						+ retornarSiguienteColumna(Character.toString(inicial.charAt(1)));
				if (Final.contentEquals(movimiento)) {
					piezaTemp = retornarPiezaPosicion(Final);
					if (piezaTemp.nombre.contentEquals("-")) {
						checker = true;
					}
				} else {

					//Movimiento derecho frontal de 3 posiciones
					movimiento = retornarSiguienteColumna(Character.toString(inicial.charAt(0)))
							+ retornarSiguienteColumna(retornarSiguienteColumna(Character.toString(inicial.charAt(1))));
					if (Final.contentEquals(movimiento)) {
						piezaTemp = retornarPiezaPosicion(Final);
						if (piezaTemp.nombre.contentEquals("-")) {
							movimiento = retornarSiguienteColumna(Character.toString(inicial.charAt(0)))
									+ (retornarSiguienteColumna(Character.toString(inicial.charAt(1))));
							piezaTemp = retornarPiezaPosicion(movimiento);
							if (!piezaTemp.nombre.contentEquals("-")) {
								checker = true;
							} else {
								checker = false;
							}

						}
					} else {

						//Movimiento izquierdo frontal de una posicion
						movimiento = retornarAnteriorColumna(retornarAnteriorColumna(Character.toString(inicial.charAt(0))))
								+ retornarSiguienteColumna(
										retornarSiguienteColumna(retornarSiguienteColumna(Character.toString(inicial.charAt(1)))));
						if (Final.contentEquals(movimiento)) {
							//33 15 medio = 24
							piezaTemp = retornarPiezaPosicion(Final);
							if (piezaTemp.nombre.contentEquals("-")) {
								movimiento = retornarAnteriorColumna(Character.toString(inicial.charAt(0)))
										+ (retornarSiguienteColumna(Character.toString(inicial.charAt(1))));
								piezaTemp = retornarPiezaPosicion(movimiento);
								if (!piezaTemp.nombre.contentEquals("-")) {
									checker = true;
								} else {
									checker = false;
								}

							}

						}
					}

				}

			}

		} else {
			// piezas Blancas.
			//Movimiento derecho frontal de una posicion
			movimiento = retornarSiguienteColumna(Character.toString(inicial.charAt(0)))
					+ retornarAnteriorColumna(Character.toString(inicial.charAt(1)));
			
			if (Final.contentEquals(movimiento)) {

				piezaTemp = retornarPiezaPosicion(Final);
				if (piezaTemp.nombre.contentEquals("-")) {
					checker = true;
				}

			} else {
				//Movimiento izquierdo frontal de una posicion
				movimiento = retornarAnteriorColumna(Character.toString(inicial.charAt(0)))
						+ retornarAnteriorColumna(Character.toString(inicial.charAt(1)));
				if (Final.contentEquals(movimiento)) {
					piezaTemp = retornarPiezaPosicion(Final);
					if (piezaTemp.nombre.contentEquals("-")) {
						checker = true;
					}
				} else {

					//Movimiento derecho frontal de 3 posiciones
					movimiento = retornarSiguienteColumna(retornarSiguienteColumna(Character.toString(inicial.charAt(0))))
							+ retornarAnteriorColumna(retornarAnteriorColumna(Character.toString(inicial.charAt(1))));
					if (Final.contentEquals(movimiento)) {
						piezaTemp = retornarPiezaPosicion(Final);
						// 47 65  medio = 56
						if (piezaTemp.nombre.contentEquals("-")) {
							movimiento = retornarSiguienteColumna(Character.toString(inicial.charAt(0)))
									+ (retornarAnteriorColumna(Character.toString(inicial.charAt(1))));
							piezaTemp = retornarPiezaPosicion(movimiento);
							if (!piezaTemp.nombre.contentEquals("-")) {
								checker = true;
							} else {
								checker = false;
							}

						}
					} else {

						//Movimiento izquierdo frontal de una posicion
						movimiento = retornarAnteriorColumna(retornarAnteriorColumna(Character.toString(inicial.charAt(0))))
								+ retornarAnteriorColumna(
										retornarAnteriorColumna(Character.toString(inicial.charAt(1))));
						//24  46  38
						if (Final.contentEquals(movimiento)) {
							piezaTemp = retornarPiezaPosicion(Final);
							if (piezaTemp.nombre.contentEquals("-")) {
								movimiento = retornarAnteriorColumna(Character.toString(inicial.charAt(0)))
										+ (retornarAnteriorColumna(Character.toString(inicial.charAt(1))));
								piezaTemp = retornarPiezaPosicion(movimiento);
								if (!piezaTemp.nombre.contentEquals("-")) {
									checker = true;
								} else {
									checker = false;
								}

							}

						}
					}

				}

			}

		}

		return checker;
	}
	
	public static boolean validarMovimientoSimpleTrasero(String inicial, String Final) {
		return false;
	}

	public static boolean validarMovimientoReina(String inicial, String Final) {
		boolean checker = false;

		checker = validarMovimientoSimple(inicial, Final);
		
		if(checker == false) {
			checker = validarMovimientoSimpleTrasero(inicial, Final);
		}
		
		return checker;
	}

	public static String retornarSiguienteColumna(String columnaActual) {

		switch (columnaActual) {
		case "1":
			return "2";
		case "2":
			return "3";
		case "3":
			return "4";
		case "4":
			return "5";
		case "5":
			return "6";
		case "6":
			return "7";
		case "7":
			return "8";
		case "8":
			return "9";
		case "9":
			return "X";
		case "X":
			return "NO";
		default:
			return "NO";
		}
	}

	public static String retornarAnteriorColumna(String columnaActual) {

		switch (columnaActual) {
		case "1":
			return "NO";
		case "2":
			return "1";
		case "3":
			return "2";
		case "4":
			return "3";
		case "5":
			return "4";
		case "6":
			return "5";
		case "7":
			return "6";
		case "8":
			return "7";
		case "9":
			return "8";
		case "X":
			return "9";
		default:
			return "NO";
		}
	}

	public static PiezaDamas retornarPiezaPosicion(String posicionInicial) {

		PiezaDamas piezaTemp = new PiezaDamas("-", "-", "-", "-");

		for (int i = 0; i < 10; i++) {
			for (int e = 0; e < 10; e++) {
				if (posicionInicial.equals(partida.tablero[i][e])) {
					piezaTemp = partida.tableroPiezas[i][e];
				}
			}
		}

		return piezaTemp;

	}

	public static String retornarObjetoEnPosicion(String posicion) {
		String retorno = "";
		for (int i = 0; i < 8; i++) {
			for (int e = 0; e < 8; e++) {
				if (posicion.equals(partida.tablero[i][e])) {
					retorno = partida.tableroPiezas[i][e].nombre;
				}
			}
		}
		return retorno;
	}

	public static boolean verificarPosicionTablero(String x) {
		Pattern pattern = Pattern.compile("{2}^[123456789X][123456789X]$");
		Matcher matcher = pattern.matcher(x);
		return matcher.matches();
	}

	public static void imprimirOpcionesJuego() {
		System.out.println("");
		System.out.println("1.Mover Pieza.");
		System.out.println("2.Terminar turno.");
		System.out.println("3.Guardar partida.");
		System.out.println("3.Salir.");
	}

	public static int contadorPiezasNegras() {
		int contador = 0;

		for (int i = 0; i < 10; i++) {
			for (int e = 0; e < 10; e++) {
				if (partida.tableroPiezas[i][e].getColor().contentEquals("N")) {
					contador++;
				}
			}
		}

		return contador;
	}

	public static int contadorPiezasBlancas() {
		int contador = 0;

		for (int i = 0; i < 10; i++) {
			for (int e = 0; e < 10; e++) {
				if (partida.tableroPiezas[i][e].getColor().contentEquals("B")) {
					contador++;
				}
			}
		}

		return contador;
	}

	public static void ImprimirEstadoJuego() {
		System.out.println("|-------------------------------|          |-------------------------------|");
		System.out.println("|          Coordenadas          |          |        Colores tablero        |");
		System.out.println("|-------------------------------|          |-------------------------------|");
		System.out.println();

		for (int i = 0; i < 10; i++) {
			System.out.print("  ");
			for (int e = 0; e < 10; e++) {
				System.out.print(partida.tablero[i][e] + " ");
			}
			System.out.print("             ");
			for (int e = 0; e < 10; e++) {
				System.out.print(partida.tableroColores[i][e] + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("|--------------------------------------------------------------------------|");
		System.out.println("|                                 Partida                                  |");
		System.out.println("|--------------------------------------------------------------------------|");
		System.out.println();
		for (int i = 0; i < 10; i++) {
			System.out.print("    ");
			for (int e = 0; e < 10; e++) {
				System.out.print(partida.tableroPiezas[i][e].nombre + partida.tableroPiezas[i][e].getColor() + " ");
			}
			System.out.print("             ");
			for (int e = 0; e < 10; e++) {
				System.out
						.print(retornarLogo(partida.tableroPiezas[i][e].nombre + partida.tableroPiezas[i][e].getColor())
								+ " ");
			}
			System.out.println();
		}
	}

	public static void guardarPartida() throws FileNotFoundException {

		String json = Serializer.convertirPartidaJSON(2);
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

	public static PartidaDamas getPartida() {
		return partida;
	}

	public static void setPartida(PartidaDamas partida) {
		JuegoDamas.partida = partida;
	}

	public static boolean cargarPartidaArchivoTexto(String tipo) throws IOException {

		boolean checker = false;

		Tablero temp = PersistenciaTexto.compararJSONTipoSolicitado(partida, tipo);
		if (temp != null) {
			partida = (PartidaDamas) temp;
			checker = true;
		} else {
			checker = false;
		}

		return checker;
	}

	public static String retornarLogo(String pieza) {

		String unicodeMessage = pieza;

		switch (unicodeMessage) {
		case "PN":
			unicodeMessage = "\u25C9";
			break;
		case "PB":
			unicodeMessage = "\u25CE";
			break;
		case "--":
			unicodeMessage = "\u25E9";
			break;
		default:
			unicodeMessage = "\u25E9";
			break;

		}
		return unicodeMessage;

	}
}

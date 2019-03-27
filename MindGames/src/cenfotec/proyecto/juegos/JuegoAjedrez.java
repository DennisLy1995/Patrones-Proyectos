package cenfotec.proyecto.juegos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import cenfotec.proyecto.artefactos.PartidaAjedrez;
import cenfotec.proyecto.artefactos.PiezaAjedrez;
import cenfotec.proyecto.logica.MovimientosAjedrez;
import cenfotec.proyecto.utiles.PersistenciaTexto;
import cenfotec.proyecto.utiles.Serializer;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

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

		if (checker == true && verificarPiezasDiferenteEquipo(coordenadaInicial, coordenadaFinal) == false) {
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
						partida.tableroPosiciones[i][e].sumarMovimiento();
					}
				}
			}
			contador++;
		} else if (checker == false) {
			System.out.println("No puedes realizar ese movimiento, vuelvelo a intentar.");
		}

	}

	public static boolean verificarPiezasDiferenteEquipo(String inicial, String ultimo) {
		boolean checker = false;
		String valorDeInicial = "";
		String valorDeUltimo = "";

		for (int i = 0; i < 8; i++) {
			for (int e = 0; e < 8; e++) {
				if (inicial.equals(partida.tablero[i][e])) {
					valorDeInicial = partida.tableroPosiciones[i][e].nombre;
				}
				if (ultimo.equals(partida.tablero[i][e])) {
					valorDeUltimo = partida.tableroPosiciones[i][e].nombre;
				}
			}
		}

		if (valorDeUltimo.charAt(1) == valorDeInicial.charAt(1)) {
			checker = true;
		} else {
			checker = false;
		}

		return checker;

	}

	public static boolean validarMovimiento(PiezaAjedrez pieza, String coordenadaInicial, String coordenadaFinal) {
		String codigo = pieza.nombre.charAt(0) + "";
		boolean checker = true;
		switch (codigo) {
		case "G":// Peon
			checker = movimientoPeon(coordenadaInicial, coordenadaFinal, pieza);
			break;

		case "R":// Torre
			checker = movimientoTorre(coordenadaInicial, coordenadaFinal);
			break;

		case "K":// Rey
			checker = movimientoRey(coordenadaInicial, coordenadaFinal);
			break;

		case "Q":// Reina
			checker = movimientoReina(coordenadaInicial, coordenadaFinal);
			break;

		case "N":// alfil
			checker = movimientoAlfil(coordenadaInicial, coordenadaFinal);
			break;

		case "B":// Caballo
			 checker = movimientoCaballo(coordenadaInicial, coordenadaFinal);
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

	public static boolean movimientoPeon(String posicionInicial, String posicionFinal, PiezaAjedrez peon) {
		boolean checker = false;

		if (posicionInicial.contentEquals(posicionFinal)) {
			System.out.println("Las coordenadas no pueden coincidir.");
		} else {
			if (contador % 2 == 0) {// Si pieza es negra.

				if (posicionInicial.charAt(0) == posicionFinal.charAt(0)) {// Cuando el peon es nuevo y quiere avanzar
																			// una
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

				} else if (posicionFinal.charAt(0) == retornarSiguienteColumna(posicionInicial.charAt(0) + "")
						.charAt(0)) {
					// Cuando se quiere comer una posicion
					if (Character.getNumericValue(posicionInicial.charAt(1)) + 1 == Character
							.getNumericValue(posicionFinal.charAt(1))) {
						if (retornarPiezaPosicion(posicionFinal).nombre.equals("--")) {

						} else {
							checker = true;
						}
					}
				} else if (posicionFinal.charAt(0) == retornarAnteriorColumna(posicionInicial.charAt(0) + "")
						.charAt(0)) {
					// Cuando se quiere comer una posicion
					if (Character.getNumericValue(posicionInicial.charAt(1)) + 1 == Character
							.getNumericValue(posicionFinal.charAt(1))) {
						if (retornarPiezaPosicion(posicionFinal).nombre.equals("--")) {

						} else {
							checker = true;
						}
					}
				}

			} else if (contador % 2 != 0) {// Si pieza es blanca.

				if (posicionInicial.charAt(0) == posicionFinal.charAt(0)) {// Cuando el peon es nuevo y quiere avanzar
																			// una
					// sola posicion al frente.
					if (Character.getNumericValue(posicionInicial.charAt(1)) - 1 == Character
							.getNumericValue(posicionFinal.charAt(1))) {
						if (retornarPiezaPosicion(posicionFinal).nombre.equals("--")) {
							checker = true;
						}
					}
					if (Character.getNumericValue(posicionInicial.charAt(1)) - 2 == Character
							.getNumericValue(posicionFinal.charAt(1)) && peon.getCantidadMovimientos() == 0) {
						if (retornarPiezaPosicion(posicionFinal).nombre.equals("--")) {
							checker = true;
						}
					}

				} else if (posicionFinal.charAt(0) == retornarSiguienteColumna(posicionInicial.charAt(0) + "")
						.charAt(0)) {
//					Cuando se quiere comer una posicion
					if (Character.getNumericValue(posicionInicial.charAt(1)) - 1 == Character
							.getNumericValue(posicionFinal.charAt(1))) {
						if (retornarPiezaPosicion(posicionFinal).nombre.equals("--")) {

						} else {
							checker = true;
						}
					}
				} else if (posicionFinal.charAt(0) == retornarAnteriorColumna(posicionInicial.charAt(0) + "")
						.charAt(0)) {
					// Cuando se quiere comer una posicion
					if (Character.getNumericValue(posicionInicial.charAt(1)) - 1 == Character
							.getNumericValue(posicionFinal.charAt(1))) {
						if (retornarPiezaPosicion(posicionFinal).nombre.equals("--")) {

						} else {
							checker = true;
						}
					}
				}

			}
		}

		return checker;
	}

	public static boolean movimientoRey(String posicionInicial, String posicionFinal) {
		boolean checker = false;

		if (posicionFinal.contentEquals(
				posicionInicial.charAt(0) + "" + Character.getNumericValue(posicionInicial.charAt(1) + 1))
				|| posicionFinal.contentEquals(
						posicionInicial.charAt(0) + "" + Character.getNumericValue(posicionInicial.charAt(1) - 1))) {
			// Cuando el movimiento es en la misma columna.
			checker = true;
		} else if (posicionFinal
				.contentEquals(retornarSiguienteColumna(posicionInicial.charAt(0) + "") + posicionInicial.charAt(1))
				|| posicionFinal.contentEquals(
						retornarAnteriorColumna(posicionInicial.charAt(0) + "") + posicionInicial.charAt(1))) {
			// Cuando el movimiento es en la misma fila.
			checker = true;
		} else if (posicionFinal
				.contentEquals(retornarSiguienteColumna(posicionInicial.charAt(0) + "")
						+ Character.getNumericValue(posicionInicial.charAt(1) + 1))
				|| posicionFinal.contentEquals(retornarAnteriorColumna(posicionInicial.charAt(0) + "")
						+ Character.getNumericValue(posicionInicial.charAt(1) + 1))) {
			// Cuando el movimiento es diagonal arriba.
			// Trabajando en este if.
			checker = true;
		} else if (posicionFinal
				.contentEquals(retornarSiguienteColumna(posicionInicial.charAt(0) + "")
						+ Character.getNumericValue(posicionInicial.charAt(1) - 1))
				|| posicionFinal.contentEquals(retornarAnteriorColumna(posicionInicial.charAt(0) + "")
						+ Character.getNumericValue(posicionInicial.charAt(1) - 1))) {
			// Cuando el movimiento es diagonal atras.
			checker = true;
		}

		return checker;
	}

	public static boolean movimientoReina(String posicionInicial, String posicionFinal) {
		boolean checker = false;
		boolean found = false;

		//trabajando en este movimiento.
		if(posicionInicial.contentEquals(posicionFinal)) {
			checker = false;
		}else {
			//Evaluar movimiento en diagonales.
			found = calcularPiezasEnMedioDiagonal(posicionInicial, posicionFinal);
			
			if(found == false) {
				//Evaluar movimiento al frente y hacia atras.
				
				
			}
			if(found == false) {
				//Evaluar movimiento horizontal.
				
			}
			

			//Ultima evaluacion.
			if(found == false) {
				checker = false;
			}else {
				checker = true;
			}
		}
		
		
		return checker;
	}

	public static boolean movimientoCaballo(String posicionInicial, String posicionFinal) {
		boolean checker = false;

		String PosicionArribaDerechaUnaAlfrente = retornarSiguienteColumna(retornarSiguienteColumna(posicionInicial.charAt(0) + "")) + 
				(Character.getNumericValue(posicionInicial.charAt(1))+1);
		
		String PosicionArribaIzquierdaUnaAlfrente = retornarAnteriorColumna(retornarAnteriorColumna(posicionInicial.charAt(0) + "")) + 
				(Character.getNumericValue(posicionInicial.charAt(1))+1);
		
		String posicionAbajoDerechaUnaAtras = retornarSiguienteColumna(retornarSiguienteColumna(posicionInicial.charAt(0) + "")) + 
				(Character.getNumericValue(posicionInicial.charAt(1))-1);
		
		String posicionAbajoIzquierdaUnaAtras = retornarAnteriorColumna(retornarAnteriorColumna(posicionInicial.charAt(0) + "")) + 
				(Character.getNumericValue(posicionInicial.charAt(1))-1);
		
		
		String PosicionArribaDerechaTresAlfrente = retornarSiguienteColumna(posicionInicial.charAt(0) + "") + 
				(Character.getNumericValue(posicionInicial.charAt(1))+2);
		
		String PosicionArribaIzquierdaTresAlfrente = retornarAnteriorColumna(posicionInicial.charAt(0) + "") + 
				(Character.getNumericValue(posicionInicial.charAt(1))+2);
		
		String posicionAbajoDerechaTresAtras = retornarSiguienteColumna(posicionInicial.charAt(0) + "") + 
				(Character.getNumericValue(posicionInicial.charAt(1))-2);
		
		String posicionAbajoIzquierdaTresAtras = retornarAnteriorColumna(posicionInicial.charAt(0) + "") + 
				(Character.getNumericValue(posicionInicial.charAt(1))-2);
		
		if(posicionFinal.contentEquals(PosicionArribaDerechaUnaAlfrente)) {
			checker = true;
		}else if(posicionFinal.contentEquals(PosicionArribaIzquierdaUnaAlfrente)) {
			checker = true;
		}else if(posicionFinal.contentEquals(posicionAbajoDerechaUnaAtras)) {
			checker = true;
		}else if(posicionFinal.contentEquals(posicionAbajoIzquierdaUnaAtras)) {
			checker = true;
		}else if(posicionFinal.contentEquals(PosicionArribaDerechaTresAlfrente)) {
			checker = true;
		}else if(posicionFinal.contentEquals(PosicionArribaIzquierdaTresAlfrente)) {
			checker = true;
		}else if(posicionFinal.contentEquals(posicionAbajoDerechaTresAtras)) {
			checker = true;
		}else if(posicionFinal.contentEquals(posicionAbajoIzquierdaTresAtras)) {
			checker = true;
		}else {
			checker = false;
		}
		
		return checker;
	}

	public static boolean movimientoTorre(String posicionInicial, String posicionFinal) {
		boolean checker = false;
		boolean checkerPiezasEnMedio = false;
		int inicio = 0;
		int ultimo = 0;
		PiezaAjedrez pieza = null;

		if (posicionInicial.contentEquals(posicionFinal)) {
			System.out.println("Las coordenadas no pueden coincidir.");
		} else {
			if (contador % 2 == 0) {// Si la pieza es negra.

				if (posicionInicial.charAt(0) == posicionFinal.charAt(0)) {
					// Si el movimiento es en la misma fila

					if (Character.getNumericValue(posicionInicial.charAt(1)) < Character
							.getNumericValue(posicionFinal.charAt(1))) {// Si el movimiento es hacia el frente.
						// Si el movimiento es en columna al frente.
						inicio = Character.getNumericValue(posicionInicial.charAt(1)) + 1;
						ultimo = Character.getNumericValue(posicionFinal.charAt(1));
						for (int i = inicio; i < ultimo; i++) {
							pieza = retornarPiezaPosicion(posicionInicial.charAt(0) + Integer.toString(i));
							if (pieza.nombre.equals("--")) {

							} else {
								checkerPiezasEnMedio = true;
							}
							System.out.println("Estoy en la posicion: " + i);
						}
						if (checkerPiezasEnMedio) {
							checker = false;
							System.out.println("Hay piezas entre la posicion inicial y la posicion final.");
						} else {
							checker = true;
						}

					} else if (Character.getNumericValue(posicionInicial.charAt(1)) > Character
							.getNumericValue(posicionFinal.charAt(1))) {
						// Si el movimiento es en columna hacia atras.
						inicio = Character.getNumericValue(posicionInicial.charAt(1)) - 1;
						ultimo = Character.getNumericValue(posicionFinal.charAt(1));
						for (int i = inicio; i > ultimo; i--) {
							pieza = retornarPiezaPosicion(posicionInicial.charAt(0) + Integer.toString(i));
							if (pieza.nombre.equals("--")) {

							} else {
								checkerPiezasEnMedio = true;
							}
							System.out.println("Estoy en la posicion: " + i);
						}
						if (checkerPiezasEnMedio) {
							checker = false;
							System.out.println("Hay piezas entre la posicion inicial y la posicion final.");
						} else {
							checker = true;
						}
					}

				} else if (posicionInicial.charAt(1) == posicionFinal.charAt(1)) {
					// Si el movimiento es en la misma columna

					if (determinarDireccionHorizontal(posicionInicial, posicionFinal).contentEquals("izquierda")) {
						// Si el movimiento es hacia la izquierda.
						String columnaActual = retornarSiguienteColumna(posicionInicial.charAt(0) + "");
						pieza = retornarPiezaPosicion(columnaActual + posicionInicial.charAt(1));
						while ((columnaActual).equals(retornarAnteriorColumna(posicionFinal.charAt(0) + "")) != true) {

							if (pieza.nombre.contentEquals("--")) {

							} else {
								checkerPiezasEnMedio = true;
							}
							columnaActual = retornarSiguienteColumna(columnaActual);
							pieza = retornarPiezaPosicion(
									retornarSiguienteColumna(columnaActual) + posicionInicial.charAt(1));

						}

						if (checkerPiezasEnMedio == true) {
							checker = false;
						} else {
							checker = true;
						}

					} else if (determinarDireccionHorizontal(posicionInicial, posicionFinal).contentEquals("derecha")) {

						// Si el movimiento es hacia la izquierda.
						String columnaActual = retornarAnteriorColumna(posicionInicial.charAt(0) + "");
						pieza = retornarPiezaPosicion(columnaActual + posicionInicial.charAt(1));
						while ((columnaActual).equals(retornarSiguienteColumna(posicionFinal.charAt(0) + "")) != true) {

							if (pieza.nombre.contentEquals("--")) {

							} else {
								checkerPiezasEnMedio = true;
							}
							columnaActual = retornarAnteriorColumna(columnaActual);
							pieza = retornarPiezaPosicion(
									retornarAnteriorColumna(columnaActual) + posicionInicial.charAt(1));

						}

						if (checkerPiezasEnMedio == true) {
							checker = false;
						} else {
							checker = true;
						}

					}

				}

			} else if (contador % 2 != 0) {// Si la pieza es blanca.

				if (posicionInicial.charAt(0) == posicionFinal.charAt(0)) {
					// Si el movimiento es en la misma fila

					if (Character.getNumericValue(posicionInicial.charAt(1)) < Character
							.getNumericValue(posicionFinal.charAt(1))) {// Si el movimiento es hacia el frente.
						// Si el movimiento es en columna al frente.
						inicio = Character.getNumericValue(posicionInicial.charAt(1)) + 1;
						ultimo = Character.getNumericValue(posicionFinal.charAt(1));
						for (int i = inicio; i < ultimo; i++) {
							pieza = retornarPiezaPosicion(posicionInicial.charAt(0) + Integer.toString(i));
							if (pieza.nombre.equals("--")) {

							} else {
								checkerPiezasEnMedio = true;
							}
							System.out.println("Estoy en la posicion: " + i);
						}
						if (checkerPiezasEnMedio) {
							checker = false;
							System.out.println("Hay piezas entre la posicion inicial y la posicion final.");
						} else {
							checker = true;
						}

					} else if (Character.getNumericValue(posicionInicial.charAt(1)) > Character
							.getNumericValue(posicionFinal.charAt(1))) {
						// Si el movimiento es en columna hacia atras.
						inicio = Character.getNumericValue(posicionInicial.charAt(1)) - 1;
						ultimo = Character.getNumericValue(posicionFinal.charAt(1));
						for (int i = inicio; i > ultimo; i--) {
							pieza = retornarPiezaPosicion(posicionInicial.charAt(0) + Integer.toString(i));
							if (pieza.nombre.equals("--")) {

							} else {
								checkerPiezasEnMedio = true;
							}
							System.out.println("Estoy en la posicion: " + i);
						}
						if (checkerPiezasEnMedio) {
							checker = false;
							System.out.println("Hay piezas entre la posicion inicial y la posicion final.");
						} else {
							checker = true;
						}
					}

				} else if (posicionInicial.charAt(1) == posicionFinal.charAt(1)) {
					// Si el movimiento es en la misma columna

					if (determinarDireccionHorizontal(posicionInicial, posicionFinal).contentEquals("izquierda")) {
						// Si el movimiento es hacia la izquierda.
						String columnaActual = retornarSiguienteColumna(posicionInicial.charAt(0) + "");
						pieza = retornarPiezaPosicion(columnaActual + posicionInicial.charAt(1));
						while ((columnaActual).equals(retornarAnteriorColumna(posicionFinal.charAt(0) + "")) != true) {

							if (pieza.nombre.contentEquals("--")) {

							} else {
								checkerPiezasEnMedio = true;
							}
							columnaActual = retornarSiguienteColumna(columnaActual);
							pieza = retornarPiezaPosicion(
									retornarSiguienteColumna(columnaActual) + posicionInicial.charAt(1));

						}

						if (checkerPiezasEnMedio == true) {
							checker = false;
						} else {
							checker = true;
						}

					} else if (determinarDireccionHorizontal(posicionInicial, posicionFinal).contentEquals("derecha")) {

						// Si el movimiento es hacia la izquierda.
						String columnaActual = retornarAnteriorColumna(posicionInicial.charAt(0) + "");
						pieza = retornarPiezaPosicion(columnaActual + posicionInicial.charAt(1));
						while ((columnaActual).equals(retornarSiguienteColumna(posicionFinal.charAt(0) + "")) != true) {

							if (pieza.nombre.contentEquals("--")) {

							} else {
								checkerPiezasEnMedio = true;
							}
							columnaActual = retornarAnteriorColumna(columnaActual);
							pieza = retornarPiezaPosicion(
									retornarAnteriorColumna(columnaActual) + posicionInicial.charAt(1));

						}

						if (checkerPiezasEnMedio == true) {
							checker = false;
						} else {
							checker = true;
						}
					}
				}
			}
		}

		return checker;
	}

	public static boolean movimientoAlfil(String posicionInicial, String posicionFinal) {
		boolean checker = calcularPiezasEnMedioDiagonal(posicionInicial, posicionFinal);
		
		return checker;
	}

	public static boolean calcularPiezasEnMedioDiagonal(String inicial, String Final) {
		boolean checker = false;
		boolean breaker = false;
		boolean piezasEnMedio = false;
		boolean sideFound = false;
		int siguiente = 0;
		String posicionTemporal = inicial;
		PiezaAjedrez piezaTemp = null;

		// Seccion de arriba derecha
		while (breaker == false) {
			if (posicionTemporal.contentEquals(Final)) {
				breaker = true;
				sideFound = true;
			} else {
				if (retornarSiguienteColumna(posicionTemporal.charAt(0) + "").contentEquals("NO")) {
					checker = false;
					breaker = true;
					piezasEnMedio = false;
				} else {
					siguiente = Character.getNumericValue(posicionTemporal.charAt(1)) + 1;
					posicionTemporal = retornarSiguienteColumna(posicionTemporal.charAt(0) + "")
							+ siguiente;
					piezaTemp = retornarPiezaPosicion(posicionTemporal);
					if (piezaTemp.nombre.contentEquals("--")) {
						
					} else {
						if(retornarPiezaPosicion(Final).nombre.contentEquals(piezaTemp.nombre)) {
							
						}else {
							piezasEnMedio = true;
							breaker = true;
						}
					}
				}
			}
		}

		
		
		// Seccion de abajo derecha.
		if (sideFound == false) {
			
			checker = false;
			breaker = false;
			piezasEnMedio = false;
			sideFound = false;
			posicionTemporal = inicial;
			
			while (breaker == false) {
				if (posicionTemporal.contentEquals(Final)) {
					breaker = true;
					sideFound = true;
				} else {
					if (retornarSiguienteColumna(posicionTemporal.charAt(0) + "").contentEquals("NO")) {
						checker = false;
						breaker = true;
						piezasEnMedio = false;
					} else {
						siguiente = Character.getNumericValue(posicionTemporal.charAt(1)) - 1;
						posicionTemporal = retornarSiguienteColumna(posicionTemporal.charAt(0) + "")
								+ siguiente;
						piezaTemp = retornarPiezaPosicion(posicionTemporal);
						if (piezaTemp.nombre.contentEquals("--")) {

						} else {
							if(retornarPiezaPosicion(Final).nombre.contentEquals(piezaTemp.nombre)) {
								
							}else {
								piezasEnMedio = true;
								breaker = true;
							}
						}
					}
				}
			}
		}

		
		posicionTemporal = inicial;
		
		// Seccion de arriba izquierda
		if (sideFound == false) {
			
			checker = false;
			breaker = false;
			piezasEnMedio = false;
			sideFound = false;
			posicionTemporal = inicial;
			
			while (breaker == false) {
				if (posicionTemporal.contentEquals(Final)) {
					breaker = true;
					sideFound = true;
				} else {
					if (retornarAnteriorColumna(posicionTemporal.charAt(0) + "").contentEquals("NO")) {
						checker = false;
						breaker = true;
						piezasEnMedio = false;
					} else {
						siguiente = Character.getNumericValue(posicionTemporal.charAt(1)) + 1;
						posicionTemporal = retornarAnteriorColumna(posicionTemporal.charAt(0) + "")
								+ siguiente;
						piezaTemp = retornarPiezaPosicion(posicionTemporal);
						if (piezaTemp.nombre.contentEquals("--")) {

						} else {
							if(retornarPiezaPosicion(Final).nombre.contentEquals(piezaTemp.nombre)) {
								
							}else {
								piezasEnMedio = true;
								breaker = true;
							}
						}
					}
				}
			}
		}
		
		posicionTemporal = inicial;

		// seccion de abajo izquierda.
		if (sideFound == false) {
			
			checker = false;
			breaker = false;
			piezasEnMedio = false;
			sideFound = false;
			posicionTemporal = inicial;
			
			while (breaker == false) {
				if (posicionTemporal.contentEquals(Final)) {
					breaker = true;
					sideFound = true;
				} else {
					if (retornarAnteriorColumna(posicionTemporal.charAt(0) + "").contentEquals("NO")) {
						checker = false;
						breaker = true;
						piezasEnMedio = false;
					} else {
						siguiente = Character.getNumericValue(posicionTemporal.charAt(1)) - 1;
						posicionTemporal = retornarAnteriorColumna(posicionTemporal.charAt(0) + "")
								+ siguiente;
						piezaTemp = retornarPiezaPosicion(posicionTemporal);
						if (piezaTemp.nombre.contentEquals("--")) {

						} else {
							if(retornarPiezaPosicion(Final).nombre.contentEquals(piezaTemp.nombre)) {
								
							}else {
								piezasEnMedio = true;
								breaker = true;
							}
						}
					}
				}
			}
		}

		// evaluacion final.
		if (piezasEnMedio == true || sideFound == false) {
			checker = false;
		} else {
			checker = true;
		}
		
		return checker;
	}

	public static String determinarDireccionHorizontal(String posicionInicial, String posicionFinal) {
		String lado = "";
		int primero = 0;
		int ultimo = 0;
		int fila = Character.getNumericValue(posicionInicial.charAt(1) - 1);
		for (int i = 0; i < 8; i++) {
			if (posicionInicial.equals(partida.tablero[fila][i]) && ultimo != 1) {
				primero = 1;
			}
			if (posicionFinal.equals(partida.tablero[fila][i]) && primero != 1) {
				ultimo = 1;
			}
		}

		if (primero > ultimo) {
			lado = "izquierda";
		} else if (primero < ultimo) {
			lado = "derecha";
		}

		return lado;
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
		//Impresion de los Simbolos.
		try {
			imprimirTableroLogos();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void imprimirTableroLogos() throws UnsupportedEncodingException {
	    
		System.out.println();
		System.out.println("                  |-------------------------------|");
		System.out.println("                  |             Juego             |");
		System.out.println("                  |-------------------------------|");
		System.out.println();

		for (int i = 0; i < 8; i++) {
			System.out.print("                                                                         ");
			for (int e = 0; e < 8; e++) {
				System.out.print(retornarLogo(partida.tableroPosiciones[i][e].nombre)+" ");
			}
			System.out.println();
		}
	     
	  }
	
	public static String retornarLogo(String pieza) {
		
		String unicodeMessage = pieza;
		
		switch(unicodeMessage) {
		case "GN":
			unicodeMessage = "\u265F";
			break;
		case "RN":
			unicodeMessage = "\u265C";
			break;
		case "NN":
			unicodeMessage = "\u265D";
			break;
		case "BN":
			unicodeMessage = "\u265E";
			break;
		case "KN":
			unicodeMessage = "\u265A";
			break;
		case "QN":
			unicodeMessage = "\u265B";
			break;
		case "GB":
			unicodeMessage = "\u2659";
			break;
		case "RB":
			unicodeMessage = "\u2656";
			break;
		case "NB":
			unicodeMessage = "\u2658";
			break;
		case "BB":
			unicodeMessage = "\u2657";
			break;
		case "QB":
			unicodeMessage = "\u2655";
			break;
		case "KB":
			unicodeMessage = "\u2654";
			break;
		case "--":
			unicodeMessage = "\u058E";
			break;
			
		}

	    //PrintStream out = new PrintStream(System.out, true, "UTF-8");
	    return unicodeMessage;
		
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

	public static String retornarSiguienteColumna(String columnaActual) {

		switch (columnaActual) {
		case "a":
			return "b";
		case "b":
			return "c";
		case "c":
			return "d";
		case "d":
			return "e";
		case "e":
			return "f";
		case "f":
			return "g";
		case "g":
			return "h";
		case "h":
			return "NO";
		default:
			return "NO";
		}
	}

	public static String retornarAnteriorColumna(String columnaActual) {

		switch (columnaActual) {
		case "a":
			return "NO";
		case "b":
			return "a";
		case "c":
			return "b";
		case "d":
			return "c";
		case "e":
			return "d";
		case "f":
			return "e";
		case "g":
			return "f";
		case "h":
			return "g";
		default:
			return "NO";
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

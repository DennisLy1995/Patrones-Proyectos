package cenfotec.proyecto.juegos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cenfotec.proyecto.artefactos.PartidaDamas;
import cenfotec.proyecto.artefactos.PiezaDamas;
import cenfotec.proyecto.artefactos.Tablero;
import cenfotec.proyecto.utiles.PersistenciaTexto;
import cenfotec.proyecto.utiles.Serializer;

public class JuegoDamas extends Juego{

	private static PartidaDamas partida = new PartidaDamas();
	private static Scanner in = new Scanner(System.in);
	
	public JuegoDamas(String jugador1, String jugador2, String ganador, String perdedor) {
		super(jugador1, jugador2, ganador, perdedor);
	}

	public static void iniciarJuego() {
		boolean breaker = false;
		String lector = "";
		
		while(breaker == false) {
			int cantNegras = contadorPiezasNegras();
			int cantBlancas = contadorPiezasBlancas();
			
			if(cantNegras <= 0) {
				breaker = true;
				ImprimirEstadoJuego();
				System.out.println("Ganan los peones Blancos.");
			}else if (cantBlancas <= 0) {
				breaker = true;
				ImprimirEstadoJuego();
				System.out.println("Ganan los peones Blancos.");
			}else {
				
				if(cantNegras == 1 && cantBlancas ==1) {
					breaker = true;
					ImprimirEstadoJuego();
					System.out.println("Empate, ninguno ha ganado.");
				}else {
					ImprimirEstadoJuego();
					imprimirOpcionesJuego();
					//Menu y juego.
					lector = in.nextLine();
					switch(lector) {
					case "1"://mover.
						moverPieza();
						break;
					case "2"://terminar turno
						break;
					case "3"://Guardar partida.
						break;
					case "4"://salir.
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
		String pieza = "";
		
		System.out.println("Ingrese la coordenada de inicio.");
		coordenadaInicial = in.nextLine();
		System.out.println("Ingrese la coordenada final.");
		coordenadaFinal = in.nextLine();
		
		if(verificarPosicionTablero(coordenadaInicial)== true && verificarPosicionTablero(coordenadaFinal) == true) {
			System.out.println("Movimiento valido.");
			
			//Aqui ingreso la validacion del movimiento segun el tipo de pieza y la pieza que mueva.
			pieza = retornarObjetoEnPosicion(coordenadaInicial);
			
		}else {
			System.out.println("Movimiento invalido.");
		}
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
		
		for(int i=0;i<10;i++) {
			for(int e = 0; e<10; e++) {
				if(partida.tableroPiezas[i][e].getColor().contentEquals("N")) {
					contador++;
				}
			}
		}
		
		return contador;
	}
	
	public static int contadorPiezasBlancas() {
		int contador = 0;
		
		for(int i=0;i<10;i++) {
			for(int e = 0; e<10; e++) {
				if(partida.tableroPiezas[i][e].getColor().contentEquals("B")) {
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
				System.out.print(retornarLogo(partida.tableroPiezas[i][e].nombre + partida.tableroPiezas[i][e].getColor()) + " ");
			}
			System.out.println();
		}		
	}

	public static void guardarPartida() throws FileNotFoundException {
		
		String json = Serializer.convertirPartidaJSON(2);
		String nombrePartida = "";
		if(json.equals("Default")) {
			System.out.println("Upps, no se ha logrado convertir la partida en formato JSON.");
		}else {
			System.out.println("Ingrese el nombre de la partida:");
			nombrePartida = in.nextLine();
			PersistenciaTexto.guardarArchivo(nombrePartida, json);
			System.out.println("Partida guardada en la siguiente direccion: C:\\Users\\Public\\Documents\\"+nombrePartida+".txt");
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
		if (temp != null){
			partida = (PartidaDamas) temp;
			checker = true;
		}else {
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

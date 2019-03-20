package cenfotec.proyecto.juegos;

import cenfotec.proyecto.artefactos.PartidaDamas;
import cenfotec.proyecto.logica.MovimientosDamas;

public class JuegoDamas extends Juego implements MovimientosDamas{

	private static PartidaDamas partida = new PartidaDamas();
	
	public JuegoDamas(String jugador1, String jugador2, String ganador, String perdedor) {
		super(jugador1, jugador2, ganador, perdedor);
		// TODO Auto-generated constructor stub
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
			System.out.print("                        ");
			for (int e = 0; e < 10; e++) {
				System.out.print(partida.tableroPiezas[i][e].nombre + partida.tableroPiezas[i][e].getColor() + " ");
			}
			System.out.println();
		}
	}

	public static PartidaDamas getPartida() {
		return partida;
	}

	public static void setPartida(PartidaDamas partida) {
		JuegoDamas.partida = partida;
	}
	
	
}

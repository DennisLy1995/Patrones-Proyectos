package cenfotec.proyecto.juegos;

import cenfotec.proyecto.artefactos.PartidaGo;
import cenfotec.proyecto.logica.MovimientosGo;

public class JuegoGo extends Juego implements MovimientosGo{

	private static PartidaGo partida = new PartidaGo();
	
	public JuegoGo(String jugador1, String jugador2, String ganador, String perdedor) {
		super(jugador1, jugador2, ganador, perdedor);
		// TODO Auto-generated constructor stub
	}

	public static void ImprimirEstadoJuego() {
		System.out.println("           |-------------------------------|");
		System.out.println("           |          Coordenadas          |");
		System.out.println("           |-------------------------------|");
		System.out.println();
		PartidaGo test = new PartidaGo();
		for (int i = 0; i < 19; i++) {
			System.out.print("  ");
			for (int e = 0; e < 19; e++) {
				System.out.print(test.tablero[i][e] + " ");
			}
			System.out.println();
		}
	}
	
}

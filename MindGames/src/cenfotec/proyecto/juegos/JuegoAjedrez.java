package cenfotec.proyecto.juegos;

import cenfotec.proyecto.artefactos.PartidaAjedrez;
import cenfotec.proyecto.artefactos.PiezaAjedrez;
import cenfotec.proyecto.logica.GeneralesJuego;
import cenfotec.proyecto.logica.MovimientosAjedrez;

public class JuegoAjedrez extends Juego implements MovimientosAjedrez{

	public static PartidaAjedrez partida = new PartidaAjedrez();
	
	public JuegoAjedrez(String jugador1, String jugador2, String ganador, String perdedor) {
		super(jugador1, jugador2, ganador, perdedor);
		
	}

	public void cargarPartida() {
		
	}

	public void initializarPartida() {
		
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
	
	
	
	
	//metodos de la interfaz.
	


	@Override
	public boolean CalcularMovimiento() {
		
		return false;
	}

	@Override
	public boolean verificarPosicionFinal() {
		
		return false;
	}

	@Override
	public boolean verificarPosicionesEnMedio() {
		
		return false;
	}

	@Override
	public void moverPieza(PiezaAjedrez pieza) {
		
		
	}

	

	
}

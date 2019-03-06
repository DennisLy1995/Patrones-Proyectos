package cenfotec.proyecto.juegos;

import cenfotec.proyecto.artefactos.PartidaAjedrez;
import cenfotec.proyecto.artefactos.PiezaAjedrez;
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
	
	public static PiezaAjedrez[] retornarTablerojuego() {
		return null;
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

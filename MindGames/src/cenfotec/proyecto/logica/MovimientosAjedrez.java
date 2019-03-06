package cenfotec.proyecto.logica;

import cenfotec.proyecto.artefactos.PiezaAjedrez;

public interface MovimientosAjedrez {

	boolean CalcularMovimiento();
	boolean verificarPosicionFinal();
	boolean verificarPosicionesEnMedio();
	void moverPieza(PiezaAjedrez pieza);
	
}

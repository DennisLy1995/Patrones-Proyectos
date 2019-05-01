package cenfotec.proyecto.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import cenfotec.proyecto.juegos.JuegoAjedrez;



public class TestUnitAjedrez {

	@Test
	public void pruebaRetornarPiezaAjedrez() {
		
		String posicion = "g1";
		String posicion1 = "g5";
		String posicion2 = "g8";
		String nombrePieza = "NN";
		String nombrePieza1 = "--";
		String nombrePieza2 = "NB";
		
		assertEquals(JuegoAjedrez.retornarPiezaPosicion(posicion).getNombre(),nombrePieza,"");
		assertEquals(JuegoAjedrez.retornarPiezaPosicion(posicion1).getNombre(),nombrePieza1,"");
		assertEquals(JuegoAjedrez.retornarPiezaPosicion(posicion2).getNombre(),nombrePieza2,"");
		
		
	}
	
	@Test
	public void pruebaSigColumnaAjedrez() {
		
		String columna = "a";
		String columna1 = "d";
		String columna2 = "f";
		String columnaSiguiente = "b";
		String columnaSiguiente1 = "e";
		String columnaSiguiente2 = "g";
		
		assertEquals(JuegoAjedrez.retornarSiguienteColumna(columna),columnaSiguiente,"Se espera que la siguiente columna sea b");
		assertEquals(JuegoAjedrez.retornarSiguienteColumna(columna1),columnaSiguiente1,"Se espera que la siguiente columna sea e");
		assertEquals(JuegoAjedrez.retornarSiguienteColumna(columna2),columnaSiguiente2,"Se espera que la siguiente columna sea g");
		
	}
	
	
	@Test
	public void pruebaAntColumnaAjedrez() {
		
		String columna = "a";
		String columna1 = "d";
		String columna2 = "f";
		String columnaSiguiente = "NO";
		String columnaSiguiente1 = "c";
		String columnaSiguiente2 = "e";
		
		assertEquals(JuegoAjedrez.retornarAnteriorColumna(columna),columnaSiguiente,"Se espera que la siguiente columna sea b");
		assertEquals(JuegoAjedrez.retornarAnteriorColumna(columna1),columnaSiguiente1,"Se espera que la siguiente columna sea e");
		assertEquals(JuegoAjedrez.retornarAnteriorColumna(columna2),columnaSiguiente2,"Se espera que la siguiente columna sea g");
		
	}
	
	
	@Test
	public void pruebaLogosAjedrez() {
		
		String GB = "\u265F";
		String RB = "\u265C";
		String NB = "\u265E";
		String BB = "\u265D";
		String KB = "\u265A";
		String QB = "\u265B";
		String GN = "\u2659";
		String RN = "\u2656";
		String NN = "\u2658";
		String BN = "\u2657";
		String KN = "\u2654";
		String QN = "\u2655";
		String defaultValue = "\u058E";
		
		assertEquals(JuegoAjedrez.retornarLogo("GB"), GB ,"Se espera \u265F");
		assertEquals(JuegoAjedrez.retornarLogo("RB"), RB ,"Se espera \u265C");
		assertEquals(JuegoAjedrez.retornarLogo("NB"), NB ,"Se espera \u265E");
		assertEquals(JuegoAjedrez.retornarLogo("BB"), BB ,"Se espera \u265D");
		assertEquals(JuegoAjedrez.retornarLogo("KB"), KB ,"Se espera \u265A");
		assertEquals(JuegoAjedrez.retornarLogo("QB"), QB ,"Se espera \u265B");
		assertEquals(JuegoAjedrez.retornarLogo("GN"), GN ,"Se espera \u2659");
		assertEquals(JuegoAjedrez.retornarLogo("RN"), RN ,"Se espera \u2656");
		assertEquals(JuegoAjedrez.retornarLogo("NN"), NN ,"Se espera \u2658");
		assertEquals(JuegoAjedrez.retornarLogo("BN"), BN ,"Se espera \u2657");
		assertEquals(JuegoAjedrez.retornarLogo("KN"), KN ,"Se espera \u2654");
		assertEquals(JuegoAjedrez.retornarLogo("QN"), QN ,"Se espera \u2655");
		assertEquals(JuegoAjedrez.retornarLogo("--"), defaultValue ,"Se espera \u058E");
		
	}
	
	
}

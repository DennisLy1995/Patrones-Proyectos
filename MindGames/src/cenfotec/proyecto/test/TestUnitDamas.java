package cenfotec.proyecto.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import cenfotec.proyecto.juegos.JuegoDamas;

public class TestUnitDamas {

	@Test
	public void pruebaRetornarPiezaDamas() {
		
		String posicion = "11";
		String posicion1 = "15";
		String posicion2 = "19";
		String nombrePieza = "P";
		String nombrePieza1 = "-";
		String nombrePieza2 = "P";
		
		assertEquals(JuegoDamas.retornarPiezaPosicion(posicion).getNombre(),nombrePieza,"");
		assertEquals(JuegoDamas.retornarPiezaPosicion(posicion1).getNombre(),nombrePieza1,"");
		assertEquals(JuegoDamas.retornarPiezaPosicion(posicion2).getNombre(),nombrePieza2,"");
		
		
	}
	
	@Test
	public void pruebaSigColumnaDamas() {
		
		String columna = "1";
		String columna1 = "5";
		String columna2 = "X";
		String columnaSiguiente = "2";
		String columnaSiguiente1 = "6";
		String columnaSiguiente2 = "NO";
		
		assertEquals(JuegoDamas.retornarSiguienteColumna(columna),columnaSiguiente,"Se espera que la siguiente columna sea b");
		assertEquals(JuegoDamas.retornarSiguienteColumna(columna1),columnaSiguiente1,"Se espera que la siguiente columna sea e");
		assertEquals(JuegoDamas.retornarSiguienteColumna(columna2),columnaSiguiente2,"Se espera que la siguiente columna sea g");
		
	}
	
	
	@Test
	public void pruebaAntSigColumnaDamas() {
		
		String columna = "1";
		String columna1 = "5";
		String columna2 = "X";
		String columnaSiguiente = "NO";
		String columnaSiguiente1 = "4";
		String columnaSiguiente2 = "9";
		
		assertEquals(JuegoDamas.retornarAnteriorColumna(columna),columnaSiguiente,"Se espera que la siguiente columna sea b");
		assertEquals(JuegoDamas.retornarAnteriorColumna(columna1),columnaSiguiente1,"Se espera que la siguiente columna sea e");
		assertEquals(JuegoDamas.retornarAnteriorColumna(columna2),columnaSiguiente2,"Se espera que la siguiente columna sea g");
		
	}
	
	
	@Test
	public void pruebaLogosDamas() {
		
		String PN = "\u25C9";
		String PB = "\u25CE";
		String RN = "\u25D5";
		String RB = "\u25D4";
		String defaultValue = "\u25A2";
		
		assertEquals(JuegoDamas.retornarLogo("PN"), PN ,"Se espera \u25C9");
		assertEquals(JuegoDamas.retornarLogo("PB"), PB ,"Se espera \u25CE");
		assertEquals(JuegoDamas.retornarLogo("RN"), RN ,"Se espera \u25D5");
		assertEquals(JuegoDamas.retornarLogo("RB"), RB ,"Se espera \u25D4");
		assertEquals(JuegoDamas.retornarLogo("--"), defaultValue ,"Se espera \u25A2");
		
	}
	
	
}

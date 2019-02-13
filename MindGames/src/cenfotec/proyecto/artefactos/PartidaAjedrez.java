package cenfotec.proyecto.artefactos;

public class PartidaAjedrez extends Tablero {

	public PiezaAjedrez[][] tableroPosiciones;

	public PartidaAjedrez() {

		this.tablero = new String[8][8];
		tableroPosiciones = new PiezaAjedrez[8][8];
		String letra = "";
		//Se agregan las coordenadas del tablero.
		for(int i=0;i<8;i++) {
			letra = PartidaAjedrez.retornarLetraCordenada(i);
			for(int e=0;e<8;e++) {
				tablero[e][i] = letra+(e+1);
			}
			
		}
		//Se agregan las piezas y sus respectivas posiciones iniciales.
		for(int i=0;i<8;i++) {
			for(int e=2;e<6;e++) {
				tableroPosiciones[e][i] = new PiezaAjedrez("NA", "*", "*", "*");
			}
		}
		//Ciclos for para agregar los peones
		for(int i=0;i<8;i++) {
			PiezaAjedrez peon = new PiezaAjedrez("PN", "Jugador1", "peon", "Negro");
			tableroPosiciones[1][i] = peon;
		}
		for(int i=0;i<8;i++) {
			PiezaAjedrez peon = new PiezaAjedrez("PB", "Jugador2", "peon", "Blanco");
			tableroPosiciones[6][i] = peon;
		}
		for(int i=0;i<8;i++) {
			tableroPosiciones[0][i] = new PiezaAjedrez("NA", "*", "*", "*");
		}
		for(int i=0;i<8;i++) {
			tableroPosiciones[7][i] = new PiezaAjedrez("NA", "*", "*", "*");	
		}
		
	}

	public static String retornarLetraCordenada(int columna) {

		String letra = "";
		switch (columna) {

		case 0:
			letra = "a";
			break;
		case 1:
			letra = "b";
			break;
		case 2:
			letra = "c";
			break;
		case 3:
			letra = "d";
			break;
		case 4:
			letra = "e";
			break;
		case 5:
			letra = "f";
			break;
		case 6:
			letra = "g";
			break;
		case 7:
			letra = "h";
			break;
		}
		return letra;
	}
	
	public static PiezaAjedrez retonarPiezaSegunPosicion() {
		
		
		
		return null;
	}

}

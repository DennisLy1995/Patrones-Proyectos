package cenfotec.proyecto.artefactos;

public class PartidaAjedrez extends Tablero {

	PiezaAjedrez[][] tableroPosiciones;

	public PartidaAjedrez() {

		this.tablero = new String[8][8];
		tableroPosiciones = new PiezaAjedrez[9][8];
		String letra = "";
		for(int i=0;i<8;i++) {
			letra = PartidaAjedrez.retornarLetraCordenada(i);
			for(int e=0;e<8;e++) {
				tablero[e][i] = letra+(e+1);
			}
			
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
	
	public static PiezaAjedrez retonarPieza() {
		
		
		return null;
	}

}

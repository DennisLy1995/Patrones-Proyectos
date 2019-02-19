package cenfotec.proyecto.artefactos;

public class PartidaDamas extends Tablero {

	public String[][] tableroColores = new String[10][10];
	public PiezaDamas[][] tableroPiezas = new PiezaDamas[10][10];

	public PartidaDamas() {

		this.tablero = new String[10][10];

		// Se agregan las coordenadas del tablero.
		for (int i = 0; i < 10; i++) {
			for (int e = 0; e < 10; e++) {
				if ((i + 1) == 10 && (e + 1) == 10) {
					tablero[e][i] = ("X") + ("X");
				} else if ((i + 1) == 10) {
					tablero[e][i] = ("X") + (e + 1);
				} else if ((e + 1) == 10) {
					tablero[e][i] = (i + 1) + ("X");
				} else {
					tablero[e][i] = (i + 1) + "" + (e + 1);
				}

			}

		}
		String color = "BL";
		// Se agregan las coordenadas del tablero.
		for (int i = 0; i < 10; i++) {
			for (int e = 0; e < 10; e++) {
				color = devolverColorOpuesto(color);
				tableroColores[e][i] = color;
			}
			color = devolverColorOpuesto(color);
		}
		
		
		
		//Ciclo for para colocar las piezas blancas.
		for (int i = 0; i < 10; i++) {
			for (int e = 0; e < 10; e++) {
				
			}
		}
		
		//Ciclo for para colocar las piezas negras.
		for (int i = 0; i < 10; i++) {
			for (int e = 0; e < 10; e++) {
				
			}
		}
		
		

	}
	
	public String devolverColorOpuesto(String color) {
		if (color.equals("BL")) {
			color = "NR";
		} else if (color.equals("NR")) {
			color = "BL";
		}
		return color;
	}

}

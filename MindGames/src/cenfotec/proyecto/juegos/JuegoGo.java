package cenfotec.proyecto.juegos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import cenfotec.proyecto.artefactos.PartidaGo;
import cenfotec.proyecto.artefactos.Tablero;
import cenfotec.proyecto.utiles.PersistenciaTexto;
import cenfotec.proyecto.utiles.Serializer;

public class JuegoGo extends Juego{

	private static PartidaGo partida = new PartidaGo();
	private static Scanner in = new Scanner(System.in);
	
	public JuegoGo(String jugador1, String jugador2, String ganador, String perdedor) {
		super(jugador1, jugador2, ganador, perdedor);
	}

	public static void ImprimirEstadoJuego() {
		Menu.imprimirConSaltoLinea("           |-------------------------------|");
		Menu.imprimirConSaltoLinea("           |          Coordenadas          |");
		Menu.imprimirConSaltoLinea("           |-------------------------------|");
		Menu.imprimirConSaltoLinea("");
		PartidaGo test = new PartidaGo();
		for (int i = 0; i < 19; i++) {
			Menu.imprimirSinSaltoLinea("  ");
			for (int e = 0; e < 19; e++) {
				Menu.imprimirSinSaltoLinea(test.tablero[i][e] + " ");
			}
			Menu.imprimirConSaltoLinea("");
		}
	}
	
	public static void guardarPartida() throws FileNotFoundException {
		
		String json = Serializer.convertirPartidaJSON(3);
		String nombrePartida = "";
		if(json.equals("Default")) {
			Menu.imprimirConSaltoLinea("Upps, no se ha logrado convertir la partida en formato JSON.");
		}else {
			Menu.imprimirConSaltoLinea("Ingrese el nombre de la partida:");
			nombrePartida = in.nextLine();
			PersistenciaTexto.guardarArchivo(nombrePartida, json);
			Menu.imprimirConSaltoLinea("Partida guardada en la siguiente direccion: C:\\Users\\Public\\Documents\\"+nombrePartida+".txt");
		}
	}
	
	public static PartidaGo getPartida() {
		return partida;
	}

	public static void setPartida(PartidaGo partida) {
		JuegoGo.partida = partida;
	}

	public static boolean cargarPartidaArchivoTexto(String tipo) throws IOException {

		boolean checker = false;
		
		Tablero temp = PersistenciaTexto.compararJSONAjedrez(partida, tipo);
		if (temp != null){
			partida = (PartidaGo) temp;
			checker = true;
		}else {
			checker = false;
		}
		
		return checker;
	}
	
}

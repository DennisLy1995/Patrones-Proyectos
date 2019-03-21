package cenfotec.proyecto.juegos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import cenfotec.proyecto.artefactos.PartidaGo;
import cenfotec.proyecto.logica.MovimientosGo;
import cenfotec.proyecto.utiles.PersistenciaTexto;
import cenfotec.proyecto.utiles.Serializer;

public class JuegoGo extends Juego implements MovimientosGo{

	private static PartidaGo partida = new PartidaGo();
	private static Scanner in = new Scanner(System.in);
	
	public JuegoGo(String jugador1, String jugador2, String ganador, String perdedor) {
		super(jugador1, jugador2, ganador, perdedor);
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
	
	public static void guardarPartida() throws FileNotFoundException {
		
		String json = Serializer.convertirPartidaJSON(3);
		String nombrePartida = "";
		if(json.equals("Default")) {
			System.out.println("Upps, no se ha logrado convertir la partida en formato JSON.");
		}else {
			System.out.println("Ingrese el nombre de la partida:");
			nombrePartida = in.nextLine();
			PersistenciaTexto.guardarArchivo(nombrePartida, json);
			System.out.println("Partida guardada en la siguiente direccion: C:\\Users\\Public\\Documents\\"+nombrePartida+".txt");
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
		checker = PersistenciaTexto.compararJSONTipoSolicitado(partida, tipo);
		
		if(checker) {
			ImprimirEstadoJuego();
			return true;
		}else {
			return false;
		}
		
	}
	
}

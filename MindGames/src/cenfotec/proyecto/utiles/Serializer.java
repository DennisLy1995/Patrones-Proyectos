package cenfotec.proyecto.utiles;

import com.google.gson.Gson;

import cenfotec.proyecto.artefactos.PartidaAjedrez;
import cenfotec.proyecto.artefactos.PartidaDamas;
import cenfotec.proyecto.artefactos.PartidaGo;
import cenfotec.proyecto.artefactos.Tablero;
import cenfotec.proyecto.juegos.JuegoAjedrez;
import cenfotec.proyecto.juegos.JuegoDamas;
import cenfotec.proyecto.juegos.JuegoGo;

public class Serializer {

	//Deserializacion.
	
	public static PartidaAjedrez convertirJSONPartidaAjedrez(String temp) {
		Gson gson = new Gson();
		return gson.fromJson(temp, PartidaAjedrez.class);
	}
	
	public static PartidaGo convertirJSONPartidaGo(String temp) {
		Gson gson = new Gson();
		return gson.fromJson(temp, PartidaGo.class);
	}
	
	public static PartidaDamas convertirJSONPartidaDamas(String temp) {
		Gson gson = new Gson();
		return gson.fromJson(temp, PartidaDamas.class);
	}
	
	//Serializacion
	
	
	public static String convertirPartidaJSON(int tipoJuego) {
		String partidaTemp = "Default";
		Gson gson = new Gson();
		try{
			partidaTemp = gson.toJson(retornarPartida(tipoJuego));
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return partidaTemp;
	}
	
	
	
	public static Tablero retornarPartida(int tipoJuego) {
		
		Tablero partida = null;
		switch(tipoJuego) {
		case 1:
			partida = JuegoAjedrez.getPartida();
			break;
		case 2:
			partida = JuegoDamas.getPartida();
			break;
		case 3:
			partida = JuegoGo.getPartida();
			break;
		}
		
		return partida;
	}
	
	
	
}

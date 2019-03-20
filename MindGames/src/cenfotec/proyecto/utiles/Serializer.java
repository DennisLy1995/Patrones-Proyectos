package cenfotec.proyecto.utiles;

import com.google.gson.Gson;

import cenfotec.proyecto.artefactos.PartidaAjedrez;
import cenfotec.proyecto.artefactos.PartidaDamas;
import cenfotec.proyecto.artefactos.PartidaGo;

public class Serializer {

	//serializacion
	
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
	
	//Deserializacion
	
	public static String convertirPartidaAjedrezJSON(PartidaAjedrez partida) {
		String partidaTemp = "Default";
		Gson gson = new Gson();
		try{
			partidaTemp = gson.toJson(partida);
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return partidaTemp;
	}
	
	
	public static String convertirPartidaDamasJSON(PartidaDamas partida) {
		String partidaTemp = "Default";
		Gson gson = new Gson();
		try{
			partidaTemp = gson.toJson(partida);
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return partidaTemp;
	}
	
	
	public static String convertirPartidaGoJSON(PartidaGo partida) {
		String partidaTemp = "Default";
		Gson gson = new Gson();
		try{
			partidaTemp = gson.toJson(partida);
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return partidaTemp;
	}
	
	
	
}

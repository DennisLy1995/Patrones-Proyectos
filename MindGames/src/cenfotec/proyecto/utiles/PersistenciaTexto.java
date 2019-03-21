package cenfotec.proyecto.utiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import cenfotec.proyecto.artefactos.Tablero;


public class PersistenciaTexto {

	private static Scanner in = new Scanner(System.in);
	private static String direccionArchivos = "C:\\Users\\Public\\Documents\\";
	private static File file;
	
	public static boolean guardarArchivo(String nombre, String datos) throws FileNotFoundException {
		
		boolean checker = false;
		direccionArchivos = "C:\\Users\\Public\\Documents\\";
		direccionArchivos = direccionArchivos+nombre+".txt";
		file = new File(direccionArchivos);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(datos);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		
		return checker;
	}
	
	public static String leerArchivoTexto(String nombre) throws IOException {
		String lecturaTemp = "";
		String lectura="";
		direccionArchivos = "C:\\Users\\Public\\Documents\\";
		direccionArchivos = direccionArchivos+nombre+".txt";
		FileReader fileReader = new FileReader(direccionArchivos);
		try {
			
			BufferedReader buffer = new BufferedReader(fileReader);
			
			while((lecturaTemp=buffer.readLine())!=null) {
				lectura = lectura + lecturaTemp;
			}
			fileReader.close();
		}catch(Exception e) {
			fileReader.close();
			System.out.println("No se ha logrado leer el archivo.");
		}
		
		return lectura;
	}
	
	public static boolean compararJSONTipoSolicitado(Tablero partida, String tipo) {
		System.out.println("Ingrese el nombre de la partida:");
		String nombreArchivo = in.nextLine();
		try {
			String temp = PersistenciaTexto.leerArchivoTexto(nombreArchivo);
			partida = Serializer.convertirJSONPartidaAjedrez(temp);
			if(partida.getTipoJuego().equals(tipo)) {
				//ImprimirEstadoJuego();
				return true;
			}else {
				System.out.println("Verifique que el archivo pertenezca a una partida de " +tipo+".");
				return false;
			}
			
		}catch(Exception e) {
			System.out.println("No se ha cargado el archivo, verifique que el archivo exista.");
			return false;
		}
	}
	
	
}

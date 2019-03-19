package cenfote.proyecto.utiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class PersistenciaTexto {

	private static String direccionArchivos = "C:\\Users\\Public\\Documents\\";
	private static File file;
	
	public static boolean guardarArchivo(String nombre, String datos) throws FileNotFoundException {
		
		boolean checker = false;
		direccionArchivos = direccionArchivos+nombre+".txt";
		file = new File(direccionArchivos);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(datos);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //close resources
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		
		
		return checker;
		
	}
	
}

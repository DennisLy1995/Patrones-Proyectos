package cenfotec.proyecto.juegos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import cenfotec.proyecto.gestores.GestorAjedrez;
import cenfotec.proyecto.gestores.GestorDamas;
import cenfotec.proyecto.gestores.GestorGo;

public class Menu {

	static java.io.BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	private static String[] menuPrincipal= { "Mindgames",
			                                  "----------------------------------------------------",
			                                  "          1. Ajedrez.", 
			                                  "           2. Tablero.", 
			                                  "            3. Go.", 
			                                  "             4. Salir.",
			                                  "",
			                                  "INGRESAR LA OPCION: "};
	private static String[] menuAjedrez= { "Ajedrez",
			                                "---------------------------------------------------------------------",
                                            "          1. Cargar partida.", 
                                            "           2. Nueva partida.", 
                                            "            3. Descargar partida actual en archivo de texto." , 
                                            "             4. Salir.",
                                            "", 
                                            "INGRESAR LA OPCION: " };
	private static String[] menuDamas= { "Damas Chinas",
                                          "---------------------------------------------------------------------",
                                          "          1. Cargar partida.", 
                                          "           2. Nueva partida.", 
                                          "            3. Descargar partida actual en archivo de texto." , 
                                          "             4. Salir.",
                                          "", 
                                          "INGRESAR LA OPCION: " };
	private static String[] menuGo= { "GO",
                                       "---------------------------------------------------------------------",
                                       "          1. Cargar partida.", 
                                       "           2. Nueva partida.", 
                                       "            3. Descargar partida actual en archivo de texto." , 
                                       "             4. Salir.",
                                       "", 
                                       "INGRESAR LA OPCION: " };

	public static void main(String[] args) throws IOException {

		int breakerLogin = 0;
		int breakerMenu = 0;
		boolean validarUsuario=false;

		while (breakerLogin == 0) {

			System.out.println("Bienvenido a Mind Games\nIngrese su nombre de usuario\n");
			String nombreUsuario = in.readLine();
			System.out.println("Ingrese su contraseña\n");
			String contrasena = in.readLine();

			validarUsuario = validacionCredenciales(nombreUsuario, contrasena);
			
			// Se valida que la contraseña y el nombre de usuario sean los correctos.
			if (validarUsuario == true) {

				while (breakerMenu == 0) {

					// Despliegue del menu principal
					int breakerSubMenu = 0;
					imprimirMenu(menuPrincipal);
					String opcionMenu = in.readLine();
					String opcionMenuJuego = "";
					switch (opcionMenu) {
					case "1":
						while (breakerSubMenu == 0) {
							imprimirMenu(menuAjedrez);
							opcionMenuJuego = Menu.opcionmenuJuego();
							breakerSubMenu = Menu.redireccionadorAjedrez(opcionMenuJuego);
						}

						break;
					case "2":
						while (breakerSubMenu == 0) {
							imprimirMenu(menuDamas);
							opcionMenuJuego = Menu.opcionmenuJuego();
							breakerSubMenu = Menu.redireccionadorDamas(opcionMenuJuego);
						}

						break;
					case "3":
						while (breakerSubMenu == 0) {
							imprimirMenu(menuGo);
							opcionMenuJuego = Menu.opcionmenuJuego();
							breakerSubMenu = Menu.redireccionadorGo(opcionMenuJuego);
						}
						break;
					case "4":
						breakerMenu = 1;
						System.out.println("\nHasta luego!!!\n");
						break;
					default:
						break;
					}

				}
				breakerLogin = 1;
			} else {
				breakerLogin = 0;
				System.out.println(
						"\nEl usuario no se encuentra en el sistema\n Por mientras use el siguiente \n usuario:Dennis; contraseña:1234");
			}

		}

	}

	private static boolean validacionCredenciales(String nombreUsuario, String contrasena) {
		
		return true;
	}

	public static void imprimirMenu(String[] opcionesMenu) {
		System.out.println("\n");
		for (int i = 0; i < opcionesMenu.length; i++) {
			System.out.println(opcionesMenu[i]);
		}
		System.out.println("\n");
	}

	
	public static String opcionmenuJuego() throws IOException {
		String opcion = in.readLine();
		return opcion;
	}

	public static int redireccionadorAjedrez(String opcionMenu) {
		int retorno = 0;
		switch (opcionMenu) {
		case "1":
			try {
				Menu.cargarPartidaAjedrez();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		case "2":
			Menu.iniciarJuegoNuevoAjedrez();
			break;
		case "3":
			try {
				Menu.guardarPartidaAjedrez();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case "4":
			System.out.println("\nDe vuelta al menu de mindgames.\n");
			retorno = 1;
			break;
		default:
			retorno = 0;
			break;
		}
		return retorno;
	}

	
	public static int redireccionadorGo(String opcionMenu) throws IOException {
		int retorno = 0;
		switch (opcionMenu) {
		case "1":
			cargarPartidaGo();
			break;
		case "2":
			Menu.iniciarJuegoNuevoGo();
			break;
		case "3":
			try {
				Menu.guardarPartidaGo();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case "4":
			System.out.println("\nDe vuelta al menu de mindgames.\n");
			retorno = 1;
			break;
		default:
			break;
		}
		return retorno;
	}

	public static int redireccionadorDamas(String opcionMenu) throws IOException {
		int retorno = 0;
		switch (opcionMenu) {
		case "1":
			cargarPartidaDamas();
			break;
		case "2":
			Menu.iniciarJuegoNuevoDamas();
			break;
		case "3":
			try {
				Menu.guardarPartidaDamas();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case "4":
			System.out.println("\nDe vuelta al menu de mindgames.\n");
			retorno = 1;
			break;
		default:
			break;
		}
		return retorno;
	}


	
	public static void iniciarJuegoNuevoAjedrez() {

		GestorAjedrez.iniciarPartida();
	}

	public static void iniciarJuegoNuevoDamas() {

		GestorDamas.imprimirEstadoJuego();
	}

	public static void iniciarJuegoNuevoGo() {
		GestorGo.imprimirEstadoJuego();
	}

	
	private static void guardarPartidaAjedrez() throws FileNotFoundException {
		GestorAjedrez.guardarPartida();
	}
	
	private static void guardarPartidaDamas() throws FileNotFoundException {
		GestorDamas.guardarPartida();
	}
	
	private static void guardarPartidaGo() throws FileNotFoundException {
		GestorGo.guardarPartida();
	}
	
	
	
	private static void cargarPartidaAjedrez() throws IOException {
		System.out.println("Ingrese un 1 para cargar partida de un archivo de texto.\n"
				+ "Ingrese un 2 para cargar la partida de nuestra base de datos.");
		String router=in.readLine();
		switch(router) {
		case "1":
			GestorAjedrez.cargarPartidaArchivoTexto();
			break;
		case "2":
			break;
		}
	}
	
	private static void cargarPartidaDamas() throws IOException {
		System.out.println("Ingrese un 1 para cargar partida de un archivo de texto.\n"
				+ "Ingrese un 2 para cargar la partida de nuestra base de datos.");
		String router=in.readLine();
		switch(router) {
		case "1":
			GestorDamas.cargarPartida();
			break;
		case "2":
			break;
		}

	}
	
	private static void cargarPartidaGo() throws IOException {
		System.out.println("Ingrese un 1 para cargar partida de un archivo de texto.\n"
				+ "Ingrese un 2 para cargar la partida de nuestra base de datos.");
		String router=in.readLine();
		switch(router) {
		case "1":
			GestorGo.cargarPartida();
			break;
		case "2":
			break;
		}
	}
	
}

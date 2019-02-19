package cenfotec.proyecto.juegos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cenfotec.proyecto.artefactos.PartidaAjedrez;
import cenfotec.proyecto.artefactos.PartidaDamas;
import cenfotec.proyecto.artefactos.PartidaGo;

public class Menu {

	static java.io.BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {

		int breakerLogin = 0;
		int breakerMenu = 0;

		while (breakerLogin == 0) {

			System.out.println("Bienvenido a Mind Games\nIngrese su nombre de usuario\\n");
			String nombreUsuario = in.readLine();
			System.out.println("Ingrese su contraseña\n");
			String contrasena = in.readLine();

			// Se valida que la contraseña y el nombre de usuario sean los correctos.
			if (nombreUsuario.equals("Dennis") && contrasena.contentEquals("1234")) {

				while (breakerMenu == 0) {

					// Despliegue del menu principal
					int breakerSubMenu = 0;
					imprimirMenuPrincipal();
					String opcionMenu = in.readLine();
					String opcionMenuJuego = "";
					switch (opcionMenu) {
					case "1":
						while (breakerSubMenu == 0) {
							imprimirMenuAjedres();
							opcionMenuJuego = Menu.opcionmenuJuego();
							breakerSubMenu = Menu.redireccionadorAjedrez(opcionMenuJuego);
						}

						break;
					case "2":
						while (breakerSubMenu == 0) {
							imprimirMenuTablero();
							opcionMenuJuego = Menu.opcionmenuJuego();
							breakerSubMenu = Menu.redireccionadorDamas(opcionMenuJuego);
						}

						break;
					case "3":
						while (breakerSubMenu == 0) {
							imprimirMenuGo();
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

	/*
	 * Funciones creadas para impresion de los diferentes Menus.
	 */

	public static void imprimirMenuPrincipal() {
		String[] opcionesMenu = { "1.Ajedrez", "2.Tablero", "3.Go", "4.Salir" };
		System.out.println("\n");
		for (int i = 0; i < opcionesMenu.length; i++) {
			System.out.println(opcionesMenu[i]);
		}
		System.out.println("\n");
	}

	public static void imprimirMenuAjedres() {
		String[] opcionesMenu = { "---Ajedrez---", "1.Cargar partida", "2.Nueva partida", "3.Salir" };
		System.out.println("\n");
		for (int i = 0; i < opcionesMenu.length; i++) {
			System.out.println(opcionesMenu[i]);
		}
		System.out.println("\n");
	}

	public static void imprimirMenuTablero() {
		String[] opcionesMenu = { "---Tablero---", "1.Cargar partida", "2.Nueva partida", "3.Salir" };
		System.out.println("\n");
		for (int i = 0; i < opcionesMenu.length; i++) {
			System.out.println(opcionesMenu[i]);
		}
		System.out.println("\n");
	}

	public static void imprimirMenuGo() {
		String[] opcionesMenu = { "---GO---", "1.Cargar partida", "2.Nueva partida", "3.Salir" };
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
			System.out.println("Cargar partida de ajedrez esta en progreso.");
			break;
		case "2":
			// System.out.println("Nueva partida de ajedrez esta en progreso.");
			Menu.pruebaImpresionMapaAjedrez();
			break;
		case "3":
			System.out.println("\nDe vuelta al menu de mindgames.\n");
			retorno = 1;
			break;
		default:
			break;
		}
		return retorno;
	}

	public static int redireccionadorGo(String opcionMenu) {
		int retorno = 0;
		switch (opcionMenu) {
		case "1":
			System.out.println("Cargar partida de GO esta en progreso.");
			break;
		case "2":
			// System.out.println("Nueva partida partida de GO esta en progreso.");
			Menu.pruebaImpresionMapaGo();
			break;
		case "3":
			System.out.println("\nDe vuelta al menu de mindgames.\n");
			retorno = 1;
			break;
		default:
			break;
		}
		return retorno;
	}

	public static int redireccionadorDamas(String opcionMenu) {
		int retorno = 0;
		switch (opcionMenu) {
		case "1":
			System.out.println("Cargar partida de Damas esta en progreso.");
			break;
		case "2":
			// System.out.println("Nueva partida partida de Damas esta en progreso.");
			Menu.pruebaImpresionMapaDamas();
			break;
		case "3":
			System.out.println("\nDe vuelta al menu de mindgames.\n");
			retorno = 1;
			break;
		default:
			break;
		}
		return retorno;
	}

	// Este metodo ahi que borrarlo.
	public static void pruebaImpresionMapaAjedrez() {

		System.out.println("|-------------------------------|   |-------------------------------|");
		System.out.println("|          Coordenadas          |   |         Partida actual        |");
		System.out.println("|-------------------------------|   |-------------------------------|");
		System.out.println();

		PartidaAjedrez test = new PartidaAjedrez();
		for (int i = 0; i < 8; i++) {
			System.out.print("     ");
			for (int e = 0; e < 8; e++) {
				System.out.print(test.tablero[i][e] + " ");
			}
			System.out.print("            ");
			for (int e = 0; e < 8; e++) {
				System.out.print(test.tableroPosiciones[i][e].nombre + " ");
			}
			System.out.println();
		}
	}

	public static void pruebaImpresionMapaDamas() {

		System.out.println("|-------------------------------|          |-------------------------------|");
		System.out.println("|          Coordenadas          |          |        Colores tablero        |");
		System.out.println("|-------------------------------|          |-------------------------------|");
		System.out.println();
		PartidaDamas test = new PartidaDamas();
		for (int i = 0; i < 10; i++) {
			System.out.print("  ");
			for (int e = 0; e < 10; e++) {
				System.out.print(test.tablero[i][e] + " ");
			}
			System.out.print("             ");
			for (int e = 0; e < 10; e++) {
				System.out.print(test.tableroColores[i][e] + " ");
			}
			System.out.println();
		}

	}

	public static void pruebaImpresionMapaGo() {
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

}

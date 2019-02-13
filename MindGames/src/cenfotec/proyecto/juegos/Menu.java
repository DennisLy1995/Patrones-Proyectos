package cenfotec.proyecto.juegos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {

	static java.io.BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		
		int breakerLogin = 0;
		int breakerMenu = 0;
		
		while(breakerLogin==0) {
			
			System.out.println("Bienvenido a Mind Games\nIngrese su nombre de usuario\\n");
			String nombreUsuario = in.readLine();
			System.out.println("Ingrese su contraseña\n");
			String contrasena = in.readLine();
			
			//Se valida que la contraseña y el nombre de usuario sean los correctos.
			if(nombreUsuario.equals("Dennis") && contrasena.contentEquals("1234")) {
				
				while(breakerMenu == 0) {
					
					//Despliegue del menu principal
					int breakerSubMenu = 0;
					imprimirMenuPrincipal();
					String opcionMenu = in.readLine();
					
					switch(opcionMenu) {
					case "1":
						imprimirMenuAjedres();
						while(breakerSubMenu==0) {
							
						}
						breakerSubMenu = 0;
						break;
					case "2":
						imprimirMenuTablero();
						while(breakerSubMenu==0) {
							
						}
						breakerSubMenu = 0;
						break;
					case "3":
						imprimirMenuGo();
						while(breakerSubMenu==0) {
							
						}
						breakerSubMenu = 0;
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
			}else {
				breakerLogin = 0;
				System.out.println("\nEl usuario no se encuentra en el sistema\n Por mientras| usuario:Dennis; contraseña:1234");
			}
			
		}
		
		

	}
	
	/*
	 * Funciones creadas para impresion de los diferentes Menus.
	 */
	
	public static void imprimirMenuPrincipal() {
		String[] opcionesMenu = {"1.Ajedrez","2.Tablero","3.Go","4.Salir"};
		System.out.println("\n");
		for(int i=0;i<opcionesMenu.length;i++) {
			System.out.println(opcionesMenu[i]);
		}
		System.out.println("\n");
	}
	
	
	
	public static void imprimirMenuAjedres(){
		String[] opcionesMenu = {"---Ajedrez---","1.Continuar","2.Nueva partida","3.Salir"};
		System.out.println("\n");
		for(int i=0;i<opcionesMenu.length;i++) {
			System.out.println(opcionesMenu[i]);
		}
		System.out.println("\n");
	}
	
	public static void imprimirMenuTablero(){
		String[] opcionesMenu = {"---Tablero---","1.Continuar","2.Nueva partida","3.Salir"};
		System.out.println("\n");
		for(int i=0;i<opcionesMenu.length;i++) {
			System.out.println(opcionesMenu[i]);
		}
		System.out.println("\n");
	}
	
	public static void imprimirMenuGo() {
		String[] opcionesMenu = {"---GO---","1.Continuar","2.Nueva partida","3.Salir"};
		System.out.println("\n");
		for(int i=0;i<opcionesMenu.length;i++) {
			System.out.println(opcionesMenu[i]);
		}
		System.out.println("\n");
	}

}

package cenfotec.proyecto.juegos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cenfotec.proyecto.artefactos.PartidaAjedrez;
import cenfotec.proyecto.artefactos.PiezaAjedrez;
import cenfotec.proyecto.artefactos.Piezas.Pieza;
import cenfotec.proyecto.artefactos.Tablero;
import cenfotec.proyecto.utiles.PersistenciaTexto;
import cenfotec.proyecto.utiles.Serializer;

public class JuegoAjedrez extends Juego {

    private static PartidaAjedrez partida = new PartidaAjedrez();
    private static int contador = partida.getContador();
    private static Scanner in = new Scanner(System.in);

    public JuegoAjedrez(String jugador1, String jugador2, String ganador, String perdedor) {
        super(jugador1, jugador2, ganador, perdedor);
    }

    public static void iniciarPartida() {

        System.out.println("Inician las piezas Negras.");
        partida = new PartidaAjedrez();
        String ganador;

        contador = 2;
        boolean breaker = false;
        while (!breaker) {
            ganador = EvaluarGanador();
            if (ganador.contentEquals("Ninguno")) {
                ImprimirEstadoJuego();
                switch (lecturaOpcionMenu()) {
                    case "1":
                        if (contador % 2 == 0) {
                            System.out.println("Mueven las piezas negras.");
                            moverPieza("N");
                        } else {
                            System.out.println("Mueven las piezas blancas.");
                            moverPieza("B");
                        }
                        breaker = false;
                        break;
                    case "2":
                        try {
                            guardarPartida();
                        } catch (Exception e) {
                            System.out.println("Ha ocurrido un problema al guardar la partida.");
                        }
                        breaker = false;
                        break;
                    case "3":
                        breaker = true;
                        break;
                    default:
                        System.out.println("Opcion no valida.");
                        breaker = false;
                        break;

                }
            } else {
                ImprimirEstadoJuego();
                System.out.println("Gana el rey " + ganador);
                System.out.println("            GAME OVER");
                breaker = true;
            }
        }

    }

    public static void continuarPartida() {
        System.out.println("El contador esta en: " + contador);
        String ganador = "";
        boolean breaker = false;

        while (breaker == false) {
            ganador = EvaluarGanador();
            if (ganador.contentEquals("Ninguno")) {
                ImprimirEstadoJuego();
                switch (lecturaOpcionMenu()) {
                    case "1":
                        if (contador % 2 == 0) {
                            System.out.println("Mueven las piezas negras.");
                            moverPieza("N");
                        } else {
                            System.out.println("Mueven las piezas blancas.");
                            moverPieza("B");
                        }
                        breaker = false;
                        break;
                    case "2":
                        try {
                            guardarPartida();
                        } catch (Exception e) {
                            System.out.println("Ha ocurrido un problema al guardar la partida.");
                        }
                        breaker = false;
                        break;
                    case "3":
                        breaker = true;
                        break;
                    default:
                        System.out.println("Opcion no valida.");
                        breaker = false;
                        break;
                }
            } else {
                ImprimirEstadoJuego();
                System.out.println("Gana el rey " + ganador);
                System.out.println("            GAME OVER");
                breaker = true;
            }
        }
    }

    public static void imprimirOpcionesJuego() {
        System.out.println("");
        System.out.println("1.Mover Pieza.");
        System.out.println("2.Guardar partida.");
        System.out.println("3.Salir.");

    }

    public static String lecturaOpcionMenu() {
        imprimirOpcionesJuego();
        String temp = "";
        temp = in.nextLine();
        if (temp.equals("1") || temp.equals("2") || temp.equals("3")) {

        } else {
            temp = "Opcion invalida, repita.";
        }
        return temp;
    }

    public static void moverPieza(String color) {
        String coordenadaInicial;
        String coordenadaFinal;
        String piezaRetorno = "";

        System.out.println("Ingrese la coordenada donde la pieza se encuentra ubicada:");
        coordenadaInicial = in.nextLine();
        System.out.println("Ingrese la coordenada final:");
        coordenadaFinal = in.nextLine();
        if (verificarPosicion(coordenadaInicial) == true
                && verificarPosicion(coordenadaFinal) == true) {
            piezaRetorno = retornarObjetoEnPosicion(coordenadaInicial);
            if (color.charAt(0) == piezaRetorno.charAt(1)) {
                colocarPieza(coordenadaInicial, coordenadaFinal, color);

            } else {
                System.out.println(
                        "No puedes mover la pieza en la posicion " + coordenadaInicial + " porque no te pertenece.");
            }

        } else {
            System.out.println("Coordenadas incorrectas.");
        }

    }

    public static boolean verificarPosicion(String posicionInicial) {
        boolean checker = verificarPosicionTablero(posicionInicial);
        if (posicionInicial.equals("") || posicionInicial.length() > 2) {
            checker = false;
        }
        return checker;
    }

    public static boolean verificarPosicionTablero(String x) {
        Pattern pattern = Pattern.compile("{2}^[abcdefgh][12345678]$");
        Matcher matcher = pattern.matcher(x);
        return matcher.matches();
    }

    private static void colocarPieza(String coordenadaInicial, String coordenadaFinal, String color) {
        Pieza temp = null;
        // Validar que el movimiento sea valido segun la pieza.

        Pieza piezaTemp = retornarPiezaPosicion(coordenadaInicial);
        boolean checker = validarMovimiento(piezaTemp, coordenadaInicial, coordenadaFinal);

        if (checker && !verificarPiezasDiferenteEquipo(coordenadaInicial, coordenadaFinal)) {
            // Remover pieza de posicion inicial.
            for (int i = 0; i < 8; i++) {
                for (int e = 0; e < 8; e++) {
                    if (coordenadaInicial.equals(partida.tablero[i][e])) {
                        temp = partida.tableroPosiciones[i][e];
                        partida.tableroPosiciones[i][e] = null;
                        //partida.tableroPosiciones[i][e] = new PiezaAjedrez("--", "*", "*", "*");
                    }
                }
            }

            // Recolocacion de la pieza.
            for (int i = 0; i < 8; i++) {
                for (int e = 0; e < 8; e++) {
                    if (coordenadaFinal.equals(partida.tablero[i][e])) {
                        partida.tableroPosiciones[i][e] = temp;
                        //partida.tableroPosiciones[i][e].sumarMovimiento();
                    }
                }
            }
            contador++;
            partida.sumarcontador();
        } else if (checker == false) {
            System.out.println("No puedes realizar ese movimiento, vuelvelo a intentar.");
        }

    }

    public static boolean verificarPiezasDiferenteEquipo(String inicial, String ultimo) {
        boolean checker = false;
        String valorDeInicial = "";
        String valorDeUltimo = "";

        for (int i = 0; i < 8; i++) {
            for (int e = 0; e < 8; e++) {
                if (inicial.equals(partida.tablero[i][e])) {
                    valorDeInicial = partida.tableroPosiciones[i][e].getNombre();
                }
                if (ultimo.equals(partida.tablero[i][e])) {
                    valorDeUltimo = partida.tableroPosiciones[i][e].getNombre();
                }
            }
        }

        if (valorDeUltimo.charAt(1) == valorDeInicial.charAt(1)) {
            checker = true;
        } else {
            checker = false;
        }

        return checker;

    }

    public static boolean validarMovimiento(Pieza pieza, String coordenadaInicial, String coordenadaFinal) {
        String codigo = pieza.getNombre().charAt(0) + "";
        boolean checker = true;
        switch (codigo) {
            case "G":// Peon
                checker = movimientoPeon(coordenadaInicial, coordenadaFinal, pieza);
                break;

            case "R":// Torre
                checker = movimientoTorre(coordenadaInicial, coordenadaFinal);
                break;

            case "K":// Rey
                checker = movimientoRey(coordenadaInicial, coordenadaFinal);
                break;

            case "Q":// Reina
                checker = movimientoReina(coordenadaInicial, coordenadaFinal);
                break;

            case "N":// Caballo
                checker = movimientoCaballo(coordenadaInicial, coordenadaFinal);
                break;

            case "B":// ALfil
                checker = movimientoAlfil(coordenadaInicial, coordenadaFinal);
                break;

        }

        return checker;

    }

    public static String retornarObjetoEnPosicion(String posicion) {
        String retorno = "";
        for (int i = 0; i < 8; i++) {
            for (int e = 0; e < 8; e++) {
                if (posicion.equals(partida.tablero[i][e])) {
                    retorno = partida.tableroPosiciones[i][e].getNombre();
                }
            }
        }

        return retorno;
    }

    public static Pieza[][] retornarTablerojuego() {
        return partida.getTableroPosiciones();
    }

    public static boolean movimientoPeon(String posicionInicial, String posicionFinal, Pieza peon) {
        boolean checker = false;

        if (posicionInicial.contentEquals(posicionFinal)) {
            System.out.println("Las coordenadas no pueden coincidir.");
        } else {
            if (contador % 2 == 0) {// Si pieza es negra.

                if (posicionInicial.charAt(0) == posicionFinal.charAt(0)) {// Cuando el peon es nuevo y quiere avanzar
                    // una
                    // sola posicion al frente.
                    if (Character.getNumericValue(posicionInicial.charAt(1)) + 1 == Character
                            .getNumericValue(posicionFinal.charAt(1))) {
                        if (retornarPiezaPosicion(posicionFinal).getNombre().equals("--")) {
                            checker = true;
                        }
                    }
                    if (Character.getNumericValue(posicionInicial.charAt(1)) + 2 == Character
                            .getNumericValue(posicionFinal.charAt(1)) && peon.getCantidadMovimientos() == 0) {
                        if (retornarPiezaPosicion(posicionFinal).getNombre().equals("--")) {
                            checker = true;
                        }
                    }

                } else if (posicionFinal.charAt(0) == retornarSiguienteColumna(posicionInicial.charAt(0) + "")
                        .charAt(0)) {
                    // Cuando se quiere comer una posicion
                    if (Character.getNumericValue(posicionInicial.charAt(1)) + 1 == Character
                            .getNumericValue(posicionFinal.charAt(1))) {
                        if (retornarPiezaPosicion(posicionFinal).getNombre().equals("--")) {

                        } else {
                            checker = true;
                        }
                    }
                } else if (posicionFinal.charAt(0) == retornarAnteriorColumna(posicionInicial.charAt(0) + "")
                        .charAt(0)) {
                    // Cuando se quiere comer una posicion
                    if (Character.getNumericValue(posicionInicial.charAt(1)) + 1 == Character
                            .getNumericValue(posicionFinal.charAt(1))) {
                        if (retornarPiezaPosicion(posicionFinal).getNombre().equals("--")) {

                        } else {
                            checker = true;
                        }
                    }
                }

            } else if (contador % 2 != 0) {// Si pieza es blanca.

                if (posicionInicial.charAt(0) == posicionFinal.charAt(0)) {// Cuando el peon es nuevo y quiere avanzar
                    // una
                    // sola posicion al frente.
                    if (Character.getNumericValue(posicionInicial.charAt(1)) - 1 == Character
                            .getNumericValue(posicionFinal.charAt(1))) {
                        if (retornarPiezaPosicion(posicionFinal).getNombre().equals("--")) {
                            checker = true;
                        }
                    }
                    if (Character.getNumericValue(posicionInicial.charAt(1)) - 2 == Character
                            .getNumericValue(posicionFinal.charAt(1)) && peon.getCantidadMovimientos() == 0) {
                        if (retornarPiezaPosicion(posicionFinal).getNombre().equals("--")) {
                            checker = true;
                        }
                    }

                } else if (posicionFinal.charAt(0) == retornarSiguienteColumna(posicionInicial.charAt(0) + "")
                        .charAt(0)) {
//					Cuando se quiere comer una posicion
                    if (Character.getNumericValue(posicionInicial.charAt(1)) - 1 == Character
                            .getNumericValue(posicionFinal.charAt(1))) {
                        if (retornarPiezaPosicion(posicionFinal).getNombre().equals("--")) {

                        } else {
                            checker = true;
                        }
                    }
                } else if (posicionFinal.charAt(0) == retornarAnteriorColumna(posicionInicial.charAt(0) + "")
                        .charAt(0)) {
                    // Cuando se quiere comer una posicion
                    if (Character.getNumericValue(posicionInicial.charAt(1)) - 1 == Character
                            .getNumericValue(posicionFinal.charAt(1))) {
                        if (retornarPiezaPosicion(posicionFinal).getNombre().equals("--")) {

                        } else {
                            checker = true;
                        }
                    }
                }

            }
        }

        return checker;
    }

    public static boolean movimientoRey(String posicionInicial, String posicionFinal) {
        boolean checker = false;

        if (posicionFinal.contentEquals(
                posicionInicial.charAt(0) + "" + Character.getNumericValue(posicionInicial.charAt(1) + 1))
                || posicionFinal.contentEquals(
                posicionInicial.charAt(0) + "" + Character.getNumericValue(posicionInicial.charAt(1) - 1))) {
            // Cuando el movimiento es en la misma columna.
            checker = true;
        } else if (posicionFinal
                .contentEquals(retornarSiguienteColumna(posicionInicial.charAt(0) + "") + posicionInicial.charAt(1))
                || posicionFinal.contentEquals(
                retornarAnteriorColumna(posicionInicial.charAt(0) + "") + posicionInicial.charAt(1))) {
            // Cuando el movimiento es en la misma fila.
            checker = true;
        } else if (posicionFinal
                .contentEquals(retornarSiguienteColumna(posicionInicial.charAt(0) + "")
                        + Character.getNumericValue(posicionInicial.charAt(1) + 1))
                || posicionFinal.contentEquals(retornarAnteriorColumna(posicionInicial.charAt(0) + "")
                + Character.getNumericValue(posicionInicial.charAt(1) + 1))) {
            // Cuando el movimiento es diagonal arriba.
            // Trabajando en este if.
            checker = true;
        } else if (posicionFinal
                .contentEquals(retornarSiguienteColumna(posicionInicial.charAt(0) + "")
                        + Character.getNumericValue(posicionInicial.charAt(1) - 1))
                || posicionFinal.contentEquals(retornarAnteriorColumna(posicionInicial.charAt(0) + "")
                + Character.getNumericValue(posicionInicial.charAt(1) - 1))) {
            // Cuando el movimiento es diagonal atras.
            checker = true;
        }

        return checker;
    }

    public static boolean movimientoReina(String posicionInicial, String posicionFinal) {
        boolean checker = false;
        boolean found = false;

        if (posicionInicial.contentEquals(posicionFinal)) {
            checker = false;
        } else {
            // Evaluar movimiento en diagonales.
            found = calcularPiezasEnMedioDiagonal(posicionInicial, posicionFinal);

            if (found == false) {
                // Evaluar movimiento al frente y hacia atras.
                found = movimientoLineal(posicionInicial, posicionFinal);
            }

            if (found == false) {
                // Evaluar movimiento horizontal.
                found = movimientoHorizontal(posicionInicial, posicionFinal);
            }

            // Ultima evaluacion.
            if (found == false) {
                checker = false;
            } else {
                checker = true;
            }
        }

        return checker;
    }

    public static boolean movimientoCaballo(String posicionInicial, String posicionFinal) {
        boolean checker = false;

        String PosicionArribaDerechaUnaAlfrente = retornarSiguienteColumna(
                retornarSiguienteColumna(posicionInicial.charAt(0) + ""))
                + (Character.getNumericValue(posicionInicial.charAt(1)) + 1);
        String PosicionArribaIzquierdaUnaAlfrente = retornarAnteriorColumna(
                retornarAnteriorColumna(posicionInicial.charAt(0) + ""))
                + (Character.getNumericValue(posicionInicial.charAt(1)) + 1);
        String posicionAbajoDerechaUnaAtras = retornarSiguienteColumna(
                retornarSiguienteColumna(posicionInicial.charAt(0) + ""))
                + (Character.getNumericValue(posicionInicial.charAt(1)) - 1);
        String posicionAbajoIzquierdaUnaAtras = retornarAnteriorColumna(
                retornarAnteriorColumna(posicionInicial.charAt(0) + ""))
                + (Character.getNumericValue(posicionInicial.charAt(1)) - 1);
        String PosicionArribaDerechaTresAlfrente = retornarSiguienteColumna(posicionInicial.charAt(0) + "")
                + (Character.getNumericValue(posicionInicial.charAt(1)) + 2);
        String PosicionArribaIzquierdaTresAlfrente = retornarAnteriorColumna(posicionInicial.charAt(0) + "")
                + (Character.getNumericValue(posicionInicial.charAt(1)) + 2);
        String posicionAbajoDerechaTresAtras = retornarSiguienteColumna(posicionInicial.charAt(0) + "")
                + (Character.getNumericValue(posicionInicial.charAt(1)) - 2);
        String posicionAbajoIzquierdaTresAtras = retornarAnteriorColumna(posicionInicial.charAt(0) + "")
                + (Character.getNumericValue(posicionInicial.charAt(1)) - 2);

        if (posicionFinal.contentEquals(PosicionArribaDerechaUnaAlfrente)) {
            checker = true;
        } else if (posicionFinal.contentEquals(PosicionArribaIzquierdaUnaAlfrente)) {
            checker = true;
        } else if (posicionFinal.contentEquals(posicionAbajoDerechaUnaAtras)) {
            checker = true;
        } else if (posicionFinal.contentEquals(posicionAbajoIzquierdaUnaAtras)) {
            checker = true;
        } else if (posicionFinal.contentEquals(PosicionArribaDerechaTresAlfrente)) {
            checker = true;
        } else if (posicionFinal.contentEquals(PosicionArribaIzquierdaTresAlfrente)) {
            checker = true;
        } else if (posicionFinal.contentEquals(posicionAbajoDerechaTresAtras)) {
            checker = true;
        } else if (posicionFinal.contentEquals(posicionAbajoIzquierdaTresAtras)) {
            checker = true;
        } else {
            checker = false;
        }

        return checker;
    }

    public static boolean movimientoTorre(String posicionInicial, String posicionFinal) {

        boolean checker = false;
        boolean found = false;

        if (posicionInicial.contentEquals(posicionFinal)) {
            checker = false;
        } else {
            // Evaluar movimiento en lineal.
            found = movimientoLineal(posicionInicial, posicionFinal);

            if (found == false) {
                // Evaluar movimiento horizontal.
                found = movimientoHorizontal(posicionInicial, posicionFinal);
            }

            // Ultima evaluacion.
            if (found == false) {
                checker = false;
            } else {
                checker = true;
            }
        }

        return checker;
    }

    public static boolean movimientoAlfil(String posicionInicial, String posicionFinal) {
        boolean checker = calcularPiezasEnMedioDiagonal(posicionInicial, posicionFinal);

        return checker;
    }

    public static boolean movimientoHorizontal(String inicial, String Final) {
        boolean checker = true;
        boolean piezasEnMedio = false;
        String posicionActual = inicial;
        Pieza piezaTemp = null;
        String lado = "";

        if (Character.getNumericValue(inicial.charAt(1)) == Character.getNumericValue(Final.charAt(1))) {

            lado = determinarDireccionHorizontal(inicial, Final);

            if (lado.contentEquals("izquierda")) {
                Final = retornarAnteriorColumna(Character.toString(Final.charAt(0))) + Final.charAt(1);
                while (!posicionActual.contentEquals(Final)) {
                    posicionActual = retornarSiguienteColumna(Character.toString(posicionActual.charAt(0)))
                            + Character.toString(inicial.charAt(1));
                    piezaTemp = retornarPiezaPosicion(posicionActual);
                    if (piezaTemp.getNombre().contentEquals("--")) {

                    } else {
                        piezasEnMedio = true;
                    }
                }

            } else if (lado.contentEquals("derecha")) {
                Final = retornarSiguienteColumna(Character.toString(Final.charAt(0))) + Final.charAt(1);
                while (!posicionActual.contentEquals(Final)) {
                    posicionActual = retornarAnteriorColumna(Character.toString(posicionActual.charAt(0)))
                            + Character.toString(inicial.charAt(1));
                    piezaTemp = retornarPiezaPosicion(posicionActual);
                    if (piezaTemp.getNombre().contentEquals("--")) {

                    } else {
                        piezasEnMedio = true;
                    }
                }

            } else {
                checker = false;
            }

            // Evaluacion Final.
            if (piezasEnMedio == true) {
                checker = false;
            }

        } else {
            checker = false;
        }

        return checker;
    }

    public static boolean movimientoLineal(String inicial, String Final) {
        boolean checker = true;
        boolean piezasEnMedio = false;
        String columna = Character.toString(inicial.charAt(0));
        Pieza piezaTemp = null;
        int contador = 0;
        int posInicial = Character.getNumericValue(inicial.charAt(1));
        int posFinal = Character.getNumericValue(Final.charAt(1));

        if (inicial.charAt(0) == Final.charAt(0)) {

            if (posInicial < posFinal) {
                posInicial = posInicial + 1;
                for (int i = posInicial; i < posFinal; i++) {

                    piezaTemp = retornarPiezaPosicion(columna + i);
                    if (piezaTemp.getNombre().contentEquals("--")) {

                    } else {
                        piezasEnMedio = true;
                    }

                    if (contador > 9) {
                        i = posFinal;
                    }
                }

            } else if (posInicial > posFinal) {
                posInicial = posInicial - 1;
                for (int i = posInicial; i > posFinal; i--) {

                    piezaTemp = retornarPiezaPosicion(columna + i);
                    if (piezaTemp.getNombre().contentEquals("--")) {

                    } else {
                        piezasEnMedio = true;
                    }

                    if (contador > 9) {
                        i = posFinal;
                    }
                }
            } else {
                checker = false;
            }

        } else {
            checker = false;
        }

        if (piezasEnMedio == true) {
            checker = false;
        }

        return checker;

    }

    public static boolean calcularPiezasEnMedioDiagonal(String inicial, String Final) {
        boolean checker = false;
        boolean breaker = false;
        boolean piezasEnMedio = false;
        boolean sideFound = false;
        int siguiente = 0;
        String posicionTemporal = inicial;
        Pieza piezaTemp = null;

        // Seccion de arriba derecha
        while (breaker == false) {
            if (posicionTemporal.contentEquals(Final)) {
                breaker = true;
                sideFound = true;
            } else {
                if (retornarSiguienteColumna(posicionTemporal.charAt(0) + "").contentEquals("NO")) {
                    checker = false;
                    breaker = true;
                    piezasEnMedio = false;
                } else {
                    siguiente = Character.getNumericValue(posicionTemporal.charAt(1)) + 1;
                    posicionTemporal = retornarSiguienteColumna(posicionTemporal.charAt(0) + "") + siguiente;
                    piezaTemp = retornarPiezaPosicion(posicionTemporal);
                    if (piezaTemp.getNombre().contentEquals("--")) {

                    } else {
                        if (retornarPiezaPosicion(Final).getNombre().contentEquals(piezaTemp.getNombre())) {

                        } else {
                            piezasEnMedio = true;
                            breaker = true;
                        }
                    }
                }
            }
        }

        // Seccion de abajo derecha.
        if (sideFound == false) {

            checker = false;
            breaker = false;
            piezasEnMedio = false;
            sideFound = false;
            posicionTemporal = inicial;

            while (breaker == false) {
                if (posicionTemporal.contentEquals(Final)) {
                    breaker = true;
                    sideFound = true;
                } else {
                    if (retornarSiguienteColumna(posicionTemporal.charAt(0) + "").contentEquals("NO")) {
                        checker = false;
                        breaker = true;
                        piezasEnMedio = false;
                    } else {
                        siguiente = Character.getNumericValue(posicionTemporal.charAt(1)) - 1;
                        posicionTemporal = retornarSiguienteColumna(posicionTemporal.charAt(0) + "") + siguiente;
                        piezaTemp = retornarPiezaPosicion(posicionTemporal);
                        if (piezaTemp.getNombre().contentEquals("--")) {

                        } else {
                            if (retornarPiezaPosicion(Final).getNombre().contentEquals(piezaTemp.getNombre())) {

                            } else {
                                piezasEnMedio = true;
                                breaker = true;
                            }
                        }
                    }
                }
            }
        }

        posicionTemporal = inicial;

        // Seccion de arriba izquierda
        if (sideFound == false) {

            checker = false;
            breaker = false;
            piezasEnMedio = false;
            sideFound = false;
            posicionTemporal = inicial;

            while (breaker == false) {
                if (posicionTemporal.contentEquals(Final)) {
                    breaker = true;
                    sideFound = true;
                } else {
                    if (retornarAnteriorColumna(posicionTemporal.charAt(0) + "").contentEquals("NO")) {
                        checker = false;
                        breaker = true;
                        piezasEnMedio = false;
                    } else {
                        siguiente = Character.getNumericValue(posicionTemporal.charAt(1)) + 1;
                        posicionTemporal = retornarAnteriorColumna(posicionTemporal.charAt(0) + "") + siguiente;
                        piezaTemp = retornarPiezaPosicion(posicionTemporal);
                        if (piezaTemp.getNombre().contentEquals("--")) {

                        } else {
                            if (retornarPiezaPosicion(Final).getNombre().contentEquals(piezaTemp.getNombre())) {

                            } else {
                                piezasEnMedio = true;
                                breaker = true;
                            }
                        }
                    }
                }
            }
        }

        posicionTemporal = inicial;

        // seccion de abajo izquierda.
        if (sideFound == false) {

            checker = false;
            breaker = false;
            piezasEnMedio = false;
            sideFound = false;
            posicionTemporal = inicial;

            while (breaker == false) {
                if (posicionTemporal.contentEquals(Final)) {
                    breaker = true;
                    sideFound = true;
                } else {
                    if (retornarAnteriorColumna(posicionTemporal.charAt(0) + "").contentEquals("NO")) {
                        checker = false;
                        breaker = true;
                        piezasEnMedio = false;
                    } else {
                        siguiente = Character.getNumericValue(posicionTemporal.charAt(1)) - 1;
                        posicionTemporal = retornarAnteriorColumna(posicionTemporal.charAt(0) + "") + siguiente;
                        piezaTemp = retornarPiezaPosicion(posicionTemporal);
                        if (piezaTemp.getNombre().contentEquals("--")) {

                        } else {
                            if (retornarPiezaPosicion(Final).getNombre().contentEquals(piezaTemp.getNombre())) {

                            } else {
                                piezasEnMedio = true;
                                breaker = true;
                            }
                        }
                    }
                }
            }
        }

        // evaluacion final.
        if (piezasEnMedio == true || sideFound == false) {
            checker = false;
        } else {
            checker = true;
        }

        return checker;
    }

    public static String determinarDireccionHorizontal(String posicionInicial, String posicionFinal) {
        String lado = "";
        int primero = 0;
        int ultimo = 0;
        int fila = Character.getNumericValue(posicionInicial.charAt(1) - 1);
        for (int i = 0; i < 8; i++) {
            if (posicionInicial.equals(partida.tablero[fila][i]) && ultimo != 1) {
                primero = 1;
            }
            if (posicionFinal.equals(partida.tablero[fila][i]) && primero != 1) {
                ultimo = 1;
            }
        }

        if (primero > ultimo) {
            lado = "izquierda";
        } else if (primero < ultimo) {
            lado = "derecha";
        }

        return lado;
    }

    public static Pieza retornarPiezaPosicion(String posicionInicial) {

        Pieza piezaTemp = null;

        for (int i = 0; i < 8; i++) {
            for (int e = 0; e < 8; e++) {
                if (posicionInicial.equals(partida.tablero[i][e])) {
                    piezaTemp = partida.tableroPosiciones[i][e];
                }
            }
        }

        return piezaTemp;

    }

    public static void ImprimirEstadoJuego() {
        System.out.println("|-------------------------------|   |-------------------------------|");
        System.out.println("|          Coordenadas          |   |         Partida actual        |");
        System.out.println("|-------------------------------|   |-------------------------------|");
        System.out.println();

        for (int i = 0; i < 8; i++) {
            System.out.print("     ");
            for (int e = 0; e < 8; e++) {
                System.out.print(partida.tablero[i][e] + " ");
            }
            System.out.print("            ");
            for (int e = 0; e < 8; e++) {
                System.out.print(partida.tableroPosiciones[i][e].getNombre() + " ");
            }
            System.out.println();
        }
        // Impresion de los Simbolos.
        try {
            imprimirTableroLogos();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public static void imprimirTableroLogos() throws UnsupportedEncodingException {

        System.out.println();
        System.out.println("                  |-------------------------------|");
        System.out.println("                  |             Juego             |");
        System.out.println("                  |-------------------------------|");
        System.out.println();

        for (int i = 0; i < 8; i++) {
            System.out.print("                                                                         ");
            for (int e = 0; e < 8; e++) {
                System.out.print(retornarLogo(partida.tableroPosiciones[i][e].getNombre()) + " ");
            }
            System.out.println();
        }

    }

    public static String EvaluarGanador() {

        String ganador = "Ninguno";
        boolean checkerNegro = false;
        boolean checkerBlanco = false;
        Pieza temp = null;

        for (int i = 0; i < 8; i++) {
            for (int e = 0; e < 8; e++) {
                if(partida.tableroPosiciones[i][e] != null){
                    if (partida.tableroPosiciones[i][e].getNombre().contentEquals("KB")) {
                        checkerBlanco = true;
                    } else if (partida.tableroPosiciones[i][e].getNombre().contentEquals("KN")) {
                        checkerNegro = true;
                    }
                }

            }
        }

        if (checkerBlanco && checkerNegro) {

        } else if (checkerBlanco && !checkerNegro) {
            ganador = "jugador2";
        } else if (!checkerBlanco && checkerNegro) {
            ganador = "jugador1";
        }

        return ganador;
    }

    public static String retornarLogo(String pieza) {

        String unicodeMessage = pieza;

        switch (unicodeMessage) {
            case "GB":
                unicodeMessage = "\u265F";
                break;
            case "RB":
                unicodeMessage = "\u265C";
                break;
            case "NB":
                unicodeMessage = "\u265E";
                break;
            case "BB":
                unicodeMessage = "\u265D";
                break;
            case "KB":
                unicodeMessage = "\u265A";
                break;
            case "QB":
                unicodeMessage = "\u265B";
                break;
            case "GN":
                unicodeMessage = "\u2659";
                break;
            case "RN":
                unicodeMessage = "\u2656";
                break;
            case "NN":
                unicodeMessage = "\u2658";
                break;
            case "BN":
                unicodeMessage = "\u2657";
                break;
            case "QN":
                unicodeMessage = "\u2655";
                break;
            case "KN":
                unicodeMessage = "\u2654";
                break;
            case "--":
                unicodeMessage = "\u058E";
                break;

        }
        return unicodeMessage;

    }

    public static void guardarPartida() throws FileNotFoundException {

        String json = Serializer.convertirPartidaJSON(1);
        String nombrePartida = "";
        if (json.equals("Default")) {
            System.out.println("Upps, no se ha logrado convertir la partida en formato JSON.");
        } else {
            System.out.println("Ingrese el nombre de la partida:");
            nombrePartida = in.nextLine();
            PersistenciaTexto.guardarArchivo(nombrePartida, json);
            System.out.println("Partida guardada en la siguiente direccion: C:\\Users\\Public\\Documents\\"
                    + nombrePartida + ".txt");
        }
    }

    public static boolean cargarPartidaArchivoTexto(String tipo) throws IOException {

        boolean checker = false;

        Tablero temp = PersistenciaTexto.compararJSONAjedrez(partida, tipo);
        if (temp != null) {
            partida = (PartidaAjedrez) temp;
            contador = partida.getContador();
            checker = true;
        } else {
            checker = false;
        }

        return checker;
    }

    public static String retornarSiguienteColumna(String columnaActual) {

        switch (columnaActual) {
            case "a":
                return "b";
            case "b":
                return "c";
            case "c":
                return "d";
            case "d":
                return "e";
            case "e":
                return "f";
            case "f":
                return "g";
            case "g":
                return "h";
            case "h":
                return "NO";
            default:
                return "NO";
        }
    }

    public static String retornarAnteriorColumna(String columnaActual) {

        switch (columnaActual) {
            case "a":
                return "NO";
            case "b":
                return "a";
            case "c":
                return "b";
            case "d":
                return "c";
            case "e":
                return "d";
            case "f":
                return "e";
            case "g":
                return "f";
            case "h":
                return "g";
            default:
                return "NO";
        }
    }

    // Metodos Get y Set de la clase.

    public static PartidaAjedrez getPartida() {
        return partida;
    }

    public static void setPartida(PartidaAjedrez partida) {
        JuegoAjedrez.partida = partida;
    }

}

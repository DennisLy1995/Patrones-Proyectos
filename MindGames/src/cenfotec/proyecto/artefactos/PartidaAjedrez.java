package cenfotec.proyecto.artefactos;

import cenfotec.proyecto.artefactos.Fabricas.Fabrica;
import cenfotec.proyecto.artefactos.Fabricas.FabricaPiezas;
import cenfotec.proyecto.artefactos.Fabricas.FabricasTypes;
import cenfotec.proyecto.artefactos.Piezas.Pieza;
import cenfotec.proyecto.artefactos.Piezas.PiezasTypes;

import static cenfotec.proyecto.artefactos.Piezas.PiezasTypes.*;

public class PartidaAjedrez extends Tablero {

    private int contador = 2;

    public Pieza[][] tableroPosiciones;
    private Fabrica fabricaPiezas;

    public PartidaAjedrez() {

        this.tipoJuego = "Ajedrez";
        this.tablero = new String[8][8];
        tableroPosiciones = new Pieza[8][8];
        fabricaPiezas = new FabricaPiezas().getFabrica(FabricasTypes.TYPE_AJEDREZ);
        String letra;
        //Se agregan las coordenadas del tablero.
        for (int i = 0; i < 8; i++) {
            letra = PartidaAjedrez.retornarLetraCordenada(i);
            for (int e = 0; e < 8; e++) {
                tablero[e][i] = letra + (e + 1);
            }

        }
        //Se agregan las piezas y sus respectivas posiciones iniciales.
        for (int i = 0; i < 8; i++) {
            for (int e = 2; e < 6; e++) {
                tableroPosiciones[e][i] = null;
                //tableroPosiciones[e][i] = new PiezaAjedrez("--", "*", "*", "*");
            }
        }
        //Ciclos for para agregar los peones
        for (int i = 0; i < 8; i++) {
            Pieza peon = fabricaPiezas.getPieza(TYPE_PEON);
            peon.setAtributos( "Jugador1", "GN", "Negro");
           // PiezaAjedrez peon = new PiezaAjedrez("GN", "Jugador1", "peon", "Negro");
            tableroPosiciones[1][i] = peon;
        }
        for (int i = 0; i < 8; i++) {
            Pieza peon = fabricaPiezas.getPieza(TYPE_PEON);
            peon.setAtributos("Jugador2", "GB", "Blanco");
            //PiezaAjedrez peon = new PiezaAjedrez("GB", "Jugador2", "peon", "Blanco");
            tableroPosiciones[6][i] = peon;
        }
        //Se agregan las piezas principales del juego
        tableroPosiciones[0][0] = retonarPiezaSegunPosicion(0, TYPE_TORRE);
        tableroPosiciones[0][1] = retonarPiezaSegunPosicion(0, TYPE_CABALLO);
        tableroPosiciones[0][2] = retonarPiezaSegunPosicion(0, TYPE_ALFIL);
        tableroPosiciones[0][3] = retonarPiezaSegunPosicion(0, TYPE_REY);
        tableroPosiciones[0][4] = retonarPiezaSegunPosicion(0, TYPE_REINA);
        tableroPosiciones[0][5] = retonarPiezaSegunPosicion(0, TYPE_ALFIL);
        tableroPosiciones[0][6] = retonarPiezaSegunPosicion(0, TYPE_CABALLO);
        tableroPosiciones[0][7] = retonarPiezaSegunPosicion(0, TYPE_TORRE);

        tableroPosiciones[7][0] = retonarPiezaSegunPosicion(7, TYPE_TORRE);
        tableroPosiciones[7][1] = retonarPiezaSegunPosicion(7, TYPE_CABALLO);
        tableroPosiciones[7][2] = retonarPiezaSegunPosicion(7, TYPE_ALFIL);
        tableroPosiciones[7][3] = retonarPiezaSegunPosicion(7, TYPE_REY);
        tableroPosiciones[7][4] = retonarPiezaSegunPosicion(7, TYPE_REINA);
        tableroPosiciones[7][5] = retonarPiezaSegunPosicion(7, TYPE_ALFIL);
        tableroPosiciones[7][6] = retonarPiezaSegunPosicion(7, TYPE_CABALLO);
        tableroPosiciones[7][7] = retonarPiezaSegunPosicion(7, TYPE_TORRE);

    }

    public static String retornarLetraCordenada(int columna) {

        String letra = "";
        switch (columna) {

            case 0:
                letra = "a";
                break;
            case 1:
                letra = "b";
                break;
            case 2:
                letra = "c";
                break;
            case 3:
                letra = "d";
                break;
            case 4:
                letra = "e";
                break;
            case 5:
                letra = "f";
                break;
            case 6:
                letra = "g";
                break;
            case 7:
                letra = "h";
                break;
        }
        return letra;
    }

    public Pieza retonarPiezaSegunPosicion(int fila, PiezasTypes tipoPieza) {

        String color;
        String tipoColor;
        String jugador;
        String nombre;

        if (fila == 0) {
            color = "negro";
            tipoColor = "N";
            jugador = "Jugador 1";
        } else {
            color = "blanco";
            tipoColor = "B";
            jugador = "Jugador 2";
        }

        switch (tipoPieza) {
            //revisar con el profesor, porque el caballo esta mal nombrado, en ingles la letra asociada es N y para el rey es K.
            case TYPE_REY:
                nombre = "K" + tipoColor;
                break;
            case TYPE_REINA:
                nombre = "Q" + tipoColor;
                break;
            case TYPE_CABALLO:
                nombre = "N" + tipoColor;
                break;
            case TYPE_ALFIL:
                nombre = "B" + tipoColor;
                break;
            case TYPE_TORRE:
                nombre = "R" + tipoColor;
                break;
            default:
                throw new RuntimeException("Se recibió un tipo de pieza incorrecto (nombre switch).");

        }

        //PiezaAjedrez piezaRetorno = new PiezaAjedrez(nombre, jugador, pieza, color);
        Pieza tempPieza = fabricaPiezas.getPieza(tipoPieza);
        tempPieza.setAtributos(nombre, jugador, color);
        return tempPieza;
    }

    public Pieza[][] getTableroPosiciones() {
        return tableroPosiciones;
    }

    public void setTableroPosiciones(Pieza[][] tableroPosiciones) {
        this.tableroPosiciones = tableroPosiciones;
    }


    public int getContador() {
        return contador;
    }


    public void setContador(int contador) {
        this.contador = contador;
    }

    public void resetearContador() {
        contador = 2;
    }

    public void sumarcontador() {
        contador++;
    }

    public void restarcontador() {
        contador--;
    }
}

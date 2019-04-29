package cenfotec.proyecto.artefactos.Fabricas;

import cenfotec.proyecto.artefactos.Piezas.*;

public class FabricaPiezasAjedrez extends Fabrica{
    public Pieza getPieza(PiezasTypes type) {
        switch (type) {
            case TYPE_REY:
                return new Rey();
            case TYPE_PEON:
                return new Peon();
            case TYPE_ALFIL:
                return new Alfil();
            case TYPE_REINA:
                return new Reina();
            case TYPE_TORRE:
                return new Torre();
            case TYPE_CABALLO:
                return new Caballo();
            case TYPE_DEFAULT:
            	return new PiezaAjedrezVacia();
            default:
                throw new RuntimeException("El tipo de pieza ingresado no se encontra.");
        }
    }


}

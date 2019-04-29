package cenfotec.proyecto.artefactos.Fabricas;

import cenfotec.proyecto.artefactos.Piezas.PeonDamas;
import cenfotec.proyecto.artefactos.Piezas.Pieza;
import cenfotec.proyecto.artefactos.Piezas.PiezaDamasVacia;
import cenfotec.proyecto.artefactos.Piezas.PiezasTypes;
import cenfotec.proyecto.artefactos.Piezas.ReinaDamas;


public class FabricaPiezasDamas extends Fabrica {
    @Override
    public Pieza getPieza(PiezasTypes type) {
    	switch (type) {
        case TYPE_PEON:
            return new PeonDamas();
        case TYPE_REINA:
            return new ReinaDamas();
        case TYPE_DEFAULT:
        	return new PiezaDamasVacia();
        default:
            throw new RuntimeException("El tipo de pieza ingresado no se encontra.");
    }
    }
}

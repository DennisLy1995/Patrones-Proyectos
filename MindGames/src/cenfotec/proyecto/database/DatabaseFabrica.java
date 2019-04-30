package cenfotec.proyecto.database;

import cenfotec.proyecto.artefactos.Fabricas.Fabrica;
import cenfotec.proyecto.artefactos.Piezas.Pieza;
import cenfotec.proyecto.artefactos.Piezas.PiezasTypes;

public class DatabaseFabrica extends Fabrica {
    @Override
    public Pieza getPieza(PiezasTypes type) {
        throw new UnsupportedOperationException("This function is not implemented.");
    }
}

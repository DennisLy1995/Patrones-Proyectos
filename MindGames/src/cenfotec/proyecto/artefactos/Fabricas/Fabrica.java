package cenfotec.proyecto.artefactos.Fabricas;

import cenfotec.proyecto.artefactos.Piezas.Pieza;
import cenfotec.proyecto.artefactos.Piezas.PiezasTypes;

public abstract class Fabrica {
    public abstract Pieza getPieza(PiezasTypes type);
}

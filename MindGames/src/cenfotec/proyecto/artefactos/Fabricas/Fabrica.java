package cenfotec.proyecto.artefactos.Fabricas;

import cenfotec.proyecto.artefactos.Piezas.Pieza;
import cenfotec.proyecto.artefactos.Piezas.PiezasTypes;
import cenfotec.proyecto.database.DatabaseConnector;
import cenfotec.proyecto.database.DatabaseTypes;

public abstract class Fabrica {
    public abstract Pieza getPieza(PiezasTypes type);
    public abstract DatabaseConnector getDatabaseConnector(DatabaseTypes type):
}

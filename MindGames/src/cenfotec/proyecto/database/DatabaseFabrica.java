package cenfotec.proyecto.database;

import cenfotec.proyecto.artefactos.Fabricas.Fabrica;
import cenfotec.proyecto.artefactos.Piezas.Pieza;
import cenfotec.proyecto.artefactos.Piezas.PiezasTypes;

public class DatabaseFabrica extends Fabrica {
    @Override
    public Pieza getPieza(PiezasTypes type) {
        throw new UnsupportedOperationException("This function is not implemented.");
    }

    @Override
    public DatabaseConnector getDatabaseConnector(DatabaseTypes type) {
        switch (type){
            case MYSQL:
                throw new UnsupportedOperationException("MySQL has not been implemented yet.");
            case SQL_SERVER:
                return new SqlServerConnector();
                default:
                    return null;
        }
    }
}

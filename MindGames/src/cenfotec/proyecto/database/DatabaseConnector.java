package cenfotec.proyecto.database;

public abstract class DatabaseConnector {
    public abstract DatabaseConnector getInstance();
    public abstract String Retrieve(String QUERY);
    public abstract boolean Insert(String QUERY);

}

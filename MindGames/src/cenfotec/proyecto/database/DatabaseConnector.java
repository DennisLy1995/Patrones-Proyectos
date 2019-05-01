package cenfotec.proyecto.database;

public abstract class DatabaseConnector {
    public abstract DatabaseConnector getInstance();
    public abstract void setCurrentConnectionData(String connectionData);
    public abstract String Retrieve(String QUERY);
    public abstract boolean Insert(String QUERY);

}

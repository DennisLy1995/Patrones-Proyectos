package cenfotec.proyecto.database;

public class SqlServerConnector extends  DatabaseConnector {
    private DatabaseConnector currentInstance = new SqlServerConnector();
    @Override
    public DatabaseConnector getInstance() {
        return currentInstance;
    }

    @Override
    public void setCurrentConnectionData(String connectionData) {

    }

    @Override
    public String Retrieve(String QUERY) {
        return null;
    }

    @Override
    public boolean Insert(String QUERY) {
        return false;
    }
}

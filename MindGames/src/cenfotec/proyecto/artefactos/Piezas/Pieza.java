package cenfotec.proyecto.artefactos.Piezas;

public abstract class Pieza {

    protected String jugador;
    protected String nombre;
    protected String color;
    private int cantidadMovimientos;
    public Pieza(){
        this.jugador = "";
        this.nombre = "";
        this.color = "";
        cantidadMovimientos = 0;
    }
    public Pieza(String jugador, String nombre, String color) {
        this.jugador = jugador;
        this.nombre = nombre;
        this.color = color;
        cantidadMovimientos = 0;
    }

    public String getColor() {
        return this.color;
    }

    public abstract String retornarMovimiento();

    public String getNombre() {
        return this.nombre;
    }

    public String getJugador() {
        return this.jugador;
    }

    public void setJugador(String tempJugador) {
        this.jugador = tempJugador;
    }

    public int getCantidadMovimientos() {
        return this.cantidadMovimientos;
    }
}

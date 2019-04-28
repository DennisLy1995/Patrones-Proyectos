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
    public void setAtributos(String NOMBRE_PIEZA, String NOMBRE_JUGADOR, String NOMBRE_COLOR){
        this.jugador = NOMBRE_JUGADOR;
        this.nombre = NOMBRE_PIEZA;
        this.color = NOMBRE_COLOR;
    }

    public String getColor() {
        return this.color;
    }

    public abstract String retornarMovimiento();

    public String getNombre() {
        if(this.nombre == null){
            this.nombre = "";
        }
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

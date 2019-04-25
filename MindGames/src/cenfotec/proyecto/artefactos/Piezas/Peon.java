package cenfotec.proyecto.artefactos.Piezas;

public class Peon extends Pieza{
    public Peon(String jugador) {
        super(jugador);
    }

    @Override
    public String retornarMovimiento() {
        return "frente/consume diagnonal";
    }

    @Override
    public String getNombre() {
        return "Peón";
    }
}

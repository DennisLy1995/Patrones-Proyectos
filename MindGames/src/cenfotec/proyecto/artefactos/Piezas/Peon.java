package cenfotec.proyecto.artefactos.Piezas;

public class Peon extends Pieza{
    public Peon() {
        super();
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

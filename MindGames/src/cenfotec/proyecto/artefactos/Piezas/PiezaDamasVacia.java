package cenfotec.proyecto.artefactos.Piezas;

public class PiezaDamasVacia extends Pieza{
    public PiezaDamasVacia() {
        super();
    }

    @Override
    public String retornarMovimiento() {
        return "frente/consume diagnonal";
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }
}

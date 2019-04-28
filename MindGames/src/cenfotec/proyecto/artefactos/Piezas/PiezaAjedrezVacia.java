package cenfotec.proyecto.artefactos.Piezas;

public class PiezaAjedrezVacia extends Pieza{

	public PiezaAjedrezVacia() {
		super();
	}

    @Override
    public String retornarMovimiento() {
        return null;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

}

package cenfotec.proyecto.artefactos.Fabricas;

public class FabricaPiezas {
    public Fabrica getFabrica(FabricasTypes type) {
        switch (type) {
            case TYPE_DAMAS:
                return new FabricaPiezasDamas();
            case TYPE_AJEDREZ:
                return new FabricaPiezasAjedrez();
            default:
                throw new RuntimeException("Tipo de fabrica no encontrado. (switch)");
        }
    }
}

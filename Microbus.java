package sistemagestion;


public class Microbus extends Vehiculo {

    private static final int CAPACIDAD = 12;
    private static final int TARIFA    = 6000;

    /**
     * @param placa     
     * @param ruta      
     * @param disponible 
     */
    public Microbus(String placa, Ruta ruta, boolean disponible) {
        super(placa, ruta, disponible);
        this.capacidadmaxima = CAPACIDAD;
        this.tarifa1         = TARIFA;
        guardarEnArchivo();
    }
}


package sistemagestion;

public class Bus extends Vehiculo {

    private static final int CAPACIDAD = 45;
    private static final int TARIFA    = 12000;

    /**
     * @param placa   
     * @param ruta       
     * @param disponible 
     */
    public Bus(String placa, Ruta ruta, boolean disponible) {
        super(placa, ruta, disponible);
        this.capacidadmaxima = CAPACIDAD;
        this.tarifa1         = TARIFA;
        guardarEnArchivo();
    }
}


package sistemagestion;


public class Bus extends Vehiculo {

    private static final int CAPACIDAD = 45;
    private static final int TARIFA    = 12000;

    /**
     * @param placa      Placa del vehículo.
     * @param ruta       Objeto {@link Ruta} obtenido del catálogo (no puede ser null).
     * @param disponible Disponibilidad del vehículo.
     */
    public Bus(String placa, Ruta ruta, boolean disponible) {
        super(placa, ruta, disponible);
        this.capacidadmaxima = CAPACIDAD;
        this.tarifa1         = TARIFA;
        guardarEnArchivo();
    }
}
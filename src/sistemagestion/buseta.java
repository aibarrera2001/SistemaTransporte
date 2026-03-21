/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestion;

/**
 *
 * @author USUARIO
 */

public class buseta extends Vehiculo {

    private static final int CAPACIDAD = 19;
    private static final int TARIFA = 8000;

    public buseta(String placa, Ruta ruta, boolean disponible) {
        super(placa, ruta, disponible);
        this.capacidadmaxima = CAPACIDAD;
        this.tarifa1 = TARIFA;
        guardarEnArchivo();
    }

    
}

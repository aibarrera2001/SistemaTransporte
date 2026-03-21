/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Andrés
 */


public class PasajeroRegular extends Pasajero {

    public PasajeroRegular(String cedula, String nombre) {
        super(cedula, nombre);
    }

    @Override public double calcularDescuento() { return 0.0; }
    @Override public String getTipo()           { return "Regular"; }

    @Override
    public void imprimirDetalle() {
        System.out.println("│ Regular      | " + cedula + " | " + nombre + " | Descuento: 0%");
    }
}

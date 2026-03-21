/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Andrés
 */


public abstract class Pasajero extends Persona {

    public Pasajero(String cedula, String nombre) {
        super(cedula, nombre);
    }

    public abstract double calcularDescuento();
    public abstract String getTipo();
}
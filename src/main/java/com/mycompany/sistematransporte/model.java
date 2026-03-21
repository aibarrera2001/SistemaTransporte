/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistematransporte;

/**
 *
 * @author jose
 */
public class model {
    
    public interface Imprimible {
    void imprimirDetalle();
}
    
    public interface Calculable {
    double calcularTotal();
}
   

public abstract class Persona implements Imprimible {

    protected String cedula;
    protected String nombre;

    public Persona(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }
}
}

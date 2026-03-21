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

public class Conductor extends Persona {

    private String licencia;
    private String categoria;

    public Conductor(String cedula, String nombre, String licencia, String categoria) {
        super(cedula, nombre);
        this.licencia = licencia;
        this.categoria = categoria;
    }

    public boolean tieneLicencia() {
        return licencia != null && !licencia.isEmpty();
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("Conductor: " + nombre + " - Licencia: " + licencia);
    }
}

public abstract class Pasajero extends Persona {

    public Pasajero(String cedula, String nombre) {
        super(cedula, nombre);
    }

    public abstract double calcularDescuento();
}

public class PasajeroEstudiante extends Pasajero {

    public PasajeroEstudiante(String cedula, String nombre) {
        super(cedula, nombre);
    }

    @Override
    public double calcularDescuento() {
        return 0.15;
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("Estudiante: " + nombre);
    }
}

public class PasajeroAdultoMayor extends Pasajero {

    public PasajeroAdultoMayor(String cedula, String nombre) {
        super(cedula, nombre);
    }

    @Override
    public double calcularDescuento() {
        return 0.30;
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("Adulto Mayor: " + nombre);
    }
}

public class PasajeroRegular extends Pasajero {

    public PasajeroRegular(String cedula, String nombre) {
        super(cedula, nombre);
    }

    @Override
    public double calcularDescuento() {
        return 0.0;
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("Regular: " + nombre);
    }
}
}

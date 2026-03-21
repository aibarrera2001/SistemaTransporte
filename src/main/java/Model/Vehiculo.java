/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Andrés
 */


public abstract class Vehiculo implements Imprimible {

    protected String placa;
    protected String ruta;
    protected int capacidadMaxima;
    protected int pasajerosActuales;
    protected boolean disponible;

    public Vehiculo(String placa, String ruta) {
        this.placa = placa;
        this.ruta = ruta;
        this.pasajerosActuales = 0;
        this.disponible = true;
    }

    public abstract double getTarifaBase();

    public boolean tieneCupos() {
        return pasajerosActuales < capacidadMaxima;
    }

    public void agregarPasajero() {
        if (tieneCupos()) pasajerosActuales++;
    }

    public int getCuposDisponibles() {
        return capacidadMaxima - pasajerosActuales;
    }

    public String getPlaca()            { return placa; }
    public String getRuta()             { return ruta; }
    public int getCapacidadMaxima()     { return capacidadMaxima; }
    public int getPasajerosActuales()   { return pasajerosActuales; }
    public boolean isDisponible()       { return disponible; }

    public void setDisponible(boolean disponible)   { this.disponible = disponible; }
    public void setPasajerosActuales(int pasajeros) { this.pasajerosActuales = pasajeros; }
}
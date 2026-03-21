/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Andrés
 */


public class Bus extends Vehiculo {

    public Bus(String placa, String ruta) {
        super(placa, ruta);
        this.capacidadMaxima = 45;
    }

    @Override
    public double getTarifaBase() { return 15000; }

    @Override
    public void imprimirDetalle() {
        System.out.println("┌─ BUS ────────────────────────────────────");
        System.out.println("│ Placa    : " + placa);
        System.out.println("│ Ruta     : " + ruta);
        System.out.println("│ Capacidad: " + capacidadMaxima);
        System.out.println("│ Cupos    : " + getCuposDisponibles());
        System.out.println("│ Tarifa   : $" + (int) getTarifaBase());
        System.out.println("│ Estado   : " + (disponible ? "Disponible" : "No disponible"));
        System.out.println("└──────────────────────────────────────────");
    }
}
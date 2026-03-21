/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Andrés
 */


public class Buseta extends Vehiculo {

    public Buseta(String placa, String ruta) {
        super(placa, ruta);
        this.capacidadMaxima = 19;
    }

    @Override
    public double getTarifaBase() { return 8000; }

    @Override
    public void imprimirDetalle() {
        System.out.println("┌─ BUSETA ─────────────────────────────────");
        System.out.println("│ Placa    : " + placa);
        System.out.println("│ Ruta     : " + ruta);
        System.out.println("│ Capacidad: " + capacidadMaxima);
        System.out.println("│ Cupos    : " + getCuposDisponibles());
        System.out.println("│ Tarifa   : $" + (int) getTarifaBase());
        System.out.println("│ Estado   : " + (disponible ? "Disponible" : "No disponible"));
        System.out.println("└──────────────────────────────────────────");
    }
}
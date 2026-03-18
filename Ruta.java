/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestion;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una ruta del sistema de transporte.
 * Almacena rutas registradas en memoria para asignarlas a vehículos.
 */
public class Ruta {

    private String codigoRuta;
    private String ciudadOrigen;
    private String ciudadDestino;
    private double distanciaKm;
    private int tiempoEstimadoMin;

    /** toma las lista de rutas disponibles en el main. */
    private static final List<Ruta> rutasRegistradas = new ArrayList<>();

    // ── Constructor 
    public Ruta(String codigoRuta, String ciudadOrigen, String ciudadDestino,
                double distanciaKm, int tiempoEstimadoMin) {
        this.codigoRuta        = codigoRuta;
        this.ciudadOrigen      = ciudadOrigen;
        this.ciudadDestino     = ciudadDestino;
        this.distanciaKm       = distanciaKm;
        this.tiempoEstimadoMin = tiempoEstimadoMin;
    }

    // ── Gestión del catálogo de rutas 

    /**
     * @param ruta
     * @return 
     */
    public static boolean registrarRuta(Ruta ruta) {
        for (Ruta r : rutasRegistradas) {
            if (r.codigoRuta.equalsIgnoreCase(ruta.codigoRuta)) {
                System.out.println("Ya existe una ruta con el código: " + ruta.codigoRuta);
                return false;
            }
        }
        rutasRegistradas.add(ruta);
        System.out.println("Ruta registrada: " + ruta.codigoRuta);
        return true;
    }

    /**
     * @param codigo Código de ruta a buscar.
     * @return La ruta encontrada, o null si no existe.
     */
    public static Ruta buscarPorCodigo(String codigo) {
        for (Ruta r : rutasRegistradas) {
            if (r.codigoRuta.equalsIgnoreCase(codigo)) {
                return r;
            }
        }
        return null;
    }

    /**
     * @return Lista de rutas disponibles.
     */
    public static List<Ruta> getRutasRegistradas() {
        return new ArrayList<>(rutasRegistradas);
    }

    /**
     * Lista en consola todas las rutas registradas.
     */
    public static void listarRutas() {
        if (rutasRegistradas.isEmpty()) {
            System.out.println("No hay rutas registradas.");
            return;
        }
        System.out.println("=== Rutas disponibles ===");
        for (Ruta r : rutasRegistradas) {
            System.out.println(r);
        }
    }

    // ── Getters y Setters

    public String getCodigoRuta() { return codigoRuta; }
    public void setCodigoRuta(String codigoRuta) { this.codigoRuta = codigoRuta; }

    public String getCiudadOrigen() { return ciudadOrigen; }
    public void setCiudadOrigen(String ciudadOrigen) { this.ciudadOrigen = ciudadOrigen; }

    public String getCiudadDestino() { return ciudadDestino; }
    public void setCiudadDestino(String ciudadDestino) { this.ciudadDestino = ciudadDestino; }

    public double getDistanciaKm() { return distanciaKm; }
    public void setDistanciaKm(double distanciaKm) { this.distanciaKm = distanciaKm; }

    public int getTiempoEstimadoMin() { return tiempoEstimadoMin; }
    public void setTiempoEstimadoMin(int tiempoEstimadoMin) { this.tiempoEstimadoMin = tiempoEstimadoMin; }

    @Override
    public String toString() {
        return String.format("[%s] %s → %s | %.1f km | %d min",
                codigoRuta, ciudadOrigen, ciudadDestino, distanciaKm, tiempoEstimadoMin);
    }
}
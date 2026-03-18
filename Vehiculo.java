package sistemagestion;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase base que representa un vehículo del sistema de transporte.
 * La ruta ya no se escribe manualmente: se asigna un objeto {@link Ruta}
 * previamente registrado en el catálogo.
 */
public class Vehiculo {

    // ── Atributos ────────────────────────────────────────────────────────────
    protected int    capacidadmaxima;
    protected int    tarifa1;
    private   String placa;
    private   Ruta   ruta;          // ← ahora es un objeto Ruta, no un String
    private   boolean disponible;

    // ── Constructor ──────────────────────────────────────────────────────────
    public Vehiculo(String placa, Ruta ruta, boolean disponible) {
        if (ruta == null) {
            throw new IllegalArgumentException(
                "La ruta no puede ser null. Registre la ruta antes de crear el vehículo.");
        }
        this.placa      = placa;
        this.ruta       = ruta;
        this.disponible = disponible;
    }

    // ── Persistencia ─────────────────────────────────────────────────────────
    protected void guardarEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("vehiculos.txt", true))) {
            bw.write(toString());
            bw.newLine();
            System.out.println(getClass().getSimpleName() + " registrado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    // ── Getters y Setters ────────────────────────────────────────────────────
    public int getCapacidad() { return capacidadmaxima; }
    public void setCapacidad(int capacidad) { this.capacidadmaxima = capacidad; }

    public int getTarifa() { return tarifa1; }
    public void setTarifa(int tarifa) { this.tarifa1 = tarifa; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public Ruta getRuta() { return ruta; }

    /**
     * Reasigna la ruta del vehículo.
     * @param ruta
     */
    public void setRuta(Ruta ruta) {
        if (ruta == null) {
            throw new IllegalArgumentException("La ruta no puede ser null.");
        }
        this.ruta = ruta;
    }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    // ── toString ─────────────────────────────────────────────────────────────
    @Override
    public String toString() {
        return placa + ","
             + capacidadmaxima + ","
             + tarifa1 + ","
             + ruta.getCodigoRuta() + ","          // guarda el código de la ruta
             + ruta.getCiudadOrigen() + "-"
             + ruta.getCiudadDestino() + ","
             + disponible + ",";
    }
}
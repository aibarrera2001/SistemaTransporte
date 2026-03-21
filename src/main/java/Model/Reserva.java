package main.java.Model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase que representa una Reserva en el sistema.
 * Implementa Serializable para permitir la persistencia de objetos.
 */
public class Reserva implements Serializable {
    
    // Identificador único para la serialización
    private static final long serialVersionUID = 1L;

    private String codigo;
    private String pasajero;
    private String vehiculo;
    private LocalDate fechaCreacion;
    private LocalDate fechaViaje;
    private EstadoReserva estado;

    // Constructor vacío (Recomendado para frameworks y serialización)
    public Reserva() {}

    // Constructor con parámetros
    public Reserva(String codigo, String pasajero, String vehiculo, 
                   LocalDate fechaCreacion, LocalDate fechaViaje, EstadoReserva estado) {
        this.codigo = codigo;
        this.pasajero = pasajero;
        this.vehiculo = vehiculo;
        this.fechaCreacion = fechaCreacion;
        this.fechaViaje = fechaViaje;
        this.estado = estado;
    }

    // --- Getters y Setters ---
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getPasajero() { return pasajero; }
    public void setPasajero(String pasajero) { this.pasajero = pasajero; }

    public String getVehiculo() { return vehiculo; }
    public void setVehiculo(String vehiculo) { this.vehiculo = vehiculo; }

    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public LocalDate getFechaViaje() { return fechaViaje; }
    public void setFechaViaje(LocalDate fechaViaje) { this.fechaViaje = fechaViaje; }

    public EstadoReserva getEstado() { return estado; }
    public void setEstado(EstadoReserva estado) { this.estado = estado; }

    @Override
    public String toString() {
        return codigo + "|" + pasajero + "|" + vehiculo + "|" + 
               fechaCreacion + "|" + fechaViaje + "|" + estado;
    }
}
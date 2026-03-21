/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.Model;

/**
 *
 * @author USUARIO
 */

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase que representa una Reserva de transporte.
 * Implementa Serializable para compatibilidad con flujos de datos.
 */
public class Reserva implements Serializable {

    // Identificador de versión para la serialización
    private static final long serialVersionUID = 1L;

    // Atributos
    private String codigo;
    private String pasajero;
    private String vehiculo;
    private LocalDate fechaCreacion;
    private LocalDate fechaViaje;
    private EstadoReserva estado;

    /**
     * Constructor por defecto (necesario para algunas librerías de persistencia)
     */
    public Reserva() {
    }

    /**
     * Constructor completo
     * @param codigo Identificador único
     * @param pasajero Nombre del cliente
     * @param vehiculo Identificación del transporte
     * @param fechaCreacion Fecha en que se registró
     * @param fechaViaje Fecha programada del servicio
     * @param estado Estado actual (ACTIVA, CONVERTIDA, CANCELADA)
     */
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPasajero() {
        return pasajero;
    }

    public void setPasajero(String pasajero) {
        this.pasajero = pasajero;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaViaje() {
        return fechaViaje;
    }

    public void setFechaViaje(LocalDate fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    /**
     * Método toString optimizado para guardar en reservas.txt
     * Usa el separador '|' para evitar conflictos con comas en nombres.
     */
    @Override
    public String toString() {
        return codigo + "|" + 
               pasajero + "|" + 
               vehiculo + "|" + 
               fechaCreacion + "|" + 
               fechaViaje + "|" + 
               estado; // El enum se convierte automáticamente a String (ej: "ACTIVA")
    }
}


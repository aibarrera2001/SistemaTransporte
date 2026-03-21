package Model;

import java.time.LocalDate;

public class Reserva implements Imprimible {

    public enum EstadoReserva { ACTIVA, CONVERTIDA, CANCELADA }

    private String codigo;
    private Pasajero pasajero;
    private Vehiculo vehiculo;
    private LocalDate fechaCreacion;
    private LocalDate fechaViaje;
    private EstadoReserva estado;

    public Reserva(String codigo, Pasajero pasajero, Vehiculo vehiculo,
                   LocalDate fechaViaje) {
        this.codigo        = codigo;
        this.pasajero      = pasajero;
        this.vehiculo      = vehiculo;
        this.fechaCreacion = LocalDate.now();
        this.fechaViaje    = fechaViaje;
        this.estado        = EstadoReserva.ACTIVA;
    }

    public boolean estaVencida() {
        return estado == EstadoReserva.ACTIVA &&
               fechaCreacion.isBefore(LocalDate.now().minusDays(1));
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("┌─ RESERVA ────────────────────────────────");
        System.out.println("│ Código   : " + codigo);
        System.out.println("│ Pasajero : " + pasajero.getNombre());
        System.out.println("│ Vehículo : " + vehiculo.getPlaca());
        System.out.println("│ Creación : " + fechaCreacion);
        System.out.println("│ Viaje    : " + fechaViaje);
        System.out.println("│ Estado   : " + estado);
        System.out.println("└──────────────────────────────────────────");
    }

    public String getCodigo()          { return codigo; }
    public Pasajero getPasajero()      { return pasajero; }
    public Vehiculo getVehiculo()      { return vehiculo; }
    public LocalDate getFechaCreacion(){ return fechaCreacion; }
    public LocalDate getFechaViaje()   { return fechaViaje; }
    public EstadoReserva getEstado()   { return estado; }

    public void setEstado(EstadoReserva estado) { this.estado = estado; }
    public void setFechaCreacion(LocalDate f)   { this.fechaCreacion = f; }
}
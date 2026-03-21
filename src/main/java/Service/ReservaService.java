/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.Service;
import dao.ReservaDAO;
import model.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
/**
 *
 * @author jose
 */
public class ReservaService {

public class ReservaServicio {

    private ReservaDAO reservaDAO = new ReservaDAO();
    private TicketService ticketService = new TicketService();

    
    public Reserva crearReserva(Pasajero pasajero, Vehiculo vehiculo, LocalDate fechaViaje) {

        
        long reservasActivas = reservaDAO.listar().stream()
                .filter(r -> r.getVehiculo().equals(vehiculo))
                .filter(r -> r.getFechaViaje().equals(fechaViaje))
                .filter(r -> r.getEstado().equals("PENDIENTE"))
                .count();

        if (reservasActivas >= vehiculo.getCapacidad()) {
            System.out.println("No hay cupos disponibles para este vehículo.");
            return null;
        }

        
        boolean existe = reservaDAO.listar().stream()
                .anyMatch(r -> r.getPasajero().equals(pasajero)
                        && r.getFechaViaje().equals(fechaViaje)
                        && r.getEstado().equals("PENDIENTE"));

        if (existe) {
            System.out.println("El pasajero ya tiene una reserva activa para ese día.");
            return null;
        }

        Reserva reserva = new Reserva(
                UUID.randomUUID().toString(),
                pasajero,
                vehiculo,
                fechaViaje
        );

        
        reserva.setFechaCreacion(LocalDateTime.now());

        reservaDAO.guardar(reserva);

        return reserva;
    }

    
    public void cancelarReserva(String idReserva) {
        Reserva reserva = reservaDAO.buscarPorId(idReserva);

        if (reserva != null && reserva.getEstado().equals("PENDIENTE")) {
            reserva.cancelar();
            System.out.println("Reserva cancelada correctamente.");
        } else {
            System.out.println("No se pudo cancelar la reserva.");
        }
    }

    
    public void convertirEnTicket(String idReserva) {
        Reserva reserva = reservaDAO.buscarPorId(idReserva);

        if (reserva != null && reserva.getEstado().equals("PENDIENTE")) {

            reserva.confirmar();

            
            ticketService.venderTicket(
                    reserva.getPasajero(),
                    reserva.getVehiculo(),
                    reserva.getFechaViaje()
            );

            System.out.println("Reserva convertida en ticket.");
        } else {
            System.out.println("No se puede convertir la reserva.");
        }
    }

    
    public void verificarVencidas() {

        List<Reserva> reservas = reservaDAO.listar();

        for (Reserva r : reservas) {
            if (r.getEstado().equals("PENDIENTE")) {

                Duration tiempo = Duration.between(r.getFechaCreacion(), LocalDateTime.now());

                if (tiempo.toHours() >= 24) {
                    r.cancelar();
                    System.out.println("Reserva vencida cancelada: " + r.getIdReserva());
                }
            }
        }
    }

    
    public List<Reserva> listarActivas() {
        return reservaDAO.listar().stream()
                .filter(r -> r.getEstado().equals("PENDIENTE"))
                .collect(Collectors.toList());
    }

    
    public List<Reserva> historialPasajero(Pasajero pasajero) {
        return reservaDAO.listar().stream()
                .filter(r -> r.getPasajero().equals(pasajero))
                .collect(Collectors.toList());
    }
}
}

package Service;

import Dao.ReservaDAO;
import Model.*;
import Model.Reserva.EstadoReserva;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ReservaService {

    private List<Reserva> reservas;
    private final ReservaDAO dao;
    private final VehiculoService vehiculoService;
    private final PersonaService  personaService;
    private final TicketService   ticketService;

    public ReservaService(VehiculoService vs, PersonaService ps, TicketService ts) {
        this.vehiculoService = vs;
        this.personaService  = ps;
        this.ticketService   = ts;
        this.dao      = new ReservaDAO();
        this.reservas = dao.cargarTodos(ps.listarPasajeros(), vs.listarVehiculos());
        verificarVencidas();
    }

    public boolean crearReserva(String cedulaPasajero, String placaVehiculo, LocalDate fechaViaje) {
        Pasajero p = personaService.buscarPasajero(cedulaPasajero);
        if (p == null) { System.out.println("  Pasajero no encontrado."); return false; }

        Vehiculo v = vehiculoService.buscarPorPlaca(placaVehiculo);
        if (v == null) { System.out.println("  Vehiculo no encontrado."); return false; }

        // Validar cupos
        long reservasActivas = reservas.stream()
            .filter(r -> r.getVehiculo().getPlaca().equals(placaVehiculo)
                      && r.getEstado() == EstadoReserva.ACTIVA)
            .count();
        int ocupados = v.getPasajerosActuales() + (int) reservasActivas;
        if (ocupados >= v.getCapacidadMaxima()) {
            System.out.println("  No hay cupos disponibles para este vehiculo.");
            return false;
        }

        // Validar reserva duplicada
        boolean existe = reservas.stream()
            .anyMatch(r -> r.getPasajero().getCedula().equals(cedulaPasajero)
                       && r.getVehiculo().getPlaca().equals(placaVehiculo)
                       && r.getFechaViaje().equals(fechaViaje)
                       && r.getEstado() == EstadoReserva.ACTIVA);
        if (existe) {
            System.out.println("  El pasajero ya tiene una reserva activa para ese vehiculo en esa fecha.");
            return false;
        }

        String codigo = "RES-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Reserva res = new Reserva(codigo, p, v, fechaViaje);
        reservas.add(res);
        dao.guardar(res);
        System.out.println("  Reserva creada. Codigo: " + codigo);
        return true;
    }

    public boolean cancelarReserva(String codigo) {
        for (Reserva r : reservas) {
            if (r.getCodigo().equals(codigo) && r.getEstado() == EstadoReserva.ACTIVA) {
                r.setEstado(EstadoReserva.CANCELADA);
                dao.guardarTodos(reservas);
                System.out.println("  Reserva cancelada.");
                return true;
            }
        }
        System.out.println("  Reserva no encontrada o ya no esta activa.");
        return false;
    }

    public boolean convertirEnTicket(String codigo) {
        for (Reserva r : reservas) {
            if (r.getCodigo().equals(codigo) && r.getEstado() == EstadoReserva.ACTIVA) {
                // getRuta() devuelve objeto Ruta — usamos origen y destino del objeto
                String origen  = r.getVehiculo().getRuta() != null
                        ? r.getVehiculo().getRuta().getCiudadOrigen()
                        : "Sin origen";
                String destino = r.getVehiculo().getRuta() != null
                        ? r.getVehiculo().getRuta().getCiudadDestino()
                        : "Sin destino";

                boolean vendido = ticketService.venderTicket(
                    r.getPasajero().getCedula(),
                    r.getVehiculo().getPlaca(),
                    origen,
                    destino
                );
                if (vendido) {
                    r.setEstado(EstadoReserva.CONVERTIDA);
                    dao.guardarTodos(reservas);
                    System.out.println("  Reserva convertida en ticket.");
                    return true;
                }
            }
        }
        System.out.println("  No se pudo convertir la reserva.");
        return false;
    }

    public int verificarVencidas() {
        int canceladas = 0;
        for (Reserva r : reservas) {
            if (r.estaVencida()) {
                r.setEstado(EstadoReserva.CANCELADA);
                canceladas++;
            }
        }
        if (canceladas > 0) dao.guardarTodos(reservas);
        return canceladas;
    }

    public List<Reserva> listarActivas() {
        return reservas.stream()
            .filter(r -> r.getEstado() == EstadoReserva.ACTIVA)
            .collect(Collectors.toList());
    }

    public List<Reserva> historialPasajero(String cedula) {
        return reservas.stream()
            .filter(r -> r.getPasajero().getCedula().equals(cedula))
            .collect(Collectors.toList());
    }

    public List<Reserva> listarReservas() { return reservas; }
}
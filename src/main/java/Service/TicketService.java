/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;
import Dao.TicketDAO;
import Model.*;
import java.util.*;
/**
 *
 * @author Andrés
 */



public class TicketService implements Calculable {

    private List<Ticket> tickets;
    private final TicketDAO       dao;
    private final VehiculoService vehiculoService;
    private final PersonaService  personaService;

    public TicketService(VehiculoService vs, PersonaService ps) {
        this.vehiculoService = vs;
        this.personaService  = ps;
        this.dao     = new TicketDAO();
        this.tickets = dao.cargarTodos(ps.listarPasajeros(), vs.listarVehiculos());
    }

    public boolean venderTicket(String cedulaPasajero, String placaVehiculo,
                                String origen, String destino) {
        Pasajero p = personaService.buscarPasajero(cedulaPasajero);
        if (p == null) { System.out.println("⚠ Pasajero no encontrado."); return false; }
        Vehiculo v = vehiculoService.buscarPorPlaca(placaVehiculo);
        if (v == null) { System.out.println("⚠ Vehículo no encontrado."); return false; }
        if (!v.tieneCupos()) { System.out.println("⚠ El vehículo está lleno."); return false; }
        Ticket t = new Ticket(p, v, origen, destino);
        v.agregarPasajero();
        vehiculoService.guardarCambios();
        tickets.add(t);
        dao.guardar(t);
        System.out.println("✔ Ticket generado. Valor: $" + (int) t.getValorFinal());
        return true;
    }

    public List<Ticket> listarTickets() { return tickets; }

    @Override
    public double calcularTotal() {
        double total = 0;
        for (Ticket t : tickets) total += t.getValorFinal();
        return total;
    }

    public Map<String, Integer> pasajerosPorTipo() {
        Map<String, Integer> mapa = new LinkedHashMap<>();
        mapa.put("Regular",      0);
        mapa.put("Estudiante",   0);
        mapa.put("Adulto Mayor", 0);
        for (Ticket t : tickets) {
            String tipo = t.getPasajero().getTipo();
            mapa.put(tipo, mapa.getOrDefault(tipo, 0) + 1);
        }
        return mapa;
    }

    public String vehiculoConMasTickets() {
        Map<String, Integer> conteo = new HashMap<>();
        for (Ticket t : tickets) {
            String placa = t.getVehiculo().getPlaca();
            conteo.put(placa, conteo.getOrDefault(placa, 0) + 1);
        }
        String mejor = "Sin datos"; int max = 0;
        for (Map.Entry<String, Integer> e : conteo.entrySet())
            if (e.getValue() > max) { max = e.getValue(); mejor = e.getKey(); }
        return mejor;
    }
}
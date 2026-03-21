package Dao;

import Model.*;
import Model.Reserva.EstadoReserva;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    private final String FILE_NAME = "reservas.txt";

    public void guardar(Reserva r) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            pw.println(r.getCodigo() + ";"
                    + r.getPasajero().getCedula() + ";"
                    + r.getVehiculo().getPlaca() + ";"
                    + r.getFechaCreacion() + ";"
                    + r.getFechaViaje() + ";"
                    + r.getEstado());
        } catch (IOException e) {
            System.err.println("Error al guardar reserva: " + e.getMessage());
        }
    }

    public List<Reserva> cargarTodos(List<Pasajero> pasajeros, List<Vehiculo> vehiculos) {
        List<Reserva> lista = new ArrayList<>();
        File f = new File(FILE_NAME);
        if (!f.exists()) return lista;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split(";");
                Pasajero pasajero = null;
                for (Pasajero pa : pasajeros)
                    if (pa.getCedula().equals(p[1])) { pasajero = pa; break; }
                Vehiculo vehiculo = null;
                for (Vehiculo v : vehiculos)
                    if (v.getPlaca().equals(p[2])) { vehiculo = v; break; }
                if (pasajero != null && vehiculo != null) {
                    Reserva r = new Reserva(p[0], pasajero, vehiculo, LocalDate.parse(p[4]));
                    r.setFechaCreacion(LocalDate.parse(p[3]));
                    r.setEstado(EstadoReserva.valueOf(p[5]));
                    lista.add(r);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar reservas: " + e.getMessage());
        }
        return lista;
    }

    public void guardarTodos(List<Reserva> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME, false))) {
            for (Reserva r : lista) {
                pw.println(r.getCodigo() + ";"
                        + r.getPasajero().getCedula() + ";"
                        + r.getVehiculo().getPlaca() + ";"
                        + r.getFechaCreacion() + ";"
                        + r.getFechaViaje() + ";"
                        + r.getEstado());
            }
        } catch (IOException e) {
            System.err.println("Error al guardar reservas: " + e.getMessage());
        }
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrés
 */

public class TicketDAO {

    private static final String ARCHIVO = "tickets.txt";

    public void guardar(Ticket t) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO, true))) {
            pw.println(t.getPasajero().getCedula() + ";"
                    + t.getPasajero().getTipo() + ";"
                    + t.getPasajero().getNombre() + ";"
                    + t.getVehiculo().getPlaca() + ";"
                    + t.getVehiculo().getClass().getSimpleName() + ";"
                    + t.getOrigen() + ";"
                    + t.getDestino() + ";"
                    + t.getFechaCompra() + ";"
                    + t.getValorFinal());
        } catch (IOException e) {
            System.err.println("Error al guardar ticket: " + e.getMessage());
        }
    }

    public List<Ticket> cargarTodos(List<Pasajero> pasajeros, List<Vehiculo> vehiculos) {
        List<Ticket> lista = new ArrayList<>();
        File f = new File(ARCHIVO);
        if (!f.exists()) return lista;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split(";");
                Pasajero pasajero = null;
                for (Pasajero pa : pasajeros)
                    if (pa.getCedula().equals(p[0])) { pasajero = pa; break; }
                Vehiculo vehiculo = null;
                for (Vehiculo v : vehiculos)
                    if (v.getPlaca().equals(p[3])) { vehiculo = v; break; }
                if (pasajero != null && vehiculo != null) {
                    Ticket t = new Ticket(pasajero, vehiculo, p[5], p[6]);
                    t.setFechaCompra(LocalDate.parse(p[7]));
                    t.setValorFinal(Double.parseDouble(p[8]));
                    lista.add(t);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar tickets: " + e.getMessage());
        }
        return lista;
    }
}
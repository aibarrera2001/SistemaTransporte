/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Model.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Andrés
 */



public class VehiculoDAO {

    private static final String ARCHIVO = "vehiculos.txt";

    public void guardar(Vehiculo v) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO, true))) {
            pw.println(v.getClass().getSimpleName() + ";"
                    + v.getPlaca() + ";"
                    + v.getRuta() + ";"
                    + v.getPasajerosActuales() + ";"
                    + v.isDisponible());
        } catch (IOException e) {
            System.err.println("Error al guardar vehículo: " + e.getMessage());
        }
    }

    public List<Vehiculo> cargarTodos() {
        List<Vehiculo> lista = new ArrayList<>();
        File f = new File(ARCHIVO);
        if (!f.exists()) return lista;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split(";");
                Vehiculo v = null;
                switch (p[0]) {
                    case "Buseta":   v = new Buseta(p[1], p[2]);   break;
                    case "MicroBus": v = new MicroBus(p[1], p[2]); break;
                    case "Bus":      v = new Bus(p[1], p[2]);      break;
                }
                if (v != null) {
                    v.setPasajerosActuales(Integer.parseInt(p[3]));
                    v.setDisponible(Boolean.parseBoolean(p[4]));
                    lista.add(v);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar vehículos: " + e.getMessage());
        }
        return lista;
    }

    public void guardarTodos(List<Vehiculo> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO, false))) {
            for (Vehiculo v : lista) {
                pw.println(v.getClass().getSimpleName() + ";"
                        + v.getPlaca() + ";"
                        + v.getRuta() + ";"
                        + v.getPasajerosActuales() + ";"
                        + v.isDisponible());
            }
        } catch (IOException e) {
            System.err.println("Error al guardar vehículos: " + e.getMessage());
        }
    }
}

package Dao;

import Model.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO {

    private static final String ARCHIVO = "vehiculos.txt";

    public void guardar(Vehiculo v) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO, true))) {
            pw.println(toLinea(v));
        } catch (IOException e) {
            System.err.println("Error al guardar vehiculo: " + e.getMessage());
        }
    }

    public void guardarTodos(List<Vehiculo> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO, false))) {
            for (Vehiculo v : lista) pw.println(toLinea(v));
        } catch (IOException e) {
            System.err.println("Error al guardar vehiculos: " + e.getMessage());
        }
    }

    // Formato: Tipo;Placa;CodigoRuta;pasajeros;disponible;cedulaConductor
    private String toLinea(Vehiculo v) {
        return v.getClass().getSimpleName() + ";"
                + v.getPlaca() + ";"
                + (v.getRuta() != null ? v.getRuta().getCodigo() : "") + ";"
                + v.getPasajerosActuales() + ";"
                + v.isDisponible() + ";"
                + (v.getConductor() != null ? v.getConductor().getCedula() : "");
    }

    public List<Vehiculo> cargarTodos(List<Ruta> rutas, List<Conductor> conductores) {
        List<Vehiculo> lista = new ArrayList<>();
        File f = new File(ARCHIVO);
        if (!f.exists()) return lista;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split(";", -1);
                if (p.length < 5) continue;

                // Buscar ruta por código
                Ruta ruta = null;
                for (Ruta r : rutas)
                    if (r.getCodigo().equalsIgnoreCase(p[2])) { ruta = r; break; }

                Vehiculo v = null;
                switch (p[0]) {
                    case "Buseta":   v = new Buseta(p[1], ruta);   break;
                    case "MicroBus": v = new MicroBus(p[1], ruta); break;
                    case "Bus":      v = new Bus(p[1], ruta);      break;
                }
                if (v != null) {
                    v.setPasajerosActuales(Integer.parseInt(p[3]));
                    v.setDisponible(Boolean.parseBoolean(p[4]));
                    // Buscar conductor
                    if (p.length > 5 && !p[5].isBlank()) {
                        for (Conductor c : conductores)
                            if (c.getCedula().equals(p[5])) { v.setConductor(c); break; }
                    }
                    lista.add(v);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar vehiculos: " + e.getMessage());
        }
        return lista;
    }
}
package Dao;

import Model.Ruta;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RutaDAO {

    private static final String ARCHIVO = "rutas.txt";

    public void guardar(Ruta r) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO, true))) {
            pw.println(r.getCodigo() + ";"
                    + r.getCiudadOrigen() + ";"
                    + r.getCiudadDestino() + ";"
                    + r.getDistanciaKm() + ";"
                    + r.getTiempoMinutos());
        } catch (IOException e) {
            System.err.println("Error al guardar ruta: " + e.getMessage());
        }
    }

    public List<Ruta> cargarTodos() {
        List<Ruta> lista = new ArrayList<>();
        File f = new File(ARCHIVO);
        if (!f.exists()) return lista;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split(";");
                if (p.length >= 5) {
                    lista.add(new Ruta(p[0], p[1], p[2],
                            Double.parseDouble(p[3]),
                            Integer.parseInt(p[4])));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar rutas: " + e.getMessage());
        }
        return lista;
    }
}
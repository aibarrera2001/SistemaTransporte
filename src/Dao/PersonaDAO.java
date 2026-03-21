package Dao;

import Model.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {

    private static final String ARCHIVO_CONDUCTORES = "conductores.txt";
    private static final String ARCHIVO_PASAJEROS   = "pasajeros.txt";

    // ── CONDUCTORES ───────────────────────────────────────────
    public void guardarConductor(Conductor c) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_CONDUCTORES, true))) {
            pw.println(c.getCedula() + ";"
                    + c.getNombre() + ";"
                    + c.getNumeroLicencia() + ";"
                    + c.getCategoriaLicencia());
        } catch (IOException e) {
            System.err.println("Error al guardar conductor: " + e.getMessage());
        }
    }

    public List<Conductor> cargarConductores() {
        List<Conductor> lista = new ArrayList<>();
        File f = new File(ARCHIVO_CONDUCTORES);
        if (!f.exists()) return lista;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split(";", -1);
                if (p.length >= 4)
                    lista.add(new Conductor(p[0], p[1], p[2], p[3]));
            }
        } catch (IOException e) {
            System.err.println("Error al cargar conductores: " + e.getMessage());
        }
        return lista;
    }

    // ── PASAJEROS ─────────────────────────────────────────────
    // Formato: Tipo;cedula;nombre;fechaNacimiento
    public void guardarPasajero(Pasajero pas) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_PASAJEROS, true))) {
            pw.println(pas.getTipo() + ";"
                    + pas.getCedula() + ";"
                    + pas.getNombre() + ";"
                    + (pas.getFechaNacimiento() != null ? pas.getFechaNacimiento().toString() : ""));
        } catch (IOException e) {
            System.err.println("Error al guardar pasajero: " + e.getMessage());
        }
    }

    public List<Pasajero> cargarPasajeros() {
        List<Pasajero> lista = new ArrayList<>();
        File f = new File(ARCHIVO_PASAJEROS);
        if (!f.exists()) return lista;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split(";", -1);
                if (p.length < 3) continue;

                LocalDate fecha = null;
                if (p.length >= 4 && !p[3].isBlank()) {
                    try { fecha = LocalDate.parse(p[3]); } catch (Exception ignored) {}
                }
                if (fecha == null) fecha = LocalDate.of(1990, 1, 1);

                Pasajero pasajero = null;
                switch (p[0]) {
                    case "Regular":
                        pasajero = new PasajeroRegular(p[1], p[2], fecha);      break;
                    case "Estudiante":
                        pasajero = new PasajeroEstudiante(p[1], p[2], fecha);   break;
                    case "Adulto Mayor":
                        pasajero = new PasajeroAdultoMayor(p[1], p[2], fecha);  break;
                }
                if (pasajero != null) lista.add(pasajero);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar pasajeros: " + e.getMessage());
        }
        return lista;
    }
}
package main.java.Dao;

import main.java.Model.Reserva;
import main.java.Model.EstadoReserva;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    private final String FILE_NAME = "reservas.txt";

    public void guardar(Reserva reserva) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(reserva.toString());
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al guardar reserva: " + e.getMessage());
        }
    }

    public List<Reserva> cargarTodos() {
        List<Reserva> reservas = new ArrayList<>();
        File file = new File(FILE_NAME);
        
        if (!file.exists()) return reservas;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split("\\|");
                reservas.add(new Reserva(
                    p[0], p[1], p[2], 
                    LocalDate.parse(p[3]), 
                    LocalDate.parse(p[4]), 
                    EstadoReserva.valueOf(p[5])
                ));
            }
        } catch (IOException e) {
            System.err.println("Error al cargar reservas: " + e.getMessage());
        }
        return reservas;
    }

    public void guardarTodos(List<Reserva> reservas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Reserva r : reservas) {
                bw.write(r.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al sobrescribir archivo de reservas: " + e.getMessage());
        }
    }
}
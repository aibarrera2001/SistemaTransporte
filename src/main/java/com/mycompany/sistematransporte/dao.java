/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistematransporte;
import model.Pasajero;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author jose
 */
public class dao {

public class PasajeroDAO {

    public void guardar(Pasajero pasajero) {
        try (FileWriter fw = new FileWriter("pasajeros.txt", true)) {
            fw.write(pasajero.getCedula() + ";" + pasajero.getNombre() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class ConductorDAO {

    public void guardar(Conductor conductor) {
        try (FileWriter fw = new FileWriter("conductores.txt", true)) {
            fw.write(conductor.getCedula() + ";" + conductor.getNombre() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class TicketDAO {

    public void guardar(Ticket ticket) {
        try (FileWriter fw = new FileWriter("tickets.txt", true)) {
            fw.write(ticket.getPasajero().getNombre() + ";" +
                     ticket.getVehiculo().getTarifaBase() + ";" +
                     ticket.calcularTotal() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
}

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
}

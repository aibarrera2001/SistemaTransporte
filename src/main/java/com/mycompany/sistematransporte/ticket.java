/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistematransporte;
import dao.*;
import model.*;
/**
 *
 * @author jose
 */
public class ticket {

public class PersonaService {

    private PasajeroDAO pasajeroDAO = new PasajeroDAO();
    private ConductorDAO conductorDAO = new ConductorDAO();

    public void registrarPasajero(Pasajero pasajero) {
        pasajeroDAO.guardar(pasajero);
        System.out.println("✅ Pasajero registrado");
    }

    public void registrarConductor(Conductor conductor) {

        if (!conductor.tieneLicencia()) {
            System.out.println("❌ El conductor no tiene licencia");
            return;
        }

        conductorDAO.guardar(conductor);
        System.out.println("✅ Conductor registrado");
    }
}
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Dao.PersonaDAO;
import Model.*;
import java.util.List;

/**
 *
 * @author Andrés
 */

public class PersonaService {

    private List<Conductor> conductores;
    private List<Pasajero>  pasajeros;
    private final PersonaDAO dao = new PersonaDAO();

    public PersonaService() {
        this.conductores = dao.cargarConductores();
        this.pasajeros   = dao.cargarPasajeros();
    }

    public void registrarConductor(Conductor c) {
        conductores.add(c);
        dao.guardarConductor(c);
        System.out.println("✔ Conductor registrado.");
    }

    public void registrarPasajero(Pasajero p) {
        pasajeros.add(p);
        dao.guardarPasajero(p);
        System.out.println("✔ Pasajero registrado.");
    }

    public Conductor buscarConductor(String cedula) {
        for (Conductor c : conductores)
            if (c.getCedula().equals(cedula)) return c;
        return null;
    }

    public Pasajero buscarPasajero(String cedula) {
        for (Pasajero p : pasajeros)
            if (p.getCedula().equals(cedula)) return p;
        return null;
    }

    public List<Conductor> listarConductores() { return conductores; }
    public List<Pasajero>  listarPasajeros()   { return pasajeros; }
}
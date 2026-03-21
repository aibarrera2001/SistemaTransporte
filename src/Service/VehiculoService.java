/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Dao.VehiculoDAO;
import Model.*;
import java.util.List;

/**
 *
 * @author Andrés
 */


public class VehiculoService {

    private List<Vehiculo> vehiculos;
    private final VehiculoDAO dao = new VehiculoDAO();

    public VehiculoService() {
        this.vehiculos = dao.cargarTodos();
    }

    public boolean registrarVehiculo(Vehiculo v) {
        for (Vehiculo ex : vehiculos)
            if (ex.getPlaca().equalsIgnoreCase(v.getPlaca())) {
                System.out.println("⚠ Ya existe un vehículo con la placa " + v.getPlaca());
                return false;
            }
        vehiculos.add(v);
        dao.guardar(v);
        System.out.println("✔ Vehículo registrado correctamente.");
        return true;
    }

    public boolean asignarConductor(String placa, Conductor conductor) {
        if (!conductor.tieneLicencia()) {
            System.out.println("⚠ El conductor no tiene licencia registrada.");
            return false;
        }
        Vehiculo v = buscarPorPlaca(placa);
        if (v == null) {
            System.out.println("⚠ No se encontró vehículo con esa placa.");
            return false;
        }
        System.out.println("✔ Conductor " + conductor.getNombre() + " asignado a " + placa);
        return true;
    }

    public Vehiculo buscarPorPlaca(String placa) {
        for (Vehiculo v : vehiculos)
            if (v.getPlaca().equalsIgnoreCase(placa)) return v;
        return null;
    }

    public List<Vehiculo> listarVehiculos() { return vehiculos; }

    public void guardarCambios() { dao.guardarTodos(vehiculos); }
}
package Service;

import Dao.VehiculoDAO;
import Model.*;
import java.util.List;

public class VehiculoService {

    private List<Vehiculo>    vehiculos;
    private final VehiculoDAO dao = new VehiculoDAO();

    // Constructor actualizado: necesita rutas y conductores para reconstruir desde archivo
    public VehiculoService(List<Ruta> rutas, List<Conductor> conductores) {
        this.vehiculos = dao.cargarTodos(rutas, conductores);
    }

    public boolean registrarVehiculo(Vehiculo v) {
        for (Vehiculo ex : vehiculos)
            if (ex.getPlaca().equalsIgnoreCase(v.getPlaca())) {
                System.out.println("  Ya existe un vehiculo con la placa " + v.getPlaca());
                return false;
            }
        vehiculos.add(v);
        dao.guardar(v);
        System.out.println("  Vehiculo registrado correctamente.");
        return true;
    }

    public boolean asignarConductor(String placa, Conductor conductor) {
        if (!conductor.tieneLicencia()) {
            System.out.println("  El conductor no tiene licencia registrada.");
            return false;
        }
        Vehiculo v = buscarPorPlaca(placa);
        if (v == null) {
            System.out.println("  No se encontro vehiculo con esa placa.");
            return false;
        }
        v.setConductor(conductor);
        dao.guardarTodos(vehiculos);
        System.out.println("  Conductor " + conductor.getNombre() + " asignado a " + placa);
        return true;
    }

    public Vehiculo buscarPorPlaca(String placa) {
        for (Vehiculo v : vehiculos)
            if (v.getPlaca().equalsIgnoreCase(placa)) return v;
        return null;
    }

    public List<Vehiculo> listarVehiculos() { return vehiculos; }
    public void guardarCambios()            { dao.guardarTodos(vehiculos); }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import sistemagestion.Bus;
import sistemagestion.Microbus;
import sistemagestion.Ruta;
import sistemagestion.buseta;

/**
 *
 * @author USUARIO
 */

public class Principal {
 
    public static void main(String[] args) {
 
        // ──  Registrar rutas en el catálogo
        Ruta.registrarRuta(new Ruta("R001", "Bogota",      "Medellin",   414.0, 480));
        Ruta.registrarRuta(new Ruta("R002", "Bogota",      "Cali",       461.0, 540));
        Ruta.registrarRuta(new Ruta("R003", "Medellin",    "Cartagena",  640.0, 720));
        Ruta.registrarRuta(new Ruta("R004", "Valledupar",  "Barranquilla", 257.0, 210));
 
        System.out.println();
        Ruta.listarRutas();
        System.out.println();
 
        // ── Buscar ruta por código antes de crear el vehículo 
        Ruta rutaSeleccionada = Ruta.buscarPorCodigo("R001");
        Ruta rutaSeleccionada1 = Ruta.buscarPorCodigo("R002");
                
 
        if (rutaSeleccionada != null) {
            // ──  Crear vehículos asignando la ruta encontrada 
            Bus      bus      = new Bus     ("ABC-123", rutaSeleccionada, true);
            buseta   miniBus  = new buseta  ("XYZ-789", rutaSeleccionada1, false);
            Microbus microbus = new Microbus("DEF-456", rutaSeleccionada1, true);
 
            System.out.println();
            System.out.println("=== Vehiculos creados ===");
            System.out.println(bus);
            System.out.println(miniBus);
            System.out.println(microbus);
        } else {
            System.out.println("Ruta no encontrada. Verifique el código.");
        }
 
        // ── Intento de ruta inexistente
        System.out.println();
        Ruta rutaInexistente = Ruta.buscarPorCodigo("R999");
        if (rutaInexistente == null) {
            System.out.println("No se puede registrar el vehículo: la ruta R999 no existe.");
        }
    }
}
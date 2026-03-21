/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.*;
import Service.*;
import java.util.Scanner;
import java.util.Map;

public class Menu {

    private final Scanner        sc = new Scanner(System.in);
    private final VehiculoService vs;
    private final PersonaService  ps;
    private final TicketService   ts;

    public Menu() {
        this.vs = new VehiculoService();
        this.ps = new PersonaService();
        this.ts = new TicketService(vs, ps);
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerEntero("Selecciona una opción: ");
            switch (opcion) {
                case 1: menuVehiculos();    break;
                case 2: menuConductores();  break;
                case 3: menuPasajeros();    break;
                case 4: menuTickets();      break;
                case 5: menuEstadisticas(); break;
                case 6: menuReportes();     break;
                case 0: System.out.println("\n👋 Saliendo del sistema TransCesar..."); break;
                default: System.out.println("⚠ Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║   TRANSCESAR S.A.S. – Sistema Tickets    ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║  1. Gestión de Vehículos                 ║");
        System.out.println("║  2. Gestión de Conductores               ║");
        System.out.println("║  3. Gestión de Pasajeros                 ║");
        System.out.println("║  4. Venta de Tickets                     ║");
        System.out.println("║  5. Consultas y Estadísticas             ║");
        System.out.println("║  6. Reportes                             ║");
        System.out.println("║  0. Salir                                ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }

    private void menuVehiculos() {
        int op;
        do {
            System.out.println("\n── Vehículos ─────────────────────────────");
            System.out.println("  1. Registrar Buseta");
            System.out.println("  2. Registrar MicroBus");
            System.out.println("  3. Registrar Bus");
            System.out.println("  4. Listar vehículos");
            System.out.println("  5. Asignar conductor a vehículo");
            System.out.println("  0. Volver");
            op = leerEntero("Opción: ");
            switch (op) {
                case 1: registrarVehiculo("Buseta");   break;
                case 2: registrarVehiculo("MicroBus"); break;
                case 3: registrarVehiculo("Bus");      break;
                case 4: listarVehiculos();             break;
                case 5: asignarConductor();            break;
            }
        } while (op != 0);
    }

    private void registrarVehiculo(String tipo) {
        System.out.print("Placa: "); String placa = sc.nextLine().trim().toUpperCase();
        System.out.print("Ruta : "); String ruta  = sc.nextLine().trim();
        Vehiculo v = null;
        switch (tipo) {
            case "Buseta":   v = new Buseta(placa, ruta);   break;
            case "MicroBus": v = new MicroBus(placa, ruta); break;
            case "Bus":      v = new Bus(placa, ruta);      break;
        }
        if (v != null) vs.registrarVehiculo(v);
    }

    private void listarVehiculos() {
        if (vs.listarVehiculos().isEmpty()) {
            System.out.println("No hay vehículos registrados."); return;
        }
        for (Vehiculo v : vs.listarVehiculos()) v.imprimirDetalle();
    }

    private void asignarConductor() {
        System.out.print("Placa del vehículo : "); String placa  = sc.nextLine().trim().toUpperCase();
        System.out.print("Cédula del conductor: "); String cedula = sc.nextLine().trim();
        Conductor c = ps.buscarConductor(cedula);
        if (c == null) { System.out.println("⚠ Conductor no encontrado."); return; }
        vs.asignarConductor(placa, c);
    }

    private void menuConductores() {
        int op;
        do {
            System.out.println("\n── Conductores ───────────────────────────");
            System.out.println("  1. Registrar conductor");
            System.out.println("  2. Listar conductores");
            System.out.println("  0. Volver");
            op = leerEntero("Opción: ");
            switch (op) {
                case 1: registrarConductor(); break;
                case 2: listarConductores();  break;
            }
        } while (op != 0);
    }

    private void registrarConductor() {
        System.out.print("Cédula   : "); String cedula   = sc.nextLine().trim();
        System.out.print("Nombre   : "); String nombre   = sc.nextLine().trim();
        System.out.print("Licencia : "); String licencia = sc.nextLine().trim();
        System.out.print("Categoría (B1/B2/C1/C2): "); String cat = sc.nextLine().trim().toUpperCase();
        ps.registrarConductor(new Conductor(cedula, nombre, licencia, cat));
    }

    private void listarConductores() {
        if (ps.listarConductores().isEmpty()) {
            System.out.println("No hay conductores registrados."); return;
        }
        for (Conductor c : ps.listarConductores()) c.imprimirDetalle();
    }

    private void menuPasajeros() {
        int op;
        do {
            System.out.println("\n── Pasajeros ─────────────────────────────");
            System.out.println("  1. Registrar pasajero");
            System.out.println("  2. Listar pasajeros");
            System.out.println("  0. Volver");
            op = leerEntero("Opción: ");
            switch (op) {
                case 1: registrarPasajero(); break;
                case 2: listarPasajeros();   break;
            }
        } while (op != 0);
    }

    private void registrarPasajero() {
        System.out.print("Cédula: "); String cedula = sc.nextLine().trim();
        System.out.print("Nombre: "); String nombre = sc.nextLine().trim();
        System.out.println("Tipo  : 1) Regular  2) Estudiante  3) Adulto Mayor");
        int tipo = leerEntero("Tipo: ");
        Pasajero p;
        switch (tipo) {
            case 2:  p = new PasajeroEstudiante(cedula, nombre);  break;
            case 3:  p = new PasajeroAdultoMayor(cedula, nombre); break;
            default: p = new PasajeroRegular(cedula, nombre);
        }
        ps.registrarPasajero(p);
    }

    private void listarPasajeros() {
        if (ps.listarPasajeros().isEmpty()) {
            System.out.println("No hay pasajeros registrados."); return;
        }
        for (Pasajero p : ps.listarPasajeros()) p.imprimirDetalle();
    }

    private void menuTickets() {
        int op;
        do {
            System.out.println("\n── Tickets ───────────────────────────────");
            System.out.println("  1. Vender ticket");
            System.out.println("  2. Listar todos los tickets");
            System.out.println("  0. Volver");
            op = leerEntero("Opción: ");
            switch (op) {
                case 1: venderTicket();  break;
                case 2: listarTickets(); break;
            }
        } while (op != 0);
    }

    private void venderTicket() {
        System.out.print("Cédula del pasajero : "); String cedula  = sc.nextLine().trim();
        System.out.print("Placa del vehículo  : "); String placa   = sc.nextLine().trim().toUpperCase();
        System.out.print("Origen              : "); String origen  = sc.nextLine().trim();
        System.out.print("Destino             : "); String destino = sc.nextLine().trim();
        ts.venderTicket(cedula, placa, origen, destino);
    }

    private void listarTickets() {
        if (ts.listarTickets().isEmpty()) {
            System.out.println("No hay tickets vendidos."); return;
        }
        for (Ticket t : ts.listarTickets()) t.imprimirDetalle();
    }

    private void menuEstadisticas() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║            ESTADÍSTICAS                  ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.printf( "║  Total recaudado : $%-21d║%n", (int) ts.calcularTotal());
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║  Pasajeros por tipo:                     ║");
        for (Map.Entry<String, Integer> e : ts.pasajerosPorTipo().entrySet()) {
            System.out.printf("║    %-14s: %-24d║%n", e.getKey(), e.getValue());
        }
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║  Vehículo más tickets: " + ts.vehiculoConMasTickets());
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println("Presiona Enter para continuar...");
        sc.nextLine();
    }

    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("⚠ Ingresa un número válido.");
            }
        }
    }
    // ── REPORTES ──────────────────────────────────────────────
private void menuReportes() {
    int op;
    do {
        System.out.println("\n── Reportes ──────────────────────────────");
        System.out.println("  1. Tickets por fecha específica");
        System.out.println("  2. Tickets por tipo de vehículo");
        System.out.println("  3. Tickets por tipo de pasajero");
        System.out.println("  4. Resumen del día actual");
        System.out.println("  0. Volver");
        op = leerEntero("Opción: ");
        switch (op) {
            case 1: reportePorFecha();      break;
            case 2: reportePorVehiculo();   break;
            case 3: reportePorPasajero();   break;
            case 4: reporteDelDia();        break;
        }
    } while (op != 0);
}

private void reportePorFecha() {
    System.out.print("Ingresa la fecha (YYYY-MM-DD): ");
    String fechaStr = sc.nextLine().trim();
    java.time.LocalDate fecha;
    try {
        fecha = java.time.LocalDate.parse(fechaStr);
    } catch (Exception e) {
        System.out.println("⚠ Formato de fecha inválido."); return;
    }
    boolean encontrado = false;
    System.out.println("\n── Tickets para " + fecha + " ──────────────────");
    for (Ticket t : ts.listarTickets()) {
        if (t.getFechaCompra().equals(fecha)) {
            t.imprimirDetalle();
            encontrado = true;
        }
    }
    if (!encontrado) System.out.println("No hay tickets para esa fecha.");
}

private void reportePorVehiculo() {
    System.out.print("Tipo de vehículo (Buseta/MicroBus/Bus): ");
    String tipo = sc.nextLine().trim();
    boolean encontrado = false;
    System.out.println("\n── Tickets con vehículo tipo " + tipo + " ─────────");
    for (Ticket t : ts.listarTickets()) {
        if (t.getVehiculo().getClass().getSimpleName().equalsIgnoreCase(tipo)) {
            t.imprimirDetalle();
            encontrado = true;
        }
    }
    if (!encontrado) System.out.println("No hay tickets para ese tipo de vehículo.");
}

private void reportePorPasajero() {
    System.out.print("Tipo de pasajero (Regular/Estudiante/Adulto Mayor): ");
    String tipo = sc.nextLine().trim();
    boolean encontrado = false;
    System.out.println("\n── Tickets de pasajeros tipo " + tipo + " ─────────");
    for (Ticket t : ts.listarTickets()) {
        if (t.getPasajero().getTipo().equalsIgnoreCase(tipo)) {
            t.imprimirDetalle();
            encontrado = true;
        }
    }
    if (!encontrado) System.out.println("No hay tickets para ese tipo de pasajero.");
}

private void reporteDelDia() {
    java.time.LocalDate hoy = java.time.LocalDate.now();
    int total = 0;
    double recaudado = 0;
    System.out.println("\n── Resumen del día " + hoy + " ──────────────────");
    for (Ticket t : ts.listarTickets()) {
        if (t.getFechaCompra().equals(hoy)) {
            t.imprimirDetalle();
            total++;
            recaudado += t.getValorFinal();
        }
    }
    System.out.println("╔══════════════════════════════════════════╗");
    System.out.printf( "║  Tickets vendidos hoy : %-17d║%n", total);
    System.out.printf( "║  Total recaudado hoy  : $%-16d║%n", (int) recaudado);
    System.out.println("╚══════════════════════════════════════════╝");
}
    
 
}
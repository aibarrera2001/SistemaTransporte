package View;

import Model.*;
import Service.*;
import java.util.List;
import java.util.Scanner;
import java.util.Map;

public class Menu {

    private final Scanner         sc = new Scanner(System.in);
    private final VehiculoService vs;
    private final PersonaService  ps;
    private final TicketService   ts;

    public Menu() {
        this.vs = new VehiculoService();
        this.ps = new PersonaService();
        this.ts = new TicketService(vs, ps);
    }

    // ══════════════════════════════════════════════════════════
    //  INICIO
    // ══════════════════════════════════════════════════════════
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
                case 0: System.out.println("\n Saliendo del sistema TransCesar..."); break;
                default: System.out.println("  Opcion no valida.");
            }
        } while (opcion != 0);
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║   TRANSCESAR S.A.S. - Sistema Tickets    ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║  1. Gestion de Vehiculos                 ║");
        System.out.println("║  2. Gestion de Conductores               ║");
        System.out.println("║  3. Gestion de Pasajeros                 ║");
        System.out.println("║  4. Venta de Tickets                     ║");
        System.out.println("║  5. Consultas y Estadisticas             ║");
        System.out.println("║  6. Reportes                             ║");
        System.out.println("║  0. Salir                                ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }

    // ══════════════════════════════════════════════════════════
    //  VEHICULOS
    // ══════════════════════════════════════════════════════════
    private void menuVehiculos() {
        int op;
        do {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║            GESTION DE VEHICULOS          ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║  1. Registrar Buseta                     ║");
            System.out.println("║  2. Registrar MicroBus                   ║");
            System.out.println("║  3. Registrar Bus                        ║");
            System.out.println("║  4. Listar vehiculos                     ║");
            System.out.println("║  5. Asignar conductor a vehiculo         ║");
            System.out.println("║  0. Volver                               ║");
            System.out.println("╚══════════════════════════════════════════╝");
            op = leerEntero("Opcion: ");
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
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║         REGISTRAR " + String.format("%-24s", tipo.toUpperCase()) + "║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.print("  Placa : "); String placa = sc.nextLine().trim().toUpperCase();
        System.out.print("  Ruta  : "); String ruta  = sc.nextLine().trim();

        Vehiculo v = null;
        switch (tipo) {
            case "Buseta":   v = new Buseta(placa, ruta);   break;
            case "MicroBus": v = new MicroBus(placa, ruta); break;
            case "Bus":      v = new Bus(placa, ruta);      break;
        }

        if (v != null) {
            boolean registrado = vs.registrarVehiculo(v);
            if (registrado) {
                v.imprimirDetalle();
                System.out.print("  Desea asignar un conductor ahora? (s/n): ");
                String resp = sc.nextLine().trim().toLowerCase();
                if (resp.equals("s")) {
                    if (ps.listarConductores().isEmpty()) {
                        System.out.println("  No hay conductores registrados.");
                    } else {
                        System.out.println("\n╔══════════════════════════════════════════╗");
                        System.out.println("║         CONDUCTORES DISPONIBLES          ║");
                        System.out.println("╠══════════╦══════════════════╦════════════╣");
                        System.out.println("║  Cedula  ║      Nombre      ║  Licencia  ║");
                        System.out.println("╠══════════╬══════════════════╬════════════╣");
                        for (Conductor c : ps.listarConductores()) {
                            System.out.printf("║ %-9s║ %-17s║ %-11s║%n",
                                    c.getCedula(), c.getNombre(), c.getCategoriaLicencia());
                        }
                        System.out.println("╚══════════╩══════════════════╩════════════╝");
                        System.out.print("  Cedula del conductor: ");
                        String cedula = sc.nextLine().trim();
                        Conductor c = ps.buscarConductor(cedula);
                        if (c == null) {
                            System.out.println("  Conductor no encontrado.");
                        } else {
                            vs.asignarConductor(placa, c);
                        }
                    }
                }
            }
        }
    }

    private void listarVehiculos() {
        if (vs.listarVehiculos().isEmpty()) {
            System.out.println("\n  No hay vehiculos registrados."); return;
        }
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║          VEHICULOS REGISTRADOS           ║");
        System.out.println("╚══════════════════════════════════════════╝");
        for (Vehiculo v : vs.listarVehiculos()) v.imprimirDetalle();
    }

    private void asignarConductor() {
        if (ps.listarConductores().isEmpty()) {
            System.out.println("\n  No hay conductores registrados."); return;
        }
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║         CONDUCTORES DISPONIBLES          ║");
        System.out.println("╠══════════╦══════════════════╦════════════╣");
        System.out.println("║  Cedula  ║      Nombre      ║ Categoria  ║");
        System.out.println("╠══════════╬══════════════════╬════════════╣");
        for (Conductor c : ps.listarConductores()) {
            System.out.printf("║ %-9s║ %-17s║ %-11s║%n",
                    c.getCedula(), c.getNombre(), c.getCategoriaLicencia());
        }
        System.out.println("╚══════════╩══════════════════╩════════════╝");

        System.out.print("  Placa del vehiculo  : "); String placa  = sc.nextLine().trim().toUpperCase();
        System.out.print("  Cedula del conductor: "); String cedula = sc.nextLine().trim();
        Conductor c = ps.buscarConductor(cedula);
        if (c == null) { System.out.println("  Conductor no encontrado."); return; }
        vs.asignarConductor(placa, c);
    }

    // ══════════════════════════════════════════════════════════
    //  CONDUCTORES
    // ══════════════════════════════════════════════════════════
    private void menuConductores() {
        int op;
        do {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║          GESTION DE CONDUCTORES          ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║  1. Registrar conductor                  ║");
            System.out.println("║  2. Listar conductores                   ║");
            System.out.println("║  0. Volver                               ║");
            System.out.println("╚══════════════════════════════════════════╝");
            op = leerEntero("Opcion: ");
            switch (op) {
                case 1: registrarConductor(); break;
                case 2: listarConductores();  break;
            }
        } while (op != 0);
    }

    private void registrarConductor() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║           REGISTRAR CONDUCTOR            ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.print("  Cedula            : "); String cedula   = sc.nextLine().trim();
        System.out.print("  Nombre            : "); String nombre   = sc.nextLine().trim();
        System.out.print("  Licencia          : "); String licencia = sc.nextLine().trim();
        System.out.print("  Categoria (B1/B2/C1/C2): "); String cat = sc.nextLine().trim().toUpperCase();
        ps.registrarConductor(new Conductor(cedula, nombre, licencia, cat));
    }

    private void listarConductores() {
        if (ps.listarConductores().isEmpty()) {
            System.out.println("\n  No hay conductores registrados."); return;
        }
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║          CONDUCTORES REGISTRADOS         ║");
        System.out.println("╚══════════════════════════════════════════╝");
        for (Conductor c : ps.listarConductores()) c.imprimirDetalle();
    }

    // ══════════════════════════════════════════════════════════
    //  PASAJEROS
    // ══════════════════════════════════════════════════════════
    private void menuPasajeros() {
        int op;
        do {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║           GESTION DE PASAJEROS           ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║  1. Registrar pasajero                   ║");
            System.out.println("║  2. Listar pasajeros                     ║");
            System.out.println("║  0. Volver                               ║");
            System.out.println("╚══════════════════════════════════════════╝");
            op = leerEntero("Opcion: ");
            switch (op) {
                case 1: registrarPasajero(); break;
                case 2: listarPasajeros();   break;
            }
        } while (op != 0);
    }

    private void registrarPasajero() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║           REGISTRAR PASAJERO             ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.print("  Cedula: "); String cedula = sc.nextLine().trim();
        System.out.print("  Nombre: "); String nombre = sc.nextLine().trim();
        System.out.println("\n  Tipo de pasajero:");
        System.out.println("  1) Regular      tarifa completa");
        System.out.println("  2) Estudiante   descuento 20%");
        System.out.println("  3) Adulto Mayor descuento 30%");
        int tipo = leerEntero("  Tipo: ");
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
            System.out.println("\n  No hay pasajeros registrados."); return;
        }
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║           PASAJEROS REGISTRADOS          ║");
        System.out.println("╚══════════════════════════════════════════╝");
        for (Pasajero p : ps.listarPasajeros()) p.imprimirDetalle();
    }

    // ══════════════════════════════════════════════════════════
    //  TICKETS
    // ══════════════════════════════════════════════════════════
    private void menuTickets() {
        int op;
        do {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║             VENTA DE TICKETS             ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║  1. Vender ticket                        ║");
            System.out.println("║  2. Listar todos los tickets             ║");
            System.out.println("║  0. Volver                               ║");
            System.out.println("╚══════════════════════════════════════════╝");
            op = leerEntero("Opcion: ");
            switch (op) {
                case 1: venderTicket();  break;
                case 2: listarTickets(); break;
            }
        } while (op != 0);
    }

    private void venderTicket() {
        // ── Pasajeros ──────────────────────────────────────
        if (ps.listarPasajeros().isEmpty()) {
            System.out.println("\n  No hay pasajeros registrados."); return;
        }
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║           PASAJEROS REGISTRADOS          ║");
        System.out.println("╠══════════╦══════════════════╦════════════╣");
        System.out.println("║  Cedula  ║      Nombre      ║    Tipo    ║");
        System.out.println("╠══════════╬══════════════════╬════════════╣");
        for (Pasajero p : ps.listarPasajeros()) {
            System.out.printf("║ %-9s║ %-17s║ %-11s║%n",
                    p.getCedula(), p.getNombre(), p.getTipo());
        }
        System.out.println("╚══════════╩══════════════════╩════════════╝");
        System.out.print("  Cedula del pasajero : ");
        String cedula = sc.nextLine().trim();

        // ── Vehiculos ──────────────────────────────────────
        List<Vehiculo> disponibles = vs.listarVehiculos().stream()
                .filter(v -> v.tieneCupos() && v.isDisponible())
                .sorted((a, b) -> Double.compare(a.getTarifaBase(), b.getTarifaBase()))
                .collect(java.util.stream.Collectors.toList());

        if (disponibles.isEmpty()) {
            System.out.println("\n  No hay vehiculos con cupos disponibles."); return;
        }

        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║      VEHICULOS DISPONIBLES  (menor tarifa primero)   ║");
        System.out.println("╠═════════╦══════════╦════════════╦═══════╦════════════╣");
        System.out.println("║  Placa  ║   Tipo   ║    Ruta    ║ Cupos ║   Tarifa   ║");
        System.out.println("╠═════════╬══════════╬════════════╬═══════╬════════════╣");
        for (int i = 0; i < disponibles.size(); i++) {
            Vehiculo v = disponibles.get(i);
            String estrella = (i == 0) ? "*" : " ";
            System.out.printf("║%s%-7s ║ %-8s ║ %-10s ║  %-4d ║ $%-9d║%n",
                    estrella,
                    v.getPlaca(),
                    v.getClass().getSimpleName(),
                    v.getRuta(),
                    v.getCuposDisponibles(),
                    (int) v.getTarifaBase());
        }
        Vehiculo mejor = disponibles.get(0);
        System.out.println("╠═════════╩══════════╩════════════╩═══════╩════════════╣");
        System.out.printf( "║  * Mas economico: %-35s║%n",
                mejor.getPlaca() + " (" + mejor.getClass().getSimpleName()
                + ") $" + (int) mejor.getTarifaBase());
        System.out.println("╚══════════════════════════════════════════════════════╝");

        System.out.print("  Placa del vehiculo : ");
        String placa = sc.nextLine().trim().toUpperCase();
        System.out.print("  Origen             : ");
        String origen = sc.nextLine().trim();
        System.out.print("  Destino            : ");
        String destino = sc.nextLine().trim();

        ts.venderTicket(cedula, placa, origen, destino);
    }

    private void listarTickets() {
        if (ts.listarTickets().isEmpty()) {
            System.out.println("\n  No hay tickets vendidos."); return;
        }
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║            TICKETS VENDIDOS              ║");
        System.out.println("╚══════════════════════════════════════════╝");
        for (Ticket t : ts.listarTickets()) t.imprimirDetalle();
    }

    // ══════════════════════════════════════════════════════════
    //  ESTADISTICAS
    // ══════════════════════════════════════════════════════════
    private void menuEstadisticas() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║              ESTADISTICAS                ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.printf( "║  Total recaudado    : $%-18d║%n", (int) ts.calcularTotal());
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║  Pasajeros por tipo:                     ║");
        for (Map.Entry<String, Integer> e : ts.pasajerosPorTipo().entrySet()) {
            System.out.printf("║    %-16s: %-22d║%n", e.getKey(), e.getValue());
        }
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.printf( "║  Vehiculo mas tickets: %-18s║%n", ts.vehiculoConMasTickets());
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println("  Presiona Enter para continuar...");
        sc.nextLine();
    }

    // ══════════════════════════════════════════════════════════
    //  REPORTES
    // ══════════════════════════════════════════════════════════
    private void menuReportes() {
        int op;
        do {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║                REPORTES                  ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║  1. Tickets por fecha especifica         ║");
            System.out.println("║  2. Tickets por tipo de vehiculo         ║");
            System.out.println("║  3. Tickets por tipo de pasajero         ║");
            System.out.println("║  4. Resumen del dia actual               ║");
            System.out.println("║  0. Volver                               ║");
            System.out.println("╚══════════════════════════════════════════╝");
            op = leerEntero("Opcion: ");
            switch (op) {
                case 1: reportePorFecha();    break;
                case 2: reportePorVehiculo(); break;
                case 3: reportePorPasajero(); break;
                case 4: reporteDelDia();      break;
            }
        } while (op != 0);
    }

    private void reportePorFecha() {
        System.out.print("  Fecha (YYYY-MM-DD): ");
        String fechaStr = sc.nextLine().trim();
        java.time.LocalDate fecha;
        try {
            fecha = java.time.LocalDate.parse(fechaStr);
        } catch (Exception e) {
            System.out.println("  Formato de fecha invalido."); return;
        }
        boolean encontrado = false;
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.printf( "║  Tickets del %-28s║%n", fecha);
        System.out.println("╚══════════════════════════════════════════╝");
        for (Ticket t : ts.listarTickets()) {
            if (t.getFechaCompra().equals(fecha)) {
                t.imprimirDetalle(); encontrado = true;
            }
        }
        if (!encontrado) System.out.println("  No hay tickets para esa fecha.");
    }

    private void reportePorVehiculo() {
        System.out.println("\n  Tipo de vehiculo:");
        System.out.println("  1) Buseta   2) MicroBus   3) Bus");
        int op = leerEntero("  Opcion: ");
        String tipo;
        switch (op) {
            case 1: tipo = "Buseta";   break;
            case 2: tipo = "MicroBus"; break;
            case 3: tipo = "Bus";      break;
            default: System.out.println("  Opcion invalida."); return;
        }
        boolean encontrado = false;
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.printf( "║  Tickets vehiculo tipo: %-17s║%n", tipo);
        System.out.println("╚══════════════════════════════════════════╝");
        for (Ticket t : ts.listarTickets()) {
            if (t.getVehiculo().getClass().getSimpleName().equalsIgnoreCase(tipo)) {
                t.imprimirDetalle(); encontrado = true;
            }
        }
        if (!encontrado) System.out.println("  No hay tickets para ese tipo de vehiculo.");
    }

    private void reportePorPasajero() {
        System.out.println("\n  Tipo de pasajero:");
        System.out.println("  1) Regular   2) Estudiante   3) Adulto Mayor");
        int op = leerEntero("  Opcion: ");
        String tipo;
        switch (op) {
            case 1: tipo = "Regular";      break;
            case 2: tipo = "Estudiante";   break;
            case 3: tipo = "Adulto Mayor"; break;
            default: System.out.println("  Opcion invalida."); return;
        }
        boolean encontrado = false;
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.printf( "║  Tickets pasajero tipo: %-17s║%n", tipo);
        System.out.println("╚══════════════════════════════════════════╝");
        for (Ticket t : ts.listarTickets()) {
            if (t.getPasajero().getTipo().equalsIgnoreCase(tipo)) {
                t.imprimirDetalle(); encontrado = true;
            }
        }
        if (!encontrado) System.out.println("  No hay tickets para ese tipo de pasajero.");
    }

    private void reporteDelDia() {
        java.time.LocalDate hoy = java.time.LocalDate.now();
        int total = 0;
        double recaudado = 0;
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.printf( "║  Resumen del dia: %-23s║%n", hoy);
        System.out.println("╚══════════════════════════════════════════╝");
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

    // ══════════════════════════════════════════════════════════
    //  UTILIDAD
    // ══════════════════════════════════════════════════════════
    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Ingresa un numero valido.");
            }
        }
    }
}
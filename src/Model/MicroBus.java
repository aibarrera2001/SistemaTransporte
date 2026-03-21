package Model;

public class MicroBus extends Vehiculo {

    public MicroBus(String placa, Ruta ruta) {
        super(placa, ruta);
        this.capacidadMaxima = 25;
    }

    @Override
    public double getTarifaBase() { return 10000; }

    @Override
    public void imprimirDetalle() {
        System.out.println("┌─ MICROBUS ───────────────────────────────");
        System.out.println("│ Placa    : " + placa);
        System.out.println("│ Ruta     : " + (ruta != null ? ruta.toString() : "Sin ruta"));
        System.out.println("│ Capacidad: " + capacidadMaxima);
        System.out.println("│ Cupos    : " + getCuposDisponibles());
        System.out.println("│ Tarifa   : $" + (int) getTarifaBase());
        System.out.println("│ Estado   : " + (disponible ? "Disponible" : "No disponible"));
        System.out.println("│ Conductor: " + (conductor != null
                ? conductor.getNombre() + " (" + conductor.getCedula() + ")"
                : "Sin asignar"));
        System.out.println("└──────────────────────────────────────────");
    }
}
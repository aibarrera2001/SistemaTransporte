package Model;
import java.time.LocalDate;

public class Ticket implements Imprimible, Calculable {
    private Pasajero  pasajero;
    private Vehiculo  vehiculo;
    private LocalDate fechaCompra;
    private String    origen;
    private String    destino;
    private double    valorFinal;

    public Ticket(Pasajero pasajero, Vehiculo vehiculo,
                  String origen, String destino) {
        this.pasajero    = pasajero;
        this.vehiculo    = vehiculo;
        this.origen      = origen;
        this.destino     = destino;
        this.fechaCompra = LocalDate.now();
        this.valorFinal  = calcularTotal();
    }

    @Override
    public double calcularTotal() {
        double tarifa = vehiculo.getTarifaBase();
        // aplicarDescuento recibe la tarifa y devuelve el valor con descuento
        return pasajero.aplicarDescuento(tarifa);
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("┌─ TICKET ─────────────────────────────────");
        System.out.println("│ Pasajero : " + pasajero.getNombre() + " (" + pasajero.getTipo() + ")");
        System.out.println("│ Vehiculo : " + vehiculo.getPlaca() + " [" + vehiculo.getClass().getSimpleName() + "]");
        System.out.println("│ Origen   : " + origen);
        System.out.println("│ Destino  : " + destino);
        System.out.println("│ Fecha    : " + fechaCompra);
        System.out.println("│ Valor    : $" + (int) valorFinal);
        System.out.println("└──────────────────────────────────────────");
    }

    public Pasajero  getPasajero()    { return pasajero; }
    public Vehiculo  getVehiculo()    { return vehiculo; }
    public LocalDate getFechaCompra() { return fechaCompra; }
    public String    getOrigen()      { return origen; }
    public String    getDestino()     { return destino; }
    public double    getValorFinal()  { return valorFinal; }
    public void setValorFinal(double valorFinal) { this.valorFinal  = valorFinal; }
    public void setFechaCompra(LocalDate fecha)  { this.fechaCompra = fecha; }
}
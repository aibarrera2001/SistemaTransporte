package Model;

import java.time.LocalDate;

public class PasajeroRegular extends Pasajero {

    public PasajeroRegular(String cedula, String nombre, LocalDate fechaNacimiento) {
        super(cedula, nombre, fechaNacimiento);
    }

    @Override public double aplicarDescuento(double tarifa) { return tarifa; }
    @Override public String getTipo() { return "Regular"; }

    @Override
    public void imprimirDetalle() {
        System.out.println("┌─ PASAJERO REGULAR ───────────────────────");
        System.out.println("│ Cedula : " + cedula);
        System.out.println("│ Nombre : " + nombre);
        System.out.println("│ Edad   : " + getEdad() + " años");
        System.out.println("│ Tipo   : Regular (sin descuento)");
        System.out.println("└──────────────────────────────────────────");
    }
}
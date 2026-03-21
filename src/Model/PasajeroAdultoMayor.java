package Model;

import java.time.LocalDate;

public class PasajeroAdultoMayor extends Pasajero {

    public PasajeroAdultoMayor(String cedula, String nombre, LocalDate fechaNacimiento) {
        super(cedula, nombre, fechaNacimiento);
    }

    @Override public double aplicarDescuento(double tarifa) { return tarifa * 0.70; }
    @Override public String getTipo() { return "Adulto Mayor"; }

    @Override
    public void imprimirDetalle() {
        System.out.println("┌─ PASAJERO ADULTO MAYOR ──────────────────");
        System.out.println("│ Cedula : " + cedula);
        System.out.println("│ Nombre : " + nombre);
        System.out.println("│ Edad   : " + getEdad() + " años");
        System.out.println("│ Tipo   : Adulto Mayor (30% descuento - asignado automaticamente)");
        System.out.println("└──────────────────────────────────────────");
    }
}
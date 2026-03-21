package Model;

import java.time.LocalDate;

public class PasajeroEstudiante extends Pasajero {

    public PasajeroEstudiante(String cedula, String nombre, LocalDate fechaNacimiento) {
        super(cedula, nombre, fechaNacimiento);
    }

    public double aplicarDescuento(double tarifa) { return tarifa * 0.80; }
    @Override public String getTipo() { return "Estudiante"; }

    @Override
    public void imprimirDetalle() {
        System.out.println("┌─ PASAJERO ESTUDIANTE ────────────────────");
        System.out.println("│ Cedula : " + cedula);
        System.out.println("│ Nombre : " + nombre);
        System.out.println("│ Edad   : " + getEdad() + " años");
        System.out.println("│ Tipo   : Estudiante (20% descuento)");
        System.out.println("└──────────────────────────────────────────");
    }
}
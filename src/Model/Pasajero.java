package Model;

import java.time.LocalDate;
import java.time.Period;

public abstract class Pasajero extends Persona {

    protected LocalDate fechaNacimiento;

    public Pasajero(String cedula, String nombre, LocalDate fechaNacimiento) {
        super(cedula, nombre);
        this.fechaNacimiento = fechaNacimiento;
    }

    public abstract double aplicarDescuento(double tarifa);
    public abstract String getTipo();

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }

    public int getEdad() {
        if (fechaNacimiento == null) return 0;
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    // Fabrica: recibe cedula, nombre y fecha y devuelve el subtipo correcto
    public static Pasajero crear(String cedula, String nombre, LocalDate fechaNacimiento) {
        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
        if (edad >= 60) {
            return new PasajeroAdultoMayor(cedula, nombre, fechaNacimiento);
        }
        return new PasajeroRegular(cedula, nombre, fechaNacimiento);
    }
}
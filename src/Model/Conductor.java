/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Andrés
 */


public class Conductor extends Persona {

    private String numeroLicencia;
    private String categoriaLicencia;

    public Conductor(String cedula, String nombre,
                     String numeroLicencia, String categoriaLicencia) {
        super(cedula, nombre);
        this.numeroLicencia    = numeroLicencia;
        this.categoriaLicencia = categoriaLicencia;
    }

    public boolean tieneLicencia() {
        return numeroLicencia != null && !numeroLicencia.trim().isEmpty();
    }

    public String getNumeroLicencia()    { return numeroLicencia; }
    public String getCategoriaLicencia() { return categoriaLicencia; }

    @Override
    public void imprimirDetalle() {
        System.out.println("┌─ CONDUCTOR ──────────────────────────────");
        System.out.println("│ Cédula    : " + cedula);
        System.out.println("│ Nombre    : " + nombre);
        System.out.println("│ Licencia  : " + numeroLicencia);
        System.out.println("│ Categoría : " + categoriaLicencia);
        System.out.println("└──────────────────────────────────────────");
    }
}
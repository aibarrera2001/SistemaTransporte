package Model;

public class Ruta implements Imprimible {

    private String codigo;
    private String ciudadOrigen;
    private String ciudadDestino;
    private double distanciaKm;
    private int    tiempoMinutos;

    public Ruta(String codigo, String ciudadOrigen,
                String ciudadDestino, double distanciaKm, int tiempoMinutos) {
        this.codigo        = codigo;
        this.ciudadOrigen  = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.distanciaKm   = distanciaKm;
        this.tiempoMinutos = tiempoMinutos;
    }

    public String getCodigo()        { return codigo; }
    public String getCiudadOrigen()  { return ciudadOrigen; }
    public String getCiudadDestino() { return ciudadDestino; }
    public double getDistanciaKm()   { return distanciaKm; }
    public int    getTiempoMinutos() { return tiempoMinutos; }

    @Override
    public String toString() {
        return "[" + codigo + "] " + ciudadOrigen + " -> " + ciudadDestino
                + " | " + distanciaKm + " km / " + tiempoMinutos + " min";
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("┌─ RUTA ───────────────────────────────────");
        System.out.println("│ Codigo   : " + codigo);
        System.out.println("│ Origen   : " + ciudadOrigen);
        System.out.println("│ Destino  : " + ciudadDestino);
        System.out.println("│ Distancia: " + distanciaKm + " km");
        System.out.println("│ Tiempo   : " + tiempoMinutos + " min");
        System.out.println("└──────────────────────────────────────────");
    }
}
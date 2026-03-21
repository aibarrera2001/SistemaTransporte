package Model;

public abstract class Vehiculo implements Imprimible {

    protected String    placa;
    protected Ruta      ruta;               // <-- ahora es objeto Ruta
    protected int       capacidadMaxima;
    protected int       pasajerosActuales;
    protected boolean   disponible;
    protected Conductor conductor;

    public Vehiculo(String placa, Ruta ruta) {
        this.placa             = placa;
        this.ruta              = ruta;
        this.pasajerosActuales = 0;
        this.disponible        = true;
        this.conductor         = null;
    }

    public abstract double getTarifaBase();

    public boolean tieneCupos()          { return pasajerosActuales < capacidadMaxima; }
    public void    agregarPasajero()     { if (tieneCupos()) pasajerosActuales++; }
    public int     getCuposDisponibles() { return capacidadMaxima - pasajerosActuales; }

    public String    getPlaca()             { return placa; }
    public Ruta      getRuta()              { return ruta; }
    public int       getCapacidadMaxima()   { return capacidadMaxima; }
    public int       getPasajerosActuales() { return pasajerosActuales; }
    public boolean   isDisponible()         { return disponible; }
    public Conductor getConductor()         { return conductor; }

    public void setDisponible(boolean d)      { this.disponible = d; }
    public void setPasajerosActuales(int p)   { this.pasajerosActuales = p; }
    public void setConductor(Conductor c)     { this.conductor = c; }
    public void setRuta(Ruta r)               { this.ruta = r; }
}
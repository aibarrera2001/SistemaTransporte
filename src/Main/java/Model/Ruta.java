package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una ruta del sistema de transporte.
 * Almacena rutas registradas en memoria para asignarlas a vehículos.
 */
public class Ruta {

    // ── Atributos ─────────────────────────────────────────────────────────────
    private String codigoRuta;
    private String ciudadOrigen;
    private String ciudadDestino;
    private double distanciaKm;
    private int    tiempoEstimadoMin;

    /** Lista compartida de rutas disponibles en el sistema. */
    private static final List<Ruta> rutasRegistradas = new ArrayList<>();

    // ── Constructor ───────────────────────────────────────────────────────────
    /**
     * @param codigoRuta        Identificador único (ej. "R001").
     * @param ciudadOrigen      Ciudad de partida.
     * @param ciudadDestino     Ciudad de llegada.
     * @param distanciaKm       Distancia en kilómetros.
     * @param tiempoEstimadoMin Tiempo estimado de viaje en minutos.
     */
    public Ruta(String codigoRuta, String ciudadOrigen, String ciudadDestino,
                double distanciaKm, int tiempoEstimadoMin) {
        this.codigoRuta        = codigoRuta;
        this.ciudadOrigen      = ciudadOrigen;
        this.ciudadDestino     = ciudadDestino;
        this.distanciaKm       = distanciaKm;
        this.tiempoEstimadoMin = tiempoEstimadoMin;
    }

    // ── Gestión del catálogo ──────────────────────────────────────────────────

    /**
     * Registra una ruta en el catálogo. No permite códigos duplicados.
     *
     * @param ruta Ruta a registrar.
     * @return true si se registró correctamente, false si el código ya existe.
     */
    public static boolean registrarRuta(Ruta ruta) {
        for (Ruta r : rutasRegistradas) {
            if (r.codigoRuta.equalsIgnoreCase(ruta.codigoRuta)) {
                System.out.println("Ya existe una ruta con el código: " + ruta.codigoRuta);
                return false;
            }
        }
        rutasRegistradas.add(ruta);
        System.out.println("Ruta registrada: " + ruta.codigoRuta);
        return true;
    }

    /**
     * Busca una ruta por su código.
     *
     * @param codigo Código de ruta a buscar.
     * @return La ruta encontrada, o null si no existe.
     */
    public static Ruta buscarPorCodigo(String codigo) {
        for (Ruta r : rutasRegistradas) {
            if (r.codigoRuta.equalsIgnoreCase(codigo)) {
                return r;
            }
        }
        return null;
    }

    /**
     * @return Copia de la lista de rutas registradas.
     */
    public static List<Ruta> getRutasRegistradas() {
        return new ArrayList<>(rutasRegistradas);
    }

    /**
     * Imprime en consola todas las rutas registradas.
     */
    public static void listarRutas() {
        if (rutasRegistradas.isEmpty()) {
            System.out.println("No hay rutas registradas.");
            return;
        }
        System.out.println("=== Rutas disponibles ===");
        for (Ruta r : rutasRegistradas) {
            System.out.println(r);
        }
    }

    // ── Getters y Setters ─────────────────────────────────────────────────────
    public String getCodigoRuta()                          { return codigoRuta; }
    public void   setCodigoRuta(String codigoRuta)         { this.codigoRuta = codigoRuta; }

    public String getCiudadOrigen()                        { return ciudadOrigen; }
    public void   setCiudadOrigen(String ciudadOrigen)     { this.ciudadOrigen = ciudadOrigen; }

    public String getCiudadDestino()                       { return ciudadDestino; }
    public void   setCiudadDestino(String ciudadDestino)   { this.ciudadDestino = ciudadDestino; }

    public double getDistanciaKm()                         { return distanciaKm; }
    public void   setDistanciaKm(double distanciaKm)       { this.distanciaKm = distanciaKm; }

    public int    getTiempoEstimadoMin()                   { return tiempoEstimadoMin; }
    public void   setTiempoEstimadoMin(int tiempoEstimadoMin) { this.tiempoEstimadoMin = tiempoEstimadoMin; }

    // ── toString ──────────────────────────────────────────────────────────────
    @Override
    public String toString() {
        return String.format("[%s] %s → %s | %.1f km | %d min",
                codigoRuta, ciudadOrigen, ciudadDestino, distanciaKm, tiempoEstimadoMin);
    }
}
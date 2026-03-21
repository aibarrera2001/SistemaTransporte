package Service;

import Dao.RutaDAO;
import Model.Ruta;
import java.util.List;

public class RutaService {

    private List<Ruta>   rutas;
    private final RutaDAO dao = new RutaDAO();

    public RutaService() {
        this.rutas = dao.cargarTodos();
    }

    public boolean registrarRuta(Ruta r) {
        for (Ruta ex : rutas) {
            if (ex.getCodigo().equalsIgnoreCase(r.getCodigo())) {
                System.out.println("  Ya existe una ruta con el codigo " + r.getCodigo());
                return false;
            }
        }
        rutas.add(r);
        dao.guardar(r);
        System.out.println("  Ruta registrada correctamente.");
        return true;
    }

    public Ruta buscarPorCodigo(String codigo) {
        for (Ruta r : rutas)
            if (r.getCodigo().equalsIgnoreCase(codigo)) return r;
        return null;
    }

    public List<Ruta> listarRutas() { return rutas; }
}
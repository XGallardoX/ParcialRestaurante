package modelo;

import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    private static ReservaDAO instancia;
    private List<Reserva> reservas = new ArrayList<>();

    private ReservaDAO() {}

    public static ReservaDAO getInstancia() {
        if (instancia == null) {
            instancia = new ReservaDAO();
        }
        return instancia;
    }

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public List<Reserva> obtenerReservasPorUsuario(int idUsuario) {
        List<Reserva> resultado = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getIdUsuario() == idUsuario) {
                resultado.add(r);
            }
        }
        return resultado;
    }
}

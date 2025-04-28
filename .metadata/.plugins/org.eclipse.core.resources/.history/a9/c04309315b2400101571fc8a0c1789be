package controlador;

import modelo.Reserva;
import modelo.ReservaDAO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ReservaController {
    private ReservaDAO reservaDAO = ReservaDAO.getInstancia();

    public ReservaController() {
        reservaDAO.cargarReservas(); // 🔥 Cargar las reservas existentes al iniciar
    }

    public void hacerReserva(int idUsuario, int idMesa, LocalDate fecha, LocalTime hora) {
        Reserva nuevaReserva = new Reserva(0, idUsuario, idMesa, fecha, hora, "Confirmada");
        reservaDAO.agregarReserva(nuevaReserva);
    }

    public List<Reserva> verReservasUsuario(int idUsuario) {
        return reservaDAO.obtenerReservasPorUsuario(idUsuario);
    }
}

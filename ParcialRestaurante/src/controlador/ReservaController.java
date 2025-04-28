package controlador;

import modelo.Reserva;
import modelo.ReservaDAO;
import modelo.Usuario;
import modelo.UsuarioDAO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ReservaController {
    private ReservaDAO reservaDAO;
    private UsuarioDAO usuarioDAO;

    public ReservaController() {
        this.reservaDAO = ReservaDAO.getInstancia();
        this.usuarioDAO = UsuarioDAO.getInstancia();
        
        reservaDAO.obtenerTodasLasReservas(); 
        usuarioDAO.cargarUsuarios();  // 🔥 Agregamos esto para que también cargue los usuarios
    }

    public void hacerReserva(int idUsuario, int idMesa, LocalDate fecha, LocalTime hora) {
        Reserva nuevaReserva = new Reserva(0, idUsuario, idMesa, fecha, hora, "Confirmada");
        reservaDAO.agregarReserva(nuevaReserva);
        reservaDAO.guardarReservas(); // 🔥 Guardar después de agregar
    }

    public List<Reserva> verReservasUsuario(int idUsuario) {
        return reservaDAO.obtenerReservasPorUsuario(idUsuario);
    }
    
    public void cancelarReserva(int idReserva) {
        Reserva reserva = obtenerReservaPorId(idReserva);
        if (reserva != null) {
            reserva.setEstado("Cancelada");
            reservaDAO.guardarReservas(); // 🔥 Guardar después de cancelar
        }
    }

    public void eliminarReserva(int idReserva) {
        reservaDAO.eliminarReserva(idReserva); // 🔥 Llamamos al DAO
    }

    public Reserva obtenerReservaPorId(int idReserva) {
        return reservaDAO.obtenerReservaPorId(idReserva); 
    }

    public List<Reserva> obtenerTodasLasReservas() {
        return reservaDAO.obtenerTodasLasReservas(); 
    }

    public Usuario obtenerUsuarioPorId(int idUsuario) {
        return usuarioDAO.buscarPorId(idUsuario); 
    }
}

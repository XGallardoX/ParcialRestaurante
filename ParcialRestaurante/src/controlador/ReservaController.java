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

    public void hacerReserva(String correoUsuario, int idMesa, LocalDate fecha, LocalTime hora) {
        Reserva nuevaReserva = new Reserva(correoUsuario, idMesa, fecha, hora, "Confirmada");
        reservaDAO.agregarReserva(nuevaReserva);
        reservaDAO.guardarReservas(); // 🔥 Guardar después de agregar
    }

    public List<Reserva> verReservasUsuario(String correo) {
        return reservaDAO.obtenerReservasPorUsuario(correo);
    }
    
    public void cancelarReserva(int idReserva) {
        Reserva reserva = obtenerReservaPorId(idReserva);
        if (reserva != null) {
            reserva.setEstado("Cancelada");
            reservaDAO.guardarReservas(); // 🔥 Guardar después de cancelar
        }
    }
    public void modificarReserva(Reserva reserva) {
        // Obtener la reserva original a modificar
        Reserva reservaExistente = reservaDAO.obtenerReservaPorId(reserva.getIdReserva());
        
        if (reservaExistente != null) {
            // Actualizar los datos de la reserva existente con los nuevos valores
            reservaExistente.setFecha(reserva.getFecha());
            reservaExistente.setHora(reserva.getHora());
            reservaExistente.setEstado(reserva.getEstado());
            
            // Guardar los cambios en la base de datos
            reservaDAO.guardarReservas(); // 🔥 Guardar después de modificar
        } else {
            System.out.println("La reserva con ID " + reserva.getIdReserva() + " no existe.");
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

    public Usuario obtenerUsuarioPorCorreo(String correo) {
        return usuarioDAO.buscarPorCorreo(correo); 
    }
    public void cargarReservaDesdeArchivo() {
        reservaDAO.cargarReservas(); // Cargar los usuarios desde el archivo
    }
}

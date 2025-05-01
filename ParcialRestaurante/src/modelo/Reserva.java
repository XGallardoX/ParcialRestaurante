package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


public class Reserva implements Serializable {
    private static final long serialVersionUID = 1L; // Recomendado para Serializable
    private static int contador =1;
    private int idReserva;
    private String correoUsuario;
    private int idMesa;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado; // Confirmada, Cancelada, etc.

    public Reserva(String correoUsuario, int idMesa, LocalDate fecha, LocalTime hora, String estado) {
        this.idReserva = contador;
        this.correoUsuario = correoUsuario;
        this.idMesa = idMesa;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        contador++;
    }

    // Getters y Setters
    public int getIdReserva() { return idReserva; }
    public void setIdReserva(int idReserva) { this.idReserva = idReserva; }

    public String getCorreoUsuario() { return correoUsuario; }
    public void setCorreoUsuario(String correoUsuario) { this.correoUsuario = correoUsuario; }

    public int getIdMesa() { return idMesa; }
    public void setIdMesa(int idMesa) { this.idMesa = idMesa; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Reserva [idReserva=" + idReserva + ", correoUsuario=" + correoUsuario + ", idMesa=" + idMesa
                + ", fecha=" + fecha + ", hora=" + hora + ", estado=" + estado + "]";
    }

    
}

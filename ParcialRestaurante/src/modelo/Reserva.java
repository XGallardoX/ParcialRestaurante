package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva {
    private int idReserva;
    private int idUsuario;
    private int idMesa;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado; // Confirmada, Cancelada, etc.

    public Reserva(int idReserva, int idUsuario, int idMesa, LocalDate fecha, LocalTime hora, String estado) {
        this.idReserva = idReserva;
        this.idUsuario = idUsuario;
        this.idMesa = idMesa;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdReserva() { return idReserva; }
    public void setIdReserva(int idReserva) { this.idReserva = idReserva; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public int getIdMesa() { return idMesa; }
    public void setIdMesa(int idMesa) { this.idMesa = idMesa; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}

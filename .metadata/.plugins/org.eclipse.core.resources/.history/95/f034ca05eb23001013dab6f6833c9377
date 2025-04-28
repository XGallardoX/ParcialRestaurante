package vista;

import controlador.ReservaController;
import modelo.Reserva;
import modelo.Usuario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class VistaReserva {
    private ReservaController reservaController = new ReservaController();

    public void hacerReserva(Usuario usuario) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Hacer Reserva ===");
        System.out.print("Id de mesa (ejemplo 1): ");
        int idMesa = sc.nextInt();
        sc.nextLine();
        System.out.print("Fecha (YYYY-MM-DD): ");
        LocalDate fecha = LocalDate.parse(sc.nextLine());
        System.out.print("Hora (HH:MM): ");
        LocalTime hora = LocalTime.parse(sc.nextLine());

        reservaController.hacerReserva(usuario.getIdUsuario(), idMesa, fecha, hora);

        System.out.println("Reserva realizada exitosamente.");
    }

    public void verReservas(Usuario usuario) {
        List<Reserva> reservas = reservaController.verReservasUsuario(usuario.getIdUsuario());
        System.out.println("=== Mis Reservas ===");
        for (Reserva r : reservas) {
            System.out.println("Fecha: " + r.getFecha() + " Hora: " + r.getHora() + " Estado: " + r.getEstado());
        }
    }
}

package main;

import modelo.Usuario;
import vista.VistaLogin;
import vista.VistaReserva;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        VistaLogin vistaLogin = new VistaLogin();
        VistaReserva vistaReserva = new VistaReserva();
        Scanner sc = new Scanner(System.in);

        Usuario usuario = null;

        while (usuario == null) {
            System.out.println("¿Tienes una cuenta? (s/n)");
            String respuesta = sc.nextLine();
            if (respuesta.equalsIgnoreCase("n")) {
                vistaLogin.registrarUsuario();
            }
            usuario = vistaLogin.mostrarLogin();
        }

        int opcion;
        do {
            System.out.println("\n1. Hacer una reserva");
            System.out.println("2. Ver mis reservas");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    vistaReserva.hacerReserva(usuario);
                    break;
                case 2:
                    vistaReserva.verReservas(usuario);
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}

package vista;

import controlador.LoginController;
import modelo.Usuario;

import java.util.Scanner;

public class VistaLogin {
    private LoginController loginController = new LoginController();

    public Usuario mostrarLogin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Login ===");
        System.out.print("Correo: ");
        String correo = sc.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();
        Usuario usuario = loginController.login(correo, contrasena);

        if (usuario != null) {
            System.out.println("¡Login exitoso! Bienvenido " + usuario.getNombre());
        } else {
            System.out.println("Credenciales incorrectas.");
        }
        return usuario;
    }

    public void registrarUsuario() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Registro ===");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Correo: ");
        String correo = sc.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();
        System.out.print("Rol (Cliente/Administrador): ");
        String rol = sc.nextLine();

        Usuario nuevoUsuario = new Usuario(0, nombre, correo, contrasena, rol);
        loginController.registrarUsuario(nuevoUsuario);

        System.out.println("Usuario registrado exitosamente.");
    }
}

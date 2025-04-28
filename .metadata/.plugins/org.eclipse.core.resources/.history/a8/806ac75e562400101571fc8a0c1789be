package vista;

import controlador.LoginController;
import modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoContrasena;
    private LoginController loginController;

    public LoginView(LoginController loginController) {
        this.loginController = loginController;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Login Restaurante");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel etiquetaUsuario = new JLabel("Correo:");
        campoUsuario = new JTextField();

        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        campoContrasena = new JPasswordField();

        JButton botonLogin = new JButton("Iniciar Sesión");
        botonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = campoUsuario.getText();
                String contrasena = new String(campoContrasena.getPassword());

                Usuario usuario = loginController.login(correo, contrasena);

                if (usuario != null) {
                    JOptionPane.showMessageDialog(LoginView.this,
                            "Login exitoso! Bienvenido " + usuario.getNombre(),
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Cerramos la ventana de login
                    dispose();

                    // Abrimos la ventana de reservas
                    VistaReserva vistaReserva = new VistaReserva(usuario);
                    vistaReserva.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(LoginView.this,
                            "Correo o contraseña incorrectos.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Botón para ir a la ventana de registro
        JButton botonRegistrar = new JButton("Registrarse");
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaRegistro();  // Abrir ventana de registro
            }
        });

        panel.add(etiquetaUsuario);
        panel.add(campoUsuario);
        panel.add(etiquetaContrasena);
        panel.add(campoContrasena);
        panel.add(botonLogin);
        panel.add(botonRegistrar);  // Añadir botón de registro

        add(panel);
    }

    // Mostrar la ventana de registro
    private void mostrarVentanaRegistro() {
        RegistroView registroView = new RegistroView(loginController);
        registroView.setVisible(true);
    }
}

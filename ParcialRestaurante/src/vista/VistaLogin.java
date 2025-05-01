package vista;

import controlador.LoginController;
import controlador.ReservaController;

import modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaLogin extends JFrame {
    private LoginController loginController;
    private JTextField correoField;
    private JPasswordField contrasenaField;
    private ReservaController reservaController;

    public VistaLogin() {
        loginController = new LoginController();
        reservaController = new ReservaController();

        loginController.cargarUsuariosDesdeArchivo();
        reservaController.cargarReservaDesdeArchivo();;


        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear los componentes de la ventana
        JLabel correoLabel = new JLabel("Correo:");
        correoField = new JTextField(20);
        JLabel contrasenaLabel = new JLabel("Contraseña:");
        contrasenaField = new JPasswordField(20);
        JButton loginButton = new JButton("Iniciar Sesión");
        JButton registroButton = new JButton("Registrar");

        // Layout
        setLayout(new GridLayout(4, 2));
        add(correoLabel);
        add(correoField);
        add(contrasenaLabel);
        add(contrasenaField);
        add(loginButton);
        add(registroButton);

     // Acción de botón de login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = correoField.getText();
                String contrasena = new String(contrasenaField.getPassword());
                Usuario usuario = loginController.login(correo, contrasena);

                if (usuario != null) {
                    // Mostrar un mensaje de éxito
                    JOptionPane.showMessageDialog(null, "Login exitoso, bienvenido " + usuario.getNombre() + "!");

                    // Cerrar la ventana de login
                    dispose();  // Esto cierra la ventana actual (Login)

                    // Redirigir dependiendo del rol
                    if (usuario.getRol().equals("Administrador")) {
                        VistaReservaAdministrador vistaAdmin = new VistaReservaAdministrador(usuario);
                        vistaAdmin.setVisible(true);  // Abre la ventana de reservas administrador
                    } else if (usuario.getRol().equals("Cliente")) {
                        VistaReservaCliente vistaCliente = new VistaReservaCliente(usuario);
                        vistaCliente.setVisible(true);  // Abre la ventana de reservas cliente
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos.");
                }
            }
        });


     // Acción de botón de registro
        registroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Cierra la ventana de login actual
                RegistroView registroView = new RegistroView(loginController);  // Pasa el loginController existente
                registroView.setVisible(true);  // Muestra la ventana de registro
            }
        });

    }
}

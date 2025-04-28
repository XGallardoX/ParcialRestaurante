package vista;

import controlador.LoginController;
import modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroView extends JFrame {

    private JTextField campoNombre;
    private JTextField campoCorreo;
    private JPasswordField campoContrasena;
    private JComboBox<String> comboRol;
    private LoginController loginController;

    public RegistroView(LoginController loginController) {
        this.loginController = loginController;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Registro Usuario");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));  // Ahora hay 6 campos
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel etiquetaNombre = new JLabel("Nombre:");
        campoNombre = new JTextField();

        JLabel etiquetaCorreo = new JLabel("Correo:");
        campoCorreo = new JTextField();

        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        campoContrasena = new JPasswordField();

        JLabel etiquetaRol = new JLabel("Rol:");
        comboRol = new JComboBox<>(new String[] {"Cliente", "Administrador"});

        JButton botonRegistrar = new JButton("Registrar");
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = campoNombre.getText();
                String correo = campoCorreo.getText();
                String contrasena = new String(campoContrasena.getPassword());
                String rol = (String) comboRol.getSelectedItem();

                // Crear nuevo usuario con los atributos recibidos
                // idUsuario se podría establecer en la base de datos como autoincremental
                Usuario nuevoUsuario = new Usuario(0, nombre, correo, contrasena, rol);  // idUsuario es 0 inicialmente

                loginController.registrarUsuario(nuevoUsuario);

                JOptionPane.showMessageDialog(RegistroView.this,
                        "¡Usuario registrado con éxito!",
                        "Registro Exitoso",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();  // Cerrar la ventana de registro
            }
        });

        panel.add(etiquetaNombre);
        panel.add(campoNombre);
        panel.add(etiquetaCorreo);
        panel.add(campoCorreo);
        panel.add(etiquetaContrasena);
        panel.add(campoContrasena);
        panel.add(etiquetaRol);
        panel.add(comboRol);
        panel.add(botonRegistrar);

        add(panel);
    }
}

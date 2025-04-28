package vista;

import modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class ReservaView extends JFrame {

    private Usuario usuario;

    public ReservaView(Usuario usuario) {
        this.usuario = usuario;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Reservas - " + usuario.getNombre());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel etiquetaBienvenida = new JLabel("¡Bienvenido, " + usuario.getNombre() + "!");
        JButton botonHacerReserva = new JButton("Hacer una reserva");
        JButton botonVerReservas = new JButton("Ver mis reservas");

        botonHacerReserva.addActionListener(e -> hacerReserva());
        botonVerReservas.addActionListener(e -> verReservas());

        panel.add(etiquetaBienvenida);
        panel.add(new JLabel()); // espacio vacío
        panel.add(botonHacerReserva);
        panel.add(botonVerReservas);

        add(panel);
    }

    private void hacerReserva() {
        // Lógica para hacer una reserva
        JOptionPane.showMessageDialog(this, "Haciendo una reserva...");
    }

    private void verReservas() {
        // Lógica para ver las reservas del usuario
        JOptionPane.showMessageDialog(this, "Verificando tus reservas...");
    }
}

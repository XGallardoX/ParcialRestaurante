package vista;

import controlador.ReservaController;
import modelo.Reserva;
import modelo.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class VistaReservaAdministrador extends JFrame {
    private ReservaController reservaController;
    private Usuario usuario;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public VistaReservaAdministrador(Usuario usuario) {
        this.usuario = usuario;
        this.reservaController = new ReservaController();
        
        setTitle("Reservas Administrador");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear los botones principales
        JButton verReservasButton = new JButton("Ver Todas las Reservas");
        JButton logoutButton = new JButton("Cerrar Sesión");

        // Layout
        setLayout(new BorderLayout());
        JPanel panelBotones = new JPanel();
        panelBotones.add(verReservasButton);
        panelBotones.add(logoutButton);
        add(panelBotones, BorderLayout.NORTH);

        // Tabla vacía al inicio
        String[] columnas = {"ID", "Mesa", "Fecha", "Hora", "Estado", "Usuario", "Correo", "Modificar", "Eliminar"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);
        tabla.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);

        // Acción de ver reservas
        verReservasButton.addActionListener(e -> cargarReservas());

        // Acción de cerrar sesión
        logoutButton.addActionListener(e -> {
            dispose();
            new VistaLogin().setVisible(true);
        });

        // Acción al hacer clic en la tabla
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.rowAtPoint(e.getPoint());
                int columna = tabla.columnAtPoint(e.getPoint());

                if (columna == 7) { // Modificar
                    int idReserva = Integer.parseInt(tabla.getValueAt(fila, 0).toString());
                    modificarReserva(idReserva);
                } else if (columna == 8) { // Eliminar
                    int idReserva = Integer.parseInt(tabla.getValueAt(fila, 0).toString());
                    eliminarReserva(idReserva);
                }
            }
        });
    }

    private void cargarReservas() {
        modeloTabla.setRowCount(0); // Limpiar tabla
        List<Reserva> reservas = reservaController.obtenerTodasLasReservas();

        for (Reserva reserva : reservas) {
        	Usuario cliente = reservaController.obtenerUsuarioPorId(reserva.getIdUsuario());
            modeloTabla.addRow(new Object[]{
                    reserva.getIdReserva(),
                    reserva.getIdMesa(),
                    reserva.getFecha(),
                    reserva.getHora(),
                    reserva.getEstado(),
                    cliente != null ? cliente.getNombre() : "Desconocido",
                    cliente != null ? cliente.getCorreo() : "Desconocido",
                    "Modificar",
                    "Eliminar"
            });
        }
    }

    private void modificarReserva(int idReserva) {
        JOptionPane.showMessageDialog(this, "Aquí puedes implementar la modificación para ID: " + idReserva);
        // Aquí podrías abrir una nueva ventana para editar los datos de la reserva
    }

    private void eliminarReserva(int idReserva) {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro que quieres eliminar esta reserva?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            reservaController.eliminarReserva(idReserva);
            cargarReservas(); // Recargar tabla
            JOptionPane.showMessageDialog(this, "Reserva eliminada exitosamente.");
        }
    }
}

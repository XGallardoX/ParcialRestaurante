package vista;

import controlador.ReservaController;
import modelo.Reserva;
import modelo.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.List;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;


public class VistaReservaAdministrador extends JFrame {
    private ReservaController reservaController;
    @SuppressWarnings("unused")
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
        JButton agregarReservaButton = new JButton("Agregar Reserva");
        JButton logoutButton = new JButton("Cerrar Sesión");

        // Layout
        setLayout(new BorderLayout());
        JPanel panelBotones = new JPanel();
        panelBotones.add(verReservasButton);
        panelBotones.add(agregarReservaButton);
        panelBotones.add(logoutButton);
        add(panelBotones, BorderLayout.NORTH);

        // Tabla vacía al inicio
        String[] columnas = {"ID","Mesa", "Fecha", "Hora", "Estado", "Usuario", "Correo", "Modificar", "Eliminar"};
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

        // Acción de agregar nueva reserva
        agregarReservaButton.addActionListener(e -> agregarReserva());
    }

    private void cargarReservas() {
        modeloTabla.setRowCount(0); // Limpiar tabla
        List<Reserva> reservas = reservaController.obtenerTodasLasReservas();

        for (Reserva reserva : reservas) {
            Usuario cliente = reservaController.obtenerUsuarioPorCorreo(reserva.getCorreoUsuario());
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
        // Obtener la reserva que se quiere modificar
        Reserva reserva = reservaController.obtenerReservaPorId(idReserva);
        
        if (reserva != null) {
            // Crear una ventana de diálogo para editar la reserva
            JDialog dialog = new JDialog(this, "Modificar Reserva", true);
            dialog.setSize(400, 300);
            dialog.setLayout(new GridLayout(6, 2));
    
            // Crear los campos para editar los datos
            JTextField fechaField = new JTextField(reserva.getFecha().toString()); // LocalDate to String
            JTextField horaField = new JTextField(reserva.getHora().toString()); // LocalTime to String
            JComboBox<String> estadoComboBox = new JComboBox<>(new String[]{"Confirmada", "Cancelada", "Pendiente"});
            estadoComboBox.setSelectedItem(reserva.getEstado());
    
            // Botones de guardar y cancelar
            JButton guardarButton = new JButton("Guardar Cambios");
            JButton cancelarButton = new JButton("Cancelar");
    
            // Agregar los componentes a la ventana de diálogo
            dialog.add(new JLabel("Fecha (yyyy-MM-dd):"));
            dialog.add(fechaField);
            dialog.add(new JLabel("Hora (HH:mm):"));
            dialog.add(horaField);
            dialog.add(new JLabel("Estado:"));
            dialog.add(estadoComboBox);
            dialog.add(guardarButton);
            dialog.add(cancelarButton);
    
            // Acción de guardar cambios
            guardarButton.addActionListener(e -> {
                String nuevaFechaStr = fechaField.getText();
                String nuevaHoraStr = horaField.getText();
                String nuevoEstado = (String) estadoComboBox.getSelectedItem();
    
                try {
                    // Convertir la fecha y hora a LocalDate y LocalTime
                    LocalDate nuevaFecha = LocalDate.parse(nuevaFechaStr);
                    LocalTime nuevaHora = LocalTime.parse(nuevaHoraStr);
    
                    // Actualizar la reserva
                    reserva.setFecha(nuevaFecha);
                    reserva.setHora(nuevaHora);
                    reserva.setEstado(nuevoEstado);
    
                    // Actualizar en la base de datos (a través del controlador)
                    reservaController.modificarReserva(reserva);
                    JOptionPane.showMessageDialog(this, "Reserva modificada exitosamente.");
                    dialog.dispose(); // Cerrar el diálogo
                    cargarReservas(); // Recargar la tabla
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(this, "Formato de fecha o hora inválido. Por favor, ingrese los datos correctamente.");
                }
            });
    
            // Acción de cancelar
            cancelarButton.addActionListener(e -> dialog.dispose());
    
            // Mostrar el diálogo
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Reserva no encontrada.");
        }
    }

    private void agregarReserva() {
        // Crear una ventana de diálogo para agregar una nueva reserva
        JDialog dialog = new JDialog(this, "Agregar Nueva Reserva", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(6, 2));

        // Crear los campos para ingresar los datos
        JTextField fechaField = new JTextField(); // Para la fecha
        JTextField horaField = new JTextField(); // Para la hora
        JTextField correoField = new JTextField(); // Para el correo del usuario
        JComboBox<String> estadoComboBox = new JComboBox<>(new String[]{"Confirmada", "Cancelada", "Pendiente"});

        // Botones de guardar y cancelar
        JButton guardarButton = new JButton("Guardar Reserva");
        JButton cancelarButton = new JButton("Cancelar");

        // Agregar los componentes a la ventana de diálogo
        dialog.add(new JLabel("Fecha (yyyy-MM-dd):"));
        dialog.add(fechaField);
        dialog.add(new JLabel("Hora (HH:mm):"));
        dialog.add(horaField);
        dialog.add(new JLabel("Correo Usuario:"));
        dialog.add(correoField);
        dialog.add(new JLabel("Estado:"));
        dialog.add(estadoComboBox);
        dialog.add(guardarButton);
        dialog.add(cancelarButton);

        // Acción de guardar nueva reserva
        guardarButton.addActionListener(e -> {
            String fechaStr = fechaField.getText();
            String horaStr = horaField.getText();
            String correoUsuario = correoField.getText();
            String estado = (String) estadoComboBox.getSelectedItem();

            try {
                // Convertir la fecha y hora a LocalDate y LocalTime
                LocalDate fecha = LocalDate.parse(fechaStr);
                LocalTime hora = LocalTime.parse(horaStr);

                // Hacer la nueva reserva
                reservaController.hacerReserva(correoUsuario, 1, fecha, hora); // Asumiendo que idMesa es 1 para este ejemplo
                JOptionPane.showMessageDialog(this, "Reserva agregada exitosamente.");
                dialog.dispose(); // Cerrar el diálogo
                cargarReservas(); // Recargar la tabla
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Formato de fecha o hora inválido. Por favor, ingrese los datos correctamente.");
            }
        });

        // Acción de cancelar
        cancelarButton.addActionListener(e -> dialog.dispose());

        // Mostrar el diálogo
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
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


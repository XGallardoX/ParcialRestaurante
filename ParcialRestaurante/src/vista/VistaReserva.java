package vista;

import controlador.ReservaController;
import modelo.Reserva;
import modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class VistaReserva extends JFrame {
    private ReservaController reservaController;
    private Usuario usuario;

    public VistaReserva(Usuario usuario) {
        this.usuario = usuario;
        reservaController = new ReservaController();
        
        setTitle("Reservas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear los componentes de la ventana
        JButton hacerReservaButton = new JButton("Hacer Reserva");
        JButton verReservasButton = new JButton("Ver Mis Reservas");
        JButton logoutButton = new JButton("Cerrar Sesión");

        // Layout
        setLayout(new GridLayout(3, 1));
        add(hacerReservaButton);
        add(verReservasButton);
        add(logoutButton);

        // Acción de hacer reserva
        hacerReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hacerReserva();
            }
        });

        // Acción de ver reservas
        verReservasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verReservas();
            }
        });

        // Acción de cerrar sesión
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new VistaLogin().setVisible(true);  // Volver a la pantalla de login
            }
        });
    }

    // Método para hacer una nueva reserva
    private void hacerReserva() {
        boolean continuar = true;

        while (continuar) {
            try {
                String idMesaStr = JOptionPane.showInputDialog(this, "Introduce el ID de la mesa:");
                if (idMesaStr == null) return;

                int idMesa = Integer.parseInt(idMesaStr);

                String fechaStr = JOptionPane.showInputDialog(this, "Introduce la fecha de la reserva (MM-DD):");
                if (fechaStr == null) return;

                String horaStr = JOptionPane.showInputDialog(this, "Introduce la hora de la reserva (HH:MM):");
                if (horaStr == null) return;

                // Año actual + fecha
                int anioActual = LocalDate.now().getYear();
                LocalDate fecha = LocalDate.parse(anioActual + "-" + fechaStr);
                LocalTime hora = LocalTime.parse(horaStr);

                // Validar que no sea en el pasado
                LocalDate hoy = LocalDate.now();
                LocalTime horaActual = LocalTime.now();

                if (fecha.isBefore(hoy) || (fecha.isEqual(hoy) && hora.isBefore(horaActual))) {
                    JOptionPane.showMessageDialog(this, "No puedes reservar en el pasado. Intenta de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                // Si todo está bien
                reservaController.hacerReserva(usuario.getIdUsuario(), idMesa, fecha, hora);
                JOptionPane.showMessageDialog(this, "Reserva realizada con éxito.");

                // Preguntar si quiere otra reserva
                int respuesta = JOptionPane.showConfirmDialog(this, "¿Deseas hacer otra reserva?", "Otra reserva", JOptionPane.YES_NO_OPTION);

                if (respuesta == JOptionPane.NO_OPTION) {
                    continuar = false;
                }
                // Si dice "Sí", vuelve a iniciar el while automáticamente

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Datos inválidos. Por favor ingresa la información correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
                // Y vuelve a pedir datos
            }
        }
    }




    // Método para ver las reservas de un usuario
    private void verReservas() {
        List<Reserva> reservas = reservaController.verReservasUsuario(usuario.getIdUsuario());

        // Definimos las columnas
        String[] columnas = {"Mesa", "Fecha", "Hora", "Estado"};

        // Llenamos los datos
        String[][] datos = new String[reservas.size()][4];
        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);
            datos[i][0] = String.valueOf(reserva.getIdMesa());
            datos[i][1] = reserva.getFecha().toString();
            datos[i][2] = reserva.getHora().toString();
            datos[i][3] = reserva.getEstado();
        }

        // Crear la tabla
        JTable tabla = new JTable(datos, columnas) {
            // Pintar filas alternadas
            @Override
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(230, 230, 230)); // Blanco y gris claro
                } else {
                    c.setBackground(new Color(184, 207, 229)); // Color cuando se selecciona la fila
                }
                return c;
            }
        };

        // Ajustes extra de la tabla
        tabla.setRowHeight(25);
        tabla.setFillsViewportHeight(true);
        tabla.setGridColor(Color.LIGHT_GRAY);

        // Personalizar encabezado
        tabla.getTableHeader().setBackground(new Color(60, 60, 60));
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(tabla);

        // Mostrar en un nuevo JFrame en lugar de JOptionPane
        JFrame frame = new JFrame("Mis Reservas");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(this);
        frame.add(scrollPane);
        frame.setVisible(true);
    }


}

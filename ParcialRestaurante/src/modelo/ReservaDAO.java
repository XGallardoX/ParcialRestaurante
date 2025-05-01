package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    private static ReservaDAO instancia;
    private List<Reserva> reservas;
    private static final String CARPETA_USUARIOS = "data"; // Carpeta donde se guardará el archivo
    private static final String ARCHIVO_RESERVAS = CARPETA_USUARIOS + "/reservas.ser"; // Usamos .ser para archivos serializados
    

    // Constructor privado para implementar el patrón Singleton
    private ReservaDAO() {
        reservas = new ArrayList<>();
    }
    
 // Guardar las reservas en el archivo
    public void guardarReservas() {
        File carpeta = new File(CARPETA_USUARIOS);
        if (!carpeta.exists()) {
            carpeta.mkdir(); // Crear la carpeta si no existe
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_RESERVAS))) {
            oos.writeObject(reservas);  // Guardar la lista de reservas
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ReservaDAO getInstancia() {
        if (instancia == null) {
            instancia = new ReservaDAO();
        }
        return instancia;
    }
    @SuppressWarnings("unchecked")
    public void cargarReservas() {
            // Crear la carpeta 'data' si no existe
            File carpeta = new File(CARPETA_USUARIOS);
            if (!carpeta.exists()) {
                carpeta.mkdir(); // Crea la carpeta
            }

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_RESERVAS))) {
                reservas = (List<Reserva>) ois.readObject();  // Leer la lista de usuarios desde el archivo
            } catch (FileNotFoundException e) {
                // Si el archivo no existe, no hacemos nada, simplemente no hay usuarios cargados
                System.out.println("Archivo de usuarios no encontrado. Creando nuevo archivo.");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    // Guardar los usuarios en el archivo
    @SuppressWarnings("unused")
    private void guardarUsuariosEnArchivo() {
        // Crear la carpeta 'data' si no existe
        File carpeta = new File(CARPETA_USUARIOS);
        if (!carpeta.exists()) {
            carpeta.mkdir(); // Crea la carpeta
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_RESERVAS))) {
            oos.writeObject(reservas);  // Guardamos la lista de usuarios en el archivo
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para agregar una nueva reserva
    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    // Obtener reservas por usuario
    public List<Reserva> obtenerReservasPorUsuario(String correo) {
        List<Reserva> reservasUsuario = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva.getCorreoUsuario().equals(correo)) {
                reservasUsuario.add(reserva);
            }
        }
        System.out.println(reservasUsuario);
        return reservasUsuario;
    }

    // Obtener todas las reservas (para administradores)
    public List<Reserva> obtenerTodasLasReservas() {
        return reservas;
    }

    // Obtener una reserva por su ID
    public Reserva obtenerReservaPorId(int idReserva) {
        for (Reserva reserva : reservas) {
            if (reserva.getIdReserva() == idReserva) {
                return reserva;
            }
        }
        return null;
    }


    // Actualizar una reserva
    public void actualizarReserva(Reserva reserva) {
        // Aquí actualizas la reserva en la base de datos o en la lista
    }

    // Eliminar una reserva
    public void eliminarReserva(int idReserva) {
        Reserva reserva = obtenerReservaPorId(idReserva);
        if (reserva != null) {
            reservas.remove(reserva);
            guardarReservas();
        }
    }

    
}

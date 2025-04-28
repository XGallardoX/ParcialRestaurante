package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    private static ReservaDAO instancia;
    private List<Reserva> reservas = new ArrayList<>();
    private static final String CARPETA_RESERVAS = "data";
    private static final String ARCHIVO_RESERVAS = CARPETA_RESERVAS + "/reservas.ser";

    private ReservaDAO() {}

    public static ReservaDAO getInstancia() {
        if (instancia == null) {
            instancia = new ReservaDAO();
        }
        return instancia;
    }

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
        guardarReservas(); // Guardar automáticamente al agregar
    }

    public List<Reserva> obtenerReservasPorUsuario(int idUsuario) {
        List<Reserva> resultado = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getIdUsuario() == idUsuario) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    public void cargarReservas() {
        File carpeta = new File(CARPETA_RESERVAS);
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_RESERVAS))) {
            reservas = (List<Reserva>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de reservas no encontrado. Creando nuevo archivo.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void guardarReservas() {
        File carpeta = new File(CARPETA_RESERVAS);
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_RESERVAS))) {
            oos.writeObject(reservas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

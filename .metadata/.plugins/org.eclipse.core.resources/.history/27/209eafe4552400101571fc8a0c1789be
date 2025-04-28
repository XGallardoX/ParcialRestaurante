package modelo;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private static UsuarioDAO instancia;
    private List<Usuario> usuarios = new ArrayList<>();

    private UsuarioDAO() {}

    public static UsuarioDAO getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioDAO();
        }
        return instancia;
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario buscarPorCorreo(String correo) {
        for (Usuario u : usuarios) {
            if (u.getCorreo().equalsIgnoreCase(correo)) {
                return u;
            }
        }
        return null;
    }
}

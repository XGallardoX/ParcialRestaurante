package controlador;

import modelo.Usuario;
import modelo.UsuarioDAO;

public class LoginController {
    private UsuarioDAO usuarioDAO = UsuarioDAO.getInstancia();
    private Usuario usuarioLogueado;  // Variable para almacenar el usuario logueado

    public Usuario login(String correo, String contrasena) {
        Usuario usuario = usuarioDAO.buscarPorCorreo(correo);
        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            this.usuarioLogueado = usuario;  // Guardamos el usuario logueado
            return usuario;
        }
        return null;
    }

    // Método para obtener el usuario logueado
    public Usuario getUsuario() {
        return usuarioLogueado;
    }

    public void registrarUsuario(Usuario usuario) {
        usuarioDAO.agregarUsuario(usuario);
    }

    // Método para cargar los usuarios desde un archivo
    public void cargarUsuariosDesdeArchivo() {
        usuarioDAO.cargarUsuarios(); // Cargar los usuarios desde el archivo
    }
}

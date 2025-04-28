package controlador;

import modelo.Usuario;
import modelo.UsuarioDAO;

public class LoginController {
    private UsuarioDAO usuarioDAO = UsuarioDAO.getInstancia();

    public Usuario login(String correo, String contrasena) {
        Usuario usuario = usuarioDAO.buscarPorCorreo(correo);
        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            return usuario;
        }
        return null;
    }

    public void registrarUsuario(Usuario usuario) {
        usuarioDAO.agregarUsuario(usuario);
    }
}

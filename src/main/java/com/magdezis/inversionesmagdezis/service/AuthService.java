package main.java.com.magdezis.inversionesmagdezis.service;

import java.sql.SQLException;
import main.java.com.fammateam.gestionresidencial.security.jbcrypt.BCrypt;
import main.java.com.magdezis.inversionesmagdezis.model.Usuario;
import main.java.com.magdezis.inversionesmagdezis.repository.AuthRepository;

public class AuthService {

    private AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public boolean autenticator(String email, String password) throws SQLException {
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            return false;
        }

        try {
            Usuario usuario = authRepository.findUserByEmail(email);

            if (usuario != null && BCrypt.checkpw(password, usuario.getContrasena_hash())) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error de conexión durante el login: " + e.getMessage());
        }

        return false;
    }

    public boolean registerUser(Usuario nuevoUsuario, String contrasena) throws SQLException {
        String hashedPassword = BCrypt.hashpw(contrasena, BCrypt.gensalt());
        nuevoUsuario.setContrasena_hash(hashedPassword);
        return authRepository.saveUser(nuevoUsuario);

    }

}

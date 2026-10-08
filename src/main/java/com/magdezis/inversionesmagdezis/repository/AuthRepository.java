package main.java.com.magdezis.inversionesmagdezis.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.java.com.magdezis.inversionesmagdezis.config.ConnectionDb;
import main.java.com.magdezis.inversionesmagdezis.model.Usuario;

public class AuthRepository {

    public Usuario findUserByEmail(String email) throws SQLException {
        String sql = "select from usuarios where email = ?";

        try (PreparedStatement pstm = ConnectionDb.getConnection().prepareStatement(sql)) {
            pstm.setString(1, email);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    rs.getInt("id_usuario");
                    rs.getString("nombre");
                    rs.getString("apellido");
                    rs.getString("email");
                    rs.getString("contrasena_hash");

                }

            }
        } catch (SQLException e) {
            System.out.println("ERROR AL BUSCAR POR EMAIL: " + e.getMessage());

        }
        return null;
    }

    public boolean saveUser(Usuario usuario) throws SQLException {
        String sql = "insert into usuarios values(?,?,?,?,?)";

        try (PreparedStatement pstm = ConnectionDb.getConnection().prepareStatement(sql)) {
            pstm.setString(1, "nombre");
            pstm.setString(2, "apellido");
            pstm.setString(3, "email");
            pstm.setString(4, "email");
            pstm.setString(5, "contrasena_hash");
            return pstm.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("ERROR AL INSETAR: " + e.getMessage());
        }
        return false;
    }

}

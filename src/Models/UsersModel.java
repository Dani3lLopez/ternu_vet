package src.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersModel {
    public static List<List<String>> cargarListaUsuarios() {
        List<List<String>> listaUsuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE estado_usuario = 'Activo' ";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                List<String> usuario = new ArrayList<>();
                usuario.add(rs.getString("nombre_usuario"));
                usuario.add(rs.getString("clave_usuario"));
                usuario.add(rs.getString("estado_usuario"));
                usuario.add(rs.getString("administrador"));
                usuario.add(rs.getString("id_doctor"));

                listaUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return listaUsuarios;
    }
    public static List<List<String>> cargarListaDoctores() {
        List<List<String>> listaDoctores = new ArrayList<>();
        String sql = "SELECT * FROM doctores";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                List<String> doctor = new ArrayList<>();
                doctor.add(rs.getString("id_doctor"));
                doctor.add(rs.getString("id_persona"));
                listaDoctores.add(doctor);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return listaDoctores;
    }
}

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
    public static boolean existenciaUsuario(String nombreUsuario) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE nombre_usuario = ?";

        try (Connection conexion = ConnectionModel.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, nombreUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.out.println("Error al verificar nombre de usuario: " + e.getMessage());
        }
        return false;
    }
    public static List<List<String>> cargarDoctoresSinUsuario() {
        List<List<String>> listaDoctores = new ArrayList<>();
        String sql = "SELECT id_doctor, id_persona FROM doctores WHERE id_doctor NOT IN (SELECT id_doctor FROM usuarios)";

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
    public static int ingresarNuevoUsuario(String nombreUsuario, String claveUsuario, String estadoUsuario, Boolean administrador, String idDoctor){
        int retorno = 0;
        String sql = "INSERT INTO usuarios (nombre_usuario, clave_usuario, estado_usuario, administrador, id_doctor) VALUES (?,?,?,1,?)";
        try(
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setString(1, nombreUsuario);
            ps.setString(2, claveUsuario);
            ps.setString(3, estadoUsuario);
            ps.setString(4, idDoctor);

            retorno = ps.executeUpdate();
            return retorno;
        } catch (SQLException e) {
            System.out.println("Error al registrar datos: " + e.getMessage());
            return retorno;
        }
    }
}

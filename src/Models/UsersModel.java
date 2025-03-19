package src.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Para obtener lista de los usuarios
public class UsersModel {
    public static List<List<String>> cargarListaUsuarios() {
        List<List<String>> listaUsuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (
                // conecta a la bd y ejecuta una consulta
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                // En el bucle, pasa por cada columna y agrega el usuario creado a la lista.
                List<String> usuario = new ArrayList<>();
                usuario.add(rs.getString("id_usuario"));
                usuario.add(rs.getString("nombre_usuario"));
                usuario.add(rs.getString("clave_usuario"));
                usuario.add(rs.getString("estado_usuario"));
                usuario.add(rs.getString("administrador"));
                usuario.add(rs.getString("id_doctor"));

                listaUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            // Si el código anterior falla, muestra estre mensaje de error
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return listaUsuarios;
    }

    // Muestra la lista de los doctores
    public static List<List<String>> cargarListaDoctores() {
        List<List<String>> listaDoctores = new ArrayList<>();
        String sql = "SELECT * FROM doctores";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
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

    // Método para mostrar los datos de un usuario dado un ID
    public static List<String> cargarUsuario(String id) {
        List<String> datosUsuario = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                datosUsuario.add(rs.getString("id_usuario"));
                datosUsuario.add(rs.getString("nombre_usuario"));
                datosUsuario.add(rs.getString("clave_usuario"));
                datosUsuario.add(rs.getString("estado_usuario"));
                datosUsuario.add(rs.getString("administrador"));
                datosUsuario.add(rs.getString("id_doctor"));
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return datosUsuario;
    }

    // Verifica que un usuario existe
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
            // Si el usuario no existe, devuelve este mensaje de error
            System.out.println("Error al verificar nombre de usuario: " + e.getMessage());
        }
        return false;
    }

    // Mostrar los doctores sin un usuario
    public static List<List<String>> cargarDoctoresSinUsuario() {
        List<List<String>> listaDoctores = new ArrayList<>();
        String sql = "SELECT id_doctor, id_persona FROM doctores WHERE id_doctor NOT IN (SELECT id_doctor FROM usuarios)";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
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

    // Agregar un usuario nuevo
    public static int ingresarNuevoUsuario(String nombreUsuario, String claveUsuario, String estadoUsuario,
            Boolean administrador, String idDoctor) {
        int retorno = 0;
        String sql = "INSERT INTO usuarios (nombre_usuario, clave_usuario, estado_usuario, administrador, id_doctor) VALUES (?,?,?,?,?)";
        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            ps.setString(2, claveUsuario);
            ps.setString(3, estadoUsuario);
            ps.setBoolean(4, administrador);
            ps.setString(5, idDoctor);

            retorno = ps.executeUpdate();
            return retorno;
        } catch (SQLException e) {
            System.out.println("Error al registrar datos: " + e.getMessage());
            return retorno;
        }
    }

    // Permite actualizar los datos de un usuario
    public static int actualizarUsuario(String id, String nombreUsuario, String claveUsuario, String estadoUsuario,
            int administrador, String idDoctor) {
        String sql = "UPDATE usuarios SET nombre_usuario=?, clave_usuario=?, estado_usuario=?, administrador=?, id_doctor=? WHERE id_usuario=?";
        try (Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            ps.setString(2, claveUsuario);
            ps.setString(3, estadoUsuario);
            ps.setInt(4, administrador);
            ps.setString(5, idDoctor);
            ps.setString(6, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar persona: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    // Desactiva un usuario dado un ID
    public static int desactivarUsuario(String id) {
        String sql = "UPDATE usuarios SET estado_usuario = 'Inactivo' WHERE id_usuario=?";
        try (Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al cambiar visibilidad del registro: " + e.getMessage());
        }
        return 0;
    }
}

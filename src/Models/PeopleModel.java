package src.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeopleModel {

    public static List<List<String>> cargarListaPersonas() {
        List<List<String>> listaPersonas = new ArrayList<>();
        String sql = "SELECT * FROM personas";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                List<String> persona = new ArrayList<>();
                persona.add(rs.getString("id_persona"));
                persona.add(rs.getString("nombre_persona"));
                persona.add(rs.getString("apellido_persona"));
                persona.add(rs.getString("telefono_persona"));
                persona.add(rs.getString("email_persona"));
                persona.add(rs.getString("dui_persona"));

                listaPersonas.add(persona);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return listaPersonas;
    }

    public static List<String> cargarPersona(String id){
        List<String> datosPersona = new ArrayList<>();
        String sql = "SELECT * FROM personas WHERE id_persona = ?";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
        ) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                datosPersona.add(rs.getString("id_persona"));
                datosPersona.add(rs.getString("nombre_persona"));
                datosPersona.add(rs.getString("apellido_persona"));
                datosPersona.add(rs.getString("telefono_persona"));
                datosPersona.add(rs.getString("email_persona"));
                datosPersona.add(rs.getString("dui_persona"));
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return datosPersona;
    }

    public static int ingresarNuevaPersona(String nombre, String apellido, String telefono, String email, String dui){
        int retorno = 0;
        String sql = "INSERT INTO personas (nombre_persona, apellido_persona, telefono_persona, email_persona, dui_persona) VALUES (?,?,?,?,?)";
        try(
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, telefono);
            ps.setString(4, email);
            ps.setString(5, dui);

            retorno = ps.executeUpdate();
            return retorno;
        } catch (SQLException e) {
            System.out.println("Error al registrar datos: " + e.getMessage());
            return retorno;
        }
    }

    public static int actualizarPersona(String id, String nombre, String apellido, String telefono, String email, String dui) {
        String sql = "UPDATE personas SET nombre_persona=?, apellido_persona=?, telefono_persona=?, email_persona=?, dui_persona=? WHERE id_persona=?";
        try (Connection conexion = ConnectionModel.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, telefono);
            ps.setString(4, email);
            ps.setString(5, dui);
            ps.setString(6, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar persona: " + e.getMessage());
        }
        return 0;
    }

    public static int eliminarPersona(String id) {
        String sql = "DELETE FROM personas WHERE id_persona=?";
        try (Connection conexion = ConnectionModel.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar el registro de persona: " + e.getMessage());
        }
        return 0;
    }
}
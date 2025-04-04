package src.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Obtiene la lista de especialidades
public class SpecialtiesModel {
    public static List<List<String>> cargarListaEspecialidades() {
        List<List<String>> listaEspecialidades = new ArrayList<>();
        String sql = "SELECT * FROM especialidades";

        try (
                // conecta a la BD y ejecuta una consulta
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                // En el bucle, pasa por cada columna y las agrega a la lista.
                List<String> especialidad = new ArrayList<>();
                especialidad.add(rs.getString("id_especialidad"));
                especialidad.add(rs.getString("nombre_especialidad"));

                listaEspecialidades.add(especialidad);
            }
        } catch (SQLException e) {
            // Si el código anterior da error, muestra este mensaje
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return listaEspecialidades;
    }

    // Agrega una nueva especialidad
    public static int ingresarNuevaEspecialidad(String nombreEspecialidad) {
        int retorno = 0;
        String sql = "INSERT INTO especialidades (nombre_especialidad) VALUES (?)";
        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombreEspecialidad);

            retorno = ps.executeUpdate();
            return retorno;
        } catch (SQLException e) {
            System.out.println("Error al registrar datos: " + e.getMessage());
            return retorno;
        }
    }

    // Para eliminar una especialidad
    public static int eliminarEspecialidad(String id) {
        String sql = "DELETE FROM especialidades WHERE id_especialidad=?";
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

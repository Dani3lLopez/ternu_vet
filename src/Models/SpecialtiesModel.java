package src.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecialtiesModel {
    public static List<List<String>> cargarListaEspecialidades() {
        List<List<String>> listaEspecialidades = new ArrayList<>();
        String sql = "SELECT * FROM especialidades";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                List<String> especialidad = new ArrayList<>();
                especialidad.add(rs.getString("id_especialidad"));
                especialidad.add(rs.getString("nombre_especialidad"));

                listaEspecialidades.add(especialidad);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return listaEspecialidades;
    }
}

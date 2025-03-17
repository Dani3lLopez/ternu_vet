package src.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicesModel {
    public static List<List<String>> cargarListaServicios() {
        List<List<String>> listaServicios = new ArrayList<>();
        String sql = "SELECT id_servicio, nombre_servicio, descripcion_servicio, precio_servicio FROM servicios WHERE visibilidad_servicio = 1";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                List<String> servicio = new ArrayList<>();
                servicio.add(rs.getString("id_servicio"));
                servicio.add(rs.getString("nombre_servicio"));
                servicio.add(rs.getString("descripcion_servicio"));
                servicio.add(rs.getString("precio_servicio"));

                listaServicios.add(servicio);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return listaServicios;
    }

    public static int ingresarServicio(String nombreServicio, String descripcionServicio, double precioServicio) {
        int retorno = 0;
        String sql = "INSERT INTO servicios (nombre_servicio, descripcion_servicio, precio_servicio) VALUES (?,?,?)";
        try(
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setString(1, nombreServicio);
            ps.setString(2, descripcionServicio);
            ps.setDouble(3, precioServicio);

            retorno = ps.executeUpdate();
            return retorno;
        } catch (SQLException e) {
            System.out.println("Error al registrar datos: " + e.getMessage());
            return retorno;
        }
    }

    public static List<String> cargarServicio(String id){
        List<String> datosServicio = new ArrayList<>();
        String sql = "SELECT * FROM servicios WHERE id_servicio = ?";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
        ) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                datosServicio.add(rs.getString("id_servicio"));
                datosServicio.add(rs.getString("nombre_servicio"));
                datosServicio.add(rs.getString("descripcion_servicio"));
                datosServicio.add(rs.getString("precio_servicio"));
                datosServicio.add(rs.getString("visibilidad_servicio"));
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return datosServicio;
    }

    public static int actualizarServicio(String id, String nombreServicio, String descripcionServicio, double precioServicio) {
        String sql = "UPDATE servicios SET nombre_servicio=?, descripcion_servicio=?, precio_servicio=? WHERE id_servicio=?";
        try (Connection conexion = ConnectionModel.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombreServicio);
            ps.setString(2, descripcionServicio);
            ps.setDouble(3, precioServicio);
            ps.setString(4, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar el servicio: " + e.getMessage());
        }
        return 0;
    }

    public static int eliminarServicio(String id) {
        String sql = "UPDATE servicios SET visibilidad_servicio=0 WHERE id_servicio=?";
        try (Connection conexion = ConnectionModel.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar el registro del servicio: " + e.getMessage());
        }
        return 0;
    }
}

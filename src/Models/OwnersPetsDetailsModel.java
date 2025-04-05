package src.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * Modelo de detalle de propietarios con mascotas
 * Este modelo contiene métodos para cargar listas de registros de detalle, mascotas y propietarios desde
 * la base de datos con la tabla "detalle_propietarios_mascotas y las tablas relacionadas
 */
public class OwnersPetsDetailsModel {

    /*
     * Carga la lista de detalles de propietarios con mascotas
     * Retorna una lista de listas. Cada sublista contiene datos de un registro en la tabla dicha
     */
    public static List<List<String>> cargarListaDetalles() {
        List<List<String>> listaDetalles = new ArrayList<>();
        // Query para obtener los registros
        String sql = "SELECT * FROM detalle_propietarios_mascotas";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                List<String> detalle = new ArrayList<>();
                detalle.add(rs.getString("id_detalle_propietario_mascota"));
                detalle.add(rs.getString("tipo_propietario"));
                detalle.add(rs.getString("id_mascota"));
                detalle.add(rs.getString("id_propietario"));

                listaDetalles.add(detalle);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return listaDetalles;
    }
    /*
     * Carga la lista de mascotas activas
     * Retorna una lista de listas. Cada sublista con datos de las mascotas con visibilidad activa
     */
    public static List<List<String>> cargarListaMascotas() {
        List<List<String>> listaMascotas = new ArrayList<>();
        String sql = "SELECT id_mascota, nombre_mascota, color_mascota, peso_mascota, unidad_peso_mascota, genero_mascota, codigo_chip_mascota, estado_reproductivo_mascota, fecha_nacimiento_mascota, talla_mascota, fallecimiento_mascota, razon_fallecimiento FROM mascotas WHERE visibilidad_mascota = 1";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                List<String> mascota = new ArrayList<>();
                mascota.add(rs.getString("id_mascota"));
                mascota.add(rs.getString("nombre_mascota"));
                mascota.add(rs.getString("color_mascota"));
                mascota.add(rs.getString("peso_mascota"));
                mascota.add(rs.getString("unidad_peso_mascota"));
                mascota.add(rs.getString("genero_mascota"));
                mascota.add(rs.getString("codigo_chip_mascota"));
                mascota.add(rs.getString("estado_reproductivo_mascota"));
                mascota.add(rs.getString("fecha_nacimiento_mascota"));
                mascota.add(rs.getString("talla_mascota"));
                mascota.add(rs.getString("fallecimiento_mascota"));
                mascota.add(rs.getString("razon_fallecimiento"));

                listaMascotas.add(mascota);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return listaMascotas;
    }

    /*
     * Carga la lista de propietarios
     * Retorna una lista de listas. Cada sublista contiene datos de un propietario
     */
    public static List<List<String>> cargarListaPropietarios() {
        List<List<String>> listaPropietarios = new ArrayList<>();
        // Query para obtener los registros de los propietarios
        String sql = "SELECT * FROM propietarios";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                List<String> propietario = new ArrayList<>();
                propietario.add(rs.getString("id_propietario"));
                propietario.add(rs.getString("id_persona"));
                propietario.add(rs.getString("id_ciudad"));
                propietario.add(rs.getString("direccion"));
                propietario.add(rs.getString("visibilidad_propietario"));

                listaPropietarios.add(propietario);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return listaPropietarios;
    }

    public static List<String> cargarDetalle(String id){
        List<String> datosDetalle = new ArrayList<>();
        String sql = "SELECT * FROM detalle_propietarios_mascotas WHERE id_detalle_propietario_mascota = ?";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
        ) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                datosDetalle.add(rs.getString("id_detalle_propietario_mascota"));
                datosDetalle.add(rs.getString("tipo_propietario"));
                datosDetalle.add(rs.getString("id_mascota"));
                datosDetalle.add(rs.getString("id_propietario"));
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return datosDetalle;
    }
    public static int ingresarNuevoDetalle(String tipoPropietario, String idMascota, String idPropietario){
        int retorno = 0;
        String sql = "INSERT INTO detalle_propietarios_mascotas (tipo_propietario, id_mascota, id_propietario) VALUES (?,?,?)";
        try(
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setString(1, tipoPropietario);
            ps.setString(2, idMascota);
            ps.setString(3, idPropietario);

            retorno = ps.executeUpdate();
            return retorno;
        } catch (SQLException e) {
            System.out.println("Error al registrar datos: " + e.getMessage());
            return retorno;
        }
    }
    public static int actualizarDetalle(String id, String tipoPropietario, String idMascota, String idPropietario) {
        String sql = "UPDATE detalle_propietarios_mascotas SET tipo_propietario=?, id_mascota=?, id_propietario=? WHERE id_detalle_propietario_mascota=?";
        try (Connection conexion = ConnectionModel.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, tipoPropietario);
            ps.setString(2, idMascota);
            ps.setString(3, idPropietario);
            ps.setString(4, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar detalle: " + e.getMessage());
        }
        return 0;
    }
    public static int eliminarDetalle(String id) {
        String sql = "DELETE FROM detalle_propietarios_mascotas WHERE id_detalle_propietario_mascota=?";
        try (Connection conexion = ConnectionModel.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar el registro: " + e.getMessage());
        }
        return 0;
    }
}

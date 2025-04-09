package src.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ConsultationsModel: Clase que gestiona todos los procesos relacionados con las consultas
 * @author TernuVet-DevTeam
 * @version 1.0
 */
public class PetsModel {

    /**
     * Carga todas las mascotas registradas en la base de datos
     * @return una lista de listas con los datos de las mascotas
     */
    public static List<List<String>> cargarListaMascotas() {
        List<List<String>> listaMascotas = new ArrayList<>();
        String sql = "SELECT id_mascota, nombre_mascota, color_mascota, peso_mascota, unidad_peso_mascota, genero_mascota, codigo_chip_mascota, estado_reproductivo_mascota, fecha_nacimiento_mascota, talla_mascota, fallecimiento_mascota, razon_fallecimiento FROM mascotas WHERE visibilidad_mascota = 1";

        try (
                Connection conexion = ConnectionModel.conectar(); // conexion a bd
                PreparedStatement ps = conexion.prepareStatement(sql); // para la preparación de las consultas SQL
                // Hace la consulta para obtener un resultado
                ResultSet rs = ps.executeQuery()) {
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

                listaMascotas.add(mascota); // agrega la mascota a la lista
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }
        // para devolver la lista de mascotas
        return listaMascotas;
    }

    /**
     * Registra una nueva mascota en la base de datos
     * @param nombreMascota nombre de la mascota
     * @param colorMascota color de la mascota
     * @param pesoMascota peso de la mascota
     * @param unidadPesoMascota unidad de peso especificado para la mascota
     * @param generoMascota género de la mascota
     * @param codigoChipMascota codigo de chip (opcional)
     * @param estadoReproductivoMascota estado reproductivo de la mascota
     * @param fechaNacimientoMascota fecha de nacimiento de la mascota
     * @param tallaMascota talla de la mascota (tamaño)
     * @return numero de filas afectadas
     */
    public static int ingresarNuevaMascota(String nombreMascota, String colorMascota, double pesoMascota, String unidadPesoMascota, String generoMascota, String codigoChipMascota, String estadoReproductivoMascota, String fechaNacimientoMascota, String tallaMascota){
        int retorno = 0;
        String sql;

        // construye la consulta dependiendo si el peso es nulo o no
        if (unidadPesoMascota == null) {
            sql = "INSERT INTO mascotas (nombre_mascota, color_mascota, peso_mascota, genero_mascota, codigo_chip_mascota, estado_reproductivo_mascota, fecha_nacimiento_mascota, talla_mascota) VALUES (?,?,?,?,?,?,?,?)";
        } else {
            sql = "INSERT INTO mascotas (nombre_mascota, color_mascota, peso_mascota, genero_mascota, codigo_chip_mascota, estado_reproductivo_mascota, fecha_nacimiento_mascota, talla_mascota, unidad_peso_mascota) VALUES (?,?,?,?,?,?,?,?,?)";
        }
        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombreMascota);
            ps.setString(2, colorMascota);
            ps.setDouble(3, pesoMascota);
            ps.setString(4, generoMascota);
            ps.setString(5, codigoChipMascota);
            ps.setString(6, estadoReproductivoMascota);
            ps.setString(7, fechaNacimientoMascota);
            ps.setString(8, tallaMascota);

            if (unidadPesoMascota != null) {
                ps.setString(9, unidadPesoMascota);
            }
            // para obtener el numero de las filas afectadas
            retorno = ps.executeUpdate();
            return retorno;
        } catch (SQLException e) {
            System.out.println("Error al registrar datos: " + e.getMessage());
            return retorno;
        }
    }

    /**
     * Carga los datos de una mascota en específico según su id
     * @param id id de la mascota
     * @return los datos de la mascota
     */
    public static List<String> cargarMascota(String id){
        List<String> datosMascota = new ArrayList<>();
        String sql = "SELECT * FROM mascotas WHERE id_mascota = ?";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                datosMascota.add(rs.getString("id_mascota"));
                datosMascota.add(rs.getString("nombre_mascota"));
                datosMascota.add(rs.getString("color_mascota"));
                datosMascota.add(rs.getString("peso_mascota"));
                datosMascota.add(rs.getString("unidad_peso_mascota"));
                datosMascota.add(rs.getString("genero_mascota"));
                datosMascota.add(rs.getString("codigo_chip_mascota"));
                datosMascota.add(rs.getString("estado_reproductivo_mascota"));
                datosMascota.add(rs.getString("fecha_nacimiento_mascota"));
                datosMascota.add(rs.getString("talla_mascota"));
                datosMascota.add(rs.getString("fallecimiento_mascota"));
                datosMascota.add(rs.getString("razon_fallecimiento"));
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return datosMascota;
    }

    /**
     * Actualiza el registro existente de una mascota
     * @param id id de la mascota
     * @param nombreMascota nuevo nombre de la mascota
     * @param colorMascota nuevo color de la mascota
     * @param pesoMascota nuevo peso de la mascota
     * @param unidadPesoMascota nueva unidad de peso para la mascota
     * @param generoMascota nuevo género de la mascota
     * @param codigoChipMascota nuevo codigo de chip
     * @param estadoReproductivoMascota nuevo estado reproductivo
     * @param fechaNacimientoMascota nueva fecha de nacimiento de la mascota
     * @param tallaMascota nueva talla para la mascota
     * @param fallecimiento_mascota mascota fallecida (true, false)
     * @param razon_fallecimiento razon de fallecimiento de la mascota
     * @return numero de filas afectadas
     */
    public static int actualizarMascota(String id, String nombreMascota, String colorMascota, double pesoMascota, String unidadPesoMascota, String generoMascota, String codigoChipMascota, String estadoReproductivoMascota, String fechaNacimientoMascota, String tallaMascota, boolean fallecimiento_mascota, String razon_fallecimiento) {
        String sql = "UPDATE mascotas SET nombre_mascota=?, color_mascota=?, peso_mascota=?, unidad_peso_mascota=?, genero_mascota=?, codigo_chip_mascota=?, estado_reproductivo_mascota=?, fecha_nacimiento_mascota=?, talla_mascota=?, fallecimiento_mascota=?, razon_fallecimiento=? WHERE id_mascota=?";
        try (Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombreMascota);
            ps.setString(2, colorMascota);
            ps.setDouble(3, pesoMascota);
            ps.setString(4, unidadPesoMascota);
            ps.setString(5, generoMascota);
            ps.setString(6, codigoChipMascota);
            ps.setString(7, estadoReproductivoMascota);
            ps.setString(8, fechaNacimientoMascota);
            ps.setString(9, tallaMascota);
            ps.setBoolean(10, fallecimiento_mascota);
            ps.setString(11, razon_fallecimiento);
            ps.setString(12, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar mascota: " + e.getMessage());
        }
        return 0;
    }

    /**
     * Actualiza la visibilidad del registro de la mascota (simula una eliminación)
     * @param id id de la mascota
     * @return numero de filas afectadas
     */
    public static int eliminarMascota(String id) {
        String sql = "UPDATE mascotas SET visibilidad_mascota = false WHERE id_mascota=?";
        try (Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar el registro de mascota: " + e.getMessage());
        }
        return 0;
    }
}

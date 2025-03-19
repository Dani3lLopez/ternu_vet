package src.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PetsModel {
    // carga lista de mascotas. Crea una cadena de listas, donde la interna
    // representa una mascota
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

    // crear nueva mascota
    public static int ingresarNuevaMascota(String nombreMascota, String colorMascota, double pesoMascota,
            String unidadPesoMascota, String generoMascota, String codigoChipMascota, String estadoReproductivoMascota,
            String fechaNacimientoMascota, String tallaMascota) {
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

    // carga los datos de una mascota
    public static List<String> cargarMascota(String id) {
        List<String> datosMascota = new ArrayList<>();
        String sql = "SELECT * FROM mascotas WHERE id_mascota = ?";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);) {
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

    // actualiza los detos de una mascota que ya existe
    public static int actualizarMascota(String id, String nombreMascota, String colorMascota, double pesoMascota,
            String unidadPesoMascota, String generoMascota, String codigoChipMascota, String estadoReproductivoMascota,
            String fechaNacimientoMascota, String tallaMascota, boolean fallecimiento_mascota,
            String razon_fallecimiento) {
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

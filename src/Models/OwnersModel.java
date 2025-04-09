package src.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * OwnersModel: Clase que gestiona todos los procesos relacionados con los propietarios
 * @author TernuVet-DevTeam
 * @version 1.0
 */
public class OwnersModel {
    /**
     * Carga todos los propietarios almacenados en la base de datos
     * @return una lista de listas de los propietarios almacenados en la base de datos
     */
    public static List<List<String>> cargarListaPropietarios() {
        List<List<String>> listaPropietarios = new ArrayList<>();
        // Querry parar obtener los propetiarios activos
        String sql = "SELECT * FROM propietarios WHERE visibilidad_propietario = 1";

        try (
                // Hace la conexion y ejecuta la query
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            // Itera cada registro obtenido
            while (rs.next()) {
                List<String> propietario = new ArrayList<>();
                propietario.add(rs.getString("id_propietario"));
                propietario.add(rs.getString("id_persona"));
                propietario.add(rs.getString("id_ciudad"));
                propietario.add(rs.getString("direccion"));
                propietario.add(rs.getString("visibilidad_propietario"));

                // Agrega el registro a la lista de propietarios
                listaPropietarios.add(propietario);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return listaPropietarios;
    }

    /**
     * Carga todas las ciudades almacenadas en la base de datos
     * @return una lista de listas de las ciudades almacenadas en la base de datos
     */
    public static List<List<String>> cargarListaCiudades() {
        List<List<String>> listaCiudades = new ArrayList<>();
        // Query para obtener todas las ciudades
        String sql = "SELECT * FROM ciudades";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                List<String> ciudad = new ArrayList<>();
                ciudad.add(rs.getString("id_ciudad"));
                ciudad.add(rs.getString("nombre_ciudad"));
                ciudad.add(rs.getString("id_departamento"));

                listaCiudades.add(ciudad);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return listaCiudades;
    }

    /**
     * Carga los datos de un propietario especificado por su id
     * @param id id del propietario
     * @return los datos del propietario
     */
    public static List<String> cargarPropietario(String id){
        List<String> datosPropietario = new ArrayList<>();
        String sql = "SELECT * FROM propietarios WHERE id_propietario = ?";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
        ) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                datosPropietario.add(rs.getString("id_propietario"));
                datosPropietario.add(rs.getString("id_persona"));
                datosPropietario.add(rs.getString("id_ciudad"));
                datosPropietario.add(rs.getString("direccion"));
                datosPropietario.add(rs.getString("visibilidad_propietario"));
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return datosPropietario;
    }
  
    /**
     * Registra un nuevo propietario en la base de datos
     * @param idPersona id de la persona propietario
     * @param idCiudad id de la ciudad
     * @param direccion direccion del propietario
     * @param visibilidadPropietario visibilidad del registro (true por defecto)
     * @return numero de filas afectadas
     */
    public static int ingresarNuevoPropietario(String idPersona, String idCiudad, String direccion, Boolean visibilidadPropietario){
        int retorno = 0;
        String sql = "INSERT INTO propietarios (id_persona, id_ciudad, direccion, visibilidad_propietario) VALUES (?,?,?,1)";
        try(
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setString(1, idPersona);
            ps.setString(2, idCiudad);
            ps.setString(3, direccion);

            retorno = ps.executeUpdate();
            return retorno;
        } catch (SQLException e) {
            System.out.println("Error al registrar datos: " + e.getMessage());
            return retorno;
        }
    }

    /**
     * Actualiza el registro de un propietario especifico
     * @param id del propietario a actualizar
     * @param idPersona nuevo id de persona
     * @param idCiudad nueva ciudad
     * @param direccion nueva direccion
     * @param visibilidadPropietario nueva visibilidad
     * @return numero de filas afectadas
     */
    public static int actualizarPropietario(String id, String idPersona, String idCiudad, String direccion, Boolean visibilidadPropietario) {
        String sql = "UPDATE propietarios SET id_persona=?, id_ciudad=?, direccion=?, visibilidad_propietario=? WHERE id_propietario=?";
        try (Connection conexion = ConnectionModel.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, idPersona);
            ps.setString(2, idCiudad);
            ps.setString(3, direccion);
            ps.setBoolean(4, visibilidadPropietario);
            ps.setString(5, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar persona: " + e.getMessage());
        }
        return 0;
    }

    /**
     * Actualiza la visibilidad de un propietario (simula una eliminacion)
     * @param id del registro a actualizar
     * @return numero de filas afectadas
     */
    public static int desactivarPropietario(String id) {
        String sql = "UPDATE propietarios SET visibilidad_propietario = 0 WHERE id_propietario=?";
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

package src.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * Este modelo se encarga de gestionar el acceso a datos de la entidad Owners
 * Estos son los propetiarios de las mascotas. Incluye metodos para cargar, insertar, actualizar
 * y eliminar registros de la tabla "propietarios" de la base de datos
 */
public class OwnersModel {

    /*
     * Carga la lista de los propetiarios activos de la base de datos
     * Retorna una lsita de listas. Cada sublista tiene datos del propetario individual
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

    /*
     * Carga la lista de ciudades que están disponibles de la tabla "ciudades"
     * Retorna una lista de lsitas. Las sublistas son datos de ciudades
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
    /*
     * Carga los datos de un propetiario en especifico con base a su id
     * El parametro es el identificador del propietario
     * Retorna una lsira de datos del propetiario
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
    /*
     * Inserta un nuevo propetiario a la base de datos
     * La visibilidad es 1 de forma predeterminada
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
    /*
     * Actualiza los datos de el propietario existente
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
    /*
     * Desactiva un propietario, es decir le cambia la visibilidad a 0
     * Esto para eliminarlo
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

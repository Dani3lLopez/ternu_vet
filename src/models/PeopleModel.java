package src.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * PeopleModel: Clase que gestiona todos los procesos relacionados con las personas
 * @author TernuVet-DevTeam
 * @version 1.0
 */
public class PeopleModel {
    /**
     * Carga a todas las personas registradas en la base de datos
     * @return una lista de listas con los datos de todas las personas registras en la base de datos
     */
    public static List<List<String>> cargarListaPersonas() {
        List<List<String>> listaPersonas = new ArrayList<>();
        // Query para obtener los datos de la tabla "personas"
        String sql = "SELECT DISTINCT(dui_persona), nombre_persona, apellido_persona, telefono_persona, email_persona, id_persona FROM personas";

        // Se garantiza un manejo de cierre de recursos automatico con una estructura try-with-resources
        try (
                // Conexion con la base de datos
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                //Realiza la query y guarda los resultados
                ResultSet rs = ps.executeQuery()
        ) {
            // Itera cada registro del ResultSet
            while (rs.next()) {
                // Agrega los campos al listado (sublistado) de persona
                List<String> persona = new ArrayList<>();
                persona.add(rs.getString("id_persona"));
                persona.add(rs.getString("nombre_persona"));
                persona.add(rs.getString("apellido_persona"));
                persona.add(rs.getString("telefono_persona"));
                persona.add(rs.getString("email_persona"));
                persona.add(rs.getString("dui_persona"));

                // Agrega la persona a la lista general de personas
                listaPersonas.add(persona);
            }
        } catch (SQLException e) {
            // Mensaje informativo en caso de error
            System.out.println("Error al leer datos: " + e.getMessage());
        }
        // Lista completa de personas
        return listaPersonas;
    }

    /**
     * Carga los datos de una persona especificada por su id
     * @param id id de la persona
     * @return los datos de la persona
     */
    public static List<String> cargarPersona(String id){
        List<String> datosPersona = new ArrayList<>();
        //Query para seleccionar los datos del registro de persona con base a su ID
        String sql = "SELECT * FROM personas WHERE id_persona = ?";

        // Manejo de cierre de recursos automatico
        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ) {
            // Establece el valor del parametro (id) para la consulta
            ps.setString(1, id);
            // Realzia la consulta y obtiene el resultado
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Si la persona existe, se añaden sus datos a la lista
                datosPersona.add(rs.getString("id_persona"));
                datosPersona.add(rs.getString("nombre_persona"));
                datosPersona.add(rs.getString("apellido_persona"));
                datosPersona.add(rs.getString("telefono_persona"));
                datosPersona.add(rs.getString("email_persona"));
                datosPersona.add(rs.getString("dui_persona"));
            }
        } catch (SQLException e) {
            // Mensaje si ocurre error
            System.out.println("Error al leer datos: " + e.getMessage());
        }
        // Lista con datos de la persona
        return datosPersona;
    }

    /**
     * Registra una nueva persona en la base de datos
     * @param nombre nombre de la persona
     * @param apellido apellido de la persona
     * @param telefono telefono de la persona
     * @param email email de la persona
     * @param dui dui de la persona
     * @return numero de filas afectadas
     */
    public static int ingresarNuevaPersona(String nombre, String apellido, String telefono, String email, String dui){
        int retorno = 0;
        // Query para insertar un nuevo registro en la tabla "personas" de la base de datos
        String sql = "INSERT INTO personas (nombre_persona, apellido_persona, telefono_persona, email_persona, dui_persona) VALUES (?,?,?,?,?)";

        // Manejo de cierre de recursos automatico
        try(
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ){
            // Parametros de la query
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, telefono);
            ps.setString(4, email);
            ps.setString(5, dui);

            // Ejecuta la query y guarda el número de filas afectadas
            retorno = ps.executeUpdate();
            return retorno;
        } catch (SQLException e) {
            // Mensaje en caso de error
            System.out.println("Error al registrar datos: " + e.getMessage());
            return retorno;
        }
    }

    /**
     * Actualiza un registro de persona existente
     * @param id id del registro de la persona
     * @param nombre nuevo nombre de la persona
     * @param apellido nuevo apellido de la persona
     * @param telefono nuevo telefono de la persona
     * @param email nuevo email de la persona
     * @param dui nuevo dui de la persona
     * @return numero de filas afectadas
     */
    public static int actualizarPersona(String id, String nombre, String apellido, String telefono, String email, String dui) {
        // Querry para actualizar los datos de la persona
        String sql = "UPDATE personas SET nombre_persona=?, apellido_persona=?, telefono_persona=?, email_persona=?, dui_persona=? WHERE id_persona=?";
        // Manejo de cierre de recursos automatico
        try (Connection conexion = ConnectionModel.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, telefono);
            ps.setString(4, email);
            ps.setString(5, dui);
            ps.setString(6, id);

            // Ejecuta la query y devuelve la cantidad de filas afectadas
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar persona: " + e.getMessage());
        }
        // En caso de error, se retorna 0
        return 0;
    }

    /**
     * Elimina el registro de una persona de la base de datos
     * @param id id del registro a eliminar
     * @return numero de filas afectadas
     */
    public static int eliminarPersona(String id) {
        // Querry para eliminar la persona de la base de datos
        String sql = "DELETE FROM personas WHERE id_persona=?";
        try (Connection conexion = ConnectionModel.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            // Establece el parametro para la querry con el ID
            ps.setString(1, id);
            // Realiza la eliminación de la persona y devuelve el número de filas. Se espera 1 si hubo exito
            return ps.executeUpdate();
        } catch (SQLException e) {
            // En caso de error, se informa y retorna 0
            System.out.println("Error al eliminar el registro de persona: " + e.getMessage());
        }
        return 0;
    }
}
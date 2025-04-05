package src.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * Modelo de doctores: maneja las operaciones CRUD en la base de datos
 * Este modelo contiene métodos para cargar, crear y actualizar registros de doctores
 * sobre la tabla "doctores" de la base de datos
 */
public class DoctorsModel {

    /*
     * Carga la lista de los doctores registrados en la base de datos
     * Retorna una lista de listas. Cada sublista contiene los datos de un doctor
     */
    public static List<List<String>> cargarListaDoctores() {
        List<List<String>> listaDoctores = new ArrayList<>();
        // Query para obtener los datos de la tabla "doctores"
        String sql = "SELECT * FROM doctores";

        try (
                // Conexion con la base de datos
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                //Realiza la query y guarda los resultados
                ResultSet rs = ps.executeQuery()
        ) {

            // Itera cada registro del ResultSet
            while (rs.next()) {
                // Agrega los campos al la sublista de doctores
                List<String> doctor = new ArrayList<>();
                doctor.add(rs.getString("id_doctor"));
                doctor.add(rs.getString("fecha_contratacion_doctor"));
                doctor.add(rs.getString("fecha_nacimiento_doctor"));
                doctor.add(rs.getString("id_persona"));
                doctor.add(rs.getString("id_especialidad"));

                // Agrega al doctor a la lista general de doctores
                listaDoctores.add(doctor);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }
        // Lista completa de doctores
        return listaDoctores;
    }

    /*
     * Carga la lista de las especialidades en la base de datos
     * Retorna una lista de listas. Cada sublista son los datos de cada especialidad
     */
    public static List<List<String>> cargarListaEspecialidades() {
        List<List<String>> listaEspecialidades = new ArrayList<>();
        // Query para obtener las especialidades
        String sql = "SELECT * FROM especialidades";

        try (
                // Se conecta con la base de datos, se realiza la query y se guardan los resultados
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                List<String> especialidad = new ArrayList<>();
                especialidad.add(rs.getString("id_especialidad"));
                especialidad.add(rs.getString("nombre_especialidad"));
                // Agrega el registro a la lista
                listaEspecialidades.add(especialidad);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return listaEspecialidades;
    }

    /*
     * Carga los datos de un doctor con base a su id
     * El parametro id es el identificador del doctor
     * Retorna una lista con los datos del doctor
     */
    public static List<String> cargarDoctor(String id){
        List<String> datosDoctor = new ArrayList<>();
        String sql = "SELECT * FROM doctores WHERE id_doctor = ?";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
        ) {
            // Establece el valor del parametro (id) para la consulta
            ps.setString(1, id);
            // Realzia la consulta y obtiene el resultado
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                datosDoctor.add(rs.getString("id_doctor"));
                datosDoctor.add(rs.getString("fecha_contratacion_doctor"));
                datosDoctor.add(rs.getString("fecha_nacimiento_doctor"));
                datosDoctor.add(rs.getString("id_persona"));
                datosDoctor.add(rs.getString("id_especialidad"));
            }
        } catch (SQLException e) {
            // Mensaje si ocurre error
            System.out.println("Error al leer datos: " + e.getMessage());
        }
        // Lista con datos del doctor
        return datosDoctor;
    }

    /*
     * Inserta un nuevo doctor a la base de datos
     * Retorna el numero de filas afectadas al realizar la query. Se espera 1 si sí se insertó
     */
    public static int ingresarNuevoDoctor(String fechaContratacion, String fechaNacimiento, String idPersona, String idEspecialidad){
        int retorno = 0;
        // Query para insertar un nuevo registro en la tabla "doctores" de la base de datos
        String sql = "INSERT INTO doctores (fecha_contratacion_doctor, fecha_nacimiento_doctor, id_persona, id_especialidad) VALUES (?,?,?,?)";
        try(
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)){
            // Parametros de la query
            ps.setString(1, fechaContratacion);
            ps.setString(2, fechaNacimiento);
            ps.setString(3, idPersona);
            ps.setString(4, idEspecialidad);
            // Ejecuta la query y guarda el numero de filas afectadas
            retorno = ps.executeUpdate();
            return retorno;
        } catch (SQLException e) {
            System.out.println("Error al registrar datos: " + e.getMessage());
            return retorno;
        }
    }

    /*
     * Actualiza los datos de un doctor de la base de datos
     * Retorna el numero de filas afectas
     */
    public static int actualizarDoctor(String id, String fechaContratacion, String fechaNacimiento, String idPersona, String idEspecialidad) {
        // Querry para actualizar los datos del doctor
        String sql = "UPDATE doctores SET fecha_contratacion_doctor=?, fecha_nacimiento_doctor=?, id_persona=?, id_especialidad=? WHERE id_doctor=?";
        // Manejo de cierre de recursos automatico
        try (Connection conexion = ConnectionModel.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, fechaContratacion);
            ps.setString(2, fechaNacimiento);
            ps.setString(3, idPersona);
            ps.setString(4, idEspecialidad);
            ps.setString(5, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar persona: " + e.getMessage());
        }
        return 0;
    }
}

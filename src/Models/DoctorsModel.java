package src.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DoctorsModel: Clase que gestiona todos los procesos relacionados con los doctores
 * @author TernuVet-DevTeam
 * @version 1.0
 */
public class DoctorsModel {
    /**
     * Carga una lista con todos los doctores registrados en la base de datos
     * @return una lista de listas de los doctores
     */
    public static List<List<String>> cargarListaDoctores() {
        List<List<String>> listaDoctores = new ArrayList<>();
        String sql = "SELECT * FROM doctores";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                List<String> doctor = new ArrayList<>();
                doctor.add(rs.getString("id_doctor"));
                doctor.add(rs.getString("fecha_contratacion_doctor"));
                doctor.add(rs.getString("fecha_nacimiento_doctor"));
                doctor.add(rs.getString("id_persona"));
                doctor.add(rs.getString("id_especialidad"));

                listaDoctores.add(doctor);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return listaDoctores;
    }

    /**
     * Carga una lista con todas las especialidades disponibles para los doctores
     * @return una lista de listas de las especialidades disponibles
     */
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

    /**
     * Carga una lista con los datos de un doctor especifico segun su id
     * @param id id del doctor
     * @return una lista con los datos del doctor especificado segun su id
     */
    public static List<String> cargarDoctor(String id){
        List<String> datosDoctor = new ArrayList<>();
        String sql = "SELECT * FROM doctores WHERE id_doctor = ?";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
        ) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                datosDoctor.add(rs.getString("id_doctor"));
                datosDoctor.add(rs.getString("fecha_contratacion_doctor"));
                datosDoctor.add(rs.getString("fecha_nacimiento_doctor"));
                datosDoctor.add(rs.getString("id_persona"));
                datosDoctor.add(rs.getString("id_especialidad"));
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return datosDoctor;
    }

    /**
     * Registra nuevos doctores en la base de datos
     * @param fechaContratacion fecha de contratacion del doctor
     * @param fechaNacimiento fecha de nacimiento del doctor
     * @param idPersona id de la persona registrada en la base de datos
     * @param idEspecialidad id de la especialidad que posee el doctor
     * @return numero de filas afectadas
     */
    public static int ingresarNuevoDoctor(String fechaContratacion, String fechaNacimiento, String idPersona, String idEspecialidad){
        int retorno = 0;
        String sql = "INSERT INTO doctores (fecha_contratacion_doctor, fecha_nacimiento_doctor, id_persona, id_especialidad) VALUES (?,?,?,?)";
        try(
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setString(1, fechaContratacion);
            ps.setString(2, fechaNacimiento);
            ps.setString(3, idPersona);
            ps.setString(4, idEspecialidad);

            retorno = ps.executeUpdate();
            return retorno;
        } catch (SQLException e) {
            System.out.println("Error al registrar datos: " + e.getMessage());
            return retorno;
        }
    }

    /**
     * Actualiza el registro de un doctor especificado por su id
     * @param id id del registro del doctor
     * @param fechaContratacion nueva fecha de contratacion
     * @param fechaNacimiento nueva fecha de nacimiento
     * @param idPersona nuevo id de persona
     * @param idEspecialidad nueva especialidad para el doctor
     * @return numero de filas afectadas
     */
    public static int actualizarDoctor(String id, String fechaContratacion, String fechaNacimiento, String idPersona, String idEspecialidad) {
        String sql = "UPDATE doctores SET fecha_contratacion_doctor=?, fecha_nacimiento_doctor=?, id_persona=?, id_especialidad=? WHERE id_doctor=?";
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

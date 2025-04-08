package src.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * AppointmentsModel: Clase que gestiona todas los procesos relacionados con citas, incluyendo la carga, inserción, actualización y desactivación de registros.
 * Se comunica directamente con la base de datos utilizando JDBC.
 * @author TernuVet-DevTeam
 * @version 1.0
 */
public class AppointmentsModel {
    /**
     * Carga las citas almacenadas en la base de datos
     * @return una lista de listas con los datos de las citas
    */
    public static List<List<String>> cargarListaCitas() {
        List<List<String>> listaCitas = new ArrayList<>();
        String sql = "SELECT * FROM citas";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                List<String> cita = new ArrayList<>();
                cita.add(rs.getString("id_cita"));
                cita.add(rs.getString("motivo_cita"));
                cita.add(rs.getString("fecha_cita"));
                cita.add(rs.getString("hora_cita"));
                cita.add(rs.getString("id_mascota"));
                cita.add(rs.getString("id_doctor"));
                cita.add(rs.getString("visibilidad_cita"));

                listaCitas.add(cita);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return listaCitas;
    }
    /**
     * Carga los datos de todas las mascotas almacenadas en la base de datos
     * @return una lista de listas con los datos de las mascotas
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
    /**
     * Carga todos los doctores almacenados en la base de datos
     * @return una lista de listas con los datos de los doctores
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
    * Carga una cita especifica segun su ID
    * @param id del ID de la cita a buscar
     * @return  los datos de la lista o vacío en caso de no encontrar el id especificado
    */
    public static List<String> cargarCita(String id){
        List<String> datosCita = new ArrayList<>();
        String sql = "SELECT * FROM citas WHERE id_cita = ?";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
        ) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                datosCita.add(rs.getString("id_cita"));
                datosCita.add(rs.getString("motivo_cita"));
                datosCita.add(rs.getString("fecha_cita"));
                datosCita.add(rs.getString("hora_cita"));
                datosCita.add(rs.getString("id_mascota"));
                datosCita.add(rs.getString("id_doctor"));
                datosCita.add(rs.getString("visibilidad_cita"));
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return datosCita;
    }

    /**
     * Ingresa una nueva cita en la base de datos
     * @param motivoCita motivo de la cita
     * @param fechaCita fecha de la cita
     * @param horaCita hora de la cita
     * @param idMascota id de mascota que asistirá a la cita
     * @param idDoctor id del doctor que llevará la cita
     * @param visibilidadCita visibilidad de la cita
     * @return numero de filas afectadas
     */
    public static int ingresarNuevaCita(String motivoCita, String fechaCita, String horaCita, String idMascota, String idDoctor, Boolean visibilidadCita){
        int retorno = 0;
        String sql = "INSERT INTO citas (motivo_cita, fecha_cita, hora_cita, id_mascota, id_doctor, visibilidad_cita) VALUES (?,?,?,?,?,?)";
        try(
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setString(1, motivoCita);
            ps.setString(2, fechaCita);
            ps.setString(3, horaCita);
            ps.setString(4, idMascota);
            ps.setString(5, idDoctor);
            ps.setBoolean(6, visibilidadCita);

            retorno = ps.executeUpdate();
            return retorno;
        } catch (SQLException e) {
            System.out.println("Error al registrar datos: " + e.getMessage());
            return retorno;
        }
    }

    /**
     * Actualiza un registro existente de una cita
     * @param id id de la cita que se actualizará
     * @param motivoCita nuevo motivo
     * @param fechaCita nueva fecha
     * @param horaCita nueva hora
     * @param idMascota nuevo id de mascota
     * @param idDoctor nuevo id de doctor
     * @param visibilidadCita nueva visibilidad
     * @return numero de filas afectadas
     */
    public static int actualizarCita(String id, String motivoCita, String fechaCita, String horaCita, String idMascota, String idDoctor, Boolean visibilidadCita) {
        String sql = "UPDATE citas SET motivo_cita=?, fecha_cita=?, hora_cita=?, id_mascota=?, id_doctor=?, visibilidad_cita=? WHERE id_cita=?";
        try (Connection conexion = ConnectionModel.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, motivoCita);
            ps.setString(2, fechaCita);
            ps.setString(3, horaCita);
            ps.setString(4, idMascota);
            ps.setString(5, idDoctor);
            ps.setBoolean(6, visibilidadCita);
            ps.setString(7, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar persona: " + e.getMessage());
        }
        return 0;
    }

    /**
     * Actualiza la visibilidad de las citas (simula una eliminación)
     * @param id id de la cita que se actualiza
     * @return numero de filas afectadas
     */
    public static int desactivarCita(String id) {
        String sql = "UPDATE citas SET visibilidad_cita = 0 WHERE id_cita=?";
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

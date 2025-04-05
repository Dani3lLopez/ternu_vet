package src.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * Modelo de citas: maneja las operaciones CRUD en la base de datos
 * Este modelo contiene métodos para cargar, crear, actualizar y desactivar registros de citas
 * Ademas, carga datos de mascotas y propietarios
 */
public class AppointmentsModel {
    /*
     * Carga la lista de las citas registradas en la base de datos
     * Retorna una lista de listas. Cada sublista contiene los datos de una cita
     */
    public static List<List<String>> cargarListaCitas() {
        List<List<String>> listaCitas = new ArrayList<>();
        // Query para obtener los datos de la tabla "citas"
        String sql = "SELECT * FROM citas";

        try (
                // Se conecta con la base de datos, se realiza la query y se guardan los resultados
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            // Itera cada registro
            while (rs.next()) {
                List<String> cita = new ArrayList<>();
                cita.add(rs.getString("id_cita"));
                cita.add(rs.getString("motivo_cita"));
                cita.add(rs.getString("fecha_cita"));
                cita.add(rs.getString("hora_cita"));
                cita.add(rs.getString("id_mascota"));
                cita.add(rs.getString("id_doctor"));
                cita.add(rs.getString("visibilidad_cita"));
                // Agrega la cita a la lista de citas
                listaCitas.add(cita);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return listaCitas;
    }
    /*
     * Carga la lista de los mascotas registradas en la base de datos
     * Retorna una lista de listas. Cada sublista son los datos de cada mascota
     */
    public static List<List<String>> cargarListaMascotas() {
        List<List<String>> listaMascotas = new ArrayList<>();
        // Query para obtener datos de las mascotas
        String sql = "SELECT id_mascota, nombre_mascota, color_mascota, peso_mascota, unidad_peso_mascota, genero_mascota, codigo_chip_mascota, estado_reproductivo_mascota, fecha_nacimiento_mascota, talla_mascota, fallecimiento_mascota, razon_fallecimiento FROM mascotas WHERE visibilidad_mascota = 1";

        try (
                // Se conecta con la base de datos, se realiza la query y se guardan los resultados
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
     * Carga la lista de los doctores registrados en la base de datos
     * Retorna una lista de listas. Cada sublista son los datos de cada doctor
     */
    public static List<List<String>> cargarListaDoctores() {
        List<List<String>> listaDoctores = new ArrayList<>();
        // Query para obtener los doctores
        String sql = "SELECT * FROM doctores";

        try (
                // Se conecta con la base de datos, se realiza la query y se guardan los resultados
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            // Itera cada registro
            while (rs.next()) {
                List<String> doctor = new ArrayList<>();
                doctor.add(rs.getString("id_doctor"));
                doctor.add(rs.getString("fecha_contratacion_doctor"));
                doctor.add(rs.getString("fecha_nacimiento_doctor"));
                doctor.add(rs.getString("id_persona"));
                doctor.add(rs.getString("id_especialidad"));
                // Agrega el registro a la lista de doctores
                listaDoctores.add(doctor);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return listaDoctores;
    }

    /*
     * Carga los datos de una cita con base a su id
     * El parametro id es el identificador de la cita
     * Retorna una lista con los datos de la cita
     */
    public static List<String> cargarCita(String id){
        List<String> datosCita = new ArrayList<>();
        //Query para seleccionar los datos del registro de la cita con base a su id
        String sql = "SELECT * FROM citas WHERE id_cita = ?";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
        ) {
            // Establece el valor del parametro (id) para la consulta
            ps.setString(1, id);
            // Realzia la consulta y obtiene el resultado
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Si la cita existe, se añaden sus datos a la lista
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
        // Lista con datos de la cita
        return datosCita;
    }

    /*
     * Inserta una nueva cita a la base de datos
     * Retorna el numero de filas afectadas al realizar la query. Se espera 1 si sí se insertó
     */
    public static int ingresarNuevaCita(String motivoCita, String fechaCita, String horaCita, String idMascota, String idDoctor, Boolean visibilidadCita){
        int retorno = 0;
        // Query para insertar un nuevo registro en la tabla "citas" de la base de datos
        String sql = "INSERT INTO citas (motivo_cita, fecha_cita, hora_cita, id_mascota, id_doctor, visibilidad_cita) VALUES (?,?,?,?,?,?)";
        try(
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)){
            // Parametros de la query
            ps.setString(1, motivoCita);
            ps.setString(2, fechaCita);
            ps.setString(3, horaCita);
            ps.setString(4, idMascota);
            ps.setString(5, idDoctor);
            ps.setBoolean(6, visibilidadCita);

            // Ejecuta la query y guarda el numero de filas afectadas
            retorno = ps.executeUpdate();
            return retorno;
        } catch (SQLException e) {
            System.out.println("Error al registrar datos: " + e.getMessage());
            return retorno;
        }
    }

    /*
     * Actualiza los datos de una cita de la base de datos
     * Retorna el numero de filas afectas
     */
    public static int actualizarCita(String id, String motivoCita, String fechaCita, String horaCita, String idMascota, String idDoctor, Boolean visibilidadCita) {
        // Query para actualizar los datos de la cita
        String sql = "UPDATE citas SET motivo_cita=?, fecha_cita=?, hora_cita=?, id_mascota=?, id_doctor=?, visibilidad_cita=? WHERE id_cita=?";
        // Manejo de cierre de recursos automatico
        try (Connection conexion = ConnectionModel.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, motivoCita);
            ps.setString(2, fechaCita);
            ps.setString(3, horaCita);
            ps.setString(4, idMascota);
            ps.setString(5, idDoctor);
            ps.setBoolean(6, visibilidadCita);
            ps.setString(7, id);
            // Ejecuta la query y devuelve la cantidad de filas afectadas
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar persona: " + e.getMessage());
        }
        // En caso de error, se retorna 0
        return 0;
    }

    /*
     * Desactiva (elimina) una cita con base en su id de la base de datos
     *  Requiere un id como parametro para identificar la cita a eliminar
     *  Retorna la cantidad de filas afectadas por la query
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

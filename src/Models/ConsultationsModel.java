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
public class ConsultationsModel {
    /**
     * Carga una lista con todas las consultas almacenadas en la base de datos
     * @return una lista de listas con los datos de las consultas.
     */
    public static List<List<String>> cargarListaConsultas() {
        List<List<String>> listaConsultas = new ArrayList<>();
        // Query para obtener los datos de la tabla "consultas"
        String sql = "SELECT * FROM consultas";

        try (
                // Se conecta con la base de datos, se realiza la query y se guardan los resultados
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            // Itera cada registro
            while (rs.next()) {
                List<String> consulta = new ArrayList<>();
                consulta.add(rs.getString("id_consulta"));
                consulta.add(rs.getString("fecha_consulta"));
                consulta.add(rs.getString("motivo_consulta"));
                consulta.add(rs.getString("diagnostico_consulta"));
                consulta.add(rs.getString("notas_consulta"));
                consulta.add(rs.getString("id_mascota"));
                consulta.add(rs.getString("id_doctor"));
                consulta.add(rs.getString("visibilidad_consulta"));
                // Agrega la consulta a la lista de consultas
                listaConsultas.add(consulta);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return listaConsultas;
    }

    /**
     * Carga una lista con todas las mascotas almacenadas en la base de datos
     * @return una lista de listas con los datos de las mascotas
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

    /**
     * Carga una lista con todos los doctores almacenados en la base de datos
     * @return una lista de listas con los datos de los doctores
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

    /**
     * Carga los datos de una consulta específica según su id
     * @param id identificador de la cita
     * @return una lista con los datos de la consulta o vacía en caso de no encontrarla
     */
    public static List<String> cargarCita(String id){
        List<String> datosConsulta = new ArrayList<>();
        String sql = "SELECT * FROM consultas WHERE id_consulta = ?";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ) {
            // Establece el valor del parametro (id) para la consulta
            ps.setString(1, id);
            // Realzia la consulta y obtiene el resultado
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                datosConsulta.add(rs.getString("id_consulta"));
                datosConsulta.add(rs.getString("fecha_consulta"));
                datosConsulta.add(rs.getString("motivo_consulta"));
                datosConsulta.add(rs.getString("diagnostico_consulta"));
                datosConsulta.add(rs.getString("notas_consulta"));
                datosConsulta.add(rs.getString("id_mascota"));
                datosConsulta.add(rs.getString("id_doctor"));
                datosConsulta.add(rs.getString("visibilidad_consulta"));
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }
        return datosConsulta;
    }

    /**
     * Inserta un nuevo registro de consulta en la base de datos
     * @param fechaConsulta fecha de la consulta
     * @param motivoConsulta motivo de la consulta
     * @param diagnosticoConsulta diagnostico obtenido en la consulta
     * @param notasConsulta notas del doctor en la consulta
     * @param idMascota id de la mascota
     * @param idDoctor id del doctor que lleva la consulta
     * @param visibilidadConsulta visibilidad del registro de la consulta
     * @return numero de filas afectadas
     */
    public static int ingresarNuevaConsulta(String fechaConsulta, String motivoConsulta, String diagnosticoConsulta, String notasConsulta, String idMascota, String idDoctor, Boolean visibilidadConsulta){
        int retorno = 0;
        // Query para insertar un nuevo registro en la tabla "consultas" de la base de datos
        String sql = "INSERT INTO consultas (fecha_consulta, motivo_consulta, diagnostico_consulta, notas_consulta, id_mascota, id_doctor, visibilidad_consulta) VALUES (?,?,?,?,?,?,?)";
        try(
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)){
            // Parametros de la query
            ps.setString(1, fechaConsulta);
            ps.setString(2, motivoConsulta);
            ps.setString(3, diagnosticoConsulta);
            ps.setString(4, notasConsulta);
            ps.setString(5, idMascota);
            ps.setString(6, idDoctor);
            ps.setBoolean(7, visibilidadConsulta);
            // Ejecuta la query y guarda el número de filas afectadas
            retorno = ps.executeUpdate();
            return retorno;
        } catch (SQLException e) {
            System.out.println("Error al registrar datos: " + e.getMessage());
            return retorno;
        }
    }

    /**
     * Actualiza un registro de consulta según su id
     * @param id id de la consulta a actualizar
     * @param fechaConsulta nueva fecha de consulta
     * @param motivoConsulta nuevo motivo de consulta
     * @param diagnosticoConsulta nuevo dignostico de consulta
     * @param notasConsulta nuevas notas de consulta
     * @param idMascota nueva mascota
     * @param idDoctor nuevo id del doctor encargado de la consulta
     * @param visibilidadConsulta nueva visibilidad para la consulta
     * @return numero de filas afectadas
     */
    public static int actualizarConsulta(String id, String fechaConsulta, String motivoConsulta, String diagnosticoConsulta, String notasConsulta, String idMascota, String idDoctor, Boolean visibilidadConsulta) {
        // Query para actualizar los datos de la cita
        String sql = "UPDATE consultas SET fecha_consulta=?, motivo_consulta=?, diagnostico_consulta=?, notas_consulta=?, id_mascota=?, id_doctor=?, visibilidad_consulta=? WHERE id_consulta=?";
        try (Connection conexion = ConnectionModel.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, fechaConsulta);
            ps.setString(2, motivoConsulta);
            ps.setString(3, diagnosticoConsulta);
            ps.setString(4, notasConsulta);
            ps.setString(5, idMascota);
            ps.setString(6, idDoctor);
            ps.setBoolean(7, visibilidadConsulta);
            ps.setString(8, id);
            // Ejecuta la query y devuelve la cantidad de filas afectadas
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar consulta: " + e.getMessage());
        }
        // En caso de error, se retorna 0
        return 0;
    }

    /**
     * Actualiza la visibilidad de la consulta (simula una eliminacion)
     * @param id id de la consulta que se actualizará
     * @return numero de filas afectadas
     */
    public static int desactivarConsulta(String id) {
        String sql = "UPDATE consultas SET visibilidad_consulta = 0 WHERE id_consulta=?";
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

package src.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultationsModel {
    public static List<List<String>> cargarListaConsultas() {
        List<List<String>> listaConsultas = new ArrayList<>();
        String sql = "SELECT * FROM consultas";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
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

                listaConsultas.add(consulta);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return listaConsultas;
    }
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
    public static List<String> cargarCita(String id){
        List<String> datosConsulta = new ArrayList<>();
        String sql = "SELECT * FROM consultas WHERE id_consulta = ?";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
        ) {
            ps.setString(1, id);
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
    public static int ingresarNuevaConsulta(String fechaConsulta, String motivoConsulta, String diagnosticoConsulta, String notasConsulta, String idMascota, String idDoctor, Boolean visibilidadConsulta){
        int retorno = 0;
        String sql = "INSERT INTO consultas (fecha_consulta, motivo_consulta, diagnostico_consulta, notas_consulta, id_mascota, id_doctor, visibilidad_consulta) VALUES (?,?,?,?,?,?,?)";
        try(
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setString(1, fechaConsulta);
            ps.setString(2, motivoConsulta);
            ps.setString(3, diagnosticoConsulta);
            ps.setString(4, notasConsulta);
            ps.setString(5, idMascota);
            ps.setString(6, idDoctor);
            ps.setBoolean(7, visibilidadConsulta);

            retorno = ps.executeUpdate();
            return retorno;
        } catch (SQLException e) {
            System.out.println("Error al registrar datos: " + e.getMessage());
            return retorno;
        }
    }
    public static int actualizarConsulta(String id, String fechaConsulta, String motivoConsulta, String diagnosticoConsulta, String notasConsulta, String idMascota, String idDoctor, Boolean visibilidadConsulta) {
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
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar consulta: " + e.getMessage());
        }
        return 0;
    }
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

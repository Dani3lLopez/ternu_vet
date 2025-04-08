package src.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * InvoicesModel: Clase que gestiona todos los procesos relacionados con las facturas
 * @author TernuVet-DevTeam
 * @version 1.0
 */
public class InvoicesModel {
    /**
     * Carga todas las facturas registradas en la base de datos
     * @return una lista de listas de facturas
     */
    public static List<List<String>> cargarListaFacturas() {
        List<List<String>> listaFacturas = new ArrayList<>();
        String sql = "SELECT * FROM facturas WHERE visibilidad_factura = 1";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                List<String> factura = new ArrayList<>();
                factura.add(rs.getString("numero_factura"));
                factura.add(rs.getString("fecha_emision_factura"));
                factura.add(rs.getString("hora_emision_factura"));
                factura.add(rs.getString("id_propietario"));
                factura.add(rs.getString("visibilidad_factura"));

                listaFacturas.add(factura);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return listaFacturas;
    }

    /**
     * Carga todos los propietarios registrados en la base de datos
     * @return una lista de listas de los propietarios registrados en la base de datos
     */
    public static List<List<String>> cargarListaPropietarios() {
        List<List<String>> listaFacturas = new ArrayList<>();
        String sql = "SELECT * FROM propietarios WHERE visibilidad_propietario = 1";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                List<String> factura = new ArrayList<>();
                factura.add(rs.getString("id_propietario"));
                factura.add(rs.getString("id_persona"));
                factura.add(rs.getString("id_ciudad"));
                factura.add(rs.getString("direccion"));
                factura.add(rs.getString("visibilidad_propietario"));

                listaFacturas.add(factura);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return listaFacturas;
    }

    /**
     * Crea un nuevo registro de factura
     * @param fechaEmision fecha de emision de la factura
     * @param horaEmision hora de emision de la factura
     * @param idPropietario id del propietario de la mascota
     * @param visibilidadFactura visibilidad de la factura
     * @return numero de filas afectadas
     */
    public static int crearNuevaFactura(String fechaEmision, String horaEmision, String idPropietario, Boolean visibilidadFactura){
        int retorno = 0;
        String sql = "INSERT INTO facturas (fecha_emision_factura, hora_emision_factura, id_propietario, visibilidad_factura) VALUES (?,?,?,?)";
        try(
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setString(1, fechaEmision);
            ps.setString(2, horaEmision);
            ps.setString(3, idPropietario);
            ps.setBoolean(4, visibilidadFactura);

            retorno = ps.executeUpdate();
            return retorno;
        } catch (SQLException e) {
            System.out.println("Error al registrar datos: " + e.getMessage());
            return retorno;
        }
    }

    /**
     * Actualiza la visibilidad de la factura (simula una eliminacion)
     * @param id de la factura que se actualizará
     * @return numero de filas afectadas
     */
    public static int desactivarFactura(String id) {
        String sql = "UPDATE facturas SET visibilidad_factura = false WHERE numero_factura=?";
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

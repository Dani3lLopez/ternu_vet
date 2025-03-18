package src.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * Este modelo gestiona el acceso a datos de la entidad "Facturas"
 * Contiene metodos para cargar, crear y desactivar facturas
 */
public class InvoicesModel {

    /*
     * Carga la lsita de facturas activas desde la base de datos
     * Retorna una lista de listas
     */
    public static List<List<String>> cargarListaFacturas() {
        List<List<String>> listaFacturas = new ArrayList<>();
        // Query para obtener los datos de la tabla "facturas"
        String sql = "SELECT * FROM facturas WHERE visibilidad_factura = 1";

        try (
                // Se conceta con la base de datos, se realiza el query y se guardan los resutlados
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            // Itera cada registro
            while (rs.next()) {
                List<String> factura = new ArrayList<>();
                factura.add(rs.getString("numero_factura"));
                factura.add(rs.getString("fecha_emision_factura"));
                factura.add(rs.getString("hora_emision_factura"));
                factura.add(rs.getString("id_propietario"));
                factura.add(rs.getString("visibilidad_factura"));

                // Agrega la factura a la lsita de facturas
                listaFacturas.add(factura);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return listaFacturas;
    }

    /*
     * Carga la lista de los propietarios registrados en la base de datos
     * Retorna una lista de listas. Cada sublista son los datos de cada propietario
     */
    public static List<List<String>> cargarListaPropietarios() {
        List<List<String>> listaFacturas = new ArrayList<>();
        // Query para obtener propietarios activos
        String sql = "SELECT * FROM propietarios WHERE visibilidad_propietario = 1";

        try (
                // Se conceta con la base de datos, se realiza el query y se guardan los resutlados
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

                // Agrega el registro a la lista
                listaFacturas.add(factura);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return listaFacturas;
    }

    /*
     * Crea una nueva factura en la base de datos
     * Retorna el numero de filas afectadas (se espera 1 si hubo exito)
     */
    public static int crearNuevaFactura(String fechaEmision, String horaEmision, String idPropietario, Boolean visibilidadFactura){
        int retorno = 0;
        // Query para insertar una nueva factura
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
    /*
     * Desactiva una factura cambiando su visibilidad
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

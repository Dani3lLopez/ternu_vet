package src.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * InvoicesDetailsModel: Clase que gestiona todos los procesos relacionados con los detalles de facturas
 * @author TernuVet-DevTeam
 * @version 1.0
 */
public class InvoicesDetailsModel {
    /**
     * Carga todos los detalles de las facturas registrados en la base de datos
     * @return una lista de listas de los detalles de las facturas
     */
    public static List<List<String>> cargarListaDetallesFacturas() {
        List<List<String>> listaFacturas = new ArrayList<>();
        String sql = "SELECT * FROM detalle_facturas";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                List<String> factura = new ArrayList<>();
                factura.add(rs.getString("id_detalle_factura"));
                factura.add(rs.getString("numero_factura"));
                factura.add(rs.getString("id_detalle_item"));
                factura.add(rs.getString("cantidad_item"));
                factura.add(rs.getString("precio_unitario_item"));

                listaFacturas.add(factura);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return listaFacturas;
    }

    /**
     * Carga todas las facturas registradas en la base de datos
     * @return una lista de listas de las facturas registradas en la base de datos
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
     * Carga todos los detalles de items registrados en la base de datos
     * @return una lista de listas de los detalles de items
     */
    public static List<List<String>> cargarListaDetallesItems() {
        List<List<String>> listaDetallesItems = new ArrayList<>();
        String sql = "SELECT * FROM detalle_items";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                List<String> detalle = new ArrayList<>();
                detalle.add(rs.getString("id_detalle_item"));
                detalle.add(rs.getString("id_producto"));
                detalle.add(rs.getString("id_servicio"));

                listaDetallesItems.add(detalle);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return listaDetallesItems;
    }

    /**
     * Carga los nombres de los items disponibles
     * @return una lista de listas de los nombres de los items disponibles
     */
    public static List<List<String>> cargarListaNombresItems() {
        List<List<String>> listaNombresItems = new ArrayList<>();
        String sql = "SELECT p.id_producto, p.nombre_producto FROM productos p LEFT JOIN detalle_items di ON p.id_producto = di.id_producto";;

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                List<String> producto = new ArrayList<>();
                producto.add(rs.getString("id_producto"));
                producto.add(rs.getString("nombre_producto"));

                listaNombresItems.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return listaNombresItems;
    }

    /**
     * Carga el detalle de una factura especificado por su id
     * @param id del registro del detalle en la base de datos
     * @return los datos del detalle de la factura o vacio en caso de no encontrar el id
     */
    public static List<String> cargarDetalleFactura(String id){
        List<String> datosDetalle = new ArrayList<>();
        String sql = "SELECT * FROM detalle_facturas WHERE id_detalle_factura = ?";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
        ) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                datosDetalle.add(rs.getString("id_detalle_factura"));
                datosDetalle.add(rs.getString("numero_factura"));
                datosDetalle.add(rs.getString("id_detalle_item"));
                datosDetalle.add(rs.getString("cantidad_item"));
                datosDetalle.add(rs.getString("precio_unitario_item"));
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return datosDetalle;
    }

    /**
     * Ingresa nuevos detalles de factura en la base de datos
     * @param numeroFactura numero de factura
     * @param idDetalleItem id del item agregado
     * @param cantidadItem cantidad del item
     * @param precioUnitario el precio del item
     * @return numero de filas afectadas
     */
    public static int ingresarNuevoDetalleFactura(String numeroFactura, String idDetalleItem, String cantidadItem, String precioUnitario){
        int retorno = 0;
        String sql = "INSERT INTO detalle_facturas (numero_factura, id_detalle_item, cantidad_item, precio_unitario_item) VALUES (?,?,?,?)";
        try(
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setString(1, numeroFactura);
            ps.setString(2, idDetalleItem);
            ps.setString(3, cantidadItem);
            ps.setString(4, precioUnitario);

            retorno = ps.executeUpdate();
            return retorno;
        } catch (SQLException e) {
            System.out.println("Error al registrar datos: " + e.getMessage());
            return retorno;
        }
    }

    /**
     * Actualiza un detalle de factura existente segun su id
     * @param id del detalle existente
     * @param numeroFactura nuevo numero de factura
     * @param idDetalleItem nuevo item
     * @param cantidadItem nueva cantidad del item
     * @param precioUnitario nuevo precio
     * @return numero de filas afectadas
     */
    public static int actualizarDetalle(String id, String numeroFactura, String idDetalleItem, String cantidadItem, String precioUnitario) {
        String sql = "UPDATE detalle_facturas SET numero_factura=?, id_detalle_item=(SELECT id_detalle_item FROM detalle_items WHERE id_producto=? LIMIT 1), cantidad_item=?, precio_unitario_item =? WHERE id_detalle_factura=?";
        try (Connection conexion = ConnectionModel.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, numeroFactura);
            ps.setString(2, idDetalleItem);
            ps.setString(3, cantidadItem);
            ps.setString(4, precioUnitario);
            ps.setString(5, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar detalle: " + e.getMessage());
        }
        return 0;
    }

    /**
     * Elimina un detalle de la factura segun su id
     * @param id del detalle
     * @return numero de filas afectadas
     */
    public static int eliminarDetalleFactura(String id) {
        String sql = "DELETE FROM detalle_facturas WHERE id_detalle_factura=?";
        try (Connection conexion = ConnectionModel.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar el registro: " + e.getMessage());
        }
        return 0;
    }
}

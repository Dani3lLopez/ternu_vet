package src.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * Modelo de detalle de facturas
 * Este modelo contiene métodos para cargar listas de registros de facturas y detalle desde
 * la base de datos con la tabla "detalle_facturas"
 */
public class InvoicesDetailsModel {

    /*
     * Carga la lista de registros de detalles de facturas desde la base de datos
     * Retorna una lista de listas de cadenas de texto. Cada sublista contiene los datos de un registro
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

    /*
     * Carga la lista de facturas activas
     * Retorna una lista de registros de facturas
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

    /*
     * Carga la lista de registros de detalles de items desde la tabla "detalle_items"
     * Retorna una lista de registros de detalles de items
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

    /*
     * Carga la lista de nombres de items con un LEFT JOIN entre productos y detalle_items
     * Retorna una lista de listas, cada una con el id y el nombre del p
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

    /*
     * Carga los detalles de una factura en especifico con base a su id
     * El parametro es el id del detalle de factura a cargar
     * Retorna una lsita con los datos del detalle
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

    /*
     * Inserta un nuevo registro en la tabla "detalle_facturas" con los datos dados
     * Retorna el numero de filas afectadas
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

    /*
     * Actualiza un nuevo registro en la tabla "detalle_facturas" con base en su id
     * Retorna el numero de filas afectadas
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

    /*
     *Elimina un registro de la tabla "detalle_facturas" con base en su id
     * Retorna el numero de filas afectadas
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

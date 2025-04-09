package src.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ProductsModel: Clase que gestiona todos los procesos relacionados con los productos
 * @author TernuVet-DevTeam
 * @version 1.0
 */
public class ProductsModel {
    /**
     * Carga todos los productos almacenados en la base de datos
     * @return una lista de listas con todos los productos de la base de datos
     */
    public static List<List<String>> cargarListaProductos() {
        List<List<String>> listaProductos = new ArrayList<>();
        String sql = "SELECT id_producto, nombre_producto, descripcion_producto, precio_producto, stock_producto, peso_producto, unidad_medida_producto, estado_producto FROM productos WHERE visibilidad_producto = 1";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                List<String> producto = new ArrayList<>();
                producto.add(rs.getString("id_producto"));
                producto.add(rs.getString("nombre_producto"));
                producto.add(rs.getString("descripcion_producto"));
                producto.add(rs.getString("precio_producto"));
                producto.add(rs.getString("stock_producto"));
                producto.add(rs.getString("peso_producto"));
                producto.add(rs.getString("unidad_medida_producto"));
                producto.add(rs.getString("estado_producto"));

                listaProductos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return listaProductos;
    }

    /**
     * Registra un nuevo producto en la base de datos
     * @param nombreProducto nombre del producto
     * @param descripcionProducto descripción del producto
     * @param precioProducto precio del producto
     * @param stockProducto stock disponible del producto
     * @param pesoProducto peso del producto
     * @param unidadMedidaProducto unidad de medida del producto
     * @return numero de filas afectadas
     */
    public static int ingresarProducto(String nombreProducto, String descripcionProducto, double precioProducto, int stockProducto, double pesoProducto, String unidadMedidaProducto) {

        int retorno = 0;

        String sql;
        if (unidadMedidaProducto == null) {
            sql = "INSERT INTO productos (nombre_producto, descripcion_producto, precio_producto, stock_producto, peso_producto) VALUES (?,?,?,?,?)";
        } else {
            sql = "INSERT INTO productos (nombre_producto, descripcion_producto, precio_producto, stock_producto, peso_producto, unidad_medida_producto) VALUES (?,?,?,?,?,?)";
        }
        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombreProducto);
            ps.setString(2, descripcionProducto);
            ps.setDouble(3, precioProducto);
            ps.setInt(4, stockProducto);
            ps.setDouble(5, pesoProducto);
            if (unidadMedidaProducto != null) {
                ps.setString(6, unidadMedidaProducto);
            }
            retorno = ps.executeUpdate();
            return retorno;
        } catch (SQLException e) {
            System.out.println("Error al registrar datos: " + e.getMessage());
            return retorno;
        }
    }

    /**
     * Carga los datos de un producto en específico
     * @param id id del producto
     * @return los datos del producto
     */
    public static List<String> cargarProducto(String id){
        List<String> datosServicio = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE id_producto = ?";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                datosServicio.add(rs.getString("id_producto"));
                datosServicio.add(rs.getString("nombre_producto"));
                datosServicio.add(rs.getString("descripcion_producto"));
                datosServicio.add(rs.getString("precio_producto"));
                datosServicio.add(rs.getString("stock_producto"));
                datosServicio.add(rs.getString("peso_producto"));
                datosServicio.add(rs.getString("unidad_medida_producto"));
                datosServicio.add(rs.getString("estado_producto"));
                datosServicio.add(rs.getString("visibilidad_producto"));
            }
        } catch (SQLException e) {
            System.out.println("Error al leer datos: " + e.getMessage());
        }

        return datosServicio;
    }

    /**
     * Actualiza el registro de un producto existente
     * @param id id del producto existente
     * @param nombreProducto nuevo nombre del producto
     * @param descripcionProducto nueva descripción del producto
     * @param precioProducto nuevo precio
     * @param stockProducto nuevo stock
     * @param pesoProducto nuevo peso
     * @param unidadMedidaProducto nueva unidad de medida
     * @return numero de filas afectadas
     */
    public static int actualizarProducto(String id, String nombreProducto, String descripcionProducto, double precioProducto, int stockProducto, double pesoProducto, String unidadMedidaProducto) {
        String sql = "UPDATE productos SET nombre_producto=?, descripcion_producto=?, precio_producto=?, stock_producto=?, peso_producto=?, unidad_medida_producto=? WHERE id_producto=?";
        try (Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombreProducto);
            ps.setString(2, descripcionProducto);
            ps.setDouble(3, precioProducto);
            ps.setInt(4, stockProducto);
            ps.setDouble(5, pesoProducto);
            ps.setString(6, unidadMedidaProducto);
            ps.setString(7, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar el producto: " + e.getMessage());
        }
        return 0;
    }

    /**
     * Actualiza la visibilidad de un producto (simula una eliminación)
     * @param id id del producto
     * @return numero de filas afectadas
     */
    public static int eliminarProducto(String id) {
        String sql = "UPDATE productos SET visibilidad_producto=0 WHERE id_producto=?";
        try (Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar el registro del servicio: " + e.getMessage());
        }
        return 0;
    }
}

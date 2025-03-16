package src.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsModel {
    public static List<List<String>> cargarListaProductos() {
        List<List<String>> listaProductos = new ArrayList<>();
        String sql = "SELECT id_producto, nombre_producto, descripcion_producto, precio_producto, stock_producto, peso_producto, unidad_medida_producto, estado_producto FROM productos WHERE visibilidad_producto = 1";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
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

    public static int ingresarProducto(String nombreProducto, String descripcionProducto, double precioProducto, int stockProducto, double pesoProducto, String unidadMedidaProducto) {
        int retorno = 0;

        String sql;
        if(unidadMedidaProducto == null){
            sql = "INSERT INTO productos (nombre_producto, descripcion_producto, precio_producto, stock_producto, peso_producto) VALUES (?,?,?,?,?)";
        }else{
            sql = "INSERT INTO productos (nombre_producto, descripcion_producto, precio_producto, stock_producto, peso_producto, unidad_medida_producto) VALUES (?,?,?,?,?,?)";
        }
        try(
            Connection conexion = ConnectionModel.conectar();
            PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setString(1, nombreProducto);
            ps.setString(2, descripcionProducto);
            ps.setDouble(3, precioProducto);
            ps.setInt(4, stockProducto);
            ps.setDouble(5, pesoProducto);
            if(unidadMedidaProducto != null){
                ps.setString(6, unidadMedidaProducto);
            }
            retorno = ps.executeUpdate();
            return retorno;
        } catch (SQLException e) {
            System.out.println("Error al registrar datos: " + e.getMessage());
            return retorno;
        }
    }

    public static List<String> cargarProducto(String id){
        List<String> datosServicio = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE id_producto = ?";

        try (
                Connection conexion = ConnectionModel.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql);
        ) {
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

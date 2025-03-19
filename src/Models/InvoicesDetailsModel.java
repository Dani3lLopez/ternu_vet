package src.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvoicesDetailsModel {
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
}

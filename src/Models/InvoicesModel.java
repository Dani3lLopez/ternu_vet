package src.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvoicesModel {
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
}

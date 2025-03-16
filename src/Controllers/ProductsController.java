package src.Controllers;

import src.Models.ProductsModel;
import src.Models.ServicesModel;

import java.util.ArrayList;
import java.util.List;

public class ProductsController {
    public ProductsController() {

    }

    private String idProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private double precioProducto;
    private int stockProducto;
    private double pesoProducto;
    private String unidadMedidaProducto;
    private String estadoProducto;
    private String visibilidadProducto;
    private List<List<String>> listaProductos;

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }

    public double getPesoProducto() {
        return pesoProducto;
    }

    public void setPesoProducto(double pesoProducto) {
        this.pesoProducto = pesoProducto;
    }

    public String getUnidadMedidaProducto() {
        return unidadMedidaProducto;
    }

    public void setUnidadMedidaProducto(String unidadMedidaProducto) {
        this.unidadMedidaProducto = unidadMedidaProducto;
    }

    public String getEstadoProducto() {
        return estadoProducto;
    }

    public void setEstadoProducto(String estadoProducto) {
        this.estadoProducto = estadoProducto;
    }

    public String getVisibilidadProducto() {
        return visibilidadProducto;
    }

    public void setVisibilidadProducto(String visibilidadProducto) {
        this.visibilidadProducto = visibilidadProducto;
    }

    public void cargarListaProductos() {
        listaProductos = ProductsModel.cargarListaProductos();
    }
    public List<List<String>> getListaProductos() {
        return listaProductos;
    }

    public int registrarProducto() {
        return ProductsModel.ingresarProducto(nombreProducto, descripcionProducto, precioProducto, stockProducto, pesoProducto, unidadMedidaProducto);
    }

    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaProductos.size()) {
            return listaProductos.get(numero - 1).get(0);
        }
        return null;
    }

    public List<String> cargarDatosProducto(int registro){
        String id = capturarIdLista(registro);
        if (id != null){
            return ProductsModel.cargarProducto(id);
        }
        return new ArrayList<>();
    }

    public void actualizarProducto(int registro, String nombreProducto, String descripcionProducto, double precioProducto, int stockProducto, double pesoProducto, String unidadMedidaProducto) {
        String id = capturarIdLista(registro);
        if (id != null) {
            int resultado = ProductsModel.actualizarProducto(id, nombreProducto, descripcionProducto, precioProducto, stockProducto, pesoProducto, unidadMedidaProducto);
            if (resultado > 0) {
                System.out.println("Registro actualizado correctamente.");
            } else {
                System.out.println("Error al actualizar el registro.");
            }
        } else {
            System.out.println("Registro inexistente.");
        }
    }

    public void eliminarProducto(int numero){
        String id = this.capturarIdLista(numero);
        if (id != null) {
            int resultado = ProductsModel.eliminarProducto(id);
            if (resultado > 0) {
                System.out.println("Producto eliminado correctamente.");
            } else {
                System.out.println("Error al eliminar el registro.");
            }
        } else {
            System.out.println("Registro inexistente.");
        }
    }
}

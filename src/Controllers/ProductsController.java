package src.Controllers;

import src.Models.ProductsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con los productos,
 * incluyendo el registro, consulta, actualización y eliminación de datos
 */
public class ProductsController {

    /**
     * Constructor vacío del controlador de productos
     */
    public ProductsController() {}

    /**
     * Atributos de un producto
     */
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

    /**
     * Getter del id del producto
     * @return idProducto
     */
    public String getIdProducto() {
        return idProducto;
    }

    /**
     * Setter del id del producto
     * @param idProducto id del producto
     */
    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Getter del nombre del producto
     * @return nombreProducto
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * Setter del nombre del producto
     * @param nombreProducto nombre del producto
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    /**
     * Getter de la descripción del producto
     * @return descripcionProducto
     */
    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    /**
     * Setter de la descripción del producto
     * @param descripcionProducto descripción del producto
     */
    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    /**
     * Getter del precio del producto
     * @return precioProducto
     */
    public double getPrecioProducto() {
        return precioProducto;
    }

    /**
     * Setter del precio del producto
     * @param precioProducto precio del producto
     */
    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    /**
     * Getter del stock del producto
     * @return stockProducto
     */
    public int getStockProducto() {
        return stockProducto;
    }

    /**
     * Setter del stock del producto
     * @param stockProducto stock del producto
     */
    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }

    /**
     * Getter del peso del producto
     * @return pesoProducto
     */
    public double getPesoProducto() {
        return pesoProducto;
    }

    /**
     * Setter del peso del producto
     * @param pesoProducto peso del producto
     */
    public void setPesoProducto(double pesoProducto) {
        this.pesoProducto = pesoProducto;
    }

    /**
     * Getter de la unidad de medida del producto
     * @return unidadMedidaProducto
     */
    public String getUnidadMedidaProducto() {
        return unidadMedidaProducto;
    }

    /**
     * Setter de la unidad de medida del producto
     * @param unidadMedidaProducto unidad de medida del producto
     */
    public void setUnidadMedidaProducto(String unidadMedidaProducto) {
        this.unidadMedidaProducto = unidadMedidaProducto;
    }

    /**
     * Getter del estado del producto
     * @return estadoProducto
     */
    public String getEstadoProducto() {
        return estadoProducto;
    }

    /**
     * Setter del estado del producto
     * @param estadoProducto estado del producto
     */
    public void setEstadoProducto(String estadoProducto) {
        this.estadoProducto = estadoProducto;
    }

    /**
     * Getter de la visibilidad del producto
     * @return visibilidadProducto
     */
    public String getVisibilidadProducto() {
        return visibilidadProducto;
    }

    /**
     * Setter de la visibilidad del producto
     * @param visibilidadProducto visibilidad del producto
     */
    public void setVisibilidadProducto(String visibilidadProducto) {
        this.visibilidadProducto = visibilidadProducto;
    }

    /**
     * Carga la lista de productos desde el modelo
     * @see ProductsModel
     */
    public void cargarListaProductos() {
        listaProductos = ProductsModel.cargarListaProductos();
    }

    /**
     * Retorna la lista de productos
     * @return listaProductos
     */
    public List<List<String>> getListaProductos() {
        return listaProductos;
    }

    /**
     * Registra un nuevo producto
     * @return número de filas afectadas
     */
    public int registrarProducto() {
        return ProductsModel.ingresarProducto(nombreProducto, descripcionProducto, precioProducto, stockProducto,
                pesoProducto, unidadMedidaProducto);
    }

    /**
     * Captura el id del producto según su número en consola
     * @param numero número del registro en consola
     * @return id del producto o null si no existe
     */
    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaProductos.size()) {
            return listaProductos.get(numero - 1).get(0);
        }
        return null;
    }

    /**
     * Carga los datos de un producto dado su número de registro
     * @param registro número de registro en consola
     * @return lista de datos del producto
     */
    public List<String> cargarDatosProducto(int registro) {
        String id = capturarIdLista(registro);
        if (id != null) {
            return ProductsModel.cargarProducto(id); // Si el od está vacío, retorna una lista vacía
        }
        return new ArrayList<>();
    }

    /**
     * Actualiza los datos de un producto
     * @param registro número del registro en consola
     * @param nombreProducto nuevo nombre del producto
     * @param descripcionProducto nueva descripción del producto
     * @param precioProducto nuevo precio del producto
     * @param stockProducto nuevo stock del producto
     * @param pesoProducto nuevo peso del producto
     * @param unidadMedidaProducto nueva unidad de medida
     */
    public void actualizarProducto(int registro, String nombreProducto, String descripcionProducto,
                                   double precioProducto, int stockProducto, double pesoProducto, String unidadMedidaProducto) {
        String id = capturarIdLista(registro);
        if (id != null) {
            int resultado = ProductsModel.actualizarProducto(id, nombreProducto, descripcionProducto, precioProducto,
                    stockProducto, pesoProducto, unidadMedidaProducto);
            if (resultado > 0) {
                System.out.println("Registro actualizado correctamente.");
            } else {
                System.out.println("Error al actualizar el registro.");
            }
        } else {
            System.out.println("Registro inexistente.");
        }
    }

    /**
     * Elimina un producto
     * @param numero número del registro en consola
     */
    public void eliminarProducto(int numero) {
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
package src.controllers;

import src.models.InvoicesDetailsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con los detalles de facturas,
 * incluyendo su creación, desactivación y carga de datos relacionados
 * @see OwnersController
 */
public class InvoicesDetailsController {

    /**
     * Constructor que inicializa las listas de propietarios y facturas
     */
    public InvoicesDetailsController() {
        listaDetallesFacturas = new ArrayList<>();
        listaFacturas = new ArrayList<>();
        listaDetallesItems = new ArrayList<>();
        listaNombresItems = new ArrayList<>();
    }

    /**
     * Atributos de los detalles de la factura
     */
    private String idDetalleFactura;
    private String numeroFactura;
    private String idDetalleItem;
    private String cantidadItem;
    private String precioUnitarioItem;

    /**
     * Listas de listas: DetallesFacturas, Facturas, DetallesItems, NombresItems
     */
    private List<List<String>> listaDetallesFacturas;
    private List<List<String>> listaFacturas;
    private List<List<String>> listaDetallesItems;
    private List<List<String>> listaNombresItems;

    // Getters y setters

    /** @return ID del detalle de factura */
    public String getIdDetalleFactura() {
        return idDetalleFactura;
    }

    /** @param idDetalleFactura ID del detalle de factura */
    public void setIdDetalleFactura(String idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    /** @return número de factura */
    public String getNumeroFactura() {
        return numeroFactura;
    }

    /** @param numeroFactura número de factura */
    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    /** @return ID del detalle del ítem */
    public String getIdDetalleItem() {
        return idDetalleItem;
    }

    /** @param idDetalleItem ID del detalle del ítem */
    public void setIdDetalleItem(String idDetalleItem) {
        this.idDetalleItem = idDetalleItem;
    }

    /** @return cantidad del ítem */
    public String getCantidadItem() {
        return cantidadItem;
    }

    /** @param cantidadItem cantidad del ítem */
    public void setCantidadItem(String cantidadItem) {
        this.cantidadItem = cantidadItem;
    }

    /** @return precio unitario del ítem */
    public String getPrecioUnitarioItem() {
        return precioUnitarioItem;
    }

    /** @param precioUnitarioItem precio unitario del ítem */
    public void setPrecioUnitarioItem(String precioUnitarioItem) {
        this.precioUnitarioItem = precioUnitarioItem;
    }

    /** @return lista de detalles de factura */
    public List<List<String>> listaDetallesFacturas() {
        return listaDetallesFacturas;
    }

    /** @return lista de facturas */
    public List<List<String>> listaFacturas() {
        return listaFacturas;
    }

    /** @return lista de detalles de ítems */
    public List<List<String>> listaDetallesItems() {
        return listaDetallesItems;
    }

    /** @return lista de nombres de ítems */
    public List<List<String>> listaNombresItems() {
        return listaNombresItems;
    }

    /**
     * Llena las listas utilizadas por el controlador con los datos del modelo
     * @see InvoicesDetailsModel
     */
    public void llenarListas(){
        listaDetallesFacturas = InvoicesDetailsModel.cargarListaDetallesFacturas();
        listaFacturas = InvoicesDetailsModel.cargarListaFacturas();
        listaDetallesItems = InvoicesDetailsModel.cargarListaDetallesItems();
        listaNombresItems = InvoicesDetailsModel.cargarListaNombresItems();
    }

    /**
     * Captura el ID real de la lista de detalles de factura
     * @param numero número del registro
     * @return id correspondiente
     */
    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaDetallesFacturas.size()) {
            return listaDetallesFacturas.get(numero - 1).get(0);
        }
        return null;
    }

    /**
     * Captura el ID de factura a partir de un índice
     * @param numero número del registro
     * @return id de la factura
     */
    public String capturarIdListaFacturas(int numero) {
        if (numero > 0 && numero <= listaFacturas.size()) {
            return listaFacturas.get(numero - 1).get(0);
        }
        return null;
    }

    /**
     * Captura el id de un detalle de ítem
     * @param numero número del registro
     * @return id del detalle de ítem
     */
    public String capturarIdListaDetallesItems(int numero) {
        if (numero > 0 && numero <= listaDetallesItems.size()) {
            System.out.println(listaDetallesItems.get(numero - 1).get(0));
            return listaDetallesItems.get(numero - 1).get(0);
        }
        return null;
    }

    /**
     * Captura el número de factura si existe en la lista
     * @param numeroFactura número de factura
     * @return número de factura si existe, No encontrada si no
     */
    public String capturarFactura(String numeroFactura){
        for (List<String> factura : listaFacturas) {
            if (factura.get(0).trim().equalsIgnoreCase(numeroFactura)) {
                return factura.get(0);
            }
        }
        return "No encontrada";
    }

    /**
     * Método para obtener el nombre del item
     * @param idDetalleItem id del item
     * @return nombre del item
     */
    public String obtenerNombreItem(String idDetalleItem) {
        return InvoicesDetailsModel.obtenerNombreItem(idDetalleItem);
    }

    /**
     * Captura el nombre de un ítem dado su ID de detalle
     * @param idDetalleItem id del detalle del ítem
     * @return nombre del ítem o mensaje de error si no se encuentra
     */
    public String capturarNombreItem(String idDetalleItem) {
        String idProducto = null;

        for (List<String> item : listaDetallesItems) {
            if (item.get(0).trim().equalsIgnoreCase(idDetalleItem.trim())) {
                idProducto = item.get(1);
                break;
            }
        }

        if (idProducto == null) {
            return "No encontrado";
        }
        for (List<String> nombreItem : listaNombresItems) {
            if (nombreItem.get(0).trim().equalsIgnoreCase(idProducto.trim())) {
                return nombreItem.get(1);
            }
        }

        return "Desconocido";
    }

    /**
     * Registra un nuevo ítem en la factura actual
     * @return número de filas afectadas
     * @see InvoicesDetailsModel
     */
    public int registrarItemFactura() {
        return InvoicesDetailsModel.ingresarNuevoDetalleFactura(numeroFactura, idDetalleItem, cantidadItem, precioUnitarioItem);
    }

    /**
     * Carga los datos de un detalle de factura específico
     * @param registro número de registro
     * @return lista con los datos del detalle
     */
    public List<String> cargarDatosDetalle(int registro){
        String id = capturarIdLista(registro);
        if (id != null){
            return InvoicesDetailsModel.cargarDetalleFactura(id);
        }
        return new ArrayList<>();
    }

    /**
     * Actualiza un detalle de factura
     * @param registro número del registro a actualizar
     * @param numeroFactura nuevo número de factura
     * @param idDetalleItem nuevo ID de detalle de ítem
     * @param cantidadItem nueva cantidad
     * @param precioUnitarioItem nuevo precio unitario
     */
    public void actualizarDetalle(int registro, String numeroFactura, String idDetalleItem, String cantidadItem, String precioUnitarioItem) {
        String id = capturarIdLista(registro);
        if (id != null) {
            int resultado = InvoicesDetailsModel.actualizarDetalle(id, numeroFactura, idDetalleItem, cantidadItem, precioUnitarioItem);
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
     * Elimina un detalle de factura
     * @param numero número del registro a eliminar
     */
    public void eliminarDetalleFactura(int numero){
        String id = this.capturarIdLista(numero);
        if (id != null) {
            int resultado = InvoicesDetailsModel.eliminarDetalleFactura(id);
            if (resultado > 0) {
                System.out.println("Detalle eliminado correctamente.");
            } else {
                System.out.println("Error al eliminar el registro.");
            }
        } else {
            System.out.println("Registro inexistente.");
        }
    }
}

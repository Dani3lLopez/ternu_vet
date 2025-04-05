package src.Controllers;

import src.Models.InvoicesDetailsModel;
import src.Models.OwnersPetsDetailsModel;

import java.util.ArrayList;
import java.util.List;

/*
 * Controlador que facilita metodos para llenar listas de detalles de facturas, facturas, detalles y nombres de items
 * Captura informacion especifica de estas listas
 */
public class InvoicesDetailsController {

    /*
     * Constructor para inicializar las listas de detalles de facturas, facturas, detalles y nombres de items
     */
    public InvoicesDetailsController() {
        listaDetallesFacturas = new ArrayList<>();
        listaFacturas = new ArrayList<>();
        listaDetallesItems = new ArrayList<>();
        listaNombresItems = new ArrayList<>();
    }

    // Atributos especificos
    private String idDetalleFactura;
    private String numeroFactura;
    private String idDetalleItem;
    private String cantidadItem;
    private String precioUnitarioItem;
    //Listas que almacenan los registros cargados de la base de datos
    private List<List<String>> listaDetallesFacturas;
    private List<List<String>> listaFacturas;
    private List<List<String>> listaDetallesItems;
    private List<List<String>> listaNombresItems;

    // Metodos getter y setter
    public String getIdDetalleFactura() {
        return idDetalleFactura;
    }

    public void setIdDetalleFactura(String idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getIdDetalleItem() {
        return idDetalleItem;
    }

    public void setIdDetalleItem(String idDetalleItem) {
        this.idDetalleItem = idDetalleItem;
    }

    public String getCantidadItem() {
        return cantidadItem;
    }

    public void setCantidadItem(String cantidadItem) {
        this.cantidadItem = cantidadItem;
    }

    public String getPrecioUnitarioItem() {
        return precioUnitarioItem;
    }

    public void setPrecioUnitarioItem(String precioUnitarioItem) {
        this.precioUnitarioItem = precioUnitarioItem;
    }

    // Metodos de acceso a las listas
    /*
     * Retorna una lista de detalles de facturas
     */
    public List<List<String>> listaDetallesFacturas() {
        return listaDetallesFacturas;
    }

    /*
     * Retorna una lista de facturas
     */
    public List<List<String>> listaFacturas() {
        return listaFacturas;
    }

    /*
     * Retorna una lista de detalles de nombres de items
     */
    public List<List<String>> listaDetallesItems() {
        return listaDetallesItems;
    }

    /*
     * Retorna una lista de nombres de items
     */
    public List<List<String>> listaNombresItems() {
        return listaNombresItems;
    }

    /*
     * Llena las listas con el modelo
     */
    public void llenarListas(){
        listaDetallesFacturas = InvoicesDetailsModel.cargarListaDetallesFacturas();
        listaFacturas = InvoicesDetailsModel.cargarListaFacturas();
        listaDetallesItems = InvoicesDetailsModel.cargarListaDetallesItems();
        listaNombresItems = InvoicesDetailsModel.cargarListaNombresItems();
    }
    // Metodo para captura de informacion
    /*
     * Captura el id del registro de detalle de factura con base en la posicion en la lista
     * El parametro es  el numero indice del registro
     * Retorna el id del registro si existe; en caso contrario, null
     */
    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaDetallesFacturas.size()) {
            return listaDetallesFacturas.get(numero - 1).get(0);
        }
        return null;
    }

    /*
     * Captura el id de un registro de factura con base a su posicion en la lista
     * El parametro es el numero indice del registro
     * Retorna el id del registro si existe y si no, null
     */
    public String capturarIdListaFacturas(int numero) {
        if (numero > 0 && numero <= listaFacturas.size()) {
            return listaFacturas.get(numero - 1).get(0);
        }
        return null;
    }

    /*
     * Captura el id de un registro de detalle de items con base a su posicion en la lista
     * El parametro es el numero indice del registro
     * Retorna el id del registro si existe y si no, null
     */
    public String capturarIdListaDetallesItems(int numero) {
        if (numero > 0 && numero <= listaDetallesItems.size()) {
            return listaDetallesItems.get(numero - 1).get(0);
        }
        return null;
    }

    /*
     * Busca y retorna el numero de item asociado a un detalle de factura
     * El parametro es el id del producto en la lista de detalle de items
     * Retorna el nombre del item si lo encuentra, si no, lo informa
     */
    public String capturarFactura(String numeroFactura){
        for (List<String> factura : listaFacturas) {
            if (factura.get(0).trim().equalsIgnoreCase(numeroFactura)) {
                return factura.get(0);
            }
        }
        return "No encontrada";
    }

    /*
     * Busca y retorna el numero de factura asociado a un registro
     * Recorre la lista de facturas y devuelve el numero de factura al encontrar una coincidencia
     * Retorna el numero de factura si la encuentra, si no, informa que no
     */
    public String capturarNombreItem(String idDetalleItem) {
        String idProducto = null;

        for (List<String> item : listaDetallesItems) {
            if (item.get(0).trim().equalsIgnoreCase(idDetalleItem.trim())) {
                idProducto = item.get(1); // Tomamos el id_producto
                break;
            }
        }

        if (idProducto == null) {
            return "No encontrado";
        }
        // con el id del producto, busca el nombre en la lista de nombres
        for (List<String> nombreItem : listaNombresItems) {
            if (nombreItem.get(0).trim().equalsIgnoreCase(idProducto.trim())) {
                return nombreItem.get(1);
            }
        }

        return "Desconocido";
    }

    /*
     * Busca y retorna el nombre del item por id del detalle de item
     * El parametro es el id del detalle de item
     * Retorna el nombre del item si la encuentra, si no, informa que no
     */
    public String capturarItem(String idDetalleItem){
        for (List<String> item : listaDetallesItems) {
            if (item.get(0).trim().equalsIgnoreCase(idDetalleItem)) {
                return item.get(1);
            }
        }
        return "No encontrado";
    }

    // Metodos de registro y manipulacion
    /*
     * Registra un nuevo registro en la tabla "detalle_facturas" con los datos actuales
     * Retorna el numero de filas afectadas
     */
    public int registrarItemFactura() {
        return InvoicesDetailsModel.ingresarNuevoDetalleFactura(numeroFactura, idDetalleItem, cantidadItem, precioUnitarioItem);
    }

    /*
     * Carga los datos de un registro de detalle de factura especifico con base en su posicion
     *  Usa como parametro el registro indice del registro
     * Retorna el numero de filas afectadas
     */
    public List<String> cargarDatosDetalle(int registro){
        String id = capturarIdLista(registro);
        if (id != null){
            return InvoicesDetailsModel.cargarDetalleFactura(id);
        }
        return new ArrayList<>();
    }

    /*
     * Actualiza los datos de un registro de detalle de factura
     * Solicita los neuvos datos y llama al modelo para actualizar
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

    /*
     * Elimina un registro de detalle de factura de forma definitiva
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

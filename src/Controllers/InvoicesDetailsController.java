package src.Controllers;

import src.Models.InvoicesDetailsModel;
import src.Models.OwnersPetsDetailsModel;

import java.util.ArrayList;
import java.util.List;

public class InvoicesDetailsController {
    public InvoicesDetailsController() {
        listaDetallesFacturas = new ArrayList<>();
        listaFacturas = new ArrayList<>();
        listaDetallesItems = new ArrayList<>();
        listaNombresItems = new ArrayList<>();
    }

    private String idDetalleFactura;
    private String numeroFactura;
    private String idDetalleItem;
    private String cantidadItem;
    private String precioUnitarioItem;
    private List<List<String>> listaDetallesFacturas;
    private List<List<String>> listaFacturas;
    private List<List<String>> listaDetallesItems;
    private List<List<String>> listaNombresItems;

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

    public List<List<String>> listaDetallesFacturas() {
        return listaDetallesFacturas;
    }

    public List<List<String>> listaFacturas() {
        return listaFacturas;
    }

    public List<List<String>> listaDetallesItems() {
        return listaDetallesItems;
    }

    public List<List<String>> listaNombresItems() {
        return listaNombresItems;
    }

    public void llenarListas(){
        listaDetallesFacturas = InvoicesDetailsModel.cargarListaDetallesFacturas();
        listaFacturas = InvoicesDetailsModel.cargarListaFacturas();
        listaDetallesItems = InvoicesDetailsModel.cargarListaDetallesItems();
        listaNombresItems = InvoicesDetailsModel.cargarListaNombresItems();
    }

    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaDetallesFacturas.size()) {
            return listaDetallesFacturas.get(numero - 1).get(0);
        }
        return null;
    }

    public String capturarIdListaFacturas(int numero) {
        if (numero > 0 && numero <= listaFacturas.size()) {
            return listaFacturas.get(numero - 1).get(0);
        }
        return null;
    }

    public String capturarIdListaDetallesItems(int numero) {
        if (numero > 0 && numero <= listaDetallesItems.size()) {
            return listaDetallesItems.get(numero - 1).get(0);
        }
        return null;
    }

    public String capturarFactura(String numeroFactura){
        for (List<String> factura : listaFacturas) {
            if (factura.get(0).trim().equalsIgnoreCase(numeroFactura)) {
                return factura.get(1);
            }
        }
        return "No encontrada";
    }

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

        for (List<String> nombreItem : listaNombresItems) {
            if (nombreItem.get(0).trim().equalsIgnoreCase(idProducto.trim())) {
                return nombreItem.get(1);
            }
        }

        return "Desconocido";
    }

    public String capturarItem(String idDetalleItem){
        for (List<String> item : listaDetallesItems) {
            if (item.get(0).trim().equalsIgnoreCase(idDetalleItem)) {
                return item.get(1);
            }
        }
        return "No encontrado";
    }

    public int registrarItemFactura() {
        return InvoicesDetailsModel.ingresarNuevoDetalleFactura(numeroFactura, idDetalleItem, cantidadItem, precioUnitarioItem);
    }

    public List<String> cargarDatosDetalle(int registro){
        String id = capturarIdLista(registro);
        if (id != null){
            return OwnersPetsDetailsModel.cargarDetalle(id);
        }
        return new ArrayList<>();
    }

    public void actualizarDetalle(int registro, String tipoPropietario, String idMascota, String idPropietario) {
        String id = capturarIdLista(registro);
        if (id != null) {
            int resultado = OwnersPetsDetailsModel.actualizarDetalle(id, tipoPropietario, idMascota, idPropietario);
            if (resultado > 0) {
                System.out.println("Registro actualizado correctamente.");
            } else {
                System.out.println("Error al actualizar el registro.");
            }
        } else {
            System.out.println("Registro inexistente.");
        }
    }

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

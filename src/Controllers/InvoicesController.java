package src.Controllers;

import src.Models.InvoicesModel;
import src.Models.UsersModel;
import java.util.ArrayList;
import java.util.List;

public class InvoicesController extends OwnersController{

    public InvoicesController() {
        super();
        listaPropietarios = new ArrayList<>();
        listaFacturas = new ArrayList<>();
    }

    private int numeroFactura;
    private String fechaEmisionFactura;
    private String horaEmisionFactura;
    private String idPropietario;
    private Boolean visibilidadFactura;

    private List<List<String>> listaPropietarios;
    private List<List<String>> listaFacturas;

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getFechaEmisionFactura() {
        return fechaEmisionFactura;
    }

    public void setFechaEmisionFactura(String fechaEmisionFactura) {
        this.fechaEmisionFactura = fechaEmisionFactura;
    }

    public String getHoraEmisionFactura() {
        return horaEmisionFactura;
    }

    public void setHoraEmisionFactura(String horaEmisionFactura) {
        this.horaEmisionFactura = horaEmisionFactura;
    }

    public String getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(String idPropietario) {
        this.idPropietario = idPropietario;
    }

    public Boolean getVisibilidadFactura() {
        return visibilidadFactura;
    }

    public void setVisibilidadFactura(Boolean visibilidadFactura) {
        this.visibilidadFactura = visibilidadFactura;
    }

    public List<List<String>> listaPropietarios() {
        return listaPropietarios;
    }

    public List<List<String>> listaFacturas() {
        return listaFacturas;
    }

    public String capturarNombres(String idPersona) {
        for (List<String> propietario : listaPropietarios()) {
            if (propietario.get(1).trim().equalsIgnoreCase(idPersona.trim())) {
                for (List<String> persona : listaPersonas()) {
                    if (persona.get(0).trim().equalsIgnoreCase(idPersona.trim())) {
                        return persona.get(1) + " " + persona.get(2);
                    }
                }
            }
        }
        return "Desconocido";
    }

    public void llenarListas(){
        listaFacturas = InvoicesModel.cargarListaFacturas();
        listaPropietarios = InvoicesModel.cargarListaPropietarios();
        cargarListaPersonas();
    }

    public String capturarIdListaFactura(int numero) {
        if (numero > 0 && numero <= listaFacturas.size()) {
            return listaFacturas.get(numero - 1).get(0);
        }
        return null;
    }

    public int crearFactura() {
        return InvoicesModel.crearNuevaFactura(fechaEmisionFactura, horaEmisionFactura, idPropietario, visibilidadFactura);
    }

    public void desactivarFactura(int numero){
        String id = this.capturarIdListaFactura(numero);
        if (id != null) {
            int resultado = InvoicesModel.desactivarFactura(id);
            if (resultado > 0) {
                System.out.println("Factura desactivada correctamente.");
            } else {
                System.out.println("Error al desactivar el registro.");
            }
        } else {
            System.out.println("Registro inexistente.");
        }
    }
}

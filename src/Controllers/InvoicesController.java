package src.Controllers;

import src.Models.InvoicesModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con las facturas,
 * incluyendo su creación, desactivación y carga de datos relacionados
 * @see OwnersController
 */
public class InvoicesController extends OwnersController {

    /**
     * Constructor que inicializa las listas de propietarios y facturas
     */
    public InvoicesController() {
        super();
        listaPropietarios = new ArrayList<>();
        listaFacturas = new ArrayList<>();
    }

    /**
     * Atributos de la factura
     */
    private int numeroFactura;
    private String fechaEmisionFactura;
    private String horaEmisionFactura;
    private String idPropietario;
    private Boolean visibilidadFactura;

    /**
     * Listas de listas: Propietarios y Facturas
     */
    private List<List<String>> listaPropietarios;
    private List<List<String>> listaFacturas;

    /**
     * Getter del número de factura
     * @return número de factura
     */
    public int getNumeroFactura() {
        return numeroFactura;
    }

    /**
     * Setter del número de factura
     * @param numeroFactura número de factura
     */
    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    /**
     * Getter de la fecha de emisión
     * @return fecha de emisión
     */
    public String getFechaEmisionFactura() {
        return fechaEmisionFactura;
    }

    /**
     * Setter de la fecha de emisión
     * @param fechaEmisionFactura fecha de emisión
     */
    public void setFechaEmisionFactura(String fechaEmisionFactura) {
        this.fechaEmisionFactura = fechaEmisionFactura;
    }

    /**
     * Getter de la hora de emisión
     * @return hora de emisión
     */
    public String getHoraEmisionFactura() {
        return horaEmisionFactura;
    }

    /**
     * Setter de la hora de emisión
     * @param horaEmisionFactura hora de emisión
     */
    public void setHoraEmisionFactura(String horaEmisionFactura) {
        this.horaEmisionFactura = horaEmisionFactura;
    }

    /**
     * Getter del ID del propietario
     * @return ID del propietario
     */
    public String getIdPropietario() {
        return idPropietario;
    }

    /**
     * Setter del ID del propietario
     * @param idPropietario ID del propietario
     */
    public void setIdPropietario(String idPropietario) {
        this.idPropietario = idPropietario;
    }

    /**
     * Getter de la visibilidad de la factura
     * @return true si está visible, false si está desactivada
     */
    public Boolean getVisibilidadFactura() {
        return visibilidadFactura;
    }

    /**
     * Setter de la visibilidad de la factura
     * @param visibilidadFactura visibilidad
     */
    public void setVisibilidadFactura(Boolean visibilidadFactura) {
        this.visibilidadFactura = visibilidadFactura;
    }

    /**
     * Lista general de propietarios
     * @return lista de listas de propietarios
     */
    public List<List<String>> listaPropietarios() {
        return listaPropietarios;
    }

    /**
     * Lista general de facturas
     * @return lista de listas de facturas
     */
    public List<List<String>> listaFacturas() {
        return listaFacturas;
    }

    /**
     * Captura el nombre completo de un propietario según el id
     * @param idPersona id de la persona
     * @return nombre completo de la persona
     */
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

    /**
     * Llena las listas de facturas, propietarios y personas
     * @see InvoicesModel
     */
    public void llenarListas() {
        listaFacturas = InvoicesModel.cargarListaFacturas();
        listaPropietarios = InvoicesModel.cargarListaPropietarios();
        cargarListaPersonas();
    }

    /**
     * Captura el id real de la lista de facturas
     * @param numero número de registro
     * @return id de la factura
     */
    public String capturarIdListaFactura(int numero) {
        if (numero > 0 && numero <= listaFacturas.size()) {
            return listaFacturas.get(numero - 1).get(0);
        }
        return null;
    }

    /**
     * Crea una nueva factura
     * @return número de filas afectadas
     */
    public int crearFactura() {
        return InvoicesModel.crearNuevaFactura(fechaEmisionFactura, horaEmisionFactura, idPropietario, visibilidadFactura);
    }

    /**
     * Desactiva una factura específica por su número de registro
     * @param numero número de registro de la factura
     * @see InvoicesModel
     */
    public void desactivarFactura(int numero) {
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

package src.Controllers;

import src.Models.InvoicesModel;
import src.Models.UsersModel;
import java.util.ArrayList;
import java.util.List;

/*
 * Este controlador extiende a OwnersController para aprovechar la estion de propietarios y personas
 * Se encarga de interactuar con InvoicesModel para crear y desactivar facturas
 * Ademas, se relaciona la informacion del propietario y la persona
 */
public class InvoicesController extends OwnersController{

    /*
     * Constructor para inicializar las listas de propietarios y facturas
     */
    public InvoicesController() {
        super();
        listaPropietarios = new ArrayList<>();
        listaFacturas = new ArrayList<>();
    }

    // Atributos especificos de la factura
    private int numeroFactura;
    private String fechaEmisionFactura;
    private String horaEmisionFactura;
    private String idPropietario;
    private Boolean visibilidadFactura;

    // Almacenar registros de propietarios y facturas
    private List<List<String>> listaPropietarios;
    private List<List<String>> listaFacturas;

    //Getters y setters
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

    /*
     * Retorna la lista de propietarios cargados (heredada)
     */
    public List<List<String>> listaPropietarios() {
        return listaPropietarios;
    }

    /*
     * Retorna de facturas cargadas
     */
    public List<List<String>> listaFacturas() {
        return listaFacturas;
    }

    /*
     * Captura el nombre completo relacionado con el id de la persona
     * Itera la lista de propietarios y la lista de personas (heredad de PeopleController)
     * El parametro es el id de la persona
     * Retorna el nombre completo, o "Desconocido" si no se encuentra
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

    /*
     * Llena la listas de facturas y propietarios. Ademas, carga la lista de personas
     */
    public void llenarListas(){
        listaFacturas = InvoicesModel.cargarListaFacturas();
        listaPropietarios = InvoicesModel.cargarListaPropietarios();
        // Metodo heredado de PeopleController
        cargarListaPersonas();
    }

    /*
     * Captura el id de la factura
     * El parametro es el numero indice en la lista de facturas
     * Retorna el numero de facturas, el id, si existe, o null si no
     */
    public String capturarIdListaFactura(int numero) {
        if (numero > 0 && numero <= listaFacturas.size()) {
            return listaFacturas.get(numero - 1).get(0);
        }
        return null;
    }

    /*
     * Crea una factura neuva. LLama al modelo
     * Retorna el numero de filas afectadas
     */
    public int crearFactura() {
        return InvoicesModel.crearNuevaFactura(fechaEmisionFactura, horaEmisionFactura, idPropietario, visibilidadFactura);
    }
    /*
     * Desactiva una factura con base a su posicion en la lista
     */
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

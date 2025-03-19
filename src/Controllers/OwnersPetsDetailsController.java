package src.Controllers;

import src.Models.ConsultationsModel;
import src.Models.OwnersPetsDetailsModel;

import java.util.ArrayList;
import java.util.List;

/*
 * Controlador que extiende OwnersController para gestionar el detalle de propietarios con mascotas
 * Facilita metodos para llenar listas de detalles, mascotas y propietarios
 * Captura informacion especifica de estas listas
 * Hereda funcionalidades de OwnersController
 */
public class OwnersPetsDetailsController extends OwnersController{

    /*
     * Constructor para inicializar las listas de detalles, mascotas y propietarios
     */
    public OwnersPetsDetailsController() {
        super();
        listaDetalles = new ArrayList<>();
        listaMascotas = new ArrayList<>();
        listaPropietarios = new ArrayList<>();
    }
    // Atributos especificos
    private String idDetallePropietarioMascota;
    private String tipoPropietario;
    private String idMascota;
    private String idPropietario;

    //Listas que almacenan los registros cargados de la base de datos
    private List<List<String>> listaDetalles;
    private List<List<String>> listaMascotas;
    private List<List<String>> listaPropietarios;

    // Metodos getter y setter
    public String getIdDetallePropietarioMascota() {
        return idDetallePropietarioMascota;
    }

    public void setIdDetallePropietarioMascota(String idDetallePropietarioMascota) {
        this.idDetallePropietarioMascota = idDetallePropietarioMascota;
    }

    public String getTipoPropietario() {
        return tipoPropietario;
    }

    public void setTipoPropietario(String tipoPropietario) {
        this.tipoPropietario = tipoPropietario;
    }

    public String getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(String idMascota) {
        this.idMascota = idMascota;
    }

    public String getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(String idPropietario) {
        this.idPropietario = idPropietario;
    }
    // Metodos de acceso a la lista
    /*
     * Retorna una lista de detalles de propietarios con mascotas
     */
    public List<List<String>> listaDetalles() {
        return listaDetalles;
    }

    /*
     * Retorna la lista de mascotas
     */
    public List<List<String>> listaMascotas() {
        return listaMascotas;
    }

    /*
     * Retorna la lista de propietarios
     */
    public List<List<String>> listaPropietarios() {
        return listaPropietarios;
    }
    
    /*
     * Llena la listas de detalles, mascotas y propietarios con el modelo
     * Por otro lado, hereda el metodo cargarListaPersonas() para actualizar la lista de personas
     */
    public void llenarListas(){
        listaDetalles = OwnersPetsDetailsModel.cargarListaDetalles();
        listaMascotas = OwnersPetsDetailsModel.cargarListaMascotas();
        listaPropietarios = OwnersPetsDetailsModel.cargarListaPropietarios();
        cargarListaPersonas();
    }

    // Metodos de captura de ids y nombres
    /*
     * Captura el id de un registro de detalle con base a su posicion en la lista
     * El parametro es el numero indice del registro
     * Retorna el id del registro si existe y si no, null
     */
    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaDetalles.size()) {
            return listaDetalles.get(numero - 1).get(0);
        }
        return null;
    }
    /*
     * Captura el id de una mascota con base a su posicion en la lista
     * El parametro es el numero indice en la lista de mascotas
     * Retorna el id de la mascota si existe o null si no
     */
    public String capturarIdListaMascotas(int numero) {
        if (numero > 0 && numero <= listaMascotas.size()) {
            return listaMascotas.get(numero - 1).get(0);
        }
        return null;
    }

    /*
     * Captura el id de un propietario con base a su posicion en la lista
     * El parametro es el numero indice del registro
     * Retorna el id del propietario si existe y si no, null
     */
    public String capturarIdListaPropietarios(int numero) {
        if (numero > 0 && numero <= listaPropietarios.size()) {
            return listaPropietarios.get(numero - 1).get(0);
        }
        return null;
    }

    /*
     * Busca y retorna el nombre completo del propietario relacionado a un id de persona
     * Recorre la lista de propietarios, y para el registro que encuentra, recorre la lista de personas
     * (heredada de PeopleController) para obtener el nombre y el apellido
     */
    public String capturarNombresPropietarios(String idPersona) {
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
     * Busca y retorna el nombre de la mascota a partir de su id
     * El parametro es el nombre de la mascota a bsucar
     * Retorna el nombre de la mascota si existe; si no, "No encontrada"
     */
    public String capturarMascotas(String idMascota){
        for (List<String> mascota : listaMascotas()) {
            if (mascota.get(0).trim().equalsIgnoreCase(idMascota)) {
                return mascota.get(1);
            }
        }
        return "No encontrada";
    }

    public int registrarDetalle() {
        return OwnersPetsDetailsModel.ingresarNuevoDetalle(tipoPropietario, idMascota, idPropietario);
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

    public void eliminarDetalle(int numero){
        String id = this.capturarIdLista(numero);
        if (id != null) {
            int resultado = OwnersPetsDetailsModel.eliminarDetalle(id);
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

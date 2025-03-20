package src.Controllers;

import src.Models.OwnersModel;

import java.util.ArrayList;
import java.util.List;

/*
 * Controlador para la entidad Propietarios (Owners)
 * Extiende las funcionalidades del controlador de People (PeopleController)
 * Interactua con OwnerModel para gestionar propietarios y cuidades
 */
public class OwnersController extends PeopleController{

    /*
     * Constructor para inicializar las listas de propietarios y ciudades
     */
    public OwnersController() {
        super();
        listaPropietarios = new ArrayList<>();
        listaCiudades = new ArrayList<>();
    }
    // Atributos especificos para Owners
    private String idPropietario;
    private String idPersona;
    private String idCiudad;
    private String direccion;
    private Boolean visibilidadPropietario;

    //Listas para almacenar los registros de propietarios y cuidades
    private List<List<String>> listaPropietarios;
    private List<List<String>> listaCiudades;

    // Getters y setters
    public String getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(String idPropietario) {
        this.idPropietario = idPropietario;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(String idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Boolean getVisibilidadPropietario() {
        return visibilidadPropietario;
    }

    public void setVisibilidadPropietario(Boolean visibilidadPropietario) {
        this.visibilidadPropietario = visibilidadPropietario;
    }

    /*
     * Captura el nombre completo de la persona relacionada al propietario
     * El parametro es el id de la persona
     * Retorna el nombre completo, o "Desconocido" si no se encuentra
     */
    public String capturarNombres(String idPersona) {
        // Itera la lsita de personas (heredada de PeopleController)
        for (List<String> persona : listaPersonas()) {
            if (persona.get(0).trim().equalsIgnoreCase(idPersona.trim())) {
                return persona.get(1) + " " + persona.get(2);
            }
        }
        return "Desconocido";
    }

    /*
     * Captura el nombre de la ciudad con base al id
     * El parametro es el id de la ciudad
     * Retorna el nombre de la ciudad, o "Sin ciudad" si no se encuentra
     */
    public String capturarCiudad(String idCiudad){
        for (List<String> ciudad : listaCiudades) {
            if (ciudad.get(0).trim().equalsIgnoreCase(idCiudad)) {
                return ciudad.get(1);
            }
        }
        return "Sin ciudad";
    }

    /*
     * Captura el id del propietario a partir de su posicion en la lista
     * El parametro es el numero indice en la lista
     * Retorna el id si existe, o null en caso que no
     */
    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaPropietarios.size()) {
            return listaPropietarios.get(numero - 1).get(0);
        }
        return null;
    }

    /*
     * Captura el id de la ciudad con base a su posicion en la lista
     * El parametro es el numero indice en la lista de especialidades
     * Retorna el id de la ciudad o null si no
     */
    public String capturarIdListaCiudad(int numero) {
        if (numero > 0 && numero <= listaCiudades.size()) {
            return listaCiudades.get(numero - 1).get(0);
        }
        return null;
    }

    /*
     * Llena las listas de  propietarios y ciudades, y carga la lista de las personas
     */
    public void llenarListas(){
        listaPropietarios = OwnersModel.cargarListaPropietarios();
        listaCiudades = OwnersModel.cargarListaCiudades();
        // Metodo heredado de PeopleController para cargar la lista de personas
        cargarListaPersonas();
    }

    /*
     * Retorna la lista de propietarios cargados
     */
    public List<List<String>> listaPropietarios() {
        return listaPropietarios;
    }

    /*
     * Retorna la lista de ciudades cargadas
     */
    public List<List<String>> listaCiudades() {
        return listaCiudades;
    }

    /*
     * Registra un nuevo propietario en la base de datos con los atributos actuales
     * Retorna el numero de filas afectadas
     */
    public int RegistrarPropietario() {
        return OwnersModel.ingresarNuevoPropietario(idPersona, idCiudad, direccion, visibilidadPropietario);
    }

    /*
     * Carga los datos de un propietario especifico con base a su posicion en la lista
     * El parametro es el registro indice en la lista
     * Retorna la lista con los datos del propietario, o lista vacia si no se encuentra
     */
    public List<String> cargarDatosPropietario(int registro){
        String id = capturarIdLista(registro);
        if (id != null){
            return OwnersModel.cargarPropietario(id);
        }
        return new ArrayList<>();
    }

    /*
     * Actualiza los datos de un propietario
     */
    public void actualizarPropietario(int registro, String idPersona, String idCiudad, String direccion, Boolean visibilidadPropietario) {
        String id = capturarIdLista(registro);
        if (id != null) {
            int resultado = OwnersModel.actualizarPropietario(id, idPersona, idCiudad, direccion, visibilidadPropietario);
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
     * Desactiva un porpietario con base a su posicion en la lista (eliminar)
     */
    public void desactivarPropietario(int numero){
        String id = this.capturarIdLista(numero);
        if (id != null) {
            int resultado = OwnersModel.desactivarPropietario(id);
            if (resultado > 0) {
                System.out.println("Propietario eliminado correctamente.");
            } else {
                System.out.println("Error al eliminar el registro.");
            }
        } else {
            System.out.println("Registro inexistente.");
        }
    }
}

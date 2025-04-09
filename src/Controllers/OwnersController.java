package src.Controllers;

import src.Models.OwnersModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con los propietarios,
 * incluyendo la obtención de datos, inserción, actualización y desactivación
 * @see PeopleController
 */
public class OwnersController extends PeopleController {

    /**
     * Constructor que inicializa las listas de propietarios y ciudades
     */
    public OwnersController() {
        super();
        listaPropietarios = new ArrayList<>();
        listaCiudades = new ArrayList<>();
    }

    /**
     * Atributos de los propietarios
     */
    private String idPropietario;
    private String idPersona;
    private String idCiudad;
    private String direccion;
    private Boolean visibilidadPropietario;

    /**
     * Listas de listas: propietarios y ciudades
     */
    private List<List<String>> listaPropietarios;
    private List<List<String>> listaCiudades;

    /**
     * Getter del id del propietario
     * @return idPropietario
     */
    public String getIdPropietario() {
        return idPropietario;
    }

    /**
     * Setter del id del propietario
     * @param idPropietario id del propietario
     */
    public void setIdPropietario(String idPropietario) {
        this.idPropietario = idPropietario;
    }

    /**
     * Getter del id de persona
     * @return idPersona
     */
    public String getIdPersona() {
        return idPersona;
    }

    /**
     * Setter del id de persona
     * @param idPersona id de la persona
     */
    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    /**
     * Getter id de ciudad
     * @return idCiudad
     */
    public String getIdCiudad() {
        return idCiudad;
    }

    /**
     * Setter id de ciudad
     * @param idCiudad id de la ciudad
     */
    public void setIdCiudad(String idCiudad) {
        this.idCiudad = idCiudad;
    }

    /**
     * Getter de la dirección del propietario
     * @return direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Setter de la dirección del propietario
     * @param direccion dirección
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Getter de la visibilidad del propietario
     * @return visibilidadPropietario
     */
    public Boolean getVisibilidadPropietario() {
        return visibilidadPropietario;
    }

    /**
     * Setter de la visibilidad del propietario
     * @param visibilidadPropietario visibilidad del propietario
     */
    public void setVisibilidadPropietario(Boolean visibilidadPropietario) {
        this.visibilidadPropietario = visibilidadPropietario;
    }

    /**
     * Captura el nombre de la persona dado su id
     * @param idPersona id de la persona
     * @return nombre completo o "Desconocido" si no se encuentra
     */
    public String capturarNombres(String idPersona) {
        for (List<String> persona : listaPersonas()) {
            if (persona.get(0).trim().equalsIgnoreCase(idPersona.trim())) {
                return persona.get(1) + " " + persona.get(2);
            }
        }
        return "Desconocido";
    }

    /**
     * Captura el nombre de la ciudad dado su id
     * @param idCiudad id de la ciudad
     * @return nombre de la ciudad o "Sin ciudad" si no se encuentra
     */
    public String capturarCiudad(String idCiudad) {
        for (List<String> ciudad : listaCiudades) {
            if (ciudad.get(0).trim().equalsIgnoreCase(idCiudad)) {
                return ciudad.get(1);
            }
        }
        return "Sin ciudad";
    }

    /**
     * Captura el id del propietario según su número en consola
     * @param numero número del registro en consola
     * @return id del propietario o null si no existe
     */
    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaPropietarios.size()) {
            return listaPropietarios.get(numero - 1).get(0);
        }
        return null;
    }

    /**
     * Captura el id de la ciudad según su número en consola
     * @param numero número del registro en consola
     * @return id de la ciudad o null si no existe
     */
    public String capturarIdListaCiudad(int numero) {
        if (numero > 0 && numero <= listaCiudades.size()) {
            return listaCiudades.get(numero - 1).get(0);
        }
        return null;
    }

    /**
     * Llena las listas de propietarios y ciudades
     * @see OwnersModel
     */
    public void llenarListas(){
        listaPropietarios = OwnersModel.cargarListaPropietarios();
        listaCiudades = OwnersModel.cargarListaCiudades();
        cargarListaPersonas();
    }

    /**
     * Lista de listas de propietarios
     * @return listaPropietarios
     */
    public List<List<String>> listaPropietarios() {
        return listaPropietarios;
    }

    /**
     * Lista de listas de ciudades
     * @return listaCiudades
     */
    public List<List<String>> listaCiudades() {
        return listaCiudades;
    }

    /**
     * Registra un nuevo propietario
     * @return número de filas afectadas
     */
    public int RegistrarPropietario() {
        return OwnersModel.ingresarNuevoPropietario(idPersona, idCiudad, direccion, visibilidadPropietario);
    }

    /**
     * Carga los datos de un propietario dado su número de registro
     * @param registro número de registro en consola
     * @return lista de datos del propietario
     */
    public List<String> cargarDatosPropietario(int registro){
        String id = capturarIdLista(registro);
        if (id != null){
            return OwnersModel.cargarPropietario(id);
        }
        return new ArrayList<>();
    }

    /**
     * Actualiza los datos de un propietario
     * @param registro número del registro en consola
     * @param idPersona nuevo id de persona
     * @param idCiudad nuevo id de ciudad
     * @param direccion nueva dirección
     * @param visibilidadPropietario nueva visibilidad
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

    /**
     * Desactiva un propietario (actualiza su visibilidad)
     * @param numero número del registro en consola
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
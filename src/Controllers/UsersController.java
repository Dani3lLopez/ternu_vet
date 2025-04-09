package src.Controllers;

import src.Models.UsersModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con los usuarios,
 * extendiendo las funcionalidades de los doctores. Permite la creación, actualización,
 * desactivación y consulta de usuarios
 * @see DoctorsController
 */
public class UsersController extends DoctorsController {

    /**
     * Constructor vacío del controlador de usuarios
     * Inicializa las listas de usuarios, doctores y doctores sin usuario
     */
    public UsersController() {
        super();
        listaUsuarios = new ArrayList<>();
        listaDoctores = new ArrayList<>();
        listaDoctoresSinUsuario = new ArrayList<>();
    }

    /**
     * Atributos de un usuario
     */
    private String idUsuario;
    private String nombreUsuario;
    private String claveUsuario;
    private String estadoUsuario;
    private Boolean administrador;
    private String idDoctor;
    private List<List<String>> listaUsuarios;
    private List<List<String>> listaDoctores;
    private List<List<String>> listaDoctoresSinUsuario;

    /**
     * Getter del id del usuario
     * @return idUsuario
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * Setter del id del usuario
     * @param idUsuario id del usuario
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Getter del nombre del usuario
     * @return nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Setter del nombre del usuario
     * @param nombreUsuario nombre del usuario
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Getter de la clave del usuario
     * @return claveUsuario
     */
    public String getClaveUsuario() {
        return claveUsuario;
    }

    /**
     * Setter de la clave del usuario
     * @param claveUsuario clave del usuario
     */
    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    /**
     * Getter del estado del usuario
     * @return estadoUsuario
     */
    public String getEstadoUsuario() {
        return estadoUsuario;
    }

    /**
     * Setter del estado del usuario
     * @param estadoUsuario estado del usuario
     */
    public void setEstadoUsuario(String estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    /**
     * Getter del valor de administrador del usuario
     * @return administrador
     */
    public Boolean getAdministrador() {
        return administrador;
    }

    /**
     * Setter del valor de administrador del usuario
     * @param administrador valor de administrador
     */
    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }

    /**
     * Getter del id del doctor asociado al usuario
     * @return idDoctor
     */
    public String getIdDoctor() {
        return idDoctor;
    }

    /**
     * Setter del id del doctor asociado al usuario
     * @param idDoctor id del doctor
     */
    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
    }

    /**
     * Captura los nombres completos de un doctor dado su id de persona
     * @param idPersona id de la persona a buscar
     * @return nombre completo del doctor o "Desconocido" si no se encuentra
     */
    public String capturarNombres(String idPersona) {
        for (List<String> doctor : listaDoctores()) {
            if (doctor.get(1).trim().equalsIgnoreCase(idPersona.trim())) { // Compara el ID de persona del doctor
                for (List<String> persona : listaPersonas()) { // Busca a la persona dentro de la lista respectiva
                    if (persona.get(0).trim().equalsIgnoreCase(idPersona.trim())) {
                        return persona.get(1) + " " + persona.get(2); // Devuelve el nombre completo de la persona
                    }
                }
            }
        }
        return "Desconocido";
    }

    /**
     * Llena las listas de usuarios, doctores y doctores sin usuario
     * @see UsersModel
     */
    public void llenarListas() {
        listaUsuarios = UsersModel.cargarListaUsuarios();
        listaDoctores = UsersModel.cargarListaDoctores();
        listaDoctoresSinUsuario = UsersModel.cargarDoctoresSinUsuario();
        cargarListaPersonas();
    }

    /**
     * Retorna la lista de usuarios
     * @return listaUsuarios
     */
    public List<List<String>> listaUsuarios() {
        return listaUsuarios;
    }

    /**
     * Retorna la lista de doctores
     * @return listaDoctores
     */
    public List<List<String>> listaDoctores() {
        return listaDoctores;
    }

    /**
     * Retorna la lista de doctores sin usuario
     * @return listaDoctoresSinUsuario
     */
    public List<List<String>> listaDoctoresSinUsuario() {
        return listaDoctoresSinUsuario;
    }

    /**
     * Captura el id de usuario dado su número en la lista
     * @param numero número del registro en consola
     * @return id del usuario o null si no existe
     */
    public String capturarIdListaUsuario(int numero) {
        if (numero > 0 && numero <= listaUsuarios.size()) {
            return listaUsuarios.get(numero - 1).get(0);
        }
        return null;
    }

    /**
     * Verifica si un usuario con el nombre proporcionado ya existe
     * @param nombreUsuario nombre del usuario a verificar
     * @return true si el usuario existe, false en caso contrario
     */
    public boolean existenciaUsuario(String nombreUsuario) {
        return UsersModel.existenciaUsuario(nombreUsuario);
    }

    /**
     * Registra un nuevo usuario en el sistema
     * @return número de filas afectadas
     */
    public int RegistrarUsuario() {
        return UsersModel.ingresarNuevoUsuario(nombreUsuario, claveUsuario, estadoUsuario, administrador, idDoctor);
    }

    /**
     * Carga los datos de un usuario dado su registro
     * @param registro número de registro del usuario
     * @return lista con los datos del usuario o una lista vacía si no se encuentra
     */
    public List<String> cargarDatosUsuario(int registro) {
        String id = capturarIdListaUsuario(registro);
        if (id != null) {
            return UsersModel.cargarUsuario(id);
        }
        return new ArrayList<>();
    }

    /**
     * Actualiza los datos de un usuario existente
     * @param registro número de registro del usuario
     * @param nombreUsuario nombre del usuario
     * @param claveUsuario clave del usuario
     * @param estadoUsuario estado del usuario
     * @param administrador valor que indica si el usuario es administrador
     * @param idDoctor id del doctor asociado
     */
    public void actualizarUsuario(int registro, String nombreUsuario, String claveUsuario, String estadoUsuario,
                                  int administrador, String idDoctor) {
        String id = capturarIdListaUsuario(registro);
        if (id != null) {
            int resultado = UsersModel.actualizarUsuario(id, nombreUsuario, claveUsuario, estadoUsuario, administrador,
                    idDoctor);
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
     * Desactiva un usuario dado su número en la lista
     * @param numero número del registro en consola
     */
    public void desactivarUsuario(int numero) {
        String id = this.capturarIdListaUsuario(numero);
        if (id != null) {
            int resultado = UsersModel.desactivarUsuario(id);
            if (resultado > 0) {
                System.out.println("Usuario desactivado correctamente.");
            } else {
                System.out.println("Error al desactivar el registro.");
            }
        } else {
            System.out.println("Registro inexistente.");
        }
    }
}

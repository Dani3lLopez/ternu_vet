package src.Controllers;

import src.Models.UsersModel;

import java.util.ArrayList;
import java.util.List;

public class UsersController extends DoctorsController {

    public UsersController() {
        super();
        listaUsuarios = new ArrayList<>();
        listaDoctores = new ArrayList<>();
        listaDoctoresSinUsuario = new ArrayList<>();
    }

    // Creamos atributos privatodos, que nos serviran para almacenar los datos de un
    // usuario
    private String idUsuario;
    private String nombreUsuario;
    private String claveUsuario;
    private String estadoUsuario;
    private Boolean administrador;
    private String idDoctor;
    private List<List<String>> listaUsuarios; // Lista de listas para almacenar los datos de los usuarios
    private List<List<String>> listaDoctores; // Lista de lista para los de doctores
    private List<List<String>> listaDoctoresSinUsuario; // Lista de lista para los de doctores sin un usuario

    // Getters y setters para cada uno de los atributos
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public String getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(String estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public Boolean getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }

    public String getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
    }

    // Dado un ID de persona, captura el nombre completo de un doctor
    public String capturarNombres(String idPersona) {
        for (List<String> doctor : listaDoctores()) {
            if (doctor.get(1).trim().equalsIgnoreCase(idPersona.trim())) { // Compara el ID de persona del doctor
                for (List<String> persona : listaPersonas()) { // Busca a la persona dentro de la lista repectiva
                    if (persona.get(0).trim().equalsIgnoreCase(idPersona.trim())) {
                        return persona.get(1) + " " + persona.get(2); // Devuelve el nombre completo de la persona
                    }
                }
            }
        }
        return "Desconocido";
    }

    // LLena todas las 3 listas que estamos utilizando
    public void llenarListas() {
        listaUsuarios = UsersModel.cargarListaUsuarios();
        listaDoctores = UsersModel.cargarListaDoctores();
        listaDoctoresSinUsuario = UsersModel.cargarDoctoresSinUsuario();
        cargarListaPersonas();
    }

    // Métodos para las tres listas
    public List<List<String>> listaUsuarios() {
        return listaUsuarios;
    }

    public List<List<String>> listaDoctores() {
        return listaDoctores;
    }

    public List<List<String>> listaDoctoresSinUsuario() {
        return listaDoctoresSinUsuario;
    }

    // Dado un número de registro, guarda el ID de un usuario
    public String capturarIdListaUsuario(int numero) {
        if (numero > 0 && numero <= listaUsuarios.size()) {
            return listaUsuarios.get(numero - 1).get(0);
        }
        return null;
    }

    // Comprueba que exista un usuario con dicho nombre
    public boolean existenciaUsuario(String nombreUsuario) {
        return UsersModel.existenciaUsuario(nombreUsuario);
    }

    // Utilizando el modelo, registra un nuevo usuario
    public int RegistrarUsuario() {
        return UsersModel.ingresarNuevoUsuario(nombreUsuario, claveUsuario, estadoUsuario, administrador, idDoctor);
    }

    // Dado un número de registro, carga los datos de un usuario específico
    public List<String> cargarDatosUsuario(int registro) {
        String id = capturarIdListaUsuario(registro);
        if (id != null) {
            return UsersModel.cargarUsuario(id);
        }
        return new ArrayList<>();
    }

    // Actualiza los cambios hechos de un usuario existente
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

    // Dado un número de registro, desactiva un usuario
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

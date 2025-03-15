package src.Controllers;

import src.Models.UsersModel;

import java.util.ArrayList;
import java.util.List;

public class UsersController extends PeopleController {

    public UsersController(){
        super();
        listaUsuarios = new ArrayList<>();
        listaDoctores = new ArrayList<>();
    }

    private String idUsuario;
    private String nombreUsuario;
    private String claveUsuario;
    private String estadoUsuario;
    private Boolean administrador;
    private String idDoctor;
    private List<List<String>> listaUsuarios;
    private List<List<String>> listaDoctores;

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

    public String capturarNombres(String idPersona) {
        for (List<String> doctor : listaDoctores()) {
            if (doctor.get(1).trim().equalsIgnoreCase(idPersona.trim())) { // Compara id_persona
                for (List<String> persona : listaPersonas()) {
                    if (persona.get(0).trim().equalsIgnoreCase(idPersona.trim())) {
                        return persona.get(1) + " " + persona.get(2); // Obtiene el nombre de la persona
                    }
                }
            }
        }
        return "Desconocido";
    }

    public String capturarNombreDoctor(String idPersona) {
        for (List<String> doctor : listaDoctores()) {
            if (doctor.get(1).trim().equalsIgnoreCase(idPersona.trim())) {
                return capturarNombres(idPersona);
            }
        }
        return "Desconocido";
    }

    public void llenarListas(){
        listaUsuarios = UsersModel.cargarListaUsuarios();
        listaDoctores = UsersModel.cargarListaDoctores();
        cargarListaPersonas();
    }

    public List<List<String>> listaUsuarios() {
        return listaUsuarios;
    }
    public List<List<String>> listaDoctores() {
        return listaDoctores;
    }
}

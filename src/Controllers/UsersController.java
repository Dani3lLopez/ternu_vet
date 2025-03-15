package src.Controllers;

import src.Models.DoctorsModel;
import src.Models.OwnersModel;
import src.Models.UsersModel;

import java.util.ArrayList;
import java.util.List;

public class UsersController extends PeopleController {

    public UsersController(){
        super();
        listaUsuarios = new ArrayList<>();
        listaDoctores = new ArrayList<>();
        listaDoctoresSinUsuario = new ArrayList<>();
    }

    private String idUsuario;
    private String nombreUsuario;
    private String claveUsuario;
    private String estadoUsuario;
    private Boolean administrador;
    private String idDoctor;
    private List<List<String>> listaUsuarios;
    private List<List<String>> listaDoctores;
    private List<List<String>> listaDoctoresSinUsuario;

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
            if (doctor.get(1).trim().equalsIgnoreCase(idPersona.trim())) {
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
        listaUsuarios = UsersModel.cargarListaUsuarios();
        listaDoctores = UsersModel.cargarListaDoctores();
        listaDoctoresSinUsuario = UsersModel.cargarDoctoresSinUsuario();
        cargarListaPersonas();
    }

    public List<List<String>> listaUsuarios() {
        return listaUsuarios;
    }
    public List<List<String>> listaDoctores() {
        return listaDoctores;
    }
    public List<List<String>> listaDoctoresSinUsuario() {
        return listaDoctoresSinUsuario;
    }

    public String capturarIdListaUsuario(int numero) {
        if (numero > 0 && numero <= listaUsuarios.size()) {
            return listaUsuarios.get(numero - 1).get(0);
        }
        return null;
    }

    public boolean existenciaUsuario(String nombreUsuario) {
        return UsersModel.existenciaUsuario(nombreUsuario);
    }

    public int RegistrarUsuario() {
        return UsersModel.ingresarNuevoUsuario(nombreUsuario, claveUsuario, estadoUsuario, administrador, idDoctor);
    }

    public List<String> cargarDatosUsuario(int registro){
        String id = capturarIdListaUsuario(registro);
        if (id != null){
            return UsersModel.cargarUsuario(id);
        }
        return new ArrayList<>();
    }

    public void actualizarUsuario(int registro, String nombreUsuario, String claveUsuario, String estadoUsuario, int administrador, String idDoctor) {
        String id = capturarIdListaUsuario(registro);
        if (id != null) {
            int resultado = UsersModel.actualizarUsuario(id, nombreUsuario, claveUsuario, estadoUsuario, administrador, idDoctor);
            if (resultado > 0) {
                System.out.println("Registro actualizado correctamente.");
            } else {
                System.out.println("Error al actualizar el registro.");
            }
        } else {
            System.out.println("Registro inexistente.");
        }
    }
}

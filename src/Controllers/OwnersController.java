package src.Controllers;

import src.Models.OwnersModel;

import java.util.ArrayList;
import java.util.List;

public class OwnersController extends PeopleController{

    public OwnersController() {
        super();
        listaPropietarios = new ArrayList<>();
        listaCiudades = new ArrayList<>();
    }

    private String idPropietario;
    private String idPersona;
    private String idCiudad;
    private String direccion;
    private Boolean visibilidadPropietario;
    private List<List<String>> listaPropietarios;
    private List<List<String>> listaCiudades;

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

    public String capturarNombres(String idPersona) {
        for (List<String> persona : listaPersonas()) {
            if (persona.get(0).trim().equalsIgnoreCase(idPersona.trim())) {
                return persona.get(1) + " " + persona.get(2);
            }
        }
        return "Desconocido";
    }
    public String capturarCiudad(String idCiudad){
        for (List<String> ciudad : listaCiudades) {
            if (ciudad.get(0).trim().equalsIgnoreCase(idCiudad)) {
                return ciudad.get(1);
            }
        }
        return "Sin ciudad";
    }
    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaPropietarios.size()) {
            return listaPropietarios.get(numero - 1).get(0);
        }
        return null;
    }
    public String capturarIdListaCiudad(int numero) {
        if (numero > 0 && numero <= listaCiudades.size()) {
            return listaCiudades.get(numero - 1).get(0);
        }
        return null;
    }
    public void llenarListas(){
        listaPropietarios = OwnersModel.cargarListaPropietarios();
        listaCiudades = OwnersModel.cargarListaCiudades();
        cargarListaPersonas();
    }
    public List<List<String>> listaPropietarios() {
        return listaPropietarios;
    }
    public List<List<String>> listaCiudades() {
        return listaCiudades;
    }
    public int RegistrarPropietario() {
        return OwnersModel.ingresarNuevoPropietario(idPersona, idCiudad, direccion, visibilidadPropietario);
    }
    public List<String> cargarDatosPropietario(int registro){
        String id = capturarIdLista(registro);
        if (id != null){
            return OwnersModel.cargarPropietario(id);
        }
        return new ArrayList<>();
    }
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

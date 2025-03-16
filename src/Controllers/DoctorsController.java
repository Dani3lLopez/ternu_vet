package src.Controllers;

import src.Models.DoctorsModel;
import src.Models.OwnersModel;

import java.util.ArrayList;
import java.util.List;

public class DoctorsController extends PeopleController{
    public DoctorsController() {
        super();
        listaDoctores = new ArrayList<>();
        listaEspecialidades = new ArrayList<>();
    }

    private String idDoctor;
    private String fechaContratacionDoctor;
    private String fechaNacimientoDoctor;
    private String idPersona;
    private String idEspecialidad;
    private List<List<String>> listaDoctores;
    private List<List<String>> listaEspecialidades;

    public String getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getFechaContratacionDoctor() {
        return fechaContratacionDoctor;
    }

    public void setFechaContratacionDoctor(String fechaContratacionDoctor) {
        this.fechaContratacionDoctor = fechaContratacionDoctor;
    }

    public String getFechaNacimientoDoctor() {
        return fechaNacimientoDoctor;
    }

    public void setFechaNacimientoDoctor(String fechaNacimientoDoctor) {
        this.fechaNacimientoDoctor = fechaNacimientoDoctor;
    }

    @Override
    public String getIdPersona() {
        return idPersona;
    }

    @Override
    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(String idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public List<List<String>> listaDoctores() {
        return listaDoctores;
    }

    public List<List<String>> listaEspecialidades() {
        return listaEspecialidades;
    }

    public void llenarListas(){
        listaDoctores = DoctorsModel.cargarListaDoctores();
        listaEspecialidades = DoctorsModel.cargarListaEspecialidades();
        cargarListaPersonas();
    }

    public String capturarNombres(String idPersona) {
        for (List<String> persona : listaPersonas()) {
            if (persona.get(0).trim().equalsIgnoreCase(idPersona.trim())) {
                return persona.get(1) + " " + persona.get(2);
            }
        }
        return "Desconocido";
    }

    public String capturarEspecialidades(String idEspecialidad){
        for (List<String> especialidad : listaEspecialidades()) {
            if (especialidad.get(0).trim().equalsIgnoreCase(idEspecialidad)) {
                return especialidad.get(1);
            }
        }
        return "Sin especialidad";
    }
    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaDoctores.size()) {
            return listaDoctores.get(numero - 1).get(0);
        }
        return null;
    }
    public String capturarIdListaEspecialidad(int numero) {
        if (numero > 0 && numero <= listaEspecialidades.size()) {
            return listaEspecialidades.get(numero - 1).get(0);
        }
        return null;
    }
    public int RegistrarDoctor() {
        return DoctorsModel.ingresarNuevoDoctor(fechaContratacionDoctor, fechaNacimientoDoctor, idPersona, idEspecialidad);
    }
    public List<String> cargarDatosDoctor(int registro){
        String id = capturarIdLista(registro);
        if (id != null){
            return DoctorsModel.cargarDoctor(id);
        }
        return new ArrayList<>();
    }
    public void actualizarDoctor(int registro, String fechaContratacionDoctor, String fechaNacimientoDoctor, String idPersona, String idEspecialidad) {
        String id = capturarIdLista(registro);
        if (id != null) {
            int resultado = DoctorsModel.actualizarDoctor(id, fechaContratacionDoctor, fechaNacimientoDoctor, idPersona, idEspecialidad);
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

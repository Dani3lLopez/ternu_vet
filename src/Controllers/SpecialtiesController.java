package src.Controllers;

import src.Models.PeopleModel;
import src.Models.SpecialtiesModel;

import java.util.List;

public class SpecialtiesController {
    public SpecialtiesController() {
    }

    private String idEspecialidad;
    private String nombre_especialidad;
    private List<List<String>> listaEspecialidades;

    public String getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(String idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getNombre_especialidad() {
        return nombre_especialidad;
    }

    public void setNombre_especialidad(String nombre_especialidad) {
        this.nombre_especialidad = nombre_especialidad;
    }

    public void cargarListaEspecialidades() {
        listaEspecialidades = SpecialtiesModel.cargarListaEspecialidades();
    }

    public List<List<String>> listaEspecialidades() {
        return listaEspecialidades;
    }
}

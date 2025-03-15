package src.Controllers;

import src.Models.PeopleModel;
import src.Models.SpecialtiesModel;

import java.util.ArrayList;
import java.util.List;

public class SpecialtiesController {
    public SpecialtiesController() {
    }

    private String idEspecialidad;
    private String nombreEspecialidad;
    private List<List<String>> listaEspecialidades;

    public String getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(String idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaEspecialidades.size()) {
            return listaEspecialidades.get(numero - 1).get(0);
        }
        return null;
    }
    public void cargarListaEspecialidades() {
        listaEspecialidades = SpecialtiesModel.cargarListaEspecialidades();
    }
    public List<List<String>> listaEspecialidades() {
        return listaEspecialidades;
    }
    public int RegistrarEspecialidad() {
        return SpecialtiesModel.ingresarNuevaEspecialidad(nombreEspecialidad);
    }
    public void eliminarEspecialidad(int numero){
        String id = this.capturarIdLista(numero);
        if (id != null) {
            int resultado = SpecialtiesModel.eliminarEspecialidad(id);
            if (resultado > 0) {
                System.out.println("Especialidad eliminada correctamente.");
            } else {
                System.out.println("Error al eliminar el registro.");
            }
        } else {
            System.out.println("Registro inexistente.");
        }
    }
}

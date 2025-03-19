package src.Controllers;

import src.Models.PeopleModel;
import src.Models.SpecialtiesModel;

import java.util.ArrayList;
import java.util.List;

public class SpecialtiesController {
    public SpecialtiesController() {
    }

    // Creamos atributos privados para poder almacenar los datos de una especialidad
    private String idEspecialidad;
    private String nombreEspecialidad;
    private List<List<String>> listaEspecialidades; // En esta lista de listas se almacenaran dichos datos

    public String getIdEspecialidad() {
        return idEspecialidad;
    }

    // Getters y settters creados para cada uno de los atributos
    public void setIdEspecialidad(String idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    // Dado un número de registro, se captura el ID de una especialidad
    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaEspecialidades.size()) {
            return listaEspecialidades.get(numero - 1).get(0);
        }
        return null;
    }

    // Desde el modelo, carga la lista de especialidades
    public void cargarListaEspecialidades() {
        listaEspecialidades = SpecialtiesModel.cargarListaEspecialidades();
    }

    // Muestra la lista cargada
    public List<List<String>> listaEspecialidades() {
        return listaEspecialidades;
    }

    // Registra la nueva especialidad
    public int RegistrarEspecialidad() {
        return SpecialtiesModel.ingresarNuevaEspecialidad(nombreEspecialidad);
    }

    // Dado un número de registro, se elemina una especialidad
    public void eliminarEspecialidad(int numero) {
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

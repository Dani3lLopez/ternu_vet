package src.controllers;

import src.models.SpecialtiesModel;

import java.util.List;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con las especialidades,
 * incluyendo el registro, consulta y eliminación de datos
 * @see SpecialtiesModel
 */
public class SpecialtiesController {

    /**
     * Constructor vacío del controlador de especialidades
     */
    public SpecialtiesController() {}

    /**
     * Atributos de una especialidad
     */
    private String idEspecialidad;
    private String nombreEspecialidad;
    private List<List<String>> listaEspecialidades;

    /**
     * Getter del id de la especialidad
     * @return idEspecialidad
     */
    public String getIdEspecialidad() {
        return idEspecialidad;
    }

    /**
     * Setter del id de la especialidad
     * @param idEspecialidad id de la especialidad
     */
    public void setIdEspecialidad(String idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    /**
     * Getter del nombre de la especialidad
     * @return nombreEspecialidad
     */
    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    /**
     * Setter del nombre de la especialidad
     * @param nombreEspecialidad nombre de la especialidad
     */
    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    /**
     * Captura el id de la especialidad según su número en la lista
     * @param numero número del registro en consola
     * @return id de la especialidad o null si no existe
     */
    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaEspecialidades.size()) {
            return listaEspecialidades.get(numero - 1).get(0);
        }
        return null;
    }

    /**
     * Carga la lista de especialidades desde el modelo
     * @see SpecialtiesModel
     */
    public void cargarListaEspecialidades() {
        listaEspecialidades = SpecialtiesModel.cargarListaEspecialidades();
    }

    /**
     * Retorna la lista de especialidades
     * @return listaEspecialidades
     */
    public List<List<String>> listaEspecialidades() {
        return listaEspecialidades;
    }

    /**
     * Registra una nueva especialidad
     * @return número de filas afectadas
     */
    public int RegistrarEspecialidad() {
        return SpecialtiesModel.ingresarNuevaEspecialidad(nombreEspecialidad);
    }

    /**
     * Elimina una especialidad dado su número en la lista
     * @param numero número del registro en consola
     */
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
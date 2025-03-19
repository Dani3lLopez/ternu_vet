package src.Controllers;

import src.Models.DoctorsModel;
import src.Models.OwnersModel;

import java.util.ArrayList;
import java.util.List;

/*
 * Controlador para la entidad de doctores
 * Extiende las funcionalidades del controlador de People (PeopleController)
 * Intermediario con el modelo y la vista (archivos .java de la entidad "doctores")
 * Interactua con OwnerModel para gestionar doctores, especialidades, personas
 */
public class DoctorsController extends PeopleController{

    /*
     * Constructor para inicializar las listas de doctores y especialidades
     */
    public DoctorsController() {
        super();
        listaDoctores = new ArrayList<>();
        listaEspecialidades = new ArrayList<>();
    }
    // Atributos especificos para los doctores
    private String idDoctor;
    private String fechaContratacionDoctor;
    private String fechaNacimientoDoctor;
    private String idPersona;
    private String idEspecialidad;
    //Listas para almacenar los registros de los doctores y las especialidades
    private List<List<String>> listaDoctores;
    private List<List<String>> listaEspecialidades;

    // Getters y setters
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

    //Se sobreescriben metodos de la clase PoepleController
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

    /*
     * Retorna la lista de doctores
     */
    public List<List<String>> listaDoctores() {
        return listaDoctores;
    }

    /*
     * Retorna la lista de especialidades
     */
    public List<List<String>> listaEspecialidades() {
        return listaEspecialidades;
    }

    /*
     * Llena las listas de doctores y especialidades, y carga la lista de las personas
     */
    public void llenarListas(){
        listaDoctores = DoctorsModel.cargarListaDoctores();
        listaEspecialidades = DoctorsModel.cargarListaEspecialidades();
        cargarListaPersonas();
    }

    /*
     * Captura el nombre completo de la persona
     * El parametro es el id de la persona
     * Retorna el nombre completo, o "Desconocido" si no se encuentra
     */
    public String capturarNombres(String idPersona) {
        // Itera la lsita de personas (heredada de PeopleController)
        for (List<String> persona : listaPersonas()) {
            if (persona.get(0).trim().equalsIgnoreCase(idPersona.trim())) {
                return persona.get(1) + " " + persona.get(2);
            }
        }
        return "Desconocido";
    }

    /*
     * Captura el nombre de la especialidad con base al id
     * El parametro es el id de la especialidad
     * Retorna el nombre de la especialidad, o "Sin especialidad" si no se encuentra
     */
    public String capturarEspecialidades(String idEspecialidad){
        for (List<String> especialidad : listaEspecialidades()) {
            if (especialidad.get(0).trim().equalsIgnoreCase(idEspecialidad)) {
                return especialidad.get(1);
            }
        }
        return "Sin especialidad";
    }

    /*
     * Captura el id del doctor a partir de su posicion en la lista
     * El parametro es el numero indice en la lista
     * Retorna el id si existe, o null en caso que no
     */
    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaDoctores.size()) {
            return listaDoctores.get(numero - 1).get(0);
        }
        return null;
    }

    /*
     * Captura el id de la especialidad con base a su posicion en la lista
     * El parametro es el numero indice en la lista de especialidades
     * Retorna el id de la especialidad o null si no
     */
    public String capturarIdListaEspecialidad(int numero) {
        if (numero > 0 && numero <= listaEspecialidades.size()) {
            return listaEspecialidades.get(numero - 1).get(0);
        }
        return null;
    }

    /*
     * Registra un nuevo doctor en la base de datos con los atributos actuales
     * Retorna el numero de filas afectadas
     */
    public int RegistrarDoctor() {
        return DoctorsModel.ingresarNuevoDoctor(fechaContratacionDoctor, fechaNacimientoDoctor, idPersona, idEspecialidad);
    }

    /*
     * Carga los datos de un doctor especifico con base a su posicion en la lista
     * El parametro es el registro indice en la lista
     * Retorna la lista con los datos del doctor, o lista vacia si no se encuentra
     */
    public List<String> cargarDatosDoctor(int registro){
        String id = capturarIdLista(registro);
        if (id != null){
            return DoctorsModel.cargarDoctor(id);
        }
        return new ArrayList<>();
    }

    /*
     * Actualiza los datos de un doctor
     */
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

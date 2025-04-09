package src.Controllers;

import src.Models.DoctorsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con los doctores,
 * incluyendo la carga de datos, inserción y actualización
 * @see PeopleController
 */
public class DoctorsController extends PeopleController {

    /**
     * Constructor que inicializa las listas de doctores y especialidades
     */
    public DoctorsController() {
        super();
        listaDoctores = new ArrayList<>();
        listaEspecialidades = new ArrayList<>();
    }

    /**
     * Atributos del doctor
     */
    private String idDoctor;
    private String fechaContratacionDoctor;
    private String fechaNacimientoDoctor;
    private String idPersona;
    private String idEspecialidad;

    /**
     * Listas de listas: Doctores y Especialidades
     */
    private List<List<String>> listaDoctores;
    private List<List<String>> listaEspecialidades;

    /**
     * Getter del id del doctor
     * @return idDoctor
     */
    public String getIdDoctor() {
        return idDoctor;
    }

    /**
     * Setter del id del doctor
     * @param idDoctor id del doctor
     */
    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
    }

    /**
     * Getter de la fecha de contratación
     * @return fechaContratacionDoctor
     */
    public String getFechaContratacionDoctor() {
        return fechaContratacionDoctor;
    }

    /**
     * Setter de la fecha de contratación
     * @param fechaContratacionDoctor fecha de contratación
     */
    public void setFechaContratacionDoctor(String fechaContratacionDoctor) {
        this.fechaContratacionDoctor = fechaContratacionDoctor;
    }

    /**
     * Getter de la fecha de nacimiento
     * @return fechaNacimientoDoctor
     */
    public String getFechaNacimientoDoctor() {
        return fechaNacimientoDoctor;
    }

    /**
     * Setter de la fecha de nacimiento
     * @param fechaNacimientoDoctor fecha de nacimiento
     */
    public void setFechaNacimientoDoctor(String fechaNacimientoDoctor) {
        this.fechaNacimientoDoctor = fechaNacimientoDoctor;
    }

    /**
     * Getter del id de la persona
     * @return idPersona
     */
    @Override
    public String getIdPersona() {
        return idPersona;
    }

    /**
     * Setter del id de la persona
     * @param idPersona id de la persona
     */
    @Override
    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    /**
     * Getter del id de especialidad
     * @return idEspecialidad
     */
    public String getIdEspecialidad() {
        return idEspecialidad;
    }

    /**
     * Setter id de especialidad
     * @param idEspecialidad id de la especialidad
     */
    public void setIdEspecialidad(String idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    /**
     * Lista general de doctores
     * @return lista de listas de doctores
     */
    public List<List<String>> listaDoctores() {
        return listaDoctores;
    }

    /**
     * Lista general de especialidades
     * @return lista de listas de especialidades
     */
    public List<List<String>> listaEspecialidades() {
        return listaEspecialidades;
    }

    /**
     * Método utilizado para llenar las listas de doctores y especialidades.
     * @see DoctorsModel
     */
    public void llenarListas() {
        listaDoctores = DoctorsModel.cargarListaDoctores();
        listaEspecialidades = DoctorsModel.cargarListaEspecialidades();
        cargarListaPersonas();
    }

    /**
     * Captura los nombres completos de una persona
     * @param idPersona id de la persona
     * @return nombre completo
     */
    public String capturarNombres(String idPersona) {
        for (List<String> persona : listaPersonas()) {
            if (persona.get(0).trim().equalsIgnoreCase(idPersona.trim())) {
                return persona.get(1) + " " + persona.get(2);
            }
        }
        return "Desconocido";
    }

    /**
     * Captura el nombre de la especialidad
     * @param idEspecialidad id de la especialidad
     * @return nombre de la especialidad
     */
    public String capturarEspecialidades(String idEspecialidad) {
        for (List<String> especialidad : listaEspecialidades()) {
            if (especialidad.get(0).trim().equalsIgnoreCase(idEspecialidad)) {
                return especialidad.get(1);
            }
        }
        return "Sin especialidad";
    }

    /**
     * Captura el id real de la lista de doctores
     * @param numero número de registro
     * @return id del doctor
     */
    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaDoctores.size()) {
            return listaDoctores.get(numero - 1).get(0);
        }
        return null;
    }

    /**
     * Captura el id real de la lista de especialidades
     * @param numero número de registro
     * @return id de la especialidad
     */
    public String capturarIdListaEspecialidad(int numero) {
        if (numero > 0 && numero <= listaEspecialidades.size()) {
            return listaEspecialidades.get(numero - 1).get(0);
        }
        return null;
    }

    /**
     * Registra un nuevo doctor
     * @return número de filas afectadas
     */
    public int RegistrarDoctor() {
        return DoctorsModel.ingresarNuevoDoctor(fechaContratacionDoctor, fechaNacimientoDoctor, idPersona, idEspecialidad);
    }

    /**
     * Carga los datos de un doctor específico
     * @param registro número de registro
     * @return datos del doctor
     */
    public List<String> cargarDatosDoctor(int registro) {
        String id = capturarIdLista(registro);
        if (id != null) {
            return DoctorsModel.cargarDoctor(id);
        }
        return new ArrayList<>();
    }

    /**
     * Actualiza un doctor existente
     * @param registro número de registro
     * @param fechaContratacionDoctor nueva fecha de contratación
     * @param fechaNacimientoDoctor nueva fecha de nacimiento
     * @param idPersona nuevo id de persona
     * @param idEspecialidad nuevo id de especialidad
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

package src.Controllers;

import src.Models.ConsultationsModel;

import java.util.ArrayList;
import java.util.List;

/*
 * Controlador para la entidad de consultas
 * Extiende las funcionalidades del controlador de doctores (DoctorsController)
 * Interactua con DoctorModel para gestionar consultas, mascotas y doctores
 */
public class ConsultationsController extends DoctorsController{

    /*
     * Constructor para inicializar las listas de consultas, mascotas y doctores
     */
    public ConsultationsController() {
        super();
        listaConsultas = new ArrayList<>();
        listaMascotas = new ArrayList<>();
        listaDoctores = new ArrayList<>();
    }
    // Atributos especificos para las consultas
    private String idConsulta;
    private String fechaConsulta;
    private String motivoConsulta;
    private String diagnosticoConsulta;
    private String notasConsulta;
    private String idMascota;
    private String idDoctor;
    private Boolean visibilidadConsulta;
    //Listas para almacenar los registros de las consultas, mascotas y doctores
    private List<List<String>> listaConsultas;
    private List<List<String>> listaMascotas;
    private List<List<String>> listaDoctores;

    // Getters y setters
    public String getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(String idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(String fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getDiagnosticoConsulta() {
        return diagnosticoConsulta;
    }

    public void setDiagnosticoConsulta(String diagnosticoConsulta) {
        this.diagnosticoConsulta = diagnosticoConsulta;
    }

    public String getNotasConsulta() {
        return notasConsulta;
    }

    public void setNotasConsulta(String notasConsulta) {
        this.notasConsulta = notasConsulta;
    }

    public String getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(String idMascota) {
        this.idMascota = idMascota;
    }
    //Se sobreescriben metodos de la clase DoctorsController
    @Override
    public String getIdDoctor() {
        return idDoctor;
    }

    @Override
    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
    }

    public Boolean getVisibilidadConsulta() {
        return visibilidadConsulta;
    }

    public void setVisibilidadConsulta(Boolean visibilidadConsulta) {
        this.visibilidadConsulta = visibilidadConsulta;
    }

    /*
     * Retorna la lista de consultas
     */
    public List<List<String>> listaConsultas() {
        return listaConsultas;
    }

    /*
     * Retorna la lista de mascotas
     */
    public List<List<String>> listaMascotas() {
        return listaMascotas;
    }

    /*
     * Retorna la lista de doctores
     */
    public List<List<String>> listaDoctores() {
        return listaDoctores;
    }

    /*
     * Llena las listas de consultas, mascotas y doctores, y carga la lista de las personas
     */
    public void llenarListas(){
        listaConsultas = ConsultationsModel.cargarListaConsultas();
        listaMascotas = ConsultationsModel.cargarListaMascotas();
        listaDoctores = ConsultationsModel.cargarListaDoctores();
        cargarListaPersonas();
    }

    /*
     * Captura el id de la consulta a partir de su posicion en la lista
     * El parametro es el numero indice en la lista
     * Retorna el id si existe, o null en caso que no
     */
    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaConsultas.size()) {
            return listaConsultas.get(numero - 1).get(0);
        }
        return null;
    }

    /*
     * Captura el id de la mascota con base a su posicion en la lista
     * El parametro es el numero indice en la lista de mascotas
     * Retorna el id de la mascota o null si no
     */
    public String capturarIdListaMascotas(int numero) {
        if (numero > 0 && numero <= listaMascotas.size()) {
            return listaMascotas.get(numero - 1).get(0);
        }
        return null;
    }

    /*
     * Captura el id del doctor con base a su posicion en la lista
     * El parametro es el numero indice en la lista de doctores
     * Retorna el id del doctor o null si no
     */
    public String capturarIdListaDoctores(int numero) {
        if (numero > 0 && numero <= listaDoctores.size()) {
            return listaDoctores.get(numero - 1).get(0);
        }
        return null;
    }

    /*
     * Captura el nombre completo del doctor asociado a un id de persona
     * El parametro es el id de la persona
     * Retorna el nombre completo, o "Desconocido" si no se encuentra
     */
    public String capturarNombresDoctores(String idPersona) {
        // Itera las listas de doctores y de personas
        for (List<String> doctor : listaDoctores()) {
            if (doctor.get(3).trim().equalsIgnoreCase(idPersona.trim())) {
                for (List<String> persona : listaPersonas()) {
                    if (persona.get(0).trim().equalsIgnoreCase(idPersona.trim())) {
                        return persona.get(1) + " " + persona.get(2);
                    }
                }
            }
        }
        return "Desconocido";
    }

    /*
     * Captura el nombre de la mascota con base al id
     * El parametro es el id de la mascota
     * Retorna el nombre de la mascota, o "No encontrada" si no se encuentra
     */
    public String capturarMascotas(String idMascota){
        for (List<String> mascota : listaMascotas()) {
            if (mascota.get(0).trim().equalsIgnoreCase(idMascota)) {
                return mascota.get(1);
            }
        }
        return "No encontrada";
    }

    /*
     * Registra una nueva consulta en la base de datos con los atributos actuales
     * Retorna el numero de filas afectadas
     */
    public int registrarConsulta() {
        return ConsultationsModel.ingresarNuevaConsulta(fechaConsulta, motivoConsulta, diagnosticoConsulta, notasConsulta, idMascota, idDoctor, visibilidadConsulta);
    }

    /*
     * Carga los datos de una consulta especifica con base a su posicion en la lista
     * El parametro es el registro indice en la lista
     * Retorna la lista con los datos de la consulta, o lista vacia si no se encuentra
     */
    public List<String> cargarDatosConsulta(int registro){
        String id = capturarIdLista(registro);
        if (id != null){
            return ConsultationsModel.cargarCita(id);
        }
        return new ArrayList<>();
    }
    /*
     * Actualiza los datos de una consulta
     */
    public void actualizarConsulta(int registro, String fechaConsulta, String motivoConsulta, String diagnosticoConsulta, String notasConsulta, String idMascota, String idDoctor, Boolean visibilidadConsulta) {
        String id = capturarIdLista(registro);
        if (id != null) {
            int resultado = ConsultationsModel.actualizarConsulta(id, fechaConsulta, motivoConsulta, diagnosticoConsulta, notasConsulta, idMascota, idDoctor, visibilidadConsulta);
            if (resultado > 0) {
                System.out.println("Registro actualizado correctamente.");
            } else {
                System.out.println("Error al actualizar el registro.");
            }
        } else {
            System.out.println("Registro inexistente.");
        }
    }

    /*
     * Desactiva una consulta con base a su posicion en la lista (eliminar)
     */
    public void desactivarConsulta(int numero){
        String id = this.capturarIdLista(numero);
        if (id != null) {
            int resultado = ConsultationsModel.desactivarConsulta(id);
            if (resultado > 0) {
                System.out.println("Consulta desactivada correctamente.");
            } else {
                System.out.println("Error al desactivar el registro.");
            }
        } else {
            System.out.println("Registro inexistente.");
        }
    }
}

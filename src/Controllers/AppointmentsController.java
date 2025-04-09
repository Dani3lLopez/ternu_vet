package src.Controllers;

import src.Models.AppointmentsModel;

import java.util.ArrayList;
import java.util.List;

/*
 * Controlador para la entidad de citas
 * Extiende las funcionalidades del controlador de doctores (DoctorsController)
 * Interactua con AppointmentsModel para gestionar citas, mascotas y doctores
 */
public class AppointmentsController extends DoctorsController {
    /*
     * Constructor para inicializar las listas de citas, mascotas y doctores
     */
    public AppointmentsController() {
        super();
        listaCitas = new ArrayList<>();
        listaMascotas = new ArrayList<>();
        listaDoctores = new ArrayList<>();
    }
    // Atributos especificos para las citas
    private String idCita;
    private String motivoCita;
    private String fechaCita;
    private String horaCita;
    private String idMascota;
    private String idDoctor;
    private Boolean visibilidadCita;
    //Listas para almacenar los registros de las citas, mascotas y doctores
    private List<List<String>> listaCitas;
    private List<List<String>> listaMascotas;
    private List<List<String>> listaDoctores;

    // Getters y setters
    public String getIdCita() {
        return idCita;
    }

    public void setIdCita(String idCita) {
        this.idCita = idCita;
    }

    public String getMotivoCita() {
        return motivoCita;
    }

    public void setMotivoCita(String motivoCita) {
        this.motivoCita = motivoCita;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }

    public String getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(String idMascota) {
        this.idMascota = idMascota;
    }

    public String getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
    }

    public Boolean getVisibilidadCita() {
        return visibilidadCita;
    }

    public void setVisibilidadCita(Boolean visibilidadCita) {
        this.visibilidadCita = visibilidadCita;
    }

    /*
     * Retorna la lista de citas
     */
    public List<List<String>> listaCitas() {
        return listaCitas;
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
     * Llena las listas de citas, mascotas y doctores, y carga la lista de las personas
     */
    public void llenarListas(){
        listaCitas = AppointmentsModel.cargarListaCitas();
        listaMascotas = AppointmentsModel.cargarListaMascotas();
        listaDoctores = AppointmentsModel.cargarListaDoctores();
        cargarListaPersonas();
    }

    /*
     * Captura el id de la cita a partir de su posicion en la lista
     * El parametro es el numero indice en la lista
     * Retorna el id si existe, o null en caso que no
     */
    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaCitas.size()) {
            return listaCitas.get(numero - 1).get(0);
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
     * Registra una nueva cita en la base de datos con los atributos actuales
     * Retorna el numero de filas afectadas
     */
    public int registrarCita() {
        return AppointmentsModel.ingresarNuevaCita(motivoCita, fechaCita, horaCita, idMascota, idDoctor, visibilidadCita);
    }

    /*
     * Carga los datos de una cita especifica con base a su posicion en la lista
     * El parametro es el registro indice en la lista
     * Retorna la lista con los datos de la cita, o lista vacia si no se encuentra
     */
    public List<String> cargarDatosCita(int registro){
        String id = capturarIdLista(registro);
        if (id != null){
            return AppointmentsModel.cargarCita(id);
        }
        return new ArrayList<>();
    }

    /*
     * Actualiza los datos de una cita
     */
    public void actualizarCita(int registro, String motivoCita, String fechaCita, String horaCita, String idMascota, String idDoctor, Boolean visibilidadCita) {
        String id = capturarIdLista(registro);
        if (id != null) {
            int resultado = AppointmentsModel.actualizarCita(id, motivoCita, fechaCita, horaCita, idMascota, idDoctor, visibilidadCita);
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
     * Desactiva una cita con base a su posicion en la lista (eliminar)
     */
    public void desactivarCita(int numero){
        String id = this.capturarIdLista(numero);
        if (id != null) {
            int resultado = AppointmentsModel.desactivarCita(id);
            if (resultado > 0) {
                System.out.println("Cita desactivada correctamente.");
            } else {
                System.out.println("Error al desactivar el registro.");
            }
        } else {
            System.out.println("Registro inexistente.");
        }
    }
}

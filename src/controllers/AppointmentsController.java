package src.controllers;

import src.models.AppointmentsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con las citas,
 * incluyendo la obtención de datos, inserción, actualización y desactivación
 * @see DoctorsController
 */
public class AppointmentsController extends DoctorsController {

    /**
     * Constructor que inicializa las listas de citas, mascotas y doctores
     */
    public AppointmentsController() {
        super();
        listaCitas = new ArrayList<>();
        listaMascotas = new ArrayList<>();
        listaDoctores = new ArrayList<>();
    }

    /**
     * Atributos de las citas
     */
    private String idCita;
    private String motivoCita;
    private String fechaCita;
    private String horaCita;
    private String idMascota;
    private String idDoctor;
    private Boolean visibilidadCita;

    /**
     * Listas de listas: citas, mascotas y doctores
     */
    private List<List<String>> listaCitas;
    private List<List<String>> listaMascotas;
    private List<List<String>> listaDoctores;

    /**
     * getter de id cita
     * @return idCita
     */
    public String getIdCita() {
        return idCita;
    }

    /**
     * setter de id cita
     * @param idCita id de la cita
     */
    public void setIdCita(String idCita) {
        this.idCita = idCita;
    }

    /**
     * Getter de motivo para la cita
     * @return motivoCita
     */
    public String getMotivoCita() {
        return motivoCita;
    }

    /**
     * Setter de motivo cita
     * @param motivoCita motivo de la cita
     */
    public void setMotivoCita(String motivoCita) {
        this.motivoCita = motivoCita;
    }

    /**
     * getter de fecha para la cita
     * @return fechaCita
     */
    public String getFechaCita() {
        return fechaCita;
    }

    /**
     * setter de fecha para la cita
     * @param fechaCita fecha de la cita
     */
    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    /**
     * getter de hora para la cita
     * @return horaCita
     */
    public String getHoraCita() {
        return horaCita;
    }

    /**
     * setter de hora para la cita
     * @param horaCita hora de la cita
     */
    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }

    /**
     * getter de id mascota
     * @return idMascota
     */
    public String getIdMascota() {
        return idMascota;
    }

    /**
     * setter de id mascota
     * @param idMascota id de la mascota
     */
    public void setIdMascota(String idMascota) {
        this.idMascota = idMascota;
    }

    /**
     * getter de id doctor
     * @return idDoctor
     */
    public String getIdDoctor() {
        return idDoctor;
    }

    /**
     * setter de id doctor
     * @param idDoctor id del doctor
     */
    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
    }

    /**
     * getter de la visibilidad para la cita
     * @return visibilidadCita
     */
    public Boolean getVisibilidadCita() {
        return visibilidadCita;
    }

    /**
     * setter para la visibilidad de la cita
     * @param visibilidadCita visibilidad de la cita
     */
    public void setVisibilidadCita(Boolean visibilidadCita) {
        this.visibilidadCita = visibilidadCita;
    }

    /**
     * Lista de listas para las citas
     * @return listaCitas
     */
    public List<List<String>> listaCitas() {
        return listaCitas;
    }

    /**
     * Lista de listas para las mascotas
     * @return listaMascotas
     */
    public List<List<String>> listaMascotas() {
        return listaMascotas;
    }

    /**
     * Lista de listas para los doctores
     * @return listaDoctores
     */
    public List<List<String>> listaDoctores() {
        return listaDoctores;
    }

    /**
     * Llena las listas llamando a los métodos de carga
     * @see AppointmentsModel
     */
    public void llenarListas(){
        listaCitas = AppointmentsModel.cargarListaCitas();
        listaMascotas = AppointmentsModel.cargarListaMascotas();
        listaDoctores = AppointmentsModel.cargarListaDoctores();
        cargarListaPersonas();
    }

    /**
     * Captura el id de la lista según aparece en consola
     * @param numero numero de registro
     * @return la posición real del registro en la base de datos
     */
    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaCitas.size()) {
            return listaCitas.get(numero - 1).get(0);
        }
        return null;
    }

    /**
     * Captura el id de la lista según aparece en consola
     * @param numero numero de registro
     * @return la posición real del registro en la base de datos
     */
    public String capturarIdListaMascotas(int numero) {
        if (numero > 0 && numero <= listaMascotas.size()) {
            return listaMascotas.get(numero - 1).get(0);
        }
        return null;
    }

    /**
     * Captura el id de la lista según aparece en consola
     * @param numero numero de registro
     * @return la posición real del registro en la base de datos
     */
    public String capturarIdListaDoctores(int numero) {
        if (numero > 0 && numero <= listaDoctores.size()) {
            return listaDoctores.get(numero - 1).get(0);
        }
        return null;
    }

    /**
     * Captura el nombre de la persona según el id de registro
     * @param idPersona id real del registro de persona en la base de datos
     * @return posición o desconocido en caso de no encontrarlo
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

    /**
     * Captura el nombre de la mascota según id
     * @param idMascota id de la mascota
     * @return el nombre de la mascota o no encontrada si no existe el registro
     */
    public String capturarMascotas(String idMascota){
        for (List<String> mascota : listaMascotas()) {
            if (mascota.get(0).trim().equalsIgnoreCase(idMascota)) {
                return mascota.get(1);
            }
        }
        return "No encontrada";
    }

    /**
     * Registra citas
     * @return numero de filas afectadas
     */
    public int registrarCita() {
        return AppointmentsModel.ingresarNuevaCita(motivoCita, fechaCita, horaCita, idMascota, idDoctor, visibilidadCita);
    }

    /**
     * Carga los datos de una cita en específica según su id
     * @param registro numero de registro
     * @return lista de datos
     */
    public List<String> cargarDatosCita(int registro){
        String id = capturarIdLista(registro);
        if (id != null){
            return AppointmentsModel.cargarCita(id);
        }
        return new ArrayList<>();
    }

    /**
     * Actualiza los datos de una cita
     * @param registro número de registro actual según consola
     * @param motivoCita nuevo motivo de cita obtenido de los atributos
     * @param fechaCita nueva fecha de cita obtenida de los atributos
     * @param horaCita nueva hora de cita obtenida de los atributos
     * @param idMascota nuevo id obtenido de los atributos
     * @param idDoctor nuevo id obtenido de los atributos
     * @param visibilidadCita nueva visibilidad obtenida de los atributos
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

    /**
     * Desactiva un registro, actualiza su visibilidad
     * @param numero numero de registro según consola
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
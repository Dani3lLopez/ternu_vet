package src.controllers;

import src.models.ConsultationsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con las consultas,
 * incluyendo la obtención de datos, inserción, actualización y desactivación
 * @see DoctorsController
 */
public class ConsultationsController extends DoctorsController{

    /**
     * Constructor que inicializa las listas de consultas, mascotas y doctores
     */
    public ConsultationsController() {
        super();
        listaConsultas = new ArrayList<>();
        listaMascotas = new ArrayList<>();
        listaDoctores = new ArrayList<>();
    }

    /**
     * Atributos de las consultas
     */
    private String idConsulta;
    private String fechaConsulta;
    private String motivoConsulta;
    private String diagnosticoConsulta;
    private String notasConsulta;
    private String idMascota;
    private String idDoctor;
    private Boolean visibilidadConsulta;

    /**
     * Listas de listas: Consultas, Mascotas, Doctores
     */
    private List<List<String>> listaConsultas;
    private List<List<String>> listaMascotas;
    private List<List<String>> listaDoctores;

    /**
     * Getter del id de la consulta
     * @return idConsulta
     */
    public String getIdConsulta() {
        return idConsulta;
    }

    /**
     * Setter del id de la consulta
     * @param idConsulta id de la consulta
     */
    public void setIdConsulta(String idConsulta) {
        this.idConsulta = idConsulta;
    }

    /**
     * Getter de la fecha de consulta
     * @return fechaConsulta
     */
    public String getFechaConsulta() {
        return fechaConsulta;
    }

    /**
     * Setter de fecha de consulta
     * @param fechaConsulta fecha de consulta
     */
    public void setFechaConsulta(String fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    /**
     * Getter de motivos de consulta
     * @return motivoConsulta
     */
    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    /**
     * Setter de motivo de consulta
     * @param motivoConsulta motivo de consulta
     */
    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    /**
     * Getter de diagnostico de consulta
     * @return diagnosticoConsulta
     */
    public String getDiagnosticoConsulta() {
        return diagnosticoConsulta;
    }

    /**
     * Setter del diagnostico de consulta
     * @param diagnosticoConsulta diagnostico de consulta
     */
    public void setDiagnosticoConsulta(String diagnosticoConsulta) {
        this.diagnosticoConsulta = diagnosticoConsulta;
    }

    /**
     * Getter de notas de consulta
     * @return notasConsulta
     */
    public String getNotasConsulta() {
        return notasConsulta;
    }

    /**
     * Setter de notas de consulta
     * @param notasConsulta notas de consulta
     */
    public void setNotasConsulta(String notasConsulta) {
        this.notasConsulta = notasConsulta;
    }

    /**
     * Getter del id de la mascota
     * @return idMascota
     */
    public String getIdMascota() {
        return idMascota;
    }

    /**
     * Setter del id de la mascota
     * @param idMascota id de la mascota
     */
    public void setIdMascota(String idMascota) {
        this.idMascota = idMascota;
    }

    /**
     * Getter del id del doctor
     * @return idDoctor
     */
    @Override
    public String getIdDoctor() {
        return idDoctor;
    }

    /**
     * Setter del id del doctor
     * @param idDoctor id del doctor
     */
    @Override
    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
    }

    /**
     * Getter de visibilidad de consulta
     * @return visibilidadConsulta
     */
    public Boolean getVisibilidadConsulta() {
        return visibilidadConsulta;
    }

    /**
     * Setter de visibilidad de consulta
     * @param visibilidadConsulta visibilidad de consulta
     */
    public void setVisibilidadConsulta(Boolean visibilidadConsulta) {
        this.visibilidadConsulta = visibilidadConsulta;
    }

    /**
     * Lista general de consultas
     * @return lista de listas de consultas
     */
    public List<List<String>> listaConsultas() {
        return listaConsultas;
    }

    /**
     * Lista general de mascotas
     * @return lista de listas de mascotas
     */
    public List<List<String>> listaMascotas() {
        return listaMascotas;
    }

    /**
     * Lista general de doctores
     * @return lista de listas de doctores
     */
    public List<List<String>> listaDoctores() {
        return listaDoctores;
    }

    /**
     * Método utilizado para llenar las listas de datos complementarios
     * @see ConsultationsModel
     */
    public void llenarListas(){
        listaConsultas = ConsultationsModel.cargarListaConsultas();
        listaMascotas = ConsultationsModel.cargarListaMascotas();
        listaDoctores = ConsultationsModel.cargarListaDoctores();
        cargarListaPersonas();
    }

    /**
     * Captura el número de registro según consola
     * @param numero número de registro
     * @return posición real de registro en la base de datos
     */
    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaConsultas.size()) {
            return listaConsultas.get(numero - 1).get(0);
        }
        return null;
    }

    /**
     * Captura el número de registro según consola
     * @param numero número de registro
     * @return posición real de registro en la base de datos
     */
    public String capturarIdListaMascotas(int numero) {
        if (numero > 0 && numero <= listaMascotas.size()) {
            return listaMascotas.get(numero - 1).get(0);
        }
        return null;
    }

    /**
     * Captura el número de registro según consola
     * @param numero número de registro
     * @return posición real de registro en la base de datos
     */
    public String capturarIdListaDoctores(int numero) {
        if (numero > 0 && numero <= listaDoctores.size()) {
            return listaDoctores.get(numero - 1).get(0);
        }
        return null;
    }

    /**
     * Captura el nombre del doctor según su id de persona
     * @param idPersona id de persona
     * @return nombre del doctor
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

    /**
     * Captura el nombre de la mascota según su id
     * @param idMascota id de la mascota
     * @return nombre de la mascota
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
     * Registra consultas
     * @return número de filas afectadas
     */
    public int registrarConsulta() {
        return ConsultationsModel.ingresarNuevaConsulta(fechaConsulta, motivoConsulta, diagnosticoConsulta, notasConsulta, idMascota, idDoctor, visibilidadConsulta);
    }

    /**
     * Carga los datos de una consulta específica según su id
     * @param registro número de registro
     * @return datos de la consulta
     */
    public List<String> cargarDatosConsulta(int registro){
        String id = capturarIdLista(registro);
        if (id != null){
            return ConsultationsModel.cargarCita(id);
        }
        return new ArrayList<>();
    }

    /**
     * Actualiza una consulta existente
     * @param registro número de registro según consola
     * @param fechaConsulta nueva fecha de consulta
     * @param motivoConsulta nuevo motivo de consulta
     * @param diagnosticoConsulta nuevo diagnóstico
     * @param notasConsulta nuevas notas
     * @param idMascota nuevo id de mascota
     * @param idDoctor nuevo id del doctor
     * @param visibilidadConsulta nueva visibilidad
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

    /**
     * Cambia la visibilidad de una consulta a false
     * @param numero número de registro
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

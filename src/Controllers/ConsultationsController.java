package src.Controllers;

import src.Models.ConsultationsModel;

import java.util.ArrayList;
import java.util.List;

public class ConsultationsController extends DoctorsController{
    public ConsultationsController() {
        super();
        listaConsultas = new ArrayList<>();
        listaMascotas = new ArrayList<>();
        listaDoctores = new ArrayList<>();
    }

    private String idConsulta;
    private String fechaConsulta;
    private String motivoConsulta;
    private String diagnosticoConsulta;
    private String notasConsulta;
    private String idMascota;
    private String idDoctor;
    private Boolean visibilidadConsulta;
    private List<List<String>> listaConsultas;
    private List<List<String>> listaMascotas;
    private List<List<String>> listaDoctores;

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

    public List<List<String>> listaConsultas() {
        return listaConsultas;
    }

    public List<List<String>> listaMascotas() {
        return listaMascotas;
    }

    public List<List<String>> listaDoctores() {
        return listaDoctores;
    }

    public void llenarListas(){
        listaConsultas = ConsultationsModel.cargarListaConsultas();
        listaMascotas = ConsultationsModel.cargarListaMascotas();
        listaDoctores = ConsultationsModel.cargarListaDoctores();
        cargarListaPersonas();
    }

    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaConsultas.size()) {
            return listaConsultas.get(numero - 1).get(0);
        }
        return null;
    }

    public String capturarIdListaMascotas(int numero) {
        if (numero > 0 && numero <= listaMascotas.size()) {
            return listaMascotas.get(numero - 1).get(0);
        }
        return null;
    }

    public String capturarIdListaDoctores(int numero) {
        if (numero > 0 && numero <= listaDoctores.size()) {
            return listaDoctores.get(numero - 1).get(0);
        }
        return null;
    }

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

    public String capturarMascotas(String idMascota){
        for (List<String> mascota : listaMascotas()) {
            if (mascota.get(0).trim().equalsIgnoreCase(idMascota)) {
                return mascota.get(1);
            }
        }
        return "No encontrada";
    }

    public int registrarConsulta() {
        return ConsultationsModel.ingresarNuevaConsulta(fechaConsulta, motivoConsulta, diagnosticoConsulta, notasConsulta, idMascota, idDoctor, visibilidadConsulta);
    }

    public List<String> cargarDatosConsulta(int registro){
        String id = capturarIdLista(registro);
        if (id != null){
            return ConsultationsModel.cargarCita(id);
        }
        return new ArrayList<>();
    }

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

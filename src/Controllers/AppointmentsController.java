package src.Controllers;

import src.Models.AppointmentsModel;
import src.Models.DoctorsModel;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsController extends DoctorsController {

    public AppointmentsController() {
        super();
        listaCitas = new ArrayList<>();
        listaMascotas = new ArrayList<>();
        listaDoctores = new ArrayList<>();
    }

    private String idCita;
    private String motivoCita;
    private String fechaCita;
    private String idMascota;
    private String idDoctor;
    private String visibilidadCita;
    private List<List<String>> listaCitas;
    private List<List<String>> listaMascotas;
    private List<List<String>> listaDoctores;

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

    public String getVisibilidadCita() {
        return visibilidadCita;
    }

    public void setVisibilidadCita(String visibilidadCita) {
        this.visibilidadCita = visibilidadCita;
    }

    public List<List<String>> listaCitas() {
        return listaCitas;
    }

    public List<List<String>> listaMascotas() {
        return listaMascotas;
    }

    public List<List<String>> listaDoctores() {
        return listaDoctores;
    }

    public void llenarListas(){
        listaCitas = AppointmentsModel.cargarListaCitas();
        listaMascotas = AppointmentsModel.cargarListaMascotas();
        listaDoctores = AppointmentsModel.cargarListaDoctores();
        cargarListaPersonas();
    }

    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaCitas.size()) {
            return listaCitas.get(numero - 1).get(0);
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

    public String capturarDoctores(String idDoctor){
        for (List<String> doctor : listaDoctores()) {
            if (doctor.get(0).trim().equalsIgnoreCase(idDoctor)) {
                return doctor.get(3);
            }
        }
        return "No encontrado";
    }
}

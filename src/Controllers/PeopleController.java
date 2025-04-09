package src.Controllers;
import src.Models.PeopleModel;

import java.util.ArrayList;
import java.util.List;

public class PeopleController {

    public PeopleController() {}

    private String idPersona;
    private String nombrePersona;
    private String apellidoPersona;
    private String telefonoPersona;
    private String emailPersona;
    private String duiPersona;

    private List<List<String>> listaPersonas;

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getApellidoPersona() {
        return apellidoPersona;
    }

    public void setApellidoPersona(String apellidoPersona) {
        this.apellidoPersona = apellidoPersona;
    }

    public String getTelefonoPersona() {
        return telefonoPersona;
    }

    public void setTelefonoPersona(String telefonoPersona) {
        this.telefonoPersona = telefonoPersona;
    }

    public String getEmailPersona() {
        return emailPersona;
    }

    public void setEmailPersona(String emailPersona) {
        this.emailPersona = emailPersona;
    }

    public String getDuiPersona() {
        return duiPersona;
    }

    public void setDuiPersona(String duiPersona) {
        this.duiPersona = duiPersona;
    }

    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaPersonas.size()) {
            return listaPersonas.get(numero - 1).get(0);
        }
        return null;
    }

    public void cargarListaPersonas() {
        listaPersonas = PeopleModel.cargarListaPersonas();
    }

    public List<List<String>> listaPersonas() {
        return listaPersonas;
    }

    public int RegistrarPersona() {
        return PeopleModel.ingresarNuevaPersona(nombrePersona, apellidoPersona, telefonoPersona, emailPersona, duiPersona);
    }

    public List<String> cargarDatosPersona(int registro){
        String id = capturarIdLista(registro);
        if (id != null){
            return PeopleModel.cargarPersona(id);
        }
        return new ArrayList<>();
    }

    public void actualizarPersona(int registro, String nombre, String apellido, String telefono, String email, String dui) {
        String id = capturarIdLista(registro);
        if (id != null) {
            int resultado = PeopleModel.actualizarPersona(id, nombre, apellido, telefono, email, dui);
            if (resultado > 0) {
                System.out.println("Registro actualizado correctamente.");
            } else {
                System.out.println("Error al actualizar el registro.");
            }
        } else {
            System.out.println("Registro inexistente.");
        }
    }

    public void eliminarPersona(int numero){
        String id = this.capturarIdLista(numero);
        if (id != null) {
            int resultado = PeopleModel.eliminarPersona(id);
            if (resultado > 0) {
                System.out.println("Propietario eliminado correctamente.");
            } else {
                System.out.println("Error al eliminar el registro.");
            }
        } else {
            System.out.println("Registro inexistente.");
        }
    }
}

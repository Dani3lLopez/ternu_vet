package src.Controllers;
import src.Models.PeopleModel;
import src.Views.People;

import java.util.ArrayList;
import java.util.List;

public class PeopleController {

    public PeopleController() {
    }

    public String idPersona;
    public String nombrePersona;
    public String apellidoPersona;
    public String telefonoPersona;
    public String emailPersona;
    public String duiPersona;
    public List<List<String>> listaPersonas;

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

    public void cargarListaPersonas() {
        listaPersonas = PeopleModel.cargarListaPersonas();
    }

    public int RegistrarPersona() {
        return PeopleModel.ingresarNuevaPersona(nombrePersona, apellidoPersona, telefonoPersona, emailPersona, duiPersona);
    }

    public String capturarId(int numero) {
        if (numero > 0 && numero <= listaPersonas.size()) {
            return listaPersonas.get(numero - 1).get(0);
        }
        return null;
    }

    public void actualizarPersona(int registro, String nombre, String apellido, String telefono, String email, String dui) {
        String id = capturarId(registro);
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
}

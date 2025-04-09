package src.controllers;

import src.models.PeopleModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con las personas,
 * incluyendo el registro, consulta, actualización y eliminación de datos
 */
public class PeopleController {

    /**
     * Constructor vacío del controlador de personas
     */
    public PeopleController() {}

    /**
     * Atributos de una persona
     */
    private String idPersona;
    private String nombrePersona;
    private String apellidoPersona;
    private String telefonoPersona;
    private String emailPersona;
    private String duiPersona;

    /**
     * Lista de listas con los datos de las personas
     */
    private List<List<String>> listaPersonas;

    /**
     * Getter del id de la persona
     * @return idPersona
     */
    public String getIdPersona() {
        return idPersona;
    }

    /**
     * Setter del id de la persona
     * @param idPersona id de la persona
     */
    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    /**
     * Getter del nombre de la persona
     * @return nombrePersona
     */
    public String getNombrePersona() {
        return nombrePersona;
    }

    /**
     * Setter del nombre de la persona
     * @param nombrePersona nombre de la persona
     */
    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    /**
     * Getter del apellido de la persona
     * @return apellidoPersona
     */
    public String getApellidoPersona() {
        return apellidoPersona;
    }

    /**
     * Setter del apellido de la persona
     * @param apellidoPersona apellido de la persona
     */
    public void setApellidoPersona(String apellidoPersona) {
        this.apellidoPersona = apellidoPersona;
    }

    /**
     * Getter del teléfono de la persona
     * @return telefonoPersona
     */
    public String getTelefonoPersona() {
        return telefonoPersona;
    }

    /**
     * Setter del teléfono de la persona
     * @param telefonoPersona teléfono de la persona
     */
    public void setTelefonoPersona(String telefonoPersona) {
        this.telefonoPersona = telefonoPersona;
    }

    /**
     * Getter del correo electrónico de la persona
     * @return emailPersona
     */
    public String getEmailPersona() {
        return emailPersona;
    }

    /**
     * Setter del correo electrónico de la persona
     * @param emailPersona correo electrónico de la persona
     */
    public void setEmailPersona(String emailPersona) {
        this.emailPersona = emailPersona;
    }

    /**
     * Getter del DUI de la persona
     * @return duiPersona
     */
    public String getDuiPersona() {
        return duiPersona;
    }

    /**
     * Setter del DUI de la persona
     * @param duiPersona número de DUI
     */
    public void setDuiPersona(String duiPersona) {
        this.duiPersona = duiPersona;
    }

    /**
     * Captura el id de la persona según su número en consola
     * @param numero número del registro en consola
     * @return id de la persona o null si no existe
     */
    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaPersonas.size()) {
            return listaPersonas.get(numero - 1).get(0);
        }
        return null;
    }

    /**
     * Llena la lista de personas desde el modelo
     * @see PeopleModel
     */
    public void cargarListaPersonas() {
        listaPersonas = PeopleModel.cargarListaPersonas();
    }

    /**
     * Lista de listas con los datos de las personas
     * @return listaPersonas
     */
    public List<List<String>> listaPersonas() {
        return listaPersonas;
    }

    /**
     * Registra una nueva persona
     * @return número de filas afectadas
     */
    public int RegistrarPersona() {
        return PeopleModel.ingresarNuevaPersona(nombrePersona, apellidoPersona, telefonoPersona, emailPersona, duiPersona);
    }

    /**
     * Carga los datos de una persona dado su número de registro
     * @param registro número de registro en consola
     * @return lista de datos de la persona
     */
    public List<String> cargarDatosPersona(int registro){
        String id = capturarIdLista(registro);
        if (id != null){
            return PeopleModel.cargarPersona(id);
        }
        return new ArrayList<>();
    }

    /**
     * Actualiza los datos de una persona
     * @param registro número del registro en consola
     * @param nombre nuevo nombre
     * @param apellido nuevo apellido
     * @param telefono nuevo teléfono
     * @param email nuevo correo electrónico
     * @param dui nuevo DUI
     */
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

    /**
     * Elimina una persona
     * @param numero número del registro en consola
     */
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
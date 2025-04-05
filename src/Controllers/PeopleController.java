package src.Controllers;
import src.Models.OwnersModel;
import src.Models.PeopleModel;

import java.util.ArrayList;
import java.util.List;

/*
 * Controlador que maneja la parte lógica de la entidad People
 * Intermediario entre la vista (People.java) y el modelo (PeopleModel)
 */
public class PeopleController {

    //Constrcutor para inicializar valores si resulta necesario
    public PeopleController() {
    }
    //Atributos privados que almacenan la información de la persona
    private String idPersona;
    private String nombrePersona;
    private String apellidoPersona;
    private String telefonoPersona;
    private String emailPersona;
    private String duiPersona;

    // Esta es una lista con todas las personas ya cargadas. Cada persona es una lista
    private List<List<String>> listaPersonas;

    // Getters y setters de los atributos
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

    /*
     * Guard EL id de la persona con base a su posición en la lista
     * El parametro numero es el indice del registro en la lsita
     * Retorna el id de la persona siempre y cuando esta existe
     * Si la persona no existe, retorna null
     */
    public String capturarIdLista(int numero) {
        // si el numero exista en la lista, retorna el primer elemento de cada sublista
        // Este primer valor es el id de la persona
        if (numero > 0 && numero <= listaPersonas.size()) {
            return listaPersonas.get(numero - 1).get(0);
        }
        return null;
    }

    /*
     * Llama al modelo para cargar la lista de las personas
     * Asigna esta a una variable local
     */
    public void cargarListaPersonas() {
        listaPersonas = PeopleModel.cargarListaPersonas();
    }
    /*
     * Retorna  una lista de las personas cargadas
     */
    public List<List<String>> listaPersonas() {
        return listaPersonas;
    }
    /*
     * Registra una nueva persona en la base de datos
     * Utiliza los valores de los atributos del controlador
     * Retorna la cantidad de filas afectadas (un numero, que se espera sea 1 si fue exitosa)
     */
    public int RegistrarPersona() {
        return PeopleModel.ingresarNuevaPersona(nombrePersona, apellidoPersona, telefonoPersona, emailPersona, duiPersona);
    }
    /*
     * Carga los datos de una persona en especifico con base a su indice en la lista
     *  El parametro es el registro indice de la lista de las personas
     *  Retorna una lsita con lo datos de la persona si esta se encuentra.
     *  De lo contrario, se retorna una lista vacia
     */
    public List<String> cargarDatosPersona(int registro){
        // Obtiene el id de la persona con base a su posicion en la lista
        String id = capturarIdLista(registro);
        if (id != null){
            // Llama al modelo para cargar los datos de la persona
            return PeopleModel.cargarPersona(id);
        }
        return new ArrayList<>();
    }
    /*
     * Actualiza los datos de una persona en la base de datos
     */
    public void actualizarPersona(int registro, String nombre, String apellido, String telefono, String email, String dui) {
        String id = capturarIdLista(registro);
        if (id != null) {
            // Llama al modelo para actualizar
            int resultado = PeopleModel.actualizarPersona(id, nombre, apellido, telefono, email, dui);
            if (resultado > 0) {
                // se verifica el resultado (1 si se actualizo) y se muestra un mensaje
                System.out.println("Registro actualizado correctamente.");
            } else {
                System.out.println("Error al actualizar el registro.");
            }
        } else {
            System.out.println("Registro inexistente.");
        }
    }
    /*
     * Elimina una persona con base a su posicion de la base de datos
     */
    public void eliminarPersona(int numero){
        String id = this.capturarIdLista(numero);
        if (id != null) {
            // Llama al modelo para eliminar el registro
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

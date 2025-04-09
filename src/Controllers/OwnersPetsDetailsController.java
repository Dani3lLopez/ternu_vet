package src.Controllers;

import src.Models.OwnersPetsDetailsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de gestionar los detalles de la relación entre propietarios y mascotas,
 * incluyendo su registro, actualización, eliminación y consultas
 * @see OwnersController
 */
public class OwnersPetsDetailsController extends OwnersController {

    /**
     * Constructor que inicializa las listas de detalles, mascotas y propietarios
     */
    public OwnersPetsDetailsController() {
        super();
        listaDetalles = new ArrayList<>();
        listaMascotas = new ArrayList<>();
        listaPropietarios = new ArrayList<>();
    }

    /**
     * Atributos de los detalles de propietario-mascota
     */
    private String idDetallePropietarioMascota;
    private String tipoPropietario;
    private String idMascota;
    private String idPropietario;

    /**
     * Listas de listas: detalles, mascotas y propietarios
     */
    private List<List<String>> listaDetalles;
    private List<List<String>> listaMascotas;
    private List<List<String>> listaPropietarios;

    /**
     * Getter del id del detalle propietario-mascota
     * @return idDetallePropietarioMascota
     */
    public String getIdDetallePropietarioMascota() {
        return idDetallePropietarioMascota;
    }

    /**
     * Setter del id del detalle propietario-mascota
     * @param idDetallePropietarioMascota id del detalle
     */
    public void setIdDetallePropietarioMascota(String idDetallePropietarioMascota) {
        this.idDetallePropietarioMascota = idDetallePropietarioMascota;
    }

    /**
     * Getter del tipo de propietario
     * @return tipoPropietario
     */
    public String getTipoPropietario() {
        return tipoPropietario;
    }

    /**
     * Setter del tipo de propietario
     * @param tipoPropietario tipo de propietario
     */
    public void setTipoPropietario(String tipoPropietario) {
        this.tipoPropietario = tipoPropietario;
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
     * Getter del id del propietario
     * @return idPropietario
     */
    public String getIdPropietario() {
        return idPropietario;
    }

    /**
     * Setter del id del propietario
     * @param idPropietario id del propietario
     */
    public void setIdPropietario(String idPropietario) {
        this.idPropietario = idPropietario;
    }

    /**
     * Lista de listas con los detalles de propietarios y mascotas
     * @return listaDetalles
     */
    public List<List<String>> listaDetalles() {
        return listaDetalles;
    }

    /**
     * Lista de listas con las mascotas
     * @return listaMascotas
     */
    public List<List<String>> listaMascotas() {
        return listaMascotas;
    }

    /**
     * Lista de listas con los propietarios
     * @return listaPropietarios
     */
    public List<List<String>> listaPropietarios() {
        return listaPropietarios;
    }

    /**
     * Llena las listas de detalles, mascotas y propietarios
     * @see OwnersPetsDetailsModel
     */
    public void llenarListas(){
        listaDetalles = OwnersPetsDetailsModel.cargarListaDetalles();
        listaMascotas = OwnersPetsDetailsModel.cargarListaMascotas();
        listaPropietarios = OwnersPetsDetailsModel.cargarListaPropietarios();
        cargarListaPersonas();
    }

    /**
     * Captura el id del detalle propietario-mascota según su número en consola
     * @param numero número del registro en consola
     * @return id del detalle o null si no existe
     */
    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaDetalles.size()) {
            return listaDetalles.get(numero - 1).get(0);
        }
        return null;
    }

    /**
     * Captura el id de la mascota según su número en consola
     * @param numero número del registro en consola
     * @return id de la mascota o null si no existe
     */
    public String capturarIdListaMascotas(int numero) {
        if (numero > 0 && numero <= listaMascotas.size()) {
            return listaMascotas.get(numero - 1).get(0);
        }
        return null;
    }

    /**
     * Captura el id del propietario según su número en consola
     * @param numero número del registro en consola
     * @return id del propietario o null si no existe
     */
    public String capturarIdListaPropietarios(int numero) {
        if (numero > 0 && numero <= listaPropietarios.size()) {
            return listaPropietarios.get(numero - 1).get(0);
        }
        return null;
    }

    /**
     * Captura el nombre completo del propietario según su idPersona
     * @param idPersona id de la persona
     * @return nombre completo o "Desconocido" si no se encuentra
     */
    public String capturarNombresPropietarios(String idPersona) {
        for (List<String> propietario : listaPropietarios()) {
            if (propietario.get(1).trim().equalsIgnoreCase(idPersona.trim())) {
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
     * @return nombre de la mascota o "No encontrada" si no existe
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
     * Registra un nuevo detalle de propietario-mascota
     * @return número de filas afectadas
     */
    public int registrarDetalle() {
        return OwnersPetsDetailsModel.ingresarNuevoDetalle(tipoPropietario, idMascota, idPropietario);
    }

    /**
     * Carga los datos de un detalle dado su número de registro
     * @param registro número del registro en consola
     * @return lista de datos del detalle
     */
    public List<String> cargarDatosDetalle(int registro){
        String id = capturarIdLista(registro);
        if (id != null){
            return OwnersPetsDetailsModel.cargarDetalle(id);
        }
        return new ArrayList<>();
    }

    /**
     * Actualiza los datos de un detalle de propietario-mascota
     * @param registro número del registro en consola
     * @param tipoPropietario nuevo tipo de propietario
     * @param idMascota nuevo id de mascota
     * @param idPropietario nuevo id de propietario
     */
    public void actualizarDetalle(int registro, String tipoPropietario, String idMascota, String idPropietario) {
        String id = capturarIdLista(registro);
        if (id != null) {
            int resultado = OwnersPetsDetailsModel.actualizarDetalle(id, tipoPropietario, idMascota, idPropietario);
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
     * Elimina un detalle de propietario-mascota
     * @param numero número del registro en consola
     */
    public void eliminarDetalle(int numero){
        String id = this.capturarIdLista(numero);
        if (id != null) {
            int resultado = OwnersPetsDetailsModel.eliminarDetalle(id);
            if (resultado > 0) {
                System.out.println("Detalle eliminado correctamente.");
            } else {
                System.out.println("Error al eliminar el registro.");
            }
        } else {
            System.out.println("Registro inexistente.");
        }
    }
}
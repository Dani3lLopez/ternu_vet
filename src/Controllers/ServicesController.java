package src.Controllers;

import src.Models.ServicesModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con los servicios,
 * incluyendo el registro, consulta, actualización y eliminación de datos
 */
public class ServicesController {

    /**
     * Constructor vacío del controlador de servicios
     */
    public ServicesController() {}

    /**
     * Atributos de un servicio
     */
    private String idServicio;
    private String nombreServicio;
    private String descripcionServicio;
    private double precioServicio;
    private boolean visibilidadServicio;
    private List<List<String>> listaServicios;

    /**
     * Getter id del servicio
     * @return idServicio
     */
    public String getIdServicio() {
        return idServicio;
    }

    /**
     * Setter id del servicio
     * @param idServicio id del servicio
     */
    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }

    /**
     * Getter del nombre del servicio
     * @return nombreServicio
     */
    public String getNombreServicio() {
        return nombreServicio;
    }

    /**
     * Setter del nombre del servicio
     * @param nombreServicio nombre del servicio
     */
    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    /**
     * Getter de la descripción del servicio
     * @return descripcionServicio
     */
    public String getDescripcionServicio() {
        return descripcionServicio;
    }

    /**
     * Setter de la descripción del servicio
     * @param descripcionServicio descripción del servicio
     */
    public void setDescripcionServicio(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }

    /**
     * Getter del precio del servicio
     * @return precioServicio
     */
    public double getPrecioServicio() {
        return precioServicio;
    }

    /**
     * Setter del precio del servicio
     * @param precioServicio precio del servicio
     */
    public void setPrecioServicio(double precioServicio) {
        this.precioServicio = precioServicio;
    }

    /**
     * Getter de la visibilidad del servicio
     * @return visibilidadServicio
     */
    public boolean isVisibilidadServicio() {
        return visibilidadServicio;
    }

    /**
     * Setter de la visibilidad del servicio
     * @param visibilidadServicio visibilidad del servicio
     */
    public void setVisibilidadServicio(boolean visibilidadServicio) {
        this.visibilidadServicio = visibilidadServicio;
    }

    /**
     * Carga la lista de servicios desde el modelo
     * @see ServicesModel
     */
    public void cargarListaServicios() {
        listaServicios = ServicesModel.cargarListaServicios();
    }

    /**
     * Retorna la lista de servicios
     * @return listaServicios
     */
    public List<List<String>> getListaServicios() {
        return listaServicios;
    }

    /**
     * Registra un nuevo servicio
     * @return número de filas afectadas
     */
    public int registrarServicio() {
        return ServicesModel.ingresarServicio(nombreServicio, descripcionServicio, precioServicio);
    }

    /**
     * Captura el id del servicio según su número en consola
     * @param numero número del registro en consola
     * @return id del servicio o null si no existe
     */
    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaServicios.size()) {
            return listaServicios.get(numero - 1).get(0);
        }
        return null;
    }

    /**
     * Carga los datos de un servicio dado su número de registro
     * @param registro número de registro en consola
     * @return lista de datos del servicio
     */
    public List<String> cargarDatosServicio(int registro) {
        String id = capturarIdLista(registro);
        if (id != null) {
            return ServicesModel.cargarServicio(id);
        }
        return new ArrayList<>();
    }

    /**
     * Actualiza los datos de un servicio
     * @param registro número del registro en consola
     * @param nombreServicio nuevo nombre del servicio
     * @param descripcionServicio nueva descripción del servicio
     * @param precioServicio nuevo precio del servicio
     */
    public void actualizarServicio(int registro, String nombreServicio, String descripcionServicio,
                                   double precioServicio) {
        String id = capturarIdLista(registro);
        if (id != null) {
            int resultado = ServicesModel.actualizarServicio(id, nombreServicio, descripcionServicio, precioServicio);
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
     * Elimina un servicio
     * @param numero número del registro en consola
     */
    public void eliminarServicio(int numero) {
        String id = this.capturarIdLista(numero);
        if (id != null) {
            int resultado = ServicesModel.eliminarServicio(id);
            if (resultado > 0) {
                System.out.println("Servicio eliminado correctamente.");
            } else {
                System.out.println("Error al eliminar el registro.");
            }
        } else {
            System.out.println("Registro inexistente.");
        }
    }
}
package src.Controllers;

import src.Models.PeopleModel;
import src.Models.ServicesModel;

import java.util.ArrayList;
import java.util.List;

public class ServicesController {
    public ServicesController() {

    }

    // Para poder guardar los datos de un servicio, hicimos diferentes atributos
    // privados
    private String idServicio;
    private String nombreServicio;
    private String descripcionServicio;
    private double precioServicio;
    private boolean visibilidadServicio;
    private List<List<String>> listaServicios; // Lista de listas para toda la información

    // También, hicmos distintos getters y setters para cada atributo creado
    public String getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getDescripcionServicio() {
        return descripcionServicio;
    }

    public void setDescripcionServicio(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }

    public double getPrecioServicio() {
        return precioServicio;
    }

    public void setPrecioServicio(double precioServicio) {
        this.precioServicio = precioServicio;
    }

    public boolean isVisibilidadServicio() {
        return visibilidadServicio;
    }

    public void setVisibilidadServicio(boolean visibilidadServicio) {
        this.visibilidadServicio = visibilidadServicio;
    }

    // Usando el modelo, carga la lista de servicios
    public void cargarListaServicios() {
        listaServicios = ServicesModel.cargarListaServicios();
    }

    // Muestra la lista
    public List<List<String>> getListaServicios() {
        return listaServicios;
    }

    // Con el modelo, registra un nuevo servicio
    public int registrarServicio() {
        return ServicesModel.ingresarServicio(nombreServicio, descripcionServicio, precioServicio);
    }

    // Dado un registro, guarda el ID de un servicio
    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaServicios.size()) {
            return listaServicios.get(numero - 1).get(0);
        }
        return null;
    }

    // Dado un registro, carga los datos de un servicio
    public List<String> cargarDatosServicio(int registro) {
        String id = capturarIdLista(registro);
        if (id != null) {
            return ServicesModel.cargarServicio(id);
        }
        return new ArrayList<>();
    }

    // Actualiza los datos cambiados de un servicio existente
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

    // Dado un registo, elimina un servicio
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

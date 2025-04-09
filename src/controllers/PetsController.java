package src.controllers;

import src.models.PetsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con las mascotas,
 * incluyendo el registro, consulta, actualización y eliminación de datos
 */
public class PetsController {

    /**
     * Constructor vacío del controlador de mascotas
     */
    public PetsController() {}

    /**
     * Atributos de una mascota
     */
    private String idMascota;
    private String nombreMascota;
    private String colorMascota;
    private double pesoMascota;
    private String unidadPesoMascota;
    private String generoMascota;
    private String codigoChipMascota;
    private String estadoReproductivoMascota;
    private String fechaNacimientoMascota;
    private String tallaMascota;
    private boolean fallecimientoMascota;
    private String razonFallecimiento;
    private boolean visibilidadMascota;
    private List<List<String>> listaMascotas;

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
     * Getter del nombre de la mascota
     * @return nombreMascota
     */
    public String getNombreMascota() {
        return nombreMascota;
    }

    /**
     * Setter del nombre de la mascota
     * @param nombreMascota nombre de la mascota
     */
    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    /**
     * Getter del color de la mascota
     * @return colorMascota
     */
    public String getColorMascota() {
        return colorMascota;
    }

    /**
     * Setter del color de la mascota
     * @param colorMascota color de la mascota
     */
    public void setColorMascota(String colorMascota) {
        this.colorMascota = colorMascota;
    }

    /**
     * Getter del peso de la mascota
     * @return pesoMascota
     */
    public double getPesoMascota() {
        return pesoMascota;
    }

    /**
     * Setter del peso de la mascota
     * @param pesoMascota peso de la mascota
     */
    public void setPesoMascota(double pesoMascota) {
        this.pesoMascota = pesoMascota;
    }

    /**
     * Getter de la unidad de peso de la mascota
     * @return unidadPesoMascota
     */
    public String getUnidadPesoMascota() {
        return unidadPesoMascota;
    }

    /**
     * Setter de la unidad de peso de la mascota
     * @param unidadPesoMascota unidad de peso de la mascota
     */
    public void setUnidadPesoMascota(String unidadPesoMascota) {
        this.unidadPesoMascota = unidadPesoMascota;
    }

    /**
     * Getter del género de la mascota
     * @return generoMascota
     */
    public String getGeneroMascota() {
        return generoMascota;
    }

    /**
     * Setter del género de la mascota
     * @param generoMascota género de la mascota
     */
    public void setGeneroMascota(String generoMascota) {
        this.generoMascota = generoMascota;
    }

    /**
     * Getter del código del chip de la mascota
     * @return codigoChipMascota
     */
    public String getCodigoChipMascota() {
        return codigoChipMascota;
    }

    /**
     * Setter del código del chip de la mascota
     * @param codigoChipMascota código del chip de la mascota
     */
    public void setCodigoChipMascota(String codigoChipMascota) {
        this.codigoChipMascota = codigoChipMascota;
    }

    /**
     * Getter del estado reproductivo de la mascota
     * @return estadoReproductivoMascota
     */
    public String getEstadoReproductivoMascota() {
        return estadoReproductivoMascota;
    }

    /**
     * Setter del estado reproductivo de la mascota
     * @param estadoReproductivoMascota estado reproductivo de la mascota
     */
    public void setEstadoReproductivoMascota(String estadoReproductivoMascota) {
        this.estadoReproductivoMascota = estadoReproductivoMascota;
    }

    /**
     * Getter de la fecha de nacimiento de la mascota
     * @return fechaNacimientoMascota
     */
    public String getFechaNacimientoMascota() {
        return fechaNacimientoMascota;
    }

    /**
     * Setter de la fecha de nacimiento de la mascota
     * @param fechaNacimientoMascota fecha de nacimiento de la mascota
     */
    public void setFechaNacimientoMascota(String fechaNacimientoMascota) {
        this.fechaNacimientoMascota = fechaNacimientoMascota;
    }

    /**
     * Getter de la talla de la mascota
     * @return tallaMascota
     */
    public String getTallaMascota() {
        return tallaMascota;
    }

    /**
     * Setter de la talla de la mascota
     * @param tallaMascota talla de la mascota
     */
    public void setTallaMascota(String tallaMascota) {
        this.tallaMascota = tallaMascota;
    }

    /**
     * Getter de si la mascota ha fallecido
     * @return fallecimientoMascota
     */
    public boolean isFallecimientoMascota() {
        return fallecimientoMascota;
    }

    /**
     * Setter de si la mascota ha fallecido
     * @param fallecimientoMascota estado de fallecimiento de la mascota
     */
    public void setFallecimientoMascota(boolean fallecimientoMascota) {
        this.fallecimientoMascota = fallecimientoMascota;
    }

    /**
     * Getter de la razón del fallecimiento de la mascota
     * @return razonFallecimiento
     */
    public String getRazonFallecimiento() {
        return razonFallecimiento;
    }

    /**
     * Setter de la razón del fallecimiento de la mascota
     * @param razonFallecimiento razón del fallecimiento
     */
    public void setRazonFallecimiento(String razonFallecimiento) {
        this.razonFallecimiento = razonFallecimiento;
    }

    /**
     * Getter de la visibilidad de la mascota
     * @return visibilidadMascota
     */
    public boolean isVisibilidadMascota() {
        return visibilidadMascota;
    }

    /**
     * Setter de la visibilidad de la mascota
     * @param visibilidadMascota estado de visibilidad de la mascota
     */
    public void setVisibilidadMascota(boolean visibilidadMascota) {
        this.visibilidadMascota = visibilidadMascota;
    }

    /**
     * Carga la lista de mascotas desde el modelo
     * @see PetsModel
     */
    public void cargarListaMascotas() {
        listaMascotas = PetsModel.cargarListaMascotas();
    }

    /**
     * Retorna la lista de mascotas
     * @return listaMascotas
     */
    public List<List<String>> getListaMascotas() {
        return listaMascotas;
    }

    /**
     * Registra una nueva mascota
     * @return número de filas afectadas
     */
    public int registrarMascota() {
        return PetsModel.ingresarNuevaMascota(nombreMascota, colorMascota, pesoMascota, unidadPesoMascota,
                generoMascota, codigoChipMascota, estadoReproductivoMascota, fechaNacimientoMascota, tallaMascota);
    }

    /**
     * Captura el id de la mascota según su número en consola
     * @param numero número del registro en consola
     * @return id de la mascota o null si no existe
     */
    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaMascotas.size()) {
            return listaMascotas.get(numero - 1).get(0);
        }
        return null;
    }

    /**
     * Carga los datos de una mascota dado su número de registro
     * @param registro número de registro en consola
     * @return lista de datos de la mascota
     */
    public List<String> cargarDatosMascota(int registro) {
        String id = capturarIdLista(registro);
        if (id != null) {
            return PetsModel.cargarMascota(id);
        }
        return new ArrayList<>();
    }

    /**
     * Actualiza los datos de una mascota
     * @param registro número del registro en consola
     * @param nombreMascota nuevo nombre de la mascota
     * @param colorMascota nuevo color de la mascota
     * @param pesoMascota nuevo peso de la mascota
     * @param unidadPesoMascota nueva unidad de peso
     * @param generoMascota nuevo género de la mascota
     * @param codigoChipMascota nuevo código de chip
     * @param estadoReproductivoMascota nuevo estado reproductivo
     * @param fechaNacimientoMascota nueva fecha de nacimiento
     * @param tallaMascota nueva talla de la mascota
     * @param fallecimientoMascota nuevo estado de fallecimiento
     * @param razonFallecimiento nueva razón de fallecimiento
     */
    public void actualizarMascota(int registro, String nombreMascota, String colorMascota, double pesoMascota,
                                  String unidadPesoMascota, String generoMascota, String codigoChipMascota, String estadoReproductivoMascota,
                                  String fechaNacimientoMascota, String tallaMascota, boolean fallecimientoMascota,
                                  String razonFallecimiento) {
        String id = capturarIdLista(registro);
        if (id != null) {
            int resultado = PetsModel.actualizarMascota(id, nombreMascota, colorMascota, pesoMascota, unidadPesoMascota,
                    generoMascota, codigoChipMascota, estadoReproductivoMascota, fechaNacimientoMascota, tallaMascota,
                    fallecimientoMascota, razonFallecimiento);
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
     * Elimina una mascota
     * @param numero número del registro en consola
     */
    public void eliminarMascota(int numero) {
        String id = this.capturarIdLista(numero);
        if (id != null) {
            int resultado = PetsModel.eliminarMascota(id);
            if (resultado > 0) {
                System.out.println("Mascota eliminada correctamente.");
            } else {
                System.out.println("Error al eliminar el registro.");
            }
        } else {
            System.out.println("Registro inexistente.");
        }
    }
}

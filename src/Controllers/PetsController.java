package src.Controllers;

import src.Models.PeopleModel;
import src.Models.PetsModel;

import java.util.ArrayList;
import java.util.List;

public class PetsController {
    // Constructor
    public PetsController() {

    }

    // Atributos
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

    // Getters y Setters para acceder y modificar los atributos

    public String getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(String idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getColorMascota() {
        return colorMascota;
    }

    public void setColorMascota(String colorMascota) {
        this.colorMascota = colorMascota;
    }

    public double getPesoMascota() {
        return pesoMascota;
    }

    public void setPesoMascota(double pesoMascota) {
        this.pesoMascota = pesoMascota;
    }

    public String getUnidadPesoMascota() {
        return unidadPesoMascota;
    }

    public void setUnidadPesoMascota(String unidadPesoMascota) {
        this.unidadPesoMascota = unidadPesoMascota;
    }

    public String getGeneroMascota() {
        return generoMascota;
    }

    public void setGeneroMascota(String generoMascota) {
        this.generoMascota = generoMascota;
    }

    public String getCodigoChipMascota() {
        return codigoChipMascota;
    }

    public void setCodigoChipMascota(String codigoChipMascota) {
        this.codigoChipMascota = codigoChipMascota;
    }

    public String getEstadoReproductivoMascota() {
        return estadoReproductivoMascota;
    }

    public void setEstadoReproductivoMascota(String estadoReproductivoMascota) {
        this.estadoReproductivoMascota = estadoReproductivoMascota;
    }

    public String getFechaNacimientoMascota() {
        return fechaNacimientoMascota;
    }

    public void setFechaNacimientoMascota(String fechaNacimientoMascota) {
        this.fechaNacimientoMascota = fechaNacimientoMascota;
    }

    public String getTallaMascota() {
        return tallaMascota;
    }

    public void setTallaMascota(String tallaMascota) {
        this.tallaMascota = tallaMascota;
    }

    public boolean isFallecimientoMascota() {
        return fallecimientoMascota;
    }

    public void setFallecimientoMascota(boolean fallecimientoMascota) {
        this.fallecimientoMascota = fallecimientoMascota;
    }

    public String getRazonFallecimiento() {
        return razonFallecimiento;
    }

    public void setRazonFallecimiento(String razonFallecimiento) {
        this.razonFallecimiento = razonFallecimiento;
    }

    public boolean isVisibilidadMascota() {
        return visibilidadMascota;
    }

    public void setVisibilidadMascota(boolean visibilidadMascota) {
        this.visibilidadMascota = visibilidadMascota;
    }

    // cargar las mascotas desde el modelo
    public void cargarListaMascotas() {
        listaMascotas = PetsModel.cargarListaMascotas();
    }

    public List<List<String>> getListaMascotas() {
        return listaMascotas;
    }

    // registra nueva mascota utilizando el modelo
    public int registrarMascota() {
        return PetsModel.ingresarNuevaMascota(nombreMascota, colorMascota, pesoMascota, unidadPesoMascota,
                generoMascota, codigoChipMascota, estadoReproductivoMascota, fechaNacimientoMascota, tallaMascota);
    }

    // obtiene numero de registro como ID y retorna null si es inválido
    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaMascotas.size()) {
            return listaMascotas.get(numero - 1).get(0);
        }
        return null;
    }

    // carga los datos de la mascota usando su ID
    public List<String> cargarDatosMascota(int registro) {
        String id = capturarIdLista(registro);
        if (id != null) {
            return PetsModel.cargarMascota(id);
        }
        return new ArrayList<>();
    }

    // actualiza los datos de la mascota si el ID no es nulo
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

    // Usando el numero de registro, se elimina una mascota
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

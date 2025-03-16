package src.Controllers;

import src.Models.PeopleModel;
import src.Models.PetsModel;

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

    // Getters y Setters

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

    public void cargarListaMascotas() {
        listaMascotas = PetsModel.cargarListaMascotas();
    }
    public List<List<String>> getListaMascotas() {
        return listaMascotas;
    }

    public int registrarMascota() {
        return PetsModel.ingresarNuevaMascota(nombreMascota, colorMascota, pesoMascota, unidadPesoMascota, generoMascota, codigoChipMascota, estadoReproductivoMascota, fechaNacimientoMascota, tallaMascota);
    }

    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaMascotas.size()) {
            return listaMascotas.get(numero - 1).get(0);
        }
        return null;
    }

    public void eliminarMascota(int numero){
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

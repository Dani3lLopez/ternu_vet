package src.Controllers;

import src.Models.ConsultationsModel;
import src.Models.OwnersPetsDetailsModel;

import java.util.ArrayList;
import java.util.List;

public class OwnersPetsDetailsController extends OwnersController{
    public OwnersPetsDetailsController() {
        super();
        listaDetalles = new ArrayList<>();
        listaMascotas = new ArrayList<>();
        listaPropietarios = new ArrayList<>();
    }

    private String idDetallePropietarioMascota;
    private String tipoPropietario;
    private String idMascota;
    private String idPropietario;
    private List<List<String>> listaDetalles;
    private List<List<String>> listaMascotas;
    private List<List<String>> listaPropietarios;

    public String getIdDetallePropietarioMascota() {
        return idDetallePropietarioMascota;
    }

    public void setIdDetallePropietarioMascota(String idDetallePropietarioMascota) {
        this.idDetallePropietarioMascota = idDetallePropietarioMascota;
    }

    public String getTipoPropietario() {
        return tipoPropietario;
    }

    public void setTipoPropietario(String tipoPropietario) {
        this.tipoPropietario = tipoPropietario;
    }

    public String getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(String idMascota) {
        this.idMascota = idMascota;
    }

    public String getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(String idPropietario) {
        this.idPropietario = idPropietario;
    }

    public List<List<String>> listaDetalles() {
        return listaDetalles;
    }

    public List<List<String>> listaMascotas() {
        return listaMascotas;
    }

    public List<List<String>> listaPropietarios() {
        return listaPropietarios;
    }

    public void llenarListas(){
        listaDetalles = OwnersPetsDetailsModel.cargarListaDetalles();
        listaMascotas = OwnersPetsDetailsModel.cargarListaMascotas();
        listaPropietarios = OwnersPetsDetailsModel.cargarListaPropietarios();
        cargarListaPersonas();
    }

    public String capturarIdLista(int numero) {
        if (numero > 0 && numero <= listaDetalles.size()) {
            return listaDetalles.get(numero - 1).get(0);
        }
        return null;
    }

    public String capturarIdListaMascotas(int numero) {
        if (numero > 0 && numero <= listaMascotas.size()) {
            return listaMascotas.get(numero - 1).get(0);
        }
        return null;
    }

    public String capturarIdListaPropietarios(int numero) {
        if (numero > 0 && numero <= listaPropietarios.size()) {
            return listaPropietarios.get(numero - 1).get(0);
        }
        return null;
    }

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

    public String capturarMascotas(String idMascota){
        for (List<String> mascota : listaMascotas()) {
            if (mascota.get(0).trim().equalsIgnoreCase(idMascota)) {
                return mascota.get(1);
            }
        }
        return "No encontrada";
    }

    public int registrarDetalle() {
        return OwnersPetsDetailsModel.ingresarNuevoDetalle(tipoPropietario, idMascota, idPropietario);
    }
}

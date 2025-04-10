package src.views;

import src.controllers.OwnersPetsDetailsController;
import src.validations.FormatException;
import src.validations.Validations;

import java.util.List;
import java.util.Scanner;

/**
 * Vista: OwnerPetsDetails
 * Contiene los métodos necesarios para administrar los detalles de la relación propietario-mascota
 */
public class OwnersPetsDetails {
    Scanner scan = new Scanner(System.in);
    public OwnersPetsDetailsController detail = new OwnersPetsDetailsController();

    /**
     * Menú de opciones para administrar los datos
     */
    public void detailMenu() {
        Scanner scan = new Scanner(System.in);
        String separador = "-";
        boolean active = true;
        while (active) {
            System.out.println("\u2764\uFE0F Qué haremos hoy?");
            System.out.println("1. Listar Detalles");
            System.out.println("2. Registrar Detalle");
            System.out.println("3. Actualizar Detalle");
            System.out.println("4. Eliminar Detalle");
            System.out.println("5. Volver al menú principal");
            System.out.println(separador.repeat(50));
            System.out.print("Seleccione una opción: ");
            String choice = scan.nextLine().trim();
            if(!choice.isEmpty()){
                try{
                    Validations.validarRangoNumeros(choice, 1, 5);
                    switch (Integer.parseInt(choice)) {
                        case 1:
                            cargarDetalles();
                            break;
                        case 2:
                            registrarDetalle();
                            System.out.println(separador.repeat(50));
                            break;
                        case 3:
                            cargarDetalles();
                            String r = "";
                            while(true){
                                System.out.print("Ingrese el número de registro a actualizar: ");
                                r = scan.nextLine().trim();
                                try{
                                    Validations.validarNumeros(r);
                                    break;
                                }catch(FormatException e){
                                    System.out.println(e.getMessage());
                                }
                            }
                            if(r.isEmpty()){
                                break;
                            }else{
                                actualizarDetalle(Integer.parseInt(r));
                                System.out.println(separador.repeat(50));
                                break;
                            }
                        case 4:
                            cargarDetalles();
                            String registro = "";
                            while(true){
                                System.out.print("Ingrese el número de registro a eliminar: ");
                                registro = scan.nextLine().trim();
                                try{
                                    Validations.validarNumeros(registro);
                                    break;
                                }catch (FormatException e){
                                    System.out.println(e.getMessage());
                                }
                            }
                            if(registro.isEmpty()){
                                break;
                            }else{
                                eliminarDetalle(Integer.parseInt(registro));
                                System.out.println(separador.repeat(50));
                                break;
                            }
                        case 5:
                            active = false;
                            System.out.println("Cerrando menú...");
                            break;
                    }
                }catch(FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * Carga todos los detalles almacenados en la base de datos
     */
    public void cargarDetalles() {
        detail.llenarListas();
        List<List<String>> detalles = detail.listaDetalles();
        if (detail.listaDetalles().isEmpty()) {
            System.out.println("No hay detalles registrados.");
        } else {
            String separador = "-".repeat(100);
            System.out.println(separador);
            System.out.printf("| %-5s | %-20s | %-25s | %-20s |\n", "No.", "Propietario", "Tipo", "Mascota");
            System.out.println(separador);

            int n = 1;
            for (List<String> detalle : detalles) {
                String tipo = detalle.get(1);
                String mascota = detail.capturarMascotas(detalle.get(2));
                String idPropietario = detalle.get(3);
                String idPersonaPropietario = "";
                for (List<String> propietario : detail.listaPropietarios()) {
                    if (propietario.get(0).equalsIgnoreCase(idPropietario)) {
                        idPersonaPropietario = propietario.get(1);
                        break;
                    }
                }
                String propietario = detail.capturarNombresPropietarios(idPersonaPropietario);

                System.out.printf("| %-5d | %-20s | %-25s | %-20s |\n", n, propietario, tipo, mascota);
                n++;
            }
            System.out.println(separador);
        }
    }

    /**
     * Registra nuevos detalles de la relación propietario-mascota
     */
    public void registrarDetalle() {
        detail.llenarListas();

        String separador = "-".repeat(70);
        System.out.println(separador);
        System.out.printf("| %-5s | %-50s |\n", "No.", "Propietario");
        System.out.println(separador);

        List<List<String>> propietarios = detail.listaPropietarios();

        int r = 1;
        for (List<String> propietario : propietarios) {
            String idp = propietario.get(1);
            String nombrePropietario = detail.capturarNombresPropietarios(idp);

            System.out.printf("| %-5d | %-50s |\n", r, nombrePropietario);
            r++;
        }
        System.out.println(separador);

        String valor = "";
        while(true){
            System.out.print("Seleccione al propietario/a *: ");
            valor = scan.nextLine().trim();
            try{
                Validations.validarCampoObligatorio(valor);
                Validations.validarRangoNumeros(valor, 1, propietarios.size());
                break;
            }catch (FormatException e){
                System.out.println(e.getMessage());
            }
        }

        String id = detail.capturarIdListaPropietarios(Integer.parseInt(valor));
        detail.setIdPropietario(id);

        System.out.println(separador);
        System.out.printf("| %-5s | %-50s |\n", "No.", "Mascota");
        System.out.println(separador);

        for (int c = 0; c < detail.listaMascotas().size(); c++) {
            List<String> mascota = detail.listaMascotas().get(c);
            System.out.printf("| %-5d | %-50s |\n", (c + 1), mascota.get(1));
        }
        System.out.println(separador);

        String v = "";
        while(true){
            System.out.print("Seleccione a la mascota *: ");
            v = scan.nextLine().trim();
            try {
                Validations.validarCampoObligatorio(v);
                Validations.validarRangoNumeros(v, 1, detail.listaMascotas().size());
                break;
            }catch (FormatException e){
                System.out.println(e.getMessage());
            }
        }

        String n = detail.capturarIdListaMascotas(Integer.parseInt(v));
        detail.setIdMascota(n);

        System.out.println("Tipo de propietario *: ");
        System.out.println("1. Propietario principal");
        System.out.println("2. Propietario secundario");

        String tipoPropietario = "";
        while (true){
            System.out.print("Opción seleccionada: ");
            tipoPropietario = scan.nextLine().trim();
            try {
                Validations.validarCampoObligatorio(tipoPropietario);
                Validations.validarRangoNumeros(tipoPropietario, 1, 2);
                break;
            }catch (FormatException e){
                System.out.println(e.getMessage());
            }
        }

        int tipoSeleccionado = Integer.parseInt(tipoPropietario);
        if (tipoSeleccionado == 1){
            tipoPropietario = "Propietario principal";
        }else if(tipoSeleccionado == 2){
            tipoPropietario = "Propietario secundario";
        }
        detail.setTipoPropietario(tipoPropietario);

        int resultado = detail.registrarDetalle();

        if (resultado == 1) {
            System.out.println("detalle registrado con éxito.");
        } else {
            System.out.println("Ha ocurrido un error al registrar el detalle.");
        }
    }

    /**
     * Actualiza un detalle existente
     * @param r número de registro
     * Es posible dejar campos en blanco para mantener su valor actual
     */
    public void actualizarDetalle(int r) {
        List<String> detalle = detail.cargarDatosDetalle(r);
        if (detalle.isEmpty()) {
            System.out.println("No se encontró el registro especificado");
            return;
        }

        String separador = "-".repeat(70);
        System.out.println(separador);
        System.out.printf("| %-5s | %-50s |\n", "No.", "Propietario");
        System.out.println(separador);

        List<List<String>> propietarios = detail.listaPropietarios();
        for (int i = 0; i < propietarios.size(); i++) {
            List<String> propietario = propietarios.get(i);
            String nombrePropietario = detail.capturarNombresPropietarios(propietario.get(1)); // ID persona en la posición 3
            System.out.printf("| %-5d | %-50s |\n", (i + 1), nombrePropietario);
        }
        System.out.println(separador);

        String np = "";
        String nuevoIdPropietario = ""; // ID actual del doctor
        while(true){
            System.out.print("Nuevo propietario: ");
            np = scan.nextLine().trim();
            if(np.isEmpty()){
                nuevoIdPropietario = detalle.get(3);
                break;
            }else{
                try{
                    Validations.validarRangoNumeros(np, 1, propietarios.size());
                    nuevoIdPropietario = propietarios.get(Integer.parseInt(np) - 1).get(0);
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println(separador);
        System.out.printf("| %-5s | %-50s |\n", "No.", "Mascota");
        System.out.println(separador);

        List<List<String>> mascotas = detail.listaMascotas();
        for (int i = 0; i < mascotas.size(); i++) {
            System.out.printf("| %-5d | %-50s |\n", (i + 1), mascotas.get(i).get(1));
        }
        System.out.println(separador);

        String nm = "";
        String nuevoIdMascota = "";
        while(true){
            System.out.print("Nueva mascota: ");
            nm = scan.nextLine().trim();
            if(nm.isEmpty()){
                nuevoIdMascota = detalle.get(2);
                break;
            }else{
                try{
                    Validations.validarRangoNumeros(nm, 1, mascotas.size());
                    nuevoIdMascota = mascotas.get(Integer.parseInt(nm) - 1).get(0);
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println("Nuevo tipo de propietario:");
        System.out.println("1. Propietario principal");
        System.out.println("2. Propietario secundario");

        String nuevoTipoPropietario = "";
        while (true){
            System.out.print("Opción seleccionada: ");
            nuevoTipoPropietario = scan.nextLine().trim();
            if(nuevoTipoPropietario.isEmpty()){
                nuevoTipoPropietario = detalle.get(1);
                break;
            }else{
                try {
                    Validations.validarRangoNumeros(nuevoTipoPropietario, 1, 2);
                    int tipoSeleccionado = Integer.parseInt(nuevoTipoPropietario);
                    if (tipoSeleccionado == 1){
                        nuevoTipoPropietario = "Propietario principal";
                    }else if(tipoSeleccionado == 2){
                        nuevoTipoPropietario = "Propietario secundario";
                    }
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        // Actualizar consulta
        detail.actualizarDetalle(r, nuevoTipoPropietario, nuevoIdMascota, nuevoIdPropietario);
    }

    /**
     * Elimina un detalle
     * @param registro número de registro
     */
    public void eliminarDetalle(int registro) {
        detail.eliminarDetalle(registro);
    }
}

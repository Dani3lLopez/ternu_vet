package src.views;

import src.controllers.ServicesController;
import src.validations.FormatException;
import src.validations.Validations;

import java.util.List;
import java.util.Scanner;

/**
 * Vista: Services
 * Contiene los métodos necesarios para administrar los servicios
 */
public class Services {
    Scanner scan = new Scanner(System.in);
    public ServicesController service = new ServicesController();

    /**
     * Menú de opciones para administrar los datos
     */
    public void serviceMenu() {
        String separador = "-";

        // Este bucle contiene todas las opciones principales del menú
        boolean active = true;
        while (active) {
            System.out.println("\uD83D\uDEC1 Qué haremos hoy?");
            System.out.println("1. Listar Servicios");
            System.out.println("2. Registrar Servicio");
            System.out.println("3. Actualizar Servicio");
            System.out.println("4. Eliminar Servicio");
            System.out.println("5. Volver al menú principal");
            System.out.println(separador.repeat(50));
            Services actual = new Services();

            // valida la opción seleccionada
            String choice;
            try{
                System.out.print("Seleccione una opción: ");
                choice = scan.nextLine().trim();
                if (!choice.isEmpty()) {
                    Validations.validarRangoNumeros(choice, 1, 5);
                    switch (Integer.parseInt(choice)) {
                        case 1:
                            actual.cargarServicios();
                            break;
                        case 2:
                            actual.registrarServicio();
                            break;
                        case 3:
                            actual.cargarServicios();
                            String registroActualizar;

                            // Este bucle solicita un número de registro hasta que se ingrese uno válido
                            while (true) {
                                System.out.print("Ingrese el número de registro a actualizar: ");
                                registroActualizar = scan.nextLine().trim();
                                try{
                                    Validations.validarNumeros(registroActualizar);
                                    break;
                                }catch (FormatException e){
                                    System.out.println(e.getMessage());
                                }
                            }
                            if(registroActualizar.isEmpty()){
                                break;
                            }else{
                                // Actualizada el servicio que se seleccionó
                                actual.actualizarServicio(Integer.parseInt(registroActualizar));
                                System.out.println("-".repeat(50));
                                break;
                            }
                        case 4:
                            actual.cargarServicios();
                            String registroEliminar;

                            // Este bucle al igual que el anterior solicita el número de registro hasta que sea valido
                            while (true) {
                                System.out.print("Ingrese el número de registro a eliminar: ");
                                registroEliminar = scan.nextLine().trim();
                                try{
                                    Validations.validarNumeros(registroEliminar);
                                    break;
                                }catch (FormatException e){
                                    System.out.println(e.getMessage());
                                }
                            }
                            if(registroEliminar.isEmpty()){
                                break;
                            }else{
                                // Elimina el servicio que fue seleccionado
                                actual.eliminarServicio(Integer.parseInt(registroEliminar));
                                System.out.println("-".repeat(50));
                                break;
                            }
                        case 5:
                            active = false;
                            System.out.println("Cerrando menú...");
                            break;
                    }
                }
            }catch (FormatException e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Carga todos los servicios almacenados en la base de datos
     */
    public void cargarServicios() {
        service.cargarListaServicios();
        if (service.getListaServicios().isEmpty()) {
            System.out.println("No hay servicios registrados.");
        } else {
            String separador = "-".repeat(120);
            System.out.println(separador);
            System.out.printf("| %-5s | %-35s | %-55s | %-25s |\n", "No.", "Nombre", "Descripción", "Precio");
            System.out.println(separador);

            int i = 1;
            for (List<String> servicio : service.getListaServicios()) {
                String valorDescripcion = servicio.get(2);
                if (valorDescripcion == null) {
                    valorDescripcion = "";
                }

                // Le da formato al precio del servicio que obtiene
                String valorPrecio = servicio.get(3);
                valorPrecio = "$ " + valorPrecio;
                System.out.printf("| %-5s | %-35s | %-55s | %-25s |\n", i, servicio.get(1), valorDescripcion,
                        valorPrecio);
                i++;
            }
            System.out.println(separador);
        }
    }

    /**
     * Registra nuevos servicios en la base de datos
     */
    public void registrarServicio() {
        String nombreServicio;

        // Válida que se ingrese un nombre y que el campo no este vacío
        while (true) {
            System.out.print("Ingrese el nombre del servicio *: ");
            nombreServicio = scan.nextLine().trim();
            try{
                Validations.validarCampoObligatorio(nombreServicio);
                break;
            }catch (FormatException e){
                System.out.println(e.getMessage());
            }
        }
        service.setNombreServicio(nombreServicio);

        // Pide que se ingrese una descripción, este espacio sí puede estar vacío
        String descripcionServicio;
        System.out.print("Ingrese la descripción del servicio: ");
        descripcionServicio = scan.nextLine().trim();
        if (descripcionServicio.equals("")) {
            descripcionServicio = null;
        }
        service.setDescripcionServicio(descripcionServicio);

        double precioServicio;

        // Este bucle asegura que se ingrese el precio del servicio y que sí sea un
        // número positivo
        while (true) {
            System.out.print("Ingrese el precio del servicio *: ");
            String input = scan.nextLine().trim();
            try{
                Validations.validarCampoObligatorio(input);
                Validations.validarDecimales(input);
                precioServicio = Double.parseDouble(input);
                break;
            }catch (FormatException e){
                System.out.println(e.getMessage());
            }
        }
        service.setPrecioServicio(precioServicio);

        // Registra la información agrega del servicio
        int resultado = service.registrarServicio();
        if (resultado == 1) {
            System.out.println("Servicio registrado con éxito");
        } else {
            System.out.println("Ha ocurrido un error");
        }
    }

    /**
     * Actualiza un servicio existente
     * @param registro número de registro
     * Es posible dejar campos en blanco para mantener su valor actual
     */
    public void actualizarServicio(int registro) {
        List<String> servicio = service.cargarDatosServicio(registro);

        if (servicio.isEmpty()) {
            System.out.println("No se encontró el registro especificado");
            return;
        }

        String nombreServicio;

        // Asegura que sí se ingrese un nuevo nombre
        System.out.print("Ingrese el nuevo nombre del servicio: ");
        nombreServicio = scan.nextLine().trim();
        if (nombreServicio.isEmpty()) {
            nombreServicio = servicio.get(1);
        }

        String descripcionServicio;
        System.out.print("Ingrese la nueva descripción del servicio: ");
        descripcionServicio = scan.nextLine().trim();
        if (descripcionServicio.isEmpty()) {
            descripcionServicio = servicio.get(2);
        }

        double precioServicio;

        // Asegura que se ingrese el nuevo precio y que sea un número positivo
        while (true) {
            System.out.print("Ingrese el nuevo precio del servicio: ");
            String input = scan.nextLine().trim();
            if (input.isEmpty()) {
                precioServicio = Double.parseDouble(servicio.get(3));
                break;
            } else {
                try{
                    Validations.validarDecimales(input);
                    precioServicio = Double.parseDouble(input);
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        service.actualizarServicio(registro, nombreServicio, descripcionServicio, precioServicio);
    }

    /**
     * Elimina un servicio
     * @param registro número de registro
     */
    public void eliminarServicio(int registro) {
        service.eliminarServicio(registro);
    }
}

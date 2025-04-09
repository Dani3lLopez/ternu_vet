package src.views;

import src.controllers.ServicesController;

import java.util.List;
import java.util.Scanner;

public class Services {
    Scanner scan = new Scanner(System.in);
    public ServicesController service = new ServicesController();

    // Hicimos un método para el menú principal de servicios
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

            // Este válida la opción seleccionada
            String choice;
            while (true) {
                System.out.print("Seleccione una opción: ");
                choice = scan.nextLine().trim();
                boolean validarMenu = validarOpciones(choice);
                if (!validarMenu) {
                    System.out.println("Opción inválida");
                } else {
                    break;
                }
            }

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

                    // Estebucle solicita un número de registro hasta que se ingrese uno válido
                    while (true) {
                        System.out.print("Ingrese el número de registro a actualizar: ");
                        registroActualizar = scan.nextLine().trim();
                        boolean validacion = validarOpciones(registroActualizar);
                        if (!validacion) {
                            System.out.println("El valor ingresado es inválido");
                        } else {
                            break;
                        }
                    }

                    // Actualizada el servicio que se seleccionó
                    actual.actualizarServicio(Integer.parseInt(registroActualizar));
                    System.out.println("-".repeat(50));
                    break;
                case 4:
                    actual.cargarServicios();
                    String registroEliminar;

                    // Este bucle al igual que el anterior solicita el número de registro hasta que
                    // sea válido
                    while (true) {
                        System.out.print("Ingrese el número de registro a eliminar: ");
                        registroEliminar = scan.nextLine().trim();
                        boolean validacion = validarOpciones(registroEliminar);
                        if (!validacion) {
                            System.out.println("El valor ingresado es inválido");
                        } else {
                            break;
                        }
                    }

                    // Elimina el servicio que fue seleccionado
                    actual.eliminarServicio(Integer.parseInt(registroEliminar));
                    System.out.println("-".repeat(50));
                    break;
                case 5:
                    active = false;
                    System.out.println("Cerrando menú...");
                    break;
                default:
                    System.out.println("El valor ingresado no corresponde a una opción de menú");
            }
        }
    }

    // Hicimos otro método para que se carge y muestre la lista de los servicios
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

    // Y otro método para registrar nuevos servicios
    public void registrarServicio() {
        String nombreServicio;

        // Válida que se ingrese un nombre y que el campo no este vacío
        while (true) {
            System.out.print("Ingrese el nombre del servicio *: ");
            nombreServicio = scan.nextLine().trim();
            if (nombreServicio.equals("")) {
                System.out.println("El nombre del servicio no puede estar vacío");
            } else {
                break;
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
            boolean validacion = validarOpciones(input);
            if (validacion) {
                precioServicio = Double.parseDouble(input);
                if (precioServicio <= 0) {
                    System.out.println("El valor ingresado es inválido");
                } else {
                    break;
                }
            } else {
                System.out.println("El valor ingresado es inválido");
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

    // También creamos un método para actualizar un servicio que ya exista
    public void actualizarServicio(int registro) {
        List<String> servicio = service.cargarDatosServicio(registro);

        String nombreServicio;

        // Asegura que sí se ingrese un nuevo nombre
        while (true) {
            System.out.print("Ingrese el nuevo nombre del servicio: ");
            nombreServicio = scan.nextLine().trim();
            if (nombreServicio.equals("")) {
                nombreServicio = servicio.get(1);
                break;
            } else {
                break;
            }
        }

        String descripcionServicio;
        System.out.print("Ingrese la nueva descripción del servicio: ");
        descripcionServicio = scan.nextLine().trim();
        if (descripcionServicio.equals("")) {
            descripcionServicio = servicio.get(2);
        }

        double precioServicio;

        // Asegura que se ingrese el nuevo precio y que sea un número positivo
        while (true) {
            System.out.print("Ingrese el nuevo precio del servicio: ");
            String input = scan.nextLine().trim();
            if (input.equals("")) {
                precioServicio = Double.parseDouble(servicio.get(3));
                break;
            } else {
                boolean validacion = validarOpciones(input);
                if (validacion) {
                    precioServicio = Double.parseDouble(input);
                    if (precioServicio <= 0) {
                        System.out.println("El valor ingresado es inválido");
                    } else {
                        break;
                    }
                } else {
                    System.out.println("El valor ingresado es inválido");
                }
            }
        }
        service.actualizarServicio(registro, nombreServicio, descripcionServicio, precioServicio);
    }

    // Otro método, este permite eliminar un servicio
    public void eliminarServicio(int registro) {
        service.eliminarServicio(registro);
    }

    // Método estático para validar si se ingresa una opción numérica correcta
    public static boolean validarOpciones(String input) {
        boolean validarNumero = false;
        int conteoLetras = 0;
        for (int i = 0; i < input.length(); i++) {
            if (Character.isAlphabetic(input.charAt(i))) {
                conteoLetras++;
            }
        }

        if (conteoLetras == 0) {
            validarNumero = true;
        }

        // Si no se ingresada nada, se considera como invalido
        if (input.isEmpty()) {
            validarNumero = false;
        }

        return validarNumero;
    }
}

package src.Views;

import src.Controllers.ServicesController;

import java.util.List;
import java.util.Scanner;

public class Services {
    Scanner scan = new Scanner(System.in);
    public ServicesController service = new ServicesController();

    public void serviceMenu() {
        String separador = "-";

        boolean active = true;
        while (active){
            System.out.println("\uD83D\uDEC1 Qué haremos hoy?");
            System.out.println("1. Listar Servicios");
            System.out.println("2. Registrar Servicio");
            System.out.println("3. Actualizar Servicio");
            System.out.println("4. Eliminar Servicio");
            System.out.println("5. Volver al menú principal");
            System.out.println(separador.repeat(50));
            Services actual = new Services();

            String choice;
            while(true){
                System.out.print("Seleccione una opción: ");
                choice = scan.nextLine().trim();
                boolean validarMenu = validarOpciones(choice);
                if (!validarMenu) {
                    System.out.println("Opción inválida");
                }else{
                    break;
                }
            }

            switch (Integer.parseInt(choice)){
                case 1:
                    actual.cargarServicios();
                    break;
                case 2:
                    actual.registrarServicio();
                    break;
                case 3:
                    actual.cargarServicios();
                    String registroActualizar;
                    while(true){
                        System.out.print("Ingrese el número de registro a actualizar: ");
                        registroActualizar = scan.nextLine().trim();
                        boolean validacion = validarOpciones(registroActualizar);
                        if (!validacion) {
                            System.out.println("El valor ingresado es inválido");
                        }else{
                            break;
                        }
                    }
                    actual.actualizarServicio(Integer.parseInt(registroActualizar));
                    System.out.println("-".repeat(50));
                    break;
                case 4:
                    actual.cargarServicios();
                    String registroEliminar;
                    while(true){
                        System.out.print("Ingrese el número de registro a eliminar: ");
                        registroEliminar = scan.nextLine().trim();
                        boolean validacion = validarOpciones(registroEliminar);
                        if (!validacion) {
                            System.out.println("El valor ingresado es inválido");
                        }else{
                            break;
                        }
                    }
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

    public void cargarServicios() {
        service.cargarListaServicios();
        if (service.getListaServicios().isEmpty()) {
            System.out.println("No hay servicios registrados.");
        } else {
            String separador = "-".repeat(120);
            System.out.println(separador);
            System.out.printf("| %-5s | %-40s | %-80s | %-25s |\n", "No.", "Nombre", "Descripción", "Precio");
            System.out.println(separador);

            int i = 1;
            for (List<String> servicio : service.getListaServicios()) {
                String valorDescripcion = servicio.get(2);
                if(valorDescripcion == null){
                    valorDescripcion = "";
                }

                String valorPrecio = servicio.get(3);
                valorPrecio = "$ " + valorPrecio;
                System.out.printf("| %-5s | %-40s | %-80s | %-25s |\n", i, servicio.get(1), valorDescripcion, valorPrecio);

                i++;
            }
            System.out.println(separador);
        }
    }

    public void registrarServicio(){
        String nombreServicio;
        while(true){
            System.out.print("Ingrese el nombre del servicio *: ");
            nombreServicio = scan.nextLine().trim();
            if(nombreServicio.equals("")){
                System.out.println("El nombre del servicio no puede estar vacío");
            }else{
                break;
            }
        }
        service.setNombreServicio(nombreServicio);

        String descripcionServicio;
        System.out.print("Ingrese la descripción del servicio: ");
        descripcionServicio = scan.nextLine().trim();
        if(descripcionServicio.equals("")){
            descripcionServicio = null;
        }
        service.setDescripcionServicio(descripcionServicio);

        double precioServicio;
        while(true){
            System.out.print("Ingrese el precio del servicio *: ");
            String input = scan.nextLine().trim();
            boolean validacion = validarOpciones(input);
            if(validacion){
                precioServicio = Double.parseDouble(input);
                if(precioServicio <= 0){
                    System.out.println("El valor ingresado es inválido");
                }else{
                    break;
                }
            }else{
                System.out.println("El valor ingresado es inválido");
            }
        }
        service.setPrecioServicio(precioServicio);

        int resultado = service.registrarServicio();
        if (resultado == 1){
            System.out.println("Servicio registrado con éxito");
        }else{
            System.out.println("Ha ocurrido un error");
        }
    }

    public void actualizarServicio(int registro){
        List<String> servicio = service.cargarDatosServicio(registro);

        String nombreServicio;
        while(true){
            System.out.print("Ingrese el nuevo nombre del servicio: ");
            nombreServicio = scan.nextLine().trim();
            if(nombreServicio.equals("")){
                nombreServicio = servicio.get(1);
                break;
            }else{
                break;
            }
        }

        String descripcionServicio;
        System.out.print("Ingrese la nueva descripción del servicio: ");
        descripcionServicio = scan.nextLine().trim();
        if(descripcionServicio.equals("")){
            descripcionServicio = servicio.get(2);
        }

        double precioServicio;
        while(true){
            System.out.print("Ingrese el nuevo precio del servicio: ");
            String input = scan.nextLine().trim();
            if(input.equals("")){
                precioServicio = Double.parseDouble(servicio.get(3));
                break;
            }else{
                boolean validacion = validarOpciones(input);
                if(validacion){
                    precioServicio = Double.parseDouble(input);
                    if(precioServicio <= 0){
                        System.out.println("El valor ingresado es inválido");
                    }else{
                        break;
                    }
                }else{
                    System.out.println("El valor ingresado es inválido");
                }
            }
        }
        service.actualizarServicio(registro, nombreServicio, descripcionServicio, precioServicio);
    }

    public void eliminarServicio(int registro) {
        service.eliminarServicio(registro);
    }

    public static boolean validarOpciones(String input){
        boolean validarNumero = false;
        int conteoLetras = 0;
        for (int i = 0; i < input.length(); i++) {
            if(Character.isAlphabetic(input.charAt(i))) {
                conteoLetras++;
            }
        }

        if(conteoLetras == 0){
            validarNumero = true;
        }

        if(input.isEmpty()){
            validarNumero = false;
        }

        return validarNumero;
    }
}

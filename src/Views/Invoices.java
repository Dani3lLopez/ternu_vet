package src.Views;

import src.Controllers.InvoicesController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

/*
 * Esta vista gestiona la interaccion con el usuario para lsitar, crear y desactivar facturas
 * Muestra las opciones, captura entradas y llama a los meotodos correspondientes a la solicitud
 */
public class Invoices {

    // Scanner para recibir entradas y poderlas procesar
    Scanner scan = new Scanner(System.in);
    // Instancia del controlador para las operaciones de facturas
    public InvoicesController invoice = new InvoicesController();

    /*
     * Muestra el menu
     * Gestiona la solicitud hecha por el usauario. Con base a la opcion seleccionada
     * se llaman a los metodos correspondientes y si se necesitan parametros se solicitan
     */
    public void invoiceMenu() {
        Scanner scan = new Scanner(System.in);
        String separador = "-";

        boolean active = true;
        while (active){
            System.out.println("\uD83D\uDCCB Qué haremos hoy?");
            System.out.println("1. Listar Facturas");
            System.out.println("2. Crear Factura");
            System.out.println("3. Desactivar Factura");
            System.out.println("4. Volver al menú principal");
            System.out.println(separador.repeat(50));
            System.out.print("Seleccione una opción: ");
            int choice = scan.nextInt();

            switch (choice){
                case 1:
                    cargarFacturas();
                    System.out.println(separador.repeat(70));
                    break;
                case 2:
                    crearFactura();
                    break;
                case 3:
                    cargarFacturas();
                    System.out.print("Ingrese el número de registro a eliminar: ");
                    int registro = scan.nextInt();
                    scan.nextLine();
                    desactivarFactura(registro);
                    break;
                case 4:
                    active = false;
                    System.out.println("Cerrando menú...");
                    break;
                default:
                    System.out.println("El valor ingresado no corresponde a una opción de menú");
            }
        }
    }
    /*
     * Carga la lsita de facturas desde el controlador y las muestra en formato tabular
     */
    public void cargarFacturas(){
        invoice.llenarListas();
        List<List<String>> facturas = invoice.listaFacturas();
        if (invoice.listaFacturas().isEmpty()) {
            System.out.println("No hay facturas registrados.");
        }else {
            String separador = "-".repeat(120);
            System.out.println(separador);
            System.out.printf("| %-5s | %-20s | %-20s | %-20s | %-20s |\n", "No.", "No. Factura", "Facha - Emision", "Hora - Emision", "Propietario");
            System.out.println(separador);

            int n = 1;
            // Itera cada factura y muestra la información en formato
            for (List<String> factura : facturas) {
                String numeroFactura = factura.get(0);
                String fechaEmision = factura.get(1);
                String horaEmision = factura.get(2);
                String idPropietario = factura.get(3);
                String idPersonaPropietario = "";

                // En la lista de propietarios, busca el registro que coincide con el idPropietario
                for (List<String> propietario : invoice.listaPropietarios()) {
                    if (propietario.get(0).equalsIgnoreCase(idPropietario)) {
                        // el id del propietario está justamente en la posicion 1 del registro
                        idPersonaPropietario = propietario.get(1);
                        break;
                    }
                }

                // OBtiene el nombre completo del propietario
                String nombrePropietario = invoice.capturarNombres(idPersonaPropietario);
                System.out.printf("| %-5d | %-20s | %-20s | %-20s | %-20s |\n", n, numeroFactura, fechaEmision, horaEmision, nombrePropietario);
                n++;
            }
            System.out.println(separador);
        }
    }

    /*
     * Solicita al usuario los datos para crear la nueva factura
     * Llama al controlador para registrar la factura en la base de datos
     */
    public void crearFactura() {
        // Actualiza las listas para tener los datos mas recientes
        invoice.llenarListas();

        String separador = "-".repeat(70);
        System.out.println(separador);
        System.out.printf("| %-5s | %-50s |\n", "No.", "Propietario");
        System.out.println(separador);

        List<List<String>> propietarios = invoice.listaPropietarios();

        int r = 1;
        for (List<String> propietario : propietarios) {
            String idp = propietario.get(1);
            String nombrePropietario = invoice.capturarNombres(idp);

            System.out.printf("| %-5d | %-50s |\n", r, nombrePropietario);
            r++;
        }
        System.out.println(separador);
        // Solcita que se selecciona a un propietario
        System.out.print("Seleccione al propietario: ");
        int valor = scan.nextInt();
        scan.nextLine();

        if (valor > 0 && valor <= propietarios.size()) {
            // Asigna el id del propietario seleccionado
            String idPropietario = propietarios.get(valor - 1).get(0);
            invoice.setIdPropietario(idPropietario);

            // Establece la fecha y hora de emision de la factura (actuales a la hora de hacerlo)
            LocalDate fecha = LocalDate.now();
            LocalTime hora = LocalTime.now();
            invoice.setFechaEmisionFactura(fecha.toString());
            invoice.setHoraEmisionFactura(hora.toString());

            // Se establece la visibilidad como activa de la factura
            boolean visibilidad = true;
            invoice.setVisibilidadFactura(visibilidad);

            // Llama al controlador para crear la factura
            int resultado = invoice.crearFactura();
            if (resultado == 1) {
                System.out.println("Factura creada con éxito.");

            } else {
                System.out.println("Ha ocurrido un error al crear la factura.");
            }
        } else {
            System.out.println("Selección de persona inválida.");
        }
    }
    /*
     * Desactiva una factura por su indice en la lista
     */
    public void desactivarFactura(int registro) {
        invoice.desactivarFactura(registro);
    }
}

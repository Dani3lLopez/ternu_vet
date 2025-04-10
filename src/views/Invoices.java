package src.views;

import src.controllers.InvoicesController;
import src.validations.FormatException;
import src.validations.Validations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

/**
 * Vista: Invoices
 * Contiene los métodos necesarios para administrar las facturas
 */
public class Invoices {

    // Scanner para recibir entradas y poderlas procesar
    Scanner scan = new Scanner(System.in);
    // Instancia del controlador para las operaciones de facturas
    public InvoicesController invoice = new InvoicesController();

    /**
     * Menú de opciones para administrar los datos de las facturas
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
            String choice = scan.nextLine().trim();
            if(!choice.isEmpty()) {
                try{
                    Validations.validarRangoNumeros(choice, 1, 4);
                    switch (Integer.parseInt(choice)){
                        case 1:
                            cargarFacturas();
                            break;
                        case 2:
                            crearFactura();
                            System.out.println(separador.repeat(50));
                            break;
                        case 3:
                            cargarFacturas();
                            String registro = "";
                            try{
                                System.out.print("Ingrese el número de registro a eliminar: ");
                                registro = scan.nextLine().trim();
                                Validations.validarNumeros(registro);
                            }catch (FormatException e){
                                System.out.println(e.getMessage());
                            }
                            if(registro.isEmpty()){
                                break;
                            }else{
                                desactivarFactura(Integer.parseInt(registro));
                                System.out.println(separador.repeat(50));
                                break;
                            }
                        case 4:
                            active = false;
                            System.out.println("Cerrando menú...");
                            break;
                    }
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * Carga todas las facturas obtenidas de la base de datos
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
                        // el id del propietario está justamente en la posición 1 del registro
                        idPersonaPropietario = propietario.get(1);
                        break;
                    }
                }

                // Obtiene el nombre completo del propietario respectivo
                String nombrePropietario = invoice.capturarNombres(idPersonaPropietario);
                System.out.printf("| %-5d | %-20s | %-20s | %-20s | %-20s |\n", n, numeroFactura, fechaEmision, horaEmision, nombrePropietario);
                n++;
            }
            System.out.println(separador);
        }
    }

    /**
     * Crea un nuevo registro de factura
     */
    public void crearFactura() {
        // Actualiza las listas para tener los datos más recientes
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
        // Solicita que se selecciona a un propietario
        String valor = "";
        while(true){
            System.out.print("Seleccione al propietario *: ");
            valor = scan.nextLine().trim();
            try{
                Validations.validarCampoObligatorio(valor);
                Validations.validarRangoNumeros(valor, 1, propietarios.size());
                break;
            }catch (FormatException e){
                System.out.println(e.getMessage());
            }
        }
        String idPropietario = propietarios.get(Integer.parseInt(valor) - 1).get(0);
        invoice.setIdPropietario(idPropietario);

        // Establece la fecha y hora de emisión de la factura (actuales a la hora de hacerlo)
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
    }

    /**
     * Desactiva el registro de una factura
     * @param registro número de registro correspondiente a una factura
     */
    public void desactivarFactura(int registro) {
        invoice.desactivarFactura(registro);
    }
}

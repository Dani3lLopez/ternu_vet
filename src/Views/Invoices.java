package src.Views;

import src.Controllers.InvoicesController;

import java.util.List;
import java.util.Scanner;

public class Invoices {
    Scanner scan = new Scanner(System.in);
    public InvoicesController invoice = new InvoicesController();

    public void invoiceMenu() {
        Scanner scan = new Scanner(System.in);
        String separador = "-";

        boolean active = true;
        while (active){
            System.out.println("\uD83D\uDC36 Qué haremos hoy?");
            System.out.println("1. Listar Facturas");
            System.out.println("2. Registrar Factura");
            System.out.println("3. Actualizar Factura");
            System.out.println("4. Desactivar Factura");
            System.out.println("5. Volver al menú principal");
            System.out.println(separador.repeat(50));
            System.out.print("Seleccione una opción: ");
            int choice = scan.nextInt();
            Invoices actual = new Invoices();

            switch (choice){
                case 1:
                    actual.cargarFacturas();
                    System.out.println(separador.repeat(70));
                    break;
                case 2:
                    //actual.registrarPropietario();
                    break;
                case 3:
                    actual.cargarFacturas();
                    System.out.print("Ingrese el número de registro a actualizar: ");
                    int r = scan.nextInt();
                    scan.nextLine();
                    //actual.actualizarPropietario(r);
                    break;
                case 4:
                    actual.cargarFacturas();
                    System.out.print("Ingrese el número de registro a eliminar: ");
                    int registro = scan.nextInt();
                    scan.nextLine();
                    //actual.desactivarPropietario(registro);
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
            for (List<String> factura : facturas) {
                String numeroFactura = factura.get(0);
                String fechaEmision = factura.get(1);
                String horaEmision = factura.get(2);
                String idPropietario = factura.get(3);
                String idPersonaPropietario = "";

                for (List<String> propietario : invoice.listaPropietarios()) {
                    if (propietario.get(0).equalsIgnoreCase(idPropietario)) {
                        idPersonaPropietario = propietario.get(1);
                        break;
                    }
                }

                String nombrePropietario = invoice.capturarNombres(idPersonaPropietario);
                System.out.printf("| %-5d | %-20s | %-20s | %-20s | %-20s |\n", n, numeroFactura, fechaEmision, horaEmision, nombrePropietario);
                n++;
            }
            System.out.println(separador);
        }
    }
}

package src.Views;

import src.Controllers.InvoicesDetailsController;

import java.util.List;
import java.util.Scanner;

public class InvoicesDetails {
    Scanner scan = new Scanner(System.in);
    public InvoicesDetailsController invoiceDetail = new InvoicesDetailsController();

    public void invoiceDetailMenu() {
        Scanner scan = new Scanner(System.in);
        String separador = "-";

        boolean active = true;
        while (active) {
            System.out.println("\uD83D\uDC36 Qué haremos hoy?");
            System.out.println("1. Listar Detalles");
            System.out.println("2. Registrar Detalle");
            System.out.println("3. Actualizar Detalle");
            System.out.println("4. Eliminar Detalle");
            System.out.println("5. Volver al menú principal");
            System.out.println(separador.repeat(50));
            System.out.print("Seleccione una opción: ");
            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    cargarDetallesFacturas();
                    System.out.println(separador.repeat(70));
                    break;
                case 2:
                    registrarDetalle();
                    break;
                case 3:
                    cargarDetallesFacturas();
                    System.out.print("Ingrese el número de registro a actualizar: ");
                    int r = scan.nextInt();
                    scan.nextLine();
                    //actualizarDetalle(r);
                    break;
                case 4:
                    cargarDetallesFacturas();
                    System.out.print("Ingrese el número de registro a eliminar: ");
                    int registro = scan.nextInt();
                    scan.nextLine();
                    eliminarDetalleFactura(registro);
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

    public void cargarDetallesFacturas() {
        invoiceDetail.llenarListas();
        List<List<String>> detalles = invoiceDetail.listaDetallesFacturas();
        if (invoiceDetail.listaDetallesFacturas().isEmpty()) {
            System.out.println("No hay detalles registrados.");
        } else {
            String separador = "-".repeat(100);
            System.out.println(separador);
            System.out.printf("| %-5s | %-20s | %-20s | %-20s | %-20s |\n", "No.", "# Factura", "Item", "Cantidad", "Precio/u");
            System.out.println(separador);

            int n = 1;
            for (List<String> detalle : detalles) {
                String numero = detalle.get(1);
                String cantidad = detalle.get(3);
                String precio = detalle.get(4);
                String idProducto = detalle.get(2);
                for (List<String> nombreProducto : invoiceDetail.listaNombresItems()) {
                    if (nombreProducto.get(0).equalsIgnoreCase(idProducto)) {
                        idProducto = nombreProducto.get(1);
                        break;
                    }
                }
                String nombreItem = invoiceDetail.capturarNombreItem(idProducto);

                System.out.printf("| %-5d | %-20s | %-20s | %-20s | %-20s |\n", n, numero, nombreItem, cantidad, precio);
                n++;
            }
            System.out.println(separador);
        }
    }
    public void registrarDetalle() {
        invoiceDetail.llenarListas();

        String separador = "-".repeat(70);
        System.out.println(separador);
        System.out.printf("| %-5s | %-50s |\n", "No.", "Producto");
        System.out.println(separador);

        List<List<String>> productos = invoiceDetail.listaNombresItems();

        int r = 1;
        for (List<String> producto : productos) {
            String idp = producto.get(1);
            String nombreProducto = invoiceDetail.capturarNombreItem(idp);

            System.out.printf("| %-5d | %-50s |\n", r, nombreProducto);
            r++;
        }
        System.out.println(separador);

        System.out.print("Seleccione el producto: ");
        int valor = scan.nextInt();
        scan.nextLine();

        if (valor > 0 && valor <= invoiceDetail.listaNombresItems().size()) {
            String id = invoiceDetail.capturarIdListaDetallesItems(valor);
            invoiceDetail.setIdDetalleItem(id);

            System.out.println("Cantidad: ");
            String cantidadProducto = scan.nextLine();
            invoiceDetail.setCantidadItem(cantidadProducto);

            System.out.println("Precio unitario: ");
            String precioUnitario = scan.nextLine();
            invoiceDetail.setPrecioUnitarioItem(precioUnitario);

            int resultado = invoiceDetail.registrarItemFactura();

            if (resultado == 1) {
                    System.out.println("detalle registrado con éxito.");
            } else {
                    System.out.println("Ha ocurrido un error al registrar el detalle.");
            }
        } else {
                System.out.println("Selección de item inválida.");
        }
    }

    public void eliminarDetalleFactura(int registro) {
        invoiceDetail.eliminarDetalleFactura(registro);
    }
}

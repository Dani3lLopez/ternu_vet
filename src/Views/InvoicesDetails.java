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
                    registrarDetalleFactura();
                    break;
                case 3:
                    cargarDetallesFacturas();
                    System.out.print("Ingrese el número de registro a actualizar: ");
                    int r = scan.nextInt();
                    scan.nextLine();
                    actualizarDetalle(r);
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
            System.out.printf("| %-5s | %-20s | %-40s | %-20s | %-20s |\n", "No.", "# Factura", "Item", "Cantidad", "Precio/u");
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

                System.out.printf("| %-5d | %-20s | %-40s | %-20s | %-20s |\n", n, numero, nombreItem, cantidad, precio);
                n++;
            }
            System.out.println(separador);
        }
    }

    public void registrarDetalleFactura() {
        invoiceDetail.llenarListas();

        String separador = "-".repeat(70);
        System.out.println(separador);
        System.out.printf("| %-5s | %-50s |\n", "No.", "Factura");
        System.out.println(separador);

        List<List<String>> facturas = invoiceDetail.listaFacturas();

        int r = 1;
        for (List<String> factura : facturas) {
            String idp = factura.get(0);

            System.out.printf("| %-5d | %-50s |\n", r, idp);
            r++;
        }
        System.out.println(separador);

        System.out.print("Seleccione la factura: ");
        int valor = scan.nextInt();
        scan.nextLine();

        if (valor > 0 && valor <= invoiceDetail.listaFacturas().size()) {
            String id = invoiceDetail.capturarIdListaFacturas(valor);
            invoiceDetail.setNumeroFactura(id);

            System.out.println(separador);
            System.out.printf("| %-5s | %-50s |\n", "No.", "Producto");
            System.out.println(separador);

            for (int c = 0; c < invoiceDetail.listaNombresItems().size(); c++) {
                List<String> item = invoiceDetail.listaNombresItems().get(c);
                System.out.printf("| %-5d | %-50s |\n", (c + 1), item.get(1));
            }
            System.out.println(separador);

            System.out.print("Seleccione el producto: ");
            int v = scan.nextInt();
            scan.nextLine();

            if (v > 0 && v <= invoiceDetail.listaNombresItems().size()) {
                String n = invoiceDetail.capturarIdListaDetallesItems(v);
                invoiceDetail.setIdDetalleItem(n);

                System.out.println("Cantidad: ");
                String cantidad = scan.nextLine();
                invoiceDetail.setCantidadItem(cantidad);

                System.out.println("Precio unitario: ");
                String precio = scan.nextLine();
                invoiceDetail.setPrecioUnitarioItem(precio);

                int resultado = invoiceDetail.registrarItemFactura();

                if (resultado == 1) {
                    System.out.println("detalle registrado con éxito.");
                } else {
                    System.out.println("Ha ocurrido un error al registrar el detalle.");
                }
            } else {
                System.out.println("Selección de persona inválida.");
            }
        }
    }

    public void actualizarDetalle(int r) {
        String idDetalle = invoiceDetail.capturarIdLista(r);
        if (idDetalle == null) {
            System.out.println("Registro extraño");
            return;
        }

        List<String> detalle = invoiceDetail.cargarDatosDetalle(r);
        if (detalle.isEmpty()) {
            System.out.println("No se encontró el registro especificado");
            return;
        }

        String separador = "-".repeat(70);
        System.out.println(separador);
        System.out.printf("| %-5s | %-50s |\n", "No.", "Factura");
        System.out.println(separador);

        List<List<String>> facturas = invoiceDetail.listaFacturas();
        for (int i = 0; i < facturas.size(); i++) {
            List<String> factura = facturas.get(i);
            String numeroFactura = invoiceDetail.capturarFactura(factura.get(0));
            System.out.printf("| %-5d | %-50s |\n", (i + 1), numeroFactura);
        }
        System.out.println(separador);

        System.out.print("Nueva factura: ");
        String np = scan.nextLine();
        String nuevoIdFactura = detalle.get(0);

        if (!np.isEmpty()) {
            int idNuevo = Integer.parseInt(np);
            if (idNuevo > 0 && idNuevo <= facturas.size()) {
                nuevoIdFactura = facturas.get(idNuevo - 1).get(0);
            } else {
                System.out.println("Factura no válida.");
            }
        }

        System.out.println(separador);
        System.out.printf("| %-5s | %-50s |\n", "No.", "Producto");
        System.out.println(separador);

        List<List<String>> productos = invoiceDetail.listaNombresItems();
        for (int i = 0; i < productos.size(); i++) {
            System.out.printf("| %-5d | %-50s |\n", (i + 1), productos.get(i).get(1));
        }
        System.out.println(separador);

        System.out.print("Nuevo producto: ");
        String nm = scan.nextLine();
        String nuevoIdProducto = detalle.get(2);

        if (!nm.isEmpty()) {
            int idNuevoProducto = Integer.parseInt(nm);
            if (idNuevoProducto > 0 && idNuevoProducto <= productos.size()) {
                nuevoIdProducto = productos.get(idNuevoProducto - 1).get(0);
            } else {
                System.out.println("Producto no válida.");
            }
        }

        System.out.print("Cantidad: ");
        String nuevaCantidad = scan.nextLine();
        if (nuevaCantidad.isEmpty()) nuevaCantidad = detalle.get(3);

        System.out.print("Precio: ");
        String nuevoPrecio = scan.nextLine();
        if (nuevoPrecio.isEmpty()) nuevoPrecio = detalle.get(4);

        invoiceDetail.actualizarDetalle(r, nuevoIdFactura, nuevoIdProducto, nuevaCantidad, nuevoPrecio);
    }

    public void eliminarDetalleFactura(int registro) {
        invoiceDetail.eliminarDetalleFactura(registro);
    }
}

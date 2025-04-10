package src.views;

import src.controllers.InvoicesDetailsController;
import src.validations.FormatException;
import src.validations.Validations;

import java.util.List;
import java.util.Scanner;

/**
 * Vista: InvoicesDetails
 * Contiene los métodos necesarios para administrar las facturas
 */
public class InvoicesDetails {
    Scanner scan = new Scanner(System.in);
    public InvoicesDetailsController invoiceDetail = new InvoicesDetailsController();

    /**
     * Menú con opciones para administrar datos
     */
    public void invoiceDetailMenu() {
        Scanner scan = new Scanner(System.in);
        String separador = "-";

        boolean active = true;
        while (active) {
            System.out.println("\uD83E\uDDFE Qué haremos hoy?");
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
                            cargarDetallesFacturas();
                            break;
                        case 2:
                            registrarDetalleFactura();
                            System.out.println(separador.repeat(50));
                            break;
                        case 3:
                            cargarDetallesFacturas();
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
                            cargarDetallesFacturas();
                            String registro = "";
                            while (true){
                                System.out.print("Ingrese el número de registro a eliminar: ");
                                registro = scan.nextLine().trim();
                                try{
                                    Validations.validarNumeros(registro);
                                    break;
                                }catch(FormatException e){
                                    System.out.println(e.getMessage());
                                }
                            }
                            if(registro.isEmpty()){
                                break;
                            }else{
                                eliminarDetalleFactura(Integer.parseInt(registro));
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
     * Carga los detalles de la factura
     */
    /**
     * Carga los detalles de la factura
     */
    public void cargarDetallesFacturas() {
        invoiceDetail.llenarListas();
        List<List<String>> detalles = invoiceDetail.listaDetallesFacturas();
        if (invoiceDetail.listaDetallesFacturas().isEmpty()) {
            System.out.println("No hay detalles registrados.");
        } else {
            String separador = "-".repeat(100);
            System.out.println(separador);
            System.out.printf("| %-5s | %-10s | %-50s | %-25s | %-20s |\n", "No.", "# Factura", "Item", "Cantidad", "Precio/u");
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

                // Llamada al controlador para obtener el nombre del item
                String nombreItem = invoiceDetail.obtenerNombreItem(idProducto);

                System.out.printf("| %-5d | %-10s | %-50s | %-25s | %-20s |\n", n, numero, nombreItem, cantidad, precio);
                n++;
            }
            System.out.println(separador);
        }
    }

    /**
     * Registra un nuevo detalle de factura
     */
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

        String valor = "";
        while(true){
            System.out.print("Seleccione la factura *: ");
            valor = scan.nextLine().trim();
            try{
                Validations.validarCampoObligatorio(valor);
                Validations.validarRangoNumeros(valor, 1, facturas.size());
                break;
            }catch(FormatException e){
                System.out.println(e.getMessage());
            }
        }

        String id = invoiceDetail.capturarIdListaFacturas(Integer.parseInt(valor));
        invoiceDetail.setNumeroFactura(id);

        System.out.println(separador);
        System.out.printf("| %-5s | %-50s |\n", "No.", "Producto");
        System.out.println(separador);

        for (int c = 0; c < invoiceDetail.listaNombresItems().size(); c++) {
            List<String> item = invoiceDetail.listaNombresItems().get(c);
            System.out.printf("| %-5d | %-50s |\n", (c + 1), item.get(1));
        }
        System.out.println(separador);

        String v = "";
        while(true){
            System.out.print("Seleccione el producto *: ");
            v = scan.nextLine().trim();
            try{
                Validations.validarCampoObligatorio(v);
                Validations.validarRangoNumeros(v, 1, invoiceDetail.listaNombresItems().size());
                break;
            }catch(FormatException e){
                System.out.println(e.getMessage());
            }
        }

        String n = invoiceDetail.capturarIdListaDetallesItems(Integer.parseInt(v));
        invoiceDetail.setIdDetalleItem(n);

        String cantidad = "";
        while(true){
            System.out.print("Cantidad *: ");
            cantidad = scan.nextLine().trim();
            try{
                Validations.validarCampoObligatorio(cantidad);
                Validations.validarRangoNumeros(cantidad, 1, 100);
                break;
            }catch(FormatException e){
                System.out.println(e.getMessage());
            }
        }
        invoiceDetail.setCantidadItem(cantidad);

        String precio = "";
        while(true){
            System.out.print("Precio unitario *: ");
            precio = scan.nextLine().trim();
            try{
                Validations.validarCampoObligatorio(precio);
                Validations.validarDecimales(precio);
                break;
            }catch(FormatException e){
                System.out.println(e.getMessage());
            }
        }
        invoiceDetail.setPrecioUnitarioItem(precio);

        int resultado = invoiceDetail.registrarItemFactura();

        if (resultado == 1) {
            System.out.println("Detalle registrado con éxito.");
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

        String np = "";
        String nuevoIdFactura = "";
        while(true){
            System.out.print("Nueva factura: ");
            np = scan.nextLine().trim();
            if(np.isEmpty()){
                nuevoIdFactura = detalle.get(1);
                break;
            }else{
                try{
                    Validations.validarRangoNumeros(np, 1, facturas.size());
                    nuevoIdFactura = facturas.get(Integer.parseInt(np) - 1).get(0);
                    break;
                }catch(FormatException e){
                    System.out.println(e.getMessage());
                }
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

        String nm = "";
        String nuevoIdProducto = "";
        while(true){
            System.out.print("Nuevo producto: ");
            nm = scan.nextLine().trim();
            if(nm.isEmpty()){
                nuevoIdProducto = detalle.get(2);
                break;
            }else{
                try{
                    Validations.validarRangoNumeros(nm, 1, productos.size());
                    nuevoIdProducto = productos.get(Integer.parseInt(nm) - 1).get(0);
                    break;
                }catch(FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        String nuevaCantidad = "";
        while(true){
            System.out.print("Cantidad: ");
            nuevaCantidad = scan.nextLine().trim();
            if(nuevaCantidad.isEmpty()){
                nuevaCantidad = detalle.get(3);
                break;
            }else{
                try{
                    Validations.validarRangoNumeros(nuevaCantidad, 1, 100);
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        String nuevoPrecio = "";
        while(true){
            System.out.print("Precio unitario: ");
            nuevoPrecio = scan.nextLine().trim();
            if(nuevoPrecio.isEmpty()){
                nuevoPrecio = detalle.get(4);
                break;
            }else{
                try{
                    Validations.validarDecimales(nuevoPrecio);
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        // Actualizar el detalle
        invoiceDetail.actualizarDetalle(r, nuevoIdFactura, nuevoIdProducto, nuevaCantidad, nuevoPrecio);
    }

    /**
     * Elimina un detalle de la factura
     * @param registro número de registro
     */
    public void eliminarDetalleFactura(int registro) {
        invoiceDetail.eliminarDetalleFactura(registro);
    }
}

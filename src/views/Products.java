package src.views;

import src.controllers.ProductsController;
import src.validations.FormatException;
import src.validations.Validations;

import java.util.List;
import java.util.Scanner;

/**
 * Vista: Products
 * Contiene los métodos necesarios para administrar los productos
 */
public class Products {
    Scanner scan = new Scanner(System.in);
    public ProductsController product = new ProductsController();

    /**
     * Menú de opciones para administrar los datos
     */
    public void productMenu() {
        String separador = "-";

        // Este es el bucle principal, con todas las opciones del menú
        boolean active = true;
        while (active) {
            System.out.println("\uD83E\uDD4E Qué haremos hoy?");
            System.out.println("1. Listar Productos");
            System.out.println("2. Registrar Producto");
            System.out.println("3. Actualizar Producto");
            System.out.println("4. Eliminar Producto");
            System.out.println("5. Volver al menú principal");
            System.out.println(separador.repeat(50));
            Products actual = new Products();

            // Válida que se seleccione una opción aceptable
            String choice;
            System.out.print("Seleccione una opción: ");
            choice = scan.nextLine().trim();
            if(!choice.isEmpty()){
                try{
                    Validations.validarRangoNumeros(choice, 1, 5);
                    switch (Integer.parseInt(choice)) {
                        case 1:
                            cargarProductos();
                            break;
                        case 2:
                            actual.registrarProducto();
                            System.out.println("-".repeat(50));
                            break;
                        case 3:
                            actual.cargarProductos();
                            String registroActualizar;

                            // Pide que se ingrese el número de registro, hasta que sea uno válido
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
                                // Actualiza el producto seleccionado
                                actual.actualizarProducto(Integer.parseInt(registroActualizar));
                                System.out.println("-".repeat(50));
                                break;
                            }
                        case 4:
                            actual.cargarProductos();
                            String registroEliminar;

                            // Solicita un número de registro válido
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
                                // Elimina el producto que se selecciono
                                actual.eliminarProducto(Integer.parseInt(registroEliminar));
                                System.out.println("-".repeat(50));
                                break;
                            }
                        case 5:
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
     * Carga todos los productos registrados en la base de datos
     */
    public void cargarProductos() {
        product.cargarListaProductos();
        if (product.getListaProductos().isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            String separador = "-".repeat(120);
            System.out.println(separador);
            System.out.printf("| %-5s | %-20s | %-35s | %-15s | %-15s | %-15s |\n", "No.", "Nombre", "Descripción",
                    "Precio", "Estado", "Peso");
            System.out.println(separador);

            int i = 1;
            for (List<String> producto : product.getListaProductos()) {
                String valorDescripcion = "";
                if (producto.get(2) == null) {
                    valorDescripcion = "";
                }else{
                    valorDescripcion = producto.get(2);
                }

                // Formatea el precio y peso obtenido del producto
                String valorPrecio = "$ " + producto.get(3);
                String valorPeso = producto.get(5) + " " + producto.get(6);
                System.out.printf("| %-5s | %-20s | %-35s | %-15s | %-15s | %-15s |\n", i, producto.get(1),
                        valorDescripcion, valorPrecio, producto.get(7), valorPeso);
                i++;
            }
            System.out.println(separador);
        }
    }

    /**
     * Registra nuevos productos en la base de datos
     */
    public void registrarProducto() {
        String nombreProducto;

        // Válida que se ingrese el nombre del producto, hasta que el espacio no este
        // vacío
        while (true) {
            System.out.print("Nombre del producto *: ");
            nombreProducto = scan.nextLine().trim();
            try{
                Validations.validarCampoObligatorio(nombreProducto);
                break;
            }catch (FormatException e){
                System.out.println(e.getMessage());
            }
        }
        product.setNombreProducto(nombreProducto); // Establece el nombre del producto

        String descripcionProducto;
        System.out.print("Ingrese la descripcion del producto: ");
        descripcionProducto = scan.nextLine().trim();
        if (descripcionProducto.equals("")) {
            descripcionProducto = null;
        }
        product.setDescripcionProducto(descripcionProducto);

        double precioProducto;

        // Asegura que se ingrese el precio y que sea un número positivo
        while (true) {
            System.out.print("Ingrese el precio del producto *: ");
            String input = scan.nextLine().trim();
            try{
                Validations.validarCampoObligatorio(input);
                Validations.validarDecimales(input);
                precioProducto = Double.parseDouble(input);
                break;
            }catch (FormatException e){
                System.out.println(e.getMessage());
            }
        }
        product.setPrecioProducto(precioProducto);

        int stockProducto;

        // Asegura que se ingrese cuaánto hay de producto y si se deja vacío lo
        // establece como 0
        while (true) {
            System.out.print("Ingrese el stock del producto: ");
            String input = scan.nextLine().trim();
            if (input.equals("")) {
                stockProducto = 0;
                break;
            } else {
                try{
                    Validations.validarNumeros(input);
                    stockProducto = Integer.parseInt(input);
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        product.setStockProducto(stockProducto);

        double pesoProducto;

        // Al igual que el precio, verifica que no este vacío y que sea un número
        // positivo
        while (true) {
            System.out.print("Ingrese el peso del producto: ");
            String input = scan.nextLine().trim();
            if (input.equals("")) {
                pesoProducto = 0;
                break;
            } else {
                try{
                    Validations.validarDecimales(input);
                    pesoProducto = Double.parseDouble(input);
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        product.setPesoProducto(pesoProducto);

        // Muestra un menú de todas las unidades de medida posibles para el producto
        String unidadMedidaProducto = "";
        System.out.println("Ingrese la unidad de medida del producto: ");
        System.out.println("1. kg");
        System.out.println("2. g");
        System.out.println("3. lb");
        System.out.println("4. ml");
        System.out.println("5. L");

        // Asegura que se seleecione una opción válida
        while (true) {
            System.out.print("Opción seleccionada: ");
            String input = scan.nextLine().trim();
            if (input.equals("")) {
                unidadMedidaProducto = "ml";
                break;
            } else {
                try{
                    Validations.validarRangoNumeros(input, 1, 5);
                    int opcionUniProducto = Integer.parseInt(input);
                    switch (opcionUniProducto) {
                        case 1:
                            unidadMedidaProducto = "kg";
                            break;
                        case 2:
                            unidadMedidaProducto = "g";
                            break;
                        case 3:
                            unidadMedidaProducto = "lb";
                            break;
                        case 4:
                            unidadMedidaProducto = "ml";
                            break;
                        case 5:
                            unidadMedidaProducto = "L";
                            break;
                    }
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        product.setUnidadMedidaProducto(unidadMedidaProducto);

        // Registra el nuevo producto
        int resultado = product.registrarProducto();
        if (resultado == 1) {
            System.out.println("Producto registrado con éxito");
        } else {
            System.out.println("Ha ocurrido un error");
        }
    }

    /**
     * Actualiza un producto existente en la base de datos
     * @param registro número de registro
     * Es posible dejar campos en blanco para mantener su valor actual
     */
    public void actualizarProducto(int registro) {
        List<String> producto = product.cargarDatosProducto(registro);

        if (producto.isEmpty()) {
            System.out.println("No se encontró el registro especificado");
            return;
        }

        String nombreProducto;

        // Valida que se ingrese un nombre, de lo contrario mantiene el que ya estaba
        while (true) {
            System.out.print("Nuevo nombre del producto: ");
            nombreProducto = scan.nextLine().trim();
            if (nombreProducto.equals("")) {
                nombreProducto = producto.get(1);
                break;
            } else {
                break;
            }
        }

        String descripcionProducto;
        System.out.print("Nueva descripción del producto: ");
        descripcionProducto = scan.nextLine().trim();
        if (descripcionProducto.equals("")) {
            descripcionProducto = producto.get(2);
        }

        double precioProducto;

        // Aegura que se ingrese un precio y que sean números positivos
        while (true) {
            System.out.print("Ingrese el nuevo precio del producto: ");
            String input = scan.nextLine().trim();
            if (input.equals("")) {
                precioProducto = Double.parseDouble(producto.get(3));
                break;
            } else {
                try{
                    Validations.validarDecimales(input);
                    precioProducto = Double.parseDouble(input);
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        int stockProducto;

        // Verifica que se ingrese cuánto producto hay, de no ingresarse mantiene el que
        // ya tenía
        while (true) {
            System.out.print("Ingrese el stock del producto: ");
            String input = scan.nextLine().trim();
            if (input.equals("")) {
                stockProducto = Integer.parseInt(producto.get(4));
                break;
            } else {
                try{
                    Validations.validarNumeros(input);
                    stockProducto = Integer.parseInt(input);
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        double pesoProducto;
        // Como los otros, verifica que se ingrese un precio y si no lo hace, mantiene
        // la anterior
        while (true) {
            System.out.print("Ingrese el peso del producto: ");
            String input = scan.nextLine().trim();
            if (input.equals("")) {
                pesoProducto = Double.parseDouble(producto.get(5));
                break;
            } else {
                try{
                    Validations.validarDecimales(input);
                    pesoProducto = Double.parseDouble(input);
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        // Muestra todas las opciones posibles para actualizar la unidad de medida del
        // producto
        String unidadMedidaProducto = "";
        System.out.println("Ingrese la unidad medida del producto: ");
        System.out.println("1. kg");
        System.out.println("2. g");
        System.out.println("3. lb");
        System.out.println("4. ml");
        System.out.println("5. L");

        // Comprueba que seseleccione una opcion válida de medida
        while (true) {
            System.out.print("Opción seleccionada: ");
            String input = scan.nextLine().trim();
            if (input.equals("")) {
                unidadMedidaProducto = producto.get(6);
                break;
            } else {
                try{
                    Validations.validarRangoNumeros(input, 1, 5);
                    int opcionUniProducto = Integer.parseInt(input);
                    switch (opcionUniProducto) {
                        case 1:
                            unidadMedidaProducto = "kg";
                            break;
                        case 2:
                            unidadMedidaProducto = "g";
                            break;
                        case 3:
                            unidadMedidaProducto = "lb";
                            break;
                        case 4:
                            unidadMedidaProducto = "ml";
                            break;
                        case 5:
                            unidadMedidaProducto = "L";
                            break;
                    }
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        product.actualizarProducto(registro, nombreProducto, descripcionProducto, precioProducto, stockProducto,
                pesoProducto, unidadMedidaProducto);
    }

    /**
     * Elimina un producto de la base de datos
     * @param registro número de registro
     */
    public void eliminarProducto(int registro) {
        product.eliminarProducto(registro);
    }
}

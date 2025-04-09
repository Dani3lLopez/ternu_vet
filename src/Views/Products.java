package src.Views;

import src.Controllers.ProductsController;

import java.util.List;
import java.util.Scanner;

public class Products {
    Scanner scan = new Scanner(System.in);
    public ProductsController product = new ProductsController();

    public void productMenu() {
        String separador = "-";

        boolean active = true;
        while (active){
            System.out.println("\uD83E\uDD4E Qué haremos hoy?");
            System.out.println("1. Listar Productos");
            System.out.println("2. Registrar Producto");
            System.out.println("3. Actualizar Producto");
            System.out.println("4. Eliminar Producto");
            System.out.println("5. Volver al menú principal");
            System.out.println(separador.repeat(50));
            Products actual = new Products();

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
                    actual.cargarProductos();
                    break;
                case 2:
                    actual.registrarProducto();
                    break;
                case 3:
                    actual.cargarProductos();
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
                    actual.actualizarProducto(Integer.parseInt(registroActualizar));
                    System.out.println("-".repeat(50));
                    break;
                case 4:
                    actual.cargarProductos();
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
                    actual.eliminarProducto(Integer.parseInt(registroEliminar));
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

    public void cargarProductos() {
        product.cargarListaProductos();
        if (product.getListaProductos().isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            String separador = "-".repeat(120);
            System.out.println(separador);
            System.out.printf("| %-5s | %-20s | %-35s | %-15s | %-15s | %-15s |\n", "No.", "Nombre", "Descripción", "Precio", "Estado", "Peso");
            System.out.println(separador);

            int i = 1;
            for (List<String> producto : product.getListaProductos()) {
                String valorDescripcion = "";
                if(producto.get(2) == null){
                    valorDescripcion = "";
                }
                String valorPrecio = "$ " + producto.get(3);
                String valorPeso = producto.get(5) + " " + producto.get(6);
                System.out.printf("| %-5s | %-20s | %-35s | %-15s | %-15s | %-15s |\n", i, producto.get(1), valorDescripcion, valorPrecio, producto.get(7), valorPeso);
                i++;
            }
            System.out.println(separador);
        }
    }

    public void registrarProducto() {
        String nombreProducto;
        while (true) {
            System.out.print("Nombre del producto *: ");
            nombreProducto = scan.nextLine().trim();
            if (nombreProducto.equals("")) {
                System.out.println("El nombre del producto no puede estar vacío");
            }else{
                break;
            }
        }
        product.setNombreProducto(nombreProducto);

        String descripcionProducto;
        System.out.print("Ingrese la descripcion del producto: ");
        descripcionProducto = scan.nextLine().trim();
        if (descripcionProducto.equals("")) {
            descripcionProducto = null;
        }
        product.setDescripcionProducto(descripcionProducto);

        double precioProducto;
        while (true){
            System.out.print("Ingrese el precio del producto *: ");
            String input = scan.nextLine().trim();
            if (input.equals("")) {
                System.out.println("El precio del producto no puede estar vacío");
            }else{
                boolean validacion = validarOpciones(input);
                if(validacion){
                    precioProducto = Double.parseDouble(input);
                    if(precioProducto <= 0){
                        System.out.println("El valor ingresado es inválido");
                    }else{
                        break;
                    }
                }else{
                    System.out.println("El valor ingresado es inválido");
                }
            }
        }
        product.setPrecioProducto(precioProducto);

        int stockProducto;
        while (true){
            System.out.print("Ingrese el stock del producto: ");
            String input = scan.nextLine().trim();
            if (input.equals("")) {
                stockProducto = 0;
                break;
            }else{
                boolean validacion = validarOpciones(input);
                if(validacion){
                    stockProducto = Integer.parseInt(input);
                    break;
                }else{
                    System.out.println("El valor ingresado es inválido");
                }
            }
        }
        product.setStockProducto(stockProducto);

        double pesoProducto;
        while (true){
            System.out.print("Ingrese el peso del producto: ");
            String input = scan.nextLine().trim();
            if (input.equals("")) {
                pesoProducto = 0;
                break;
            }else{
                boolean validacion = validarOpciones(input);
                if(validacion){
                    pesoProducto = Double.parseDouble(input);
                    break;
                }else{
                    System.out.println("El valor ingresado es inválidoj");
                }
            }
        }
        product.setPesoProducto(pesoProducto);

        String unidadMedidaProducto;
        System.out.println("Ingrese la unidad medida del producto: ");
        System.out.println("1. kg");
        System.out.println("2. g");
        System.out.println("3. lb");
        System.out.println("4. ml");
        System.out.println("5. L");
        while (true){
            System.out.print("Opción seleccionada: ");
            String input = scan.nextLine().trim();
            if (input.equals("")) {
                unidadMedidaProducto = null;
                break;
            } else {
                boolean validacion = validarOpciones(input);
                if(validacion) {
                    int opcionUniProducto = Integer.parseInt(input);
                    if (opcionUniProducto == 1) {
                        unidadMedidaProducto = "kg";
                        break;
                    } else if (opcionUniProducto == 2) {
                        unidadMedidaProducto = "g";
                        break;
                    } else if(opcionUniProducto == 3) {
                        unidadMedidaProducto = "lb";
                        break;
                    } else if(opcionUniProducto == 4) {
                        unidadMedidaProducto = "ml";
                        break;
                    }else if(opcionUniProducto == 5) {
                        unidadMedidaProducto = "L";
                        break;
                    }else{
                        System.out.println("Opción no valida");
                    }
                }else{
                    System.out.println("El valor ingresado es inválido");
                }
            }
        }
        product.setUnidadMedidaProducto(unidadMedidaProducto);

        int resultado = product.registrarProducto();
        if (resultado == 1){
            System.out.println("Producto registrado con éxito");
        }else{
            System.out.println("Ha ocurrido un error");
        }
    }

    public void actualizarProducto(int registro){
        List<String> producto = product.cargarDatosProducto(registro);

        String nombreProducto;
        while (true) {
            System.out.print("Nombre del producto *: ");
            nombreProducto = scan.nextLine().trim();
            if (nombreProducto.equals("")) {
                nombreProducto = producto.get(1);
                break;
            }else{
                break;
            }
        }

        String descripcionProducto;
        System.out.print("Ingrese la descripcion del producto: ");
        descripcionProducto = scan.nextLine().trim();
        if (descripcionProducto.equals("")) {
            descripcionProducto = producto.get(2);
        }

        double precioProducto;
        while (true){
            System.out.print("Ingrese el precio del producto *: ");
            String input = scan.nextLine().trim();
            if (input.equals("")) {
                precioProducto = Double.parseDouble(producto.get(3));
                break;
            }else{
                boolean validacion = validarOpciones(input);
                if(validacion){
                    precioProducto = Double.parseDouble(input);
                    if(precioProducto <= 0){
                        System.out.println("El valor ingresado es inválido");
                    }else{
                        break;
                    }
                }else{
                    System.out.println("El valor ingresado es inválido");
                }
            }
        }

        int stockProducto;
        while (true){
            System.out.print("Ingrese el stock del producto: ");
            String input = scan.nextLine().trim();
            if (input.equals("")) {
                stockProducto = Integer.parseInt(producto.get(4));
                break;
            }else{
                boolean validacion = validarOpciones(input);
                if(validacion){
                    stockProducto = Integer.parseInt(input);
                    break;
                }else{
                    System.out.println("El valor ingresado es inválido");
                }
            }
        }

        double pesoProducto;
        while (true){
            System.out.print("Ingrese el peso del producto: ");
            String input = scan.nextLine().trim();
            if (input.equals("")) {
                pesoProducto = Double.parseDouble(producto.get(5));
                break;
            }else{
                boolean validacion = validarOpciones(input);
                if(validacion){
                    pesoProducto = Double.parseDouble(input);
                    break;
                }else{
                    System.out.println("El valor ingresado es inválidoj");
                }
            }
        }

        String unidadMedidaProducto;
        System.out.println("Ingrese la unidad medida del producto: ");
        System.out.println("1. kg");
        System.out.println("2. g");
        System.out.println("3. lb");
        System.out.println("4. ml");
        System.out.println("5. L");
        while (true){
            System.out.print("Opción seleccionada: ");
            String input = scan.nextLine().trim();
            if (input.equals("")) {
                unidadMedidaProducto = producto.get(6);
                break;
            } else {
                boolean validacion = validarOpciones(input);
                if(validacion) {
                    int opcionUniProducto = Integer.parseInt(input);
                    if (opcionUniProducto == 1) {
                        unidadMedidaProducto = "kg";
                        break;
                    } else if (opcionUniProducto == 2) {
                        unidadMedidaProducto = "g";
                        break;
                    } else if(opcionUniProducto == 3) {
                        unidadMedidaProducto = "lb";
                        break;
                    } else if(opcionUniProducto == 4) {
                        unidadMedidaProducto = "ml";
                        break;
                    }else if(opcionUniProducto == 5) {
                        unidadMedidaProducto = "L";
                        break;
                    }else{
                        System.out.println("Opción no valida");
                    }
                }else{
                    System.out.println("El valor ingresado es inválido");
                }
            }
        }

        product.actualizarProducto(registro, nombreProducto, descripcionProducto, precioProducto, stockProducto, pesoProducto, unidadMedidaProducto);
    }

    public void eliminarProducto(int registro) {
        product.eliminarProducto(registro);
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

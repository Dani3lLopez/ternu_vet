package src.Views;

import src.Controllers.PetsController;

import java.util.List;
import java.util.Scanner;

public class Pets {
    Scanner scan = new Scanner(System.in);
    public PetsController pet = new PetsController();

    public void petMenu() {
        String separador = "-";

        boolean active = true;
        while (active){
            System.out.println("\uD83D\uDC36 Qué haremos hoy?");
            System.out.println("1. Listar Mascotas");
            System.out.println("2. Registrar Mascota");
            System.out.println("3. Actualizar Mascota");
            System.out.println("4. Eliminar Mascota");
            System.out.println("5. Volver al menú principal");
            System.out.println(separador.repeat(50));
            System.out.print("Seleccione una opción: ");
            int choice = scan.nextInt();

            switch (choice){
                case 1:
                    cargarMascotas();
                    break;
                case 2:
                    registrarMascota();
                    break;
                case 4:
                    cargarMascotas();
                    scan.nextLine();
                    String registro;
                    while(true){
                        System.out.print("Ingrese el número de registro a eliminar: ");
                        registro = scan.nextLine().trim();
                        boolean validacion = validarOpciones(registro);
                        if (!validacion) {
                            System.out.println("El valor ingresado es inválido");
                        }else{
                            break;
                        }
                    }
                    eliminarMascota(Integer.parseInt(registro));
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

    public void cargarMascotas() {
        pet.cargarListaMascotas();
        if (pet.getListaMascotas().isEmpty()) {
            System.out.println("No hay mascotas registradas.");
        } else {
            String separador = "-".repeat(120);
            System.out.println(separador);
            System.out.printf("| %-5s | %-20s | %-20s | %-15s | %-30s | %-15s |\n", "No.", "Nombre", "Color", "Peso", "Genero", "Fallecido");
            System.out.println(separador);

            int i = 1;
            for (List<String> mascota : pet.getListaMascotas()) {
                String valorFallecimiento;
                if(mascota.get(10).equals("0")){
                    valorFallecimiento = "No fallecido/a";
                }else{
                    valorFallecimiento = "Fallecido/a";
                }

                String valorColor = mascota.get(2);
                if(valorColor == null){
                    valorColor = "";
                }

                System.out.printf("| %-5d | %-20s | %-20s | %-15s | %-30s | %-15s |\n", i, mascota.get(1), valorColor, (mascota.get(3) + " " + mascota.get(4)), mascota.get(5), valorFallecimiento);
                i++;
            }
            System.out.println(separador);
        }
    }

    public void registrarMascota() {
        String nombreMascota;
        while (true) {
            System.out.print("Nombre de la mascota *: ");
            nombreMascota = scan.nextLine().trim();
            if (nombreMascota.equals("")) {
                System.out.println("El nombre de la mascota no puede estar vacío");
            } else {
                break;
            }
        }
        pet.setNombreMascota(nombreMascota);

        System.out.print("Color de la mascota: ");
        String colorMascota = scan.nextLine().trim();
        if(colorMascota.isEmpty()){
            colorMascota = null;
        }
        pet.setColorMascota(colorMascota);

        double pesoMascota;
        while (true) {
            System.out.print("Peso de la mascota: ");
            String input = scan.nextLine().trim();
            if (input.equals("")) {
                pesoMascota = 0.0;
                break;
            } else {
                boolean validacion = validarOpciones(input);
                if (validacion) {
                    pesoMascota = Double.parseDouble(input);
                    break;
                } else {
                    System.out.println("El valor ingresado es inválido");
                }
            }
        }
        pet.setPesoMascota(pesoMascota);

        System.out.println("Seleccione la unidad de medida para el peso de la mascota: ");
        System.out.println("1. lb");
        System.out.println("2. kg");

        String unidadPesoMascota = "";
        while (true) {
            System.out.print("Opción seleccionada: ");
            String input = scan.nextLine().trim();
            if (input.equals("")) {
                unidadPesoMascota = null;
                break;
            } else {
                boolean validacion = Pets.validarOpciones(input);
                if(validacion) {
                    int opcionUniPeso = Integer.parseInt(input);
                    if (opcionUniPeso == 1) {
                        unidadPesoMascota = "lb";
                        break;
                    } else if (opcionUniPeso == 2) {
                        unidadPesoMascota = "kg";
                        break;
                    } else {
                        System.out.println("El valor ingresado es inválido");
                    }
                }else{
                    System.out.println("El valor ingresado es inválido");
                }
            }
        }
        pet.setUnidadPesoMascota(unidadPesoMascota);

        String generoMascota;
        System.out.println("Seleccione el genero de la mascota: ");
        System.out.println("1. Masculino");
        System.out.println("2. Femenino");
        while (true) {
            System.out.print("Opción seleccionada: ");
            String opcionGenero = scan.nextLine().trim();
            boolean validacion = Pets.validarOpciones(opcionGenero);
            if(validacion && !opcionGenero.isEmpty()) {
                if (Integer.parseInt(opcionGenero) == 1) {
                    generoMascota = "Masculino";
                    break;
                } else if (Integer.parseInt(opcionGenero) == 2) {
                    generoMascota = "Femenino";
                    break;
                } else {
                    System.out.println("Opción inválida");
                }
            }else{
                System.out.println("Opción inválida");
            }
        }
        pet.setGeneroMascota(generoMascota);

        System.out.print("Chip de la mascota: ");
        String codigoChipMascota = scan.nextLine().trim();
        if(codigoChipMascota.isEmpty()){
            codigoChipMascota = null;
        }
        pet.setCodigoChipMascota(codigoChipMascota);

        System.out.println("Seleccione el estado reproductivo de la mascota: ");
        System.out.println("1. Esterilizada");
        System.out.println("2. No esterilizada");
        System.out.println("3. Desconocido");
        System.out.print("Opción seleccionada: ");
        String estadoRMascota = "";
        while (true) {
            System.out.print("Opción seleccionada: ");
            String input = scan.nextLine().trim();
            if (input.equals("")) {
                estadoRMascota = null;
                break;
            } else {
                boolean validacion = Pets.validarOpciones(input);
                if(validacion) {
                    int opcionEstadoR = Integer.parseInt(input);
                    if (opcionEstadoR == 1) {
                        estadoRMascota = "Esterilizado";
                        break;
                    } else if (opcionEstadoR == 2) {
                        estadoRMascota = "No esterilizado";
                        break;
                    } else if(opcionEstadoR == 3) {
                        estadoRMascota = "Desconocido";
                        break;
                    }
                    else{
                        System.out.println("El valor ingresado es inválido");
                    }
                }else{
                    System.out.println("El valor ingresado es inválido");
                }
            }
        }
        pet.setEstadoReproductivoMascota(estadoRMascota);

        String fechaNacimientoMascota;
        while(true){
            System.out.print("Fecha de nacimiento de la mascota: ");
            fechaNacimientoMascota = scan.nextLine();
            if(fechaNacimientoMascota.trim().isEmpty()){
                fechaNacimientoMascota = null;
                break;
            }else{
                boolean validarFechaNacimientoMascota = validarFecha(fechaNacimientoMascota.trim());
                if(validarFechaNacimientoMascota == false){
                    System.out.println("El valor ingresado es inválido");
                }else{
                    break;
                }
            }
        }
        pet.setFechaNacimientoMascota(fechaNacimientoMascota);

        System.out.println("Seleccione la talla de la mascota: ");
        System.out.println("1. Miniatura");
        System.out.println("2. Pequeño");
        System.out.println("3. Mediano");
        System.out.println("4. Grande");
        System.out.println("5. Gigante");
        System.out.println("6. Desconocido");
        String tallaMascota;
        while (true) {
            System.out.print("Opción seleccionada: ");
            String input = scan.nextLine().trim();
            if (input.equals("")) {
                tallaMascota = null;
                break;
            } else {
                boolean validacion = Pets.validarOpciones(input);
                if(validacion) {
                    int opcionTalla = Integer.parseInt(input);
                    if(opcionTalla == 1){
                        tallaMascota = "Miniatura";
                        break;
                    }else if(opcionTalla == 2){
                        tallaMascota = "Pequeño";
                        break;
                    }else if(opcionTalla == 3){
                        tallaMascota = "Mediano";
                        break;
                    }else if(opcionTalla == 4){
                        tallaMascota = "Grande";
                        break;
                    }else if(opcionTalla == 5){
                        tallaMascota = "Gigante";
                        break;
                    }else if(opcionTalla == 6){
                        tallaMascota = "Desconocido";
                        break;
                    }else{
                        System.out.println("El valor ingresado es inválido");
                    }
                }else{
                    System.out.println("El valor ingresado es inválido");
                }
            }
        }
        pet.setTallaMascota(tallaMascota);

        int resultado = pet.registrarMascota();
        if (resultado == 1){
            System.out.println("Mascota registrada con éxito");
        }else{
            System.out.println("Ha ocurrido un error");
        }
        System.out.println("-".repeat(50));
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

        return validarNumero;
    }

    public static boolean validarFecha(String fecha){
        boolean validarFecha = true;
        if(fecha.length() == 10){
            if(fecha.charAt(4) != '-' || fecha.charAt(7) != '-'){
                validarFecha = false;
            }
            int conteoNoValidosAnio = 0;
            int conteoNoValidosMes = 0;
            int conteoNoValidosDia = 0;

            for (int i = 0; i < 4; i++) {
                if(!Character.isDigit(fecha.charAt(i))){
                    conteoNoValidosAnio++;
                }
            }

            for (int i = 5; i < 7; i++) {
                if(!Character.isDigit(fecha.charAt(i))){
                    conteoNoValidosMes++;
                }
            }

            for (int i = 8; i < 10; i++) {
                if(!Character.isDigit(fecha.charAt(i))){
                    conteoNoValidosDia++;
                }
            }

            if(conteoNoValidosAnio > 0 || conteoNoValidosMes > 0 || conteoNoValidosDia > 0){
                validarFecha = false;
            }

        }else{
            validarFecha = false;
        }
        return validarFecha;
    }

    public void eliminarMascota(int registro) {
        pet.eliminarMascota(registro);
    }
}

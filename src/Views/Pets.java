package src.Views;

import src.Controllers.PetsController;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import src.validations.FormatException;
import src.validations.Validations;

public class Pets {
    Scanner scan = new Scanner(System.in); // creamos un objeto scanner para leer la entrada del usuario
    public PetsController pet = new PetsController(); // creamos una instacia del controlador

    // Creamos un método para poder mostrar el menú
    public void petMenu() {
        String separador = "-";

        boolean active = true;
        while (active) {
            // Permitira mostrar todas las opciones del menú
            System.out.println("\uD83D\uDC36 Qué haremos hoy?");
            System.out.println("1. Listar Mascotas");
            System.out.println("2. Registrar Mascota");
            System.out.println("3. Actualizar Mascota");
            System.out.println("4. Eliminar Mascota");
            System.out.println("5. Volver al menú principal");
            System.out.println(separador.repeat(50)); // Imprime una line separadora

            String choice; // Esta variable es para almacenar la opción elegida por el usuario
            while (true) {
                System.out.print("Seleccione una opción: ");
                choice = scan.nextLine().trim(); // aquí se lee la entrada del usuario y se eliminan los espacios en
                if(!choice.isEmpty()){
                    try{
                        Validations.validarRangoNumeros(choice, 1, 5);
                        break;
                    }catch(FormatException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
            Pets actual = new Pets(); // Genera una nueva instancia de la clase Pets

            // Usamos un switch para que se ejecute la acción que corresponda a la opción
            // elegida
            switch (Integer.parseInt(choice)) {
                case 1:
                    cargarMascotas();
                    break;
                case 2:
                    registrarMascota();
                    System.out.println("-".repeat(50));
                    break;
                case 3:
                    actual.cargarMascotas(); // Muestra la lista de mascotas antes de hacer cambios
                    String registroActualizar;
                    while (true) {
                        System.out.print("Ingrese el número de registro a actualizar: ");
                        registroActualizar = scan.nextLine().trim();
                        try{
                            Validations.validarNumeros(registroActualizar);
                            break;
                        }catch(FormatException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    if(registroActualizar.isEmpty()){
                        break;
                    }else{
                        actual.actualizarMascota(Integer.parseInt(registroActualizar)); // Llama al método para actualizar
                        // una moscata
                        System.out.println("-".repeat(50));
                        break;
                    }
                case 4:
                    actual.cargarMascotas();
                    String registroEliminar;
                    while (true) {
                        System.out.print("Ingrese el número de registro a eliminar: ");
                        registroEliminar = scan.nextLine().trim();
                        try{
                            Validations.validarNumeros(registroEliminar);
                            break;
                        }catch(FormatException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    if(registroEliminar.isEmpty()){
                        break;
                    }else{
                        actual.eliminarMascota(Integer.parseInt(registroEliminar));
                        System.out.println("-".repeat(50));
                        break;
                    }
                case 5:
                    active = false; // Aquí se sale del bucle
                    System.out.println("Cerrando menú...");
                    break;
                default:
                    System.out.println("El valor ingresado no corresponde a una opción de menú");
            }
        }
    }

    // Hicimos otro método para poder cargar y enseñar la lista de mascotas
    public void cargarMascotas() {
        pet.cargarListaMascotas();
        // Verificamos si la lista está vacia
        if (pet.getListaMascotas().isEmpty()) {
            System.out.println("No hay mascotas registradas.");
        } else {
            // De haber mascotas, las muestrará en una tabla
            String separador = "-".repeat(120);
            System.out.println(separador);
            // Mostrará la cabezera de la tabla con los nombres de las columnas
            System.out.printf("| %-5s | %-20s | %-20s | %-15s | %-30s | %-15s |\n", "No.", "Nombre", "Color", "Peso",
                    "Genero", "Fallecido");
            System.out.println(separador);

            int i = 1;
            for (List<String> mascota : pet.getListaMascotas()) {
                String valorFallecimiento;

                if (mascota.get(10).equals("0")) {
                    valorFallecimiento = "No fallecido/a";
                } else {
                    valorFallecimiento = "Fallecido/a";
                }

                String valorColor = mascota.get(2);
                if (valorColor == null) {
                    valorColor = "";
                }

                // Mostrará la información de la mascota en una fila de la tabla
                System.out.printf("| %-5d | %-20s | %-20s | %-15s | %-30s | %-15s |\n", i, mascota.get(1), valorColor,
                        (mascota.get(3) + " " + mascota.get(4)), mascota.get(5), valorFallecimiento);
                i++;
            }
            System.out.println(separador);
        }
    }

    // Otro método para los registro de nuevas máscotas dentro del sistema
    public void registrarMascota() {
        String nombreMascota;

        // Este bucle verificará que se ingrese un nombre válido
        while (true) {
            System.out.print("Nombre de la mascota *: ");
            nombreMascota = scan.nextLine().trim();

            // verifica que el nombre no quede vacío
            try{
                Validations.validarCampoObligatorio(nombreMascota);
                break;
            }catch(FormatException e){
                System.out.println(e.getMessage());
            }
        }
        pet.setNombreMascota(nombreMascota); // Agrega el nombre en el controlador

        // Permitira pedir y guardar información extra de la mascota a registrar
        System.out.print("Color de la mascota: ");
        String colorMascota = scan.nextLine().trim();
        if (colorMascota.isEmpty()) {
            colorMascota = null;
        }
        pet.setColorMascota(colorMascota);

        double pesoMascota;

        // Asegura que el peso sea válido
        while (true) {
            System.out.print("Peso de la mascota: ");
            String input = scan.nextLine().trim();
            if(input.isEmpty()) {
                pesoMascota = 0.0;
                break;
            }else{
                try{
                    Validations.validarNumeros(input);
                    pesoMascota = Double.parseDouble(input);
                    break;
                }catch(FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        pet.setPesoMascota(pesoMascota); // Agrega el peso al controlador

        // Pide seleccionar la unidad de medida para el peso
        System.out.println("Seleccione la unidad de medida para el peso de la mascota: ");
        System.out.println("1. lb");
        System.out.println("2. kg");

        String unidadPesoMascota = ""; // Aquí se agregara la unidad de medida

        // Asegura que la unidad de medida ingresada sea válida
        while (true) {
            System.out.print("Opción seleccionada: ");
            String input = scan.nextLine().trim();
            if (input.isEmpty()) {
                unidadPesoMascota = "kg";
                break;
            } else {
                // Verifica cuál de las opciones de unidad de peso ha sido seleccionada
                try{
                    Validations.validarRangoNumeros(input, 1, 2);
                    int opcionUniPeso = Integer.parseInt(input);
                    if (opcionUniPeso == 1) {
                        unidadPesoMascota = "lb";
                        break;
                    } else if (opcionUniPeso == 2) {
                        unidadPesoMascota = "kg";
                        break;
                    }
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        pet.setUnidadPesoMascota(unidadPesoMascota); // Agrega la unidad de peso al controlador

        String generoMascota;
        System.out.println("Seleccione el genero de la mascota *: ");
        System.out.println("1. Masculino");
        System.out.println("2. Femenino");

        // Este bucle permite establecer el genero de la mascota
        while (true) {
            System.out.print("Opción seleccionada: ");
            String opcionGenero = scan.nextLine().trim();
            try{
                Validations.validarCampoObligatorio(opcionGenero);
                Validations.validarRangoNumeros(opcionGenero, 1, 2);
                if (Integer.parseInt(opcionGenero) == 1) {
                    generoMascota = "Masculino";
                    break;
                } else if (Integer.parseInt(opcionGenero) == 2) {
                    generoMascota = "Femenino";
                    break;
                }
            }catch (FormatException e){
                System.out.println(e.getMessage());
            }
        }
        pet.setGeneroMascota(generoMascota); // Establece el género de la mascota

        System.out.print("Chip de la mascota: ");
        String codigoChipMascota = scan.nextLine().trim();
        if (codigoChipMascota.isEmpty()) {
            codigoChipMascota = null;
        }
        pet.setCodigoChipMascota(codigoChipMascota); // Agrega el código del chip establecido

        System.out.println("Seleccione el estado reproductivo de la mascota: ");
        System.out.println("1. Esterilizada");
        System.out.println("2. No esterilizada");
        System.out.println("3. Desconocido");
        String estadoRMascota = "";

        // Este bucle permite agregar si la mascota esta esterilizada o no
        while (true) {
            System.out.print("Opción seleccionada: ");
            String input = scan.nextLine().trim();
            if (input.isEmpty()) {
                estadoRMascota = null;
                break;
            } else {
                try{
                    Validations.validarRangoNumeros(input, 1, 3);
                    int opcionEstadoR = Integer.parseInt(input);
                    if (opcionEstadoR == 1) {
                        estadoRMascota = "Esterilizado";
                        break;
                    } else if (opcionEstadoR == 2) {
                        estadoRMascota = "No esterilizado";
                        break;
                    } else if (opcionEstadoR == 3) {
                        estadoRMascota = "Desconocido";
                        break;
                    }
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        pet.setEstadoReproductivoMascota(estadoRMascota); // Agrega el estado repructivo de la mascota

        String fechaNacimientoMascota;

        // Con estee bucle obtenemos la fecha de nacimiento de la mascota
        while (true) {
            System.out.print("Fecha de nacimiento de la mascota: ");
            fechaNacimientoMascota = scan.nextLine();
            if (fechaNacimientoMascota.trim().isEmpty()) {
                fechaNacimientoMascota = null;
                break;
            } else {
                try{
                    Validations.validarRangoFechas(fechaNacimientoMascota, LocalDate.now().minusYears(50), LocalDate.now());
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        pet.setFechaNacimientoMascota(fechaNacimientoMascota); // Añade la fecha de nacimiento ingresada

        // Lista las tallas posibles que puede tener la mascota
        System.out.println("Seleccione la talla de la mascota: ");
        System.out.println("1. Miniatura");
        System.out.println("2. Pequeño");
        System.out.println("3. Mediano");
        System.out.println("4. Grande");
        System.out.println("5. Gigante");
        System.out.println("6. Desconocido");
        String tallaMascota = "";

        // Este bucle permite ingresar la talla seleccionada
        while (true) {
            System.out.print("Opción seleccionada: ");
            String input = scan.nextLine().trim();
            if (input.isEmpty()) {
                tallaMascota = null;
                break;
            } else {
                try{
                    Validations.validarRangoNumeros(input, 1, 6);
                    switch (Integer.parseInt(input)) {
                        case 1:
                            tallaMascota = "Miniatura";
                            break;
                        case 2:
                            tallaMascota = "Pequeno";
                            break;
                        case 3:
                            tallaMascota = "Mediano";
                            break;
                        case 4:
                            tallaMascota = "Grande";
                            break;
                        case 5:
                            tallaMascota = "Gigante";
                            break;
                        case 6:
                            tallaMascota = "Desconocido";
                            break;
                    }
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        pet.setTallaMascota(tallaMascota); // Establece la talla seleccionada

        // Registra la mascota o indica si ha habido un error
        int resultado = pet.registrarMascota();
        if (resultado == 1) {
            System.out.println("Mascota registrada con éxito");
        } else {
            System.out.println("Ha ocurrido un error");
        }
        System.out.println("-".repeat(50));
    }

    // Creamos otro método para poder actualizar la información de las mascotas que
    // ya estan en el sistema
    public void actualizarMascota(int registro) {

        // Muestra la información actual de la mascota desde el registro especificado
        List<String> mascota = pet.cargarDatosMascota(registro);
        String nombreMascota;
        System.out.print("Nuevo nombre de la mascota: ");
        nombreMascota = scan.nextLine().trim();

        // Permite mantener el nombre que esta, si el nuevo estuviera vacío
        if (nombreMascota.isEmpty()) {
            nombreMascota = mascota.get(1); // El nombre está en el índice 1
        }

        System.out.print("Nuevo color de la mascota: ");
        String colorMascota = scan.nextLine().trim();
        if (colorMascota.isEmpty()) {
            colorMascota = mascota.get(2); // El color está en el índice 2
        }

        double pesoMascota;

        // Este bucle ayuda a registrar el nuevo peso
        while (true) {
            System.out.print("Nuevo peso de la mascota: ");
            String input = scan.nextLine().trim();
            if (input.isEmpty()) {
                pesoMascota = Double.parseDouble(mascota.get(3)); // El peso está en el índice 3
                break;
            } else {
                try{
                    Validations.validarNumeros(input);
                    pesoMascota = Double.parseDouble(input);
                    break;
                }catch(FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println("Seleccione la unidad de medida nueva para el peso de la mascota: ");
        System.out.println("1. lb");
        System.out.println("2. kg");
        String unidadPesoMascota = "";

        // Este bucle permite ingresar la nueva unidad de medida para el peso
        while (true) {
            System.out.print("Opción seleccionada: ");
            String input = scan.nextLine().trim();
            if (input.isEmpty()) {
                unidadPesoMascota = mascota.get(4); // La unidad de medida para el peso está en el índice 4
                break;
            } else {
                try{
                    Validations.validarRangoNumeros(input, 1, 2);
                    int opcionUniPeso = Integer.parseInt(input);
                    if (opcionUniPeso == 1) {
                        unidadPesoMascota = "lb";
                        break;
                    } else if (opcionUniPeso == 2) {
                        unidadPesoMascota = "kg";
                        break;
                    }
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        String generoMascota;
        System.out.println("Seleccione el nuevo genero de la mascota: ");
        System.out.println("1. Masculino");
        System.out.println("2. Femenino");

        // Permite establecer un nuevo genero
        while (true) {
            System.out.print("Opción seleccionada: ");
            String opcionGenero = scan.nextLine().trim();
            if (opcionGenero.isEmpty()) {
                generoMascota = mascota.get(5); // El genero está en el índice 5
                break;
            } else {
                try{
                    Validations.validarRangoNumeros(opcionGenero, 1, 2);
                    if (Integer.parseInt(opcionGenero) == 1) {
                        generoMascota = "Masculino";
                        break;
                    } else if (Integer.parseInt(opcionGenero) == 2) {
                        generoMascota = "Femenino";
                        break;
                    }
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.print("Nuevo chip de la mascota: ");
        String codigoChipMascota = scan.nextLine().trim();
        if (codigoChipMascota.isEmpty()) {
            codigoChipMascota = mascota.get(6); // El chip está en el índice 6
        }

        System.out.println("Seleccione el nuevo estado reproductivo de la mascota: ");
        System.out.println("1. Esterilizada");
        System.out.println("2. No esterilizada");
        System.out.println("3. Desconocido");
        String estadoRMascota = "";

        // Con este bucle se puede seleccionar un nuevo estado reproductivo para la
        // mascota
        while (true) {
            System.out.print("Opción seleccionada: ");
            String input = scan.nextLine().trim();
            if (input.isEmpty()) {
                estadoRMascota = mascota.get(7); // El estado está en el índice 7
                break;
            } else {
                try{
                    Validations.validarRangoNumeros(input, 1, 3);
                    int opcionEstadoR = Integer.parseInt(input);
                    if (opcionEstadoR == 1) {
                        estadoRMascota = "Esterilizado";
                        break;
                    } else if (opcionEstadoR == 2) {
                        estadoRMascota = "No esterilizado";
                        break;
                    } else if (opcionEstadoR == 3) {
                        estadoRMascota = "Desconocido";
                        break;
                    }
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        String fechaNacimientoMascota;

        // Permite cambiar la fecha de nacimiento
        while (true) {
            System.out.print("Nueva fecha de nacimiento de la mascota: ");
            fechaNacimientoMascota = scan.nextLine();
            if (fechaNacimientoMascota.trim().isEmpty()) {
                fechaNacimientoMascota = mascota.get(8); // La fecha de nacimiento esta en el índice 8
                break;
            } else {
                try{
                    Validations.validarRangoFechas(fechaNacimientoMascota, LocalDate.now().minusYears(50), LocalDate.now());
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println("Seleccione la nueva talla de la mascota: ");
        System.out.println("1. Miniatura");
        System.out.println("2. Pequeño");
        System.out.println("3. Mediano");
        System.out.println("4. Grande");
        System.out.println("5. Gigante");
        System.out.println("6. Desconocido");
        String tallaMascota = "";

        // Permite modificar la talla de la mascota
        while (true) {
            System.out.print("Opción seleccionada: ");
            String input = scan.nextLine().trim();
            if (input.isEmpty()) {
                tallaMascota = mascota.get(9); // La talla está en el índice 9
                break;
            } else {
                try{
                    Validations.validarRangoNumeros(input, 1, 6);
                    switch (Integer.parseInt(input)) {
                        case 1:
                            tallaMascota = "Miniatura";
                            break;
                        case 2:
                            tallaMascota = "Pequeno";
                            break;
                        case 3:
                            tallaMascota = "Mediano";
                            break;
                        case 4:
                            tallaMascota = "Grande";
                            break;
                        case 5:
                            tallaMascota = "Gigante";
                            break;
                        case 6:
                            tallaMascota = "Desconocido";
                            break;
                    }
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println("Indique si la mascota sigue con vida o ha fallecido: ");
        System.out.println("1. No fallecido");
        System.out.println("2. Fallecido");
        boolean fallecido = false;
        String razonesFallecimiento;

        // Permite cambiar si la mascota esta viva o ya falleció
        while (true) {
            System.out.print("Opción seleccionada: ");
            String input = scan.nextLine().trim();

            // Mantiene la información que ya tenía, de no ingresarse nada
            if (input.equals("")) {
                if (mascota.get(10).equals("1")) { // Si fallecio o no está en el índice 10
                    fallecido = true;
                } else {
                    fallecido = false;
                }
                break;
            } else {
                try{
                    Validations.validarRangoNumeros(input, 1, 2);
                    int opcionFallecido = Integer.parseInt(input);
                    if (opcionFallecido == 1) {
                        fallecido = false;
                        break;
                    } else if (opcionFallecido == 2) {
                        fallecido = true;
                        break;
                    }
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        // De cambiar el estado de la mascota a fallecido, pide indicar las razones del
        // fallecimiento
        if (fallecido && mascota.get(10).equals("0")) {
            System.out.print("Indique las razones del fallecimiento de la mascota: ");
            razonesFallecimiento = scan.nextLine().trim();

            // Si por el contrario, la mascota aparecia como fallecida y ahora es cambiado a
            // viva, se eliminan las razones que estaban
        } else if (!fallecido && mascota.get(10).equals("1")) {
            razonesFallecimiento = null;
        } else {

            // Y si no se hace ningún cambio, se mantienen las razones que estaban
            razonesFallecimiento = mascota.get(11);
        }

        pet.actualizarMascota(registro, nombreMascota, colorMascota, pesoMascota, unidadPesoMascota, generoMascota,
                codigoChipMascota, estadoRMascota, fechaNacimientoMascota, tallaMascota, fallecido,
                razonesFallecimiento); // Hace todos los cambios realizados
    }

    // También hicimos un método para eliminar una mascota del sistema,
    // seleccionando su número de registro
    public void eliminarMascota(int registro) {
        pet.eliminarMascota(registro);
    }
}

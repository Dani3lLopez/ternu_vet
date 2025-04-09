package src.views;

import src.controllers.PeopleController;
import src.validations.FormatException;
import src.validations.Validations;

import java.text.Format;
import java.util.List;
import java.util.Scanner;

/*
 * Clase de vista (People)
 * Su funcion es realizar la interaccion con el usuario
 * Muestra el menu de opciones para personas, solicita acciones del usuario
 * Ademas, llama a los metodos del controlador
 */
public class People {

    // Objeto Scanner para leer los datos ingresados en la consola por los usuarios
    Scanner scan = new Scanner(System.in);
    // Se instancia el controlador para gesrionar la parte logica de People
    public PeopleController person = new PeopleController();

    /*
     * Metodo para mostrar el menu de opciones
     */
    public void peopleMenu() {
        Scanner scan = new Scanner(System.in);
        String separador = "-";

        // Opciones del menu
        boolean active = true;
        while (active){
            System.out.println("\uD83E\uDDD1 Qué haremos hoy?");
            System.out.println("1. Listar Personas");
            System.out.println("2. Registrar Persona");
            System.out.println("3. Actualizar Persona");
            System.out.println("4. Eliminar Persona");
            System.out.println("5. Volver al menú principal");
            System.out.println(separador.repeat(50));
            System.out.print("Seleccione una opción: ");

            // Guarda la opcion seleccionada por el usuario
            String choice = scan.nextLine().trim();
            if(!choice.isEmpty()){
                try{
                    // Se llaman a los metodos correspondientes con base a la opcion seleccionada por el usuario.
                    // Ademas, si se requeiren ciertos parametros, se solicitan al usuario
                    Validations.validarRangoNumeros(choice, 1, 5);
                    switch (Integer.parseInt(choice)){
                        case 1:
                            cargarPersonas();
                            break;
                        case 2:
                            registrarPersona();
                            System.out.println("-".repeat(50));
                            break;
                        case 3:
                            cargarPersonas();
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
                                actualizarPersona(Integer.parseInt(r));
                                System.out.println("-".repeat(50));
                                break;
                            }
                        case 4:
                            cargarPersonas();
                            String registro = "";
                            while(true){
                                System.out.print("Ingrese el número de registro a actualizar: ");
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
                                eliminarPersona(Integer.parseInt(registro));
                                System.out.println("-".repeat(50));
                                break;
                            }
                        case 5:
                            // Vuelve al menu principal
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

    /*
     * Carga la lista de personas desde el controlador
     * Muestro estas personas en la consola
     */
    public void cargarPersonas() {
        // Llama al controlador
        person.cargarListaPersonas();
        if (person.listaPersonas().isEmpty()) {
            System.out.println("No hay personas registradas.");
        } else {
            // Formato para mostrar los datos
            String separador = "-".repeat(120);
            System.out.println(separador);
            System.out.printf("| %-5s | %-20s | %-20s | %-15s | %-30s | %-15s |\n", "No.", "Nombre", "Apellido", "Teléfono", "Email", "DUI");
            System.out.println(separador);
            //Muestra cada persona
            int i = 1;
            for (List<String> persona : person.listaPersonas()) {
                System.out.printf("| %-5d | %-20s | %-20s | %-15s | %-30s | %-15s |\n", i, persona.get(1), persona.get(2), persona.get(3), persona.get(4), persona.get(5));
                i++;
            }
            System.out.println(separador);
        }
    }
    /*
     * Solicita al usuario los datos de la nueva persona
     *  Registra los datos a traves del controlador
     */
    public void registrarPersona() {
        String nombre = "";
        while (true) {
            System.out.print("Nombres *: ");
            nombre = scan.nextLine().trim();
            try {
                Validations.validarCampoObligatorio(nombre);
                break;
            } catch (FormatException e) {
                System.out.println(e.getMessage());
            }
        }
        person.setNombrePersona(nombre);

        String apellido = "";
        while (true) {
            System.out.print("Apellidos *: ");
            apellido = scan.nextLine().trim();
            try {
                Validations.validarCampoObligatorio(apellido);
                break;
            } catch (FormatException e) {
                System.out.println(e.getMessage());
            }
        }
        person.setApellidoPersona(apellido);

        String telefono = "";
        while (true) {
            System.out.print("Telefono: ");
            telefono = scan.nextLine().trim();
            if (telefono.isEmpty()) {
                telefono = null;
                break;
            } else {
                try {
                    Validations.validarNumeroTelefono(telefono);
                    break;
                } catch (FormatException e) {
                    System.out.println(e.getMessage());
                }
            }
            person.setTelefonoPersona(telefono);
        }

        String correo = "";
        while(true) {
            System.out.print("Correo Electronico: ");
            correo = scan.nextLine().trim();
            if(correo.isEmpty()){
                correo = null;
                break;
            }else{
                try{
                    Validations.validarEmail(correo);
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        person.setEmailPersona(correo);

        String dui = "";
        while (true) {
            System.out.print("Identificacion (DUI): ");
            dui = scan.nextLine().trim();
            if(dui.isEmpty()){
                dui = null;
                break;
            }else{
                try{
                    Validations.validarDUI(dui);
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        person.setDuiPersona(dui);

        // Llama al controlador para registrar a la persona
        int resultado = person.RegistrarPersona();
        if (resultado == 1){
            System.out.println("Persona registrada con éxito");
        }else{
            System.out.println("Ha ocurrido un error");
        }
    }

    /*
     * Actualiza los datos de una persona; solicita a los usuarios los nuevos valores
     * Permite dejar un campo vacio para mantener el valor que ya tenia
     */
    public void actualizarPersona(int r){
        List<String> persona = person.cargarDatosPersona(r);

        if (persona.isEmpty()) {
            System.out.println("No se encontró el registro especificado");
            return;
        }

        System.out.print("Nuevo Nombre: ");
        String nombre = scan.nextLine().trim();
        if (nombre.isEmpty()) nombre = persona.get(1);

        System.out.print("Nuevo Apellido: ");
        String apellido = scan.nextLine().trim();
        if (apellido.isEmpty()) apellido = persona.get(2);

        String telefono = "";
        while(true){
            System.out.print("Nuevo Teléfono: ");
            telefono = scan.nextLine().trim();
            if (telefono.isEmpty()){
                telefono = persona.get(3);
                break;
            }else{
                try{
                    Validations.validarNumeroTelefono(telefono);
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        String email = "";
        while(true){
            System.out.print("Nuevo Email: ");
            email = scan.nextLine().trim();
            if (email.isEmpty()){
                email = persona.get(4);
                break;
            }else{
                try{
                    Validations.validarEmail(email);
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        String dui = "";
        while (true){
            System.out.print("Nuevo DUI: ");
            dui = scan.nextLine().trim();
            if (dui.isEmpty()){
                dui = persona.get(5);
            }else{
                try{
                    Validations.validarDUI(dui);
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        // LLama al controlador para realizar la actualizacion
        person.actualizarPersona(r, nombre, apellido, telefono, email, dui);
    }

    /*
     * Elimina a una persona con base a su indice en la lista
     */
    public void eliminarPersona(int registro) {
        person.eliminarPersona(registro);
    }
}

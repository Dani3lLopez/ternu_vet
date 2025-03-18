package src.Views;

import src.Controllers.PeopleController;

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

        boolean active = true;
        while (active){
            // Opciones del menu
            System.out.println("\uD83D\uDC36 Qué haremos hoy?");
            System.out.println("1. Listar Personas");
            System.out.println("2. Registrar Persona");
            System.out.println("3. Actualizar Persona");
            System.out.println("4. Eliminar Persona");
            System.out.println("5. Volver al menú principal");
            System.out.println(separador.repeat(50));
            System.out.print("Seleccione una opción: ");

            // Guarda la opcion seleccionada por el usuario
            int choice = scan.nextInt();

            // Se llaman a los metodos correspondientes con base a la opcion seleccionada por el usuario.
            // Ademas, si se requeiren ciertos parametros, se solicitan al usuario

            switch (choice){
                case 1:
                    cargarPersonas();
                    break;
                case 2:
                    registrarPersona();
                    break;
                case 3:
                    cargarPersonas();
                    System.out.print("Ingrese el número de registro a actualizar: ");
                    int r = scan.nextInt();
                    scan.nextLine();
                    actualizarPersona(r);
                    break;
                case 4:
                    cargarPersonas();
                    System.out.print("Ingrese el número de registro a eliminar: ");
                    int registro = scan.nextInt();
                    scan.nextLine();
                    eliminarPersona(registro);
                    break;
                case 5:
                    // Vuelve al menu principal
                    active = false;
                    System.out.println("Cerrando menú...");
                    break;
                default:
                    System.out.println("El valor ingresado no corresponde a una opción de menú");
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
    public void registrarPersona(){
        System.out.println("Nombres: ");
        String nombre = scan.nextLine();
        person.setNombrePersona(nombre);
        System.out.println("Apellidos: ");
        String apellido = scan.nextLine();
        person.setApellidoPersona(apellido);
        System.out.println("Telefono: ");
        String telefono = scan.nextLine();
        person.setTelefonoPersona(telefono);
        System.out.println("Correo Electronico: ");
        String correo = scan.nextLine();
        person.setEmailPersona(correo);
        System.out.println("Identificacion (DUI): ");
        String dui = scan.nextLine();
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
     * Actualza los datos de una persona; solicita a los usuarios los nuevos valores
     * Permite dejar un campo vacio para mantener el valor que ya tenia
     */
    public void actualizarPersona(int r){
        List<String> persona = person.cargarDatosPersona(r);
        System.out.print("Nuevo Nombre: ");
        String nombre = scan.nextLine();
        if (nombre.isEmpty()) nombre = persona.get(1);
        System.out.print("Nuevo Apellido: ");
        String apellido = scan.nextLine();
        if (apellido.isEmpty()) apellido = persona.get(2);
        System.out.print("Nuevo Teléfono: ");
        String telefono = scan.nextLine();
        if (telefono.isEmpty()) telefono = persona.get(3);
        System.out.print("Nuevo Email: ");
        String email = scan.nextLine();
        if (email.isEmpty()) email = persona.get(4);
        System.out.print("Nuevo DUI: ");
        String dui = scan.nextLine();
        if (dui.isEmpty()) dui = persona.get(5);
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

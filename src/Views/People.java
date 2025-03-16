package src.Views;

import src.Controllers.PeopleController;

import java.util.List;
import java.util.Scanner;

public class People {
    Scanner scan = new Scanner(System.in);
    public PeopleController person = new PeopleController();

    public void peopleMenu() {
        Scanner scan = new Scanner(System.in);
        String separador = "-";

        boolean active = true;
        while (active){
            System.out.println("\uD83D\uDC36 Qué haremos hoy?");
            System.out.println("1. Listar Personas");
            System.out.println("2. Registrar Persona");
            System.out.println("3. Actualizar Persona");
            System.out.println("4. Eliminar Persona");
            System.out.println("5. Volver al menú principal");
            System.out.println(separador.repeat(50));
            System.out.print("Seleccione una opción: ");
            int choice = scan.nextInt();

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
                    active = false;
                    System.out.println("Cerrando menú...");
                    break;
                default:
                    System.out.println("El valor ingresado no corresponde a una opción de menú");
            }
        }
    }

    public void cargarPersonas() {
        person.cargarListaPersonas();
        if (person.listaPersonas().isEmpty()) {
            System.out.println("No hay personas registradas.");
        } else {
            String separador = "-".repeat(120);
            System.out.println(separador);
            System.out.printf("| %-5s | %-20s | %-20s | %-15s | %-30s | %-15s |\n", "No.", "Nombre", "Apellido", "Teléfono", "Email", "DUI");
            System.out.println(separador);

            int i = 1;
            for (List<String> persona : person.listaPersonas()) {
                System.out.printf("| %-5d | %-20s | %-20s | %-15s | %-30s | %-15s |\n", i, persona.get(1), persona.get(2), persona.get(3), persona.get(4), persona.get(5));
                i++;
            }
            System.out.println(separador);
        }
    }
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

        int resultado = person.RegistrarPersona();
        if (resultado == 1){
            System.out.println("Persona registrada con éxito");
        }else{
            System.out.println("Ha ocurrido un error");
        }
    }
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
        person.actualizarPersona(r, nombre, apellido, telefono, email, dui);
    }
    public void eliminarPersona(int registro) {
        person.eliminarPersona(registro);
    }
}

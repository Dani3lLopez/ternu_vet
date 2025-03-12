package src.Views;

import src.Controllers.PeopleController;
import src.Models.PeopleModel;

import java.util.List;
import java.util.Scanner;

public class People {
    Scanner scan = new Scanner(System.in);
    public PeopleController person = new PeopleController();

    public void registrarPersona(){
        System.out.println("Nombres: ");
        String nombre = scan.nextLine();
        person.nombrePersona = nombre;
        System.out.println("Apellidos: ");
        String apellido = scan.nextLine();
        person.apellidoPersona = apellido;
        System.out.println("Telefono: ");
        String telefono = scan.nextLine();
        person.telefonoPersona = telefono;
        System.out.println("Correo Electronico: ");
        String correo = scan.nextLine();
        person.emailPersona = correo;
        System.out.println("Identificacion (DUI): ");
        String dui = scan.nextLine();
        person.duiPersona = dui;
        int resultado = person.RegistrarPersona();
        if (resultado == 1){
            System.out.println("Persona registrada con éxito");
        }else{
            System.out.println("Ha ocurrido un error");
        }
    }
    public void cargarPersonas(){
        person.cargarListaPersonas();
        if (person.listaPersonas.isEmpty()) {
            System.out.println("No hay personas registradas.");
        } else {
            int index = 1;
            for (List<String> persona : person.listaPersonas) {
                System.out.println(index + ". " + persona.get(1) + " - " + persona.get(2) + " - " + persona.get(3) + " - " + persona.get(4));
                index++;
            }
        }
    }
    public void cargarPersona(int registro){
        PeopleController con = new PeopleController();
        con.cargarListaPersonas();
        List<String> persona = con.cargarDatosPersona(registro);

        if (!persona.isEmpty()) {
            System.out.println("Información de la persona:");
            System.out.println("Nombre: " + persona.get(1));
            System.out.println("Apellido: " + persona.get(2));
            System.out.println("Teléfono: " + persona.get(3));
            System.out.println("Email: " + persona.get(4));
            System.out.println("DUI: " + persona.get(5));
        } else {
            System.out.println("No se encontró una persona con ese registro.");
        }
    }
    public void eliminarPersona(int registro) {
        String id = person.capturarIdLista(registro);
        if (id != null) {
            int resultado = PeopleModel.eliminarPersona(id);
            if (resultado > 0) {
                System.out.println("Registro eliminado correctamente.");
            } else {
                System.out.println("Error al eliminar el registro.");
            }
        } else {
            System.out.println("Registro inexistente.");
        }
    }
    public void actualizarPersona(int r){
        System.out.print("Nuevo Nombre: ");
        String nombre = scan.nextLine();
        System.out.print("Nuevo Apellido: ");
        String apellido = scan.nextLine();
        System.out.print("Nuevo Teléfono: ");
        String telefono = scan.nextLine();
        System.out.print("Nuevo Email: ");
        String email = scan.nextLine();
        System.out.print("Nuevo DUI: ");
        String dui = scan.nextLine();
        person.actualizarPersona(r, nombre, apellido, telefono, email, dui);
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String separador = "-";

        boolean active = true;
        while (active){
            System.out.println("\uD83D\uDC36 Qué haremos hoy?");
            System.out.println("1. Listar Personas");
            System.out.println("2. Registrar Persona");
            System.out.println("3. Actualizar Persona");
            System.out.println("4. Eliminar Persona");
            System.out.println("5. Salir");
            System.out.println(separador.repeat(50));
            System.out.print("Seleccione una opción: ");
            int choice = scan.nextInt();
            People actual = new People();

            switch (choice){
                case 1:
                    actual.cargarPersonas();
                    break;
                case 2:
                    actual.registrarPersona();
                case 3:
                    actual.cargarPersonas();
                    System.out.print("Ingrese el número de registro a actualizar: ");
                    int r = scan.nextInt();
                    scan.nextLine();
                    actual.actualizarPersona(r);
                    break;
                case 4:
                    actual.cargarPersonas();
                    System.out.print("Ingrese el número de registro a eliminar: ");
                    int registro = scan.nextInt();
                    scan.nextLine();
                    actual.eliminarPersona(registro);
                    break;
                case 5:
                    active = false;
                    System.out.println("Cerrando menú...");
                    break;
                case 6:
                    actual.cargarPersona(2);
                    break;
                default:
                    System.out.println("El valor ingresado no corresponde a una opción de menú");
            }
        }
    }
}

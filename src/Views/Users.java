package src.Views;

import src.Controllers.PeopleController;
import src.Controllers.UsersController;

import java.util.List;
import java.util.Scanner;

public class Users {
    Scanner scan = new Scanner(System.in);
    public UsersController user = new UsersController();
    public PeopleController person = new PeopleController();

    public void userMenu() {
        Scanner scan = new Scanner(System.in);
        String separador = "-";

        boolean active = true;
        while (active){
            System.out.println("\uD83D\uDC36 Qué haremos hoy?");
            System.out.println("1. Listar Usuarios");
            System.out.println("2. Registrar Usuarios");
            System.out.println("3. Actualizar Usuarios");
            System.out.println("4. Desactivar Usuarios");
            System.out.println("5. Volver al menú principal");
            System.out.println(separador.repeat(50));
            System.out.print("Seleccione una opción: ");
            int choice = scan.nextInt();
            Users actual = new Users();

            switch (choice){
                case 1:
                    actual.cargarUsuarios();
                    System.out.println(separador.repeat(70));
                    break;
                case 2:
                    //actual.registrarPropietario();
                    break;
                case 3:
                    //actual.cargarPropietarios();
                    System.out.print("Ingrese el número de registro a actualizar: ");
                    int r = scan.nextInt();
                    scan.nextLine();
                    //actual.actualizarPropietario(r);
                    break;
                case 4:
                    //actual.cargarPropietarios();
                    System.out.print("Ingrese el número de registro a eliminar: ");
                    int registro = scan.nextInt();
                    scan.nextLine();
                    //actual.desactivarPropietario(registro);
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

    public void cargarUsuarios(){
        user.llenarListas();
        List<List<String>> usuarios = user.listaUsuarios();
        if (user.listaUsuarios().isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        }else {
            String separador = "-".repeat(85);
            System.out.println(separador);
            System.out.printf("| %-5s | %-20s | %-20s | %-20s |\n", "No.", "Usuario", "Estado", "Doctor");
            System.out.println(separador);

            int n = 1;
            for (List<String> usuario : usuarios) {
                String nombre = usuario.get(0);
                String estado = usuario.get(2);
                String idDoctor = usuario.get(4);
                String idPersonaDoctor = "";

                for(List<String> doctor: user.listaDoctores()){
                    if(doctor.get(0).equalsIgnoreCase(idDoctor)){
                        idPersonaDoctor = doctor.get(1);
                        break;
                    }
                }
                String doctorNombre = user.capturarNombres(idPersonaDoctor);

                System.out.printf("| %-5d | %-20s | %-20s | %-20s|\n", n, nombre, estado, doctorNombre);
                n++;
            }
            System.out.println(separador);
        }
    }
}

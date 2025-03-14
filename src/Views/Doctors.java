package src.Views;

import src.Controllers.DoctorsController;
import src.Controllers.PeopleController;

import java.util.List;
import java.util.Scanner;

public class Doctors {
    Scanner scan = new Scanner(System.in);
    public DoctorsController doc = new DoctorsController();
    public PeopleController person = new PeopleController();

    public void doctorMenu() {
        Scanner scan = new Scanner(System.in);
        String separador = "-";

        boolean active = true;
        while (active){
            System.out.println("\uD83D\uDC36 Qué haremos hoy?");
            System.out.println("1. Listar Doctores");
            System.out.println("2. Registrar Doctores");
            System.out.println("3. Actualizar Doctores");
            System.out.println("4. Eliminar Doctores");
            System.out.println("5. Volver al menú principal");
            System.out.println(separador.repeat(50));
            System.out.print("Seleccione una opción: ");
            int choice = scan.nextInt();
            Doctors actual = new Doctors();

            switch (choice){
                case 1:
                    actual.cargarDoctores();
                    System.out.println(separador.repeat(70));
                    break;
                case 2:
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

    public void cargarDoctores(){
        doc.llenarListas();
        List<List<String>> doctores = doc.listaDoctores();
        if (doc.listaDoctores().isEmpty()) {
            System.out.println("No hay doctores registrados.");
        }else {
            String separador = "-".repeat(100);
            System.out.println(separador);
            System.out.printf("| %-5s | %-20s | %-20s | %-20s | %-20s |\n", "No.", "Fec. Contratacion", "Fec. Nacimiento", "Nombre", "Especialidad");
            System.out.println(separador);

            int n = 1;
            for (List<String> doctor : doctores) {
                String fechaCont = doctor.get(1);
                String fechaNacimiento = doctor.get(2);
                String nombre = doc.capturarNombres(doctor.get(3));
                String especialidad = doc.capturarEspecialidades(doctor.get(4));

                System.out.printf("| %-5d | %-20s | %-20s | %-20s | %-20s |\n", n, fechaCont, fechaNacimiento, nombre, especialidad);
                n++;
            }
            System.out.println(separador);
        }
    }
}

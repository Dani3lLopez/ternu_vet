package src.Views;

import src.Controllers.SpecialtiesController;

import java.util.List;
import java.util.Scanner;

public class Specialties {
    Scanner scan = new Scanner(System.in);
    public SpecialtiesController specialty = new SpecialtiesController();

    public void specialtiesMenu() {
        Scanner scan = new Scanner(System.in);
        String separador = "-";

        boolean active = true;
        while (active){
            System.out.println("\uD83D\uDC36 Qué haremos hoy?");
            System.out.println("1. Listar Especialidades");
            System.out.println("2. Registrar Especialidades");
            System.out.println("3. Eliminar Especialidades");
            System.out.println("4. Volver al menú principal");
            System.out.println(separador.repeat(50));
            System.out.print("Seleccione una opción: ");
            int choice = scan.nextInt();
            Specialties actual = new Specialties();

            switch (choice){
                case 1:
                    actual.cargarEspecialidades();
                    break;
                case 2:
                    //actual.registrarPersona();
                    break;
                case 3:
                    actual.cargarEspecialidades();
                    System.out.print("Ingrese el número de registro a actualizar: ");
                    int r = scan.nextInt();
                    scan.nextLine();
                    //actual.actualizarPersona(r);
                    break;
                case 4:
                    actual.cargarEspecialidades();
                    System.out.print("Ingrese el número de registro a eliminar: ");
                    int registro = scan.nextInt();
                    scan.nextLine();
                    //actual.eliminarPersona(registro);
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

    public void cargarEspecialidades() {
        specialty.cargarListaEspecialidades();
        if (specialty.listaEspecialidades().isEmpty()) {
            System.out.println("No hay especialidades registradas.");
        } else {
            String separador = "-".repeat(32);
            System.out.println(separador);
            System.out.printf("| %-5s | %-20s |\n", "No.", "Especialidad");
            System.out.println(separador);

            int i = 1;
            for (List<String> especialidad : specialty.listaEspecialidades()) {
                System.out.printf("| %-5d | %-20s |\n", i, especialidad.get(1));
                i++;
            }
            System.out.println(separador);
        }
    }
}

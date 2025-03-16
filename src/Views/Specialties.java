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
            System.out.println("\uD83E\uDE7A Qué haremos hoy?");
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
                    actual.registrarEspecialidad();
                    break;
                case 3:
                    actual.cargarEspecialidades();
                    System.out.print("Ingrese el número de registro que desea eliminar: ");
                    int r = scan.nextInt();
                    scan.nextLine();
                    actual.eliminarEspecialidad(r);
                    break;
                case 4:
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
    public void registrarEspecialidad(){
        System.out.println("Nombres: ");
        String nombreEspecialidad = scan.nextLine();
        specialty.setNombreEspecialidad(nombreEspecialidad);

        int resultado = specialty.RegistrarEspecialidad();
        if (resultado == 1){
            System.out.println("Especialidad registrada con éxito");
        }else{
            System.out.println("Ha ocurrido un error");
        }
    }
    public void eliminarEspecialidad(int registro) {
        specialty.eliminarEspecialidad(registro);
    }
}

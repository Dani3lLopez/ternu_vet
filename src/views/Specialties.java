package src.views;

import src.controllers.SpecialtiesController;
import src.validations.FormatException;
import src.validations.Validations;

import java.util.List;
import java.util.Scanner;

public class Specialties {
    Scanner scan = new Scanner(System.in);
    public SpecialtiesController specialty = new SpecialtiesController();

    // Creamos un menú principal para la gestión de especialidades
    public void specialtiesMenu() {
        Scanner scan = new Scanner(System.in);
        String separador = "-";

        boolean active = true;
        while (active) {
            System.out.println("\uD83E\uDE7A Qué haremos hoy?");
            System.out.println("1. Listar Especialidades");
            System.out.println("2. Registrar Especialidades");
            System.out.println("3. Eliminar Especialidades");
            System.out.println("4. Volver al menú principal");
            System.out.println(separador.repeat(50));
            System.out.print("Seleccione una opción: ");
            String choice = scan.nextLine().trim();
            if(!choice.isEmpty()) {
                try{
                    Validations.validarRangoNumeros(choice, 1, 4);
                    switch (Integer.parseInt(choice)) {
                        case 1:
                            cargarEspecialidades();
                            break;
                        case 2:
                            registrarEspecialidad();
                            System.out.println("-".repeat(50));
                            break;
                        case 3:
                            cargarEspecialidades();
                            String r = "";
                            while(true){
                                System.out.print("Ingrese el número de registro que desea eliminar: ");
                                r = scan.nextLine().trim();
                                try{
                                    Validations.validarNumeros(r);
                                    break;
                                }catch (FormatException e){
                                    System.out.println(e.getMessage());
                                }
                            }
                            if(r.isEmpty()){
                                break;
                            }else{
                                eliminarEspecialidad(Integer.parseInt(r));
                                System.out.println("-".repeat(50));
                                break;
                            }
                        case 4:
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

    // Primero carga la lista de especialidades y luego la muestra
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

    // Registra una nueva especialidad
    public void registrarEspecialidad() {
        String nombreEspecialidad = "";
        while (true){
            System.out.print("Nombre de la especialidad *: ");
            nombreEspecialidad = scan.nextLine().trim();
            try{
                Validations.validarCampoObligatorio(nombreEspecialidad);
                break;
            }catch (FormatException e){
                System.out.println(e.getMessage());
            }
        }
        specialty.setNombreEspecialidad(nombreEspecialidad);

        int resultado = specialty.RegistrarEspecialidad();
        if (resultado == 1) {
            System.out.println("Especialidad registrada con éxito");
        } else {
            System.out.println("Ha ocurrido un error");
        }
    }

    // Dado un número de registro, elemina una especialidad
    public void eliminarEspecialidad(int registro) {
        specialty.eliminarEspecialidad(registro);
    }
}

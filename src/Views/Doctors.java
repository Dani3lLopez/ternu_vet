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
            System.out.println("\uD83D\uDC8A Qué haremos hoy?");
            System.out.println("1. Listar Doctores");
            System.out.println("2. Registrar Doctores");
            System.out.println("3. Actualizar Doctores");
            System.out.println("4. Volver al menú principal");
            System.out.println(separador.repeat(50));
            System.out.print("Seleccione una opción: ");
            int choice = scan.nextInt();

            switch (choice){
                case 1:
                    cargarDoctores();
                    System.out.println(separador.repeat(70));
                    break;
                case 2:
                    registrarDoctor();
                    break;
                case 3:
                    cargarDoctores();
                    System.out.print("Ingrese el número de registro a actualizar: ");
                    int r = scan.nextInt();
                    scan.nextLine();
                    actualizarDoctor(r);
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
    public void registrarDoctor() {
        person.cargarListaPersonas();
        doc.llenarListas();

        String separador = "-".repeat(70);
        System.out.println(separador);
        System.out.printf("| %-5s | %-50s |\n", "No.", "Nombre");
        System.out.println(separador);
        int r = 1;
        for (int p = 0; p < person.listaPersonas().size(); p++) {
            List<String> persona = person.listaPersonas().get(p);

            System.out.printf("| %-5d | %-50s |\n", r, persona.get(1) + " " + persona.get(2));
            r++;
        }
        System.out.println(separador);

        System.out.print("Seleccione al doctor/a: ");
        int valor = scan.nextInt();
        scan.nextLine();

        if (valor > 0 && valor <= person.listaPersonas().size()) {
            String id = person.capturarIdLista(valor);
            doc.setIdPersona(id);

            System.out.println(separador);
            System.out.printf("| %-5s | %-50s |\n", "No.", "Especialidad");
            System.out.println(separador);

            for (int c = 0; c < doc.listaEspecialidades().size(); c++) {
                List<String> doctor = doc.listaEspecialidades().get(c);
                System.out.printf("| %-5d | %-50s |\n", (c + 1), doctor.get(1));
            }
            System.out.println(separador);

            System.out.print("Seleccione la especialidad: ");
            int v = scan.nextInt();
            scan.nextLine();

            if (v > 0 && v <= doc.listaEspecialidades().size()) {
                String n = doc.capturarIdListaEspecialidad(v);
                doc.setIdEspecialidad(n);

                System.out.println("Fecha de nacimiento (YYYY-MM-DD): ");
                String fechaNacimiento = scan.nextLine();
                doc.setFechaNacimientoDoctor(fechaNacimiento);

                System.out.println("Fecha de contratacion (YYYY-MM-DD): ");
                String fechaContratacion = scan.nextLine();
                doc.setFechaContratacionDoctor(fechaContratacion);

                int resultado = doc.RegistrarDoctor();

                if (resultado == 1) {
                    System.out.println("Doctor registrado con éxito.");
                } else {
                    System.out.println("Ha ocurrido un error al registrar el doctor.");
                }
            } else {
                System.out.println("Selección de persona inválida.");
            }
        }
    }
    public void actualizarDoctor(int r) {
        List<String> doctor = doc.cargarDatosDoctor(r);
        if (doctor.isEmpty()) {
            System.out.println("No se encontró el registro especificado");
            return;
        }

        String id = doctor.get(0);
        person.cargarListaPersonas();
        System.out.println("Personas Disponibles:");
        String separador = "-".repeat(70);
        System.out.println(separador);
        System.out.printf("| %-5s | %-50s |\n", "No.", "Nombre");
        System.out.println(separador);
        int i = 1;
        for (List<String> persona : person.listaPersonas()) {
            System.out.printf("| %-5d | %-50s |\n", i, persona.get(1) + " " + persona.get(2));
            i++;
        }
        System.out.println(separador);

        System.out.print("Nuevo doctor: ");
        String np = scan.nextLine();
        String nid = doctor.get(3);
        if (!np.isEmpty()) {
            int idNuevo = Integer.parseInt(np);
            if (idNuevo > 0 && idNuevo <= person.listaPersonas().size()) {
                nid = person.capturarIdLista(idNuevo);
            } else {
                System.out.println("Persona no valida.");
            }
        }

        doc.llenarListas();
        System.out.println("Especialidades:");
        for (int c = 0; c < doc.listaEspecialidades().size(); c++) {
            List<String> especialidad = doc.listaEspecialidades().get(c);
            System.out.println((c + 1) + ". " + especialidad.get(1));
        }

        System.out.print("Nueva especialidad: ");
        String ne = scan.nextLine();
        String nide = doctor.get(4);
        if (!ne.isEmpty()) {
            int idNuevo = Integer.parseInt(ne);
            if (idNuevo > 0 && idNuevo <= doc.listaEspecialidades().size()) {
                nide = doc.capturarIdListaEspecialidad(idNuevo);
            } else {
                System.out.println("Especialidad no valida.");
            }
        }

        System.out.print("Nueva fecha de nacimiento (YYYY-MM-DD): ");
        String nuevaFechaNacimiento = scan.nextLine();
        if (nuevaFechaNacimiento.isEmpty()) {
            nuevaFechaNacimiento = doctor.get(2);
        }

        System.out.print("Nueva fecha de contratacion (YYYY-MM-DD): ");
        String nuevaFechaContratacion = scan.nextLine();
        if (nuevaFechaContratacion.isEmpty()) {
            nuevaFechaContratacion = doctor.get(1);
        }

        doc.actualizarDoctor(r, nuevaFechaContratacion, nuevaFechaNacimiento, nid, nide);
    }
}

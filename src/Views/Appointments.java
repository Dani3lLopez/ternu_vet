package src.Views;

import src.Controllers.AppointmentsController;

import java.util.List;
import java.util.Scanner;

public class Appointments {
    Scanner scan = new Scanner(System.in);
    public AppointmentsController appointment = new AppointmentsController();

    public void appointmentMenu() {
        Scanner scan = new Scanner(System.in);
        String separador = "-";

        boolean active = true;
        while (active){
            System.out.println("\uD83D\uDC36 Qué haremos hoy?");
            System.out.println("1. Listar Citas");
            System.out.println("2. Registrar Citas");
            System.out.println("3. Actualizar Citas");
            System.out.println("4. Eliminar Citas");
            System.out.println("5. Volver al menú principal");
            System.out.println(separador.repeat(50));
            System.out.print("Seleccione una opción: ");
            int choice = scan.nextInt();
            Appointments actual = new Appointments();

            switch (choice){
                case 1:
                    actual.cargarCitas();
                    System.out.println(separador.repeat(70));
                    break;
                case 2:
                    //actual.registrarDoctor();
                    break;
                case 3:
                    //actual.cargarDoctores();
                    System.out.print("Ingrese el número de registro a actualizar: ");
                    int r = scan.nextInt();
                    scan.nextLine();
                    //actual.actualizarDoctor(r);
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

    public void cargarCitas(){
        appointment.llenarListas();
        List<List<String>> citas = appointment.listaCitas();
        if (appointment.listaCitas().isEmpty()) {
            System.out.println("No hay citas registradas.");
        }else {
            String separador = "-".repeat(100);
            System.out.println(separador);
            System.out.printf("| %-5s | %-20s | %-20s | %-20s | %-20s |\n", "No.", "Fec. Cita", "Hora - Cita", "Mascota", "Doctor");
            System.out.println(separador);

            int n = 1;
            for (List<String> cita : citas) {
                String fechaCita = cita.get(2);
                String horaCita = cita.get(3);
                String mascota = appointment.capturarMascotas(cita.get(4));
                String idDoctor = cita.get(5);
                String idPersonaDoctor = "";
                for (List<String> doctor : appointment.listaDoctores()) {
                    if (doctor.get(0).equalsIgnoreCase(idDoctor)) {
                        idPersonaDoctor = doctor.get(3);
                        break;
                    }
                }
                String doctor = appointment.capturarNombresDoctores(idPersonaDoctor);

                System.out.printf("| %-5d | %-20s | %-20s | %-20s | %-20s |\n", n, fechaCita, horaCita, mascota, doctor);
                n++;
            }
            System.out.println(separador);
        }
    }
}

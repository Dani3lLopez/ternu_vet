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
        while (active) {
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

            switch (choice) {
                case 1:
                    cargarCitas();
                    System.out.println(separador.repeat(70));
                    break;
                case 2:
                    registrarCita();
                    break;
                case 3:
                    cargarCitas();
                    System.out.print("Ingrese el número de registro a actualizar: ");
                    int r = scan.nextInt();
                    scan.nextLine();
                    actualizarCita(r);
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

    public void cargarCitas() {
        appointment.llenarListas();
        List<List<String>> citas = appointment.listaCitas();
        if (appointment.listaCitas().isEmpty()) {
            System.out.println("No hay citas registradas.");
        } else {
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

    public void registrarCita() {
        appointment.llenarListas();

        String separador = "-".repeat(70);
        System.out.println(separador);
        System.out.printf("| %-5s | %-50s |\n", "No.", "Doctor");
        System.out.println(separador);

        List<List<String>> doctores = appointment.listaDoctores();

        int r = 1;
        for (List<String> doctor : doctores) {
            String idp = doctor.get(3);
            String nombreDoctor = appointment.capturarNombresDoctores(idp);

            System.out.printf("| %-5d | %-50s |\n", r, nombreDoctor);
            r++;
        }
        System.out.println(separador);

        System.out.print("Seleccione al doctor/a: ");
        int valor = scan.nextInt();
        scan.nextLine();

        if (valor > 0 && valor <= appointment.listaDoctores().size()) {
            String id = appointment.capturarIdListaDoctores(valor);
            appointment.setIdDoctor(id);

            System.out.println(separador);
            System.out.printf("| %-5s | %-50s |\n", "No.", "Mascota");
            System.out.println(separador);

            for (int c = 0; c < appointment.listaMascotas().size(); c++) {
                List<String> mascota = appointment.listaMascotas().get(c);
                System.out.printf("| %-5d | %-50s |\n", (c + 1), mascota.get(1));
            }
            System.out.println(separador);

            System.out.print("Seleccione la mascota: ");
            int v = scan.nextInt();
            scan.nextLine();

            if (v > 0 && v <= appointment.listaMascotas().size()) {
                String n = appointment.capturarIdListaMascotas(v);
                appointment.setIdMascota(n);

                System.out.println("Motivo de la cita: ");
                String motivoCita = scan.nextLine();
                appointment.setMotivoCita(motivoCita);

                System.out.println("Fecha de cita (YYYY-MM-DD): ");
                String fechaCita = scan.nextLine();
                appointment.setFechaCita(fechaCita);

                System.out.println("Hora de cita (HH-MM-SS): ");
                String horaCita = scan.nextLine();
                appointment.setHoraCita(horaCita);

                Boolean visibilidad = true;
                appointment.setVisibilidadCita(visibilidad);

                int resultado = appointment.registrarCita();

                if (resultado == 1) {
                    System.out.println("Cita registrada con éxito.");
                } else {
                    System.out.println("Ha ocurrido un error al registrar la cita.");
                }
            } else {
                System.out.println("Selección de persona inválida.");
            }
        }
    }

    public void actualizarCita(int r) {
        String idCita = appointment.capturarIdLista(r);
        if (idCita == null) {
            System.out.println("Registro extraño");
            return;
        }

        List<String> cita = appointment.cargarDatosCita(r);
        if (cita.isEmpty()) {
            System.out.println("No se encontró el registro especificado");
            return;
        }

        String separador = "-".repeat(70);
        System.out.println(separador);
        System.out.printf("| %-5s | %-50s |\n", "No.", "Doctor");
        System.out.println(separador);

        List<List<String>> doctores = appointment.listaDoctores();
        for (int i = 0; i < doctores.size(); i++) {
            List<String> doctor = doctores.get(i);
            String nombreDoctor = appointment.capturarNombresDoctores(doctor.get(3)); // ID persona en la posición 3
            System.out.printf("| %-5d | %-50s |\n", (i + 1), nombreDoctor);
        }
        System.out.println(separador);

        System.out.print("Nuevo doctor: ");
        String np = scan.nextLine();
        String nuevoIdDoctor = cita.get(5); // ID actual del doctor

        if (!np.isEmpty()) {
            int idNuevo = Integer.parseInt(np);
            if (idNuevo > 0 && idNuevo <= doctores.size()) {
                nuevoIdDoctor = doctores.get(idNuevo - 1).get(0);
            } else {
                System.out.println("Doctor no válido.");
            }
        }

        System.out.println(separador);
        System.out.printf("| %-5s | %-50s |\n", "No.", "Mascota");
        System.out.println(separador);

        List<List<String>> mascotas = appointment.listaMascotas();
        for (int i = 0; i < mascotas.size(); i++) {
            System.out.printf("| %-5d | %-50s |\n", (i + 1), mascotas.get(i).get(1));
        }
        System.out.println(separador);

        // Seleccionar mascota
        System.out.print("Nueva mascota: ");
        String nm = scan.nextLine();
        String nuevoIdMascota = cita.get(4);

        if (!nm.isEmpty()) {
            int idNuevaMascota = Integer.parseInt(nm);
            if (idNuevaMascota > 0 && idNuevaMascota <= mascotas.size()) {
                nuevoIdMascota = mascotas.get(idNuevaMascota - 1).get(0);
            } else {
                System.out.println("Mascota no válida.");
            }
        }

        System.out.print("Nuevo motivo de cita: ");
        String nuevoMotivo = scan.nextLine();
        if (nuevoMotivo.isEmpty()) nuevoMotivo = cita.get(1);

        System.out.print("Nueva fecha de cita: ");
        String nuevaFecha = scan.nextLine();
        if (nuevaFecha.isEmpty()) nuevaFecha = cita.get(2);

        System.out.print("Nueva hora de cita: ");
        String nuevaHora = scan.nextLine();
        if (nuevaHora.isEmpty()) nuevaHora = cita.get(3);

        Boolean visibilidad = true;

        // Actualizar cita
        appointment.actualizarCita(r, nuevoMotivo, nuevaFecha, nuevaHora, nuevoIdMascota, nuevoIdDoctor, visibilidad);
    }
}


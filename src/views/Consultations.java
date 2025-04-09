package src.views;

import src.controllers.ConsultationsController;

import java.util.List;
import java.util.Scanner;

/*
 * Esta vista gestiona la interaccion con el usuario para listar, crear y desactivar consultas
 * Muestra las opciones, captura entradas y llama a los metodos correspondientes a la solicitud
 */
public class Consultations {
    // Scanner para recibir entradas y poderlas procesar
    Scanner scan = new Scanner(System.in);
    // Instancia del controlador para las operaciones de consultas
    public ConsultationsController consultation = new ConsultationsController();

    /*
     * Muestra el menu
     * Gestiona la solicitud hecha por el usuario. Con base a la opcion seleccionada
     * se llaman a los metodos correspondientes y si se necesitan parametros se solicitan
     */
    public void consultationMenu() {
        Scanner scan = new Scanner(System.in);
        String separador = "-";

        boolean active = true;
        while (active) {
            System.out.println("\uD83D\uDC36 Qué haremos hoy?");
            System.out.println("1. Listar Consultas");
            System.out.println("2. Registrar Consultas");
            System.out.println("3. Actualizar Consultas");
            System.out.println("4. Eliminar Consultas");
            System.out.println("5. Volver al menú principal");
            System.out.println(separador.repeat(50));
            System.out.print("Seleccione una opción: ");
            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    cargarConsultas();
                    System.out.println(separador.repeat(70));
                    break;
                case 2:
                    registrarConsulta();
                    break;
                case 3:
                    cargarConsultas();
                    System.out.print("Ingrese el número de registro a actualizar: ");
                    int r = scan.nextInt();
                    scan.nextLine();
                    actualizarConsulta(r);
                    break;
                case 4:
                    cargarConsultas();
                    System.out.print("Ingrese el número de registro a eliminar: ");
                    int registro = scan.nextInt();
                    scan.nextLine();
                    desactivarConsulta(registro);
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

    /*
     * Carga la lista de consultas desde el controlador y las muestra en formato tabular
     */
    public void cargarConsultas() {
        consultation.llenarListas();
        List<List<String>> consultas = consultation.listaConsultas();
        if (consultation.listaConsultas().isEmpty()) {
            System.out.println("No hay consultas registradas.");
        } else {
            String separador = "-".repeat(100);
            System.out.println(separador);
            System.out.printf("| %-5s | %-20s | %-20s | %-20s |\n", "No.", "Fec. Consulta", "Mascota", "Doctor");
            System.out.println(separador);

            int n = 1;
            for (List<String> consulta : consultas) {
                String fechaConsulta = consulta.get(1);
                String mascota = consultation.capturarMascotas(consulta.get(5));
                String idDoctor = consulta.get(6);
                String idPersonaDoctor = "";
                for (List<String> doctor : consultation.listaDoctores()) {
                    if (doctor.get(0).equalsIgnoreCase(idDoctor)) {
                        idPersonaDoctor = doctor.get(3);
                        break;
                    }
                }
                String doctor = consultation.capturarNombresDoctores(idPersonaDoctor);

                System.out.printf("| %-5d | %-20s | %-20s | %-20s |\n", n, fechaConsulta, mascota, doctor);
                n++;
            }
            System.out.println(separador);
        }
    }
    /*
     * Solicita al usuario los datos para crear la nueva consulta
     * Llama al controlador para registrar la consulta en la base de datos
     */
    public void registrarConsulta() {
        // Actualiza las listas para tener los datos mas recientes
        consultation.llenarListas();

        String separador = "-".repeat(70);
        System.out.println(separador);
        // Muestra a los doctores disponibles para que se seleccione uno
        System.out.printf("| %-5s | %-50s |\n", "No.", "Doctor");
        System.out.println(separador);

        List<List<String>> doctores = consultation.listaDoctores();

        int r = 1;
        // Itera la lista de doctores y muestra sus nombres
        for (List<String> doctor : doctores) {
            String idp = doctor.get(3);
            String nombreDoctor = consultation.capturarNombresDoctores(idp);

            System.out.printf("| %-5d | %-50s |\n", r, nombreDoctor);
            r++;
        }
        System.out.println(separador);
        // Solicita al usuario seleccionar un doctor
        System.out.print("Seleccione al doctor/a: ");
        int valor = scan.nextInt();
        scan.nextLine();

        if (valor > 0 && valor <= consultation.listaDoctores().size()) {
            String id = consultation.capturarIdListaDoctores(valor);
            consultation.setIdDoctor(id);

            System.out.println(separador);
            // Muestra la lista de mascotas para elegir
            System.out.printf("| %-5s | %-50s |\n", "No.", "Mascota");
            System.out.println(separador);

            for (int c = 0; c < consultation.listaMascotas().size(); c++) {
                List<String> mascota = consultation.listaMascotas().get(c);
                System.out.printf("| %-5d | %-50s |\n", (c + 1), mascota.get(1));
            }
            System.out.println(separador);
            // Selecciona una mascota
            System.out.print("Seleccione la mascota: ");
            int v = scan.nextInt();
            scan.nextLine();

            if (v > 0 && v <= consultation.listaMascotas().size()) {
                // Asigna el id de la mascota seleccionada
                String n = consultation.capturarIdListaMascotas(v);
                consultation.setIdMascota(n);
                // Solicita al usuario ingresar los diversos datos requeridos
                System.out.println("Motivo de la consulta: ");
                String motivoConsulta = scan.nextLine();
                consultation.setMotivoConsulta(motivoConsulta);

                System.out.println("Fecha de consulta (YYYY-MM-DD): ");
                String fechaConsulta = scan.nextLine();
                consultation.setFechaConsulta(fechaConsulta);

                System.out.println("Diagnostico: ");
                String diagnostico = scan.nextLine();
                consultation.setDiagnosticoConsulta(diagnostico);

                System.out.println("Notas: ");
                String notas = scan.nextLine();
                consultation.setNotasConsulta(notas);

                Boolean visibilidad = true;
                consultation.setVisibilidadConsulta(visibilidad);

                // Llama al controlador para registrar la nueva consulta
                int resultado = consultation.registrarConsulta();

                if (resultado == 1) {
                    System.out.println("Consulta registrada con éxito.");
                } else {
                    System.out.println("Ha ocurrido un error al registrar la consulta.");
                }
            } else {
                System.out.println("Selección de persona inválida.");
            }
        }
    }

    /*
     * Actualiza los datos de una consulta existente; solicita a los usuarios los nuevos valores
     * Llama al controlador para aplicar los cambios
     * El parametro es el indice de la consulta a actualizar
     */
    public void actualizarConsulta(int r) {
        String idConsulta = consultation.capturarIdLista(r);
        if (idConsulta == null) {
            System.out.println("Registro extraño");
            return;
        }
        // Carga los datos actuales de la consulta
        List<String> consulta = consultation.cargarDatosConsulta(r);
        if (consulta.isEmpty()) {
            System.out.println("No se encontró el registro especificado");
            return;
        }

        String separador = "-".repeat(70);
        System.out.println(separador);
        // Muestra una tabla con los doctores disponibles
        System.out.printf("| %-5s | %-50s |\n", "No.", "Doctor");
        System.out.println(separador);

        List<List<String>> doctores = consultation.listaDoctores();
        // Muestra el nombre de cada doctor
        for (int i = 0; i < doctores.size(); i++) {
            List<String> doctor = doctores.get(i);
            String nombreDoctor = consultation.capturarNombresDoctores(doctor.get(3)); // ID persona en la posición 3
            System.out.printf("| %-5d | %-50s |\n", (i + 1), nombreDoctor);
        }
        System.out.println(separador);
        // Se solicita el nuevo doctor, o se puede dejar vacio si es el mismo
        System.out.print("Nuevo doctor: ");
        String np = scan.nextLine();
        String nuevoIdDoctor = consulta.get(6); // ID actual del doctor

        if (!np.isEmpty()) {
            int idNuevo = Integer.parseInt(np);
            if (idNuevo > 0 && idNuevo <= doctores.size()) {
                nuevoIdDoctor = doctores.get(idNuevo - 1).get(0);
            } else {
                System.out.println("Doctor no válido.");
            }
        }

        System.out.println(separador);
        // Muestra la lista de mascotas disponibles
        System.out.printf("| %-5s | %-50s |\n", "No.", "Mascota");
        System.out.println(separador);

        List<List<String>> mascotas = consultation.listaMascotas();
        for (int i = 0; i < mascotas.size(); i++) {
            System.out.printf("| %-5d | %-50s |\n", (i + 1), mascotas.get(i).get(1));
        }
        System.out.println(separador);

        // Permite al usuario seleccionar la nueva mascota, o dejar vacio para mantener la actual
        System.out.print("Nueva mascota: ");
        String nm = scan.nextLine();
        String nuevoIdMascota = consulta.get(5);

        if (!nm.isEmpty()) {
            int idNuevaMascota = Integer.parseInt(nm);
            if (idNuevaMascota > 0 && idNuevaMascota <= mascotas.size()) {
                nuevoIdMascota = mascotas.get(idNuevaMascota - 1).get(0);
            } else {
                System.out.println("Mascota no válida.");
            }
        }
        // Solicita el nuevo motivo de la consulta, se puede dejar el actual
        System.out.print("Nuevo motivo de consulta: ");
        String nuevoMotivo = scan.nextLine();
        if (nuevoMotivo.isEmpty()) nuevoMotivo = consulta.get(2);
        // Solicita la nueva fecha de la consulta, se puede dejar la actual
        System.out.print("Nueva fecha de consulta: ");
        String nuevaFecha = scan.nextLine();
        if (nuevaFecha.isEmpty()) nuevaFecha = consulta.get(1);
        // Solicita el nuevo diagnostico de la consulta, se puede dejar la actual
        System.out.print("Nuevo diagnostico: ");
        String nuevoDiagnostico = scan.nextLine();
        if (nuevoDiagnostico.isEmpty()) nuevoDiagnostico = consulta.get(3);
        //Solicita las nuevas notas de la consulta, se puede dejar la actual
        System.out.print("Nuevas notas: ");
        String nuevaNota = scan.nextLine();
        if (nuevaNota.isEmpty()) nuevaNota = consulta.get(4);

        Boolean visibilidad = true;

        // Actualizar consulta
        consultation.actualizarConsulta(r, nuevaFecha, nuevoMotivo, nuevoDiagnostico, nuevaNota, nuevoIdMascota, nuevoIdDoctor, visibilidad);
    }
    public void desactivarConsulta(int registro) {
        consultation.desactivarConsulta(registro);
    }
}

package src.views;

import src.controllers.AppointmentsController;
import src.validations.FormatException;
import src.validations.Validations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

/**
 * Vista: Appointments
 * Contiene los métodos necesarios para administrar a las citas
 */
public class Appointments {
    // Scanner para recibir entradas y poderlas procesar
    Scanner scan = new Scanner(System.in);
    // Instancia del controlador para las operaciones de citas
    public AppointmentsController appointment = new AppointmentsController();

    /**
     * Menú de opciones para administrar los datos
     */
    public void appointmentMenu() {
        Scanner scan = new Scanner(System.in);
        String separador = "-";

        boolean active = true;
        while (active) {
            System.out.println("\uD83D\uDCC5 Qué haremos hoy?");
            System.out.println("1. Listar Citas");
            System.out.println("2. Registrar Citas");
            System.out.println("3. Actualizar Citas");
            System.out.println("4. Eliminar Citas");
            System.out.println("5. Volver al menú principal");
            System.out.println(separador.repeat(50));
            System.out.print("Seleccione una opción: ");
            String choice = scan.nextLine().trim();
            if(!choice.isEmpty()){
                try{
                    Validations.validarRangoNumeros(choice, 1, 5);
                    switch (Integer.parseInt(choice)) {
                        case 1:
                            cargarCitas();
                            break;
                        case 2:
                            registrarCita();
                            System.out.println(separador.repeat(50));
                            break;
                        case 3:
                            cargarCitas();
                            String r = "";
                            while(true){
                                System.out.print("Ingrese el número de registro a actualizar: ");
                                r = scan.nextLine().trim();
                                try{
                                    Validations.validarNumeros(r);
                                    break;
                                }catch(FormatException e){
                                    System.out.println(e.getMessage());
                                }
                            }
                            if(r.isEmpty()){
                                break;
                            }else{
                                actualizarCita(Integer.parseInt(r));
                                System.out.println(separador.repeat(50));
                                break;
                            }
                        case 4:
                            cargarCitas();
                            String registro = "";
                            while(true){
                                System.out.print("Ingrese el número de registro a eliminar: ");
                                registro = scan.nextLine().trim();
                                try {
                                    Validations.validarNumeros(registro);
                                    break;
                                }catch(FormatException e){
                                    System.out.println(e.getMessage());
                                }
                            }
                            if(registro.isEmpty()){
                                break;
                            }else{
                                desactivarCita(Integer.parseInt(registro));
                                System.out.println(separador.repeat(50));
                                break;
                            }
                        case 5:
                            active = false;
                            System.out.println("Cerrando menú...");
                            break;
                    }
                }catch(FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * Carga las citas de la base de datos
     */
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
            // Itera cada cita y muestra la información en formato
            for (List<String> cita : citas) {
                String fechaCita = cita.get(2);
                String horaCita = cita.get(3);
                String mascota = appointment.capturarMascotas(cita.get(4));
                String idDoctor = cita.get(5);
                String idPersonaDoctor = "";

                // En la lista de doctores, busca el registro que coincide con el idDoctor
                for (List<String> doctor : appointment.listaDoctores()) {
                    if (doctor.get(0).equalsIgnoreCase(idDoctor)) {
                        idPersonaDoctor = doctor.get(3);
                        break;
                    }
                }
                // Obtiene el nombre completo del doctor respectivo
                String doctor = appointment.capturarNombresDoctores(idPersonaDoctor);

                System.out.printf("| %-5d | %-20s | %-20s | %-20s | %-20s |\n", n, fechaCita, horaCita, mascota, doctor);
                n++;
            }
            System.out.println(separador);
        }
    }

    /**
     * Registra nuevas citas en la base de datos
     */
    public void registrarCita() {
        // Actualiza las listas para tener los datos mas recientes
        appointment.llenarListas();

        String separador = "-".repeat(70);
        System.out.println(separador);
        // Muestra a los doctores disponibles para que se seleccione uno
        System.out.printf("| %-5s | %-50s |\n", "No.", "Doctor");
        System.out.println(separador);

        List<List<String>> doctores = appointment.listaDoctores();

        int r = 1;
        // Itera la lista de doctores y muestra sus nombres
        for (List<String> doctor : doctores) {
            String idp = doctor.get(3);
            String nombreDoctor = appointment.capturarNombresDoctores(idp);

            System.out.printf("| %-5d | %-50s |\n", r, nombreDoctor);
            r++;
        }
        System.out.println(separador);
        // Solicita al usuario seleccionar un doctor
        String valor = "";
        while(true){
            System.out.print("Seleccione al doctor/a *: ");
            valor = scan.nextLine().trim();
            try{
                Validations.validarCampoObligatorio(valor);
                Validations.validarRangoNumeros(valor, 1, appointment.listaDoctores().size());
                break;
            }catch(FormatException e){
                System.out.println(e.getMessage());
            }
        }

        String id = appointment.capturarIdListaDoctores(Integer.parseInt(valor));
        appointment.setIdDoctor(id);

        System.out.println(separador);
        // Muestra la lista de mascotas para elegir
        System.out.printf("| %-5s | %-50s |\n", "No.", "Mascota");
        System.out.println(separador);

        for (int c = 0; c < appointment.listaMascotas().size(); c++) {
            List<String> mascota = appointment.listaMascotas().get(c);
            System.out.printf("| %-5d | %-50s |\n", (c + 1), mascota.get(1));
        }

        System.out.println(separador);

        String v = "";
        // Selecciona una mascota
        while(true){
            System.out.print("Seleccione la mascota *: ");
            v = scan.nextLine().trim();
            try{
                Validations.validarCampoObligatorio(v);
                Validations.validarRangoNumeros(v, 1, appointment.listaMascotas().size());
                break;
            }catch(FormatException e){
                System.out.println(e.getMessage());
            }
        }
        String n = appointment.capturarIdListaMascotas(Integer.parseInt(v));
        appointment.setIdMascota(n);

        // Solicita al usuario ingresar el motivo de la cita
        String motivoCita = "";
        while(true){
            System.out.print("Motivo de la cita *: ");
            motivoCita = scan.nextLine().trim();
            try{
                Validations.validarCampoObligatorio(motivoCita);
                break;
            }catch(FormatException e){
                System.out.println(e.getMessage());
            }
        }
        appointment.setMotivoCita(motivoCita);

        String fechaCita = "";
        while(true){
            System.out.print("Fecha de la cita *: ");
            fechaCita = scan.nextLine().trim();
            try{
                Validations.validarCampoObligatorio(fechaCita);
                Validations.validarRangoFechas(fechaCita, LocalDate.now(), LocalDate.now().plusYears(1));
                break;
            }catch (FormatException e){
                System.out.println(e.getMessage());
            }
        }
        appointment.setFechaCita(fechaCita);

        // Se solicita la hora de lo cita
        String horaCita = "";
        while(true){
            System.out.print("Hora de la cita *: ");
            horaCita = scan.nextLine().trim();
            try{
                Validations.validarCampoObligatorio(horaCita);
                Validations.validarRangoHoras(horaCita, LocalTime.of(8, 0, 0), LocalTime.of(16, 0, 0));
                break;
            }catch (FormatException e){
                System.out.println(e.getMessage());
            }
        }
        appointment.setHoraCita(horaCita);

        Boolean visibilidad = true;
        appointment.setVisibilidadCita(visibilidad);

        // Llama al controlador para registrar la nueva cita
        int resultado = appointment.registrarCita();

        if (resultado == 1) {
            System.out.println("Cita registrada con éxito.");
        } else {
            System.out.println("Ha ocurrido un error al registrar la cita.");
        }
    }

    /**
     * Actualiza un registro de cita existente
     * @param r número de registro
     * Es posible dejar campos en blanco para mantener su valor actual
     */
    public void actualizarCita(int r) {
        // Carga los datos actuales de la cita
        List<String> cita = appointment.cargarDatosCita(r);
        if (cita.isEmpty()) {
            System.out.println("No se encontró el registro especificado");
            return;
        }

        String separador = "-".repeat(70);
        System.out.println(separador);
        // Muestra una tabla con los doctores disponibles
        System.out.printf("| %-5s | %-50s |\n", "No.", "Doctor");
        System.out.println(separador);

        List<List<String>> doctores = appointment.listaDoctores();
        // Muestra el nombre de cada doctor
        for (int i = 0; i < doctores.size(); i++) {
            List<String> doctor = doctores.get(i);
            String nombreDoctor = appointment.capturarNombresDoctores(doctor.get(3)); // ID persona en la posición 3
            System.out.printf("| %-5d | %-50s |\n", (i + 1), nombreDoctor);
        }
        System.out.println(separador);

        String np = "";
        String nuevoIdDoctor = "";
        while(true){
            System.out.print("Nuevo doctor: ");
            np = scan.nextLine().trim();
            if(np.isEmpty()){
                nuevoIdDoctor = cita.get(5);
                break;
            }else{
                try{
                    Validations.validarRangoNumeros(np, 1, doctores.size());
                    nuevoIdDoctor = doctores.get(Integer.parseInt(np) - 1).get(0);
                    break;
                }catch(FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println(separador);
        // Muestra la lista de mascotas disponibles
        System.out.printf("| %-5s | %-50s |\n", "No.", "Mascota");
        System.out.println(separador);

        List<List<String>> mascotas = appointment.listaMascotas();
        for (int i = 0; i < mascotas.size(); i++) {
            System.out.printf("| %-5d | %-50s |\n", (i + 1), mascotas.get(i).get(1));
        }
        System.out.println(separador);

        String nm = "";
        String nuevoIdMascota = "";
        while(true){
            System.out.print("Nueva mascota: ");
            nm = scan.nextLine().trim();
            if(nm.isEmpty()){
                nuevoIdMascota = cita.get(4);
                break;
            }else{
                try{
                    Validations.validarRangoNumeros(nm, 1, mascotas.size());
                    nuevoIdMascota = mascotas.get(Integer.parseInt(nm) - 1).get(0);
                    break;
                }catch(FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        //Solicita el nuevo motivo de la cita, se puede dejar el actual
        System.out.print("Nuevo motivo de cita: ");
        String nuevoMotivo = scan.nextLine();
        if (nuevoMotivo.isEmpty()) nuevoMotivo = cita.get(1);

        String nuevaFecha = "";
        while(true){
            System.out.print("Nueva fecha de cita: ");
            nuevaFecha = scan.nextLine().trim();
            if(nuevaFecha.isEmpty()){
                nuevaFecha = cita.get(2);
                break;
            }else{
                try{
                    Validations.validarRangoFechas(nuevaFecha, LocalDate.now(), LocalDate.now().plusYears(1));
                    break;
                }catch(FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        String nuevaHora = "";
        while(true){
            //Solicita la nueva hora de la cita, se puede dejar la actual
            System.out.print("Nueva hora de cita: ");
            nuevaHora = scan.nextLine().trim();
            if(nuevaHora.isEmpty()){
                nuevaHora = cita.get(3);
                break;
            }else{
                try{
                    Validations.validarRangoHoras(nuevaHora, LocalTime.of(8, 0, 0), LocalTime.of(16, 0, 0));
                    break;
                }catch(FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        Boolean visibilidad = true;

        // Llama al controlador para actualizar la cita con los datos nuevos
        appointment.actualizarCita(r, nuevoMotivo, nuevaFecha, nuevaHora, nuevoIdMascota, nuevoIdDoctor, visibilidad);
    }

    /**
     * Desactiva un registro de cita
     * @param registro número de registro
     */
    public void desactivarCita(int registro) {
        appointment.desactivarCita(registro);
    }
}


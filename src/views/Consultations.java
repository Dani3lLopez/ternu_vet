package src.views;

import src.controllers.ConsultationsController;
import src.validations.FormatException;
import src.validations.Validations;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Vista: Consultations
 * Contiene los métodos necesarios para administrar a las consultas
 */
public class Consultations {
    // Scanner para recibir entradas y poderlas procesar
    Scanner scan = new Scanner(System.in);
    // Instancia del controlador para las operaciones de consultas
    public ConsultationsController consultation = new ConsultationsController();

    /**
     * Menú de opciones para administrar datos
     */
    public void consultationMenu() {
        Scanner scan = new Scanner(System.in);
        String separador = "-";

        boolean active = true;
        while (active) {
            System.out.println("\uD83E\uDD15 Qué haremos hoy?");
            System.out.println("1. Listar Consultas");
            System.out.println("2. Registrar Consultas");
            System.out.println("3. Actualizar Consultas");
            System.out.println("4. Eliminar Consultas");
            System.out.println("5. Volver al menú principal");
            System.out.println(separador.repeat(50));
            System.out.print("Seleccione una opción: ");
            String choice = scan.nextLine().trim();

            if(!choice.isEmpty()){
                try{
                    Validations.validarRangoNumeros(choice, 1, 5);
                    switch (Integer.parseInt(choice)) {
                        case 1:
                            cargarConsultas();
                            break;
                        case 2:
                            registrarConsulta();
                            System.out.println(separador.repeat(50));
                            break;
                        case 3:
                            cargarConsultas();
                            String r = "";
                            while (true){
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
                                actualizarConsulta(Integer.parseInt(r));
                                System.out.println(separador.repeat(50));
                                break;
                            }
                        case 4:
                            cargarConsultas();
                            String registro = "";
                            while(true){
                                System.out.print("Ingrese el número de registro a eliminar: ");
                                registro = scan.nextLine().trim();
                                try{
                                    Validations.validarNumeros(registro);
                                    break;
                                }catch(FormatException e){
                                    System.out.println(e.getMessage());
                                }
                            }
                            if(registro.isEmpty()){
                               break;
                            }else{
                                desactivarConsulta(Integer.parseInt(registro));
                                System.out.println(separador.repeat(50));
                                break;
                            }
                        case 5:
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

    /**
     * Carga las consultas almacenadas en la base de datos
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

    /**
     * Registra nuevas consultas en la base de datos
     */
    public void registrarConsulta() {
        // Actualiza las listas para tener los datos más recientes
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
        String valor = "";
        while(true){
            System.out.print("Seleccione al doctor/a *: ");
            valor = scan.nextLine().trim();
            try{
                Validations.validarCampoObligatorio(valor);
                Validations.validarRangoNumeros(valor, 1, doctores.size());
                break;
            }catch (FormatException e){
                System.out.println(e.getMessage());
            }
        }

        String id = consultation.capturarIdListaDoctores(Integer.parseInt(valor));
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

        String v = "";
        while(true){
            System.out.print("Seleccione la mascota *: ");
            v = scan.nextLine().trim();
            try{
                Validations.validarCampoObligatorio(v);
                Validations.validarRangoNumeros(v, 1, consultation.listaMascotas().size());
                break;
            }catch (FormatException e){
                System.out.println(e.getMessage());
            }
        }

        String n = consultation.capturarIdListaMascotas(Integer.parseInt(v));
        consultation.setIdMascota(n);

        String motivoConsulta = "";
        // Solicita al usuario ingresar los diversos datos requeridos
        while(true){
            System.out.print("Motivo de la consulta *: ");
            motivoConsulta = scan.nextLine().trim();
            try {
                Validations.validarCampoObligatorio(motivoConsulta);
                break;
            }catch (FormatException e){
                System.out.println(e.getMessage());
            }
        }
        consultation.setMotivoConsulta(motivoConsulta);

        String fechaConsulta = "";
        while(true){
            System.out.print("Fecha de consulta *: ");
            fechaConsulta = scan.nextLine().trim();
            try{
                Validations.validarCampoObligatorio(fechaConsulta);
                Validations.validarRangoFechas(fechaConsulta, LocalDate.now().minusYears(1), LocalDate.now());
                break;
            }catch (FormatException e){
                System.out.println(e.getMessage());
            }
        }
        consultation.setFechaConsulta(fechaConsulta);

        String diagnostico = "";
        while(true){
            System.out.print("Diagnostico *: ");
            diagnostico = scan.nextLine().trim();
            try{
                Validations.validarCampoObligatorio(diagnostico);
                break;
            }catch (FormatException e){
                System.out.println(e.getMessage());
            }
        }
        consultation.setDiagnosticoConsulta(diagnostico);

        System.out.print("Notas: ");
        String notas = scan.nextLine().trim();
        if(notas.isEmpty()){
            notas = null;
        }
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
    }

    /**
     * Actualiza una cita existente
     * @param r número de registro
     * Es posible dejar campos en blanco para mantener su valor actual
     */
    public void actualizarConsulta(int r) {
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

        String np = "";
        String nuevoIdDoctor = "";
        while(true){
            System.out.print("Nuevo doctor: ");
            np = scan.nextLine().trim();
            if(np.isEmpty()){
                nuevoIdDoctor = consulta.get(6);
                break;
            }else{
                try{
                    Validations.validarRangoNumeros(np, 1, doctores.size());
                    nuevoIdDoctor = doctores.get(Integer.parseInt(np) - 1).get(0);
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
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

        String nm = "";
        String nuevoIdMascota = "";
        while(true){
            System.out.print("Nueva mascota: ");
            nm = scan.nextLine().trim();
            if(nm.isEmpty()){
                nuevoIdMascota = consulta.get(5);
                break;
            }else{
                try{
                    Validations.validarRangoNumeros(nm, 1, mascotas.size());
                    nuevoIdMascota = mascotas.get(Integer.parseInt(nm) - 1).get(0);
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        // Solicita el nuevo motivo de la consulta, se puede dejar el actual
        System.out.print("Nuevo motivo de consulta: ");
        String nuevoMotivo = scan.nextLine().trim();
        if (nuevoMotivo.isEmpty()) nuevoMotivo = consulta.get(2);

        // Solicita la nueva fecha de la consulta, se puede dejar la actual
        String nuevaFecha = "";
        while(true){
            System.out.print("Nueva fecha de consulta: ");
            nuevaFecha = scan.nextLine().trim();
            if(nuevaFecha.isEmpty()){
                nuevaFecha = consulta.get(1);
                break;
            }else{
                try{
                    Validations.validarRangoFechas(nuevaFecha, LocalDate.now().minusYears(1), LocalDate.now());
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        // Solicita el nuevo diagnostico de la consulta, se puede dejar la actual
        System.out.print("Nuevo diagnostico: ");
        String nuevoDiagnostico = scan.nextLine().trim();
        if (nuevoDiagnostico.isEmpty()) nuevoDiagnostico = consulta.get(3);

        //Solicita las nuevas notas de la consulta, se puede dejar la actual
        System.out.print("Nuevas notas: ");
        String nuevaNota = scan.nextLine().trim();
        if (nuevaNota.isEmpty()) nuevaNota = consulta.get(4);

        Boolean visibilidad = true;

        // Actualizar consulta
        consultation.actualizarConsulta(r, nuevaFecha, nuevoMotivo, nuevoDiagnostico, nuevaNota, nuevoIdMascota, nuevoIdDoctor, visibilidad);
    }

    /**
     * Desactiva un registro de consulta
     * @param registro número de registro
     */
    public void desactivarConsulta(int registro) {
        consultation.desactivarConsulta(registro);
    }
}

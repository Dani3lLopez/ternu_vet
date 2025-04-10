package src.views;

import src.controllers.DoctorsController;
import src.controllers.PeopleController;
import src.validations.FormatException;
import src.validations.Validations;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Vista: Doctors
 * Contiene los métodos necesarios para administrar a los doctores
 */
public class Doctors {
    // Objeto Scanner para leer los datos ingresados en la consola por los usuarios
    Scanner scan = new Scanner(System.in);
    // Objeto del controlador para gestionar la parte logica de Doctors
    public DoctorsController doc = new DoctorsController();
    public PeopleController person = new PeopleController();

    /**
     * Menú de opciones
     */
    public void doctorMenu() {
        Scanner scan = new Scanner(System.in);
        String separador = "-";

        String choice ="";
        boolean active = true;
        while (active){
            System.out.println("\uD83D\uDC8A Qué haremos hoy?");
            System.out.println("1. Listar Doctores");
            System.out.println("2. Registrar Doctores");
            System.out.println("3. Actualizar Doctores");
            System.out.println("4. Volver al menú principal");
            System.out.println(separador.repeat(50));
            System.out.print("Seleccione una opción: ");
            // Guarda la opción seleccionada por el usuario
            choice = scan.nextLine().trim();
            if(!choice.isEmpty()) {
                try{
                    Validations.validarRangoNumeros(choice, 1, 4);
                    // Se llaman a los métodos correspondientes con base a la opción seleccionada por el usuario.
                    // Si se requieren ciertos parámetros, se solicitan al usuario
                }catch(FormatException e){
                    System.out.println(e.getMessage());
                }

                switch (Integer.parseInt(choice)){
                    case 1:
                        cargarDoctores();
                        break;
                    case 2:
                        registrarDoctor();
                        System.out.println(separador.repeat(50));
                        break;
                    case 3:
                        cargarDoctores();
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
                        if(!r.isEmpty()){
                            actualizarDoctor(Integer.parseInt(r));
                            System.out.println(separador.repeat(50));
                            break;
                        }else{
                            break;
                        }
                    case 4:
                        // Vuelve al menu principal
                        active = false;
                        System.out.println("Cerrando menú...");
                        break;
                }
            }
        }
    }

    /**
     * Carga la lista de doctores
     */
    public void cargarDoctores(){
        // Llama al controlador
        doc.llenarListas();
        List<List<String>> doctores = doc.listaDoctores();
        if (doc.listaDoctores().isEmpty()) {
            System.out.println("No hay doctores registrados.");
        }else {
            // Formato para mostrar los datos
            String separador = "-".repeat(100);
            System.out.println(separador);
            System.out.printf("| %-5s | %-20s | %-20s | %-20s | %-20s |\n", "No.", "Fec. Contratacion", "Fec. Nacimiento", "Nombre", "Especialidad");
            System.out.println(separador);
            //Muestra cada doctor
            int n = 1;
            for (List<String> doctor : doctores) {
                String fechaCont = doctor.get(1);
                String fechaNacimiento = doctor.get(2);
                if (fechaNacimiento == null)
                {
                    fechaNacimiento = "";
                }
                String nombre = doc.capturarNombres(doctor.get(3));
                String especialidad = doc.capturarEspecialidades(doctor.get(4));

                System.out.printf("| %-5d | %-20s | %-20s | %-20s | %-20s |\n", n, fechaCont, fechaNacimiento, nombre, especialidad);
                n++;
            }
            System.out.println(separador);
        }
    }

    /**
     * Registra un nuevo doctor
     */
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

        String valor = "";
        while(true){
            System.out.print("Seleccione al doctor/a *: ");
            valor = scan.nextLine().trim();
            try{
                Validations.validarCampoObligatorio(valor);
                Validations.validarRangoNumeros(valor, 1, person.listaPersonas().size());
                break;
            }catch (FormatException e){
                System.out.println(e.getMessage());
            }
        }

        String id = person.capturarIdLista(Integer.parseInt(valor));
        doc.setIdPersona(id);

        System.out.println(separador);
        System.out.printf("| %-5s | %-50s |\n", "No.", "Especialidad");
        System.out.println(separador);

        for (int c = 0; c < doc.listaEspecialidades().size(); c++) {
            List<String> doctor = doc.listaEspecialidades().get(c);
            System.out.printf("| %-5d | %-50s |\n", (c + 1), doctor.get(1));
        }
        System.out.println(separador);
        String v = "";
        while(true){
            System.out.print("Seleccione la especialidad *: ");
            v = scan.nextLine().trim();
            try{
                Validations.validarCampoObligatorio(v);
                Validations.validarRangoNumeros(v, 1, doc.listaEspecialidades().size());
                break;
            }catch (FormatException e){
                System.out.println(e.getMessage());
            }
        }

        String n = doc.capturarIdListaEspecialidad(Integer.parseInt(v));
        doc.setIdEspecialidad(n);

        String fechaNacimiento = "";
        while(true){
            System.out.print("Fecha de nacimiento: ");
            fechaNacimiento = scan.nextLine().trim();
            if(fechaNacimiento.isEmpty()){
                fechaNacimiento = null;
                break;
            }else{
                try{
                    Validations.validarRangoFechas(fechaNacimiento, LocalDate.now().minusYears(100), LocalDate.now());
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        doc.setFechaNacimientoDoctor(fechaNacimiento);

        String fechaContratacion = "";
        while(true){
            System.out.print("Fecha de contratacion *: ");
            fechaContratacion = scan.nextLine().trim();
            try{
                Validations.validarCampoObligatorio(fechaContratacion);
                Validations.validarRangoFechas(fechaContratacion, LocalDate.now().minusYears(20), LocalDate.now());
                break;
            }catch (FormatException e){
                System.out.println(e.getMessage());
            }
        }
        doc.setFechaContratacionDoctor(fechaContratacion);

        // Llama al controlador para registrar a la persona
        int resultado = doc.RegistrarDoctor();

        if (resultado == 1) {
            System.out.println("Doctor registrado con éxito.");
        } else {
            System.out.println("Ha ocurrido un error al registrar el doctor.");
        }
    }

    /**
     * Actualiza el registro existente de un doctor
     * @param r número de registro
     * Es posible dejar campos en blanco para mantener su valor actual
     */
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

        String valor = "";
        String idPersona = doctor.get(3);
        while(true){
            System.out.print("Seleccione al nuevo doctor/a: ");
            valor = scan.nextLine().trim();
            if(!valor.isEmpty()){
                try{
                    Validations.validarRangoNumeros(valor, 1, person.listaPersonas().size());
                    idPersona = person.capturarIdLista((Integer.parseInt(valor)));
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }else{
                break;
            }
        }

        doc.llenarListas();
        System.out.println("Especialidades:");
        for (int c = 0; c < doc.listaEspecialidades().size(); c++) {
            List<String> especialidad = doc.listaEspecialidades().get(c);
            System.out.println((c + 1) + ". " + especialidad.get(1));
        }

        String ne = "";
        String nide = doctor.get(4);
        while(true){
            System.out.print("Seleccione la nueva especialidad: ");
            ne = scan.nextLine().trim();
            if(!ne.isEmpty()){
                try{
                    Validations.validarRangoNumeros(ne, 1, doc.listaEspecialidades().size());
                    nide = doc.capturarIdListaEspecialidad((Integer.parseInt(ne)));
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }else{
                break;
            }
        }

        String nuevaFechaNacimiento = "";
        while(true){
            System.out.print("Nueva fecha de nacimiento: ");
            nuevaFechaNacimiento = scan.nextLine().trim();
            if(nuevaFechaNacimiento.isEmpty()){
                nuevaFechaNacimiento = doctor.get(2);
                break;
            }else{
                try{
                    Validations.validarRangoFechas(nuevaFechaNacimiento, LocalDate.now().minusYears(100), LocalDate.now());
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        String nuevaFechaContratacion = "";
        while(true){
            System.out.print("Nueva fecha de contratacion: ");
            nuevaFechaContratacion = scan.nextLine().trim();
            if(nuevaFechaContratacion.isEmpty()){
                nuevaFechaContratacion = doctor.get(1);
                break;
            }else{
                try{
                    Validations.validarRangoFechas(nuevaFechaContratacion, LocalDate.now().minusYears(20), LocalDate.now());
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        doc.actualizarDoctor(r, nuevaFechaContratacion, nuevaFechaNacimiento, idPersona, nide);
    }
}

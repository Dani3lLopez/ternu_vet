package src.views;

import src.controllers.OwnersController;
import src.controllers.PeopleController;
import src.validations.FormatException;
import src.validations.Validations;

import java.util.List;
import java.util.Scanner;

/**
 * Vista: Owners
 * Contiene los métodos necesarios para administrar a los propietarios
 */
public class Owners {

    // Clase para leer entradas
    Scanner scan = new Scanner(System.in);
    // Instancia del controlador de propietarios
    public OwnersController owner = new OwnersController();
    // Instancia del controlador de personas
    public PeopleController person = new PeopleController();

    /**
     * Menú con opciones para administrar los datos
     */
    public void ownerMenu() {
        Scanner scan = new Scanner(System.in);
        String separador = "-";

        boolean active = true;
        while (active){
            System.out.println("\uD83E\uDDB4 Qué haremos hoy?");
            System.out.println("1. Listar Propietarios");
            System.out.println("2. Registrar Propietarios");
            System.out.println("3. Actualizar Propietarios");
            System.out.println("4. Desactivar Propietarios");
            System.out.println("5. Volver al menú principal");
            System.out.println(separador.repeat(50));
            System.out.print("Seleccione una opción: ");
            String choice = scan.nextLine().trim();

            if(!choice.isEmpty()) {
                try{
                    Validations.validarRangoNumeros(choice, 1, 5);
                    // Se llaman a los metodos respectivos a la opcion seleccioanda
                    // Si se necesita mas informacion, se solicita y guarda
                    switch (Integer.parseInt(choice)){
                        case 1:
                            cargarPropietarios();
                            break;
                        case 2:
                            registrarPropietario();
                            System.out.println("-".repeat(50));
                            break;
                        case 3:
                            cargarPropietarios();
                            String r = "";
                            while(true){
                                System.out.print("Ingrese el número de registro a actualizar: ");
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
                                actualizarPropietario(Integer.parseInt(r));
                                System.out.println("-".repeat(50));
                                break;
                            }
                        case 4:
                            cargarPropietarios();
                            String registro = "";
                            while(true){
                                System.out.print("Ingrese el número de registro a eliminar: ");
                                registro = scan.nextLine().trim();
                                try{
                                    Validations.validarNumeros(registro);
                                    break;
                                }catch (FormatException e){
                                    System.out.println(e.getMessage());
                                }
                            }
                            if(registro.isEmpty()){
                                break;
                            }else{
                                desactivarPropietario(Integer.parseInt(registro));
                                System.out.println("-".repeat(50));
                                break;
                            }
                        case 5:
                            active = false;
                            System.out.println("Cerrando menú...");
                            break;
                        default:
                            System.out.println("El valor ingresado no corresponde a una opción de menú");
                    }
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * Carga la lista de propietarios obtenida de la base de datos
     */
    public void cargarPropietarios(){
        owner.llenarListas();
        List<List<String>> propietarios = owner.listaPropietarios();
        if (owner.listaPropietarios().isEmpty()) {
            System.out.println("No hay propietarios registrados.");
        }else {
            String separador = "-".repeat(70);
            System.out.println(separador);
            System.out.printf("| %-5s | %-20s | %-20s | %-20s |\n", "No.", "Nombre", "Ciudad", "Dirección");
            System.out.println(separador);

            int n = 1;
            // Itera y muestra cada propietario
            for (List<String> propietario : propietarios) {
                String nombre = owner.capturarNombres(propietario.get(1));
                String ciudad = owner.capturarCiudad(propietario.get(2));
                String direccion = propietario.get(3);
                if (direccion == null)
                {
                    direccion = "";
                }

                System.out.printf("| %-5d | %-20s | %-20s | %-20s |\n", n, nombre, ciudad, direccion);
                n++;
            }
        }
    }

    /**
     * Registra un nuevo propietario en la base de datos
     */
    public void registrarPropietario() {
        person.cargarListaPersonas(); //carga la lista de personas
        owner.llenarListas(); //llenamos las listas

        String separador = "-".repeat(70);
        System.out.println(separador);
        System.out.printf("| %-5s | %-50s |\n", "No.", "Nombre"); //formato de tabla
        System.out.println(separador);
        int r = 1;
        for (int p = 0; p < person.listaPersonas().size(); p++) {
            List<String> persona = person.listaPersonas().get(p);

            System.out.printf("| %-5d | %-50s |\n", r, persona.get(1) + " " + persona.get(2)); //llenamos la tabla
            r++;
        }
        System.out.println(separador);

        String valor = "";
        while(true){
            System.out.print("Seleccione a la persona propietaria *: ");
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
        owner.setIdPersona(id);

        System.out.println(separador);
        System.out.printf("| %-5s | %-50s |\n", "No.", "Ciudad");
        System.out.println(separador);

        for (int c = 0; c < owner.listaCiudades().size(); c++) {
            List<String> ciudad = owner.listaCiudades().get(c);
            System.out.printf("| %-5d | %-50s |\n", (c + 1), ciudad.get(1));
        }
        System.out.println(separador);

        String v = "";
        while(true){
            System.out.print("Seleccione su ciudad: ");
            v = scan.nextLine().trim();
            if(v.isEmpty()){
                break;
            }else{
                try{
                    Validations.validarRangoNumeros(v, 1, owner.listaCiudades().size());
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        String n = "";
        if(v.isEmpty()){
            n = null;
        }else{
            n = owner.capturarIdListaCiudad(Integer.parseInt(v));
        }
        owner.setIdCiudad(n);

        System.out.print("Dirección: ");
        String direccion = scan.nextLine().trim();
        owner.setDireccion(direccion);

        int resultado = owner.RegistrarPropietario();

        if (resultado == 1) {
            System.out.println("Propietario registrado con éxito.");
        } else {
            System.out.println("Ha ocurrido un error al registrar el propietario.");
        }
    }

    /**
     * Actualiza un propietario existente
     * @param r número de registro
     * Es posible dejar campos en blanco para mantener su valor actual
     */
    public void actualizarPropietario(int r) {
        List<String> propietario = owner.cargarDatosPropietario(r);
        if (propietario.isEmpty()) {
            System.out.println("No se encontró el registro especificado");
            return;
        }

        String id = propietario.get(0);
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

        String np = "";
        String nid = "";

        while(true){
            System.out.print("Seleccione a la nueva persona propietaria: ");
            np = scan.nextLine().trim();
            if(!np.isEmpty()){
                try{
                    Validations.validarRangoNumeros(np, 1, person.listaPersonas().size());
                    nid = person.capturarIdLista(Integer.parseInt(np));
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }else{
                nid = propietario.get(1);
                break;
            }
        }

        // muestra la lista de ciudades disponibles
        owner.llenarListas();
        System.out.println("Ciudades Disponibles:");
        for (int c = 0; c < owner.listaCiudades().size(); c++) {
            List<String> ciudad = owner.listaCiudades().get(c);
            System.out.println((c + 1) + ". " + ciudad.get(1));
        }

        String nc = "";
        String nidc = "";

        while(true){
            System.out.print("Nueva ciudad: ");
            nc = scan.nextLine().trim();
            if(!nc.isEmpty()){
                try{
                    Validations.validarRangoNumeros(nc, 1, owner.listaCiudades().size());
                    nidc = owner.capturarIdListaCiudad(Integer.parseInt(nc));
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }else{
                nidc = propietario.get(2);
                break;
            }
        }

        System.out.print("Nueva dirección: ");
        String nuevaDireccion = scan.nextLine();
        if (nuevaDireccion.isEmpty()) {
            nuevaDireccion = propietario.get(3);
        }
        boolean visibilidad = true;
        owner.actualizarPropietario(r, nid, nidc, nuevaDireccion, visibilidad);
    }

    /**
     * Desactiva el registro de un propietario
     * @param registro número de registro
     */
    public void desactivarPropietario(int registro) {
        owner.desactivarPropietario(registro);
    }
}

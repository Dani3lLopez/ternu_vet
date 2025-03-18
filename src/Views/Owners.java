package src.Views;

import src.Controllers.OwnersController;
import src.Controllers.PeopleController;

import java.util.List;
import java.util.Scanner;

/*
 * Vista de propetiarios: gestiona la interaccion con el usuario
 * Se basa en operaciones de un menu para registrar, actualizar, desactivar propietarios
 */
public class Owners {

    // Clase para leer entradas
    Scanner scan = new Scanner(System.in);
    // Instancia del controlador de propietarios
    public OwnersController owner = new OwnersController();
    // Instancia del controlador de personas
    public PeopleController person = new PeopleController();

    /*
     * Muestra el menu principal y gestiona la opcion que se seleccione
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
            int choice = scan.nextInt();

            // Se llaman a los metodos respectivos a la opcion seleccioanda
            // Si se necesita mas informacion, se solicita y guarda
            switch (choice){
                case 1:
                    cargarPropietarios();
                    System.out.println(separador.repeat(70));
                    break;
                case 2:
                    registrarPropietario();
                    break;
                case 3:
                    cargarPropietarios();
                    System.out.print("Ingrese el número de registro a actualizar: ");
                    int r = scan.nextInt();
                    scan.nextLine();
                    actualizarPropietario(r);
                    break;
                case 4:
                    cargarPropietarios();
                    System.out.print("Ingrese el número de registro a eliminar: ");
                    int registro = scan.nextInt();
                    scan.nextLine();
                    desactivarPropietario(registro);
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
     * Carga y muestra la lista de propietarios en formato de tabla
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

                System.out.printf("| %-5d | %-20s | %-20s | %-20s |\n", n, nombre, ciudad, direccion);
                n++;
            }
        }
    }
    /*
     * Registra a un nuevo propietario
     * Muetsra primero la lista de personas disponibles para seleccionar al propietario
     * Luego, muestra la lista de ciudades para elgeir una
     * Por ultimo, se solicita la direccion
     */
    public void registrarPropietario() {
        person.cargarListaPersonas();
        owner.llenarListas();

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

        System.out.print("Seleccione a la persona propietaria: ");
        int valor = scan.nextInt();
        scan.nextLine();

        if (valor > 0 && valor <= person.listaPersonas().size()) {
            String id = person.capturarIdLista(valor);
            owner.setIdPersona(id);

            System.out.println(separador);
            System.out.printf("| %-5s | %-50s |\n", "No.", "Ciudad");
            System.out.println(separador);

            for (int c = 0; c < owner.listaCiudades().size(); c++) {
                List<String> ciudad = owner.listaCiudades().get(c);
                System.out.printf("| %-5d | %-50s |\n", (c + 1), ciudad.get(1));
            }
            System.out.println(separador);

            System.out.print("Seleccione su ciudad: ");
            int v = scan.nextInt();
            scan.nextLine();

            if (v > 0 && v <= owner.listaCiudades().size()) {
                String n = owner.capturarIdListaCiudad(v);
                owner.setIdCiudad(n);

                System.out.println("Dirección: ");
                String direccion = scan.nextLine();
                owner.setDireccion(direccion);

                int resultado = owner.RegistrarPropietario();

                if (resultado == 1) {
                    System.out.println("Propietario registrado con éxito.");
                } else {
                    System.out.println("Ha ocurrido un error al registrar el propietario.");
                }
            } else {
                System.out.println("Selección de persona inválida.");
            }
        }
    }
    /*
     * Actualizar los datos de un propietario que existe
     * El parametro es el idncie del registro a actualizar
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

        System.out.print("Nuevo propietario: ");
        String np = scan.nextLine();
        String nid = propietario.get(1);
        if (!np.isEmpty()) {
            int idNuevo = Integer.parseInt(np);
            if (idNuevo > 0 && idNuevo <= person.listaPersonas().size()) {
                nid = person.capturarIdLista(idNuevo);
            } else {
                System.out.println("Persona no valida.");
            }
        }

        // muestra la lsita de ciudades disponibles
        owner.llenarListas();
        System.out.println("Ciudades Disponibles:");
        for (int c = 0; c < owner.listaCiudades().size(); c++) {
            List<String> ciudad = owner.listaCiudades().get(c);
            System.out.println((c + 1) + ". " + ciudad.get(1) + " " + ciudad.get(2));
        }

        System.out.print("Nueva ciudad: ");
        String nc = scan.nextLine();
        String nidc = propietario.get(2);
        if (!nc.isEmpty()) {
            int idNuevo = Integer.parseInt(nc);
            if (idNuevo > 0 && idNuevo <= owner.listaCiudades().size()) {
                nidc = owner.capturarIdListaCiudad(idNuevo);
            } else {
                System.out.println("Ciudad no valida.");
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
    /*
     * Desactiva un propietario
     */
    public void desactivarPropietario(int registro) {
        owner.desactivarPropietario(registro);
    }
}

package src.Views;

import src.Controllers.UsersController;

import java.util.List;
import java.util.Scanner;

public class Users {
    Scanner scan = new Scanner(System.in);
    public UsersController user = new UsersController();

    // Hicimos un método principal para el menú de usuarios
    public void userMenu() {
        Scanner scan = new Scanner(System.in);
        String separador = "-";

        boolean active = true;

        // Bucle que contiene todas las funciones y opciones principales del menú
        while (active) {
            System.out.println("\uD83D\uDC64 Qué haremos hoy?");
            System.out.println("1. Listar Usuarios");
            System.out.println("2. Registrar Usuarios");
            System.out.println("3. Actualizar Usuarios");
            System.out.println("4. Desactivar Usuarios");
            System.out.println("5. Volver al menú principal");
            System.out.println(separador.repeat(50));
            System.out.print("Seleccione una opción: ");
            int choice = scan.nextInt();

            switch (choice) {
                case 1:

                    // Carga los usuarios
                    cargarUsuarios();
                    System.out.println(separador.repeat(70));
                    break;
                case 2:

                    // Registra al usuario nuevo
                    registrarUsuarios();
                    break;
                case 3:

                    // Carga los usuarios y solicita el número de registro que se quiere actualizar
                    cargarUsuarios();
                    System.out.print("Ingrese el número de registro a actualizar: ");
                    int r = scan.nextInt();
                    scan.nextLine();

                    // Actualiza el usuario
                    actualizarUsuario(r);
                    break;
                case 4:

                    // Carga los usuarios y pide el número de registro que se quiere desactivar
                    cargarUsuarios();
                    System.out.print("Ingrese el número de registro que desea desactivar: ");
                    int registro = scan.nextInt();
                    scan.nextLine();

                    // Desactiva el usuario
                    desactivarUsuario(registro);
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

    // Hicimos otro método para cargar y mostrar la lista de usuarios
    public void cargarUsuarios() {
        user.llenarListas();
        List<List<String>> usuarios = user.listaUsuarios();
        if (user.listaUsuarios().isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            String separador = "-".repeat(85);
            System.out.println(separador);
            System.out.printf("| %-5s | %-20s | %-20s | %-20s |\n", "No.", "Usuario", "Estado", "Doctor");
            System.out.println(separador);

            int n = 1;
            for (List<String> usuario : usuarios) {
                String nombre = usuario.get(1);
                String estado = usuario.get(3);
                String idDoctor = usuario.get(5);
                String idPersonaDoctor = "";

                // Dado el usuario, busca el doctor
                for (List<String> doctor : user.listaDoctores()) {
                    if (doctor.get(0).equalsIgnoreCase(idDoctor)) {
                        idPersonaDoctor = doctor.get(1);
                        break;
                    }
                }

                // Obtiene el nombre del doctor
                String doctorNombre = user.capturarNombres(idPersonaDoctor);

                // Imprime la fila correspondiente
                System.out.printf("| %-5d | %-20s | %-20s | %-20s|\n", n, nombre, estado, doctorNombre);
                n++;
            }
            System.out.println(separador);
        }
    }

    // Creamos un método para registrar los nuevos usuarios
    public void registrarUsuarios() {
        user.llenarListas();

        String separador = "-".repeat(70);
        System.out.println(separador);
        System.out.printf("| %-5s | %-50s |\n", "No.", "Doctor");
        System.out.println(separador);

        // Obtiene la lista de doctores sin usuario
        List<List<String>> doctores = user.listaDoctoresSinUsuario();

        // Se asegura que todos los doctores tengan usuario
        if (doctores.isEmpty()) {
            System.out.println("Todos los doctores poseen un usuario.");
            System.out.println(separador);
            userMenu();
            return;
        }

        // Muestra los doctores que no tienen usuairo
        int r = 1;
        for (List<String> doctor : doctores) {
            String idp = doctor.get(1);
            String nombreDoctor = user.capturarNombres(idp);

            System.out.printf("| %-5d | %-50s |\n", r, nombreDoctor);
            r++;
        }
        System.out.println(separador);

        // Solicita que se seleccione un doctor
        System.out.print("Seleccione al usuario: ");
        int valor = scan.nextInt();
        scan.nextLine();

        // Comprueba que lo ingresado sea válido
        if (valor > 0 && valor <= doctores.size()) {
            String idDoctor = doctores.get(valor - 1).get(0);
            user.setIdDoctor(idDoctor);

            System.out.println("Nombre de usuario: ");
            String nombre = scan.nextLine();
            if (user.existenciaUsuario(nombre)) {
                System.out.println("El usuario ya existe.");
                return;
            }
            user.setNombreUsuario(nombre);
            System.out.println("Clave: ");
            String clave = scan.nextLine();
            user.setClaveUsuario(clave);
            String estado = "Activo";
            user.setEstadoUsuario(estado);

            // Evalua si el usuario es administrador o no
            List<List<String>> usuarios = user.listaUsuarios();
            boolean admin = usuarios.isEmpty();
            user.setAdministrador(admin);

            int resultado = user.RegistrarUsuario();

            if (resultado == 1) {
                System.out.println("Usuario registrado con éxito.");

            } else {
                System.out.println("Ha ocurrido un error al registrar el usuario.");
            }
        } else {
            System.out.println("Selección de persona inválida.");
        }
    }

    // Hicimos otro método para poder actualizar un usuario existente
    public void actualizarUsuario(int r) {
        String idUsuario = user.capturarIdListaUsuario(r);
        if (idUsuario == null) {
            System.out.println("Registro extraño");
            return;
        }

        // Carga la información del usuario
        List<String> usuario = user.cargarDatosUsuario(r);
        if (usuario.isEmpty()) {
            System.out.println("No se encontró el registro especificado");
            return;
        }

        // Ontiene la información actual
        String nombreUsuarioActual = usuario.get(1);
        String claveUsuarioActual = usuario.get(2);
        String idDoctorActual = usuario.get(5);

        System.out.print("Nuevo nombre de usuario: ");
        String nuevoNombreUsuario = scan.nextLine();
        if (nuevoNombreUsuario.isEmpty()) {
            nuevoNombreUsuario = nombreUsuarioActual;
        }

        System.out.print("Nueva clave: ");
        String nuevaClaveUsuario = scan.nextLine();
        if (nuevaClaveUsuario.isEmpty()) {
            nuevaClaveUsuario = claveUsuarioActual;
        }

        System.out.print("¿Es administrador? (1/0): ");
        int administrador = scan.nextInt();
        scan.nextLine();

        System.out.print("Estado (Activo/Inactivo): ");
        String estado = scan.nextLine();

        // Actualiza la informaxión cambiada
        user.actualizarUsuario(r, nuevoNombreUsuario, nuevaClaveUsuario, estado, administrador, idDoctorActual);
    }

    // Y por último otro método para desactivar el usuario
    public void desactivarUsuario(int registro) {
        user.desactivarUsuario(registro);
    }
}

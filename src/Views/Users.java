package src.Views;

import src.Controllers.PeopleController;
import src.Controllers.UsersController;

import java.util.List;
import java.util.Scanner;

public class Users {
    Scanner scan = new Scanner(System.in);
    public UsersController user = new UsersController();
    public PeopleController person = new PeopleController();

    public void userMenu() {
        Scanner scan = new Scanner(System.in);
        String separador = "-";

        boolean active = true;
        while (active) {
            System.out.println("\uD83D\uDC36 Qué haremos hoy?");
            System.out.println("1. Listar Usuarios");
            System.out.println("2. Registrar Usuarios");
            System.out.println("3. Actualizar Usuarios");
            System.out.println("4. Desactivar Usuarios");
            System.out.println("5. Volver al menú principal");
            System.out.println(separador.repeat(50));
            System.out.print("Seleccione una opción: ");
            int choice = scan.nextInt();
            Users actual = new Users();

            switch (choice) {
                case 1:
                    actual.cargarUsuarios();
                    System.out.println(separador.repeat(70));
                    break;
                case 2:
                    actual.registrarUsuarios();
                    break;
                case 3:
                    actual.cargarUsuarios();
                    System.out.print("Ingrese el número de registro a actualizar: ");
                    int r = scan.nextInt();
                    scan.nextLine();
                    actual.actualizarUsuario(r);
                    break;
                case 4:
                    //actual.cargarPropietarios();
                    System.out.print("Ingrese el número de registro a eliminar: ");
                    int registro = scan.nextInt();
                    scan.nextLine();
                    //actual.desactivarPropietario(registro);
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

                for (List<String> doctor : user.listaDoctores()) {
                    if (doctor.get(0).equalsIgnoreCase(idDoctor)) {
                        idPersonaDoctor = doctor.get(1);
                        break;
                    }
                }
                String doctorNombre = user.capturarNombres(idPersonaDoctor);

                System.out.printf("| %-5d | %-20s | %-20s | %-20s|\n", n, nombre, estado, doctorNombre);
                n++;
            }
            System.out.println(separador);
        }
    }

    public void registrarUsuarios() {
        user.llenarListas();

        String separador = "-".repeat(70);
        System.out.println(separador);
        System.out.printf("| %-5s | %-50s |\n", "No.", "Doctor");
        System.out.println(separador);

        List<List<String>> doctores = user.listaDoctoresSinUsuario();

        if (doctores.isEmpty()) {
            System.out.println("Todos los doctores poseen un usuario.");
            System.out.println(separador);
            userMenu();
            return;
        }

        int r = 1;
        for (List<String> doctor : doctores) {
            String idp = doctor.get(1);
            String nombreDoctor = user.capturarNombres(idp);

            System.out.printf("| %-5d | %-50s |\n", r, nombreDoctor);
            r++;
        }
        System.out.println(separador);

        System.out.print("Seleccione al usuario: ");
        int valor = scan.nextInt();
        scan.nextLine();

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

    public void actualizarUsuario(int r) {
        String idUsuario = user.capturarIdListaUsuario(r);
        System.out.println(idUsuario);
        if (idUsuario == null) {
            System.out.println("Registro extraño");
            return;
        }

        List<String> usuario = user.cargarDatosUsuario(r);
        if (usuario.isEmpty()) {
            System.out.println("No se encontró el registro especificado");
            return;
        }

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

        user.actualizarUsuario(r, nuevoNombreUsuario, nuevaClaveUsuario, estado, administrador, idDoctorActual);
    }
}

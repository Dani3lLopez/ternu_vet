package src.views;

import src.controllers.UsersController;
import src.validations.FormatException;
import src.validations.Validations;

import java.util.List;
import java.util.Scanner;

/**
 * Vista: Users
 * Contiene los métodos necesarios para administrar los usuarios
 */
public class Users {
    Scanner scan = new Scanner(System.in);
    public UsersController user = new UsersController();

    /**
     * Menú de opciones para administrar los datos
     */
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
            String choice = scan.nextLine().trim();
            if(!choice.isEmpty()) {
                try{
                    Validations.validarRangoNumeros(choice, 1, 5);
                    switch (Integer.parseInt(choice)) {
                        case 1:
                            // Carga los usuarios
                            cargarUsuarios();
                            break;
                        case 2:
                            // Registra al usuario nuevo
                            registrarUsuarios();
                            System.out.println(separador.repeat(50));
                            break;
                        case 3:
                            // Carga los usuarios y solicita el número de registro que se quiere actualizar
                            cargarUsuarios();
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
                                // Actualiza el usuario
                                actualizarUsuario(Integer.parseInt(r));
                                System.out.println(separador.repeat(50));
                                break;
                            }
                        case 4:
                            // Carga los usuarios y pide el número de registro que se quiere desactivar
                            cargarUsuarios();
                            String registro = "";
                            while(true){
                                System.out.print("Ingrese el número de registro que desea desactivar: ");
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
                                // Desactiva el usuario
                                desactivarUsuario(Integer.parseInt(registro));
                                System.out.println(separador.repeat(50));
                                break;
                            }
                        case 5:
                            active = false;
                            System.out.println("Cerrando menú...");
                            break;
                    }
                }catch (FormatException e){
                    e.getMessage();
                }
            }
        }
    }

    /**
     * Carga todos los usuarios de la base de datos
     */
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

    /**
     * Registra nuevos usuarios en la base de datos
     */
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
        String valor = "";
        while(true){
            System.out.print("Seleccione al usuario *: ");
            valor = scan.nextLine().trim();
            try{
                Validations.validarCampoObligatorio(valor);
                Validations.validarRangoNumeros(valor, 1, doctores.size());
                break;
            }catch (FormatException e){
                System.out.println(e.getMessage());
            }
        }

        String idDoctor = doctores.get(Integer.parseInt(valor) - 1).get(0);
        user.setIdDoctor(idDoctor);

        String nombre = "";
        while(true){
            System.out.print("Nombre de usuario *: ");
            nombre = scan.nextLine().trim();
            try{
                Validations.validarCampoObligatorio(nombre);
                break;
            }catch (FormatException e){
                System.out.println(e.getMessage());
            }
        }
        if (user.existenciaUsuario(nombre)) {
            System.out.println("El usuario ya existe.");
            return;
        }
        user.setNombreUsuario(nombre);

        String contra = "";
        while(true){
            System.out.print("Clave *: ");
            contra = scan.nextLine().trim();
            try{
                Validations.validarCampoObligatorio(contra);
                contra = Validations.encriptarContra(contra);
                break;
            }catch (FormatException e){
                System.out.println(e.getMessage());
            }
        }
        user.setClaveUsuario(contra);
        String estado = "Activo";
        user.setEstadoUsuario(estado);

        // Evalúa si el usuario es administrador o no
        List<List<String>> usuarios = user.listaUsuarios();
        boolean admin = usuarios.isEmpty();
        user.setAdministrador(admin);

        int resultado = user.RegistrarUsuario();

        if (resultado == 1) {
            System.out.println("Usuario registrado con éxito.");

        } else {
            System.out.println("Ha ocurrido un error al registrar el usuario.");
        }
    }

    /**
     * Actualiza un registro existente de usuario
     * @param r número de registro
     * Es posible dejar campos en blanco para mantener su valor actual
     */
    public void actualizarUsuario(int r) {
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
        int administradorActual = Integer.parseInt(usuario.get(4));
        String estadoActual = usuario.get(3);

        System.out.print("Nuevo nombre de usuario: ");
        String nuevoNombreUsuario = scan.nextLine().trim();
        if(nuevoNombreUsuario.isEmpty()){
            nuevoNombreUsuario = nombreUsuarioActual;
        }

        System.out.print("Nueva clave: ");
        String nuevaClaveUsuario = scan.nextLine();
        if (nuevaClaveUsuario.isEmpty()) {
            nuevaClaveUsuario = claveUsuarioActual;
        }else{
            nuevaClaveUsuario = Validations.encriptarContra(nuevaClaveUsuario);
        }

        System.out.println("¿Es administrador?");
        System.out.println("1. Sí");
        System.out.println("2. No");
        int administrador = 0;
        while(true){
            System.out.print("Opción seleccionada: ");
            String opcion = scan.nextLine().trim();
            if(opcion.isEmpty()){
                administrador = administradorActual;
                break;
            }else{
                try{
                    Validations.validarRangoNumeros(opcion, 1, 2);
                    administrador = Integer.parseInt(opcion);
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println("Estado del usuario");
        System.out.println("1. Activo");
        System.out.println("2. Inactivo");
        String estado;
        while(true){
            System.out.print("Opcion seleccionada: ");
            String opcion = scan.nextLine().trim();
            if(opcion.isEmpty()){
                estado = estadoActual;
                break;
            }else{
                try{
                    Validations.validarRangoNumeros(opcion, 1, 2);
                    if(Integer.parseInt(opcion) == 1){
                        estado = "Activo";
                    }else{
                        estado = "Inactivo";
                    }
                    break;
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        // Actualiza la informaxión cambiada
        user.actualizarUsuario(r, nuevoNombreUsuario, nuevaClaveUsuario, estado, administrador, idDoctorActual);
    }

    /**
     * Desactiva el registro de un usuario
     * @param registro número de registro
     */
    public void desactivarUsuario(int registro) {
        user.desactivarUsuario(registro);
    }
}

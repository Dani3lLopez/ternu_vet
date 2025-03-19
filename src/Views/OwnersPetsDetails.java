package src.Views;

import src.Controllers.OwnersPetsDetailsController;

import java.util.List;
import java.util.Scanner;

/*
 * Vista de detalles de propietarios con mascotas
 *  Interactua con el usuario para gestionar el detalles de propietarios con mascotas
 * Permite listar, registrar, actualizar y eliminar registros
 * Se comunica con el controlador OwnersPetsDetailsController para realizar las opciones
 */
public class OwnersPetsDetails {
    Scanner scan = new Scanner(System.in);
    public OwnersPetsDetailsController detail = new OwnersPetsDetailsController();

    /*
     * Muestra el menu
     */
    public void detailMenu() {
        Scanner scan = new Scanner(System.in);
        String separador = "-";
        boolean active = true;
        while (active) {
            System.out.println("\uD83D\uDC36 Qué haremos hoy?");
            System.out.println("1. Listar Detalles");
            System.out.println("2. Registrar Detalle");
            System.out.println("3. Actualizar Detalle");
            System.out.println("4. Eliminar Detalle");
            System.out.println("5. Volver al menú principal");
            System.out.println(separador.repeat(50));
            System.out.print("Seleccione una opción: ");
            int choice = scan.nextInt();

            // Se llama el metodo con base a la seleccion. Si se requieren mas parametros, se solicitan
            switch (choice) {
                case 1:
                    cargarDetalles();
                    System.out.println(separador.repeat(70));
                    break;
                case 2:
                    //registrarConsulta();
                    break;
                case 3:
                    cargarDetalles();
                    System.out.print("Ingrese el número de registro a actualizar: ");
                    int r = scan.nextInt();
                    scan.nextLine();
                    //actualizarConsulta(r);
                    break;
                case 4:
                    cargarDetalles();
                    System.out.print("Ingrese el número de registro a eliminar: ");
                    int registro = scan.nextInt();
                    scan.nextLine();
                    //desactivarConsulta(registro);
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
     * Carga y muestra la lista de detalles en formato de tabla
     * Se obtienen las listas de detalles, mascotas y propietarios a traves del controlador
     * Se muestra la informacion por columnas
     */
    public void cargarDetalles() {
        detail.llenarListas();
        List<List<String>> detalles = detail.listaDetalles();
        if (detail.listaDetalles().isEmpty()) {
            System.out.println("No hay detalles registrados.");
        } else {
            String separador = "-".repeat(100);
            System.out.println(separador);
            System.out.printf("| %-5s | %-20s | %-20s | %-20s |\n", "No.", "Propietario", "Tipo", "Mascota");
            System.out.println(separador);

            int n = 1;
            for (List<String> detalle : detalles) {
                String tipo = detalle.get(1);
                String mascota = detail.capturarMascotas(detalle.get(2));
                String idPropietario = detalle.get(3);
                String idPersonaPropietario = "";
                for (List<String> propietario : detail.listaPropietarios()) {
                    if (propietario.get(0).equalsIgnoreCase(idPropietario)) {
                        idPersonaPropietario = propietario.get(1);
                        break;
                    }
                }
                String propietario = detail.capturarNombresPropietarios(idPersonaPropietario);

                System.out.printf("| %-5d | %-20s | %-20s | %-20s |\n", n, propietario, tipo, mascota);
                n++;
            }
            System.out.println(separador);
        }
    }
}

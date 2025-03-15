package src.app;

import src.Views.Doctors;
import src.Views.Owners;
import src.Views.People;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String separador = "-".repeat(50);

        boolean active = true;
        while (active) {
            System.out.println("\uD83D\uDC36 Escoge una opción de menú para navegar");
            System.out.println("1. Personas");
            System.out.println("2. Propietarios");
            System.out.println("3. Doctores");
            System.out.println("4. Salir");
            System.out.println(separador);
            System.out.print("Seleccione una opción: ");
            int choice = scan.nextInt();
            scan.nextLine();
            System.out.println(separador);

            switch (choice) {
                case 1:
                    People ppl = new People();
                    ppl.peopleMenu();
                    break;
                case 2:
                    Owners owner = new Owners();
                    owner.ownerMenu();
                    break;
                case 3:
                    Doctors doctor = new Doctors();
                    doctor.doctorMenu();
                    break;
                case 4:
                    active = false;
                    System.out.println("Cerrando el programa...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}

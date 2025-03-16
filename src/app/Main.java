package src.app;

import src.Views.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String separador = "-".repeat(50);

        boolean active = true;
        while (active) {
            System.out.println(separador);
            System.out.println("\uD83D\uDC3E ¡Bienvenido/a! Selecciona una opción para continuar \uD83D\uDC3E");
            System.out.println("1. Personas \uD83E\uDDD1 ");
            System.out.println("2. Propietarios \uD83E\uDDB4");
            System.out.println("3. Doctores \uD83D\uDC8A");
            System.out.println("4. Especialidades \uD83E\uDE7A");
            System.out.println("5. Usuarios \uD83D\uDC64");
            System.out.println("6. Facturas \uD83D\uDCCB");
            System.out.println("7. Mascotas \uD83D\uDC36");
            System.out.println("8. Productos \uD83E\uDD4E");
            System.out.println("9. Servicios \uD83D\uDEC1");
            System.out.println("10. Citas \uD83D\uDCC5");
            System.out.println("❌ Cualquier otra opción para salir ❌");
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
                    Specialties especialty = new Specialties();
                    especialty.specialtiesMenu();
                    break;
                case 5:
                    Users user = new Users();
                    user.userMenu();
                    break;
                case 6:
                    Invoices invoice = new Invoices();
                    invoice.invoiceMenu();
                    break;
                case 7:
                    Pets pet = new Pets();
                    pet.petMenu();
                    break;
                case 10:
                    Appointments appointment = new Appointments();
                    appointment.appointmentMenu();
                    break;
                default:
                    active = false;
                    System.out.println("Cerrando el programa...");
                    break;
            }
        }
    }
}

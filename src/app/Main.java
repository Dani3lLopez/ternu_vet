package src.app;

import src.validations.FormatException;
import src.validations.Validations;
import src.views.*;

import java.util.Scanner;

/**
 * Clase: Main
 * Contiene el menú principal de opciones para navegar en el sistema
 */
public class Main {
    public static void main(String[] args) {
        //Scanner para manejar las opciones ingresadas por el usuario
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
            System.out.println("11. Consultas \uD83E\uDD15");
            System.out.println("12. Detalles-Propietarios-Mascotas \u2764\uFE0F");
            System.out.println("13. Detalles-Facturas \uD83E\uDDFE");
            System.out.println("0. Para salir del programa ❌");
            System.out.println(separador);
            System.out.print("Seleccione una opción: ");
            String choice = scan.nextLine().trim();
            if(!choice.isEmpty()) {
                try{
                    Validations.validarNumeros(choice);
                    //Dependiendo de su elección se ejecuta el menú de la opción seleccionada
                    switch (Integer.parseInt(choice)) {
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
                            Specialties specialty = new Specialties();
                            specialty.specialtiesMenu();
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
                        case 8:
                            Products product = new Products();
                            product.productMenu();
                            break;
                        case 9:
                            Services service = new Services();
                            service.serviceMenu();
                            break;
                        case 10:
                            Appointments appointment = new Appointments();
                            appointment.appointmentMenu();
                            break;
                        case 11:
                            Consultations consultation = new Consultations();
                            consultation.consultationMenu();
                            break;
                        case 12:
                            OwnersPetsDetails detail = new OwnersPetsDetails();
                            detail.detailMenu();
                            break;
                        case 13:
                            InvoicesDetails invoiceDetail = new InvoicesDetails();
                            invoiceDetail.invoiceDetailMenu();
                            break;
                        case 0:
                            //Se cierra el programa
                            active = false;
                            System.out.println("Cerrando el programa...");
                            break;
                    }
                }catch (FormatException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}

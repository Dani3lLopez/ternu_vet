package src.validations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Base64;

public class Validations {
    // Verifica que lo ingresado sean solo números
    public static void validarNumeros(String input) {
        int conteoLetras = 0;
        for (int i = 0; i < input.length(); i++) {

            // Verifica si el carácter es una letra
            if (!Character.isDigit(input.charAt(i))) {
                conteoLetras++;
            }
        }

        // Si no encuentra letras, comprueba que solo hay números y establece la
        // varaible como verdadera
        if (conteoLetras != 0) {
            throw new FormatException("Entrada inválida: solo se permiten números enteros positivos.");
        }
    }

    public static void validarDecimales(String input) {
        int conteoLetras = 0;
        for (int i = 0; i < input.length(); i++) {

            // Verifica si el carácter es una letra
            if (!Character.isDigit(input.charAt(i)) && input.charAt(i) != '.') {
                conteoLetras++;
            }
        }

        // Si no encuentra letras, comprueba que solo hay números y establece la
        // varaible como verdadera
        if (conteoLetras != 0) {
            throw new FormatException("Entrada inválida: solo se permiten números decimales positivos.");
        }
    }

    public static void validarRangoNumeros(String input, int min, int max) {
        validarNumeros(input);
        if (Integer.parseInt(input) < min || Integer.parseInt(input) > max) {
            throw new FormatException("Entrada inválida: el valor ingresado está fuera del rango permitido (" + min + " - " + max + ").");
        }
    }

    public static void validarCampoObligatorio(String input) {
        if(input == null || input.isEmpty()) {
            throw new FormatException("Entrada inválida: el campo es obligatorio.");
        }
    }

    // Nos permite validar si los datos ingresados representan una fecha en formato
    // "YYYY-MM-DD"
    public static void validarFecha(String fecha) {
        boolean validarFecha = true;

        // Comprueba que la longitud de los datos ingresados sea 10
        if (fecha.length() == 10) {

            // Verifica que los caracteres en las posiciones 4 y 7 sean guiones
            if (fecha.charAt(4) != '-' || fecha.charAt(7) != '-') {
                validarFecha = false;
            }
            int conteoNoValidosAnio = 0;
            int conteoNoValidosMes = 0;
            int conteoNoValidosDia = 0;

            // Verifica que los primeros 4 caracteres sean números
            for (int i = 0; i < 4; i++) {
                if (!Character.isDigit(fecha.charAt(i))) {
                    conteoNoValidosAnio++;
                }
            }

            // Verifica que en la posición 5 y 6 también hayan números
            for (int i = 5; i < 7; i++) {
                if (!Character.isDigit(fecha.charAt(i))) {
                    conteoNoValidosMes++;
                }
            }

            // Y también que los de la 8 y 9 sean números
            for (int i = 8; i < 10; i++) {
                if (!Character.isDigit(fecha.charAt(i))) {
                    conteoNoValidosDia++;
                }
            }

            // Si uno de los contadores es mayor a cero, estonces invalidará la fecha
            // ingresada
            if (conteoNoValidosAnio > 0 || conteoNoValidosMes > 0 || conteoNoValidosDia > 0) {
                validarFecha = false;
            }

        } else {
            validarFecha = false; // y si la longitud no es 10, la fecha tampoco será valida
        }

        if(!validarFecha) {
            throw new FormatException("Entrada inválida: formato de fecha (YYYY-MM-DD) incorrecto.");
        }
    }

    public static LocalTime validarHora(String hora){
        LocalTime horaConvertida;
        if(hora.length() == 8) {
            try{
                horaConvertida = LocalTime.parse(hora);
            }catch (DateTimeParseException e){
                throw new FormatException("Entrada inválida: formato de 24 horas (HH:MM) incorrecto.");
            }
        }else if (hora.length() == 4){
            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
                horaConvertida = LocalTime.parse(hora, formatter);
            }catch (DateTimeParseException e){
                throw new FormatException("Entrada inválida: formato de 24 horas (HH:MM) incorrecto.");
            }
        }else{
            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                horaConvertida = LocalTime.parse(hora, formatter);
            }catch (DateTimeParseException e){
                throw new FormatException("Entrada inválida: formato de 24 horas (HH:MM) incorrecto.");
            }
        }
        return horaConvertida;
    }

    public static void validarRangoFechas(String fecha, LocalDate fechaInicio, LocalDate fechaFinal) {
        validarFecha(fecha);
        LocalDate fechaConvertida = LocalDate.parse(fecha);
        if (fechaConvertida.isBefore(fechaInicio) || fechaConvertida.isAfter(fechaFinal)) {
            throw new FormatException("Entrada inválida: fecha fuera del rango permitido (" + fechaInicio + " - " + fechaFinal + ").");
        }
    }

    public static void validarRangoHoras(String hora, LocalTime horaInicio, LocalTime horaFinal){
        LocalTime horaConvertida = validarHora(hora);
        if(horaConvertida.isBefore(horaInicio) || horaConvertida.isAfter(horaFinal)) {
            throw new FormatException("Entrada inválida: hora fuera del rango permitido (" + horaInicio + " - " + horaFinal + ").");
        }
    }

    public static void validarEmail(String email){
        if(!email.contains("@") || !email.contains(".")) {
            throw new FormatException("Entrada inválida: formato de email incorrecto (debe incluir un '@' y un dominio válido).");
        }
    }

    public static void validarNumeroTelefono(String telefono){
        boolean validacion = true;
        int conteoNumeros = 0;
        char telefonoChars[] = telefono.toCharArray();
        if(telefonoChars.length == 9) {
            for (int i = 0; i < telefonoChars.length; i++) {
                if(i == 4){
                    if(telefonoChars[i] != '-'){
                        validacion = false;
                    }
                }else{
                    if(Character.isDigit(telefonoChars[i])){
                        conteoNumeros++;
                    }
                }
            }
        }else{
            validacion = false;
        }

        if(conteoNumeros != 8){
            validacion = false;
        }

        if(!validacion) {
            throw new FormatException("Entrada inválida: formato de número de teléfono (XXXX-XXXX) incorrecto.");
        }
    }

    public static void validarDUI(String dui){
        int conteoNumeros = 0;
        boolean validacion = true;
        char duiChars[] = dui.toCharArray();
        if(duiChars.length == 10) {
            for (int i = 0; i < duiChars.length; i++) {
                if(i <= 7 || i == 9){
                    if(Character.isDigit(duiChars[i])){
                        conteoNumeros++;
                    }
                }else if(i == 8){
                    if(duiChars[i] != '-'){
                        validacion = false;
                    }
                }
            }
        }else{
            validacion = false;
        }

        if(conteoNumeros != 9){
            validacion = false;
        }

        if(!validacion) {
            throw new FormatException("Entrada inválida: formato de DUI (XXXXXXXX-X) incorrecto.");
        }
    }

    public static String encriptarContra(String contra){
        return Base64.getEncoder().encodeToString(contra.getBytes());
    }

}

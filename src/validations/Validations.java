package src.validations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Base64;

/**
 * Clase: Validations
 * Almacena todas las validaciones necesarias para el correcto funcionamiento de todos los procesos del sistema
 */
public class Validations {
    /**
     * Verifica que lo ingresado sean solo números
     * @param input dato de entrada
     */
    public static void validarNumeros(String input) {
        int conteoLetras = 0;
        for (int i = 0; i < input.length(); i++) {

            // Verifica si el carácter es una letra
            if (!Character.isDigit(input.charAt(i))) {
                conteoLetras++;
            }
        }

        // Si no encuentra letras, comprueba que solo hay números y establece la variable como verdadera
        if (conteoLetras != 0) {
            throw new FormatException("Entrada inválida: solo se permiten números enteros positivos.");
        }
    }

    /**
     * Verifica que el dato de entrada sea un número decimal
     * @param input dato de entrada
     */
    public static void validarDecimales(String input) {
        int conteoLetras = 0;
        for (int i = 0; i < input.length(); i++) {

            // Verifica si el carácter es una letra
            if (!Character.isDigit(input.charAt(i)) && input.charAt(i) != '.') {
                conteoLetras++;
            }
        }

        // Si no encuentra letras, comprueba que solo hay números y establece la variable como verdadera
        if (conteoLetras != 0) {
            throw new FormatException("Entrada inválida: solo se permiten números decimales positivos.");
        }
    }

    /**
     * Verifica que los números estén dentro del rango definido
     * @param input dato de entrada
     * @param min valor mínimo definido
     * @param max valor máximo definido
     */
    public static void validarRangoNumeros(String input, int min, int max) {
        validarNumeros(input);
        if (Integer.parseInt(input) < min || Integer.parseInt(input) > max) {
            throw new FormatException("Entrada inválida: el valor ingresado está fuera del rango permitido (" + min + " - " + max + ").");
        }
    }

    /**
     * Verifica que un campo obligatorio no esté vacío
     * @param input dato de entrada
     */
    public static void validarCampoObligatorio(String input) {
        if(input == null || input.isEmpty()) {
            throw new FormatException("Entrada inválida: el campo es obligatorio.");
        }
    }

    /**
     * Nos permite validar si los datos ingresados representan una fecha en formato "YYYY-MM-DD"
     * @param fecha dato de entrada
     */
    public static void validarFecha(String fecha) {
        try{
            LocalDate.parse(fecha);
        }catch(DateTimeParseException e){
            throw new FormatException("throw new FormatException(\"Entrada inválida: formato de fecha (YYYY-MM-DD) incorrecto");
        }
    }

    /**
     * Verifica que la hora ingresada contenga el formato correcto
     * @param hora dato de entrada
     * @return hora formateada
     */
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

    /**
     * Verifica que las fechas cumplan con el rango establecido
     * @param fecha dato de entrada
     * @param fechaInicio fecha de inicio del rango
     * @param fechaFinal fecha final del rango
     */
    public static void validarRangoFechas(String fecha, LocalDate fechaInicio, LocalDate fechaFinal) {
        validarFecha(fecha);
        LocalDate fechaConvertida = LocalDate.parse(fecha);
        if (fechaConvertida.isBefore(fechaInicio) || fechaConvertida.isAfter(fechaFinal)) {
            throw new FormatException("Entrada inválida: fecha fuera del rango permitido (" + fechaInicio + " - " + fechaFinal + ").");
        }
    }

    /**
     * Verifica que la hora ingresada cumpla con el rango de horas establecido
     * @param hora dato de entrada
     * @param horaInicio hora inicial del rango
     * @param horaFinal hora final del rango
     */
    public static void validarRangoHoras(String hora, LocalTime horaInicio, LocalTime horaFinal){
        LocalTime horaConvertida = validarHora(hora);
        if(horaConvertida.isBefore(horaInicio) || horaConvertida.isAfter(horaFinal)) {
            throw new FormatException("Entrada inválida: hora fuera del rango permitido (" + horaInicio + " - " + horaFinal + ").");
        }
    }

    /**
     * Verifica que el e-mail cumpla con el formato requerido
     * @param email dato de entrada
     */
    public static void validarEmail(String email){
        if(!email.contains("@") || !email.contains(".")) {
            throw new FormatException("Entrada inválida: formato de email incorrecto (debe incluir un '@' y un dominio válido).");
        }
    }

    /**
     * Verifica que el teléfono cumpla con el formato requerido
     * @param telefono dato de entrada
     */
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

    /**
     * Verifica que el DUI cumpla con el formato requerido
     * @param dui dato de entrada
     */
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

    /**
     * Encripta la contraseña provista por el usuario
     * @param contra valor de contraseña sin encriptar
     * @return contraseña encriptada
     */
    public static String encriptarContra(String contra){
        return Base64.getEncoder().encodeToString(contra.getBytes());
    }

}

package src.test.validations.test;
import org.junit.jupiter.api.*;
import src.validations.FormatException;
import src.validations.Validations;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas para validar las funcionalidades de la clase Validations.
 * Cada método de prueba se enfoca en verificar la correcta validación de diferentes tipos de entradas.
 */
public class ValidationsTest {
    /**
     * Método que se ejecuta después de cada prueba, muestra el estado de la prueba.
     * @param testInfo Información sobre la prueba actual.
     */
    @AfterEach
    void afterEachTest(TestInfo testInfo) {
        // Muestra el número de la prueba que se completó
        int posguion = testInfo.getDisplayName().indexOf("-");
        String prueba = testInfo.getDisplayName().substring(0,posguion);
        String caso = testInfo.getDisplayName().substring(posguion+2);
        System.out.println("-".repeat(50));
        System.out.println(prueba);
        System.out.println(caso);
        System.out.println("Estado de la prueba: completada con éxito!");
    }

    /**
     * Prueba que válida que los números positivos no generen excepciones.
     */
    @DisplayName("Prueba: validarNumeros - Caso: Validar números positivos")
    @Test
    void testValidarNumerosPositivos() {
        assertDoesNotThrow(() -> Validations.validarNumeros("12345"));
    }

    /**
     * Prueba que válida que no se acepten letras en la validación de números.
     */
    @DisplayName("Prueba: validarNumeros - Caso: Validar que no se acepten letras")
    @Test
    void testValidarNumerosLetras() {
        assertThrows(FormatException.class, () -> Validations.validarNumeros("123as45"));
    }

    /**
     * Prueba que valida que no se acepten números negativos en la validación de números.
     */
    @DisplayName("Prueba: validarNumeros - Caso: Validar que no se acepten números negativos")
    @Test
    void testValidarNumerosNegativos() {
        assertThrows(FormatException.class, () -> Validations.validarNumeros("-12345"));
    }

    /**
     * Prueba que válida que los números decimales positivos no generen excepciones.
     */
    @DisplayName("Prueba: validarDecimales - Caso: Validar números decimales positivos")
    @Test
    void testValidarDecimalesPositivos() {
        assertDoesNotThrow(() -> Validations.validarDecimales("123.45"));
    }

    /**
     * Prueba que valida que no se acepten números decimales negativos.
     */
    @DisplayName("Prueba: validarDecimales - Caso: Validar que no se acepten números decimales negativos")
    @Test
    void testValidarDecimalesNegativos() {
        assertThrows(FormatException.class, () -> Validations.validarDecimales("-123.45"));
    }

    /**
     * Prueba que válida que no se acepten letras en la validación de números decimales.
     */
    @DisplayName("Prueba: validarDecimales - Caso: Validar que no se acepten letras")
    @Test
    void testValidarDecimalesLetras() {
        assertThrows(FormatException.class, () -> Validations.validarDecimales("123.a45"));
    }

    /**
     * Prueba que válida que un número esté dentro del rango permitido.
     */
    @DisplayName("Prueba: validarRangoNumeros - Caso: Validar rango de números dentro del rango permitido")
    @Test
    void testValidarRangoNumerosDentroDelRango() {
        assertDoesNotThrow(() -> Validations.validarRangoNumeros("15", 10, 20));
    }

    /**
     * Prueba que válida que un número fuera del rango no sea aceptado.
     */
    @DisplayName("Prueba: validarRangoNumeros - Caso: Validar que el número no pueda estar fuera del rango")
    @Test
    void testValidarRangoNumerosFueraDelRango() {
        assertThrows(FormatException.class, () -> Validations.validarRangoNumeros("25", 10, 20));
    }

    /**
     * Prueba que válida que un campo obligatorio no esté vacío.
     */
    @DisplayName("Prueba: validarCampoObligatorio - Caso: Validar campo obligatorio no vacío")
    @Test
    void testValidarCampoObligatorioNoVacio() {
        assertDoesNotThrow(() -> Validations.validarCampoObligatorio("Dato obligatorio"));
    }

    /**
     * Prueba que válida que un campo obligatorio no esté vacío.
     */
    @DisplayName("Prueba: validarCampoObligatorio - Caso: Validar que el campo obligatorio no pueda ir vacío")
    @Test
    void testValidarCampoObligatorioVacio() {
        assertThrows(FormatException.class, () -> Validations.validarCampoObligatorio(""));
    }

    /**
     * Prueba que valida una fecha con formato correcto.
     */
    @DisplayName("Prueba: validarFecha - Caso: Validar fecha con formato correcto")
    @Test
    void testValidarFechaCorrecta() {
        assertDoesNotThrow(() -> Validations.validarFecha("2023-04-01"));
    }

    /**
     * Prueba que valida una fecha con formato incorrecto.
     */
    @DisplayName("Prueba: validarFecha - Caso: Validar que no se acepten fechas con formato incorrecto")
    @Test
    void testValidarFechaIncorrecta() {
        assertThrows(FormatException.class, () -> Validations.validarFecha("2023-13-01"));
    }

    /**
     * Prueba que valida una hora con formato correcto.
     */
    @DisplayName("Prueba: validarHora - Caso: Validar hora con formato correcto")
    @Test
    void testValidarHoraCorrecta() {
        assertDoesNotThrow(() -> Validations.validarHora("15:30"));
    }

    /**
     * Prueba que valida una hora con formato incorrecto.
     */
    @DisplayName("Prueba: validarHora - Caso: Validar que no se acepten horas con formato incorrecto")
    @Test
    void testValidarHoraIncorrecta() {
        assertThrows(FormatException.class, () -> Validations.validarHora("25:30"));
    }

    /**
     * Prueba que válida si una fecha está dentro de un rango permitido.
     */
    @DisplayName("Prueba: validarRangoFechas - Caso: Validar rango de fechas dentro del rango permitido")
    @Test
    void testValidarRangoFechasDentroDelRango() {
        assertDoesNotThrow(() -> Validations.validarRangoFechas("2023-04-01", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31)));
    }

    /**
     * Prueba que válida si una fecha fuera del rango no sea aceptada.
     */
    @DisplayName("Prueba: validarRangoFechas - Caso: Validar que no se acepten fechas fuera del rango")
    @Test
    void testValidarRangoFechasFueraDelRango() {
        assertThrows(FormatException.class, () -> Validations.validarRangoFechas("2024-01-01", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31)));
    }

    /**
     * Prueba que válida si una hora está dentro de un rango permitido.
     */
    @DisplayName("Prueba: validarRangoHoras - Caso: Validar rango de horas dentro del rango permitido")
    @Test
    void testValidarRangoHorasDentroDelRango() {
        assertDoesNotThrow(() -> Validations.validarRangoHoras("12:30", LocalTime.of(9, 0), LocalTime.of(18, 0)));
    }

    /**
     * Prueba que válida si una hora fuera del rango no sea aceptada.
     */
    @DisplayName("Prueba: validarRangoHoras - Caso:Validar que no se acepten horas fuera del rango")
    @Test
    void testValidarRangoHorasFueraDelRango() {
        assertThrows(FormatException.class, () -> Validations.validarRangoHoras("19:30", LocalTime.of(9, 0), LocalTime.of(18, 0)));
    }

    /**
     * Prueba que valida un email con formato correcto.
     */
    @DisplayName("Prueba: validarEmail - Caso: Validar formato de email correcto")
    @Test
    void testValidarEmailCorrecto() {
        assertDoesNotThrow(() -> Validations.validarEmail("usuario@dominio.com"));
    }

    /**
     * Prueba que valida un email con formato incorrecto.
     */
    @DisplayName("Prueba: validarEmail - Caso: Validar que no se acepten emails con formato incorrecto")
    @Test
    void testValidarEmailIncorrecto() {
        assertThrows(FormatException.class, () -> Validations.validarEmail("usuario@dominio"));
    }

    /**
     * Prueba que valida un número de teléfono con formato correcto.
     */
    @DisplayName("Prueba: validarNumeroTelefono - Caso: Validar formato de teléfono correcto")
    @Test
    void testValidarNumeroTelefonoCorrecto() {
        assertDoesNotThrow(() -> Validations.validarNumeroTelefono("1234-5678"));
    }

    /**
     * Prueba que valida un número de teléfono con formato incorrecto.
     */
    @DisplayName("Prueba: validarNumeroTelefono - Caso: Validar que no se acepten teléfonos con formato incorrecto")
    @Test
    void testValidarNumeroTelefonoIncorrecto() {
        assertThrows(FormatException.class, () -> Validations.validarNumeroTelefono("12345-678"));
    }

    /**
     * Prueba que valida un DUI con formato correcto.
     */
    @DisplayName("Prueba: validarDUI - Caso: Validar formato de DUI correcto")
    @Test
    void testValidarDUICorrecto() {
        assertDoesNotThrow(() -> Validations.validarDUI("12345678-9"));
    }

    /**
     * Prueba que valida un DUI con formato incorrecto.
     */
    @DisplayName("Prueba: validarDUI - Caso: Validar que no se acepten DUIs con formato incorrecto")
    @Test
    void testValidarDUIIncorrecto() {
        assertThrows(FormatException.class, () -> Validations.validarDUI("123456789"));
    }

    /**
     * Prueba que valida el encriptado de una contraseña.
     */
    @DisplayName("Prueba: encriptarContra - Caso: Validar encriptación de contraseña")
    @Test
    void testEncriptarContra() {
        String contra = "contraseña";
        String resultado = Validations.encriptarContra(contra);
        assertNotNull(resultado);
        assertNotEquals(contra, resultado);
    }
}
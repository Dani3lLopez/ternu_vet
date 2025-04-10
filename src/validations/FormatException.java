package src.validations;

/**
 * Validación de formato de entrada
 */
public class FormatException extends IllegalArgumentException {
    public FormatException(String msg) {
        super(msg);
    }
}

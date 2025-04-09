package src.models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ConnectionModel: Clase que administra la conexión a la base de datos
 * Esta clase proporciona un método para establecer la conexión con la base de datos y un método main para probar la conexión
 * @author TernuVet-DevTeam
 * @version 1.0
 */
public class ConnectionModel {

    // Variable estática para almacenar el nombre de la base de datos
    private static String dbName = "db_vet_ternurita";

    /**
     * Configura el nombre de la base de datos a utilizar
     * @param databaseName el nombre de la base de datos
     */
    public static void setDatabaseName(String databaseName) {
        dbName = databaseName;
    }

    /**
     * Establece una conexión con la base de datos según la configuración predeterminada
     * @return una instancia de Connection
     * @throws SQLException sí ocurre algún tipo de error al intentar conectarse a la base
     */
    public static Connection conectar() throws SQLException {
        // URL, usuario y contraseña de conexión a la base de datos
        String url = "jdbc:mariadb://localhost:3306/" + dbName;
        String user = "root";
        String pass = "1234";

        try {
            // Carga el driver de Maria DB
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("No se pudo cargar el jdbc: " + e.getMessage());
        }

        // Retorna la conexión a la base de datos
        return DriverManager.getConnection(url, user, pass);
    }

    /**
     * Método principal para probar que la conexión funcione
     * Imprime un mensaje de estado de conexión
     */
    public static void main(String[] args) {

        //setDatabaseName("db_vet_ternurita_test"); //Quitar el comentario en caso de querer testear con la base de pruebas

        try {
            Connection conexion = conectar();
            System.out.println("Conexión a la base de datos '" + dbName + "' establecida exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión: " + e.getMessage());
        }
    }
}
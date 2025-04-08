package src.Models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
* ConectionModel: Clase que administra la conexión a la base de datos.
 * Esta clase proporciona un método para establecer la conexion con la base de datos y un metodo main para probar la conexion.
 * @author TernuVet-DevTeam
 * @version 1.0
*/
public class ConnectionModel {

    /**
     * Establece una conexion con la base de datos
     * @return una instancia de Connection
     * @throws SQLException si ocurre algún tipo de error al intentar conectarse a la base
     */
    public static Connection conectar() throws SQLException{
        String url = "jdbc:mariadb://localhost:3306/db_vet_ternurita";
        String user = "root";
        String pass = "1234";

        try {
            //Carga el driver de Maria DB
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("No se pudo cargar el jdbc: " + e.getMessage());
        }
        //Retorna la conexion a la base de datos
        return DriverManager.getConnection(url, user, pass);
    }

    /**
     * Metodo principal para probar que la conexion funcione
     * Imprime un mensaje de estado de conexion.
     */
    public static void main(String[] args) {
        try {
            Connection conexion = conectar();
            System.out.println("Conexión establecida exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión: " + e.getMessage());
        }
    }
}

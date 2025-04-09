package src.Models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Esta clase se encarga de hacer la conexion con la base de de datos
 * Se hace con el controlador JDBC de MariaDB
 * Utiliza un metodo estatico para conectarse y uno main para probar la conexion
 */
public class ConnectionModel {

    /*
     * Retorna un objeto Connection que representa la conexion hecha
     * Se lanza una SQLException si ocurre un erro durante la conexion
     */
    public static Connection conectar() throws SQLException{
        // URL, usuario y contraseña de conexion a la base de datos
        String url = "jdbc:mariadb://localhost:3306/db_vet_ternurita";
        String user = "root";
        String pass = "2806";

        try {
            //Intenta cargar el controlador JDBC de MariaDB
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("No se pudo cargar el jdbc: " + e.getMessage());
        }

        // Retorna la conexion establecida con DriverManager
        return DriverManager.getConnection(url, user, pass);
    }

    /*
     * Metodo main para probar la conexion a la base de datos
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

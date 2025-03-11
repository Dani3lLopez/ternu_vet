package src.Models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionModel {
    public static Connection conectar() throws SQLException{
        String url = "jdbc:mariadb://localhost:3306/ternuvet";
        String user = "root";
        String pass = "1234";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("No se pudo cargar el jdbc: " + e.getMessage());
        }

        return DriverManager.getConnection(url, user, pass);
    }

    public static void main(String[] args) {
        try {
            Connection conexion = conectar();
            System.out.println("Conexión establecida exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión: " + e.getMessage());
        }
    }
}

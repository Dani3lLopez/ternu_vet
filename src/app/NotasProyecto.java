package src.app;

public class NotasProyecto {
    /*
    * El proyecto sigue una arquitectura MVC (Modelo, Vista, Controlador) 3 capas
    *
    * Modelo: Sentencias y logica de base de datos.
    * Vista: Simulacion de front-end del proyecto (todo en consola).
    * Controlador: Capa de comunicación entre Modelo y Vista (Almacena atributos, getters-setters y otros metodos que ayudan al flujo de los datos)
    *
    * MODELO:
    *   - Se compone por cada uno de los mantenimientos respectivos de un CRUD
    *       - CREATE
    *       - READ
    *       - UPDATE
    *       - DELETE
    *
    *   - Posee consultas parametrizadas para evitar inyecciones SQL.
    *
    * METEDOS DEL MODELO:
    *   - Cargar...
    *       - Hace el SELECT de los registros alojados en la base de datos.
    *   - Registrar...
    *       - Hace el INSERT de los registros a la base de datos.
    *   - Actualizar...
    *       - Hace el UPDATE de un registro en especifico de la base de datos.
    *   - Eliminar...
    *       - Hace el DELETE de un registro en especifico de la base de datos.
    *
    * ID TIPO UUID (UNIQUE UNIVERSAL ID - STRING) -> NO ES UN NÚMERO, SINO UNA CADENA
    *
    * CONNECTION-MODEL:
    *   Modelo en el que se alojan las credenciales y configuraciones para conectarse con la base de datos.
    *
    * ASPECTOS IMPORTANTES:
    *   - Es necesario instalar el JDBC de MariaDB para que pueda funcionar (https://mariadb.com/kb/en/installing-mariadb-connectorj/)
    *   - Si es necesario utilicen sus credenciales (si no son las mismas)
    *   - Para instalar el JDBC deben buscar la opcion: "Project Structure" en el menu del IDE
    *       ->MODULES
    *           ->DEPENDENCIES y agregar el JDBC (+)
    *
    * CRUD DE EJEMPLO -> PEOPLE
    *
    * NOTA: Subiré unos videos a Youtube para explicar el funcionamiento y el flujo de la información que sigue el proyecto para que puedan entender de donde viene cada cosa.
    *
    * ARCHIVO MAIN EN APP:
    *   - Servirá como menú principal para nuestro sistema.
    * */
}

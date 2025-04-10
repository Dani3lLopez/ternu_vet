package src.test.models.test;

import org.junit.jupiter.api.*;
import src.models.ConnectionModel;
import src.models.UsersModel;

import java.util.Base64;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UsersModelTest {
    private String idUsuarioActualizar;
    private String nombreActualizar;
    private String claveActualizar;
    private String estadoActualizar;
    private int administradorActualizar;
    private String idDoctorActualizar;
    private String usuarioExistencia;

    @BeforeAll
    static void setUp(){
        ConnectionModel.setDatabaseName("db_vet_ternurita_test");
    }

    @BeforeEach
    void setUpTest() {
        idUsuarioActualizar = "9f7239ad-067c-11f0-a366-40c2ba206b07";
        nombreActualizar = "RoberM123";
        claveActualizar = Base64.getEncoder().encodeToString("RobertoContra123".getBytes());
        estadoActualizar = "Activo";
        administradorActualizar = 0;
        idDoctorActualizar = "7926d25d-0679-11f0-a366-40c2ba206b07";
        usuarioExistencia = "grojas";
    }

    @AfterAll
    static void tearDown(){
        ConnectionModel.setDatabaseName("db_vet_ternurita");
    }

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

    @Test
    @Order(1)
    @DisplayName("Prueba: cargarListaUsuarios - Caso: Cargar lista de usuarios")
    void testCargarListaUsuarios() {
        List<List<String>> usuarios = UsersModel.cargarListaUsuarios();
        assertNotNull(usuarios);
    }

    @Test
    @Order(2)
    @DisplayName("Prueba: cargarListaDoctores - Caso: Cargar lista de doctores")
    void testCargarListaDoctores() {
        List<List<String>> doctores = UsersModel.cargarListaDoctores();
        assertNotNull(doctores);
    }

    @Test
    @Order(3)
    @DisplayName("Prueba: cargarDoctoresSinUsuario - Caso: Listar doctores sin usuario")
    void testCargarDoctoresSinUsuario() {
        List<List<String>> doctores = UsersModel.cargarDoctoresSinUsuario();
        assertNotNull(doctores);
    }

    @Test
    @Order(4)
    @DisplayName("Prueba: existenciaUsuario - Caso: Verificar existencia de un usuario existente")
    void testExistenciaUsuario() {
        boolean existe = UsersModel.existenciaUsuario(usuarioExistencia);
        assertTrue(existe);
    }

    @Test
    @Order(5)
    @DisplayName("Prueba: ingresarNuevoUsuario - Caso: Insertar nuevo usuario")
    void testIngresarNuevoUsuario() {
        int resultado = UsersModel.ingresarNuevoUsuario("UsuarioPrueba123", Base64.getEncoder().encodeToString("contra123".getBytes()), "Activo", true, idDoctorActualizar);
        assertEquals(1, resultado);
    }

    @Test
    @Order(6)
    @DisplayName("Prueba: cargarUsuario - Caso: Cargar datos de un usuario existente")
    void testCargarUsuario() {
        assertNotNull(UsersModel.cargarUsuario(idUsuarioActualizar));
    }

    @Test
    @Order(7)
    @DisplayName("Prueba: actualizarUsuario - Caso: Actualizar los datos del usuario")
    void testActualizarUsuario() {
        int resultado = UsersModel.actualizarUsuario(idUsuarioActualizar, nombreActualizar, claveActualizar, estadoActualizar, administradorActualizar, idDoctorActualizar);
        assertEquals(1, resultado);
    }

    @Test
    @Order(8)
    @DisplayName("Prueba: desactivarUsuario - Caso: Desactivar cuenta del usuario")
    void testDesactivarUsuario() {
        int resultado = UsersModel.desactivarUsuario(idUsuarioActualizar);
        assertEquals(1, resultado, "Debe actualizar el estado del usuario");
    }
}

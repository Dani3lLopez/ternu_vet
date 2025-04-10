![image](https://github.com/user-attachments/assets/12243c3d-48be-4423-8062-b8756dad1a02)# TernuVet-System 🐾

![License](https://img.shields.io/badge/License-MIT-blue.svg) ![Java](https://img.shields.io/badge/Java-8%2B-orange.svg)

Este proyecto es un sistema de gestión general para una veterinaria, donde los administradores pueden agendar, modificar y cancelar citas, administrar registros de sus doctores, registros de mascotas y sus propietarios. Además, es posible crear usuarios, manejar productos, creación y manejo de facturas, servicios y consultas para los pacientes. Se implementa utilizando el patrón de diseño **Modelo-Vista-Controlador (MVC)** y utiliza **Java** con conexión a base de datos para el almacenamiento.

## Acerca del Proyecto 🎯

Este proyecto está diseñado para gestionar una clínica veterinaria. Es ideal para pequeñas clínicas o veterinarios que desean un sistema básico de gestión general para sus negocios en una etapa de crecimiento.

## Características 🔧

- **Gestión de Mascotas**: Crear, actualizar y eliminar mascotas.
- **Gestión de Consultas**: Registrar y gestionar consultas.
- **Gestión de Citas**: Registrar y gestionar citas.
- **Gestión de Doctores**: Registrar y actualizar la información de los doctores.
- **Gestión de Usuarios**: Registrar y actualizar la información de los usuarios.
- **Gestión de Personas**: Registrar y actualizar la información de las personas.
- **Gestión de Propietarios**: Registrar y actualizar la información de los propietarios.
- **Gestión de Facturas**: Registrar y actualizar la información de las facturas.
- **Gestión de Productos**: Registrar y actualizar la información de productos.
- **Gestión de Servicios**: Registrar y actualizar la información de los servicios.
- **Interfaz en Consola**: El sistema es operado a través de la consola.

##  Estructura del Proyecto 📂

```plaintext
│── src
│   ├── app
│   │   ├── Main
│   │   ├── NotasProyecto
│   ├── controllers
│   │   ├── AppointmentsController
│   │   ├── ConsultationsController
│   │   ├── DoctorsController
│   │   ├── InvoicesController
│   │   ├── InvoicesDetailsController
│   │   ├── OwnersController
│   │   ├── OwnersPetsDetailsController
│   │   ├── PeopleController
│   │   ├── PetsController
│   │   ├── ProductsController
│   │   ├── ServicesController
│   │   ├── SpecialtiesController
│   │   ├── UsersController
│   ├── models
│   │   ├── AppointmentsModel
│   │   ├── ConnectionModel
│   │   ├── ConsultationsModel
│   │   ├── DoctorsModel
│   │   ├── InvoicesModel
│   │   ├── InvoicesDetailsModel
│   │   ├── OwnersModel
│   │   ├── OwnersPetsDetailsModel
│   │   ├── PeopleModel
│   │   ├── PetsModel
│   │   ├── ProductsModel
│   │   ├── ServicesModel
│   │   ├── SpecialtiesModel
│   │   ├── UsersModel
│   ├── test
│   │   ├── models.test
│   │   │   ├── UsersModelTest
│   │   ├── validations.test
│   │   │   ├── ValidationsTest
│   ├── validations
│   │   ├── FormatException
│   │   ├── Validations
│   ├── views
│   │   ├── Appointments
│   │   ├── Consultations
│   │   ├── Doctors
│   │   ├── Invoices
│   │   ├── InvoicesDetails
│   │   ├── Owners
│   │   ├── OwnersPetsDetails
│   │   ├── People
│   │   ├── Pets
│   │   ├── Products
│   │   ├── Services
│   │   ├── Specialties
│   │   ├── Users
│── README.md
│── External Libraries
│   ├── mariadb-java-client-2.7.12.jar

```

## Ultimas Actualizaciones 🆕

- **Renombramiento de paquetes**: Los paquetes del proyecto fueron renombrados.
- **Documentación interna Javadoc**: Cada clase tiene sus respectivos comentarios que generan un Javadoc.
- **Validación de campos**: Se implementan validaciones en los campos de entrada de datos para asegurar el correcto funcionamiento del proyecto.
- **Encriptación de contraseñas**: (Nueva funcionalidad) el sistema encripta contraseñas antes de enviar los datos a la base de datos.
- **Cambio en la clase de conexión**: Se ha definido un parámetro para el nombre de la base de datos, esto facilita el cambio entre la base de testing y la original.
- **Implementación de testing**: Se han implementado pruebas con JUnit 5.10.0 Jupiter.
- **Errores solucionados**: Errores encontrados han sido solucionados.

## Requisitos 📌

Antes de comenzar, asegúrate de tener instalados los siguientes programas en tu máquina:

- **Java 8 o superior**: Asegúrate de tener Java instalado. Si no lo tienes, puedes descargarlo desde [aquí](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html).
- **MySQL**: Para almacenar la información de la base de datos.
- **IDE o editor de texto** (por ejemplo, IntelliJ IDEA, Eclipse, VSCode).

## Instrucciones de Instalación ⚙️

1. **Clona el repositorio:**

   ```bash
   git clone https://github.com/Dani3lLopez/ternu_vet.git

2. **Descargar el JDBC JAR Driver de mariaDB**

   **Link de descarga:**
   
   https://mariadb.com/kb/en/installing-mariadb-connectorj/

   **Configuración necesaria:**
   
   ![image](https://github.com/user-attachments/assets/279bd8ed-1461-4e97-b3fc-194e164f8500)

3. **Añadir el JDBC al proyecto:**

   - ![image](https://github.com/user-attachments/assets/a5270c2a-c6ec-4910-b506-219598c5cf3e)
   - ![image](https://github.com/user-attachments/assets/429f8a21-53c0-443c-9cd4-8002dc1b9f66)
   - ![image](https://github.com/user-attachments/assets/f9c681ad-674b-4414-9b37-12065de6f754)
   - Buscar el archivo JAR y cargarlo en el programa.

4. **Descargar la libreria JUnit para pruebas unitarias**
   - Ir a la sección de ProjectStructure y seleccionar Libraries.
   - ![image](https://github.com/user-attachments/assets/8e62e261-a38d-4c5a-82a8-c5d801f3ef40)
   - Dar click al signo + para indicar que se añadirá una libreria nueva.
   - ![image](https://github.com/user-attachments/assets/7ca3a1ff-ecf8-45a2-a3ca-9d3cf95b57e2)
   - Seleccionar que se añadirá desde Maven.
   - ![image](https://github.com/user-attachments/assets/7593177f-0c33-4223-a0e6-810edc9bafa6)
   - Buscar la libreria (JUnit - Jupiter - v. 5.10.0).
   - ![image](https://github.com/user-attachments/assets/7c82f771-b7ba-4f37-b454-38ccc055d967)
   - Presionar OK y seguir el proceso para la instalación.

5. **Colocar tus credenciales de acceso a la base de datos**
   - Ir al archivo ConnectionModel en el paquete models.
   - Editar las variables de user y pass (en caso difieran con tu información)
   - ![image](https://github.com/user-attachments/assets/bdd3d23d-045e-4b99-a173-08cab79de9c0)

6. **Monta la base de datos de producción y de testeos en tu computadora**
  - Ejecuta los scripts (MySQL) de las bases de producción y testeo en tu computadora.

## Instrucciones para ejecutar las pruebas unitarias 🧪
   - Ir al paquete de test.
   - Dentro del paquete de test, buscar las clases de prueba.
   - ![image](https://github.com/user-attachments/assets/a7961022-ec25-4222-b8ec-e02ecebde43e)
   - Una vez dentro del archivo que deseas probar, solo hace falta ejecutar el archivo .java.
   - **Nota:** para probar las clases dentro de models.test es necesario tener la base test en tu computadora.

## Instrucciones para generar JAVADOC 📄

1. **Clic en "Tools" dentro del proyecto:**

   ![image](https://github.com/user-attachments/assets/c9d7ea6a-0b99-403c-9c2e-32eb57dee7a0)


2. **Selecciona: "Generate Javadoc"**

   ![image](https://github.com/user-attachments/assets/81f9b065-5e4f-4027-a6ef-48c736e5fe16)


   **Configuración necesaria:**
   
  ![javadoc](https://github.com/user-attachments/assets/5870ae04-78b6-414c-88ac-9fd76d59ebb0)


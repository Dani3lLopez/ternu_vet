# Sistema de Gestión Veterinaria 🐾

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

## 📂 Estructura del Proyecto

```plaintext
│── src
│   ├── app
│   │   ├── Main
│   │   ├── NotasProyecto
│   │   ├── Controllers
│   │   │   ├── AppointmentsController
│   │   │   ├── ConsultationsController
│   │   │   ├── DoctorsController
│   │   │   ├── InvoicesController
│   │   │   ├── InvoicesDetailsController
│   │   │   ├── OwnersController
│   │   │   ├── OwnersPetsDetailsController
│   │   │   ├── PeopleController
│   │   │   ├── PetsController
│   │   │   ├── ProductsController
│   │   │   ├── ServicesController
│   │   │   ├── SpecialtiesController
│   │   │   ├── UsersController
│   ├── Models
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
│   ├── Views
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

## Requisitos 📌

Antes de comenzar, asegúrate de tener instalados los siguientes programas en tu máquina:

- **Java 8 o superior**: Asegúrate de tener Java instalado. Si no lo tienes, puedes descargarlo desde [aquí](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html).
- **MySQL** (o cualquier base de datos que prefieras): Para almacenar la información.
- **IDE o editor de texto** (por ejemplo, IntelliJ IDEA, Eclipse, VSCode).

## Instrucciones de Instalación ⚙️

1. **Clona el repositorio:**

   ```bash
   git clone https://github.com/Dani3lLopez/ternu_vet.git

2. **Desacargar el JDBC JAR Driver de mariaDB**

   **Link de descarga:**
   https://mariadb.com/kb/en/installing-mariadb-connectorj/
   
   ![image](https://github.com/user-attachments/assets/a3b26676-262a-4954-95a8-0a7c96dca4e6)

   **Configuración necesaria:**
   ![image](https://github.com/user-attachments/assets/279bd8ed-1461-4e97-b3fc-194e164f8500)

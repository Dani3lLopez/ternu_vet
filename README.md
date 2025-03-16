# Sistema de Gestión de Citas Veterinarias 🐾

![License](https://img.shields.io/badge/License-MIT-blue.svg) ![Java](https://img.shields.io/badge/Java-8%2B-orange.svg)

Este proyecto es un sistema de gestión de citas para un veterinario, donde los usuarios pueden gestionar citas, doctores, mascotas y personas involucradas. Se implementa utilizando el patrón de diseño **Modelo-Vista-Controlador (MVC)** y utiliza **Java** con conexión a base de datos para el almacenamiento.

## Tabla de Contenidos 📑

- [Acerca del Proyecto](#acerca-del-proyecto)
- [Características](#características)
- [Requisitos](#requisitos)
- [Instrucciones de Instalación](#instrucciones-de-instalación)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Cómo Usar](#cómo-usar)
- [Tecnologías Usadas](#tecnologías-usadas)
- [Licencia](#licencia)
- [Contacto](#contacto)

## Acerca del Proyecto 🎯

Este proyecto está diseñado para gestionar citas en una clínica veterinaria. Permite a los usuarios registrar, actualizar y eliminar citas, así como gestionar a los doctores y mascotas en el sistema. Es ideal para pequeñas clínicas o veterinarios que desean un sistema básico de gestión.

## Características 🔧

- **Gestión de Citas**: Crear, actualizar y eliminar citas.
- **Gestión de Doctores**: Registrar y gestionar doctores disponibles.
- **Gestión de Mascotas**: Registrar y gestionar mascotas.
- **Gestión de Personas**: Registrar y actualizar la información de los usuarios.
- **Interfaz en Consola**: El sistema es operado a través de la consola.

## 📂 Estructura del Proyecto

```plaintext
src
├── Controllers
│   ├── AppointmentsController.java   # Controlador para gestionar las citas
│   └── DoctorsController.java        # Controlador para gestionar los doctores
├── Models
│   ├── AppointmentsModel.java        # Modelo para las citas
│   ├── ConnectionModel.java          # Manejo de la conexión a la base de datos
│   ├── DoctorsModel.java             # Modelo para los doctores
│   └── PeopleModel.java              # Modelo para las personas
├── Views
│   ├── Appointments.java             # Vista para interactuar con las citas
│   └── Doctors.java                  # Vista para interactuar con los doctores
└── Main.java                          # Clase principal que ejecuta el programa


## Requisitos 📌

Antes de comenzar, asegúrate de tener instalados los siguientes programas en tu máquina:

- **Java 8 o superior**: Asegúrate de tener Java instalado. Si no lo tienes, puedes descargarlo desde [aquí](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html).
- **MySQL** (o cualquier base de datos que prefieras): Para almacenar la información.
- **IDE o editor de texto** (por ejemplo, IntelliJ IDEA, Eclipse, VSCode).

## Instrucciones de Instalación ⚙️

1. **Clona el repositorio:**

   ```bash
   git clone https://github.com/Dani3lLopez/ternu_vet.git

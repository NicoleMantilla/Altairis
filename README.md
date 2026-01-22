# PRUEBA ALTAIRIS HOTELS - Backoffice Fullstack MVP

He desarrollado esta solución integral para **Altairis Hotels** con el objetivo de centralizar la gestión operativa de sus propiedades. El enfoque principal ha sido crear una herramienta intuitiva que permita visualizar el estado del negocio de forma clara y eficiente.

---

## Funcionalidades Implementadas

### 1. Gestión Integral de Hoteles
* **Administración:** Alta de hoteles con sus datos principales.
* **Visualización:** Búsqueda fluida diseñada para manejar volúmenes elevados de registros.

### 2. Control de Tipos de Habitación
* **Catálogo Visual:** Gestión de tipos de habitación por hotel con detalles técnicos y soporte de imágenes para identificación rápida.

### 3. Operativa y Reservas
* **Inventario:** Visualización clara de la disponibilidad para facilitar la comprensión operativa.
* **Gestión de Reservas:** Registro y consulta de la actividad diaria de forma centralizada.

---

## 🧩 Arquitectura y Decisiones Técnicas

He diseñado este MVP bajo un enfoque **Fullstack profesional**, priorizando la mantenibilidad y el tipado estricto.

### Estructura del Proyecto (Monorepo)
* **Backend (Java/Spring Boot):** Arquitectura por capas (*Domain, Application, Infrastructure*) para un desacoplamiento total de la lógica de negocio.
* **Frontend (Next.js 15):** Uso de App Router, TypeScript y **Tailwind CSS v4** para una interfaz moderna y rápida.
* **Persistencia:** MySQL con Hibernate, configurado para la generación automática de esquemas.

---

## ⚙️ Instrucciones de Ejecución

Para garantizar la flexibilidad, la solución está preparada para ejecutarse en dos modalidades:

### ▶️ Opción 1: Ejecución Manual (Recomendada)
Es el método ideal para visualizar los logs en tiempo real y asegurar la estabilidad en el entorno local.

1.  **Base de Datos:** Crear esquema `hotel_db` en MySQL.
2.  **Backend:**
    ```bash
    cd backend
    ./mvnw spring-boot:run
    ```
3.  **Frontend:**
    ```bash
    cd frontend
    npm install
    npm run dev
    ```
    *Acceso:* [http://localhost:3000](http://localhost:3000)

### ▶️ Opción 2: Docker (Estructura Implementada)
He incluido la configuración completa para un despliegue unificado. Aunque requiere **Docker Desktop**, los archivos ya están configurados:
  ```bash
        docker compose up --build
  ```
---
## Mejoras Futuras y Escalabilidad

El MVP está diseñado como una base sólida sobre la cual se pueden implementar las siguientes fases:

* **Autenticación y RBAC:** Implementación de Spring Security con JWT para diferenciar roles entre administradores de hotel y personal de recepción.
* **Notificaciones:** Sistema de alertas mediante correo electrónico (Spring Mail) para confirmaciones de reserva y cambios de disponibilidad.

---

## Observaciones del Cliente (Altairis)

En cumplimiento con los requerimientos específicos del cliente, he puesto especial énfasis en:

> **"Se valora que la información sea fácil de interpretar para la operación diaria, especialmente en inventario y reservas."**

* **Solución:** He implementado una interfaz de usuario limpia, eliminando ruido visual y utilizando tarjetas informativas que permiten leer el estado de una habitación en menos de 2 segundos.
* **Estado de la Operativa:** El flujo de navegación está optimizado para que el usuario siempre tenga una visión clara de la actividad diaria del hotel sin necesidad de realizar múltiples clics.



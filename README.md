# PRUEBA ALTAIRIS HOTELS - Backoffice Fullstack MVP 🏨

He desarrollado esta solución integral para **Altairis Hotels** con el objetivo de centralizar la gestión operativa de sus propiedades. El enfoque principal ha sido crear una herramienta intuitiva que permita visualizar el estado del negocio de forma clara y eficiente, priorizando la **mantenibilidad del código** y un **enfoque arquitectónico profesional**.

---

## Enfoque Arquitectónico y Criterio Técnico

Para este proyecto, he tomado decisiones orientadas a resolver problemas reales de negocio mediante una arquitectura robusta:

* **Claridad y Lógica:** El código sigue principios **SOLID**. He utilizado el **Patrón Repositorio** para desacoplar la lógica de persistencia y **DTOs** para asegurar contratos de datos estrictos, evitando la exposición innecesaria de entidades de base de datos.
* **Frontend Escalable (Next.js 15):** He seleccionado el **App Router** y **TypeScript** para garantizar un tipado fuerte en toda la aplicación, reduciendo errores en tiempo de ejecución y mejorando la mantenibilidad.

---

## Funcionalidades Esenciales

### 1. Administración de Hoteles
* **Gestión de Datos:** Lógica optimizada para el alta y la edición de propiedades.
* **Búsqueda Eficiente:** Visualización preparada para manejar volúmenes elevados de registros mediante una interfaz limpia.

### 2. Inventario y Disponibilidad
* **Gestión de Tipos de Habitación:** Asociación dinámica de habitaciones por hotel.
* **Comprensión Operativa:** Visualización gráfica del inventario diseñada específicamente para facilitar la lectura rápida del estado del hotel.

### 3. Operativa de Reservas
* **Registro de Actividad:** Flujo lógico para el alta de reservas, manteniendo la integridad referencial con el inventario disponible.
---

## 🧪 Validación y Pruebas (Postman)

Para garantizar la fiabilidad de la lógica de negocio y los contratos de datos (DTOs), se ha validado toda la API mediante **Postman**.

* **Colección incluida:** En la carpeta `/postman` encontrarás el archivo `altairis_collection.json`.
* **Cómo usarla:** Puedes importar este archivo en Postman para probar inmediatamente todos los endpoints (Hoteles, Habitaciones, Reservas) sin tener que configurar las peticiones manualmente.
* **Criterio Técnico:** El uso de esta colección asegura que los flujos de datos (POST/GET) cumplen con las validaciones del backend y facilitan la revisión de la lógica de integración.

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
* Testing: Ampliación de cobertura con tests unitarios.

---

## Observaciones del Cliente (Altairis)

En cumplimiento con los requerimientos específicos del cliente, he puesto especial énfasis en:

> **"Se valora que la información sea fácil de interpretar para la operación diaria, especialmente en inventario y reservas."**

* **Solución:** He implementado una interfaz de usuario limpia, eliminando ruido visual y utilizando tarjetas informativas que permiten leer el estado de una habitación en menos de 2 segundos.
* **Estado de la Operativa:** El flujo de navegación está optimizado para que el usuario siempre tenga una visión clara de la actividad diaria del hotel sin necesidad de realizar múltiples clics.



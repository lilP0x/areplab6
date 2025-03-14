# Diseño de Aplicación Segura - Taller de Arquitectura Empresarial

## Descripción General
Este proyecto forma parte del **Taller de Arquitectura Empresarial**, enfocado en diseñar y desplegar una aplicación segura y escalable utilizando **infraestructura en AWS**. La aplicación está basada en un servidor Spring Boot que maneja tanto el frontend como el backend.


## 
los elementos del servidor apache se encontraran en el siguiente repositorio 

https://github.com/lilP0x/Areplabstatic.git

## Arquitectura de la Aplicación
La arquitectura está diseñada para garantizar **comunicación segura** y **escalabilidad**.

### Componentes y Flujo de Comunicación
1. **Cliente (HTML + JavaScript)**: Servido mediante un cliente Apache y comunicado mediante una peticion directa a la api.
2. **Servidor Spring Boot (Backend y Servidor de Archivos Estáticos)**: Maneja la autenticación, la lógica de negocio y sirve los archivos estáticos del frontend.
3. **Infraestructura en AWS**:
    - **Instancia EC2**: Aloja el servidor Spring Boot.
    - **Instancia2 EC2**: Aloja la instancia de Apache.
    - **Grupos de Seguridad**: por medio del uso de los cors es posible habilitar que puntos tienen acceso directo a la api. Para este ejericio todos los destinos son permitidos.

### Diagrama del Sistema
```plaintext
Usuario (Navegador) ⇄ HTTPS ⇄ Servidor apache (EC2) ⇄ Servidor spring-boot
```
- El **cliente (navegador)** descarga el frontend desde el cliente apache.
- El frontend envía **solicitudes API** de manera segura al mismo servidor Spring Boot.

## Medidas de Seguridad Implementadas


### Autenticación y Seguridad en el Inicio de Sesión
- Las **contraseñas se almacenan de forma segura ya que son hasheadas apenas llegan al servidor spring-boot.

## Despliegue en AWS

### Paso 1: Configurar Instancia EC2
1. Crear una **instancia EC2** para alojar Spring Boot.

### Paso 2: Desplegar el Backend en Spring Boot
1. Instalar Java y dependencias de Spring Boot.

### Paso 3: Configurar instancia EC2 2
Para el servidor apache es necesario unicamente instalar apache y servir archivos estaticos (html, css, js)

4. Ejecutar la aplicación con:
   ```sh
   mvn spring-boot:run
   ```

### Paso 3: Verificación y Pruebas
- Confirmar que el **frontend se carga de manera segura** mediante HTTPS.
- Verificar que **las API responden correctamente** a través de HTTPS.
- Probar la **autenticación de usuarios y almacenamiento seguro de contraseñas**.


## Estructura del Proyecto
```
/ (Directorio raíz)(Aplicación Spring Boot)
│── src/   
    │── model
    │── repository
    │── controller
    │── service
│──pom.xml
│── README.md  (Resumen del proyecto y guía de configuración)
```
### Estructura del servidor apache

```
/ (Directorio raíz)
│── var/www/html/ (Archivos HTML y JS)
```

## Detalles adicionales y proceso de como se implemento

## Configuracion CORS apartado securityConfig

![cors.png](readmeImages%2Fcors.png)

Como previamente se menciono fue necesario crear una capa nueva dentro del back que tenga toda la configuracion para la seguridad y acceso de la api, esto esta dado por los cors.

## Configuracion CORS apartado APIREST

![corsApi.png](readmeImages%2FcorsApi.png)

@CrossOrigin(origins = "*")

Con esa linea permitimos que cualquier origen consulte la api sin problema de que el servidor rechace la conexion.


## Prueba del despliegue

![htmlApache.png](readmeImages%2FhtmlApache.png)


## Detalles de la implementacion 

Se utilizo una base de datos H2 en memoria para facilitar la implementacion.


spring.application.name=areplab6

```
# H2 Database Config
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.datasource.hikari.maximum-pool-size=5

# Deshabilitar seguridad en la consola de H2
spring.h2.console.settings.web-allow-others=true
spring.h2.console.settings.trace=false

# Configuracion de Spring Security
spring.security.user.name=admin
spring.security.user.password=admin
spring.security.user.roles=USER,ADMIN
```


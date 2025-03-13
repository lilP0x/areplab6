# Diseño de Aplicación Segura - Taller de Arquitectura Empresarial

## Descripción General
Este proyecto forma parte del **Taller de Arquitectura Empresarial**, enfocado en diseñar y desplegar una aplicación segura y escalable utilizando **infraestructura en AWS**. La aplicación está basada en un servidor Spring Boot que maneja tanto el frontend como el backend.

## Arquitectura de la Aplicación
La arquitectura está diseñada para garantizar **comunicación segura** y **escalabilidad**.

### Componentes y Flujo de Comunicación
1. **Cliente (HTML + JavaScript)**: Servido directamente desde Spring Boot a través de **HTTPS (cifrado TLS)**.
2. **Servidor Spring Boot (Backend y Servidor de Archivos Estáticos)**: Maneja la autenticación, la lógica de negocio y sirve los archivos estáticos del frontend.
3. **Infraestructura en AWS**:
    - **Instancia EC2**: Aloja el servidor Spring Boot.
    - **Grupos de Seguridad**: Restringen el acceso solo a los puertos necesarios (443 para HTTPS, 22 para SSH, etc.). 

### Diagrama del Sistema
```plaintext
Usuario (Navegador) ⇄ HTTPS ⇄ Servidor Spring Boot (EC2)
```
- El **cliente (navegador)** descarga el frontend directamente desde Spring Boot.
- El frontend envía **solicitudes API** de manera segura al mismo servidor Spring Boot.
- Toda la comunicación está **cifrada con certificados TLS** de **Let’s Encrypt**.

## Medidas de Seguridad Implementadas

### Cifrado TLS
- Spring Boot utiliza **certificados Let’s Encrypt** para **HTTPS**.
- Garantiza la **confidencialidad** e **integridad** de los datos durante la transmisión.

### Autenticación y Seguridad en el Inicio de Sesión
- Las **contraseñas se almacenan de forma segura


## Despliegue en AWS

### Paso 1: Configurar Instancia EC2
1. Crear una **instancia EC2** para alojar Spring Boot.
2. Configurar los **Grupos de Seguridad** para permitir solo **HTTPS (443) y SSH (22)**.

### Paso 2: Desplegar el Backend en Spring Boot
1. Instalar Java y dependencias de Spring Boot.
2. Colocar los archivos del frontend en `src/main/resources/static/` para que Spring Boot los sirva automáticamente.

```properties
   server.port=443
   server.ssl.key-store-type=PKCS12
   server.ssl.key-store=classpath:keystore.p12
   server.ssl.key-store-password=
   server.ssl.key-alias=
   ```
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
/ (Directorio raíz)
│── src/main/resources/static/  (Archivos HTML y JS servidos por Spring Boot)
│── backend/   (Aplicación Spring Boot)
│── docs/      (Documentación de la arquitectura y diagramas)
│── README.md  (Resumen del proyecto y guía de configuración)
```

## Referencias
- [Documentación de Spring Security](https://spring.io/projects/spring-security)
- [Guía de AWS EC2](https://docs.aws.amazon.com/ec2/)

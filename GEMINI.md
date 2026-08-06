# Contexto del Proyecto para Gemini CLI

## 1. Descripción
[Netfils] -Que hace el proyecto: Es un proyecto de base de datos 1, en el quetratamos de hacer un programa que lea un archivo .txt con datos lo muestre en una tabla con un GUI, y ademas tenga la funcionalidad de excribir en ese archivo

## 2. Stack Tecnológico
- **Lenguaje:** Java 21
- **Build Tool:** Maven
- **Librerías principales:** Lombok, ModelMapper, OpenCSV
- **Entorno/SO objetivo:** Windows

## 3. Estructura del Proyecto
/co.edu.unbosque.
.../modelo
.../model/persistence/ (dentro de esta esta la interfaz de un DAO, y como tal el dao que solo tienen para crear buscar, y cargar y escribir en el archivo txt)
.../model/ (aqui tenemos dos archivos, show y show dto, siento la entidad que se muestra en el txt, con ciertas caracteriztcas
.../controller/controller (aqui tenemos la conexion del modelo con la vista, aqui se manejan los action listener del gui)
...../view (aqui tenemos la vista, que es el GUI, con un JFrame y un JTable para mostrar los datos del txt, ademas de botones para filtrar y un buscador)

## 4. Convenciones de Código y Reglas
- Mantener inmutabilidad donde sea posible.
- Usar anotaciones de Lombok (`@Value`, `@Builder`, `@RequiredArgsConstructor`) para reducir boilerplate.
- NO agregar dependencias externas en el `pom.xml` sin pedir confirmación.

## 5. Comandos de Verificación
- Compilar: `mvn clean compile`
- Ejecutar aplicación: `mvn exec:java`

## 6. Estado del Desarrollo
### ✅ Implementado
- Configuración inicial de Maven y dependencias base.
- Parsing de archivos CSV con OpenCSV.

### ⏳ Pendiente
- Creacion de la interfaz gráfica (GUI) para guardar un nuevo dato.

---

## 🎯 Tarea Actual para Gemini
**Objetivo:** Implementar la funcionalidad de guardar un nuevo dato en el archivo .txt desde la interfaz gráfica (GUI).
**Descripción:** Se requiere que al ingresar un nuevo dato en la GUI y presionar el botón "Guardar", el dato se agregue al archivo .txt correspondiente y se actualice la tabla en la GUI para reflejar el cambio, ademas de crear campos para crear la nueva entidad
**Archivos a modificar:** Se tienen que modificar los archivos de controller, view facad y show table view
**Aclaracion y detalles:** Necesito que funciona con algo llamado tabs, para mostrar una pestaña para la tabla, y otra para guardar una entidad, dentro de eso (puedes crear una nueva clase llamda "ShowFormView" para la vista de guardar, y un "ShowFormController" para el controlador de esa vista). Ya existe un metodo "add" en dao, lo que necesitas es implementar una confirmacion dentor del gui y que se guarde en el archivo txt

**Condicionales:** Divide bien el mvc, dentro del controller recolectas los datos y los pasas al modelo, en la vista solo puedes poner los espacios y el diseño grafico, nada de procesamiento de datos o logica de negocio, ya en el modelo todo tiene que ser dentro del DAO

---

## 📝 Bitácora de Cambios

### habilitacion de crear una entidad y creacion de GUI para este fin
**Fecha:** 2024-05-23 (o fecha actual según sistema)
**Descripción:** Se implementó la funcionalidad completa para añadir nuevos registros desde la interfaz gráfica.
**Lo que se hizo:**
1.  **Refactorización de la Vista:** Se transformó la interfaz principal (`ViewFacade`) para usar `JTabbedPane`, permitiendo navegar entre el "Catálogo" y el "Nuevo Registro".
2.  **Nueva Vista de Formulario:** Se creó `ShowFormView` con un diseño organizado (`GridBagLayout`) para capturar los 12 atributos de la entidad.
3.  **Integración en el Controlador:** Se actualizó `Controller.java` para manejar los eventos del formulario, recolectar los datos en un `ShowDTO`, delegar el guardado al `ShowDAO` y refrescar la tabla automáticamente tras un guardado exitoso.
4.  **Persistencia:** Se aseguró que al guardar, se llame al método `persist()` del DAO para actualizar físicamente el archivo `catalogo.txt`.
5.  **Clean Code:** Se mantuvieron los principios MVC, dejando la lógica de procesamiento en el controlador y la persistencia en el modelo (DAO).

# Netflis

Netflis es una aplicación de escritorio desarrollada en Java con Swing para gestionar un catálogo de contenidos (series y películas) almacenado en un archivo de texto plano. El proyecto demuestra, de forma práctica, cómo aplicar el patrón MVC en una aplicación sencilla, cómo cargar datos desde un archivo, mostrarlos en una tabla, filtrarlos y además guardar nuevos registros para que persistan en disco.

Este proyecto fue pensado como una herramienta educativa para trabajar conceptos de base de datos, persistencia de datos, diseño de interfaces gráficas y arquitectura de software.

---

## Resumen general

Netflis permite:

- Cargar un catálogo de contenidos desde un archivo `catalogo.txt`.
- Mostrar los registros en una interfaz visual tipo tabla.
- Buscar y filtrar registros por distintos campos: ID, título, director, reparto, tipo, país, categoría, rating y año.
- Capturar nuevos registros desde un formulario gráfico.
- Persistir los cambios de vuelta en el archivo de datos para que no se pierdan al cerrar la aplicación.

En esencia, la aplicación funciona como una mini base de datos local basada en archivos de texto, con una interfaz gráfica amigable para interactuar con ella.

---

## ¿Qué hace exactamente el proyecto?

La aplicación se comporta como un gestor simple de catálogo de contenidos. Su flujo principal es:

1. Al iniciar, carga los datos desde `src/archivos/catalogo.txt`.
2. Los datos se transforman en objetos Java y se muestran en una tabla.
3. El usuario puede filtrar la información con un buscador y selectores.
4. También puede crear un nuevo registro desde un formulario.
5. Al guardar, el sistema añade el nuevo registro a la colección en memoria y lo escribe nuevamente en el archivo.

Aunque no es un sistema de base de datos completo como PostgreSQL o MySQL, sí representa muy bien el concepto de persistencia, validación y manipulación de datos en una aplicación real.

---

## Stack tecnológico

- Lenguaje: Java 21
- Interfaz gráfica: Java Swing
- Build tool: Maven
- Librerías principales:
  - Lombok: para reducir boilerplate con anotaciones.
  - ModelMapper: para mapear entre `Show` y `ShowDTO`.
  - OpenCSV: para leer y escribir archivos CSV/tabla con delimitador personalizado.

---

## Arquitectura del proyecto

El proyecto sigue el patrón MVC (Modelo - Vista - Controlador), con una separación clara de responsabilidades:

- Modelo: representa los datos y la lógica de persistencia.
- Vista: se encarga de dibujar la interfaz gráfica.
- Controlador: recibe eventos de la interfaz y coordina la interacción con el modelo.

### Flujo arquitectónico

```text
Usuario -> Vista (Swing) -> Controlador -> Modelo (DAO) -> Archivo catalogo.txt
                        ^                                      |
                        |                                      |
                        +----------- respuesta / refresh -------+
```

---

## Estructura de carpetas

```text
Netflis/
├── pom.xml
├── GEMINI.md
├── src/
│   ├── archivos/
│   │   └── catalogo.txt
│   └── main/
│       └── java/
│           └── co/
│               └── edu/
│                   └── unbosque/
│                       └── netflis/
│                           ├── controller/
│                           │   ├── AplMain.java
│                           │   └── Controller.java
│                           ├── model/
│                           │   ├── Show.java
│                           │   ├── ShowDTO.java
│                           │   └── persistence/
│                           │       ├── OperationDAO.java
│                           │       └── ShowDAO.java
│                           └── view/
│                               ├── ShowFormView.java
│                               ├── ShowTableView.java
│                               └── ViewFacade.java
└── target/
```

---

## Descripción detallada de cada capa

### 1. Capa de controlador

Ubicación: `src/main/java/co/edu/unbosque/netflis/controller/`

#### `AplMain`
- Punto de entrada de la aplicación.
- Crea una instancia del controlador principal.

#### `Controller`
- Es el cerebro de la aplicación.
- Recibe los eventos de los botones y los formularios.
- Coordina entre la vista y el DAO.
- Se encarga de:
  - Inicializar la interfaz.
  - Cargar los datos al iniciar.
  - Ejecutar búsquedas.
  - Guardar nuevos registros.
  - Refrescar la tabla después de cada operación.

Este controlador actúa como un puente entre lo que ve el usuario y lo que hace el sistema.

---

### 2. Capa de modelo

Ubicación: `src/main/java/co/edu/unbosque/netflis/model/`

#### `Show`
- Representa la entidad principal del sistema.
- Es la clase que se usa para mapear los datos del archivo.
- Está anotada para trabajar con OpenCSV y vincular los campos con las columnas del archivo.
- Contiene atributos como:
  - `showId`
  - `type`
  - `title`
  - `director`
  - `cast`
  - `country`
  - `date`
  - `releaseYear`
  - `rating`
  - `duration`
  - `listedIn`
  - `description`

#### `ShowDTO`
- Es un objeto de transferencia de datos.
- Se usa para simplificar la comunicación entre la vista y el controlador.
- Evita exponer directamente la entidad de persistencia en la capa de presentación.

#### `persistence/OperationDAO<T, E>`
- Define una interfaz genérica con operaciones comunes de acceso a datos.
- Sirve como contrato para los repositorios/DAOs.

#### `persistence/ShowDAO`
- Es el componente más importante de la capa de persistencia.
- Se encarga de:
  - Cargar datos desde `catalogo.txt`.
  - Guardar datos en memoria en una lista de `Show`.
  - Buscar por distintos criterios.
  - Escribir los cambios al archivo.
- Usa OpenCSV para leer y escribir con un formato delimitado por `|`.

---

### 3. Capa de vista

Ubicación: `src/main/java/co/edu/unbosque/netflis/view/`

#### `ViewFacade`
- Es la ventana principal de la aplicación.
- Extiende de `JFrame`.
- Organiza el contenido en pestañas mediante `JTabbedPane`.
- Tiene dos secciones principales:
  - Catálogo
  - Nuevo Registro

#### `ShowTableView`
- Es la vista que muestra los registros en forma de tabla.
- Incluye:
  - Combo box para seleccionar el criterio de búsqueda.
  - Campo de texto para ingresar el texto a buscar.
  - Botones de búsqueda y limpieza.
  - Una tabla (`JTable`) para visualizar los resultados.

#### `ShowFormView`
- Es el formulario para agregar nuevos registros.
- Contiene campos para capturar los datos de la entidad.
- Incluye botones para guardar y limpiar.

La vista se enfoca únicamente en mostrar componentes y capturar información; no implementa lógica de negocio compleja.

---

## Modelo de datos

La aplicación maneja una entidad llamada `Show`, que representa un elemento del catálogo con los siguientes campos:

- `showId`: identificador único del contenido.
- `type`: tipo de contenido (por ejemplo, Movie o TV Show).
- `title`: título.
- `director`: director.
- `cast`: reparto.
- `country`: país.
- `date`: fecha de ingreso al catálogo.
- `releaseYear`: año de lanzamiento.
- `rating`: clasificación.
- `duration`: duración.
- `listedIn`: categorías.
- `description`: descripción del contenido.

El archivo `catalogo.txt` almacena estos datos en formato delimitado por `|`, con una primera fila de encabezados.

---

## Flujo de mostrar datos

Este es el camino que sigue la aplicación cuando el usuario quiere ver el catálogo:

```text
1. El usuario abre la aplicación.
2. El controlador crea la vista y el DAO.
3. El DAO carga los datos desde catalogo.txt.
4. El controlador transforma los objetos en ShowDTO.
5. La vista ShowTableView recibe la lista y la muestra en la tabla.
6. El usuario puede filtrar los resultados usando el buscador.
```

### En términos de código

- `AplMain` inicia la aplicación.
- `Controller` llama a `dao.load()` al arrancar.
- `ShowDAO` lee el archivo con OpenCSV.
- `Controller` obtiene la lista de `Show` y la convierte a `ShowDTO`.
- `ShowTableView.actualizarTabla(...)` muestra los resultados en la interfaz.

---

## Flujo de guardar datos

Este es el camino que sigue la aplicación cuando el usuario agrega un nuevo registro:

```text
1. El usuario llena el formulario en la pestaña “Nuevo Registro”.
2. Al presionar “Guardar Registro”, el controlador recolecta los datos.
3. Se crea un objeto ShowDTO con la información capturada.
4. El controlador delega la operación al ShowDAO.
5. El DAO convierte el DTO a entidad Show y lo añade a la lista en memoria.
6. Se invoca persist() para escribir el nuevo contenido al archivo catalogo.txt.
7. La tabla se actualiza para reflejar el cambio.
```

### Validaciones básicas

Actualmente el sistema valida que al menos:

- `showId` no esté vacío.
- `title` no esté vacío.

Si faltan datos obligatorios, se muestra un mensaje de advertencia al usuario.

---

## Cómo se maneja la persistencia

La persistencia está centralizada en `ShowDAO`.

### Lectura
- Se usa `CsvToBeanBuilder` de OpenCSV para leer el archivo `catalogo.txt`.
- Los datos se cargan en objetos `Show` y se almacenan en una lista interna.

### Escritura
- Se usa `StatefulBeanToCsvBuilder` para serializar la lista de objetos nuevamente al archivo.
- El formato del archivo sigue siendo compatible con la lectura inicial.

Esto permite que la aplicación recuerde los datos entre ejecuciones.

---

## Dependencias importantes

### Lombok
Reduce código repetitivo en clases como `Show` y `ShowDTO` mediante anotaciones como `@Getter`, `@Setter`, `@Data`, `@NoArgsConstructor` y `@AllArgsConstructor`.

### ModelMapper
Permite convertir entre entidades y DTOs sin escribir manualmente miles de asignaciones.

### OpenCSV
Facilita el trabajo con archivos delimitados y el mapeo de columnas a propiedades Java.

---

## Cómo ejecutar la aplicación

### Requisitos
- Java 21 instalado.
- Maven instalado y configurado en el PATH.

### Comandos típicos

Desde la raíz del proyecto:

```bash
mvn clean compile
mvn exec:java
```

Si prefieres ejecutar desde tu IDE (IntelliJ IDEA, Eclipse o VS Code), puedes correr la clase principal:

```text
co.edu.unbosque.netflis.controller.AplMain
```

---

## Notas de diseño

Algunas decisiones de diseño importantes del proyecto:

- Se usa MVC para separar claramente responsabilidades.
- La vista no procesa datos; solo muestra y captura información.
- El controlador coordina las operaciones y evita que la lógica de negocio se mezcle con la interfaz.
- El DAO concentra la lógica de acceso a datos y persistencia.
- El archivo `catalogo.txt` sirve como almacenamiento local simple, ideal para un ejercicio académico.

---

## Estado actual del proyecto

El proyecto ya implementa:

- Carga inicial del catálogo desde un archivo.
- Visualización en tabla.
- Búsqueda y filtros.
- Formulario para crear nuevos registros.
- Persistencia en archivo.

Es un ejemplo claro de una aplicación Java Swing con arquitectura modular y enfoque educativo.

---

## Conclusión

Netflis es un proyecto pequeño pero bien estructurado que demuestra cómo construir una interfaz gráfica con Java, organizar una aplicación con MVC y persistir información en un archivo de texto. Es ideal para estudiar conceptos fundamentales de programación orientada a objetos, arquitectura de software y manejo de datos.

Si alguien lee este README, debería poder entender rápidamente qué hace el proyecto, cómo está organizado, cómo se manejan los datos y cómo interactúa la aplicación con el usuario.

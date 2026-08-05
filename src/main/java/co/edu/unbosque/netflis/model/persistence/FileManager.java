package co.edu.unbosque.netflis.model.persistence;

import java.io.*;
import java.util.Scanner;

/**
 * Utilidad para operaciones de E/S sobre archivos de la aplicación.
 * Provee métodos estáticos para crear carpetas, leer/escribir archivos de texto
 * y leer/escribir objetos serializados.
 *
 * Nota: actualmente los DAOs usan su propio código NIO para CSV. FileManager
 * contiene utilidades adicionales empleadas en otras partes del proyecto.
 */
public class FileManager {

    // Estos tres son para archivos de texto
    private static Scanner lectorDeArchivo; // Funcion para leer un archivo de texto plano
    private static File archivo; // Una variable del tipo file
    private static PrintWriter escritorDeArchivo; // Nos permite implmentar un escrito de archivos

    private static final String RUTA_CARPETA = "src/archivos"; // Direccion de la carpeta

    // Atributos para serializado
    private static FileOutputStream fos; // Sirve para escribir bytes en un archivo
    private static ObjectOutputStream oos; // Sirve para escribir archivos serializados
    private static FileInputStream fis; // Sirve para leer bytes de archivos
    private static ObjectInputStream ois; // Sirve para leer objetos serializados

    /**
     * Contructor vacio por norma
     */
    public FileManager() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Crea la carpeta de archivos si no existe.
     */
    public static void crearCarpeta() {
        archivo = new File(RUTA_CARPETA);
        if (!archivo.exists() || !archivo.isDirectory()) {
            archivo.mkdir();
        }
    }

    /**
     * Escribe el contenido en un archivo de texto dentro de la carpeta definida.
     * Si el archivo no existe lo crea y sobrescribe el contenido existente.
     *
     * @param nombreDeArchivo nombre del archivo (p. ej. "Usuarios.csv")
     * @param contenido texto a escribir
     */
    public static void escribirArchivoDeTexto(String nombreDeArchivo, String contenido) {

        try {
            archivo = new File(RUTA_CARPETA + "/" + nombreDeArchivo);

            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            escritorDeArchivo = new PrintWriter(archivo);
            escritorDeArchivo.println(contenido);
            escritorDeArchivo.close();

        } catch (IOException e) {
            System.out.println("Error al escribir el archivo de texto. (Creacion del archivo)");
            e.printStackTrace();
        }

    }

    /**
     * Lee el contenido de un archivo de texto dentro de la carpeta definida.
     * Si el archivo no existe lo crea y retorna cadena vacía.
     *
     * @param nombreDeArchivo nombre del archivo
     * @return contenido del archivo (String) o null si hay error
     */
    public static String leerArchivoDeTexto(String nombreDeArchivo) {

        try {
            archivo = new File(RUTA_CARPETA + "/" + nombreDeArchivo);
            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            lectorDeArchivo = new Scanner(archivo);

            String contenido = "";
            while (lectorDeArchivo.hasNext()) {
                contenido += lectorDeArchivo.nextLine();
            }
            lectorDeArchivo.close();
            return contenido;

        } catch (IOException e) {
            System.out.println("Error al leer el archivo. (Creacion del archivo)");
            e.printStackTrace();
            return null;
        }
    }

    // Funciones para serializado

    /**
     * Escribe un objeto serializado en un archivo dentro de la carpeta definida.
     * @param nombreArchivo nombre del archivo
     * @param contenido objeto a serializar
     */
    public static void escribirArchivoSerializado(String nombreArchivo, Object contenido) {

        try {

            archivo = new File(RUTA_CARPETA + "/" + nombreArchivo);

            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            fos = new FileOutputStream(archivo);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(contenido);

            oos.close();
            fos.close();

        } catch (IOException e) {
            System.out.println("Problemas al abrir el archivo serializado (Escritura)");
            e.printStackTrace();
        }
    }

    /**
     * Lee un objeto serializado desde un archivo dentro de la carpeta definida.
     * Si el archivo no existe lo crea y retorna {@code null}.
     *
     * @param nombreArchivo nombre del archivo serializado
     * @return objeto leído o {@code null} en caso de error
     */
    public static Object leerArchivoSerializado(String nombreArchivo) {

        Object contenido = null;

        try {

            archivo = new File(RUTA_CARPETA + "/" + nombreArchivo);

            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            fis = new FileInputStream(archivo);
            ois = new ObjectInputStream(fis);

            contenido = ois.readObject();

            ois.close();
            fis.close();

        } catch (IOException e) {
            System.out.println("Error al leer el archivo serializado");
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            System.out.println("Error en los datos del archivo serializado");
            e.printStackTrace();
        }
        return contenido;
    }

}

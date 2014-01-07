import java.io.File;
import java.util.*;
/**
 * @author      David Lilue <dvdalilue@gmail.com>
 * @version     1.0          
 * @since       2013-12-23
 */
public class RmiClient { 

    private File local;
    private Collection<File> stream;
    /**
     * <p>
     * Se crea una estructura con un path unico y un
     * DirectoryStream que se iniciliza en null.
     * <p>
     */
    public RmiClient() {
        this.local = new File(".");
        this.stream = new ArrayList<File>();
    }
    /**
     * Crea un DirectoryStream del path actual de la aplicacion.
     *
     */
    public void scan_directory() {
        this.stream = new ArrayList<File>();
        addTree(this.local, this.stream);
    }
    /**
     * Crea un DirectoryStream a partir del path pasado como
     * parametro.
     *
     * @param path en el cual se desea el directory stream
     */
    public void scan_this_directory(String path) {
        this.stream = new ArrayList<File>();
        addTree(new File(path), this.stream);
    }
    /**
     * Imprime los archivos en el directorio
     *
     */
    public void to_s() {
        for (File aux : this.stream) {
            System.out.println(" " + aux.getPath());            
        }
    }
    /**
     * Recorre el directorio recursivamente
     *
     * @param file estructura del archivo donde se desea buscar
     * @param all arreglo de los archivos en el direcctorio
     */
    public static void addTree(File file, Collection<File> all) {
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                all.add(child);
                addTree(child, all);
            }
        }
    }
    /**
     * Muestra las opciones de comandos
     *
     */
    public static void info() {
        System.out.println("\n***Comandos disponibles:\n\n" +
                           "rls - muestra la lista de archivos disponibles en servidor centralizado.\n" +
                           "lls - muestra la lista de archivos disponibles localmente (en el cliente).\n" +
                           "sub <archivo> - sube un archivo al servidor remoto (Ej: sub clase.pdf).\n" +
                           "baj <archivo> - baja un archivo desde el servidor remoto (Ej: baj ejemplo.c).\n" +
                           "bor <archivo> - borra el archivo en el servidor remoto.\n" +
                           "info - muestra la lista de comandos.\n" +
                           "sal - termina la ejecución del programa cliente.\n");
    }
}

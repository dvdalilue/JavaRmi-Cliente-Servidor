import java.io.*;
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
     * Crea un archivo a partir de un buffer de bytes con la informacion.
     *
     * @param buffer arreglo de bytes con la informacion del archivo
     */
    public void create_file(byte[] buffer, String name) {
        try {
            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(name));
            output.write(buffer,0,buffer.length);
            output.flush();
            output.close();
        } catch (Exception e) {
            File aux = new File(name);
            aux.delete();
            System.err.println("El archivo no existe, por lo que no se puede bajar");
        }
    }
    /**
     * Obtiene el arreglo de bytes con la informacion del archivo
     * especificado en el nombre.
     *
     * @param name nombre del archivo que se desea sacar el buffer
     * @return arreglo de byte con la informacion del archivo
     */
    public byte[] get_byte_b(String name) {
        try {
            File file = new File(name);
            byte buffer[] = new byte[(int)file.length()];
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(name));
            input.read(buffer,0,buffer.length);
            input.close();
            return(buffer);
        } catch(Exception e){
            return(null);
        }
    }
    /**
     * Imprime los archivos en el directorio
     *
     */
    public void to_s() {
        for (File aux : this.stream) {
            if (aux.isDirectory()) {
                System.out.println("\u001B[32m" + aux.getPath() + "\u001B[0m");
            } else {
                System.out.println(aux.getPath());
            }           
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
                //addTree(child, all);
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

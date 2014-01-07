import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.File;
import java.lang.StringBuilder;
/**
 * @author      David Lilue <dvdalilue@gmail.com>
 * @version     1.0          
 * @since       2013-12-23
 */
public class RmiServerImpl
    extends UnicastRemoteObject 
    implements RmiServer {
    /**
     * <p>
     * Contruye la clase a partir del constructor de la clase
     * extendida UnicastRemoteObject
     * <p>
     */
    public RmiServerImpl() throws RemoteException {
        super(0);
    }
    /**
     * Devuelve un mensaje.
     *
     * @return el string de un mensaje
     */
    public String getMessage() {
        return "Hello!!";
    }
    /**
     * Devuelve un string donde esta la informacion de los 
     * archivos en el directorio actual
     *
     * @return el string de los archivos
     */
    public String directory() {
        File file = new File(".");
        StringBuilder aux = new StringBuilder("");
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                aux.append("\n" + child.getPath());
            }
        }
        return aux.toString();
    }
    /**
     * Elimina un archivo or directorio.
     * <p>
     * Toma el nombre del archivo o directorio del parametro
     * lo elimina.
     * <p>
     * @param name nombre del archivo o directorio a borrar.
     * @return string de true si lo logro, de lo contrario de false
     */
    public String del(String name) {
        File aux = new File(name);
        return Boolean.toString(aux.delete());
    }
}

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * @author      David Lilue <dvdalilue@gmail.com>
 * @version     1.0          
 * @since       2013-12-23
 */
public interface RmiServer extends Remote {

    /**
     * Obtiene un mensaje.
     * <p>
     * Devuelve um mensaje definido.
     * <p>
     *
     * @return sting del mensaje
     */
    public String getMessage() throws RemoteException;
    /**
     * Devuelve un string donde esta la informacion de los 
     * archivos en el directorio actual
     *
     * @return el string de los archivos
     */
    public String directory() throws RemoteException;
    /**
     * Elimina un archivo or directorio.
     * <p>
     * Toma el nombre del archivo o directorio del parametro
     * lo elimina.
     * <p>
     * @param name nombre del archivo o directorio a borrar.
     * @return string de true si lo logro, de lo contrario de false
     */
    public String del(String name) throws RemoteException;
}

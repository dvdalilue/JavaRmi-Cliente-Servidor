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
     * Descarga un archivo or directorio.
     * <p>
     * Toma el nombre del archivo o directorio del parametro
     * lo descarga.
     * <p>
     * @param name nombre del archivo o directorio a descargar.
     * @return cadena de bytes con la informacion
     */
    public byte[] down(String name) throws RemoteException;
    /**
     * Sube un archivo o directorio.
     * <p>
     * Toma el nombre del archivo o directorio del parametro
     * lo sube(crea localmente).
     * <p>
     * @param buffer arreglo de bytes con la informacion 
     */
    public void up(byte[] buffer, String name) throws RemoteException;
    /**
     * Elimina un archivo or directorio.
     * <p>
     * Toma el nombre del archivo o directorio del parametro
     * lo elimina.
     * <p>
     * @param name nombre del archivo o directorio a borrar.
     * @return true si lo logro eliminar, de lo contrario de false
     */
    public Boolean del(String name) throws RemoteException;
    /**     
     * Verifica si la combinacion de nombre y clave es parte de los usuarios.
     * <p>
     * La verficcacion se realiza gracias a un servidor de autenticacion
     * done estan todos los usuarios con su clave. Que poseen permiso de entrar
     * al servidor de archivos.
     * <p>
     * @param usr nombre del usuario a verificar.
     * @param pass clave que supuestamente tiene asignado el usuario.
     *
     * @return retorna true si existe la combinacion, sino false
     */
    public Boolean authentic(String usr_pass) throws RemoteException;

    public void log() throws RemoteException;
    
    public void new_cmd(String cmd, String name) throws RemoteException;
}

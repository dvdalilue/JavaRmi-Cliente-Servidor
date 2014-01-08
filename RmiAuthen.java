import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * @author      David Lilue <dvdalilue@gmail.com>
 * @version     1.0          
 * @since       2014-01-07
 */
public interface RmiAuthen extends Remote {

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
}

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
}

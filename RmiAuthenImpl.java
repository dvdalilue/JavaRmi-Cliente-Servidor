import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.File;
import java.lang.StringBuilder;
/**
 * @author      David Lilue <dvdalilue@gmail.com>
 * @version     1.0          
 * @since       2014-01-07
 */
public class RmiAuthenImpl
    extends UnicastRemoteObject 
    implements RmiAuthen {
    /**
     * <p>
     * Contruye la clase a partir del constructor de la clase
     * extendida UnicastRemoteObject
     * <p>
     */
    public RmiAuthenImpl() throws RemoteException {
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
}

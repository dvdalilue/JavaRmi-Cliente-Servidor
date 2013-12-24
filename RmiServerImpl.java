import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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
}

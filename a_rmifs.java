import java.rmi.RemoteException;
import java.rmi.Naming;
import java.rmi.registry.*; 
/**
 * @author      David Lilue <dvdalilue@gmail.com>
 * @version     1.0          
 * @since       2014-01-07
 */
public class a_rmifs {
    /**
     * Metodo main de la clase que efectua la acciones del cliente.
     * <p>
     * ********\\\\Sujeto a cambios!!!!
     * <p>
     *
     *@param args arreglo de strings, de los argumentos de entrada
     * cuando se ejecuta el main
     */
    public static void main(String args[]) throws Exception {
        System.out.println("RMI server started");
 
        try { //special exception handler for registry creation
            LocateRegistry.createRegistry(1100); 
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            //do nothing, error means registry already exists
            System.out.println("java registry already exists.");
        }
 
        //Instantiate RmiServer
        RmiAuthenImpl obj = new RmiAuthenImpl();
 
        // Bind this object instance to the name "RmiServer"
        Naming.rebind("rmi://localhost:1100/RmiAuthentication", obj);
        System.out.println("PeerServer bound in registry");
    }
}

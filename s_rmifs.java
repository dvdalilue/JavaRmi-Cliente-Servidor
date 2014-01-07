import java.rmi.RemoteException;
import java.rmi.Naming;
import java.rmi.registry.*; 
import java.io.*;
/**
 * @author      David Lilue <dvdalilue@gmail.com>
 * @version     1.0          
 * @since       2013-12-23
 */
public class s_rmifs {
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
        try {
            RmiAuthen aut = (RmiAuthen)Naming.lookup("rmi://localhost:1100/RmiAuthentication");
            System.out.println(aut.getMessage());
            System.out.println("RMI server started");
 
            try { //special exception handler for registry creation
                LocateRegistry.createRegistry(1099); 
                System.out.println("java RMI registry created.");
            } catch (RemoteException e) {
                //do nothing, error means registry already exists
                System.out.println("java registry already exists.");
            }
 
            //Instantiate RmiServer
            RmiServerImpl obj = new RmiServerImpl();
 
            // Bind this object instance to the name "RmiServer"
            Naming.rebind("rmi://localhost:1099/RmiService", obj);
            System.out.println("PeerServer bound in registry");
            while (true) {
                System.out.print("Servidor Rmi~$ ");
                
                //  open up standard input
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                
                String cmd = null;
                
                //  read the username from the command-line; need to use try/catch with the
                //  readLine() method
                try {
                    cmd = br.readLine();
                    if (cmd.equals("sal")) {
                        System.exit(0);
                    }
                } catch (IOException ioe) {
                    System.out.println("IO error trying to read your name!");
                    System.exit(1);
                }
            }
        } catch (Exception e) {
            System.out.println("RmiServer exception: " + e);
        }
    }
}

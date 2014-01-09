import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.rmi.Naming;
import java.io.*;
/**
 * @author      David Lilue <dvdalilue@gmail.com>
 * @version     1.0          
 * @since       2014-01-07
 */
public class a_rmifs {
    /**
     * Metodo main de la clase que efectua la acciones del servidor
     * de autenticacion.
     * <p>
     * ********\\\\Sujeto a cambios!!!!
     * <p>
     *
     *@param args arreglo de strings, de los argumentos de entrada
     * cuando se ejecuta el main
     */
    public static void main(String args[]) throws Exception {
        System.out.println("RMI server iniciando"); 
        try {
            LocateRegistry.createRegistry(1100); 
            System.out.println("java RMI registry creado.");
        } catch (RemoteException e) {
            System.out.println("java RMI registry ya existe.");
        }
         //Instancia RmiAuthenImpl
        RmiAuthenImpl obj = new RmiAuthenImpl("user");
 
        // Bind este objeto a la instancia "RmiAuthenImpl"
        Naming.rebind("rmi://localhost:1100/RmiAuthentication", obj);
        while (true) {
            System.out.print("[Servidor_Authen Rmi:~]$ ");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String cmd = null;
            try {
                cmd = br.readLine();
                if (cmd.equals("sal")) {
                    System.exit(0);
                }
            } catch (IOException ioe) {
                System.out.println("IO error leyendo el comando!");
                System.exit(1);
            }
        }
    }
}

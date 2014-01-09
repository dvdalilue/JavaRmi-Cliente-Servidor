import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.rmi.Naming;
import java.io.*;
/**
 * @author      David Lilue <dvdalilue@gmail.com>
 * @version     1.0          
 * @since       2013-12-23
 */
public class s_rmifs {
    /**
     * Metodo main de la clase que efectua la acciones del servidor
     * de archivos asi como enlazar con el servidor de autenticacion
     * al construir el objeto RmiServerImpl. Para luego recibir
     * comandos por pantalla.
     * <p>
     * ********\\\\Sujeto a cambios!!!!
     * <p>
     *
     *@param args arreglo de strings, de los argumentos de entrada
     * cuando se ejecuta el main
     */
    public static void main(String args[]) throws Exception {
        try {
            System.out.println("RMI server iniciando"); 
            try {
                LocateRegistry.createRegistry(1099); 
                System.out.println("java RMI registry creado.");
            } catch (RemoteException e) {
                System.out.println("java RMI registry ya existe.");
            }
             //Instancia RmiServerImpl
            RmiServerImpl obj = new RmiServerImpl("localhost",1100);
 
            // Bind este objeto a la instancia "RmiServerImpl"
            Naming.rebind("rmi://localhost:1099/RmiService", obj);
            while (true) {
                System.out.print("[Servidor_Archivos Rmi:~]$ ");
                
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String cmd = null;
                try {
                    cmd = br.readLine();
                    if (cmd.equals("sal")) {
                        System.exit(0);
                    } else if (cmd.equals("log")) {
                        obj.log();
                    }
                } catch (IOException ioe) {
                    System.out.println("IO error leyendo el comando!");
                    System.exit(1);
                }
            }
        } catch (Exception e) {
            System.out.println("RmiServer exception: " + e);
        }
    }
}

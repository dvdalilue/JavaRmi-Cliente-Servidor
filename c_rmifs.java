import java.rmi.Naming;
import java.io.File;
import java.util.*;
import java.io.*;
/**
 * @author      David Lilue <dvdalilue@gmail.com>
 * @version     1.0          
 * @since       2013-12-23
 */
public class c_rmifs {
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
            RmiServer obj = (RmiServer)Naming.lookup("rmi://localhost:1099/RmiService");
            //RmiServer obj = (RmiServer)Naming.lookup("rmi://serena.ldc.usb.ve:1099/RmiService");
            RmiClient cli = new RmiClient();
            //cli.info();
            //System.out.println(obj.del("delete"));
            while (true) {
                System.out.print("Cliente Rmi~$ ");
                
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
                    else if (cmd.equals("men")) {
                        System.out.println(obj.getMessage());                        
                    }
                } catch (IOException ioe) {
                    System.out.println("IO error trying to read your name!");
                    System.exit(1);
                }
            }
        } catch (Exception e) {
            System.out.println("RmiServer exception: " + e);
            System.exit(1);
        }
    }
}

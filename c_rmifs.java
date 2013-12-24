import java.rmi.Naming;
import java.nio.file.Files;
import java.nio.file.Path;
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
        RmiServer obj = (RmiServer)Naming.lookup("//localhost/RmiServerImpl");
        System.out.println(obj.getMessage());
        RmiClient cli = new RmiClient();
        cli.directory_stream();
        System.out.println("");
        for (Path file : cli.stream) {
            if (!Files.isDirectory(file)) {
                System.out.println("  " + file.getFileName());
            }
        }
        System.out.println("");
    }
}

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.lang.StringBuilder;
import java.io.*;
/**
 * @author      David Lilue <dvdalilue@gmail.com>
 * @version     1.0          
 * @since       2014-01-07
 */
public class RmiAuthenImpl
    extends UnicastRemoteObject 
    implements RmiAuthen {

    /**
     *
     * Archivo de los usuarios permitidos con su clave
     * 
     */
    private String usr;
    private static final long serialVersionUID = 7526472295622776147L;

    /**
     * <p>
     * Contruye la clase a partir del constructor de la clase
     * extendida UnicastRemoteObject, ademas inicializa su string
     * con toda la informacion del archivo que se busca por el
     * parametro pasado al constructor.
     * <p>
     *
     * @param arch archivo con los usuarios y claves permitidos
     */
    public RmiAuthenImpl(String arch) throws RemoteException {
        super(0);
        String aux = "";
        StringBuilder temp = new StringBuilder("");
        try {
            BufferedReader in = new BufferedReader(new FileReader(new File(arch)));;
            while ((aux = in.readLine()) != null) {
                temp.append(aux);
            }
            this.usr = temp.toString();
        } catch (Exception e) {
            System.out.println("Problemas leyendo el archivo: " + e);
            System.exit(1);
        }
    }

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
    public Boolean authentic(String usr_pass) {
        if (this.usr.contains(usr_pass.subSequence(0,usr_pass.length()-1))) {
            return true;
        }
        return false;
    }
}

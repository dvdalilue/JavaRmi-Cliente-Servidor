import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.*;
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
     *
     * Archivo de los usuarios permitidos con su clave
     * 
     */
    private String usr;

    /**
     * <p>
     * Contruye la clase a partir del constructor de la clase
     * extendida UnicastRemoteObject
     * <p>
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
     * Devuelve un mensaje.
     *
     * @return el string de un mensaje
     */
    public String getMessage() {
        return "Hello!!";
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

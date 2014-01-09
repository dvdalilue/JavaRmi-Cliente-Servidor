import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.*;
import java.lang.StringBuilder;
/**
 * @author      David Lilue <dvdalilue@gmail.com>
 * @version     1.1          
 * @since       2013-12-23
 */
public class RmiServerImpl
    extends UnicastRemoteObject 
    implements RmiServer {

    private RmiAuthen aut;
    private Queue<String> cmds;

    /**
     * <p>
     * Contruye la clase a partir del constructor de la clase
     * extendida UnicastRemoteObject
     * <p>
     */
    public RmiServerImpl(String host) throws RemoteException {
        super(0);
        this.cmds = new Queue<String>();
        try {
            this.aut = (RmiAuthen)Naming.lookup("rmi://" + host + ":1100/RmiAuthentication");
        } catch (Exception e) {
            System.out.println("Error: " + e);
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
     * Devuelve un string donde esta la informacion de los 
     * archivos en el directorio actual
     *
     * @return el string de los archivos
     */
    public String directory() {
        File file = new File(".");
        StringBuilder aux = new StringBuilder("\n");
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                if (child.isDirectory()) {
                    aux.append("\u001B[32m" + child.getPath() + "\u001B[0m" + "\n");
                } else {
                    aux.append(child.getPath() + "\n");
                } 
            }
        }
        return aux.toString();
    }
    /**
     * Elimina un archivo or directorio.
     * <p>
     * Toma el nombre del archivo o directorio del parametro
     * lo elimina.
     * <p>
     * @param name nombre del archivo o directorio a borrar.
     * @return string de true si lo logro, de lo contrario de false
     */
    public Boolean del(String name) {
        File aux = new File(name);
        return aux.delete();
    }
    /**
     * Descarga un archivo o directorio.
     * <p>
     * Toma el nombre del archivo o directorio del parametro
     * lo descarga.
     * <p>
     * @param name nombre del archivo o directorio a descargar.
     * @return cadena de bytes con la informacion
     */
    public byte[] down(String name) {
        try {
            File file = new File(name);
            byte buffer[] = new byte[(int)file.length()];
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(name));
            input.read(buffer,0,buffer.length);
            input.close();
            return(buffer);
        } catch(Exception e){
            System.out.println("DownFile: "+e.getMessage());
            e.printStackTrace();
            return(null);
        }
    }
    /**
     * Sube un archivo o directorio.
     * <p>
     * Toma el nombre del archivo o directorio del parametro
     * lo sube(crea localmente).
     * <p>
     * @param buffer arreglo de bytes con la informacion 
     */
    public void up(byte[] buffer, String name) {
        try {
            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(name));
            output.write(buffer,0,buffer.length);
            output.flush();
            output.close();
        } catch (Exception e) {
            System.out.println("Fallo al intentar crear un archivo: " + e);
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
        try {
            return this.aut.authentic(usr_pass);
        } catch (Exception e) {
            System.out.println("ServerAuthen: " + e);
        }
        return false;
    }

    public void log() {
        this.cmds.to_s(20);
    }
    
    public void new_cmd(String cmd, String name) {
        this.cmds.add_ini(cmd, name);
    }
}

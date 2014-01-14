import gnu.getopt.Getopt;
import java.rmi.Naming;
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
        int c;
        Getopt g = new Getopt("c_rmifs", args, "f:m:p:c:h?");
        g.setOpterr(true);

        String usuarios = null;
        int puerto = 0;
        String servidor = null;
        String comandos = null;

        while ((c = g.getopt()) != -1)
            {
                switch(c)
                    {
                    case 'f':
                        usuarios = g.getOptarg();
                        break;
                    case 'm':
                        servidor = g.getOptarg();
                        break;
                    case 'p':
                        try {
                            puerto = Integer.parseInt(g.getOptarg());
                        } catch (Exception e) {
                            System.out.println("Error Puerto: Debe espicificar un entero");
                            System.exit(1);
                        }
                        break;
                    case 'c':
                        comandos = g.getOptarg();
                        break;
                    case 'h':
                        System.out.print("\n\nSintaxis correcta para c_rmifs.java -- ./java c_rmifs [-f usuarios] -m servidor -p puerto [-c comandos] \n \n Detalles de las opciones:\n -h <ayuda>     : Solicita ayuda.\n [-f usuarios]  : Nombre del archivo de los usuarios y claves.\n -m <servidor>  : Es el nombre DNS o dirección IP del computador donde corre el servidor de archivos.\n -p <puerto>    : Puerto donde estará el rmiregistry del servidor.\n [-c comandos]  : Será el nombre y dirección relativa o absoluta de un archivo, que contendrá en cada línea, uno de los comandos que el cliente puede ejecutar por línea de comandos. El cliente debe ejecutar primero los comandos de este archivo y al terminar comenzar a aceptar comandos por teclado, a menos que uno de los comandos del archivo sea el comando sal. ");
                        System.exit(0);
                        break;
                    case '?':
                        break; // getopt() already printed an error
                    default:
                        System.out.print("getopt() returned " + c + "\n");
                    }
            }

        // ------------------------------ CONDICIONES DE ENTRADA -------------------------------------------------------------------------------//

        if(puerto==0 || servidor==null){
            System.out.println("Es obligatorio especificar un puerto y un servidor, para mayor información solicite ayuda con la opción -h.\n");
            System.exit(0);
        }
 
        // -------------------------------------- FIN CONDICIONES -------------------------------------------------------------------------------//

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String atr[], name, key, cmd = null;
            
            if (usuarios == null) {
                System.out.print("Nombre: ");
                name = br.readLine().trim();
                System.out.print("Clave: ");
                key = name + ":" + br.readLine().trim();;
            } else {
                key = FileManager.get_first_line(usuarios);
                atr = key.split(":");
                name = atr[0];
            }

            RmiServer obj = (RmiServer)Naming.lookup("rmi://"+servidor+":"+puerto+"/RmiService");
            if (!(obj.authentic(key))) {
                System.out.println("***Usuario o clave invalido!!!");
                System.exit(0);
            }
            RmiClient cli = new RmiClient();

            if (comandos != null) {
                FileManager stream;
                stream = new FileManager(comandos);
                while ((cmd = stream.get_line()) != null) {
                    obj.new_cmd(cmd,name);
                    atr = cmd.split(" ");
                    System.out.print("\u001B[37m"+"Comando desde archivo:\u001B[31m "+atr[0]+"\u001B[0m \n");
                    cmd_manager(cmd, atr, cli, obj);
                }
            }
            
            while (true) {
                System.out.print("\u001B[37m["+name+"@"+"Cliente Rmi:~]$ \u001B[0m");
                cmd = br.readLine();
                if (!cmd.isEmpty()) {
                    obj.new_cmd(cmd,name);
                    atr = cmd.split(" ");
                    cmd_manager(cmd, atr, cli, obj);
                }
            }
        } catch (Exception e) {
            System.out.println("Error RmiClient");
            System.exit(1);
        }
    }

    public static void cmd_manager(String cmd, String[] atr, RmiClient cli, RmiServer obj) {
        byte[] buffer;

        if (cmd.equals("sal")) {
            System.exit(0);
        }
        else if (cmd.equals("info")) {
            RmiClient.info();
        }
        else if (cmd.equals("lls")) {
            cli.scan_directory();
            cli.to_s();
        }
        else if (cmd.equals("rls")) {
            try {
                System.out.println(obj.directory());                        
            } catch (Exception e) {
                System.out.println("Error rls:" + e);
            }
        }
        else if (atr[0].equals("sub")) {
            try {
                if ((buffer = cli.get_byte_b(atr[1])) != null) {
                    obj.up(buffer, atr[1]);
                } else {
                    System.out.println("El archivo que desea subir no existe en su directorio local.");
                }
            } catch (Exception e) {
                System.out.println("Falto identificar el archivo. Ej. sub test.txt");
            }
        }
        else if (atr[0].equals("baj")) {
            try {
                buffer = obj.down(atr[1]);
                cli.create_file(buffer,atr[1]);
            } catch (Exception e) {
                System.out.println("Falto identificar el archivo. Ej. baj test.txt");
            }
        }
        else if (atr[0].equals("bor")) {
            try {
                if (obj.del(atr[1])) {
                    System.out.println("Se elimino el archivo");
                } else {
                    System.out.println("No se elimino el archivo");
                }
            } catch (Exception e) {
                System.out.println("Falto identificar el archivo. Ej. bor test.txt");
            }
        } else {
            System.out.println("-- Rmi Client: " +atr[0]+ ": comando invalido");
        }
    }
}

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
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String atr[], name, key, cmd = null;
            byte[] buffer;
            System.out.print("Nombre: ");
            name = br.readLine();
            System.out.print("Clave: ");
            key = br.readLine();

            RmiServer obj = (RmiServer)Naming.lookup("rmi://localhost:1099/RmiService");
            if (!(obj.authentic(name+":"+key))) {
                System.out.println("***Usuario o clave invalido!!!");
                System.exit(0);
            }
            RmiClient cli = new RmiClient();
            
            while (true) {
                System.out.print("\u001B[37m["+name+"@"+"Cliente Rmi:~]$ \u001B[0m");

                try {
                    cmd = br.readLine();
                    if (!cmd.isEmpty()) {
                        obj.new_cmd(cmd,name);
                        atr = cmd.split(" ");
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
                            System.out.println(obj.directory());                        
                        }
                        else if (atr[0].equals("sub")) {
                            try {
                                if ((buffer = cli.get_byte_b(atr[1])) != null) {
                                    obj.up(buffer, atr[1]+"2");
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
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Command exception: " + e);
                    System.exit(1);
                }
            }
        } catch (Exception e) {
            System.out.println("RmiClient exception: " + e);
            System.exit(1);
        }
    }
}

import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.rmi.Naming;
import gnu.getopt.Getopt;
import java.io.*;
/**
 * @author      David Lilue <dvdalilue@gmail.com> --- 09-10444
 *		Verónica Liñayo <vlinayo@gmail.com> --- 08-10615
 * @version     1.0          
 * @since       2014-01-07
 */
public class a_rmifs {
    /**
     * Metodo main de la clase que efectua la acciones del servidor
     * de autenticacion.
     *
     *@param args arreglo de strings, de los argumentos de entrada
     * cuando se ejecuta el main
     */
    public static void main(String args[]) throws Exception {

        int c;

	/**
         * Función Getopt de la librería gnu.getopt.Getopt, basada en la función Getopt de C
	 * la cual permite el procesamiento y manejo de los datos de entrada por consola.
	 *
	 *@param args arreglo de strings, de los argumentos de entrada
	 * cuando se ejecuta el main
	 */
        Getopt g = new Getopt("rmifs", args, "f:p:h?");
        g.setOpterr(true);  //Activa el manejador de errores de la función Getopt.
        
        String usuarios = null;
        int puerto = 0;
        while ((c = g.getopt()) != -1)
            {
                switch(c)
                    {
                    case 'p':
                        try {
                            puerto = Integer.parseInt(g.getOptarg());
                        } catch (Exception e) {
                            System.out.println("Error Puerto: Debe espicificar un entero");
                            System.exit(1);
                        }
                        break;
                    case 'f':
                        usuarios = g.getOptarg();
                        break;
                    case 'h':
                        System.out.print("\n\nSintaxis correcta: rmifs.java -- ./java rmifs -f <usuario> -p <puerto> [-h <ayuda>] \n \n Detalles de las opciones:\n [-h <ayuda>]   : Solicita ayuda.\n -f <usuarios>  : Nombre del archivo de los usuarios y claves.\n -p <puerto>    : Aunque normalmente en los procesos de invocación remota de métodos se pudiera usar un puerto por defecto, y no colocarlo aquí, por el hecho de tener varios proyectos de características similares en la misma instalación, solicitamos que los programas rmiregistry corran en un puerto que comience con el número 2 y que las siguientes 5 cifras correspondan a las últimas 5 cifras del carnet de alguno de los integrantes del grupo. Esta norma busca evitar que interactúen programas de proyectos diferentes.  -- Puertos probados: 210615 210444.\n ");
                        System.exit(0);
                        break;
                    case '?': //El manejador se encarga de este caso(Insertar un valor inválido).
                        break; 
                    default:
                        System.out.print("getopt() returned " + c + "\n");
                    }
            }

  // -------------------------------------------------------CONDICIONES DE ENTRADA ----------------------------------------------------------//
    
     
        if(puerto==0 || usuarios==null){
            System.out.println("Es obligatorio especificar todos los argumentos, para mayor información solicite ayuda con la opción -h.\n");
            System.exit(1);
        }
 
  // -------------------------------------------------------- FIN CONDICIONES ---------------------------------------------------------------//

        System.out.println("RMI server iniciando"); 
        try {
            LocateRegistry.createRegistry(puerto); 
            System.out.println("java RMI registry creado.");
        } catch (RemoteException e) {
            System.out.println("java RMI registry ya existe.");
        }
        //Instancia RmiAuthenImpl
        RmiAuthenImpl obj = new RmiAuthenImpl(usuarios);
 
        // Bind este objeto a la instancia "RmiAuthenImpl"
        Naming.rebind("rmi://localhost:"+puerto+"/RmiAuthentication", obj);
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

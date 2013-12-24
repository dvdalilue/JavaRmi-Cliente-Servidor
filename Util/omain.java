import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;
import java.lang.Integer;

public class Main {

    //static private Scanner in;
    //static private PrintStream out;

    public Main(PrintStream ps) {
        System.setOut(ps);
    }

    public static void main(String[] args) {
        Main m = null;
        
        if (args.length == 2) {
            try{
        	BufferedReader in = new BufferedReader(new FileReader(args[0]));
        	Scanner inScan = new Scanner(in);
        	PrintStream out = new PrintStream(args[1]);

        	m = new Main(out);
        	String linea = "";
        	int i = 1;
                Box<Integer> caja = new Box<Integer>(1);
                System.out.println(
        	while (inScan.hasNext()) {
        	    linea = inScan.next();
                    System.out.printf("%s\n",linea);
        	}

            } catch(FileNotFoundException e){
        	System.out.println(e.getMessage());
            }
        }
        else {
            System.err.println("Sintaxis: prog <ArchivoEntrada> <ArchivoSalida>");
            System.exit(-1);
        }

    }
}

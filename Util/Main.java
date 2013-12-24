import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;
import java.lang.Object;

public class Main {

    public static void main(String[] args) {
        
        if (args.length == 1) {
            try {
        	BufferedReader in = new BufferedReader(new FileReader(args[0]));
        	Scanner inScan = new Scanner(in);
        	String linea = "";
        	Integer i = 1;
                Queue<Box<String>> cola = new Queue<Box<String>>();
                while (inScan.hasNextLine()) {
                    linea = inScan.nextLine();
                    cola.add_end(new Box<String>(linea));
                    i++;
                }                
                i = 1;
                while(!cola.is_empty()) {
                    Box<String> aux = new Box<String>();
                    aux = cola.first();
                    System.out.println("Palabra " + i + ": " + aux.get_value());
                    i++;
                }
            } catch(FileNotFoundException e){
                System.out.println(e.getMessage());
            }
        }
        else {
            System.err.println("Sintaxis: prog <ArchivoEntrada>");
            System.exit(-1);
        }
    }
}

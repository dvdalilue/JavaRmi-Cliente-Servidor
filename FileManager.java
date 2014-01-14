import java.io.*;

public class FileManager  {

    private BufferedReader stream;

    public FileManager(String file) {
        try{
            this.stream = new BufferedReader(new FileReader(new File(file)));
        } catch (FileNotFoundException e){ //Catch de excepciones
            System.out.println("No se pudo abrir el archivo: " + e.getMessage());
        }

    }

    public String get_line() {
        String linea = null;        
        try {
            while ((linea = this.stream.readLine()) != null) {
                if (linea.trim().length() != 0) {
                    linea = linea.trim();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Problema leyendo el archivo: " + e.getMessage());
            System.exit(1);
        }
        return linea;
    }

    public static String get_first_line(String file) {
        String strLinea = null;
        try{
            BufferedReader buffer = new BufferedReader(new FileReader(new File(file)));
            try {
                while ((strLinea = buffer.readLine()) != null) {
                    if (strLinea.trim().length() != 0) {
                        strLinea = strLinea.trim();
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Problema leyendo el archivo: " + e.getMessage());
                System.exit(1);
            }
        } catch (FileNotFoundException e){ //Catch de excepciones
            System.out.println("No se pudo abrir el archivo: " + e.getMessage());
        }
        return strLinea;
    }
}



import java.rmi.Naming;
import java.lang.Object;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;

public class RmiClient { 

    public static void main(String args[]) throws Exception {
      //  RmiServer obj = (RmiServer)Naming.lookup("//localhost/RmiServerImpl");
      //  System.out.println(obj.getMessage());
        Path dir = FileSystems.getDefault().getPath("./");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
                System.out.println("");
                for (Path file : stream) {
                    if (!Files.isDirectory(file)) {
                        System.out.println("  " + file.getFileName());
                    }
                }
                System.out.println("");
            }
    }
}

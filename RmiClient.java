import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.DirectoryIteratorException;
import java.io.IOException;
/**
 * @author      David Lilue <dvdalilue@gmail.com>
 * @version     1.0          
 * @since       2013-12-23
 */
public class RmiClient { 

    public DirectoryStream<Path> stream;
    private final Path dir;
    /**
     * <p>
     * Se crea una estructura con un path unico y un
     * DirectoryStream que se iniciliza en null.
     * <p>
     */
    public RmiClient() {
        this.dir = FileSystems.getDefault().getPath("./");
        this.stream = null;
    }
    /**
     * Crea un DirectoryStream del path actual de la aplicacion.
     *
     */
    public void directory_stream() {
        try {
            this.stream = Files.newDirectoryStream(this.dir);
        } catch (IOException | DirectoryIteratorException e) {
            System.err.println(e);
        }
    }
    /**
     * Crea un DirectoryStream a partir del path pasado como
     * parametro.
     *
     * @param dir path en el cual se desea el directory stream
     */
    public void directory_stream(Path dir) {
        try {
            this.stream = Files.newDirectoryStream(dir);
        } catch (IOException | DirectoryIteratorException e) {
            System.err.println(e);
        }
    }
}

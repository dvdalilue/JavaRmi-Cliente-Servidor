import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiServer extends Remote {

    public String getMessage() throws RemoteException;

    public long add(long a, long b) throws RemoteException;
}

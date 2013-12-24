import java.rmi.Naming;
 
public class RmiClient { 

    public static void main(String args[]) throws Exception {
        RmiServer obj = (RmiServer)Naming.lookup("//localhost/RmiServerImpl");
        System.out.println(obj.getMessage()); 
    }
}

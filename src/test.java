import java.net.InetAddress;

public class test {
    public static void main(String[] args) {
        String subnet = "192.168.16.133";
        try {
            InetAddress address = InetAddress.getByName("JOASH");
        System.out.println(address.getHostName());
        }catch(Exception e){
        e.printStackTrace();
    }
        
        
    }
}
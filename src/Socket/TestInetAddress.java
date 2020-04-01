package Socket;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class TestInetAddress {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println("计算机名称:" + inetAddress.getHostName());
        System.out.println("ip地址:" + inetAddress.getHostAddress());
        byte[] address = inetAddress.getAddress();
        System.out.println(Arrays.toString(address));
    }
}

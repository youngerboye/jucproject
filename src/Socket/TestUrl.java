package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class TestUrl {
    public static void main(String[] args) {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            URL imooc = new URL("https://www.imooc.com");
            URL url  = new URL(imooc,"/index.html?username=tom#test");
            System.out.println("协议:"+url.getProtocol());
            System.out.println("主机:"+url.getHost());
            System.out.println("端口号:"+url.getPort());

            inputStream = imooc.openStream();
            inputStreamReader = new InputStreamReader(inputStream,"utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            while ((bufferedReader.readLine())!=null){
                System.out.println(bufferedReader.readLine());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

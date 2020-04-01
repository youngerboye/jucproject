package chat;



import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

public class Client {

    Logger log = Logger.getLogger(Client.class.getName());

    private Socket socket;

    public Client() throws IOException {

        log.info("正在连接服务端...");
        socket = new Socket("localhost",8088);
        log.info("已连接服务端！");
    }

    public void start(){
        try {
            ServerHandler serverHandler= new ServerHandler();
            Thread thread = new Thread(serverHandler);
            thread.start();

            Scanner scanner = new Scanner(System.in);
            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(outputStream,"utf-8");
            PrintWriter pw = new PrintWriter(osw,true);

            String nickName = scanner.nextLine();
            pw.println(nickName);

            //最后一次发送的时间
            long last = System.currentTimeMillis();
            while(true){
                String message = scanner.nextLine();
                if(System.currentTimeMillis()-last>=1000){
                    pw.println(message);
                    last = System.currentTimeMillis();
                }else{
                    System.out.println("说话速度过快");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = null;
        try {
            client = new Client();
            client.start();
        } catch (IOException e) {
            System.out.println("客户端启动失败");
            e.printStackTrace();
        }
    }

    private class ServerHandler implements Runnable{

        @Override
        public void run() {
            try {
                InputStream inputStream = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(inputStream,"utf-8");
                BufferedReader br = new BufferedReader(isr);

                String message = null;
                while ((message=br.readLine())!=null){
                    log.info(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

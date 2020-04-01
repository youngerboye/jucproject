package chat;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 聊天室服务端
 */
public class Server {

    Logger log = Logger.getLogger(Server.class.getName());

    private ServerSocket server;

    private List<PrintWriter> allOut;

    public Server() throws IOException {

        server = new ServerSocket(8088);
        allOut = new ArrayList<>();
    }

    public void start() {
        try {
            while (true) {

                log.info("等待客户端连接...");
                Socket socket = server.accept();
                log.info("一个客户端连接了");
                ClientHander hander = new ClientHander(socket);

                Thread thread = new Thread(hander);
                thread.start();
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = null;
        try {
            server = new Server();
            server.start();
        } catch (IOException e) {
            System.out.println("服务器启动失败");
            e.printStackTrace();
        }
    }


    private class ClientHander implements Runnable {
        private Socket socket;
        private String host;

        public ClientHander(Socket socket) {
            this.socket = socket;

            InetAddress address = socket.getInetAddress();
            host = address.getHostAddress();
        }


        @Override
        public void run() {
            PrintWriter pw = null;

            System.out.println(host + "上线了");

            try {
                InputStream in = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(in, "utf-8");
                BufferedReader br = new BufferedReader(isr);


                OutputStream out = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(out, "utf-8");
                pw = new PrintWriter(osw, true);

                synchronized (allOut) {
                    allOut.add(pw);
                }

                String message = null;

                while ((message = br.readLine()) != null) {
                    synchronized (allOut) {
                        for (PrintWriter o : allOut) {
                            o.println(host + "说：" + message);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                allOut.remove(pw);
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(host + "下线");
            }
        }
    }
}

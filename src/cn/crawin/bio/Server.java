package cn.crawin.bio;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    final static int PORT = 8765;

    public static void main(String[] args) {

        ServerSocket server = null;
        try {
            server = new ServerSocket(PORT);
            System.out.println("server start ...");
            // 进行阻塞
//            Socket accept = server.accept();
            // 新建一个线程执行客户端的任务
//            new Thread(new ServerHandler(accept)).start();

            // 使用线程池
            Socket socket = null;
            HandlerExecutorPool executorPool = new HandlerExecutorPool(50, 1000);
            while (true){
                socket = server.accept();
                executorPool.execute(new ServerHandler(socket));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

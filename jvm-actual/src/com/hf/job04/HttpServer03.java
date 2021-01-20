package com.hf.job04;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * 用固定线程池执行程序
 * @author hfzhang
 * @date 2021/1/18
 */
public class HttpServer03 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8803);
        ExecutorService executorService = new ThreadPoolExecutor(40,40,0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        while(true){
            try {
                final Socket socket = serverSocket.accept();
                executorService.execute(() -> service(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void service(Socket socket){
        try {
            //模拟业务操作，20ms
            Thread.sleep(20);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP:/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "Hello nio";
            printWriter.println("Content-length:"+body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

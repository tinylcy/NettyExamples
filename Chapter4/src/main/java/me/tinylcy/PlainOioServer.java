package me.tinylcy;

import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by chenyangli.
 * <p>
 * Blocking networking without Netty.
 */
public class PlainOioServer {

    public void serve(int port) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(port);
        try {
            for (; ; ) {
                final Socket client = serverSocket.accept();
                System.out.println("Accepted connection from " + client);
                new Thread(new Runnable() {
                    public void run() {
                        OutputStream out;
                        try {
                            out = client.getOutputStream();
                            out.write("Hi!\r\n".getBytes(CharsetUtil.UTF_8));
                            out.flush();
                            client.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                client.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        new PlainOioServer().serve(9090);
    }
}

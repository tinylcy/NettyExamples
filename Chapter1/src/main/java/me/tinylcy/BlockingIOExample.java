package me.tinylcy;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by chenyangli.
 * <p>
 * Blocking I/O example.
 */
public class BlockingIOExample {

    public void serve(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket client = serverSocket.accept();
        BufferedReader in =
                new BufferedReader(new InputStreamReader(client.getInputStream()));
        BufferedWriter out =
                new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        String request, response;
        while ((request = in.readLine()) != null) {
            if ("Done".equals(request)) {
                break;
            }
            response = "Processed.\n";
            out.write(response);
            out.flush();
        }
    }

    public static void main(String[] args) throws Exception {
        new BlockingIOExample().serve(8080);
    }
}

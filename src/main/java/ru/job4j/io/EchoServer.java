package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * cd c:\Tools\curl-7.79.1-win64-mingw\bin\
 * curl -i http://localhost:9000/?msg=Hello
 */
public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    while (in.ready()) {
                        String str = in.readLine();
                        System.out.println(str);
                        if (str.contains("msg=Bye")) {
                            System.out.println("GoodBye");
                            server.close();
                        } else if (str.contains("?msg")) {
                            String reply = str.split("[= ]")[2] + "\r\n";
                            out.write(reply.getBytes());
                        }
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Ошибка ввода-вывода", e);
        }
    }
}
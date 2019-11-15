package com.heller.jcip.ch06;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            Socket conn = socket.accept();
            handleRequest(conn);
        }
    }

    private static void handleRequest(Socket conn) throws IOException {
        System.out.println("done " + conn);
        conn.close();
    }

}

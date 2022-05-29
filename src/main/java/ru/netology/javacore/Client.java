package ru.netology.javacore;

import org.json.simple.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class Client {
    public static char pickRandomChar() {
        String chars = "ABCDEFG";
        return chars.charAt(new Random().nextInt(chars.length()));
    }

    public static void main(String[] args) throws IOException {
        try (
                Socket socket = new Socket("localhost", 8989);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ) {
            //это то, что мы отправляем на сервер
            out.println("{ \"type\": \"ADD\", \"task\": \"task #" + pickRandomChar() + "\" }");
            //это то, что мы принимаем от сервера
            System.out.println(in.readLine());
        }
    }
}

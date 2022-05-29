package ru.netology.javacore;

import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.text.html.parser.Parser;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TodoServer {
    int port;
    Todos todos;
    final String fileName = "data.json";

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }


    public void start() throws IOException {
        System.out.println("Starting server at " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(8989);) { // стартуем сервер один(!) раз
            while (true) { // в цикле(!) принимаем подключения
                try (
                        Socket socket = serverSocket.accept();
                        // получать сообщения
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        // отправлять сообщения
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                ) {
                    //читаем строку
                    String jsonString = in.readLine(); //assign your JSON String here
                    Object obj = new JSONParser().parse(jsonString);
                    JSONObject ourJson = (JSONObject) obj;
                    String type = (String) ourJson.get("type");
                    String task = (String) ourJson.get("task");

                    if (type.equals("ADD")) {
                        todos.addTask(task);
                    }
                    if (type.equals("REMOVE")) {
                        todos.removeTask(task);
                    }
                    // отвечаем клиенту
                    out.println(todos.getAllTasks());
                }
            }
        } catch (IOException | ParseException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}

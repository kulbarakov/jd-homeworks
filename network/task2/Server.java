import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("Сервер запущен");
        int port = 8888;
        while (true) {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println("Принятно новое подключение");
            out.println("Привет. Напиши свое имя");
            final String name = in.readLine();
            out.println("Вы ребенок? (да/нет)");
            if (in.readLine().equalsIgnoreCase("да")) {
                out.println(String.format("Добро пожаловать на детскую площадку, %s, пойдем играть!", name));
            } else {
                out.println(String.format("Добро пожаловать на территорию взрослых, %s, Хорошего дня!", name));
            }
            serverSocket.close();
        }
    }
}

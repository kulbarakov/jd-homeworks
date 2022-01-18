import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("Сервер запущен");
        int port = 8888;
        String inetAddress = InetAddress.getByName("localhost").getHostAddress();
        while (true) {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.println("Принятно новое подключение");
            final String name = in.readLine();

            out.println(String.format("Привет, %s, ваш порт %d, адрес хоста: %s"
                    , name, clientSocket.getPort(), inetAddress));
            serverSocket.close();
        }
    }
}

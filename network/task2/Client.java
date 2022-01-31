import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        String host = "netology.homework";
        int port = 8888;
        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            System.out.println(in.readLine());
            Scanner scanner = new Scanner(System.in);
            out.println(scanner.nextLine());
            System.out.println(in.readLine());
            out.println(scanner.nextLine());
            System.out.println(in.readLine());
        }
    }
}

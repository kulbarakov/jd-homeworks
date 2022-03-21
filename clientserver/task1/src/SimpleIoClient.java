import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SimpleIoClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 23334);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {

            String msg;
            while (true) {
                System.out.println("Сервер: " + in.readLine());
                msg = scanner.nextLine();
                out.println(msg);
                if (msg.equals("end")) break;
                System.out.println("Сервер: " + in.readLine());
            }

        }
    }
}

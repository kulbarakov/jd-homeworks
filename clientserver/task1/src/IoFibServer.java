import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IoFibServer {
    static Map<Integer, BigInteger> fibs = new ConcurrentHashMap<>();

    static BigInteger getFibbonacciValue(int n) {
        if (n <= 1) return BigInteger.ZERO;
        if (n == 2) return BigInteger.ONE;

        if (fibs.get(n - 1) == null) fibs.put(n - 1, getFibbonacciValue(n - 1));
        if (fibs.get(n - 2) == null) fibs.put(n - 2, getFibbonacciValue(n - 2));
        return fibs.get(n - 1).add(fibs.get(n - 2));
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(23334);
        while (true) {
            try (Socket socket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                System.out.println("Подключился клиент: " + socket.getRemoteSocketAddress());
                out.println("Введите номер числа из ряда Фибоначчи (end - выход):");
                String inputString;
                while ((inputString = in.readLine()) != null) {
                    if (inputString.equals("end")) {
                        System.out.println("Клиент отключился");
                        break;
                    }
                    System.out.println("Клиент ввел число: " + inputString);
                    out.println("Ответ: " + getFibbonacciValue(Integer.parseInt(inputString)));
                    Thread.sleep(500);
                    out.println("Введите номер числа из ряда Фибоначчи (end - выход): ");
                }
            } catch (IOException | InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

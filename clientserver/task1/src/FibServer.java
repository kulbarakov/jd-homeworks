import java.io.*;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FibServer {
    static Map<Integer, BigInteger> fibs = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {

        final ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress("localhost", 23334));

        while (true) {
            try (SocketChannel socketChannel = serverChannel.accept()) {
                final ByteBuffer inputBuffer = ByteBuffer.allocate(2 << 10);

                while (socketChannel.isConnected()) {
                    socketChannel.write(ByteBuffer.wrap("Введите номер числа из ряда Фибоначчи".getBytes(StandardCharsets.UTF_8)));

                    int bytesCount = socketChannel.read(inputBuffer);
                    if (bytesCount == -1) break;
                    final String msg = new String(inputBuffer.array(), 0, bytesCount, StandardCharsets.UTF_8);
                    System.out.println("Клиент ввел число: " + msg);
                    inputBuffer.clear();

                    socketChannel.write(ByteBuffer.wrap(("Ответ: " + getFibbonacciValue(Integer.parseInt(msg)).toString()
                            + "\n").getBytes(StandardCharsets.UTF_8)));
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    static BigInteger getFibbonacciValue(int n) {
        if (n <= 1) return BigInteger.ZERO;
        if (n == 2) return BigInteger.ONE;

        if (fibs.get(n - 1) == null) fibs.put(n - 1, getFibbonacciValue(n - 1));
        if (fibs.get(n - 2) == null) fibs.put(n - 2, getFibbonacciValue(n - 2));
        return fibs.get(n - 1).add(fibs.get(n - 2));
    }
}

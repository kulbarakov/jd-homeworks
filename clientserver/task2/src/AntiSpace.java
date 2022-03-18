import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AntiSpace {
    private static final Map<SocketChannel, ByteBuffer> sockets = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {

        final ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress("localhost", 8081));
        serverChannel.configureBlocking(false);

        Selector selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        log("Server start");
        try {
            while (true) {
                selector.select();
                for (SelectionKey key : selector.selectedKeys()) {
                    if (key.isValid()) {
                        try {
                            if (key.isAcceptable()) {
                                SocketChannel socketChannel = serverChannel.accept();
                                socketChannel.configureBlocking(false);
                                log("Connected " + socketChannel.getRemoteAddress());
                                sockets.put(socketChannel, ByteBuffer.allocate(1000));
                                socketChannel.register(selector, SelectionKey.OP_READ);
                            } else if (key.isReadable()) {
                                SocketChannel socketChannel = (SocketChannel) key.channel();
                                ByteBuffer buffer = sockets.get(socketChannel);
                                int bytesRead = socketChannel.read(buffer);
                                log("Reading from " + socketChannel.getRemoteAddress() + ", bytes read=" + bytesRead);

                                if (bytesRead > 0 && buffer.get(buffer.position() - 1) == '\n') {
                                    socketChannel.register(selector, SelectionKey.OP_WRITE);
                                }
                            } else if (key.isWritable()) {
                                SocketChannel socketChannel = (SocketChannel) key.channel();
                                ByteBuffer buffer = sockets.get(socketChannel);

                                buffer.flip();
                                String clientMessage = new String(buffer.array(), buffer.position(), buffer.limit());

                                String response = clientMessage.replace(" ", "");

                                buffer.clear();
                                buffer.put(ByteBuffer.wrap(response.getBytes()));
                                buffer.flip();

                                int bytesWritten = socketChannel.write(buffer);
                                log("Writting to " + socketChannel.getRemoteAddress() + ", bytes written=" + bytesWritten);
                                if (!buffer.hasRemaining()) {
                                    buffer.compact();
                                    socketChannel.register(selector, SelectionKey.OP_READ);
                                }
                            }
                        } catch (IOException e) {
                            log("ERROR " + e.getMessage());
                        }
                    }
                }
                selector.selectedKeys().clear();
            }
        } catch (IOException e) {
            log("ERROR " + e.getMessage());
        } finally {
            serverChannel.close();
        }

    }

    private static void log(String message) {
        System.out.println("[" + Thread.currentThread().getName() + "] " + message);
    }
}

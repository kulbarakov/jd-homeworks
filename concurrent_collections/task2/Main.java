import java.util.*;
import java.util.concurrent.*;

public class Main {
    static Map<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();
    static Map<Integer,Integer> synchronizedMap = Collections.synchronizedMap(new HashMap<>());
    static final int ARRAY_SIZE = 1000;

    public static void main(String[] args) throws InterruptedException {

        int[] ints = new int[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            ints[i] = new Random().nextInt(ARRAY_SIZE);
        }

        Thread t1 = new Thread(new MapWriterReader(concurrentHashMap, ints));
        Thread t2 = new Thread(new MapWriterReader(concurrentHashMap, ints));
        Thread t3 = new Thread(new MapWriterReader(concurrentHashMap, ints));

        long startTime = System.currentTimeMillis();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        long finishTime = System.currentTimeMillis();
        System.out.println("ConcurrentHashMap: " + (finishTime-startTime));

        Thread t4 = new Thread(new MapWriterReader(synchronizedMap, ints));
        Thread t5 = new Thread(new MapWriterReader(synchronizedMap, ints));
        Thread t6 = new Thread(new MapWriterReader(synchronizedMap, ints));

        startTime = System.currentTimeMillis();
        t4.start();
        t5.start();
        t6.start();
        t4.join();
        t5.join();
        t6.join();
        finishTime = System.currentTimeMillis();
        System.out.println("SynchronizedMap: " + (finishTime-startTime));
    }
}

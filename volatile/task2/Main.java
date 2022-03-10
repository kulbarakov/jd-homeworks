import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.LongAdder;

public class Main {
    final static int ARRAY_SIZE = 100;
    static int[] candyShopCash = new int[ARRAY_SIZE];
    static int[] toysShopCash = new int[ARRAY_SIZE];
    static int[] commonShopCash = new int[ARRAY_SIZE];
    static LongAdder adder = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < ARRAY_SIZE; i++) {
            candyShopCash[i] = new Random().nextInt(999) + 1;
            commonShopCash[i] = new Random().nextInt(999) + 1;
            toysShopCash[i] = new Random().nextInt(999) + 1;
        }

        Thread candyShopThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int shopCash : candyShopCash) {
                    adder.add(shopCash);
                }
            }
        });

        Thread commonShopThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int shopCash : commonShopCash) {
                    adder.add(shopCash);
                }
            }
        });

        Thread toysShopThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int shopCash : toysShopCash) {
                    adder.add(shopCash);
                }
            }
        });

        candyShopThread.start();
        commonShopThread.start();
        toysShopThread.start();

        candyShopThread.join();
        commonShopThread.join();
        toysShopThread.join();

        System.out.println("Candy shop cash:");
        System.out.println(Arrays.toString(candyShopCash));
        System.out.println("Common shop cash:");
        System.out.println(Arrays.toString(commonShopCash));
        System.out.println("Toys shop cash:");
        System.out.println(Arrays.toString(toysShopCash));

        System.out.println(adder);
    }

}

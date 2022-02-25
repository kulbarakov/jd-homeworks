import java.util.Random;
import java.util.concurrent.Callable;

public class CallableThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int count = new Random().nextInt(10) + 1;
        int i = 0;
        while (i < count) {
            i++;
            System.out.println("Сообщение №" + i + " от потока " + Thread.currentThread().getName() +
                    " ID=" + Thread.currentThread().getId());
            Thread.sleep(2000);
        }
        return i;
    }
}

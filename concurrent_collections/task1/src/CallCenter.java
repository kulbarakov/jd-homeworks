import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CallCenter {
    private static final int OPERATORS_COUNT = 10;
    private static final ConcurrentLinkedQueue<Long> queue = new ConcurrentLinkedQueue<>();
    private static final int OPERATORS_TIMEOUT = 3000;

    public static void main(String[] args) throws InterruptedException {

        Thread ats = new Thread(new ATS(queue));
        ats.start();

        List<Runnable> operators = new ArrayList<>();

        for (int i = 0; i < OPERATORS_COUNT; i++) {
            operators.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!Thread.interrupted()) {
                        if (queue.isEmpty()) {
                            System.out.println(Thread.currentThread().getName() + " скучает без работы.");
                        } else
                            System.out.println(Thread.currentThread().getName() + " принял звонок #" + queue.poll());
                        try {
                            Thread.sleep(OPERATORS_TIMEOUT);
                        } catch (InterruptedException e) {
                            System.out.println(Thread.currentThread().getName() + ": Мне пора домой");
                            break;
                        }
                    }
                }
            }, "Оператор " + i));
        }

        ExecutorService executorService = Executors.newFixedThreadPool(OPERATORS_COUNT);
        for (Runnable operator : operators) {
            executorService.submit(operator);
        }

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

        ats.interrupt();
        System.out.println("Клиентов все еще ожидающих ответа операторов: " + queue.size());
    }
}

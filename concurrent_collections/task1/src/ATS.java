import java.sql.Time;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ATS implements Runnable {
    private final int NUMBER_OF_CALLS = 15;
    private final int ATS_TIMEOUT = 1000;
    private ConcurrentLinkedQueue<Long> queue;

    public ATS(ConcurrentLinkedQueue<Long> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            for (int i = 0; i < NUMBER_OF_CALLS; i++) {
                queue.add((new Date().getTime()) + i);
            }
            System.out.println("АТС: Поступили звонки - " + NUMBER_OF_CALLS);
            try {
                Thread.sleep(ATS_TIMEOUT);
            } catch (InterruptedException e) {
                System.out.println("АТС завершил работу");
                break;
            }
        }
    }
}

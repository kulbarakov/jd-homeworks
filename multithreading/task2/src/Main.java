import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int coreCount = Runtime.getRuntime().availableProcessors();
        final ExecutorService pool = Executors.newFixedThreadPool(coreCount);
        List<CallableThread> listOfCallables = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            CallableThread callable = new CallableThread();
            listOfCallables.add(callable);
        }
        List<Future<Integer>> futureList = pool.invokeAll(listOfCallables);
        pool.shutdown();
        for (int i = 0; i < futureList.size(); i++) {
            System.out.println("Результат задачи " + (i+1) + ": " + futureList.get(i).get());
        }
//        System.out.println("Результат самой быстрой задачи: " + pool.invokeAny(listOfCallables));
    }
}

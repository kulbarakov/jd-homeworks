public class Main {
    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("Группа потоков");
        Thread[] threads = new Thread[4];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new QuadroThread(group,"Поток №"+(i+1));
        }
        for (Thread t:threads) {
            t.start();
        }
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        group.interrupt();
        System.out.println("Завершаю все потоки");
    }
}

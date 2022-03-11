public class Main {
    volatile private boolean isOn = false;
    final int ITERATION_COUNT = 5;
    final int TUMBLE_PERIOD = 2000;
    final int TUMBLE_OFF_TIME = 500;

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        System.out.println("Main started");
        main.start();
        System.out.println("Main finished");
    }

    public void start() throws InterruptedException {
        Thread userThread = new Thread(user);
        Thread toyThread = new Thread(toy);
        userThread.start();
        System.out.println("User activated");

        toyThread.start();
        System.out.println("Toy activated");

        userThread.join();
        System.out.println("User finished");

        toyThread.interrupt();
        System.out.println("Toy finished");
    }


    Runnable user = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < ITERATION_COUNT; i++) {
                try {
                    isOn = true;
                    System.out.println("Tumbler ON");
                    Thread.sleep(TUMBLE_PERIOD);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    };

    Runnable toy = new Runnable() {
        @Override
        public void run() {
            boolean flag = true;
            while (!Thread.interrupted()) {
                try {
                    while (!isOn) {
                        if (Thread.interrupted()) {
                            Thread.currentThread().interrupt();
                            flag = false;
                            break;
                        }
                    }
                    Thread.sleep(TUMBLE_OFF_TIME);
                    if (flag) {
                        isOn = false;
                        System.out.println("Tumbler Off");
                    }
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    };
}

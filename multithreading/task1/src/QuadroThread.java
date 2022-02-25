public class QuadroThread extends Thread {
    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                Thread.sleep(2500);
                System.out.println("Работает " + getName());
            }

        } catch (InterruptedException ex) {
            System.out.println(getName() + " пытались завершить во время сна");

        } finally {
            System.out.printf("%s завершен\n", getName());
        }
    }

    public QuadroThread(ThreadGroup group, String name) {
        super(group, name);
    }
}

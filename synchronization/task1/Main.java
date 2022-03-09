public class Main {

    public static void main(String[] args) throws InterruptedException {
        final AutoShow carShowRoom = new AutoShow();
        ThreadGroup group = new ThreadGroup("Покупатели");
        for (int i = 0; i < 3; i++) {
            new Thread(group, carShowRoom::sellCar, "Покупатель " + (i + 1)).start();
        }
        for (int j = 0; j < 10; j++) {
            Thread t = new Thread(null, carShowRoom::acceptCar, "АвтоВаз");
            t.start();
            Thread.sleep(4000);
        }
        group.interrupt();
        System.out.println("Автосалон закрылся!!!");
    }
}

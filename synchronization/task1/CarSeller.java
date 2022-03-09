public class CarSeller {
    // Продавец автосалона
    private AutoShow autoShow;
    private final int CAR_PRODUCE_TIMEOUT = 2000;
    private final int CAR_BUY_TIMEOUT = 500;
    private final int RETURN_TIMEOUT = 1000;


    public CarSeller(AutoShow autoShow) {
        this.autoShow = autoShow;
    }

    public synchronized void releaseCar() {
        try {
            autoShow.getCars().add(new Car());
            System.out.println("Производитель " + Thread.currentThread().getName() + " выпустил 1 авто");
            Thread.sleep(CAR_PRODUCE_TIMEOUT);
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sellCar() {
        while (!Thread.interrupted())
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            Thread.sleep(CAR_BUY_TIMEOUT);
            while (autoShow.getCars().size() == 0) {
                System.out.println("Машин нет");
                wait();
            }
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто");
            autoShow.getCars().remove(0);
            Thread.sleep(RETURN_TIMEOUT);
        } catch (InterruptedException e) {
            break;
        }
    }
}

import java.util.ArrayList;
import java.util.List;

public class AutoShow {
    // Автосалон
    CarSeller seller = new CarSeller(this);
    List<Car> cars = new ArrayList<>();

    public void sellCar() {
        seller.sellCar();
    }

    public void acceptCar() {
        seller.releaseCar();
    }

    public List<Car> getCars() {
        return cars;
    }
}

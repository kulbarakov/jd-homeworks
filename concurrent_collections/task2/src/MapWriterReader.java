import java.util.Map;
import java.util.Random;

public class MapWriterReader implements Runnable {
    private Map<Integer, Integer> map;
    private int[] ints;

    public MapWriterReader(Map<Integer, Integer> map, int[] ints) {
        this.map = map;
        this.ints = ints;
    }

    @Override
    public void run() {
        for (int i = 0; i < ints.length; i++) {
            int randomInt = new Random().nextInt(ints.length);
            map.put(randomInt, randomInt);
            map.get(randomInt);
        }
    }
}

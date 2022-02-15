public class Frog {
    public static final int MIN_POSITION = 0;
    public static final int MAX_POSITION = 10;

    protected int position;

    public Frog() {
        position = 5;
    }

    public boolean jump(int steps) {
        // сделаем прыжок, если прыжок слишком большой
        // для поля, то не прыгнем и вернём false
        if ((position + steps) > MAX_POSITION || (position + steps) < MIN_POSITION) {
            return false;
        } else {
            position += steps;
            return true;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            if (position == i) result.append("^");
            else result.append("_");
        }
        return result.toString();
    }
}

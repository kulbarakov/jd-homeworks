import java.util.Random;

public class Main {
    public static int[] genIntArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }

    public static int calcSum(int[] array) {
        int sum = 0;
        for (int j : array) {
            sum += j;
        }
        return sum;
    }

    public static void main(String[] args) {
        final int ARRAY_SIZE = 100000;
        int[] array = genIntArray(ARRAY_SIZE);
        long start = System.currentTimeMillis();
        int sum = calcSum(array);
        long end = System.currentTimeMillis();
        long l = end - start;
        System.out.printf("Сумма элементов массива: %d, среднее: %.3f\n", sum, (double) sum / ARRAY_SIZE);
        System.out.println("Время вычисления: " + l);

        ArraySumTask arraySumTask = new ArraySumTask(0, ARRAY_SIZE, array);
        start = System.currentTimeMillis();
        sum = arraySumTask.compute();
        end = System.currentTimeMillis();
        l = end - start;
        System.out.printf("Сумма элементов массива: %d, среднее: %.3f\n", sum, (double) sum / ARRAY_SIZE);
        System.out.println("Время вычисления: " + l);

    }
}

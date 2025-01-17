import java.util.concurrent.RecursiveTask;

public class ArraySumTask extends RecursiveTask<Integer> {
    private final int[] array;
    private final int start;
    private final int end;

    @Override
    protected Integer compute() {
        final int diff = end - start;
        return switch (diff) {
            case 0 -> 0;
            case 1 -> array[start];
            case 2 -> array[start] + array[start + 1];
            default -> forkTasksAndGetResult();
        };
    }

    private int forkTasksAndGetResult() {
        final int middle = (end - start) / 2 + start;
        ArraySumTask task1 = new ArraySumTask(start, middle, array);
        ArraySumTask task2 = new ArraySumTask(middle, end, array);
        invokeAll(task1, task2);
        return task1.join() + task2.join();
    }

    public ArraySumTask(int start, int end, int[] array) {
        this.array = array;
        this.start = start;
        this.end = end;
    }
}

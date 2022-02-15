import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Frog frog = new Frog();
        Scanner scanner = new Scanner(System.in);
        String input;
        List<FrogCommand> commands = new ArrayList<>();
        int curCommand = -1;
        while (true) {
            //считываем ввод и конструируем комманду, если
            //пользователь не хотел выйти
            if ((input = scanner.next()).equals("0")) {
                break;
            } else if (input.equals("<<")) {
                if (curCommand < 0) {
                    System.out.println("Нечего отменять!");
                } else {
                    commands.get(curCommand).undo();
                    curCommand--;
                }
            } else if (input.equals("!!")) {
                if (curCommand == commands.size() - 1) {
                    System.out.println("Нечего отменять!");
                } else {
                    curCommand++;
                    commands.get(curCommand).doit();
                }
            } else {
                FrogCommand cmd = null;
                if (input.startsWith("+")) {
                    cmd = FrogCommands.jumpRightCommand(frog, Integer.parseInt(input));
                } else if (input.startsWith("-")) {
                    cmd = FrogCommands.jumpLeftCommand(frog, -Integer.parseInt(input));
                }
                curCommand++;
                commands.add(cmd);
                cmd.doit();
            }
            System.out.println(frog.toString());
        }
    }
}

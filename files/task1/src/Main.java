import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        StringBuilder logSB = new StringBuilder();
        logSB.append(createDir(logSB, "Games\\src"));
        logSB.append(createDir(logSB, "Games\\res"));
        logSB.append(createDir(logSB, "Games\\savegames"));
        logSB.append(createDir(logSB, "Games\\temp"));
        logSB.append(createDir(logSB, "Games\\src\\main"));
        logSB.append(createDir(logSB, "Games\\src\\test"));
        logSB.append(createDir(logSB, "Games\\res\\drawables"));
        logSB.append(createDir(logSB, "Games\\res\\vectors"));
        logSB.append(createDir(logSB, "Games\\res\\icons"));

        logSB.append(createFile(logSB, "Games\\src\\main\\Main.java"));
        logSB.append(createFile(logSB, "Games\\src\\main\\Utils.java"));
        logSB.append(createFile(logSB, "Games\\temp\\temp.txt"));

        File file = new File("Games\\temp\\temp.txt");

        try (FileWriter fw = new FileWriter(file)) {
            fw.write(logSB.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String createDir(StringBuilder log, String path) {
        File file = new File(path);
        String result;
        if (file.mkdir())
            result = LocalDateTime.now() + " - Папка " + file.getName() + " создана\n";
        else result = "Не удалось создать папку " + file.getName() + "\n";
        return result;
    }

    static String createFile(StringBuilder log, String path) {
        String result = null;
        File file = new File(path);
        try {
            if (file.createNewFile())
                result = LocalDateTime.now() + " - Файл " + file.getName() + " создан\n";
            else result = "Не удалось создать файл " + file.getName() + "\n";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

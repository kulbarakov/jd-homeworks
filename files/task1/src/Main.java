import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        StringBuilder logSB = new StringBuilder();
        File file = new File("Games\\src");
        if (file.mkdir())
            logSB.append(LocalDateTime.now()).append(" - Папка ").append(file.getName()).append(" создана\n");
        file = new File("Games\\res");
        if (file.mkdir())
            logSB.append(LocalDateTime.now()).append(" - Папка ").append(file.getName()).append(" создана\n");
        file = new File("Games\\savegames");
        if (file.mkdir())
            logSB.append(LocalDateTime.now()).append(" - Папка ").append(file.getName()).append(" создана\n");
        file = new File("Games\\temp");
        if (file.mkdir())
            logSB.append(LocalDateTime.now()).append(" - Папка ").append(file.getName()).append(" создана\n");
        file = new File("Games\\src\\main");
        if (file.mkdir())
            logSB.append(LocalDateTime.now()).append(" - Папка ").append(file.getName()).append(" создана\n");
        file = new File("Games\\src\\test");
        if (file.mkdir())
            logSB.append(LocalDateTime.now()).append(" - Папка ").append(file.getName()).append(" создана\n");
        file = new File("Games\\src\\main\\Main.java");
        try {
            if (file.createNewFile())
                logSB.append(LocalDateTime.now()).append(" - Файл ").append(file.getName()).append(" создан\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        file = new File("Games\\src\\main\\Utils.java");
        try {
            if (file.createNewFile())
                logSB.append(LocalDateTime.now()).append(" - Файл ").append(file.getName()).append(" создан\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        file = new File("Games\\res\\drawables");
        if (file.mkdir())
            logSB.append(LocalDateTime.now()).append(" - Папка ").append(file.getName()).append(" создана\n");
        file = new File("Games\\res\\vectors");
        if (file.mkdir())
            logSB.append(LocalDateTime.now()).append(" - Папка ").append(file.getName()).append(" создана\n");
        file = new File("Games\\res\\icons");
        if (file.mkdir())
            logSB.append(LocalDateTime.now()).append(" - Папка ").append(file.getName()).append(" создана\n");
        file = new File("Games\\temp\\temp.txt");
        try {
            if (file.createNewFile())
                logSB.append(LocalDateTime.now()).append(" - Файл ").append(file.getName()).append(" создан\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter fw = new FileWriter(file)) {
            fw.write(logSB.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

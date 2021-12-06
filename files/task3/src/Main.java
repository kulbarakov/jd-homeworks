import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress gp1 = new GameProgress(100, 1, 10, 200);
        GameProgress gp2 = new GameProgress(75, 2, 7, 100);
        GameProgress gp3 = new GameProgress(30, 3, 3, 150);

        String fileGP1 = "gp1.dat";
        String fileGP2 = "gp2.dat";
        String fileGP3 = "gp3.dat";


        List<String> fileList = List.of(fileGP1, fileGP2, fileGP3);

        saveGame(fileGP1, gp1);
        saveGame(fileGP2, gp2);
        saveGame(fileGP3, gp3);

        zipFiles("Games\\gp.zip", fileList);
        openZip("Games\\gp.zip", "Games\\savegames");
        System.out.println(openProgress("Games\\savegames\\gp1.dat"));

    }

    static void saveGame(String fileName, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void zipFiles(String zipFile, List<String> files) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
            for (String file : files) {
                FileInputStream fis = new FileInputStream(file);
                ZipEntry entry = new ZipEntry(file);
                zos.putNextEntry(entry);// считываем содержимое файла в массив
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);// добавляем содержимое к архиву
                zos.write(buffer);// закрываем текущую запись для новой записи
                zos.closeEntry();
                fis.close();
                new File(file).delete();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void openZip(String pathToZip, String destPath) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(pathToZip))) {
            ZipEntry entry;
            String fileName;
            while ((entry = zis.getNextEntry()) != null) {
                fileName = entry.getName();
                FileOutputStream fos = new FileOutputStream(destPath + "\\" + fileName);
                for (int c = zis.read(); c != -1; c = zis.read()) {
                    fos.write(c);
                }
                fos.flush();
                zis.closeEntry();
                ;
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static GameProgress openProgress(String pathToDat) {
        GameProgress gameProgress = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathToDat))) {
            gameProgress = (GameProgress) ois.readObject();            
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return gameProgress;        
    }
}

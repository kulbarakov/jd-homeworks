import java.util.Arrays;
import java.util.List;

public class Main {

    //функциональный интерфейс для написания детерминированной (чистой) функции
    @FunctionalInterface
    public interface TextToDictFunc {
        List<String> apply(String originalText);
    }

    public static void main(String[] args) {

        final String originalText = "qewyrt upio sadfe ehty jekko cuvve pomma zuyna limbo scrip";

        //для формирования массива слов из исходного текста, его сортировки и конвертации в коллекцию
        //используются монады (Stream API)
        TextToDictFunc textToDictFunc = (text) -> Arrays.stream(originalText.split(" ")).sorted().toList();

        List<String> dictionary = textToDictFunc.apply(originalText);

        System.out.println(dictionary);
    }
}

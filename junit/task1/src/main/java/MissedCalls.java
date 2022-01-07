import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;

public class MissedCalls {
    private Contacts contacts;
    private Map<LocalDateTime, String> missedCalls = new TreeMap<>();

    public MissedCalls(Contacts contacts) {
        this.contacts = contacts;
    }

    public void addMissedCall(String phone) {
        missedCalls.put(LocalDateTime.now(), phone);
    }

    public void clearMissedCalls() {
        missedCalls.clear();
        //System.out.println("Список пропущенных вызовов очищен");
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        if (missedCalls.isEmpty()) {
            result.append("Пропущенных вызовов нет!");
        } else {
            for (Map.Entry<LocalDateTime, String> entry : missedCalls.entrySet()) {
                Contact contact = contacts.findContact(entry.getValue());
                if (contact == null) {
                    result.append(entry.getKey().format(formatter)).append(": ").append(entry.getValue()).append("\n");
                } else {
                    result.append(entry.getKey().format(formatter)).append(": ").append(contact).append("\n");
                }
            }
        }
        return result.toString();
    }
}

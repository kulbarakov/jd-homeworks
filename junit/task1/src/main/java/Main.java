import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Contacts contacts = new Contacts();
        MissedCalls missedCalls = new MissedCalls(contacts);

        while (true) {
            System.out.println("\n" +
                    "Меню:\n" +
                    "1. Добавить контакт\n" +
                    "2. Добавить пропущенный вызов\n" +
                    "3. Вывести все пропущенные вызовы\n" +
                    "4. Очистить пропущенные вызовы\n" +
                    "5. Удалить контакт\n" +
                    "6. Редактировать контакт\n" +
                    "0. Выход\n" +
                    "Выберите пункт из меню (1-6):");
            String input = scanner.nextLine();
            if (input.equals("0")) break;
            switch (input) {
                case "1":
                    addContact(contacts, scanner);
                    break;
                case "2":
                    System.out.println("Введите номер вызывающего"); //87776665544
                    String missedPhone = scanner.nextLine();
                    missedCalls.addMissedCall(missedPhone);
                    break;
                case "3":
                    System.out.println("Список пропущенных вызовов");
                    System.out.println(missedCalls);
                    break;
                case "4":
                    missedCalls.clearMissedCalls();
                    break;
                case "5":
                    System.out.println("Введите данные контакта (имя фамилия):");
                    input = scanner.nextLine();
                    String name = input.split(" ")[0];
                    String surname = input.split(" ")[1];
                    if (contacts.removeContact(name, surname)) {
                        System.out.println("Контакт " + name + " " + surname + " удален");
                    } else {
                        System.out.println("Такого контакта нет");
                    }
                    break;
                case "6":
                    System.out.println("Введите данные контакта для редактирования (имя фамилия):");
                    input = scanner.nextLine();
                    name = input.split(" ")[0];
                    surname = input.split(" ")[1];
                    Contact contactToEdit = contacts.findContact(name, surname);
                    if (contactToEdit == null)
                        System.out.println("Такого контакта нет");
                    else {
                        while (true) {
                            System.out.println("Выберите поле для редактирования");
                            System.out.println("1 - Имя");
                            System.out.println("2 - Фамилия");
                            System.out.println("3 - Телефон");
                            System.out.println("4 - Группа");
                            System.out.println("0 - Завершить");
                            input = scanner.nextLine();
                            if (input.equals("0")) break;
                            switch (input) {
                                case "1":
                                    contactToEdit.setName(scanner.nextLine());
                                    break;
                                case "2":
                                    contactToEdit.setSurname(scanner.nextLine());
                                    break;
                                case "3":
                                    contactToEdit.setPhone(scanner.nextLine());
                                    break;
                                case "4":
                                    Group group;
                                    switch (scanner.nextLine()) {
                                        case "работа":
                                            group = Group.WORK;
                                            break;
                                        case "друзья":
                                            group = Group.FRIEND;
                                            break;
                                        case "семья":
                                            group = Group.FAMILY;
                                            break;
                                        default:
                                            group = Group.valueOf(scanner.nextLine());
                                    }
                                    contactToEdit.setGroup(group);
                            }
                        }
                    }
                    break;
            }
        }

    }

    public static void addContact(Contacts contacts, Scanner scanner) {
        System.out.println("Введите данные контакта: имя, фамилия, номер телефона, группа контакта: работа, друзья, семья");
        //Иван, Иванов, 87776665544, работа
        String newContact = scanner.nextLine();
        String name = newContact.split(", ")[0];
        String surname = newContact.split(", ")[1];
        String phone = newContact.split(", ")[2];
        Group group;
        switch (newContact.split(", ")[3]) {
            case "работа":
                group = Group.WORK;
                break;
            case "друзья":
                group = Group.FRIEND;
                break;
            case "семья":
                group = Group.FAMILY;
                break;
            default:
                group = Group.valueOf(newContact.split(", ")[3]);
        }
        contacts.addContact(name, surname, phone, group);
    }

}
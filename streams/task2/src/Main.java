import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long countOfChild = persons.stream().filter(person -> person.getAge() < 18).count();
        System.out.println("Количество несовершеннолетних: " + countOfChild);

        List<String> mensForMilitary = persons.stream().filter(
                        person -> person.getAge() > 17 && person.getAge() < 28 && person.getSex() == Sex.Man)
                .map(Person::getFamily).collect(Collectors.toList());
        System.out.println("Количество военнообязанных: " + mensForMilitary.size());

        List<Person> workers = persons.stream().filter(
                        person -> person.getAge() > 17 && person.getEducation() == Education.HIGHER &&
                                ((person.getSex() == Sex.Man && person.getAge() < 66) ||
                                        (person.getSex() == Sex.Woman && person.getAge() < 61)))
                .sorted(Comparator.comparing(Person::getFamily)).collect(Collectors.toList());
        System.out.println("Количество работоспособных (с высшим образованием): " + workers.size());

    }
}

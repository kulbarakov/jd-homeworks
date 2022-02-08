import java.util.OptionalInt;

public class PersonBuilder {

    private String name;
    private String surname;
    private OptionalInt age;
    private String city;

    public PersonBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public PersonBuilder setAddress(String city) {
        this.city = city;
        return this;
    }

    public PersonBuilder setAge(int age) {
        if (age < 0) throw new IllegalArgumentException("Указан некорректный возраст");
        this.age = OptionalInt.of(age);
        return this;
    }


    public Person build() {
        Person person;
        if (name == null) throw new IllegalStateException("Укажите имя");
        if (surname == null) throw new IllegalStateException("Укажите фамилию");
        if (age.isPresent())
            person = new Person(name, surname, age.getAsInt());
        else
            person = new Person(name, surname);
        if (city != null) person.setAddress(city);
        return person;
    }
}

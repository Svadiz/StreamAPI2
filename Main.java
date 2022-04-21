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
        long count = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println(count);

        List<String> priz = persons.stream()
                .filter(person -> person.getAge() < 27 && person.getAge() > 18)
                .filter(person -> (person.getSex()).equals(Sex.MAN))
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(priz);

        List<Person> workers = persons.stream()
                .filter(person -> (person.getAge() < 60 && person.getAge() > 18 && (person.getSex()).equals(Sex.WOMAN)) | (person.getAge() < 65 && person.getAge() > 18 && (person.getSex()).equals(Sex.MAN)))
                .filter(person -> (person.getEducation().equals(Education.HIGHER)))

                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(workers);
    }
}

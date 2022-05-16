package ru.mycompany;

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

        long minors = persons.stream()
                .filter(age -> age.getAge() < 18 )
                .count();
        System.out.println("Количество несовершеннолетних = " + minors);

        List<String> conscripts = persons.stream()
                .filter(age -> age.getAge() > 18 )
                .filter(age -> age.getAge() < 27 )
                .filter(sex -> sex.getSex().equals(Sex.MAN))
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Количество призывников = " + conscripts.size());

        List<String> workable = persons.stream()
                .filter(age -> age.getAge() > 18 )
                .filter(age -> age.getAge() < 65 )
                .filter(education -> education.getEducation().equals(Education.HIGHER))
                .sorted(Comparator.comparing(Person::getFamily))//
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Количество потенциально работоспособных людей с высшим образованием = " + workable.size());
    }
}

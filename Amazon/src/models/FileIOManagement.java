package models;

import Main.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class FileIOManagement {

    private static final String peopleFileName = "people.bin";
    public static List<Person> _people = new ArrayList<Person>();
    private static final Person _admin = new Person("", "", "admin", "dev", Geschlecht.n);

    public static void synchronizePersonDatabase() {
        if (Files.exists(Paths.get(peopleFileName))) {
            importPeople(peopleFileName);
        } else {
            createPeople(peopleFileName);
        }
    }

    public static void importPeople(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName); ObjectInputStream ois = new ObjectInputStream(fis)) {
            _people = (List<Person>) ois.readObject();
        } catch (IOException e) {
            out.println("Etwas ist schief gelaufen! Bitte wenden Sie sich an den Callcenter-Support!");
        } catch (ClassNotFoundException e) {
            out.println("Klasse Person existiert nicht!");
        }
    }

    public static void createPeople(String fileName) {
        try {
            Files.createFile(Paths.get(fileName));
        } catch (IOException e) {
            out.println("Datei konnte nicht erzeugt werden!");
        }
        try (FileOutputStream fos = new FileOutputStream(fileName); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            _people.add(_admin);
            oos.writeObject(_people);
        } catch (IOException e) {
            out.println("Admin konnte nicht erzeugt werden!");
        }
    }

    public static void personAbspeichern() {
        try (FileOutputStream fos = new FileOutputStream(peopleFileName); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(_people);
        } catch (IOException e) {
            out.println("Personen konnte nicht abgespeichert werden!");
        }
    }

    public static void personAbspeichern(Person person) {
        try (FileOutputStream fos = new FileOutputStream(peopleFileName); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            _people.add(person);
            oos.writeObject(_people);
        } catch (IOException e) {
            out.println("Personen konnte nicht abgespeichert werden!");
        }
    }
}

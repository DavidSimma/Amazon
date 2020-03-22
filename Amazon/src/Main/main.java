package Main;

import models.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class main {

    private static Scanner reader = new Scanner(System.in);
    private static Shop shop = new Shop();
    public static Person p = new Person();
    private static Mail mail = new Mail();
    private static final String peopleFileName = "people.bin";
    private static List<Person> _people = new ArrayList<Person>();
    private static final Person _admin = new Person("", "", "admin", "dev", Geschlecht.n);

    public static void main(String[] args) throws InterruptedException {

        char answer, löschen;
        char antwort, antwort2, antwort3;
        boolean registrierungErfolgreich, anmeldungErfolgreich;
        synchronizePersonDatabase();

        for (Person s : _people){
            out.println(s.toString());
            out.println(s.get_adressen().toString());
            out.println(s.get_warenkorb().toString());
        }

        do {
            registrierungErfolgreich = false;
            out.print("Wollen Sie sich regsitrieren?[j/n]: ");
            antwort = reader.next().charAt(0);
            switch (antwort) {
                case 'j':
                    registrierungErfolgreich = true;
                    registriervorgang();
                    break;
                case 'n':
                    do {
                        registrierungErfolgreich = true;
                        anmeldungErfolgreich = false;
                        out.print("Wollen Sie sich anmelden[j/n]:");
                        antwort2 = reader.next().charAt(0);
                        switch (antwort2) {
                            case 'j':
                                anmeldungErfolgreich = true;
                                anmeldevorgang();
                                break;
                            case 'n':
                                anmeldungErfolgreich = false;
                                epischerCountdown();
                            default:
                                out.println("Tut mir leid, aber Sie haben einen falschen Buchstaben eingegeben");
                                break;
                        }


                    } while (!anmeldungErfolgreich);
                    break;
                default:
                    out.println("Tut mir leid, aber Sie haben einen falschen Buchstaben eingegeben");
                    break;
            }
        } while (!registrierungErfolgreich);


        do {

            out.println("s ... Shop öffnen");
            out.println("w ... Warenkorb öffnen");
            out.println("l ... Accout löschen");
            out.println("e ... Seite verlassen");
            out.print("Antwort: ");
            answer = reader.next().charAt(0);
            switch (answer) {
                case 'e':

                    epischerCountdown();
                    break;
                case 's':
                    shop.warenGenerieren(Shop.articleFileName);
                    out.println(shop.warenAusgeben());
                    do {


                        out.println("s ... Nach Ware Suchen");
                        out.println("w ... Warenkorb öffnen");
                        out.println("v ... Ware in Warenkorb verschieben");
                        out.println("z ... Shop verlassen");
                        out.print("Antwort: ");
                        answer = reader.next().charAt(0);
                        switch (answer) {
                            case 'w':

                                out.println(shop.get_aktuellePerson().get_warenkorb());
                                out.println(shop.get_aktuellePerson().get_warenkorb().getWaren());
                                do {
                                    out.println("b ... Bestellvorgang");
                                    out.println("x ... Warenkorb verlassen");
                                    out.print("Antwort: ");
                                    antwort3 = reader.next().charAt(0);
                                    switch (antwort3) {
                                        case 'b':
                                            out.println("Ihnen wird eine Bestätigungsemail der Transaktions geschickt!");
                                            mail.login("smtp.gmail.com", "465");
                                            try {
                                                mail.send("kleinAmazon@gmail.com",
                                                        "kleinAmazonSuupport", "Transaktionsbestätigung",
                                                        "Ihre Transaktion wurde erfolgreich abgesendet!");
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                        case 'x':
                                            out.println("Sie werden jtzt zur Hauptseite zurückgeleitet...");
                                            Thread.sleep(2000);
                                            clearConsole();
                                            break;
                                        default:
                                            out.println("Tut mir leid, aber Sie haben einen falschen Buchstaben eingegeben");
                                            break;
                                    }
                                } while (antwort3 != 'x');

                                break;
                            case 'z':
                                out.println("Sie werden jtzt zur Hauptseite zurückgeleitet...");
                                Thread.sleep(2000);
                                clearConsole();

                                break;
                            case 'v':
                                String name;
                                int anzahl;
                                out.print("Geben Sie den Namen des Produktes ein: ");
                                name = reader.next();
                                out.print("Geben Sie die Menge, der hinzuzufügenden Artikel ein: ");
                                anzahl = reader.nextInt();
                                shop.wareInWarenkorbVerschieben(name, anzahl);

                                break;

                            case 's':
                                out.print("Geben Sie den Namen des zu suchenden Prduktes ein: ");
                                String suche = reader.next();
                                out.println(shop.nachWarenSuchen(suche));


                                break;
                            default:
                                out.println("Tut mir leid, aber Sie haben einen falschen Buchstaben eingegeben");
                                break;
                        }
                    } while (answer != 'z');
                    break;
                case 'w':
                    out.println(p.get_warenkorb().toString());
                    out.println(p.get_warenkorb());

                    break;
                case 'l':
                    do {
                        out.print("Wollen Sie den Account wirklich löschen? [j/n]");
                        löschen = reader.next().toLowerCase().charAt(0);
                        switch (löschen) {
                            case 'j':
                                _people.remove(p);
                                answer = 'e';
                                break;
                            case 'n':

                                break;
                            default:
                                out.println("Tut mir leid, aber Sie haben einen falschen Buchstaben eingegeben");
                                break;
                        }
                    }while (löschen != 'j' || löschen != 'n');
            }
        } while (answer != 'e');


    }


    public static void registriervorgang() {

        out.print("Geben Sie Ihren Vornamen ein: ");
        String vn = reader.next();
        p.set_vorname(vn);
        out.print("Geben Sie Ihren Nachnamen ein: ");
        String nn = reader.next();
        p.set_nachname(nn);
        boolean geschl;
        String geschleht;
        do {
            geschl = false;
            out.print("Geben Sie Ihr Geschlecht an[m/w/n(nicht angegeben)]: ");
            geschleht = reader.next();
            if (geschleht.toLowerCase().equals("m") || geschleht.toLowerCase().equals("w") || geschleht.toLowerCase().equals("n")) {
                geschl = true;
            }
        } while (geschl == false);
        Geschlecht geschlecht = Geschlecht.valueOf(geschleht);
        p.set_geschlecht(geschlecht);
        String email;
        do {
            out.print("Bitte geben Sie Ihre Email-Adresse ein: ");
            email = reader.next();
        }while(!p.emailIsOK(email));
        p.set_email(email);
        boolean uebereinstimmung = false;
        String pw1 = null;
        do {
            out.print("Geben Sie Ihr Passwort ein: ");
            pw1 = reader.next();
            out.print("Geben SIe Ihr Passwort erneut ein: ");
            String pw2 = reader.next();
            if (pw1.equals(pw2)) {
                uebereinstimmung = true;
            } else {
                out.println("Passwort stimmt nicht überein. Geben Sie es erneut ein!");
            }
        } while (uebereinstimmung == false);
        p.set_passwort(pw1);

        char auswahlAdresse;
        do {
            out.print("Adresse eingeben?[j,n]: ");
            auswahlAdresse = reader.next().charAt(0);
            switch (auswahlAdresse) {
                case 'j':
                    p.add_Adresse(adresseEingeben());
                    break;
                case 'n':
                    break;
                default:
                    break;
            }
        } while (auswahlAdresse != 'n');
        try (FileOutputStream fos = new FileOutputStream(peopleFileName); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            _people.add(p);
            oos.writeObject(_people);
        } catch (IOException e) {
            out.println("Registrierung nicht erfolgreich, probieren Sie es erneut!");
        }
    }

    public static Adresse adresseEingeben() {
        Adresse a = new Adresse();

        out.print("Straße: ");
        a.set_straßenname(reader.next());
        out.print("Hausnummer: ");
        a.set_hausnummer(reader.next());
        out.print("Land: ");
        a.set_land(reader.next());
        out.print("Stadt: ");
        a.set_stadt(reader.next());
        out.print("Plz: ");
        a.set_plz(reader.next());

        return a;
    }


    public static void clearConsole() {
        for (int i = 0; i < 1000; i++) {
            out.println(" ");
        }

    }


    public static void epischerCountdown() {
        out.print("Programm wird geschlossen in: ");
        out.print("3");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        out.print(" ... 2");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        out.print(" ... 1");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        out.println(" ... bye");
        System.exit(0);
    }

    public static void anmeldevorgang() {
        String email, pw;
        do {
            do {
                out.print("Geben Sie Ihre Emailadresse ein: ");
                email = reader.next();
            }while (!emailIsAvailable(email));
            do {
                out.print("Bitte geben Sie Ihr Passwort ein: ");
                pw = reader.next();
            }while(!pwIsAvailable(pw));
        }while (!loginSuccesful(p));
    }

    public static void synchronizePersonDatabase() {
        if (Files.exists(Paths.get(peopleFileName))) {
            importPeople(peopleFileName);
        } else {
            createPeople(peopleFileName);
        }
    }

    public static void importPeople(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName); ObjectInputStream ois = new ObjectInputStream(fis)) {
            _people = (List<Person>)ois.readObject();
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

    public static boolean emailIsAvailable(String email){
        for(Person u : _people){
            if (u.get_email().equals(email)){
                p = u;
                return true;
            }
        }
        return false;
    }
    public static boolean pwIsAvailable(String pw){
        if (p.get_passwort().equals(pw)){
                return true;
            }
        return false;
    }
    public static boolean loginSuccesful(Person person){
        if(emailIsAvailable(person.get_email()) && pwIsAvailable(person.get_passwort())){
            return true;
        }
        out.println("EMail und/oder Passwort falsch!");
        return false;
    }

}

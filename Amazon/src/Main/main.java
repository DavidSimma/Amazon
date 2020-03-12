package Main;
import models.*;
import models.Person.Geschlecht;

import java.util.Scanner;

import static java.lang.System.out;

public class main {

    private static Scanner reader = new Scanner(System.in);
    private static Shop shop = new Shop();
    public static Person p = new Person();
    private static Mail mail = new Mail();

    public static void main(String[] args) throws InterruptedException {

        char answer;
        char antwort, antwort2, antwort3;
        boolean registrierungErfolgreich, anmeldungErfolgreich;

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
                                anmeldungErfolgreich = true;
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
            out.println("e ... Seite verlassen");
            out.print("Antwort: ");
            answer = reader.next().charAt(0);
            switch (answer) {
                case 'e':

                    epischerCountdown();
                    break;
                case 's':
                    shop.warenGenerieren(Shop.filename);
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
                default:
                    out.println("Tut mir leid, aber Sie haben einen falschen Buchstaben eingegeben");
                    break;
            }
        } while (answer != 'e');


    }


    public static void registriervorgang() {

        out.print("Geben Sie Ihren Vornamen ein: ");
        String vn = reader.next();
        p.set_vorname(vn);
        out.print("Geben Sie Ihren Nachnamen ein: ");
        String nn = reader.next();
        p.set_vorname(nn);
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

        out.print("Bitte geben Sie Ihre Email-Adresse ein: ");
        String email = reader.next();
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


    // Der Anmeldevorgang ist eigentlich nicht funktionsfähig, weil wir keine Datenbank von Benutzern haben
    // Wir haben die Methode trotzdem behalten, damit wir uns nicht jedes Mal neu Registrieren mussten
    public static void anmeldevorgang() {
        out.print("Geben Sie Ihren Namen ein: ");
        String antwort12 = reader.next();
        if (antwort12.equals("admin")) {
            out.print("Servus Amazon Chef! Wir brauchen dein PW: ");
            String pw12 = reader.next();
            if (pw12.equals("dev")) {
            } else {
                epischerCountdown();
            }
        } else {
            epischerCountdown();
        }

        // Test-Daten
        p.set_vorname("Vorname");
        p.set_nachname("Nachname");
        p.set_geschlecht(Geschlecht.n);
        p.set_email("davidseidl2002@gmail.com");
        p.set_passwort("david");

    }
}

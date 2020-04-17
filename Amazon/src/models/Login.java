package models;

import static java.lang.System.out;

import Main.*;

import java.util.Scanner;

public class Login {
    private static Scanner reader = new Scanner(System.in).useDelimiter("\\n");
    public static Person p = new Person();

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
        } while (!p.emailIsOK(email));
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
        FileIOManagement.personAbspeichern(p);
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

    public static void anmeldevorgang() {
        String email, pw;
        do {
            do {
                out.print("Geben Sie Ihre Emailadresse ein: ");
                email = reader.next();
            } while (!emailIsAvailable(email));
            do {
                out.print("Bitte geben Sie Ihr Passwort ein: ");
                pw = reader.next();
            } while (!pwIsAvailable(pw));
        } while (!loginSuccesful(p));
    }

    public static boolean emailIsAvailable(String email) {
        for (Person u : FileIOManagement.people) {
            if (u.get_email().equals(email)) {
                p = u;
                return true;
            }
        }
        return false;
    }

    public static boolean pwIsAvailable(String pw) {
        if (p.get_passwort().equals(pw)) {
            return true;
        }
        return false;
    }

    public static boolean loginSuccesful(Person person) {
        if (emailIsAvailable(person.get_email()) && pwIsAvailable(person.get_passwort())) {
            return true;
        }
        out.println("EMail und/oder Passwort falsch!");
        return false;
    }
}

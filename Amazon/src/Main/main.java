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

    private static Scanner reader = new Scanner(System.in).useDelimiter("\\n");
    private static Shop shop = new Shop();
    public static Person p = new Person();
    private static Mail mail = new Mail();
    private static final String peopleFileName = "people.bin";

    public static void main(String[] args) throws InterruptedException {

        char answer, löschen;
        char antwort, antwort2, antwort3;
        boolean registrierungErfolgreich, anmeldungErfolgreich;
        FileIOManagement.synchronizePersonDatabase();

        /*
        for (Person s : _people){
            out.println(s.toString());
            out.println(s.get_adressen().toString());
            out.println(s.get_warenkorb().toString());
            out.println(s.get_warenkorb().getWaren().toString());
        }

         */

        do {
            registrierungErfolgreich = false;
            out.print("Wollen Sie sich regsitrieren?[j/n]: ");
            antwort = reader.next().charAt(0);
            switch (antwort) {
                case 'j':
                    registrierungErfolgreich = true;
                    Login.registriervorgang();
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
                                Login.anmeldevorgang();
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
                    shop.warenGenerieren();
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

                                out.println(p.get_warenkorb());
                                out.println(p.get_warenkorb().getWaren());
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
                                                out.println("Bestellvorgang fehlgeschlagen");
                                            }
                                            p.get_warenkorb().getWaren().clear();
                                            out.println("Ihr Warenkorb wurde erfolgreich gelöscht!");

                                            FileIOManagement.personAbspeichern();
                                            break;
                                        case 'x':
                                            out.println("Sie werden jetzt zur Hauptseite zurückgeleitet...");
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
                                FileIOManagement.personAbspeichern();
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
                    out.println(p.get_warenkorb().getWaren().toString());

                    break;
                case 'l':
                    do {
                        out.print("Wollen Sie den Account wirklich löschen? [j/n]");
                        löschen = reader.next().toLowerCase().charAt(0);
                        switch (löschen) {
                            case 'j':
                                FileIOManagement._people.remove(p);
                                answer = 'e';
                                break;
                            case 'n':

                                break;
                            default:
                                out.println("Tut mir leid, aber Sie haben einen falschen Buchstaben eingegeben");
                                break;
                        }
                    } while (löschen != 'j' || löschen != 'n');
            }
        } while (answer != 'e');


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


}

package models;

import Main.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Shop {
    public List<Ware> _waren = new ArrayList<Ware>();

    public Person _aktuellePerson = new Person();

    public void warenGenerieren(){

        _waren.add(new Handy(315.00, "01", true, "Xiaomi Mi 9T", "Xiaomi",
                "Super Smartphone (Made in China Nice)", "Galaxy Blue", "2019",
                6.39, 64));
        _waren.add(new Handy(12.95, "02", true, "Einweghandy", "simvalley MOBILE",
                "Einweghandy für alle Fälle", "Schwarz", "1945",
                1.77, 1));
        _waren.add(new Laptop(1900.00, "03", true, "Huawei Matebook X Pro", "Huawei",
                "damit auch ihre Geldtasche leer wird", "Grau", "2019", 16
                , "Intel Core i7 8550U"));
        _waren.add(new Laptop(3499.99, "04", true, "Razer Blade Pro", "Razer",
                "eignet sich fantastisch für Microsoft Solitaire", "Graphitschwarz", "2019", 16
                , "Intel Core 9750H 6 Core"));
        _waren.add(new Bier(1.01, "05", true, "Gösser Märzen", "Gösser",
                "Ein Bier ist kein Bier", new Date(2021, 01, 01), 5.2));
        _waren.add(new Orangen(3.49, "06", true, "Orangen", "Südafrika",
                "Saftige Orangen zum verschlingen", new Date(2021, 01, 01), true));
        _waren.add(new Filme(13.99, "07", true, "Star Wars: Eine Neue Hoffnung", "George Lukas",
                "Seit der Vernichtung der Jedi-Ritter steht die Galaxie unter der grausamen Herrschaft des Imperiums." +
                        " Nur eine kleine Gruppe von Rebellen, angeführt von der schönen Prinzessin Leia, widersetzt sich " +
                        "der dunklen Macht. Als es ihnen gelingt diegeheimen Baupläne für den gefährlichen Todesstern zu " +
                        "entwenden, gerät das Imperium in Aufruhr. Durch Zufall gelangen die Pläne ausgerechnet in die" +
                        " Hände des Farmersjungen Luke Skywalker der spürt, dass er sein bisheriges Leben hinter sich " +
                        "lassen muss. Gemeinsam mit dem weisen Obi-Wan Kenobi, den Weltraumabenteurern Han Solo und Chewbacca " +
                        "sowie den Droiden R2-D2 und C-3PO nimmt er den Kampf gegen das mächtige Imperium auf. Es beginnt ein" +
                        " Wettlauf gegen die Zeit, denn der Kampfstern kann nur vor seiner Fertigstellung zerstört werden... " +
                        "und der finstere Darth Vader ist ihnen dicht auf den Fersen.", "mp4", 12, "1977"));
        _waren.add(new Musik(1.29, "08", true, "TNT", "AC/DC",
                "TNT ... Dynamite", "mp3", "AC/DC"));
    }

    public String warenAusgeben(){
        return _waren.toString();
    }

    public List<Ware> nachWarenSuchen(String warenName){
        List<Ware> gefundeneWaren = new ArrayList<Ware>();
        for(Ware w:_waren){
            if (w.get_name().contains(warenName)){
                gefundeneWaren.add(w);
            }
        }
        return gefundeneWaren;
    }

    public Person get_aktuellePerson(){
        return _aktuellePerson;
    }

    public void wareInWarenkorbVerschieben(String warenName, int menge){
        boolean inWarenKorbVorhanden = false;
        for(Warenkorbeintrag wke: _aktuellePerson.get_warenkorb().getWaren()){
           if(wke.get_ware().get_name().equals(warenName)){

              wke.set_menge(wke.get_menge() + menge);
              inWarenKorbVorhanden = true;
            }
        }
        if (!inWarenKorbVorhanden) {
            for (Ware w : _waren) {
                if (w.get_name().equals(warenName)) {

                    _aktuellePerson.get_warenkorb().getWaren().add(new Warenkorbeintrag(menge, w));
                }
            }
        }

    }

    public void wareAusWarenkorbEntfernen(String warenName, int menge){
        for(Warenkorbeintrag wke: main.p.get_warenkorb().getWaren()){
            if(wke.get_ware().equals(wke)){
                wke.set_menge(wke.get_menge() + menge);
            }
            else{

            }
        }
    }

}

package models;

import java.util.ArrayList;
import java.util.List;

public class Warenkorb {
    private List<Warenkorbeintrag> _waren = new ArrayList<Warenkorbeintrag>();
    private List<Warenkorbeintrag> _gefundeneWaren = new ArrayList<Warenkorbeintrag>();
    private double _preis = 0;
    private int _gesamtMenge = 0;

    public void addWaren(Warenkorbeintrag waren) {
        _waren.add(waren);

    }

    public boolean removeWaren(Warenkorbeintrag waren) {
        try {
            _waren.remove(waren);
        } catch (Exception e) {
            return false;
        }
        return true;

    }

    public List<Warenkorbeintrag> getWaren() {
        return _waren;
    }

    public void searchForWaren(String warenName) {
        for (Warenkorbeintrag w : _waren) {
            if (w.get_ware().get_name().contains(warenName)) {
                _gefundeneWaren.add(w);
            }
        }
    }

    public double gesamtpreis() {

        for (Warenkorbeintrag w : _waren) {
            _preis += (w.get_ware().get_preis() * w.get_menge());
        }
        return _preis;
    }

    public int gesamtWarenMenge() {
        for (Warenkorbeintrag w : _waren) {
            _gesamtMenge += w.get_menge();
        }
        return _gesamtMenge;
    }

    @Override
    public String toString() {
        return "Gesamtpreis: " + gesamtpreis() + "\n" +
                " gesamte Warenmenge: " + gesamtWarenMenge();
    }

}

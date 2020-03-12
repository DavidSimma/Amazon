package models;

public class Adresse {

    private String _straßenname, _hausnummer, _land,_stadt,_plz;

    public String get_straßenname() {
        return _straßenname;
    }

    public void set_straßenname(String _straßenname) {
        this._straßenname = _straßenname;
    }

    public String get_hausnummer() {
        return _hausnummer;
    }

    public void set_hausnummer(String _hausnummer) {
        this._hausnummer = _hausnummer;
    }

    public String get_land() {
        return _land;
    }

    public void set_land(String _land) {
        this._land = _land;
    }

    public String get_stadt() {
        return _stadt;
    }

    public void set_stadt(String _stadt) {
        this._stadt = _stadt;
    }

    public String get_plz() {
        return _plz;
    }

    public void set_plz(String _plz) {
        this._plz = _plz;
    }

    public Adresse(){this("", "", "", "", "");}
    public Adresse(String straßenname, String hausnummer, String land, String stadt, String plz) {
        set_straßenname(straßenname);
        set_hausnummer(hausnummer);
        set_land(land);
        set_stadt(stadt);
        set_plz(plz);
    }

    @Override
    public String toString() {
        return "Straße: " + get_straßenname() + ",  Hausnummer: " + get_hausnummer() + ", Land: " + get_land()
                + ", Stadt: " + get_stadt() + ", Postleitzahl: " + get_plz();
    }



}

package models;

public abstract class Ware {
    private double _preis;
    private String _artikelNr;
    private boolean _aufLager;
    private String _name;
    private String _hersteller;
    private String _beschreibung;

    public double get_preis() {
        return _preis;
    }
    public void set_preis(double preis) {
        if(preis >= 0.00)
            this._preis = preis;
    }

    public String get_artikelNr() {
        return _artikelNr;
    }
    public void set_artikelNr(String artikelNr) {
        this._artikelNr = artikelNr;
    }

    public boolean is_aufLager() {
        return _aufLager;
    }
    public void set_aufLager(boolean aufLager) {
        this._aufLager = aufLager;
    }

    public String get_name() {
        return _name;
    }
    public void set_name(String name) {
        this._name = name;
    }

    public String get_hersteller() {
        return _hersteller;
    }
    public void set_hersteller(String hersteller) {
        this._hersteller = hersteller;
    }

    public String get_beschreibung() {
        return _beschreibung;
    }
    public void set_beschreibung(String beschreibung) {
        this._beschreibung = beschreibung;
    }

    public Ware(){this(0.0, "", false, "", "", "");}
    public Ware(double preis, String artikelNr, boolean aufLager, String name, String hersteller, String beschreibung) {
        this._preis = preis;
        this._artikelNr = artikelNr;
        this._aufLager = aufLager;
        this._name = name;
        this._hersteller = hersteller;
        this._beschreibung = beschreibung;
    }

    @Override
    public String toString(){
        return "\n" + "ArtikelNr: " + get_artikelNr() + "\n" +
                "Artikelname: " + get_name() + "\n" +
                "auf Lager: "+is_aufLager()+"\n"+
                "Preis: "+get_preis()+"\n"+
                "Hersteller: "+get_hersteller()+"\n" +
                "Beschreibung: "+get_beschreibung();
    }
}

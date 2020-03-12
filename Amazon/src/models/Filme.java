package models;

public class Filme extends Medien {
    private int _altersfreigabe;
    private String _releaseJahr;

    public int get_altersfreigabe() {
        return _altersfreigabe;
    }

    public void set_altersfreigabe(int altersfreigabe) {
        if(altersfreigabe >= 0)
        this._altersfreigabe = altersfreigabe;
    }

    public String get_releaseJahr() {
        return _releaseJahr;
    }

    public void set_releaseJahr(String releaseJahr) {
        this._releaseJahr = releaseJahr;
    }

    public Filme(){this(0.0, "", false, "", "", "", "", 0, "");}
    public Filme(double preis, String artikelNr, boolean aufLager, String name, String hersteller, String beschreibung, String dateiForm, int altersfreigabe, String releaseJahr) {
        super(preis, artikelNr, aufLager, name, hersteller, beschreibung, dateiForm);
        set_altersfreigabe(altersfreigabe);
        set_releaseJahr(releaseJahr);
    }

    @Override
    public String toString(){
        return super.toString()+"\n"+
                "Altersfreigabe: "+get_altersfreigabe()+"\n"+
                "Veröffentlichungsjahr: "+get_releaseJahr() + "\n";
    }
}

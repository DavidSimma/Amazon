package models;

import java.util.Date;

public class Bier extends Nahrung {
    private double _alkoholgehalt;

    public double get_alkoholgehalt() {
        return _alkoholgehalt;
    }

    public void set_alkoholgehalt(double alkoholgehalt) {
        if(alkoholgehalt >= 0)
        this._alkoholgehalt = alkoholgehalt;
    }

    public Bier(){this(0.0, "", false, "", "", "", null, 0.0);}
    public Bier(double preis, String artikelNr, boolean aufLager, String name, String hersteller, String beschreibung, Date ablaufDatum, double alkoholgehalt) {
        super(preis, artikelNr, aufLager, name, hersteller, beschreibung, ablaufDatum);
        set_alkoholgehalt(alkoholgehalt);
    }

    @Override

    public String toString(){
        return super.toString()+"\n"+
                "Alkoholgehalt: "+get_alkoholgehalt() + "\n";
    }
}

package models;

import java.util.Date;

public class Orangen extends  Nahrung{
    private boolean _istBio;

    public boolean is_istBio() {
        return _istBio;
    }

    public void set_istBio(boolean istBio) {
        this._istBio = istBio;
    }

    public Orangen(){this(0.0, "", false, "", "", "", null, false);}
    public Orangen(double preis, String artikelNr, boolean aufLager, String name, String hersteller, String beschreibung, Date ablaufDatum, boolean istBio) {
        super(preis, artikelNr, aufLager, name, hersteller, beschreibung, ablaufDatum);
        set_istBio(istBio);
    }

    @Override
    public String toString(){
        return super.toString()+"\n"+
                "ist Bio: "+is_istBio() + "\n";
    }
}

package models;

import java.util.Date;

public abstract class Nahrung extends Ware {
    private Date _ablaufDatum = new Date();
    private Date _aktuelleZeit = java.util.Calendar.getInstance().getTime();

    public boolean is_abgelaufen() {
        if (_ablaufDatum.after(_aktuelleZeit)){
            return true;
        }
        else{
            return false;
        }
    }
    public void set_ablaufDatum(Date ablaufDatum) {
        this._ablaufDatum = ablaufDatum;
    }

    public Nahrung(){this(0.0, "", false, "", "", "", null);}
    public Nahrung(double preis, String artikelNr, boolean aufLager, String name, String hersteller, String beschreibung, Date ablaufDatum) {
        super(preis, artikelNr, aufLager, name, hersteller, beschreibung);
        set_ablaufDatum(ablaufDatum);
    }

    @Override
    public String toString(){
        return super.toString() + "\n"+
                "Ablaufdatum: "+is_abgelaufen();
    }
}

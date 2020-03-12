package models;

public class Handy extends Elektronikartikel {
    private double _bildschirmgröße;
    private int _speicher;

    public double get_bildschirmgröße() {
        return _bildschirmgröße;
    }
    public void set_bildschirmgröße(double bildschirmgröße) {
        if (bildschirmgröße >= 0)
        this._bildschirmgröße = bildschirmgröße;
    }

    public int get_speicher() {
        return _speicher;
    }
    public void set_speicher(int speicher) {
        if (speicher >= 0)
        this._speicher = speicher;
    }

    public Handy(){this(0.0, "", false, "", "", "", "", "", 0.0, 0);}
    public Handy(double preis, String artikelNr, boolean aufLager, String name, String hersteller, String beschreibung, String farbe, String modellJahr, double bildschirmgröße, int speicher) {
        super(preis, artikelNr, aufLager, name, hersteller, beschreibung, farbe, modellJahr);
        set_bildschirmgröße(bildschirmgröße);
        set_speicher(speicher);
    }

    @Override
    public String toString() {
        return super.toString()+"\n"+
                "Bilschrimgröße: "+get_bildschirmgröße()+"\n"+
                "Speicher: "+get_speicher() + "\n";
    }
}

package models;

public abstract class Elektronikartikel extends Ware {
    private String _farbe;
    private String _modellJahr;

    public String get_farbe() {
        return _farbe;
    }
    public void set_farbe(String farbe) {
        this._farbe = farbe;
    }

    public String get_modellJahr() {
        return _modellJahr;
    }
    public void set_modellJahr(String modellJahr) {
        this._modellJahr = modellJahr;
    }

    public Elektronikartikel(){this(0.0, "", false, "", "", "", "", "");}
    public Elektronikartikel(double preis, String artikelNr, boolean aufLager, String name, String hersteller, String beschreibung, String farbe, String modellJahr) {
        super(preis, artikelNr, aufLager, name, hersteller, beschreibung);
        set_farbe(farbe);
        set_modellJahr(modellJahr);
    }

    @Override
    public String toString(){
        return super.toString() + "\n" +
                "Farbe: "+get_farbe()+"\n"+
                "Modelljahr: "+get_modellJahr();
    }
}

package models;

public class Musik extends Medien {
    private String _interpret;

    public String get_interpret() {
        return _interpret;
    }

    public void set_interpret(String interpret) {
        this._interpret = interpret;
    }

    public Musik(){this(0.0, "", false, "", "", "", "", "");}
    public Musik(double preis, String artikelNr, boolean aufLager, String name, String hersteller, String beschreibung, String dateiForm, String interpret) {
        super(preis, artikelNr, aufLager, name, hersteller, beschreibung, dateiForm);
        set_interpret(interpret);
    }

    @Override
    public String toString(){
        return super.toString()+"\n"+
                "Interpret: "+get_interpret() + "\n";
    }
}

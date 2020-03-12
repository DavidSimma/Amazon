package models;

public abstract class Medien extends Ware {
    private String _dateiForm;

    public String get_dateiForm() {
        return _dateiForm;
    }
    public void set_dateiForm(String dateiForm) {
        this._dateiForm = dateiForm;
    }

    public Medien(){this(0.0, "", false, "", "", "", "");}
    public Medien(double preis, String artikelNr, boolean aufLager, String name, String hersteller, String beschreibung, String dateiForm) {
        super(preis, artikelNr, aufLager, name, hersteller, beschreibung);
        set_dateiForm(dateiForm);
    }

    @Override
    public String toString(){
        return super.toString()+"\n"+
                "Dateiform: "+get_dateiForm();
    }
}

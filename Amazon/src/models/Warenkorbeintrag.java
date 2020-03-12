package models;

public class Warenkorbeintrag {
    private int _menge;
    private Ware _ware;

    public int get_menge() {
        return _menge;
    }
    public void set_menge(int menge) {
        if(menge >= 0)
            this._menge = menge;
    }
    public Ware get_ware() {
        return _ware;
    }
    public void set_ware(Ware ware) {
        this._ware = ware;
    }

    public Warenkorbeintrag(){this(0,null);}
    public Warenkorbeintrag(int menge, Ware ware) {
        this._menge = menge;
        this._ware = ware;
    }
    @Override
    public String toString() {
        return "Ware: "+get_ware() +"\n"+
                " Menge: " + get_menge()+"\n"+"\n"+"\n";
    }





}

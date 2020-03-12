package models;

public class Laptop extends Elektronikartikel {
    private int _ram;
    private String _cpu;

    public int get_ram() {
        return _ram;
    }

    public void set_ram(int ram) {
        if(ram >= 0)
        this._ram = ram;
    }

    public String get_cpu() {
        return _cpu;
    }

    public void set_cpu(String cpu) {
        this._cpu = cpu;
    }

    public Laptop(){this(0.0, "", false, "", "", "", "", "", 0, "");}
    public Laptop(double preis, String artikelNr, boolean aufLager, String name, String hersteller, String beschreibung, String farbe, String modellJahr, int ram, String cpu) {
        super(preis, artikelNr, aufLager, name, hersteller, beschreibung, farbe, modellJahr);
        set_ram(ram);
        set_cpu(cpu);
    }

    @Override
    public String toString(){
        return super.toString() + "\n"+
                "RAM: "+get_ram()+"\n"+
                "CPU: "+get_cpu() + "\n";
    }
}

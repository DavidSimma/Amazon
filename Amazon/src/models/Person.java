package models;

import java.util.ArrayList;
import java.util.List;

public class Person{

    private List<Adresse> _adressen = new ArrayList<Adresse>();
    private String _vorname,_nachname,_email,_passwort;
    private Geschlecht _geschlecht;
    public enum Geschlecht{
        m, w, n;
    }
    private Warenkorb _warenkorb = new Warenkorb();


    public String get_vorname() {
        return _vorname;
    }
    public void set_vorname(String _vorname) {
        this._vorname = _vorname;
    }
    public String get_nachname() {
        return _nachname;
    }
    public void set_nachname(String _nachname) {
        this._nachname = _nachname;
    }
    public String get_email() {
        return _email;
    }
    public void set_email(String _email) {
        this._email = _email;
    }
    public String get_passwort() {
        return _passwort;
    }
    public void set_passwort(String _passwort) {
        this._passwort = _passwort;
    }
    public void set_geschlecht(Geschlecht geschlecht) {
        this._geschlecht = geschlecht;
    }
    public String Geschlecht(){
        if(_geschlecht == Geschlecht.m){
            return "Männlich";
        }else if (_geschlecht == Geschlecht.w){
            return "Weiblich";
        }
        else{
            return "nicht angegeben";
        }

    }

    public void add_Adresse(Adresse adresse){
        _adressen.add(adresse);
    }
    public boolean remove_Adresse(Adresse adresse){
        if(_adressen.remove(adresse)){
            return true;
        }
        else {
            return false;
        }
    }
    public List<Adresse> get_adressen(){
        return _adressen;
    }

    public Warenkorb get_warenkorb() {
        return _warenkorb;
    }


    public Person() {this("","","","", Geschlecht.n);}
    public Person(String vorname, String nachname, String email, String passwort, Geschlecht geschlecht) {
        set_vorname(vorname);
        set_nachname(nachname);
        set_email(email);
        set_passwort(passwort);
        set_geschlecht(geschlecht);
    }
    @Override
    public String toString() {
        return "Vorname: " + get_vorname() + ", Nachname: " + get_nachname() + ", Email:" + get_email() + ", Passwort: "
                + get_passwort() + ", Geschlecht: " + Geschlecht();
    }





}

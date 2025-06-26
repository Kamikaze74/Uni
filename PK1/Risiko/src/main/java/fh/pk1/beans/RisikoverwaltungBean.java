package fh.pk1.beans;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import fh.pk1.fachebene.AkzeptablesRisiko;
import fh.pk1.fachebene.ExtremesRisiko;
import fh.pk1.fachebene.InakzeptablesRisiko;
import fh.pk1.fachebene.Risiko;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RisikoverwaltungBean {

    private final ObservableList<Risiko> risikos = FXCollections.observableArrayList(); // new SimpleObjectProperty<LinkedList<Risiko>>();

    public ObjectProperty<LinkedList<Risiko>> getRisikosProperty() {    return risikos;  }
    public LinkedList<Risiko> getRisikos() {    return risikos.get();    }
    public void setRisikos(LinkedList<Risiko> risikos) {     this.risikos.set(risikos);    }

    public void add(AkzeptablesRisiko risiko) {
        risiko.setId(fixId());
        getRisikos().add(risiko);
    }
    public void add(InakzeptablesRisiko risiko) {
        risiko.setId(fixId());
        getRisikos().add(risiko);
    }
    public void add(ExtremesRisiko risiko) {
        risiko.setId(fixId());
        getRisikos().add(risiko);
    }

    public String zeigeRisiken() {
        aufSortieren();
        String ausgabe = "";
        for (Risiko a:getRisikos()){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            a.druckeDaten(baos);
            ausgabe += baos.toString();
        }
        return ausgabe;
    }

    public String sucheRisikoMitmaxRueckstellung() {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Risiko max = getRisikos().getFirst();
        for (Risiko a: getRisikos()){
            if(a.ermittleRueckstellung() > max.ermittleRueckstellung())
                max = a;
        }
        max.druckeDaten(baos);
        return baos.toString();
    }

    public float berechneSummeRueckstellungen() {

        float summe = 0.0f;
        for (Risiko a: getRisikos())
            summe += a.ermittleRueckstellung();

        return summe;
    }

    private void aufSortieren(){
        Collections.sort(getRisikos(), new Comparator<Risiko>() { public int compare(Risiko p1, Risiko p2){
            return Float.compare(p1.berechneRisikowert(), p2.berechneRisikowert());   }});
    }

    private int fixId() {
        int ausgabe = 0;
        for (Risiko a: getRisikos())
            ausgabe++;
        return ausgabe;
    }
}
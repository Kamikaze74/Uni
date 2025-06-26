package fh.pk1.beans;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.Comparator;

import fh.pk1.fachebene.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RisikoverwaltungBean {

    private final ObservableList<Risiko> risikos = FXCollections.observableArrayList(); // new SimpleObjectProperty<LinkedList<Risiko>>();

    public void add(AkzeptablesRisiko risiko) {
        risiko.setId(fixId());
        risikos.add(risiko);
    }
    public void add(InakzeptablesRisiko risiko) {
        risiko.setId(fixId());
        risikos.add(risiko);
    }
    public void add(ExtremesRisiko risiko) {
        risiko.setId(fixId());
        risikos.add(risiko);
    }

    public String zeigeRisiken() {
        aufSortieren();
        String ausgabe = "";
        for (Risiko a:risikos){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            a.druckeDaten(baos);
            ausgabe += baos.toString();
        }
        return ausgabe;
    }

    public String sucheRisikoMitmaxRueckstellung() {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Risiko max = risikos.get(0);
        for (Risiko a: risikos){
            if(a.ermittleRueckstellung() > max.ermittleRueckstellung())
                max = a;
        }
        max.druckeDaten(baos);
        return baos.toString();
    }

    public float berechneSummeRueckstellungen() {

        float summe = 0.0f;
        for (Risiko a: risikos)
            summe += a.ermittleRueckstellung();

        return summe;
    }

    private void aufSortieren(){
        Collections.sort(risikos, new Comparator<Risiko>() { public int compare(Risiko p1, Risiko p2){
            return Float.compare(p1.berechneRisikowert(), p2.berechneRisikowert());   }});
    }

    private int fixId() {
        int ausgabe = 0;
        for (Risiko a: risikos)
            ausgabe++;
        return ausgabe;
    }
}
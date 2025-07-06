package fh.pk1.gui.beans;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RisikoverwaltungBean {

    private final ObservableList<RisikoBean> risikos = FXCollections.observableArrayList(); // new SimpleObjectProperty<LinkedList<Risiko>>();

    public void add(RisikoBean risiko) {
        risiko.setId(fixId());
        risikos.add(risiko);
    }
    public void add(InakzeptablesRisikoBean risiko) {
        risiko.setId(fixId());
        risikos.add(risiko);
    }
    public void add(ExtremesRisikoBean risiko) {
        risiko.setId(fixId());
        risikos.add(risiko);
    }

    public Iterator<RisikoBean> iterator() {
        return risikos.iterator();
    }

    public String zeigeRisiken() {
        aufSortieren();
        String ausgabe = "";
        for (RisikoBean a:risikos){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            a.druckeDaten(baos);
            ausgabe += baos.toString();
        }
        return ausgabe;
    }

    public RisikoBean sucheRisikoMitmaxRueckstellung() {

        RisikoBean max = risikos.get(0);
        for (RisikoBean a: risikos){
            if(a.ermittleRueckstellung() > max.ermittleRueckstellung())
                max = a;
        }
        return max;
    }

    public float berechneSummeRueckstellungen() {

        float summe = 0.0f;
        for (RisikoBean a: risikos)
            summe += a.ermittleRueckstellung();

        return summe;
    }

    private void aufSortieren(){
        Collections.sort(risikos, new Comparator<RisikoBean>() { public int compare(RisikoBean p1, RisikoBean p2){
            return Float.compare(p1.berechneRisikowert(), p2.berechneRisikowert());   }});
    }

    private int fixId() {
        int ausgabe = 0;
        for (RisikoBean a: risikos)
            ausgabe++;
        return ausgabe;
    }
}
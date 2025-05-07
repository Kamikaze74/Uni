import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
public class Risikoverwaltung {

    LinkedList<Risiko> risikos = new LinkedList<Risiko>();

    public void aufnehmen(Risiko r) {
        
        risikos.add(r);
    }

    public String zeigeRisiken() {
        
        aufSortieren();
        String ausgabe = "";

        for (Risiko a: risikos){
            ausgabe += (a).druckeDaten();
        }

        return ausgabe;
    }

    public String sucheRisikoMitmaxRueckstellung() {

        Risiko max = risikos.getFirst();
        for (Risiko a: risikos){
            if(a.ermittleRueckstellung() > max.ermittleRueckstellung())
                max = a;
        }
        return max.druckeDaten();
    }

    public float berechneSummeRueckstellungen() {

        float summe = 0.0f;
        for (Risiko a: risikos)
            summe += a.ermittleRueckstellung();

        return summe;
    }

    private void aufSortieren(){

        Collections.sort(risikos, new Comparator<Risiko>() {
        public int compare(Risiko p1, Risiko p2) {
                return Float.compare(p1.berechneRisikowert(), p2.berechneRisikowert());
            }
        });
    }
}
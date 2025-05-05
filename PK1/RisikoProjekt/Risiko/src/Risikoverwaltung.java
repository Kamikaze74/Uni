import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
public class Risikoverwaltung {

    LinkedList<Risiko> risikos = new LinkedList<Risiko>();

    public void aufnehmen(Risiko r) {
        
        risikos.add(r);
    }

    public void zeigeRisiken() {
        
        aufSortieren();

        for (Risiko a: risikos){
            (a).druckeDaten();
        }
    }

    public void sucheRisikoMitmaxRueckstellung() {
       
        Risiko max = risikos.getFirst();
        for (Risiko a: risikos){
            if(a.ermittleRueckstellung() > max.ermittleRueckstellung())
                max = a;
        }
        max.druckeDaten();
    }

    public float berechneSummeRueckstellungen() {

        float summe = 0.0f;
        for (Risiko a: risikos)
            summe += a.ermittleRueckstellung();
       
        return summe;
    }

    public void aufSortieren(){

        Collections.sort(risikos, new Comparator<Risiko>() {
        public int compare(Risiko p1, Risiko p2) {
                return Float.compare(p1.berechneRisikowert(), p2.berechneRisikowert());
                // frag wegen den typen zb Float
            }
        });
    }
}
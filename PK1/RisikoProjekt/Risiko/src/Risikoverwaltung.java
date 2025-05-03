import java.util.LinkedList;
public class Risikoverwaltung{

    LinkedList<Risiko> risikos = new LinkedList<>();

    public void aufnehmen(Risiko r) {
        
        risikos.add(r);
    }

    public void zeigeRisiken() {
        
        for (Risiko a: risikos){
            a.druckeDaten();
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
}
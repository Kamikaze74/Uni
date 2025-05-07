import java.util.Iterator;
import java.util.LinkedList;
public class RisikoverwaltungUnsicher{

    LinkedList risikos = new LinkedList();

    public void aufnehmen(Risiko r) {
        
        risikos.add(r);
    }

    public void zeigeRisiken() {

        Iterator it = risikos.iterator();

        while(it.hasNext()){

            Object o = it.next();

            if(o instanceof AkzeptablesRisiko){

                AkzeptablesRisiko ak = (AkzeptablesRisiko) o;
                ak.druckeDaten();
            }

            else if(o instanceof InakzeptablesRisiko){

                InakzeptablesRisiko ak = (InakzeptablesRisiko) o;
                ak.druckeDaten();
            }

                else if(o instanceof ExtremesRisiko){

                    ExtremesRisiko ak = (ExtremesRisiko) o;
                    ak.druckeDaten();
                }
        }
    }

    public void sucheRisikoMitmaxRueckstellung() {
    
        Iterator it = risikos.iterator();
        Risiko max = (Risiko) risikos.getFirst();

        while(it.hasNext()){

            Object o = it.next();
        
            if(o instanceof AkzeptablesRisiko){
                AkzeptablesRisiko ak = (AkzeptablesRisiko) o;

                if(ak.ermittleRueckstellung() >= max.ermittleRueckstellung())
                    max = ak;
            }

            else if(o instanceof InakzeptablesRisiko){
                InakzeptablesRisiko ak = (InakzeptablesRisiko) o;

                if(ak.ermittleRueckstellung() >= max.ermittleRueckstellung())
                    max = ak;
            }

            else if(o instanceof ExtremesRisiko){
                    ExtremesRisiko ak = (ExtremesRisiko) o;

                    if(ak.ermittleRueckstellung() >= max.ermittleRueckstellung())
                        max = ak;
                }

            if(max instanceof AkzeptablesRisiko)
                max.druckeDaten();
    
            else if(max instanceof InakzeptablesRisiko)
                max.druckeDaten();

            else if(max instanceof ExtremesRisiko)
                max.druckeDaten();
        }
    }

    public float berechneSummeRueckstellungen() {

        Iterator it = risikos.iterator();
        float summe = 0.0f;

        while(it.hasNext()){

            Object o = it.next();

            if(o instanceof InakzeptablesRisiko){
                InakzeptablesRisiko ak = (InakzeptablesRisiko) o;
                summe += ak.ermittleRueckstellung();
            }
            else if(o instanceof ExtremesRisiko){
                ExtremesRisiko ak = (ExtremesRisiko) o;
                summe += ak.ermittleRueckstellung();
            }
        }
        return summe;
    }
}
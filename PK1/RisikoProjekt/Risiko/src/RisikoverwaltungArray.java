public class RisikoverwaltungArray{

    private Risiko[] risikos;

    public void aufnehmen(Risiko r) {
        //Collection c = null;
        
        if(risikos == null){
            risikos = new Risiko[1];
            risikos[0] = r;
        }
        else{
            Risiko[] nRisikos = new Risiko[risikos.length+1];
            for(int i = 0; i < risikos.length; i++)
                nRisikos[i] = risikos[i];
            nRisikos[nRisikos.length-1] = r;
            risikos = nRisikos;
        }
    }

    public void zeigeRisiken() {
        
        for(int i = 0; i < risikos.length; i++)
            risikos[i].druckeDaten();
    }

    public void sucheRisikoMitmaxRueckstellung() {
    
        Risiko nr = risikos[0];
        for(int i = 1; i < risikos.length; i++)
            if(nr.ermittleRueckstellung() > risikos[i].ermittleRueckstellung())
                nr = risikos[i];
        nr.druckeDaten();
    }

    public float berechneSummeRueckstellungen() {

        float summe = 0.0f;
        for(int i = 0; i < risikos.length; i++)
            summe += risikos[i].ermittleRueckstellung();
    
        return summe;
    }
}
import java.util.Objects;

public class InakzeptablesRisiko extends Risiko {
    
    private String massname;

    public InakzeptablesRisiko(String p_bezeichnung, float p_eintrittswahrscheinlichkeit, float p_kosten_im_schadensfall, String p_massname) {
        super(p_bezeichnung, p_eintrittswahrscheinlichkeit, p_kosten_im_schadensfall);
        this.massname = p_massname;
    }

    public String getMassname() {
        return this.massname;
    }
    @Override
    public float ermittleRueckstellung() {

        return berechneRisikowert();
    }
    @Override
    public void druckeDaten() {

    }
    @Override
    public boolean equals(Object o) {

        if(this == o && o != null && o instanceof InakzeptablesRisiko){

            InakzeptablesRisiko ak = (InakzeptablesRisiko) o;
            if(getBezeichnung().equals(ak.getBezeichnung()) && getEintrittswahrscheinlichkeit() == ak.getEintrittswahrscheinlichkeit() && getKosten_im_schadenfall() == ak.getKosten_im_schadenfall() && massname.equals(ak.massname))
            return true;
        }
        return false;
    }
    @Override
    public int hashCode(){
    
        return Objects.hash(getBezeichnung(), getEintrittswahrscheinlichkeit(), getKosten_im_schadenfall(), getMassname());
    }
}
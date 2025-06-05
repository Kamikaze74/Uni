package PK1.RisikoJoption.src;
import java.io.*;
import java.util.Formatter;
import java.util.Objects;
public class AkzeptablesRisiko extends Risiko{

    public AkzeptablesRisiko(String bezeichnung, float eintrittswahrscheinlichkeit, float kosten_im_schadensfall, int setId) {
        super(bezeichnung, eintrittswahrscheinlichkeit, kosten_im_schadensfall,setId);
    }
    
    @Override
    public float ermittleRueckstellung() {
        return 0.2f;
    }

    public String druckeDatenMitString() {
        try (Formatter formatter = new Formatter()) {
            formatter.format("Id %d %s \"%s\" aus %d/%d;\n Risikowert %.2f; Rueckstellung %.2f\n", this.getId(), this.getClass().getName(), this.getBezeichnung(), this.getErstellungsdatum().getMonthValue(), this.getErstellungsdatum().getYear(), this.berechneRisikowert(), this.ermittleRueckstellung());
            return formatter.toString();
        }
    }

    public void druckeDaten(OutputStream stream) {
        
        try(OutputStreamWriter osw = new OutputStreamWriter(stream);
            Formatter formatter = new Formatter()){

            formatter.format("Id %d %s \"%s\" aus %d/%d;\n Risikowert %.2f; Rueckstellung %.2f\n", this.getId(), this.getClass().getName(), this.getBezeichnung(), this.getErstellungsdatum().getMonthValue(), this.getErstellungsdatum().getYear(), this.berechneRisikowert(), this.ermittleRueckstellung());
            osw.write(formatter.toString());

        } catch (IOException e){e.printStackTrace(); System.out.print("IOException");}

    }

    @Override
    public boolean equals(Object o) {

        if(this == o && o != null && o instanceof AkzeptablesRisiko){

            AkzeptablesRisiko ak = (AkzeptablesRisiko) o;
            if(getBezeichnung().equals(ak.getBezeichnung()) && getEintrittswahrscheinlichkeit() == ak.getEintrittswahrscheinlichkeit() && getKosten_im_schadenfall() == ak.getKosten_im_schadenfall())
            return true;
        }
        return false;
    }
    @Override
    public int hashCode(){

    return Objects.hash(getBezeichnung(), getEintrittswahrscheinlichkeit(), getKosten_im_schadenfall());
    }
}
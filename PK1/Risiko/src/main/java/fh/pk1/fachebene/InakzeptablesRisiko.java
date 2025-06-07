package fh.pk1.fachebene;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Formatter;
import java.util.Objects;

public class InakzeptablesRisiko extends Risiko{
    
    private String maßnahme;

    public InakzeptablesRisiko(String bezeichnung, float eintrittswahrscheinlichkeit, float kosten_im_schadensfall, String maßnahme) {
        super(bezeichnung, eintrittswahrscheinlichkeit, kosten_im_schadensfall);
        this.maßnahme = maßnahme;
    }

    public String getMaßnahme() {
        return this.maßnahme;
    }
    @Override
    public float ermittleRueckstellung() {

        return berechneRisikowert();
    }
    public String druckeDatenMitString() {
        try (Formatter formatter = new Formatter()) {
            formatter.format("Id %d %s \"%s\" aus %d/%d;\n Risikowert %.2f; Rueckstellung %.2f;\nMaßnahme \"%s\"\n", this.getId(), this.getClass().getName(), this.getBezeichnung(), this.getErstellungsdatum().getMonthValue(), this.getErstellungsdatum().getYear(), this.berechneRisikowert(), this.ermittleRueckstellung(), maßnahme);
            return formatter.toString();
        }
    }
    public void druckeDaten(OutputStream stream) {
        
        try(OutputStreamWriter osw = new OutputStreamWriter(stream)
            ;Formatter formatter = new Formatter()){
            
            formatter.format("Id %d %s \"%s\" aus %d/%d;\n Risikowert %.2f; Rueckstellung %.2f;\nMaßnahme \"%s\"\n", this.getId(), this.getClass().getName(), this.getBezeichnung(), this.getErstellungsdatum().getMonthValue(), this.getErstellungsdatum().getYear(), this.berechneRisikowert(), this.ermittleRueckstellung(), maßnahme);
            osw.write(formatter.toString());

        } catch (IOException e){e.printStackTrace(); System.out.print("IOException");}

    }
    @Override
    public boolean equals(Object o) {

        if(this == o && o != null && o instanceof InakzeptablesRisiko){

            InakzeptablesRisiko ak = (InakzeptablesRisiko) o;
            if(getBezeichnung().equals(ak.getBezeichnung()) && getEintrittswahrscheinlichkeit() == ak.getEintrittswahrscheinlichkeit() && getKosten_im_schadenfall() == ak.getKosten_im_schadenfall() && maßnahme.equals(ak.maßnahme))
            return true;
        }
        return false;
    }
    @Override
    public int hashCode(){
    
        return Objects.hash(getBezeichnung(), getEintrittswahrscheinlichkeit(), getKosten_im_schadenfall(), getMaßnahme());
    }
}
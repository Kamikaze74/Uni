package fh.pk1.fachebene;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Formatter;
import java.util.Objects;

public class ExtremesRisiko extends InakzeptablesRisiko{

    private float versicherungsbeitrag;

    public ExtremesRisiko(String bezeichnung, float eintrittswahrscheinlichkeit, float kosten_im_schadensfall, String massname, float versicherungsbeitrag) {
        super(bezeichnung, eintrittswahrscheinlichkeit, kosten_im_schadensfall, massname);
        this.versicherungsbeitrag = versicherungsbeitrag;
    }

    public void setVersicherungsbeitrag(float versicherungsbeitrag){ this.versicherungsbeitrag = versicherungsbeitrag;}
    public float getVersicherungsbeitrag() {
        return versicherungsbeitrag;
    }
    @Override
    public float ermittleRueckstellung() {
        return versicherungsbeitrag;
    }
    public String druckeDatenString() {
        try (Formatter formatter = new Formatter()) {
            formatter.format("Id %d %s \"%s\" aus %d/%d;\nRisikowert %.2f; Versicherungsbeitrag %.2f;\nMaßnahme \"%s\"\n", this.getId(), this.getClass().getName(), this.getBezeichnung(), this.getErstellungsdatum().getMonthValue(), this.getErstellungsdatum().getYear(), this.berechneRisikowert(), this.getVersicherungsbeitrag(), this.getMassname());
            return formatter.toString();
        }
    }
    public void druckeDaten(OutputStream stream) {
        
        try(OutputStreamWriter osw = new OutputStreamWriter(stream);
            Formatter formatter = new Formatter()){
            
            formatter.format("Id %d %s \"%s\" aus %d/%d;\nRisikowert %.2f; Versicherungsbeitrag %.2f;\nMaßnahme \"%s\"\n", this.getId(), this.getClass().getName(), this.getBezeichnung(), this.getErstellungsdatum().getMonthValue(), this.getErstellungsdatum().getYear(), this.berechneRisikowert(), this.getVersicherungsbeitrag(), this.getMassname());
            osw.write(formatter.toString());

        } catch (IOException e){e.printStackTrace(); System.out.print("IOException");}

    }
    @Override
    public boolean equals(Object o) {

        if(super.equals(o)){
        
            ExtremesRisiko ak = (ExtremesRisiko) o;
            if(versicherungsbeitrag == ak.versicherungsbeitrag)
                return true;
        }
        return false;
    }
    @Override
    public int hashCode() {
        
        return Objects.hash(super.hashCode(), versicherungsbeitrag);
    }
}
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Formatter;
import java.util.Objects;

public class ExtremesRisiko extends InakzeptablesRisiko {

    private float versicherungsbeitrag;

    public ExtremesRisiko(String p_bezeichnung, float p_eintrittswahrscheinlichkeit, float p_kosten_im_schadensfall, String p_massname, float p_versicherungsbeitrag) {
        super(p_bezeichnung, p_eintrittswahrscheinlichkeit, p_kosten_im_schadensfall, p_massname);
        this.versicherungsbeitrag = p_versicherungsbeitrag;
    }

    public float getVersicherungsbeitrag() {
        return versicherungsbeitrag;
    }
    @Override
    public float ermittleRueckstellung() {
        return versicherungsbeitrag;
    }
    public String druckeDatenMitString() {
        Formatter formatter = new Formatter();
        formatter.format("Id %d %s \"%s\" aus %d/%d;\nRisikowert %.2f; Versicherungsbeitrag %.2f;\nMaßnahme \"%s\"\n", this.getId(), this.getClass().getName(), this.getBezeichnung(), this.getErstellungsdatum().getMonthValue(), this.getErstellungsdatum().getYear(), this.berechneRisikowert(), this.getVersicherungsbeitrag(), this.getMassname());
        return formatter.toString();
    }
    public void druckeDaten(OutputStream stream) {
        
        try(OutputStreamWriter osw = new OutputStreamWriter(stream)){
            Formatter formatter = new Formatter();
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
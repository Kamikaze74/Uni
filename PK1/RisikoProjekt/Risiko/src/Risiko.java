import java.io.OutputStream;
<<<<<<< HEAD
import java.io.Serializable;
import java.time.LocalDate;

public abstract class Risiko implements Serializable{
    
=======
import java.time.LocalDate;
public abstract class Risiko {
    
    private static int count = 0;
>>>>>>> 273e5e8 (ka)
    private int id;
    private String bezeichnung;
    private float eintrittswahrscheinlichkeit;
    private float kosten_im_schadenfall;
    private LocalDate erstellungsdatum;

<<<<<<< HEAD
    public Risiko(String bezeichnung, float eintrittswahrscheinlichkeit, float kosten_im_schadenfall, int setId) {
        this.id = setId;
=======
    public Risiko(String bezeichnung, float eintrittswahrscheinlichkeit, float kosten_im_schadenfall) {
        this.id = count++;
>>>>>>> 273e5e8 (ka)
        this.bezeichnung = bezeichnung;
        this.eintrittswahrscheinlichkeit = eintrittswahrscheinlichkeit;
        this.kosten_im_schadenfall = kosten_im_schadenfall;
        this.erstellungsdatum = LocalDate.now();
    }

    public int getId() {
    return id;
    }
    public String getBezeichnung() {
        return bezeichnung;
    }
    public float getEintrittswahrscheinlichkeit() {
        return eintrittswahrscheinlichkeit;
    }
    public float getKosten_im_schadenfall() {
        return kosten_im_schadenfall;
    }
    public LocalDate getErstellungsdatum() {
        return erstellungsdatum;
    }
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
    public void setEintrittswahrscheinlichkeit(float p_eintrittswahrscheinlichkeit) {
        this.eintrittswahrscheinlichkeit = p_eintrittswahrscheinlichkeit;
    }
    public void setKosten_im_schadenfall(float p_kosten_im_schadenfall) {
        this.kosten_im_schadenfall = p_kosten_im_schadenfall;
    }
    public float berechneRisikowert(){
        return eintrittswahrscheinlichkeit * kosten_im_schadenfall;
    }
    
    public abstract float ermittleRueckstellung();
    public abstract void druckeDaten(OutputStream stream);
}

package fh.pk1.beans;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Formatter;
import java.util.Objects;

import fh.pk1.fachebene.*;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

public class ExtremesRisikoBean extends InakzeptablesRisikoBean{

    private FloatProperty versicherungsbeitrag = new SimpleFloatProperty();

    public float getVersicherungsbeitrag(){ return versicherungsbeitrag.get(); }
    public FloatProperty getPropertyVersicherunsbeitrag(){  return versicherungsbeitrag;    }
    public void setVersicherungsbeitrag(float versicherungsbeitrag){ this.versicherungsbeitrag.set(versicherungsbeitrag);   }

    @Override
    public float ermittleRueckstellung() {
        return versicherungsbeitrag.get();
    }

    @Override
    public void druckeDaten(OutputStream stream) {
        try (OutputStreamWriter osw = new OutputStreamWriter(stream);
            Formatter formatter = new Formatter(osw)) {

            formatter.format(
                "Id %d %s \"%s\" aus %d/%d;\nRisikowert %.2f; Versicherungsbeitrag %.2f;\nMaßnahme \"%s\"\n",
                getId(),
                getClass().getName(),
                getBezeichnung(),
                getErstellungsdatum().getMonthValue(),
                getErstellungsdatum().getYear(),
                berechneRisikowert(),
                getVersicherungsbeitrag(),
                getMaßnahme()
            );
            formatter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public ExtremesRisiko toFE(){
        ExtremesRisiko risiko = new ExtremesRisiko(getBezeichnung(), getEintrittswahrscheinlichkeit(), getKosten_im_schadenfall(), getMaßnahme(), getVersicherungsbeitrag());
        risiko.setVersicherungsbeitrag(getVersicherungsbeitrag());
        risiko.setErstellungsdatum(getErstellungsdatum());
        return risiko;
    }
    @Override
    public boolean fromFE(Risiko risiko) {
        if (!(risiko instanceof ExtremesRisiko)) return false;
        ExtremesRisiko that = (ExtremesRisiko) risiko;
        setBezeichnung(that.getBezeichnung());setEintrittswahrscheinlichkeit(that.getEintrittswahrscheinlichkeit());
        setKosten_im_schadenfall(that.getKosten_im_schadenfall());setMaßnahme(that.getBezeichnung());setErstellungsdatum(that.getErstellungsdatum());
        setId(that.getId());setVersicherungsbeitrag(that.getVersicherungsbeitrag());
        return true;
    }

    @Override
    public boolean equals(Object o) {

        if(this == o ) return true;
        if(!super.equals(o)) return false;
        if(o.getClass() != getClass()) return false;

        ExtremesRisikoBean that = (ExtremesRisikoBean) o;
        return Float.compare(getVersicherungsbeitrag(), that.getVersicherungsbeitrag()) == 0;
    }

    @Override
    public int hashCode() {
        
        return Objects.hash(super.hashCode(), getVersicherungsbeitrag());
    }
}

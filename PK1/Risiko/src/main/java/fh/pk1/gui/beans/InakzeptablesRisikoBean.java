package fh.pk1.gui.beans;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Formatter;
import java.util.Objects;

import fh.pk1.fachebene.InakzeptablesRisiko;
import fh.pk1.fachebene.Risiko;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InakzeptablesRisikoBean extends RisikoBean {

    private final StringProperty maßnahme = new SimpleStringProperty();

    public String getMaßnahme() {   return maßnahme.get() != null ? maßnahme.get() : "";    }
    public StringProperty getPropertyMaßnahme() {   return maßnahme;  }
    public void setMaßnahme(String maßnahme) { this.maßnahme.set(maßnahme);}

    @Override
    public Risiko toFE() {
        InakzeptablesRisiko risiko = new InakzeptablesRisiko(getBezeichnung(), getEintrittswahrscheinlichkeit(), getKosten_im_schadenfall(), getMaßnahme());
        risiko.setErstellungsdatum(getErstellungsdatum());
        return risiko;
    }

    @Override
    public boolean fromFE(Risiko risiko) {
        if(risiko.getClass() != InakzeptablesRisiko.class) return false;
        InakzeptablesRisiko that = (InakzeptablesRisiko) risiko;
        setBezeichnung(that.getBezeichnung());setEintrittswahrscheinlichkeit(that.getEintrittswahrscheinlichkeit());
        setKosten_im_schadenfall(that.getKosten_im_schadenfall());setMaßnahme(that.getMaßnahme());setErstellungsdatum(that.getErstellungsdatum());
        setId(that.getId());
        return true;
    }

    @Override
    public float ermittleRueckstellung() {  return berechneRisikowert();    }

    @Override
    public void druckeDaten(OutputStream stream) {
        try (OutputStreamWriter osw = new OutputStreamWriter(stream);
            Formatter formatter = new Formatter(osw)) {

            formatter.format(
                "Id %d %s \"%s\" aus %d/%d; Rueckstellung %.2f; Maßnahme \"%s\"\n",
                getId(),
                getClass().getName(),
                getBezeichnung(),
                getErstellungsdatum().getMonthValue(),
                getErstellungsdatum().getYear(),
                ermittleRueckstellung(),
                getMaßnahme()
            );
            formatter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
}


    @Override
    public boolean equals(Object o) {

        if(this == o ) return true;
        if(!super.equals(o)) return false;
        if (o.getClass() != getClass()) return false;
        InakzeptablesRisikoBean that = (InakzeptablesRisikoBean) o;
        return Objects.equals(getMaßnahme(), that.getMaßnahme());

    }
    @Override
    public int hashCode(){

        return Objects.hash(getBezeichnung(), getEintrittswahrscheinlichkeit(), getKosten_im_schadenfall(), getMaßnahme());
    }
}

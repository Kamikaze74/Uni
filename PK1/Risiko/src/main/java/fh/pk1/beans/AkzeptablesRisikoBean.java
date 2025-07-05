package fh.pk1.beans;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Formatter;
import java.util.Objects;

import fh.pk1.fachebene.*;

public class AkzeptablesRisikoBean extends RisikoBean{
    
    @Override
    public float ermittleRueckstellung() {
        return 0.2f;
    }

    @Override
    public void druckeDaten(OutputStream stream) {
        try (OutputStreamWriter osw = new OutputStreamWriter(stream);
            Formatter formatter = new Formatter(osw)) {
    
            formatter.format(
                "%nId %d %s \"%s\" aus %d/%d;\nRisikowert %.2f;\n",
                getId(),
                getClass().getName(),
                getBezeichnung(),
                getErstellungsdatum().getMonthValue(),
                getErstellungsdatum().getYear(),
                berechneRisikowert()
            );
            formatter.flush();
    
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException beim Schreiben von Risiko-Daten");
        }
    }
    

    @Override
    public boolean equals(Object o) {

        if(this == o ) return true;
        if(!super.equals(o)) return false;
        if(o.getClass() != getClass()) return false;

        return true;
    }

    @Override
    public int hashCode(){
        return Objects.hash(getBezeichnung(), getEintrittswahrscheinlichkeit(), getKosten_im_schadenfall());
    }

    @Override
    public AkzeptablesRisiko toFE() {
        AkzeptablesRisiko risiko = new AkzeptablesRisiko(getBezeichnung(), getEintrittswahrscheinlichkeit(), getKosten_im_schadenfall());
        risiko.setErstellungsdatum(getErstellungsdatum());
        return risiko;
}

    @Override
    public boolean fromFE(Risiko risiko) {
        if (!(risiko instanceof AkzeptablesRisiko)) return false;
        setId(risiko.getId());
        setBezeichnung(risiko.getBezeichnung());
        setEintrittswahrscheinlichkeit(risiko.getEintrittswahrscheinlichkeit());
        setKosten_im_schadenfall(risiko.getKosten_im_schadenfall());
        setErstellungsdatum(risiko.getErstellungsdatum());
        return true;
    }
}
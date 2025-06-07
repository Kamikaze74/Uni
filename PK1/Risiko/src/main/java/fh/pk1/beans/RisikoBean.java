package fh.pk1.beans;

import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Objects;

import fh.pk1.fachebene.*;
import javafx.beans.property.*;

public abstract class RisikoBean{
    
    private final IntegerProperty id = new SimpleIntegerProperty();     // Wieso final??? && Sind Properties Serializable??Nein
    private final StringProperty bezeichnung = new SimpleStringProperty();
    private final FloatProperty eintrittswahrscheinlichkeit = new SimpleFloatProperty();
    private final FloatProperty kosten_im_schadenfall = new SimpleFloatProperty();

    private final ObjectProperty<LocalDate> erstellungsdatum = new SimpleObjectProperty<>(LocalDate.now());

    public IntegerProperty getIdProperty() {    return id;  }
    public int getId() {    return id.get();    }
    public void setId(int id) {     this.id.set(id);    }

    public StringProperty getBezeichnungProperty() {    return bezeichnung; }
    public String getBezeichnung() {    return bezeichnung.get();   }
    public void setBezeichnung(String bezeichnung) {    this.bezeichnung.set(bezeichnung);  }

    public FloatProperty getEintrittswahrscheinlichkeitProperty() {     return eintrittswahrscheinlichkeit;     }
    public float getEintrittswahrscheinlichkeit() {     return eintrittswahrscheinlichkeit.get();   }
    public void setEintrittswahrscheinlichkeit(float eintrittswahrscheinlichkeit) {     this.eintrittswahrscheinlichkeit.set(eintrittswahrscheinlichkeit);  }
    
    public FloatProperty getKosten_im_schadenfallProperty() {   return kosten_im_schadenfall;   }
    public float getKosten_im_schadenfall() {   return kosten_im_schadenfall.get();     }
    public void setKosten_im_schadenfall(float p_kosten_im_schadenfall) {   this.kosten_im_schadenfall.set(p_kosten_im_schadenfall);    }

    public ObjectProperty<LocalDate> erstellungsdatumProperty() { return erstellungsdatum; }
    public LocalDate getErstellungsdatum() { return erstellungsdatum.get(); }
    public void setErstellungsdatum(LocalDate datum) { this.erstellungsdatum.set(datum); }

    
    public float berechneRisikowert(){
        return getEintrittswahrscheinlichkeit() * getKosten_im_schadenfall();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AkzeptablesRisikoBean that = (AkzeptablesRisikoBean) o;
        return Float.compare(that.getEintrittswahrscheinlichkeit(), getEintrittswahrscheinlichkeit()) == 0 &&
                Float.compare(that.getKosten_im_schadenfall(), getKosten_im_schadenfall()) == 0 &&
                Objects.equals(getBezeichnung(), that.getBezeichnung());
    }
    
    public abstract Risiko toFE();
    public abstract boolean fromFE(Risiko risiko);
    
    public abstract float ermittleRueckstellung();
    public abstract void druckeDaten(OutputStream stream);
}
package fh.pk1.fachebene;

import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDate;

public abstract class Risiko implements Serializable {

    private int id;
    private String bezeichnung;
    private float eintrittswahrscheinlichkeit;
    private float kosten_im_schadenfall;
    private LocalDate erstellungsdatum;

    public Risiko(String bezeichnung, float eintrittswahrscheinlichkeit, float kosten_im_schadenfall) {
        this.bezeichnung = bezeichnung;
        this.eintrittswahrscheinlichkeit = eintrittswahrscheinlichkeit;
        this.kosten_im_schadenfall = kosten_im_schadenfall;
        this.erstellungsdatum = LocalDate.now();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getBezeichnung() { return bezeichnung; }
    public void setBezeichnung(String bezeichnung) { this.bezeichnung = bezeichnung; }

    public float getEintrittswahrscheinlichkeit() { return eintrittswahrscheinlichkeit; }
    public void setEintrittswahrscheinlichkeit(float eintrittswahrscheinlichkeit) {
        this.eintrittswahrscheinlichkeit = eintrittswahrscheinlichkeit;
    }

    public float getKosten_im_schadenfall() { return kosten_im_schadenfall; }
    public void setKosten_im_schadenfall(float kosten_im_schadenfall) {
        this.kosten_im_schadenfall = kosten_im_schadenfall;
    }

    public LocalDate getErstellungsdatum() { return erstellungsdatum; }
    public void setErstellungsdatum(LocalDate erstellungsdatum) {
        this.erstellungsdatum = erstellungsdatum;
    }

    public float berechneRisikowert(){
        return eintrittswahrscheinlichkeit * kosten_im_schadenfall;
    }

    public abstract float ermittleRueckstellung();
    public abstract void druckeDaten(OutputStream stream);
    }
package fh.pk1.fachebene;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Risikoverwaltung implements Serializable{

    private static final long serialVersionUID = -729034045372955790L;
    LinkedList<Risiko> risikos = new LinkedList<Risiko>();

    public void add(AkzeptablesRisiko risiko) {
        risiko.setId(fixId());
        risikos.add(risiko);
    }
    public void add(InakzeptablesRisiko risiko) {
        risiko.setId(fixId());
        risikos.add(risiko);
    }
    public void add(ExtremesRisiko risiko) {
        risiko.setId(fixId());
        risikos.add(risiko);
    }

    public String zeigeRisiken() {
        aufSortieren();
        String ausgabe = "";
        for (Risiko a: risikos){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            a.druckeDaten(baos);
            ausgabe += baos.toString();
        }
        return ausgabe;
    }

    public String sucheRisikoMitmaxRueckstellung() {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Risiko max = risikos.getFirst();
        for (Risiko a: risikos){
            if(a.ermittleRueckstellung() > max.ermittleRueckstellung())
                max = a;
        }
        max.druckeDaten(baos);
        return baos.toString();
    }

    public float berechneSummeRueckstellungen() {

        float summe = 0.0f;
        for (Risiko a: risikos)
            summe += a.ermittleRueckstellung();

        return summe;
    }

    public void serialsierung(){

        File datei = new File("Objekte");
    
        try (FileOutputStream fos = new FileOutputStream(datei);
            ObjectOutputStream osw = new ObjectOutputStream(fos)) {
    
            osw.writeObject(risikos);
    
        } catch (IOException e) {
            System.out.println("Fehler bei der Serialisierung");
            e.printStackTrace();
            }
    }

    public void deserialsierung(File file){     // Praktikum 9

        try ( FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis)) {

            this.risikos = (LinkedList<Risiko>) ois.readObject();

        } catch (IOException e) {
            System.out.println("Fehler bei der Serialisierung");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Fehler: class-Datei nicht gefunden");
        }
    }

    private void aufSortieren(){
        Collections.sort(risikos, new Comparator<Risiko>() { public int compare(Risiko p1, Risiko p2){
            return Float.compare(p1.berechneRisikowert(), p2.berechneRisikowert());   }});
    }

    private int fixId() {
        int ausgabe = 0;
        for (Risiko a: risikos)
            ausgabe++;
        return ausgabe;
    }
}
package fh.pk1.fachebene;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Risikoverwaltung implements Serializable{

    private static final long serialVersionUID = -729034045372955790L;
    List<Risiko> risikos = new ArrayList<Risiko>();

    public List<Risiko> getRisikos() {
        return risikos;
    }
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

    /*
    public void zeigeRisiken() {
        zeigeRisiken(System.out);
    }
    */

    public void zeigeRisiken(OutputStream out) {
        // TODO
    }

    public Iterator<Risiko> iterator() {
        return risikos.iterator();
    }

    public String sucheRisikoMitmaxRueckstellung() {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Risiko max = risikos.get(0);
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

    public void listeInDatei(String dateiName){

    File datei = new File(dateiName);

    try (FileOutputStream fos = new FileOutputStream(datei);
        OutputStreamWriter osw = new OutputStreamWriter(fos)) {

        osw.write(zeigeRisiken());
        // this.zeigeRisiken(osw);

    } catch (IOException e) {

        e.printStackTrace();
        }
    }

    public void serialsierung(){

        File file = new File("C:\\Users\\mikai\\Documents\\Uni\\PK1\\Risiko\\src\\main\\java\\fh\\pk1\\datenhaltung\\daten");

        try (FileOutputStream fos = new FileOutputStream(file);
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
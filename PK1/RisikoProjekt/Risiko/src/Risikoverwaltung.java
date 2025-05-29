<<<<<<< HEAD
import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
public class Risikoverwaltung implements Serializable{

    private static final long serialVersionUID = -729034045372955790L;
=======
import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
public class Risikoverwaltung {

>>>>>>> 273e5e8 (ka)
    LinkedList<Risiko> risikos = new LinkedList<Risiko>();

    public void aufnehmen(Risiko r) {
        
        risikos.add(r);
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

<<<<<<< HEAD
    public int fixId() {
        
        int ausgabe = 0;
        for (@SuppressWarnings("unused") Risiko a: risikos)
            ausgabe++;
        
        return ausgabe;
    }

=======
>>>>>>> 273e5e8 (ka)
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

    private void aufSortieren(){

        Collections.sort(risikos, new Comparator<Risiko>() {
        public int compare(Risiko p1, Risiko p2) {
                return Float.compare(p1.berechneRisikowert(), p2.berechneRisikowert());
            }
        });
    }
<<<<<<< HEAD

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

    public void deserialsierung(File file){

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
=======
>>>>>>> 273e5e8 (ka)
}
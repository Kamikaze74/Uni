<<<<<<< HEAD
import java.io.*;
import javax.swing.JOptionPane;

public class Menu implements Serializable{
=======
import java.io.ByteArrayOutputStream;
import javax.swing.JOptionPane;
public class Menu {
>>>>>>> 273e5e8 (ka)

private Risikoverwaltung verwaltung;

public Menu(Risikoverwaltung verwaltung){
<<<<<<< HEAD
=======

>>>>>>> 273e5e8 (ka)
    this.verwaltung = verwaltung;
    menuPrint();
}

private Risikoverwaltung menuPrint(){
    int auswahl=0;
    
<<<<<<< HEAD
    while (auswahl != 8) {
    do{
        auswahl= checkInput("1. Risiko aufnehmen\n" +
                            "2. Zeige alle Risiken\n" +
                            "3. Risikoliste in Datei schreiben\n" +
                            "4. Zeige Risiko mit maximaler R¨uckstellung\n" +
                            "5. Berechne Summe aller R¨uckstellungen\n" +
                            "6. Speichern\n" +
                            "7. Laden\n" +
                            "8. Beenden\n", Integer.class);

    }while( auswahl < 1 || auswahl > 8);
=======

    while (auswahl != 5) {
    do{
            //da fehlt eine exception
        auswahl= checkInput("1. Risiko aufnehmen\n" +
                            "2. Zeige alle Risiken\n" +
                            "3. Zeige Risiko mit maximaler R¨uckstellung\n" +
                            "4. Berechne Summe aller R¨uckstellungen\n" +
                            "5. Beenden\n", Integer.class);

    }while( auswahl < 1 || auswahl > 5);
>>>>>>> 273e5e8 (ka)
        switch (auswahl) {
            case 1:
                Risiko rs = add();
                verwaltung.aufnehmen(rs);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                rs.druckeDaten(baos);
                JOptionPane.showMessageDialog(null, baos.toString());
<<<<<<< HEAD
=======

>>>>>>> 273e5e8 (ka)
                break;

            case 2:
                JOptionPane.showMessageDialog(null, verwaltung.zeigeRisiken());
                break;

            case 3:
<<<<<<< HEAD
                listeInDatei();
                break;

            case 4:
                JOptionPane.showMessageDialog(null, verwaltung.sucheRisikoMitmaxRueckstellung());
                break;
            
            case 5:
                JOptionPane.showMessageDialog(null, verwaltung.berechneSummeRueckstellungen());
                break;
            case 6:
                verwaltung.serialsierung();
                JOptionPane.showMessageDialog(null, "Gespeichert");
                break;

            case 7:

                File file = checkInput("Filepfad angeben", File.class);
                verwaltung.deserialsierung(file);
                JOptionPane.showMessageDialog(null,"Geladen");
                break;
=======
                JOptionPane.showMessageDialog(null, verwaltung.sucheRisikoMitmaxRueckstellung());
                break;
            
            case 4:
                JOptionPane.showMessageDialog(null, verwaltung.berechneSummeRueckstellungen());
                break;
>>>>>>> 273e5e8 (ka)
            }
        }
    return verwaltung;
    }

private Risiko add(){

        String bh = checkInput("Bezeichnung", String.class);
        float ew = checkInput("Eintrittswahrscheinlichkeit", Float.class);
        float kis = checkInput("kosten im Schadensfall", Float.class);
<<<<<<< HEAD
        AkzeptablesRisiko rs = new AkzeptablesRisiko(bh, ew, kis, verwaltung.fixId());
=======
        AkzeptablesRisiko rs = new AkzeptablesRisiko(bh, ew, kis);
>>>>>>> 273e5e8 (ka)

        if(rs.getKosten_im_schadenfall() >= 1000000.00){
            String mn = checkInput("Es handelt sich um ein extremes Risiko.\nEine Maßnahme muss definiert werden", String.class);
            float vb = checkInput("und den Versicherungsbeitrag", Float.class);
<<<<<<< HEAD
            return new ExtremesRisiko(bh, ew, kis, mn, vb, verwaltung.fixId());
        }
        else if(rs.berechneRisikowert() >= 10000.00){
            String mn = checkInput("Es handelt sich um ein inakzeptabeles Risiko.\nEine Maßnahme muss definiert werden", String.class);
            return new InakzeptablesRisiko(bh, ew, kis, mn, verwaltung.fixId());
        }
        return rs;
    }

private void listeInDatei(){

    String dateiName = checkInput("Geben sie einen Dateinamen an", String.class);
    File datei = new File(dateiName);

    try (FileOutputStream fos = new FileOutputStream(datei);
         OutputStreamWriter osw = new OutputStreamWriter(fos)) {

         osw.write(verwaltung.zeigeRisiken());

    } catch (IOException e) {

         e.printStackTrace();
        }
    }





@SuppressWarnings({ "unchecked", "unused" })
private <T> T checkInput(String prompt, Class<T> type) { // <T> für eine belieblieges T // T rückgabewert
    while (true) {
        try {
            String input = JOptionPane.showInputDialog(null, prompt);

            if(input.equals("")){
                JOptionPane.showConfirmDialog(null, "Eingabe leer! Neue eingabe wählen?","??", JOptionPane.YES_NO_OPTION);

            }else if(input == null){
                JOptionPane.showMessageDialog(null, "Eingabe abgebrochen.");
                throw new NullPointerException("Benutzer hat die Eingabe abgebrochen.");
            }

            if (type == String.class) {
                return (T) input;
            } else if (type == Float.class) {
                return (T) Float.valueOf(Float.parseFloat(input)); // die teilen sich ein catch block
            } else if (type == Integer.class) {
                return (T) Integer.valueOf(Integer.parseInt(input)); // die teilen sich ein catch block
            } else if (type == File.class) {
                File file = new File(input);
                return (T) file;
               
            } else {
                throw new IllegalArgumentException("Nicht unterstützter Typ: " + type);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ungültige Eingabe! Bitte eine gültige Kommazahl eingeben.");
        }
=======
            return new ExtremesRisiko(bh, ew, kis, mn, vb);
        }
        else if(rs.berechneRisikowert() >= 10000.00){
            String mn = checkInput("Es handelt sich um ein inakzeptabeles Risiko.\nEine Maßnahme muss definiert werden", String.class);
            return new InakzeptablesRisiko(bh, ew, kis, mn);
        }

        return rs;
    }

@SuppressWarnings("unchecked")
private <T> T checkInput(String prompt, Class<T> type) { // <T> für eine belieblieges T // T rückgabewert
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(null, prompt);

                if(input == null){ // something weird with null
                    JOptionPane.showMessageDialog(null, "Eingabe abgebrochen.");
                    throw new NullPointerException("Benutzer hat die Eingabe abgebrochen.");
                }

                if (type == String.class) {
                    return (T) input;
                } else if (type == Float.class) {
                    return (T) Float.valueOf(Float.parseFloat(input)); // die teilen sich ein catch block
                } else if (type == Integer.class) {
                    return (T) Integer.valueOf(Integer.parseInt(input)); // die teilen sich ein catch block
                } else {
                    throw new IllegalArgumentException("Nicht unterstützter Typ: " + type);
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ungültige Eingabe! Bitte eine gültige Kommazahl eingeben.");
            }
>>>>>>> 273e5e8 (ka)
        }
    }
}
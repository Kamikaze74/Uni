import javax.swing.JOptionPane;
public class Menu {
private Risikoverwaltung verwaltung;

public Menu(Risikoverwaltung verwaltung){

    this.verwaltung = verwaltung;
    menuPrint();
}

private Risikoverwaltung menuPrint(){
    int auswahl=0;
    

    while (auswahl != 5) {
    do{
            //da fehlt eine exception
        auswahl= Integer.parseInt(JOptionPane.showInputDialog(null, "1. Risiko aufnehmen\n" +
                                                                    "2. Zeige alle Risiken\n" +
                                                                    "3. Zeige Risiko mit maximaler R¨uckstellung\n" +
                                                                    "4. Berechne Summe aller R¨uckstellungen\n" +
                                                                    "5. Beenden\n"));

    }while( auswahl < 1 || auswahl > 4); 
        switch (auswahl) {
            case 1:
                Risiko rs = add();
                verwaltung.aufnehmen(rs);
                JOptionPane.showMessageDialog(null, rs.druckeDaten());
                break;

            case 2:
                JOptionPane.showMessageDialog(null, verwaltung.zeigeRisiken());
                break;

            case 3:
                JOptionPane.showMessageDialog(null, verwaltung.sucheRisikoMitmaxRueckstellung());
                break;
            
            case 4:
                JOptionPane.showMessageDialog(null, verwaltung.berechneSummeRueckstellungen());
                break;
            }
        }
    return verwaltung;
    }

    private Risiko add(){

        String bh = checkInput("Bezeichnung", String.class);
        float ew = checkInput("Eintrittswahrscheinlichkeit", Float.class);
        float kis = checkInput("kosten im Schadensfall", Float.class);
        AkzeptablesRisiko rs = new AkzeptablesRisiko(bh, ew, kis);

        if(rs.getKosten_im_schadenfall() >= 1000000.00){
            String mn = checkInput("Es handelt sich um ein extremes Risiko.\nEine Maßnahme muss definiert werden", String.class);
            float vb = checkInput("und den Versicherungsbeitrag", Float.class);
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
                    return (T) Float.valueOf(Float.parseFloat(input));
                } else {
                    throw new IllegalArgumentException("Nicht unterstützter Typ: " + type);
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ungültige Eingabe! Bitte eine gültige Kommazahl eingeben.");
            }
        }
    }
}
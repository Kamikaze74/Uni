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
                verwaltung.zeigeRisiken();
                JOptionPane.showMessageDialog(null, verwaltung.zeigeRisiken());
                break;

            case 3:
                JOptionPane.showMessageDialog(null, verwaltung.sucheRisikoMitmaxRueckstellung());
                break;
            
            case 4:
                verwaltung.berechneSummeRueckstellungen();
                JOptionPane.showMessageDialog(null, verwaltung.berechneSummeRueckstellungen());
                break;
            }
        }
    return verwaltung;
    }

    private Risiko add(){
        String bh = JOptionPane.showInputDialog(null, "Bezeichnung");
        float ew = Float.parseFloat(JOptionPane.showInputDialog(null, "Eintrittswahrscheinlichkeit"));
        float kis = Float.parseFloat(JOptionPane.showInputDialog(null, "kosten im Schadensfall"));

        AkzeptablesRisiko rs = new AkzeptablesRisiko(bh, ew, kis);

        if(rs.getKosten_im_schadenfall() >= 1000000.00){
            String mn = JOptionPane.showInputDialog(null, "Es handelt sich um ein extremes Risiko.\nEine Maßnahme muss definiert werden");
            float vb = Float.parseFloat(JOptionPane.showInputDialog(null, "und den Versicherungsbeitrag"));
            return new ExtremesRisiko(bh, ew, kis, mn, vb);
        }
        else if(rs.berechneRisikowert() >= 10000.00){
            String mn = JOptionPane.showInputDialog(null, "Es handelt sich um ein inakzeptabeles Risiko.\nEine Maßnahme muss definiert werden");
            return new InakzeptablesRisiko(bh, ew, kis, mn);
        }
        return rs;
    }
}
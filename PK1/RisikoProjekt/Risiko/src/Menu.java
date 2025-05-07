import java.util.Scanner;
public class Menu {
private Risikoverwaltung verwaltung;
private Scanner sc = new Scanner(System.in);

public Menu(Risikoverwaltung verwaltung){

    this.verwaltung = verwaltung;
    menuPrint();
}

private Risikoverwaltung menuPrint(){
    int auswahl=0;

    while (auswahl != 5) {
    do{

        System.out.printf( "1. Risiko aufnehmen\n" +
                        "2. Zeige alle Risiken\n" +
                        "3. Zeige Risiko mit maximaler R¨uckstellung\n" +
                        "4. Berechne Summe aller R¨uckstellungen\n" +
                        "5. Beenden\n");
        auswahl=sc.nextInt();

    }while( auswahl < 1 || auswahl > 5);
        switch (auswahl) {
            case 1:
                verwaltung.aufnehmen(add());
                break;

            case 2:
                verwaltung.zeigeRisiken();
                break;

            case 3:
                verwaltung.sucheRisikoMitmaxRueckstellung();
                break;
            
            case 4:
                verwaltung.berechneSummeRueckstellungen();
                break;
            }
        }
    return verwaltung;
    }

    private Risiko add(){
            System.out.printf("Bezeichnung: ");
        String bh = sc.next();
            System.out.printf("\nEintrittswahrscheinlichkeit: ");
        float ew = sc.nextFloat();
            System.out.printf("\nkosten im Schadensfall: ");
        float kis = sc.nextFloat();

        AkzeptablesRisiko rs = new AkzeptablesRisiko(bh, ew, kis);

        if(rs.getKosten_im_schadenfall() >= 1000000.00){
            System.out.printf("\nEs handelt sich um ein extremes Risiko.\nEine Maßnahme muss definiert werden: ");
            String mn = sc.next();
            System.out.printf("\n und den Versicherungsbeitrag: ");
            float vb = sc.nextFloat();
            return new ExtremesRisiko(bh, ew, kis, mn, vb);
        }
        else if(rs.berechneRisikowert() >= 10000.00){
            System.out.printf("\nEs handelt sich um ein inakzeptabeles Risiko.\nEine Maßnahme muss definiert werden: ");
            String mn = sc.next();
            return new InakzeptablesRisiko(bh, ew, kis, mn);
        }
        return rs;
    }
}
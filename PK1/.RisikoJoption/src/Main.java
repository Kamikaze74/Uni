package PK1.RisikoJoption.src;

public class Main {
    public static void main(String[] args) {
        // Beispielhafte Risikoobjekte erstellen
        Risiko ar1 = new AkzeptablesRisiko("Lizenzkosten steigen", 0.4f, 2000f,0);
        Risiko ar2 = new AkzeptablesRisiko("Cloud-Kosten", 0.5f, 1800f,1);
        Risiko ar3 = new AkzeptablesRisiko("Server-Ausfall", 0.7f, 2200f,2);

        Risiko er1 = new ExtremesRisiko("Cyberangriff", 0.1f, 100000f, "die masname", 5000.0f,3);
        Risiko er2 = new ExtremesRisiko("Hochwasser", 0.3f, 150000f, "gebäudeversicherung", 8000.0f,4);

        InakzeptablesRisiko ir1 = new InakzeptablesRisiko("Kernreaktor-Leck", 0.05f, 500000f, "Stilllegung",5);

        // Risikoverwaltung erstellen und Risiken hinzufügen
        Risikoverwaltung verwaltung = new Risikoverwaltung();
    /*
        verwaltung.aufnehmen(ar1);
        verwaltung.aufnehmen(ar2);
        verwaltung.aufnehmen(ar3);
        verwaltung.aufnehmen(er1);
        verwaltung.aufnehmen(er2);
        verwaltung.aufnehmen(ir1);

        // Alle Risiken anzeigen
        System.out.println("\n--- Alle Risiken ---");
        verwaltung.zeigeRisiken();

        // Risiko mit maximaler Rückstellung suchen
        System.out.println("\n--- Risiko mit maximaler Rückstellung ---");
        verwaltung.sucheRisikoMitmaxRueckstellung();

        // Summe der Rückstellungen berechnen
        System.out.println("\n--- Summe aller Rückstellungen ---");
        float summe = verwaltung.berechneSummeRueckstellungen();
        System.out.printf("Summe: %.2f\n", summe);

        System.out.println(ar1.equals(er1) + "" + ar1.equals(ar1));
        */

        @SuppressWarnings("unused")
        Menu m = new Menu(verwaltung);

    }
}

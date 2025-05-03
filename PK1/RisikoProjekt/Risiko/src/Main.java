public class Main {
    public static void main(String[] args) {
        // Beispielhafte Risikoobjekte erstellen
        AkzeptablesRisiko ar1 = new AkzeptablesRisiko("Lizenzkosten steigen", 0.4f, 2000f);
        AkzeptablesRisiko ar2 = new AkzeptablesRisiko("Cloud-Kosten", 0.5f, 1800f);
        AkzeptablesRisiko ar3 = new AkzeptablesRisiko("Server-Ausfall", 0.7f, 2200f);

        ExtremesRisiko er1 = new ExtremesRisiko("Cyberangriff", 0.1f, 100000f, "die masname", 5000.0f);
        ExtremesRisiko er2 = new ExtremesRisiko("Hochwasser", 0.3f, 150000f, "gebäudeversicherung", 8000.0f);

        InakzeptablesRisiko ir1 = new InakzeptablesRisiko("Kernreaktor-Leck", 0.05f, 500000f, "Stilllegung");

        // Risikoverwaltung erstellen und Risiken hinzufügen
        RisikoverwaltungArray verwaltung = new RisikoverwaltungArray();
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
    }
}
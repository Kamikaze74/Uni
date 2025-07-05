import java.util.*;

public class Graph
{
	private int maxKnotenzahl;
	private int aktKnotenzahl;
	private Knoten[] knoten;

	public Graph(int maxKnotenzahl)
	{
		assert(maxKnotenzahl >= 2);

		knoten = new Knoten[this.maxKnotenzahl = maxKnotenzahl];
	}

	public Knoten suchenUndEinfuegen(final String knotenname)
	{
		for (int a = 0; a < aktKnotenzahl; a++)
			if (knoten[a].getName().compareTo(knotenname) == 0)
				return knoten[a];

		// Knoten existiert noch nicht, wird neu eingefügt
		knoten[aktKnotenzahl] = new Knoten(knotenname, null);

		return knoten[aktKnotenzahl++];
	}

	public void addKante(final Knoten k1, final Knoten k2)
	{
		k1.einfuegenNachfolger(k2);
		k2.inkrementAnzahlVorgaenger();
	}

	public List<Integer> topSort()
	{
		// Liste der Ergebnisknoten
		LinkedList<Integer> tp = new LinkedList<Integer>();
		boolean besucht[] = new boolean [aktKnotenzahl];

		boolean gefunden;
		do
		{
			gefunden = false;

 			// Knoten ohne Vorgänger suchen
			for (int a = 0; a < aktKnotenzahl; a++)
				if (knoten[a].getAnzahlVorgaenger() == 0 && !besucht[a])
				{
					gefunden = true;
					besucht[a] = true;

					tp.addLast(a);

					// Alle ausgehenden Kanten von a verfolgen
					KnotenLink listPtr = knoten[a].getErsterNachfolger();
					while (listPtr != null)
					{
						listPtr.getKnoten().dekrementAnzahlVorgaenger();
						listPtr = listPtr.getNaechsterNachfolger();
					}
				}
		}
		while (gefunden);

		if (tp.size() != aktKnotenzahl)
			System.out.println("Graph enthielt Zyklen oder war nicht zusammenhängend!");

		return tp;
	}

	public void ausgeben()
	{
		for (int a = 0; a < maxKnotenzahl; a++)
			if (knoten[a] != null)
			{
				System.out.print("Knoten: " + knoten[a].getName() + "  Position im Feld: " + a + "  Anzahl der Vorgänger: " + knoten[a].getAnzahlVorgaenger());
				System.out.print("  Kanten zu anderen Knoten: ");

				KnotenLink nachfolger = knoten[a].getErsterNachfolger();
				while (nachfolger != null)
				{
					System.out.print(knoten[a].getName() + "->" + nachfolger.getKnoten().getName() + " ");
					nachfolger = nachfolger.getNaechsterNachfolger();
				}

				System.out.println();
			}
	}

	public static void main(String[] args)
	{
		Graph g = new Graph(10);

		g.addKante(g.suchenUndEinfuegen("1"), g.suchenUndEinfuegen("3"));
		g.addKante(g.suchenUndEinfuegen("1"), g.suchenUndEinfuegen("2"));
		g.addKante(g.suchenUndEinfuegen("2"), g.suchenUndEinfuegen("10"));
		g.addKante(g.suchenUndEinfuegen("2"), g.suchenUndEinfuegen("4"));
		g.addKante(g.suchenUndEinfuegen("3"), g.suchenUndEinfuegen("5"));
		g.addKante(g.suchenUndEinfuegen("4"), g.suchenUndEinfuegen("8"));
		g.addKante(g.suchenUndEinfuegen("4"), g.suchenUndEinfuegen("6"));
		g.addKante(g.suchenUndEinfuegen("5"), g.suchenUndEinfuegen("8"));
		g.addKante(g.suchenUndEinfuegen("6"), g.suchenUndEinfuegen("3"));
		g.addKante(g.suchenUndEinfuegen("7"), g.suchenUndEinfuegen("9"));
		g.addKante(g.suchenUndEinfuegen("7"), g.suchenUndEinfuegen("5"));
		g.addKante(g.suchenUndEinfuegen("9"), g.suchenUndEinfuegen("10"));
		g.addKante(g.suchenUndEinfuegen("9"), g.suchenUndEinfuegen("4"));

		System.out.println("Aufgebauter Graph:");
		g.ausgeben();

		System.out.println("\nGraph in topologischer Reihenfolge:");
		List<Integer> tp = g.topSort();

		ListIterator<Integer> it = tp.listIterator();
		while (it.hasNext())
			System.out.print(g.knoten[it.next()].getName() + " ");
	}
}
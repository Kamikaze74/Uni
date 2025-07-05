import java.util.*;

public class Graph
{
	private static final int KNOTENZAHL = 8;
	private static boolean[] besucht;
	private Knoten[] knoten = new Knoten[KNOTENZAHL];

	public Graph()
	{
		knoten[0] = new Knoten("A",
			new KnotenLink(1,
			new KnotenLink(5, null)));
		knoten[1] = new Knoten("B",
			new KnotenLink(2,
			new KnotenLink(3,
			new KnotenLink(6, null))));
		knoten[2] = new Knoten ("C", null);
		knoten[3] = new Knoten("D",
			new KnotenLink(4,
			new KnotenLink(6, null)));
		knoten[4] = new Knoten("E", null);
		knoten[5] = new Knoten("F", null);
		knoten[6] = new Knoten("G",
			new KnotenLink(7, null));
		knoten[7] = new Knoten("H", null);
	}

	// Tiefensuche
	public void dfsRekursiv(final int start)
	{
		besucht = new boolean[KNOTENZAHL];
		rekDfs(start);
	}

	private void rekDfs(final int k)
	{
		besucht[k] = true;
		System.out.print(knoten[k].getName() + " ");

		KnotenLink vp = knoten[k].getErsterNachfolger();
		while (vp != null)
		{
			int ziel = vp.getZiel();
			if (!besucht[ziel])
				rekDfs(ziel);
			vp = vp.getNaechsterNachfolger();
		}
	}

	// Breitensuche
	public void bfsIterativ(final int start)
	{
		boolean[] besucht = new boolean[KNOTENZAHL];
		Queue<Integer> q = new LinkedList<Integer>();

		q.add(start);
		besucht[start] = true;

		while (!q.isEmpty())
		{
			int k = q.remove();
			System.out.print(knoten[k].getName() + " ");

			KnotenLink vp = knoten[k].getErsterNachfolger();
			while (vp != null)
			{
				int ziel = vp.getZiel();
				if (!besucht[ziel])
				{
					q.add(ziel);
					besucht[ziel] = true;
				}
				vp = vp.getNaechsterNachfolger();
			}
		}	
		System.out.println();
	}
	
	public void ausgeben()
	{
		System.out.println("Graph: Adjazenzlisten");
		for (int i = 0; i < knoten.length; i++)
		{
			System.out.print(knoten[i].getName() + ": ");
			KnotenLink nachfolger = knoten[i].getErsterNachfolger();
			while (nachfolger != null)
			{
				System.out.print(" --> " + knoten[nachfolger.getZiel()].getName());
				nachfolger = nachfolger.getNaechsterNachfolger();
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args)
	{
		Graph g = new Graph();
		g.ausgeben();

		System.out.println("Start Breitendurchlauf mit " + g.knoten[0].getName());
		g.bfsIterativ(0);

		System.out.println("\nStart Tiefendurchlauf mit " + g.knoten[0].getName());
		g.dfsRekursiv(0);
	}
}
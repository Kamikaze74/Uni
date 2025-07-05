import java.util.*;

public class Graph
{
	private static final int KNOTENZAHL = 7;
	private static boolean[] besucht;
	private Knoten[] knoten;
	private boolean[][] matrix = {
			{false, true, false, false, false, false, false},
			{false, false, true, false, false, false, false},
			{false, true, false, false, false, false, false},
			{false, false, false, false, false, false, false},
			{false, false, false, true, false, false, false},
			{false, true, true, false, false, false, false},
			{false, false, false, false, false, false, false},
		};

	public Graph()
	{
		knoten  = new Knoten[KNOTENZAHL];
		knoten[0] = new Knoten("A");
		knoten[1] = new Knoten("B");
		knoten[2] = new Knoten("C");
		knoten[3] = new Knoten("D");
		knoten[4] = new Knoten("E");
		knoten[5] = new Knoten("F");
		knoten[6] = new Knoten("G");
	}

	private boolean isKante(int k1, int k2)
	{
		if(matrix.length > k1 && matrix[0].length > k2 && (matrix[k1][k2] == true || matrix[k2][k1] == true))
			return true;

		return false;
	}
	
	// Tiefensuche
	public void dfsRekursiv(final int k){
		//besucht = new boolean[knoten.length];
		rekDfs(k);
	}
	private void rekDfs(final int k)
	{
		besucht[k] = true;
		System.out.print(knoten[k].getName());
		for(int i = 0; i < matrix[0].length; i++){
			if(isKante(k, i)&&besucht[i] == false){
					rekDfs(i);
			}
		}
		

		// Rekursive Tiefensuche mit Hilfe der Methode isKante implementieren
		
	}

	public void zusammenhangskomponenten()
	{
		besucht = new boolean [KNOTENZAHL];
		int z = 0;
		for(int i = 0; i < KNOTENZAHL; i++){
			if( besucht[i] == false){
				System.out.println();
				rekDfs(i);
				z++;
			}
		}
		System.out.printf("%nLänge: "+z);

	}
	
	public void bfsIterativ(final int start)
	{
		// TODO
	}

	public static void main(String[] args)
	{
		Graph g = new Graph();
		System.out.println("Bestimmung der Zusammenhangskomponenten");
		g.zusammenhangskomponenten();
		System.out.println();
		
		System.out.println("Start Breitendurchlauf mit " + g.knoten[0].getName());
		g.bfsIterativ(0);
	}
}
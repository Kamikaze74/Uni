public class HybridSort
{
	public static void hybridsort1(int[] array, final int links, final int rechts)
	{
		final int pivot = array[(links + rechts) / 2];
		int li = links;
		int re = rechts;

		do
		{
			// Linker Zeiger
			while (array[li] < pivot)
				li++;

			// Rechter Zeiger
			while (array[re] > pivot)
				re--;

			if (li <= re) {
			// Elemente tauschen und Zeiger weiterbewegen (WICHTIG!)
				int temp = array[li];
				array[li++] = array[re];
				array[re--] = temp;
			}
		} while (li <= re);

		// Divide and conquer: rekursive Aufrufe für neue Teilbereiche
		if (links+4 < re)
			hybridsort1(array, links, re);
		else
			BasicSort.bubblesort(array, links, re);

		if (li+4 < rechts)
			hybridsort1(array, li, rechts);
		else
			BasicSort.bubblesort(array, li, rechts);
	}
	
	public static void hybridsort2(int[] array, final int links, final int rechts)
	{
		final int pivot = array[(links + rechts) / 2];
		int li = links;
		int re = rechts;

		do
		{
			// Linker Zeiger
			while (array[li] < pivot)
				li++;

			// Rechter Zeiger
			while (array[re] > pivot)
				re--;

			if (li <= re) {
			// Elemente tauschen und Zeiger weiterbewegen (WICHTIG!)
				int temp = array[li];
				array[li++] = array[re];
				array[re--] = temp;
			}
		} while (li <= re);

		// Divide and conquer: rekursive Aufrufe für neue Teilbereiche
		if (links+4 < re)
			hybridsort2(array, links, re);
		else
			BasicSort.insertionsort(array, links, re);

		if (li+4 < rechts)
			hybridsort2(array, li, rechts);
		else
			BasicSort.insertionsort(array, li, rechts);
	}
}
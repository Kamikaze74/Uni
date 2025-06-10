package UEB09_10;
public class QuickSort
{
	public static void quicksort(int[] array, final int links, final int rechts)
	{
	
		int li = links;
		int re = rechts;
		int pivo = array[(rechts + links) / 2];

		do{
			while (array[li] < pivo)
			li++;
			
			while (array[re] > pivo)
			re--;

			if(li <= re){
				int x = array[li];
				array[li] = array[re];
				array[re] = x;
				re--;li++;
			}
		} while(li < re);

		if(links < re)
			quicksort(array, links, re);

		if(li < rechts)
			quicksort(array, li, rechts);
	}
}
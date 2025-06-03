public class BasicSort
{
	// Selectionsort:
	// Sortiere das Teilfeld von array beginnend mit Index links bis einschlie�lich Index rechts
	public static void selectionsort(int[] array, final int links, final int rechts)
	{
		for (int i = links; i < rechts; i++)
		{
			// Kleinstes Element im unsortierten Teil finden
			int min = i;

			for (int j = i + 1; j <= rechts; j++)
				if (array[min] > array[j])
					min = j;

			// Elemente tauschen
			int temp = array[min];
			array[min] = array[i];
			array[i] = temp;
		}
	}

	// Insertionsort:
	// Sortiere das Teilfeld von array beginnend mit Index links bis einschlie�lich Index rechts
	public static void insertionsort(int[] array, final int links, final int rechts)
	{
		for(int i = links + 2; i <= rechts; i++){
			int x = array[i];
			int j = i -1;

			while( j >= 1 && array[j] > x){
				array[j + 1] = array[j];
				j--;
			}
		array[j+1] = x;
		}
	}

	// Bubblesort:
	// Sortiere das Teilfeld von array beginnend mit Index links bis einschlie�lich Index rechts
	public static void bubblesort(int[] array, final int links, final int rechts)
	{
		for(int j = rechts; links + 1 <= j; j--){
			for(int i = links; i <= j - 1; i++){
				if(array[i] > array[i + 1]){
					int x = array[i + 1];
					array[i + 1] = array[i];
					array[i] = x;
				}
			}
		}
	}
}
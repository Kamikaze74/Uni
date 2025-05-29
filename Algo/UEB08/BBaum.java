class BBaum<T extends Comparable<T>>
{
	public BKnoten<T> wurzel;

	public BBaum(BKnoten<T> wurzel)
	{
		assert(wurzel != null);

		this.wurzel = wurzel;
	}

	// Wrapper-Methode
	public void traversieren()
	{
		traversieren(wurzel);
	}

	// Eigentliche Implementierung
	private void traversieren(BKnoten<T> knoten)
	{
		for(int i = 0; i < knoten.kinder.length; i++){
			if(knoten.kinder[i] != null)
			traversieren(knoten.kinder[i]);

			if(knoten.elemente.length > i)
			System.out.print(knoten.elemente[i] + " ");
		}
	}

	// Wrapper-Methode
	public boolean suchen(final T daten)
	{
		assert(daten != null);

		return suchen(daten, wurzel);
	}

	// Eigentliche Implementierung
	private boolean suchen(final T daten, BKnoten<T> knoten)
	{
		if(knoten == null){
			return false;
		}
		for(int i = 0; knoten.elemente.length > i && knoten.elemente[i] != null; i++){
			
			int value = daten.compareTo(knoten.elemente[i]);
			
			if(value == 0)
				return true;

			if(value < 0 && knoten.kinder[i] != null)
				return suchen(daten, knoten.kinder[i]);
		}
			return suchen(daten, knoten.kinder[knoten.kinder.length-1]);
	}
}
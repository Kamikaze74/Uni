public class Professor implements IHashable
{
	private String name;
	private long hausruf;

	public String getName()
	{
		return name;
	}

	public long getHausruf()
	{
		return hausruf;
	}

	public Professor(String name, int Hausruf)
	{
		this.name = name;
		this.hausruf  = Hausruf;
	}

	// Hash-Wert
	public int hash(final int tableSize)
	{
		char c1 = name.charAt(0);
		char c2 = name.charAt(1);

		int h1 = c1 - '0';
		int h2 = c2 - '0';
		int h = (h1 + h2) % tableSize;

		// Debug-Ausgabe zu Testzwecken
		// System.out.println("Name:" + Name + " Hashwert:" + h);

		return h;
	}

	public boolean equals(Object o)
	{
		// In diesem Beispiel nur Professor-Objekte zulassen
		if (!(o instanceof Professor))
			return false;

		final Professor p = (Professor)o;

		return ((p.name).equals(this.name) && (p.hausruf == this.hausruf));
	}

	public String toString()
	{
		return name;
	}
}

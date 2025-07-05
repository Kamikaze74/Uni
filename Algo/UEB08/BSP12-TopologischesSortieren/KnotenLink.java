
public class KnotenLink 
{
	private Knoten knoten; // Verweis auf Knoten
	private KnotenLink naechsterNachfolger;

	public KnotenLink (Knoten knoten, KnotenLink n)
	{
		this.knoten = knoten;
		naechsterNachfolger = n;
	}

	public Knoten getKnoten()
	{
		return knoten;
	}

	public KnotenLink getNaechsterNachfolger()
	{
		return naechsterNachfolger;
	}

}

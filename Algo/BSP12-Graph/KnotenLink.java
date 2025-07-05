
public class KnotenLink 
{
	private int ziel; // Index des Knotens
	private KnotenLink naechsterNachfolger;

	public KnotenLink (int z, KnotenLink n)
	{
		ziel = z;
		naechsterNachfolger = n;
	}

	public int getZiel()
	{
		return ziel;
	}

	public KnotenLink getNaechsterNachfolger()
	{
		return naechsterNachfolger;
	}

}

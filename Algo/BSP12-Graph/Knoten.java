
public class Knoten 
{
	private String name;
	private KnotenLink ersterNachfolger;

	public Knoten(String name, KnotenLink nachfolger)
	{
		this.name = name;
		ersterNachfolger = nachfolger;
	}

	public String getName()
	{
		return name;
	}

	public KnotenLink getErsterNachfolger()
	{
		return ersterNachfolger;
	}
}

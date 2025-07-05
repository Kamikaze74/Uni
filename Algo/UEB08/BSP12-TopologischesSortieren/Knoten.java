
public class Knoten 
{
	private String name;
	private KnotenLink ersterNachfolger;
	private int anzahlVorgaenger;

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
	
	public void einfuegenNachfolger(Knoten knoten)
	{
		ersterNachfolger = new KnotenLink(knoten, ersterNachfolger);
	}
	
	public int getAnzahlVorgaenger()
	{
		return anzahlVorgaenger;
	}
	
	public void inkrementAnzahlVorgaenger()
	{
		anzahlVorgaenger++;
	}
	
	public void dekrementAnzahlVorgaenger()
	{
		anzahlVorgaenger--;
	}
	

}

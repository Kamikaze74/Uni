import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class AVLBaum<T extends Comparable<T>>
{
	private AVLKnoten<T> wurzel;
	private boolean hoeheGeaendert;

	// Wird nur f�r grafische Oberfl�che ben�tigt, ohne
	// diese Methode k�nnte die gesamte Implementierung
	// des Baumes geheim gehalten werden. Alle �ffentlichen
	// Methoden sind parameterlos oder besitzen als
	// einzigen Parameter einen Schl�sselwert
	public AVLKnoten<T> getWurzel()
	{
		return wurzel;
	}

	public boolean istLeer()
	{
		return (wurzel == null);
	}


// Methoden zum Suchen

	public boolean suchen(final T daten)
	{
		AVLKnoten <T> comp = getWurzel();

		while (comp != null) {

			int value = daten.compareTo(comp.getDaten());

			if(value == 0){
				return true;
			}else if(value < 0){
				comp = comp.getKnotenLinks();
			}else{
				comp = comp.getKnotenRechts();
			}
		}
		return false;
	}


// Methoden zum Einf�gen

	public void einfuegen(final T daten)
	{
		// Setzen der Merker-Variable hoeheGeandert auf false
		// Das wird zwar nach einem Links- oder Rechts-Ausgleich gemacht,
		// aber diese finden nicht statt, wenn ein bereits existierender
		// Schl�ssel wiederholt eingef�gt wird!
		hoeheGeaendert = false;

		// Beim Einf�gen wird der Baum neu zusammengesetzt, um Rotationen
		// zu erm�glichen. Daher tritt hier kein Sonderfall auf, aber die
		// Wurzel muss neu zugewiesen werden.
		wurzel = einfuegenKnoten(daten, wurzel);
	}

	private AVLKnoten<T> einfuegenKnoten(final T daten, AVLKnoten<T> teilbaum)
	{
		if (teilbaum == null)
		{
			hoeheGeaendert = true;

			return new AVLKnoten<T>(daten, null, null);
		}

		// Vergleichs-Ergebnis zwischenspeichern, da compareTo()
		// aufw�ndig sein kann, und das Ergebnis mehrfach ben�tigt
		// wird
		final int cmp = daten.compareTo(teilbaum.getDaten());

		if (cmp < 0)
		{
			// Einzuf�gende Daten sind KLEINER als Daten im aktuellen Knoten
			// und m�ssen daher im LINKEN Teilbaum eingef�gt werden
			teilbaum.setKnotenLinks(einfuegenKnoten(daten, teilbaum.getKnotenLinks()));
			if (hoeheGeaendert)
				teilbaum = linksAusgleich(teilbaum);
		}
		else
			if (cmp > 0)
			{
				// Einzuf�gende Daten sind GROESSER als Daten im aktuellen Knoten
				// und m�ssen daher im RECHTEN Teilbaum eingef�gt werden
				teilbaum.setKnotenRechts(einfuegenKnoten(daten, teilbaum.getKnotenRechts()));
				if (hoeheGeaendert)
					teilbaum = rechtsAusgleich(teilbaum);
			}

		return teilbaum;
	}

	private AVLKnoten<T> linksAusgleich(AVLKnoten<T> k)
	{
		AVLKnoten<T> x;

		switch (k.getBalance())
		{
			case +1:
			{
				k.setBalance(0);				// Der mit k beginnende Teilbaum ist jetzt balanciert
				hoeheGeaendert = false;
				break;
			}

			case 0:								// Der mit k beginnende Teilbaum ist jetzt linkslastig
			{
				k.setBalance(-1);
				break;
			}

			case -1:							// Ausgleich notwendig
			{
				x = k.getKnotenLinks();

				if (x.getBalance() == -1)		// Fall 3a
				{
					k = rechtsRotation(k, x);
				}
				else
					if (x.getBalance() == +1)	// Fall 3b
					{
						k = lrDoppelRotation(k, x);
						k.setBalance(0);
					}

				k.setBalance(0);
				hoeheGeaendert = false;
			}
		}

		return k;
	}

	private AVLKnoten<T> rechtsAusgleich(AVLKnoten<T> k)
	{
		AVLKnoten<T> x;

		switch (k.getBalance())
		{
			case -1:
			{
				k.setBalance(0);				// Der mit k beginnende Teilbaum ist jetzt balanciert
				hoeheGeaendert = false;
				break;
			}

			case 0:								// Der mit k beginnende Teilbaum ist jetzt rechtslastig
			{
				k.setBalance(+1);
				break;
			}

			case +1:							// Ausgleich notwendig
			{
				x = k.getKnotenRechts();

				if (x.getBalance() == +1)		// Fall 3a
				{
					k = linksRotation(k, x);
				}
				else
					if (x.getBalance() == -1)	// Fall 3b
					{
						k = rlDoppelRotation(k, x);
					}

				k.setBalance(0);
				hoeheGeaendert = false;
			}
		}

		return k;
	}

	private AVLKnoten<T> rechtsRotation(AVLKnoten<T> k, AVLKnoten<T> x)
	{
		k.setKnotenLinks(x.getKnotenRechts());
		x.setKnotenRechts(k);
		k.setBalance(0);

		return x;
	}

	private AVLKnoten<T> lrDoppelRotation(AVLKnoten<T> k, AVLKnoten<T> x)
	{
		AVLKnoten<T> y = x.getKnotenRechts();
		x.setKnotenRechts(y.getKnotenLinks());
		y.setKnotenLinks(x);
		k.setKnotenLinks(y.getKnotenRechts());
		y.setKnotenRechts(k);

		switch (y.getBalance())
		{
			case -1:
			{
				k.setBalance(+1);
				x.setBalance(0);
				break;
			}
			case +1:
			{
				k.setBalance(0);
				x.setBalance(-1);
				break;
			}
			case 0:
			{
				k.setBalance(0);
				x.setBalance(0);
			}
		}

		return y;
	}

	private AVLKnoten<T> linksRotation(AVLKnoten<T> k, AVLKnoten<T> x)
	{
		k.setKnotenRechts(x.getKnotenLinks());
		x.setKnotenLinks(k);
		k.setBalance(0);

		return x;
	}

	private AVLKnoten<T> rlDoppelRotation(AVLKnoten<T> k, AVLKnoten<T> x)
	{
		AVLKnoten<T> y = x.getKnotenLinks();
		x.setKnotenLinks(y.getKnotenRechts());
		y.setKnotenRechts(x);
		k.setKnotenRechts(y.getKnotenLinks());
		y.setKnotenLinks(k);

		switch (y.getBalance())
		{
			case +1:
			{
				k.setBalance(-1);
				x.setBalance(0);
				break;
			}
			case -1:
			{
				k.setBalance(0);
				x.setBalance(+1);
				break;
			}
			case 0:
			{
				k.setBalance(0);
				x.setBalance(0);
			}
		}

		return y;
	}


// Methode zum Traversieren

	// Pre-Order
	public String traversierePreOrder()
	{
		Deque<AVLKnoten<T>> k = new LinkedList<>();
		k.push(getWurzel());
		String out = "";

		while(k.isEmpty() != true){

			AVLKnoten<T> remove = k.pop();

			if(remove.getKnotenRechts() != null)
			k.push(remove.getKnotenRechts());
			if(remove.getKnotenLinks() != null)
			k.push(remove.getKnotenLinks());

			out += remove.getDaten().toString();
		}
		return out;
	}
}
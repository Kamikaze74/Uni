import java.util.*;

public class Liste<T> {
	protected Link<T> anfang;
	protected Link<T> ende;

	public Liste() {
		// Leere Liste: alle Zeiger sind null (Standardwerte)
	}

	// Einfachster Fall und gleichzeitig Sonderfall:
	// Element am Anfang einf�gen
	public void einfuegen(final T daten) {
		// Funktioniert immer
		anfang = new Link<T>(daten, anfang);

		// Bei einer leeren Liste muss auch ende gesetzt werden
		if (ende == null)
			ende = anfang;
	}

	// Einfachster Fall und gleichzeitig Sonderfall:
	// Element am Anfang entfernen
	public T entfernen() {
		T opfer = null;

		// Clowns k�nnten diese Methode bei leerer Liste aufrufen
		if (!istLeer()) {
			// Bei einer Liste mit nur noch einem Elment muss
			// auch ende gel�scht werden
			if (anfang == ende)
				ende = null;

			opfer = anfang.daten;
			anfang = anfang.naechster;
		}

		return opfer;
	}

	// Einfacher Fall: Anf�gen am Ende der Liste
	public void anfuegen(final T daten) {
		// Neuen Link anlegen
		Link<T> neu = new Link<T>(daten, null);

		// Wenn die Liste leer ist, m�ssen anfang und ende gesetzt werden
		if (istLeer()) {
			// Neuen Link als anfang der Liste
			anfang = neu;
		} else {
			// Anf�gen des Elements an das bisherige ende
			ende.naechster = neu;
		}

		// Neues Ende
		ende = neu;
	}

	// EINF�GEN AN EINER BESTIMMTEN STELLE
	// Der Programmcode ist so gestaltet, dass er fehlerhafte Eingaben
	// (z.B. eine negative oder zu hohe Position) elegant verarbeitet
	// ohne abzust�rzen.
	public void einfuegen(final T daten, int position) {
		// Wenn die Liste leer oder die Position 0 ist, entspricht dies
		// einem Einf�gen am Anfang
		if (istLeer() || (position <= 0)) {
			einfuegen(daten);

			return;
		}

		// VORG�NGER ZUR POSITION FINDEN
		// Dessen Vorg�nger muss es geben: lediglich bei einer leeren
		// Liste oder f�r position==0 gibt es keinen. Genau diese F�lle
		// wurden aber oben bereits behandelt! Wird eine zu hohe position
		// �bergeben, wird automatisch das letzte Element zum Vorg�nger.
		Link<T> vorgaenger = anfang;

		while ((--position > 0) && (vorgaenger.naechster != null))
			vorgaenger = vorgaenger.naechster;

		// Neues Element erzeugen
		vorgaenger.naechster = new Link<T>(daten, vorgaenger.naechster);

		// Beim Einf�gen als letztes Element muss auch ende gesetzt werden
		if (vorgaenger == ende)
			ende = vorgaenger.naechster;
	}

	public T entfernen(int position) {
		// Wenn die Liste leer oder die position < 0 ist, wird kein Element
		// entfernt
		if (istLeer() || (position < 0))
			return null;

		// Wenn die position 0 ist, wird am Anfang der Liste gel�scht
		if (position == 0)
			return entfernen();

		// VORG�NGER ZUR POSITION FINDEN
		// Bei einer leeren Liste oder f�r position==0 gibt es keinen Vorg�nger;
		// genau diese F�lle wurden oben bereits behandelt. Es gibt aber auch
		// keinen Vorg�ner, wenn position zu gro� ist! In diesem Fall wird das
		// letzte Element NICHT Vorg�nger, damit nur tats�chlich existierende
		// Elemente aus der Liste entfernt werden.
		Link<T> vorgaenger = anfang;

		while ((--position > 0) && (vorgaenger != null))
			vorgaenger = vorgaenger.naechster;

		// Gibt es ein Elment zum l�schen?
		if ((vorgaenger == null) || (vorgaenger.naechster == null))
			return null;

		final T opfer = vorgaenger.naechster.daten;

		// Element l�schen und ggf. ende anpassen beim L�schen des letzten
		// Listen-Elements
		if ((vorgaenger.naechster = vorgaenger.naechster.naechster) == null)
			ende = vorgaenger;

		return opfer;
	}

	// Liefert die Position eines bestimmten Elements oder -1 zur�ck.
	// Existiert ein Element mehrfach, wird die Position des ersten Vorkommens
	// zur�ckgegeben.
	public int suchen(final T daten) {
		// Liste leer?
		if (istLeer())
			return -1;

		Link<T> zeiger = anfang;
		int position = 0;

		// Abfrage auf Gleichheit ist m�glich mit equals (Operation von der Klasse
		// Object)
		while ((zeiger != null) && !zeiger.daten.equals(daten)) {
			position++;
			zeiger = zeiger.naechster;
		}

		return (zeiger == null) ? -1 : position;
	}

	public ListeIterator<T> iterator() {
		return new ListeIterator<T>(anfang, ende);
	}

	public Link<T> getAnfang() {
		return anfang;
	}

	public Link<T> getEnde() {
		return ende;
	}

	// Pr�fen, ob Liste leer ist
	public boolean istLeer() {
		// Diese Methode wird im Praktikum implementiert
		// TODO
		return anfang == null;
	}

	public void verketten(Liste<T> zweiteListe) {

		// Verkettung nur durchf�hren, wenn beide Listen nicht identisch sind
		if (zweiteListe == this)
			return;

		// Eigentliche Verkettung
		if (istLeer()) {
			// Sonderfall: die eigene Liste (this) ist leer!
			anfang = zweiteListe.anfang;
		} else {
			// Wenn die eigene Liste (this) nicht leer ist, muss
			// ende != null sein.
			ende.naechster = zweiteListe.anfang;
		}

		// Letztes Element der ersten Liste hat jetzt das erste Element der
		// zweiten Liste als Nachfolger. Dadurch kann nun das Listenende
		// auf das ende der zweiten Liste gesetzt werden.
		// Sonderfall: wenn die zweite Liste leer ist, darf das eigene ende
		// nicht �berschrieben werden!
		if (zweiteListe.ende != null)
			ende = zweiteListe.ende;

		// Zweites Listenobjekt abkoppeln
		zweiteListe.anfang = zweiteListe.ende = null;
	}

	public int entferneWerte(final T opfer) {
		int anzGeloeschte = 0;

		// Elemente am Anfang der Liste entfernen, wenn die Nutzdaten
		// mit dem Opfer uebereinstimmen
		while (anfang != null && anfang.daten.equals(opfer)) {
			anfang = anfang.naechster;
			anzGeloeschte++;
		}

		if (anfang == null)
			// Wenn die Liste jetzt leer ist, ende korrigieren
			ende = null;
		else
		{
			Link<T> zeiger = anfang;
			// Zeiger zeigt auf ein Link-Objekt, dass Opfer nicht enthaelt
			
			while (zeiger != null && zeiger.naechster != null) {
				// Vorausschau auf den Nachfolger von Zeiger
				if (zeiger.naechster.daten.equals(opfer)) {
					// Element aus der Liste entfernen
					
					zeiger.naechster = zeiger.naechster.naechster;
					anzGeloeschte++;
					if (zeiger.naechster == null)
						// Zeiger befindet sich auf dem letzten Element; ende korrigieren
						ende = zeiger;
				} else {
					// Zeiger aktualisieren
					zeiger = zeiger.naechster;
				}
			}
		}
		
	   return anzGeloeschte;

	}

	public int entferneWerte2(final T opfer) {
		int anzGeloeschte = 0;

		Link<T> zeiger = anfang;
		Link<T> vorgaenger = null;

		while (zeiger != null) {
			if (zeiger.daten.equals(opfer)) {
				// Wenn am Anfang der Liste, anfang manipulieren
				if (vorgaenger == null) {
					anfang = zeiger.naechster;
				} else {
					// Element aus Liste entfernen; Vorg�nger bleibt der alte
					vorgaenger.naechster = zeiger.naechster;
				}

				anzGeloeschte++;
			} else {
				// Vorgaenger aktualisieren
				vorgaenger = zeiger;
			}

			// Zeiger aktualisieren
			zeiger = zeiger.naechster;
		}

		ende = vorgaenger;

		return anzGeloeschte;
	}
}
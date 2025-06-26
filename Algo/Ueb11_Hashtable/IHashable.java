public interface IHashable
{
	// Es wird gefordert, dass von den Objekten, die gespeichert werden sollen,
	// eine Methode zur Berechnung des Hash-Wertes angeboten wird.
	int hash(final int tableSize);
}
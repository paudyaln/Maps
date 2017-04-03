/*
 * interface for a map
 *
 * data stored by key, with no duplicate keys allowed
 * submitted by paudyaln
 */

public interface Map<K, V>
{
	V add (K key, V value);
	V remove (K key);
	V getValue(K key);
	boolean containsKey(K key);
	String getKeys();

	int size();
	String toString();
}


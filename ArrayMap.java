/*
 * array implementation of a map
 *
 * data stored by key, with no duplicate keys allowed
 * submitted by paudyaln
 */

public class ArrayMap<K, V> implements Map<K, V>
{
	private class Pair
	{
		private K key;
		private V value;

		public Pair(K key, V value)
		{
			this.key = key;
			this.value = value;
		}

		public String toString()
		{
			return key + "=" + value;
		}
	}

	public static final int DEFAULT_CAPACITY = 10;

	private Pair [] map; 
	private int size;

	public ArrayMap ()
	{
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public ArrayMap (int capacity)
	{
		map = (Pair []) new ArrayMap.Pair[capacity];
		size = 0;
	}	
	
	/*
  	 * if key in map, replaces old value with new and returns old value
   	 * otherwise adds key value pair and returns null
 	 */
 
	public V add (K key, V value)
	{
		checkItem(key, value);

		for (int i = 0; i < size; i++)
		{
			if (map[i].key.equals(key))
			{
				V oldValue = map[i].value;
				map[i].value = value;
				return oldValue;
			}
		}
		
		ensureSpace();
		map[size] = new Pair(key, value);
		size++;
		return null;
	}

	/*
  	 *  removes key value pair from map and returns value
   	 *  if key not found, returns null
 	 */
 
	public V remove (K key)
	{
		if(containsKey(key)){
			V temp = null;
			for(int i = 0; i < size; i++){
				if(map[i].key.equals(key)){
					temp = map[i].value;
					map[i].value = map[size-1].value;
					map[i].key = map[size-1].key;
					map[size-1].value = null;
					map[size-1].key = null;
					size--;
					return temp;
				}
			}
		}
		else return null;

		return null;
	}

	/*
  	 *  returns value stored for key
   	 *  if key not found, returns null
 	 */
 
	public V getValue(K key)
	{
		if(key == null){
			//if key is null
			throw new NullPointerException("Key cannot be null");
		}
		//check key
		if(containsKey(key)) {
			for (int i = 0; i < size; i++) {
				if (map[i].key.equals(key)) {
					//return key value from map
					return map[i].value;
				}
			}
		}
		//if key not found
		return null;
	}

	/*
	 * returns true if key is in map
 	 */
 
	public boolean containsKey(K key)
	{
		for (int i = 0; i < size; i++)
		{
			if (map[i].key.equals(key))
			{
				return true;
			}
		}

		return false;
	}

	public int size()	
	{
		return size;
	}

	public String getKeys() 
	{
		if (size == 0)
		{
			return "[]";
		}
		
		String a = "[";

		for (int i = 0; i < size-1; i++)
		{
			a+= map[i].key + ", ";
		}

		return a+ map[size-1].key + "]";
	}

	public String toString()
	{
		if (size == 0)
		{
			return "{}";
		}
		
		String a = "{";

		for (int i = 0; i < size-1; i++)
		{
			a+= map[i] + ", ";
		}

		return a+ map[ size-1] + "}";
	}

	private void checkItem (K key, V value)
	{
		if (key == null || value == null)
		{
			throw new IllegalArgumentException("Key/value can't be null");
		}
	}

	private void ensureSpace()
	{
		if (size == map.length)
		{
			@SuppressWarnings("unchecked")
			Pair [] larger = (Pair []) new ArrayMap.Pair[size*2];
		
			for (int i = 0; i < size; i++)
			{
				larger[i] = map[i];
			}

			map = larger;
			larger = null;
		}
	}
}

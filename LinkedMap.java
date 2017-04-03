/*
 * linked implementation of a map
 *
 * data stored by key, with no duplicate keys allowed
 * Submitted by Nischal
 */

public class LinkedMap<K, V> implements Map<K, V>
{
	private class Node
	{
		private K key;
		private V value;
		private Node next;

		public Node(K key, V value)
		{
			this.key = key;
			this.value = value;
			next = null;
		}

		public String toString()
		{
			return key + "=" + value;
		}
	}

	private Node head;
	private int size;

	public LinkedMap ()
	{
		head = null;
		size = 0;
	}
	
	/*
  	 * if key in map, replaces old value with new and returns old value
   	 * otherwise adds key value pair and returns null
 	 */

	public V add (K key, V value)
	{
		checkItem(key, value);

		Node current = head;

		for (int i = 0; i < size; i++)
		{
			if (current.key.equals(key))
			{
				V oldValue = current.value;
				current.value = value;
				return oldValue;
			}

			current = current.next;
		}

		Node newest  = new Node(key, value);
		newest.next = head;
		head = newest;
		size++;
		return null;
	}

	/*
  	 *  removes key value pair from map and returns value
   	 *  if key not found, returns null
 	 */
 
	public V remove (K key)
	{
		//traverse node
		Node current = head;
		Node previous = head;

		if(head.key.equals(key)){
			head = head.next;
			size--;
			return previous.value;
		}
		//removing the value
		while(current.next != null){
			previous = current;
			current = current.next;
			if(current.key.equals(key)){
				V removed = current.value;
				previous.next = current.next;
				size--;
				return removed;
			}
		}
		return null;
	}

	/*
	 * returns value associated with key
   	 * if key not found, returns null
 	 */
 
	public V getValue(K key)
	{
		return getNode(key).value;
	}

	/*
	 * returns true if key is in map 
 	 */
 
	public boolean containsKey(K key)
	{
		Node current = head;
		while (current != null)
		{
			if (current.key.equals(key))
			{
				return true;
			}

			current = current.next;
		}

		return false;
	}

	public int size()	
	{
		return size;
	}

	public String getKeys()
	{
		String list = "[";
	
		Node current = head;
		
		while (current.next != null)
		{
			list += current.key + ", ";
			current = current.next;
		}
		
		return list + current.key + "]";
	}

	public String toString()
	{
		if (size == 0)
		{
			return "{}";
		}

		String list = "{";
		Node current = head;
		
		while (current.next != null)
		{
			list += current + ", ";
			current = current.next;
		}
		
		return list + current + "}";
	}

	private void checkItem (K key, V value)
	{
		if (key == null || value == null)
		{
			throw new IllegalArgumentException("Key/value can't be null");
		}
	}

	private Node getNode (K key)
	{
		Node current = head;
		int count = 0;

		while (current != null)
		{
			if (current.key.equals(key))
			{
				return current;
			}
	
			current = current.next;	
			count++;
		}

		return null;
	}
}

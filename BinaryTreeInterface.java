
public interface BinaryTreeInterface {
	
	public boolean add(String word, String def);
	public boolean contains(String word);
	
	/**
	 * Tells if the tree is empty or not by checking if the root
	 * is null
	 * @return true/false depending on if the tree is empty or no
	 */
	public boolean isEmpty();
	public void clear();
	public String remove(String word);
	public String toString();
	
	

}

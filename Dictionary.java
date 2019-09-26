
public class Dictionary implements BinaryTreeInterface {
	
	//create new Node to be root of tree
	private Node root = new Node();
	
	//constructor
	public Dictionary() {
		root = null;
	}// end constructor


	/**
	 * calls privateAdd method so root is hidden
	 * @param word String for word in dictionary
	 * @param def String for definition of entry
	 * @return boolean of call to privateAdd method
	 */
	public boolean add(String word, String def) {
		return privateAdd(root, word, def);
	}
	
	/**
	 * adds entry as root if the dictionary is empty
	 * adds entry as new node otherwise.
	 * @param rootNode root passed in from add method
	 * @param word string for word in dictionary
	 * @param def string for definition of entry
	 * @return returns false if the entry was already in
	 * the dictionary. Returns true otherwise
	 */
	private boolean privateAdd(Node rootNode, String word, String def) {
		// TODO Auto-generated method stub
		boolean result = true;
		if(contains(word))
			result = false;
		else if(isEmpty()) {
			Node newNode = new Node(word, def);
			root = newNode;
		}// end else if
		else {
			Node newNode = new Node(word, def);
			int comparison = newNode.getWord().compareTo(rootNode.getWord());
			if (comparison < 0) {
				if (rootNode.hasLeft())
					privateAdd(rootNode.getLeft(), word, def);
				else rootNode.setLeft(new Node(word, def));
			}//end if
			if (comparison > 0) {
				if (rootNode.hasRight())
					privateAdd(rootNode.getRight(), word, def);
				else
					rootNode.setRight(new Node(word, def));
			}//end if
		}//end if
		
		return result;
	}
	
	

		
	


	
	/**
	 * public implementation of contains, return true of false depending
	 * on if the word was found in the tree or not
	 */
	@Override
	public boolean contains(String word) {
	
		return find(root, word);
	}
	
	/**
	 * searches tree for instance of word
	 * returns true if word is found
	 * returns false if word is not found
	 * @param rootNode root of tree
	 * @param word String of word being searched for
	 * @return true/false depending on if word was found or not
	 */
	private boolean find(Node rootNode, String word) {
		boolean result=false;

		if(rootNode!=null){
			String rootWord = rootNode.getWord();
			if(word.equals(rootWord))
				result = true;
			else if(word.compareTo(rootWord)<0)
				result = find(rootNode.getLeft(), word);
			else
				result = find(rootNode.getRight(), word);
		}
		return result;
	}
	
	/**
	 * public implementation of findWord, returns the node associated with 
	 * the word field that is being searched for if it exists
	 * @param word word being searched for 
	 * @return Node associated with word being searched for
	 */
	public Node findWord(String word) {
		return privateFindWord(root, word);
	}
	
	/**
	 * private implementation of findWord
	 * @param rootNode root of the subtree that is being searched
	 * @param word word that is being searched for
	 * @return Node associated with word being searched for 
	 */
	private Node privateFindWord(Node rootNode, String word) {
		Node wordNode = new Node();
		String rootWord = rootNode.getWord();
		wordNode = null;
		if(isEmpty())
			return wordNode;
		else if(rootNode!=null) {
			if(word.equals(rootWord)) {
				wordNode=rootNode;
				return wordNode;
			}
			else if(word.compareTo(rootWord)<0)
				privateFindWord(rootNode.getLeft(), word);
			else
				privateFindWord(rootNode.getRight(), word);
		}
		return wordNode;
	}

	
	/**
	 * tells if the tree is empty by telling if the tree's main root is null or not
	 */
	@Override
	public boolean isEmpty() {
		return (root == null);
	}


	/**
	 * clears tree by setting trees main root to null
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		root = null;
	}


	/**
	 * public implementation or remove
	 * removes the entry and returns the string value of the 
	 * word that was removed
	 */
	@Override
	public String remove(String word) {
		// TODO Auto-generated method stub
		Node oldEntry = new Node();
		Node newRoot = removeEntry(root, word, oldEntry);
		
		return oldEntry.getWord();
	}
	
	/**
	 * private implementation or remove
	 * @param rootNode root of the subtree that is being searched for 
	 * the node to remove
	 * @param word word being removed
	 * @param oldEntry 
	 * @return
	 */
	private Node removeEntry(Node rootNode, String word, Node oldEntry) {
		if(rootNode!=null) {
			String rootWord = rootNode.getWord();
			int comp = word.compareTo(rootWord);
			
			if(comp==0) {
				oldEntry.setWord(rootWord);
				rootNode = removeRoot(rootNode);
			}
			else if(comp<0) {
				Node leftChild = rootNode.getLeft();
				Node subRoot = removeEntry(leftChild, word, oldEntry);
				rootNode.setLeft(subRoot);
			}
			else {
				Node rightChild = rootNode.getRight();
				rootNode.setRight(removeEntry(rightChild, word, oldEntry));
			}
		}
		return rootNode;
	}
	
	/**
	 * method for removing root of subtree
	 * @param rootNode root of subtree that is to be removed
	 * @return root that was removed
	 */
	private Node removeRoot(Node rootNode) {
		if(rootNode.hasLeft() && rootNode.hasRight()) {
			Node leftSub = rootNode.getLeft();
			Node largestNode = rootNode.findLargest(leftSub);
			
			rootNode.setWord(largestNode.getWord());
			rootNode.setDef(largestNode.getDef());
			
			rootNode.setLeft(removeLargest(leftSub));
		}
		else if(rootNode.hasRight())
			rootNode=rootNode.getRight();
		else
			rootNode=rootNode.getLeft();
		return rootNode;
	}
	
	

	/**
	 * find largest node in subtree and remove it 
	 * @param rootNode root of subtree that is being traversed
	 * @return largest node that was removed
	 */
	private Node removeLargest(Node rootNode) {
		if(rootNode.hasRight()) {
			Node rightChild = rootNode.getRight();
			Node subRoot = removeLargest(rightChild);
			rootNode.setRight(subRoot);
		}
		else {
			rootNode = rootNode.getLeft();
		}
		return rootNode;
	}



	
	/**
	 * prints out the word and definition of the node in a well formatted way
	 * @param wordNode node that is to have word and def printed out
	 */
	public void toStrings(Node wordNode) {
		String word = wordNode.getWord();
		String def = wordNode.getDef();
		String entry = word + "\n" + " - " + def;
		System.out.println(entry);
	}
	
	/**
	 * public implementation of listAll, calls privateListAll
	 */
	public void listAll() {
		privateListAll(root);
	}
	

	/**
	 * lists all words and def in alphabetical order
	 * @param node root of the subtree that is being traversed
	 */
	private void privateListAll(Node node) {
		if(node!=null) {
			privateListAll(node.getLeft());
			//toStrings(findWord(node.getWord()));
			System.out.println(node.getWord());
			System.out.println("  - " + node.getDef());
			System.out.println("");
			privateListAll(node.getRight());
		}
		
	}//end dictionary class
	
	
	
	
	
	
	
	//IM PUTTING A SEPARATION HERE BECAUSE I KEEP FORGETTING WHAT CLASS IM IN
	
	
	
	
	//start of node class
	private class Node {

		private String word; //holds word
		private String def; //holds definition
		private Node left; //holds left child
		private Node right; //holds right child

		//no arg constructor that sets all value to null
		private Node() {
			word = null;
			def = null;
			left = null;
			right = null;
		}
		
		//parameterized constructor takes in a word and def and populates
		//corresponding fields
		private Node(String word, String def) {
			this.word=word;
			this.def=def;
			left = null;
			right = null;
		}
		
		/**
		 * sets word field of a node
		 * @param word word to populate word field 
		 */
		private void setWord(String word) {
			this.word = word;
		}
		
		
		/**
		 * sets definition field of a node 
		 * @param def definition to populate def field 
		 */
		private void setDef(String def) {
			this.def = def;
		}
		
		/**
		 * sets right child of a node 
		 * @param right node to become right child 
		 */
		private void setRight(Node right) {
			this.right=right;
		}
		
		/**
		 * sets left child of a node 
		 * @param left node to become left child 
		 */
		private void setLeft(Node left) {
			this.left = left;
		}
		
		
		/**
		 * 
		 * @return word field of the node 
		 */
		private String getWord() {
			return word;
		}
		
		/**
		 * 
		 * @return definition field of the node 
		 */
		private String getDef() {
			return def;
		}
		
		/**
		 * 
		 * @return left child of the node 
		 */
		private Node getLeft() {
			return left;
		}
	
		/**
		 * 
		 * @return right child of the node 
		 */
		private Node getRight() {
			return right;
		}
		
		/**
		 * 
		 * @return true / false based on if node has a right child or not 
		 */
		private boolean hasRight() {
			return (right != null);
		}
		
		/**
		 * 
		 * @return true / false based on if node has left child or not 
		 */
		private boolean hasLeft() {
			return (left != null);
		}
		
		/**
		 * finds largest node in a give tree
		 * @param rootNode root of the tree that is being traversed 
		 * @return largest node found in tree
		 */
		private Node findLargest(Node rootNode) {
			if(rootNode.hasRight())
				rootNode = findLargest(rootNode.getRight());
			
			return rootNode;
		}
		
		
		
	}//end Node class
}
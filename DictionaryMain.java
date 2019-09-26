import java.util.Scanner;


public class DictionaryMain {
	
	//Dictionary object to store words and defs in 
	static Dictionary myDict = new Dictionary();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    
		//clears screen of all old text before opening new menu
		clearScreen();
		//calls menu to begin program
		menu();

	}

	/*
	 * Main menu that gives option to add a word, remove a word, search for a word,
	 * list the dictionary in alphabetical order, and clear the dictionary
	 */
	public static void menu() {
		System.out.println("Welcome to the Dictionary!");
		System.out.println("Please select what you would like to do: ");
		System.out.println("1) Enter a new word");
		System.out.println("2) Remove a word");
		System.out.println("3) Search for a word");
		System.out.println("4) List the contents of the dictionary");
		System.out.println("5) Clear the contents of the dictionary");
		System.out.println("");
		System.out.println("- You can return to this menu at any time by entering '*m' ");
		
		//create new scanner to read user input
		Scanner keyboard = new Scanner(System.in);
		//read the next line the user enter as an int
		int input = keyboard.nextInt();
		keyboard.nextLine();
		
		//switch statement to open the correct menu depending on the users selection
		switch (input){
		case 1:
			//open menu to add a word
			clearScreen();
			addMenu();
			break;
		case 2:
			//open menu to remove a word
			clearScreen();
			removeMenu();
			break;
		case 3:
			//open menu to search for a word 
			clearScreen();
			searchMenu();
			break;
		case 4:
			//open menu to list all words
			clearScreen();
			listMenu();
			break;
		case 5: 
			//warning message to tell user that clearing the dictionary cannot
			//be undone
			clearScreen();
			clearWarning();
			break;
		default:
			//else stay at the main menu
			clearScreen();
			menu();
		}
	}
	
	
	//menu to allow the user to enter a word and def to the dictionary
	public static void addMenu() {
		//string variable to hold user input on whether or not to continue adding words
		String cont = "y";
		
		//do while loop that will continue until the user decides to stop 
		//adding words
		do {
		System.out.println("Enter the word you would like to add: ");
		Scanner keyboard = new Scanner(System.in);
		//read in user input for the word
		String word = keyboard.nextLine();
		
		//if the user enters '*m' return to the main menu
		if(word.equals("*m")) {
			menu();
			break;
		}

		System.out.println("Enter the definition of this word: ");
		//read in user input for the def
		String def = keyboard.nextLine();
		
		//if user enter '*m' return to the main menu
		if(def.equals("*m")) {
			clearScreen();
			menu();
			break;
		}
		else
			//add method called to add a node to the binary tree with fields
			//for the word and def
			myDict.add(word, def);
		System.out.println("Would you like to add another word? (y/n)");
		//read in user input on whether they would like to continue or not 
		cont = keyboard.nextLine();
		//if user enters '*m" return to the main menu
		if(cont.equals("*m")) {
			clearScreen();
			menu();
			break;
		}
		}while(cont.equals("y"));
		//if the loop is broken out of by the user deciding not to enter any more
		//words, go back to the main menu
		clearScreen();
		menu();
	}
	
	//menu that lists all words in the dictionary in alphabetical order
	public static void listMenu() {
		//calls listAll method to traverse the tree in order
		myDict.listAll();
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter '*m' when ready to return to the main menu");
		//read in user input
		String in = keyboard.nextLine();
		//if user enter '*m' return to main menu
		if(in.equals("*m")) {
			clearScreen();
			menu();
		}
	}
	
	//menu that allows user to remove words from the dictionary
	public static void removeMenu() {
		//String variable to hold user input on whether or no to continue adding words
		String cont = "y";
		//do while loop that will continue as long as the user wants to keep removing words
		do {
		System.out.println("Enter the word you would like to remove: ");
		Scanner keyboard = new Scanner(System.in);
		//take in user input for the name of the word they would like to remove
		String rem = keyboard.nextLine();
		//if the user enters '*m' return to main menu
		if(rem.equals("*m")) {
			clearScreen();
			menu();
			break;
		}
		//check if the word is in the dictionary, if it is not, tell this to 
		//the user, as a word cannot be removed if it does not first exist
		else if(!myDict.contains(rem)) {
			System.out.println("Sorry, this word is not in the dictionary");
		}
	    //if the word does exist in the dictionary, call the remove method to remove it
		else
			myDict.remove(rem);
			
		System.out.println("Would you like to remove another word? (y/n)");
		//read in user input on whether or not they want to remove another word
		cont = keyboard.nextLine();
		//if user enters '*m" return to main menu
		if(cont.equals("*m")) {
			clearScreen();
			menu();
			break;
		}
		}while(cont.equals("y"));
		//if the loop has been broken out of by the user no wanting to remove any
		//more words, return to the main menu
		clearScreen();
		menu();
	}
	
	//menu to allow user to search for the definition of a specific word
	public static void searchMenu() {
		//string variable to hold user input on whether to serach for another word or not
		String cont = "y";
		//do while loop that will run as long as the user wants to keep
		//searching for words
		do {
			System.out.println("Enter the word you would like to search for: ");
			Scanner keyboard = new Scanner(System.in);
			//read in user input of the word they would like to search for 
			String word = keyboard.nextLine();
			//if user enters '*m' return to main menu
			if(word.equals("*m")) {
				clearScreen();
				menu();
				break;
			}
			//calls contains method to check if the word is in the dictionary, 
			//if not, tell this to the user
			else if(!myDict.contains(word))
				System.out.println("Sorry, that word is not in the dictionary");
			//else print out the word and def of the word being searched for 
			else {
				myDict.toStrings(myDict.findWord(word));
			}
			
			System.out.println("Would you like to search for another word? (y/n)");
			//read user input on whether or not they want to enter in another word 
			cont = keyboard.nextLine();
			//if user enters '*m' return to main menu
			if(cont.equals("*m")) {
				clearScreen();
				menu();
				break;
			}
			
		}while(cont.equals("y"));
		//if loop has been broken out of by user not wanting to search for any more 
		//words, return to main menu
		clearScreen();
		menu();
	}
	
	//warns the user that clearing the dictionary cannot be undone and asks if they
	//want to continue
	public static void clearWarning() {
		System.out.println("Are you sure you want to clear the dictionary? (y/n)\nThis action"
				+ " cannot be reversed");
		Scanner keyboard = new Scanner(System.in);
		//string variable to hold user input on if they want to clear the dict or not
		String input = keyboard.nextLine();
		//if user enters yes the dictionary is cleared and returns to menu
		if(input.equals("y")) {
			myDict.clear();
			clearScreen();
			menu();
		}
		//if user does not enter yes simply returns to main menu
		else
			clearScreen();
			menu();
	}
	
	//clears the screen by printing out a lot of white space
	//there is probably a better way to do this but this is due in 6 hours so 
	//this is the easiest solution 
	public static void clearScreen() {  
	    for(int i=0; i<=75; i++) {
	    	System.out.println("\n");
	    }
	} 
	
	
	
}

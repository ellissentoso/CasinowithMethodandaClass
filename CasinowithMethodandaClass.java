/* Name: Ellis Sentoso
*  Instructor : Dave Harden
*  Class: CS1 - Java
*  Assignment: Lab 7 - Casino with Methods and a Class
*  Description: Simulation of a  real life slot machine with probability involvement, class (instance vs static) and methods implementation,
*  			    and fields implementation
*  Email: ellissentoso@gmail.com
*  Date: 07/26/2019
*/
package assignment7;

import java.util.Scanner;  // Import the Scanner class
import java.lang.Math; 


public class Foothill {
	// Variables for Bets
	static final int MIN_BET = 1;
	static final int MAX_BET = 50;
	static final int NULL = 0;
	// Variables for Pools of Numbers based on % Probability
	static final int SPACE_MIN = 1; // 7% Probability
	static final int SPACE_MAX = 70;// 7% Probability
	static final int SEVEN_MIN = 71;// 15% Probability
	static final int SEVEN_MAX = 220;//15% Probability
	static final int BAR_MIN = 221;	 //38% Probability
	static final int BAR_MAX = 600;	 //38% Probability
	static final int CHERRY_MIN = 601; //40% Probability
	static final int CHERRY_MAX = 1000;//40% Probability

	// String variables
	static final String CHERRIE_ = "cherries";
	static final String BAR_ = "BAR";
	static final String SEVEN_ = "7";
	static final String SPACE_ = "(space)";
	// Multiplier Variables
	static final int MULT_0= 5;
	static final int MULT_1 = 15;
	static final int MULT_2 = 30;
	static final int MULT_3 = 50;
	static final int MULT_4 = 100;
	/*------------------------------------------------------------------------------------------------------------------------*/
	//Precondition: inputs from the user of int
	//Postcondition: return int
	public static int getBet() {
		Scanner input = new Scanner(System.in);  // Create a Scanner object
		int bet = 0;
		do {
			System.out.print("How much would you like to bet (1 - 50) or 0 to quit?\n");
			bet = input.nextInt();
			if (bet == 0) 		// If the user bet 0, then exit
				System.exit(0);
		} while (bet < MIN_BET || bet > MAX_BET); // Legal boundaries
		input.close(); // Prevent leaking
		return bet;		
	}
	// This is a private class method
/*------------------------------------------------------------------------------------------------------------------------*/
	//Precondition: random generation of numbers from 1-1000
	//Postcondition: return string
	private static String randomString() {
		String str = "";

		// Generate value between 1-1000 and Pull() method will call this 3 times
		int rand = (int)(1000*Math.random()); // Random generating of # 1-1000
//		System.out.println("the rand: " + rand);      debug statement  
		if (rand >= SPACE_MIN && rand <= SPACE_MAX) {
			str = SPACE_; // Pool of # that generates SPACE
		}
		else if (rand >= SEVEN_MIN && rand <= SEVEN_MAX) {
			str = SEVEN_; // Pool of # that generates SEVEN
		}
		else if (rand >= BAR_MIN && rand <= BAR_MAX) {
			str = BAR_; // Pool of # that generates BAR
		}
		else if (rand >= CHERRY_MIN && rand <= CHERRY_MAX) {
			str = CHERRIE_; // Pool of # that generates CHERRY
		}
		return str;

	}
/*------------------------------------------------------------------------------------------------------------------------*/
	//Precondition: an array of string
	//Postcondition: return TripleString object
	public static TripleString pull() {
		String [] str = new String[3];		// creating an array of strings
		for(int i = 0; i < 3; i++) { 		// Calling it 3 times 
			str[i] = randomString();		// Generate String randomly 
//			System.out.println(str[i]);		// Print the string
		}
		// Creating an object of TripleString class with the array of str
		TripleString obj = new TripleString (str[0], str[1], str[2]);
		return obj;
	}
/*------------------------------------------------------------------------------------------------------------------------*/
	
	public static void main(String[] args) {
		int bet = Foothill.getBet();
		int mult;
//		System.out.println(bet);
		TripleString obj = pull(); // Creating Triple STring Obj
		mult = Foothill.getPayMultiplier(obj); // Invoking statically
		int winnings = bet * mult;	// Total winnings
		Foothill.display(obj, winnings); // Displaying
	}
/*------------------------------------------------------------------------------------------------------------------------*/
	//Precondition: TripleString thePull object
	//Postcondition: return amount of multiplier based on the TripleString Object
	public static int getPayMultiplier(TripleString thePull) {
		if (thePull.getString1() == CHERRIE_ && thePull.getString2() != CHERRIE_) {
			return MULT_0; // Multiplier amount #1
		}
		else if (thePull.getString1() == CHERRIE_ && thePull.getString2() == CHERRIE_ && thePull.getString3() != CHERRIE_) {
			return MULT_1; // Multiplier amount #2
		}
		else if (thePull.getString1() == CHERRIE_ && thePull.getString2() == CHERRIE_ && thePull.getString3() == CHERRIE_) {
			return MULT_2; // Multiplier amount #3
		}
		else if (thePull.getString1() == BAR_ && thePull.getString2() == BAR_ && thePull.getString3() == BAR_) {
			return MULT_3; // Multiplier amount #4
		}
		else if (thePull.getString1() == SEVEN_ && thePull.getString2() == SEVEN_ && thePull.getString3() == SEVEN_) {
			return MULT_4; // Multiplier amount #5

		}
		else
			return 0;

	}
	/*------------------------------------------------------------------------------------------------------------------------*/
	//Precondition: TripleString thePull, int winnings 
	//Postcondition: display to the user
	public static void display (TripleString thePull, int winnings ) {
		System.out.println("whirrrrrr .... and your pull is ...");
		System.out.print(" ");
		System.out.print(thePull.getString1());
		System.out.print("  ");
		System.out.print(thePull.getString2());
		System.out.print("  ");
		System.out.print(thePull.getString3());
		System.out.println();
		if (winnings == 0) {
			System.out.println("sorry - you lost");
		}
		else {
			System.out.println("congrats, you won $" + winnings + ".");
		}
	}

}
/*------------------------------------------------------------------------------------------------------------------------*/
class TripleString {
	// Private Class Instance Members
	private String string1;
	private String string2;
	private String string3;

	// Public Class Static Constants
	public static final int MAX_LEN = 50;
	public static final int MIN_LEN = 1;
	public static final String DEFAULT_STRING = " (undefined) ";

	// A default ctor which initializes all variables to DEFAULT_STRING
	public TripleString() {
		string1 = DEFAULT_STRING;
		string2 = DEFAULT_STRING;
		string3 = DEFAULT_STRING;
	}
	/*______________________________________________________________________________________________*/

	// A constructor that initializes all members according to the passed parameters. 
	// If not valid, it will change the corresponding one to DEFAULT_STRING
	public TripleString(String str1, String str2, String str3) {
		if (validString(str1)) {
			string1 = str1;
		}
		else {
			string1 = DEFAULT_STRING;
		}
		if (validString(str2)) {
			string2 = str2;
		}
		else {
			string2 = DEFAULT_STRING;
		}
		if (validString(str3)) {
			string3 = str3;
		}
		else {
			string3 = DEFAULT_STRING;
		}
	}
	/*______________________________________________________________________________________________*/
	// A mutator for string1 which changes it to str if valid. If not valid, adopts no action
	public boolean setString1( String str ) {
		if (validString(str)) {
			string1 = str;
			return true;
		}
		return false;
	}
	/*______________________________________________________________________________________________*/

	// A mutator for string2 which changes it to str if valid. If not valid, adopts no action
	public boolean setString2( String str ) {
		if (validString(str)) {
			string2 = str;
			return true;
		}
		return false;
	}
	/*______________________________________________________________________________________________*/

	// A mutator for string3 which changes it to str if valid. If not valid, adopts no action
	public boolean setString3( String str ) {
		if (validString(str)) {
			string3 = str;
			return true;
		}
		return false;
	}
	/*______________________________________________________________________________________________*/

	// An accessor for string1
	public String getString1() {
		return string1;
	}
	/*______________________________________________________________________________________________*/

	// An accessor for string2
	public String getString2() {
		return string2;
	}
	/*______________________________________________________________________________________________*/

	// An accessor for string3
	public String getString3() {
		return string3;
	}
	/*______________________________________________________________________________________________*/

	// An accessor for all strings in the form of "string1: string2: string3: "
	public String toString() {
		String str1, str2, str3;

		// it will give "null" if it is a null String
		if (string1 == null) {
			str1 = "null";
		}
		else {
			str1 = string1;
		}
		if (string2 == null) {
			str2 = "null";
		}
		else {
			str2 = string2;
		}
		if (string3 == null) {
			str3 = "null";
		}
		else {
			str3 = string3;
		}

		return "string1: " + str1 + " string2: " + str2 + " string3: " + str3;
	}
	/*______________________________________________________________________________________________*/

	// Private helper method to determine if it is valid
	private boolean validString( String str ) {
		// If null, return false
		if (str == null)
			return false;
		// If not, return if str is within the range
		return (str.length() >= MIN_LEN && str.length() <= MAX_LEN);
	}
}
/*---------------------------------------------Sample Run--------------------------------------------------------
 * How much would you like to bet (1 - 50) or 0 to quit?

50
whirrrrrr .... and your pull is ...
 cherries  cherries  BAR
congrats, you won $750.

How much would you like to bet (1 - 50) or 0 to quit?

2
whirrrrrr .... and your pull is ...
 space  BAR BAR
sorry - you lost

How much would you like to bet (1 - 50) or 0 to quit?

0
 
How much would you like to bet (1 - 50) or 0 to quit?
2
whirrrrrr .... and your pull is ...
 BAR  cherries  cherries
sorry - you lost

How much would you like to bet (1 - 50) or 0 to quit?
-1
How much would you like to bet (1 - 50) or 0 to quit?
-3
 * 
 * 
 * 
 * 
 * */

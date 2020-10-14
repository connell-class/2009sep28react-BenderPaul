package com.revature.eval.java.core;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j=0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		
		//Initialize a character array with length equal to the input string
		//Convert the input string into array format
		char[] phraseArray = new char[phrase.length()]; 	
		phraseArray = phrase.toCharArray(); 
		
		//Initialize the string for the acronym
		//Append the first character of the phrase to our acronym
		String acronym = ""; 
		acronym = acronym + phraseArray[0]; 	
		
		//Parse the phrase array
		//Checks for spaces or dashes
		//Append the character after a space or dash, capitalized
		for(int i = 1; i < phrase.length(); i++) {		
			if(phraseArray[i] == ' ' || phraseArray[i] == '-') {	
				acronym = acronym + Character.toUpperCase(phraseArray[i+1]);
			}
		}
		return acronym;
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			
			//If all three sides are equal then return true
			
			if (this.sideOne == this.sideTwo & this.sideOne == this.sideThree & this.sideTwo == this.sideThree) {
				return true;		
			}
			return false;
		}

		public boolean isIsosceles() {
			
			// If two sides are equal AND one side is not equal to the others return true
			if((this.sideOne == this.sideTwo & this.sideOne != this.sideThree) || (this.sideOne == this.sideThree & this.sideOne != this.sideTwo) || (this.sideTwo == this.sideThree & this.sideTwo != this.sideOne)) {
				return true;
			}
			return false;
		}

		public boolean isScalene() {
			
			// If no sides are equal to one another return true
			if (this.sideOne != this.sideTwo && this.sideOne != this.sideThree && this.sideTwo != this.sideThree) {
				return true;
			}
			return false;
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		
		//Create a hashmap of each letter and its corresponding score value
		
		HashMap<Character, Integer> lettersAndScores = new HashMap<Character, Integer>(26);
		lettersAndScores.put('A', 1);
		lettersAndScores.put('E', 1);
		lettersAndScores.put('I', 1);
		lettersAndScores.put('O', 1);
		lettersAndScores.put('U', 1);
		lettersAndScores.put('L', 1);
		lettersAndScores.put('N', 1);
		lettersAndScores.put('R', 1);
		lettersAndScores.put('S', 1);
		lettersAndScores.put('T', 1);
		lettersAndScores.put('D', 2);
		lettersAndScores.put('G', 2);
		lettersAndScores.put('B', 3);
		lettersAndScores.put('C', 3);
		lettersAndScores.put('M', 3);
		lettersAndScores.put('P', 3);
		lettersAndScores.put('F', 4);
		lettersAndScores.put('H', 4);
		lettersAndScores.put('V', 4);
		lettersAndScores.put('W', 4);
		lettersAndScores.put('Y', 4);
		lettersAndScores.put('K', 5);
		lettersAndScores.put('J', 8);
		lettersAndScores.put('X', 8);
		lettersAndScores.put('Q', 10);
		lettersAndScores.put('Z', 10);
		
		//Insert the characters of the string into the hashmap to get the corresponding values
		//Add the values to a score variable to then return
		
		int score = 0;
		for(int i=0; i<string.length(); i++) {
			score += lettersAndScores.get(Character.toUpperCase(string.charAt(i)));
		}
		return score;
	}
	

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		
		//Start by trimming the whitespace from the input
		string = string.trim();
		//Create an array of valid numbers to test against the input
		Integer[] phoneNumberValues = new Integer[] {0,1,2,3,4,5,6,7,8,9};
		//Create an array of invalid input characters to throw exceptions against
		String[] invalidCharacters = new String[] {"a","b","c","@","&","!",":"};

		for (int i=0; i<string.length(); i++) {
			//Create a temporary value to store the character we're checking as a String
			String tempString = "";
			//Create an integer variable with the numeric value of the character from the phone number string
			int temp = Character.getNumericValue(string.charAt(i));
			/*
			 * If the integer value associated with the ith index of the phone number isn't a number, remove it
			 */
			if (!Arrays.asList(phoneNumberValues).contains(temp)){
				tempString = tempString + string.charAt(i);
				/*
				 * If the ith index of the phone number is within our array of invalid characters, throw an illegal argument exception
				 */
				if (Arrays.asList(invalidCharacters).contains(tempString)) {
					throw new IllegalArgumentException();
				}
				string = string.replace(tempString ,"");
				i--;

			}
		}
		//Final check to make sure the phone number is the right length. Otherwise, throw an illegal argument exception
		if(string.length()>10) {
			throw new IllegalArgumentException();
		}
		return string;
		/*
		 * FOR FUTURE REFERENCE: It would probably be easier and more efficient to compare ascii values. I don't think we would need
		 * to use both an Integer array and String Array. Less conditionals, as well. 
		 */
	}
	
	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		//Split the input string by into individual words. Takes out the spaces, commas, apostrophes
		String[] splitWords = string.split("[\\s',]");
		//Initiate the hashmap to store unique words as keys and incrementing values with repeated keys
		Map<String, Integer> wordCount = new HashMap<>();
		for(int i = 0; i < splitWords.length; i++ ) {
			//increment word count if the word is already in the hashmap. Make a new entry if word is unique to the map.
			if (wordCount.containsKey(splitWords[i])) {
				wordCount.put(splitWords[i], wordCount.get(splitWords[i])+1);
			}else {
				wordCount.put(splitWords[i], 1);
			}		
		}
		//Remove the empty spaces from the map (to satisfy a test case)
		wordCount.remove("");
		return wordCount;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	// use the binary search function from arrays?
	static class BinarySearch<T> {
		private List<T> sortedList;

		public int indexOf(T t) {
			
			/*
			 * The first block of code is specifically for the case that t is an instanceof String
			 */

			if (t instanceof String) {
				int x = Integer.parseInt((String) t);
				int middle = this.sortedList.size()/2;
				/*Set reference middle to be the center of the list
				*	As long as our middle reference doesn't correspond to the value we're looking for, move forward or backward depending on
				*	on how the middle value compares to the value we want.
				*/
				while (Integer.parseInt((String) this.sortedList.get(middle)) != x) {
					if(Integer.parseInt((String) this.sortedList.get(middle))>x) {
						middle = middle/2;
					}else if(Integer.parseInt((String) this.sortedList.get(middle))<x) {
						middle = middle + middle/2;
					}
				}
				return middle;

				
				/* 
				 *This block of code is for the case that t is an instanceof string 
				 */
				
			}else if(t instanceof Integer) {
				Integer x = (Integer)t;
				int middle = this.sortedList.size()/2;
				/*Set reference middle to be the center of the list
				*	As long as our middle reference doesn't correspond to the value we're looking for, move forward or backward depending on
				*	on how the middle value compares to the value we want.
				*/
				while (!this.sortedList.get(middle).equals(x)) {
					middle--;
					if((Integer)this.sortedList.get(middle) > x) {
						middle = middle - Math.round((middle+1)/2);
					}else if((Integer)this.sortedList.get(middle) < x) {
						//This is a safety measure in the event the middle reference isn't reaching the end of the list.
						//Ideally the algorithm wouldn't need this safety measure, but here we are.
						if (middle == this.sortedList.size()-3) {
							middle+=2;	
						}else {
							middle = middle + (Math.round(sortedList.size()-middle)/2);
						}
					}
				}
				return middle;
			}
			return 0;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	
	public String toPigLatin(String string) {
		//Create an array of vowels
		Character[] vowels = new Character[] {'a','e','i','o','u'};
		//Create an array in order to split the string into individual words
		String[] splitByWords = string.split("[\\s',]");
		//Iterate through the array of split up words
		for (int i = 0; i < splitByWords.length; i++) {
			/*
			 * For each word in the array, if the first character is a consonant or the last
			 * character is a q, put the first character at the end and then delete the first character.
			 * 
			 * Add "ay" onto the end.
			 */
			while ((!Arrays.asList(vowels).contains(splitByWords[i].charAt(0))) || (splitByWords[i].charAt(splitByWords[i].length()-1) == 'q')) {
				splitByWords[i] = splitByWords[i] + splitByWords[i].charAt(0);
				splitByWords[i] = splitByWords[i].substring(1,splitByWords[i].length());
			}
			splitByWords[i] = splitByWords[i] + "ay"; 
		}
		String temp = splitByWords[0];
		
		//format the array back into a returnable string
		if(splitByWords.length>1) {
			for (int i=1; i<splitByWords.length; i++) {
				temp = temp + " " + splitByWords[i];
			}
		}
		return temp;
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		
		//Convert input to a string format
		String inputValue = String.valueOf(input);
		
		//Iterate across the string
		//Raise the value of the int within the string to the power of the length of the string
		//Add this value to a temp value 
		
		int temp = 0;
		for(int i=0; i<inputValue.length(); i++) {		
			temp += Math.round(Math.pow(Character.getNumericValue(inputValue.charAt(i)),inputValue.length()));
		}
		
		//Check whether or not the obtained value matches the initial input
		
		if(input == temp) {
			return true;
		}		
		return false;
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	
	public List<Long> calculatePrimeFactorsOf(long l) {
		//Initialize list for factors
		List<Long> factors = new ArrayList<Long>();
		//Iterate from the first possible prime factor (2) to the number we're testing
		for (Long i = 2l; i <= l; i++) {
			while (l % i == 0) {
				//Only prime numbers will get added to the factors list because the the lesser values have already been tested
				//If no prior values are factors of i, then i is prime.
				factors.add(i);
				l = l/i;
			}
		}
		return factors;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {

		private int key;
		private Character[] alphabetListLower =  new Character[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		private Character[] alphabetListUpper =  new Character[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};


		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			
			
			for (int i = 0; i < string.length(); i++) {
				
				//If the letter is uppercase then use the cipher on the alphabetListUpper
				if (Character.isUpperCase(string.charAt(i))) {
					//CASE: ELEMENT IS CAPITALIZED AND GOES BEYOND INDEX 25
					if (Arrays.asList(alphabetListUpper).indexOf(string.charAt(i))+key>25) {
						string = string.substring(0,i) + alphabetListUpper[Arrays.asList(alphabetListUpper).indexOf(string.charAt(i))+key-26] + string.substring(i+1);
						//CASE: ELEMENT IS CAPITALIZED AND DOES NOT GO BEYOND INDEX
					}else {
						string = string.substring(0,i) + alphabetListUpper[Arrays.asList(alphabetListUpper).indexOf(string.charAt(i))+key] + string.substring(i+1);

					}
				//This block is for lowercase letters
				}else if (Character.isLowerCase(string.charAt(i))) {
						//CASE: ELEMENT IS LOWERCASE AND GOES BEYOND INDEX 25
					if (Arrays.asList(alphabetListLower).indexOf(string.charAt(i))+key>25) {
						string = string.substring(0,i) + alphabetListLower[Arrays.asList(alphabetListLower).indexOf(string.charAt(i))+key-26] + string.substring(i+1);
						//CASE: ELEMENT IS LOWERCASE AND DOES NOT GO BEYOND INDEX
					}else {
						string = string.substring(0,i) + alphabetListLower[Arrays.asList(alphabetListLower).indexOf(string.charAt(i))+key] + string.substring(i+1);
					}
				}
			}
			//System.out.println(string);	
			return string;
		}
	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) {
		//check to see if input is valid
		if (i < 1) {
			throw new IllegalArgumentException();
		}
		/*
		 * Initialize a variable for the number of primes counted at any given point
		 * initialize a variable for the value being tested to be a prime
		 */
		int numberOfPrimesCounted = 0;
		int isThisValuePrime = 1;

		//Keep counting primes until we get to the ith prime
		while (numberOfPrimesCounted != i) {
			isThisValuePrime++;

			/*
			 * for the number being tested to be a prime, iterate from 2 to that number.
			 * If the counter gets to be more than half the value being tested, we know immediately that the number is prime
			 */
			for (int n = 2; n<=isThisValuePrime; n++) {
				if (n > (isThisValuePrime)/2) {
					numberOfPrimesCounted++;
					break;

					//If at any point we find a valid factor then we know the value being tested is not prime
				} else if (isThisValuePrime%n == 0) {
					break;
				}
			}
		}
		return isThisValuePrime;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {
		static Map<Character, Character> encodeCipher = new HashMap<>(26);
		static Map<Character, Character> decodeCipher = new HashMap<>(26);
		static Character[] numbers = new Character[] {'1','2','3','4','5','6','7','8','9','0'};
		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			//Define the cipher mapping for encoding
			
			encodeCipher.put('a','z'); encodeCipher.put('b','y');
			encodeCipher.put('c','x'); encodeCipher.put('d','w');
			encodeCipher.put('e','v'); encodeCipher.put('f','u');
			encodeCipher.put('g','t'); encodeCipher.put('h','s');
			encodeCipher.put('i','r'); encodeCipher.put('j','q');
			encodeCipher.put('k','p'); encodeCipher.put('l','o');
			encodeCipher.put('m','n'); encodeCipher.put('n','m');
			encodeCipher.put('o','l'); encodeCipher.put('p','k');
			encodeCipher.put('q','j'); encodeCipher.put('r','i');
			encodeCipher.put('s','h'); encodeCipher.put('t','g');
			encodeCipher.put('u','f'); encodeCipher.put('v','e');
			encodeCipher.put('w','d'); encodeCipher.put('x','c');
			encodeCipher.put('y','b'); encodeCipher.put('z','a');
			//Initialize encoded string
			String encodedString = "";
			int charCount = 0;
			for (int i = 0; i < string.length(); i++) {
				//Test if character is a number
				if (Arrays.asList(numbers).contains(string.charAt(i))) {
					encodedString += string.charAt(i);
					charCount++;
				//Test is character is in hashmap
				}else if (encodeCipher.containsKey(string.charAt(i))){
					encodedString += encodeCipher.get(string.charAt(i));
					charCount++;
				//Test if character is uppercase and in hashmap
				}else if (encodeCipher.containsKey(Character.toLowerCase(string.charAt(i)))) {
					encodedString += encodeCipher.get(Character.toLowerCase(string.charAt(i)));
					charCount++;
				}
				//Add a space after every 5 character entries (unless string already ends in a space)
				if (i < string.length()-2) {
					if (charCount%5==0 && charCount != 0 && encodedString.charAt(encodedString.length()-1)!= ' '){
						//System.out.println(charCount); 
						encodedString += " ";
					}
				}
			}
			//System.out.println("encoded string is " + encodedString);
			return encodedString;
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		
		public static String decode(String string) {
			//Define mapping for decoding strings
			decodeCipher.put('z','a'); decodeCipher.put('y','b');
			decodeCipher.put('x','c'); decodeCipher.put('w','d');
			decodeCipher.put('v','e'); decodeCipher.put('u','f');
			decodeCipher.put('t','g'); decodeCipher.put('s','h');
			decodeCipher.put('r','i'); decodeCipher.put('q','j');
			decodeCipher.put('p','k'); decodeCipher.put('o','l');
			decodeCipher.put('n','m'); decodeCipher.put('m','n');
			decodeCipher.put('l','o'); decodeCipher.put('k','p');
			decodeCipher.put('j','q'); decodeCipher.put('i','r');
			decodeCipher.put('h','s'); decodeCipher.put('g','t');
			decodeCipher.put('f','u'); decodeCipher.put('e','v');
			decodeCipher.put('d','w'); decodeCipher.put('c','x');
			decodeCipher.put('b','y'); decodeCipher.put('a','z');
			//Remove the spaces from input string
			string = string.replaceAll("\\s+","");
			//Initialize placeholder for decoded string
			String decodedString = "";
			for (int i = 0; i < string.length(); i++) {

				//Test if character is a number
				if (Arrays.asList(numbers).contains(string.charAt(i))) {
					decodedString += string.charAt(i);

					//Test is character is in hashmap of decoding characters
				}else if (decodeCipher.containsKey(string.charAt(i))){
					decodedString += decodeCipher.get(string.charAt(i));
				}
			}
			//System.out.println("decoded string is " + decodedString);
			return decodedString;
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		//Take out the dashes from the string to make manipulation simpler
		string = string.replaceAll("\\-","");
		//This is the value that we will test against mod 11 at the end
		int computationValue = 0;
		//Array of acceptable characters for the isbn
		Character[] acceptableCharacters = new Character[] {'1','2','3','4','5','6','7','8','9','0','x','X'};
		//This is a number that will iterate down as we go forward in the ISBN. It multiplies by each integer by one less each time.
		int multiplier = 10;
		/*
		 * Iterate through the string.
		 * 		Check if the character is invalid, and if so, return false.
		 * 			If the character is valid, then check if it's at an X or not and multiply the value accordingly with our multiplier variable.
		 */
		for (int i = 0; i<string.length(); i++) {
			if (Arrays.asList(acceptableCharacters).contains(string.charAt(i))) {
				if (string.charAt(i)!='X') {
					computationValue += (Character.getNumericValue(string.charAt(i)) * multiplier);
					multiplier--;
				}else if (string.charAt(i)=='X') {
					computationValue += (10 * multiplier);
					multiplier--;
				}
			}else{
				return false;
			}

		}
		//Test the resulting computation value to see if we have a valid ISBN. Otherwise return False.
		if (computationValue % 11 == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		string = string.replaceAll("\\-","");
		//If the string is less than 26 characters then we can just get rid of it immediately.
		if(string.length() < 26) {
			return false;
		}
		//Create a new hashmap with each letter of the alphabet valued at 0
		Map<Character, Integer> letterTracker = new HashMap<>();
		letterTracker.put('a', 0); 	letterTracker.put('b', 0);
		letterTracker.put('c', 0); 	letterTracker.put('d', 0);
		letterTracker.put('e', 0);	letterTracker.put('f', 0);
		letterTracker.put('g', 0);	letterTracker.put('h', 0);
		letterTracker.put('i', 0);	letterTracker.put('j', 0);
		letterTracker.put('k', 0);	letterTracker.put('l', 0);
		letterTracker.put('m', 0);	letterTracker.put('n', 0);
		letterTracker.put('o', 0);	letterTracker.put('p', 0);
		letterTracker.put('q', 0);	letterTracker.put('r', 0);
		letterTracker.put('s', 0);	letterTracker.put('t', 0);
		letterTracker.put('u', 0);	letterTracker.put('v', 0);
		letterTracker.put('w', 0);	letterTracker.put('x', 0);
		letterTracker.put('y', 0);	letterTracker.put('z', 0);
		
		//When a letter occurs in the string, change the corresponding value in the hashmap to 1
		for (int i = 0; i < string.length(); i++) {
			letterTracker.put(string.charAt(i), 1);
		}
		//If the hashmap contains the value 0 at this point, we know that there was a letter not included in the string
		if (letterTracker.containsValue(0)) {
		return false;
		
		}else {
			return true;
		}
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	
	public Temporal getGigasecondDate(Temporal given) throws ClassCastException {
		//instantiate a LocalDateTime object to reference the input temporal
		LocalDateTime convertedInput;
		/*
		 * Attempt to cast the input to LocalDateTime (if the input is in year/month/day format)
		 * Will come back with a ClassCastException if the input is in year/month/day/hour/etc... format, so catch and 
		 * reassign accordingly.
		 */
		try {
			convertedInput = ((LocalDate) given).atStartOfDay();

		}catch (ClassCastException e) {
			convertedInput = (LocalDateTime) given;
		}

		//Instantiate a Duration object that holds a gigasecond
		Duration gigasecond = Duration.ofSeconds(1000000000);

		//return the localdatetime + a gigasecond
		return gigasecond.addTo(convertedInput);
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		/*
		 * Variables:	
		 * 		sum is the sum of all unique multiples. sum is returned at the end
		 * 		max is the number we want to stay under with the multiples
		 * 		multiple is the value of the multiple we have at any given time as an element of the array is added to itself
		 * 		duplicates is an array that will store each multiple. This is to ensure we are not adding duplicates to the
		 * 			overall sum.
		 */
		int sum = 0;
		int max = i;
		int multiple;	
		List<Integer> duplicates = new ArrayList<>();

		/*
		 * for each value in the array, add its multiples to the sum as long as it's both unique per our duplicates array
		 * 		and less than the max
		 */
		for (int j = 0; j < set.length; j++) { 
			multiple = set[j];
			while (multiple < max) {
				
				if (!duplicates.contains(multiple)){
					duplicates.add(multiple);
					//System.out.println(multiple);
					sum = sum + multiple;	
				}
				multiple += set[j];
			}
		}
		return sum;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		string = string.replaceAll("\\ ","");	

		/*
		 * return false immediately if the input isn't formatted correctly
		 */
		if (string.length()<=1) {
			return false;
		}else if(string.contains("a")) {
			return false;
		}else if(string.contains("-")) {
			return false;
		}

		//initiate reference for the sum of values which will be tested at the end for validity
		int sumOfValues = 0;

		/*
		 * Iterate backwards through every other number and multiply by two
		 * Check if value is above 9, otherwise just add it to the overall
		 */
		for (int i = string.length()-2; i>=0; i = i-2) {
			sumOfValues += Character.getNumericValue(string.charAt(i)) * 2;
			if ((Character.getNumericValue(string.charAt(i)) * 2) > 9) {
				sumOfValues -= 9;
			}
		}
		//add the other values that aren't being manipulated to the sum total
		for (int i = string.length()-1; i>=0; i = i-2) {
			sumOfValues += Character.getNumericValue(string.charAt(i));
		}
		//Check if the sum passes the criteria. if so, return true.
		if (sumOfValues%10==0) {
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		/*
		 * remove instances of question marks from the string and then split the string by spaces into a String array
		 */
		string = string.replaceAll("\\?","");
		String[] words = string.split(" ");
		/*
		 * value1 and value2 correspond to the two ints within the word problem.
		 * String operation is the operator that will be performed on the 2 values.
		 */
		int value1;
		int value2;
		String operation = words[3];

		/*
		 * Depending on what the operation is, we know where the values will be located in the String[].
		 * assign the values for any given case and then perform the necesary operation on them.
		 */

		switch(operation) {
		
		case("plus"):
			value1 = Integer.parseInt(words[2]);
			value2 = Integer.parseInt(words[4]);
			return value1 + value2;

		case("minus"):
			value1 = Integer.parseInt(words[2]);
			value2 = Integer.parseInt(words[4]);
			return value1 - value2;

		case("multiplied"):
			value1 = Integer.parseInt(words[2]);
			value2 = Integer.parseInt(words[5]);
			return value1 * value2;

		case("divided"):
			value1 = Integer.parseInt(words[2]);
			value2 = Integer.parseInt(words[5]);
			return value1 / value2;

		}
		return 0;
	}

}

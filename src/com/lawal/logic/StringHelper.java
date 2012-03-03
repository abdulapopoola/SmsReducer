package com.lawal.logic;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import android.text.TextUtils;
public class StringHelper {

	// public static Replaceable OUReplaceable = new Replaceable("o", new String[] { "ou" });
	public static Replaceable UReplaceable = new Replaceable("u", new String[] { "uu", "oo", "ou", "uo" });
	public static Replaceable AReplaceable = new Replaceable("a", new String[] { "er", "aa" });
	public static Replaceable GHTReplaceable = new Replaceable("t", new String[] { "ght" });
	
	//FIX ME. bad idea, when vowel before ing
	public static Replaceable INGReplaceable = new Replaceable("N", new String[] { "ing" });
	public static Replaceable IReplaceable = new Replaceable("i", new String[] { "ie", "ei", "ee", "ii" });
	public static final String BRACKETS = "({[<)}]>";
	public static final String CLOSING_BRACKETS = ")}]>";
	public static final String OPENING_BRACKETS = "({[<";
	public static final char[] PUNCTUATION_CHAR = new char[] { '!', '?', ';', '.', ':', '-' };
	public static final String SENTENCE_PUNCTUATION = "!?,;.:-";

	/**
	 * Capitalize first char.
	 * 
	 * @param str
	 *            the str
	 * @return the string
	 */
	public static String capitalizeFirstChar(String str) {

		if (!isEmpty(str)) {
			char c = str.charAt(0);
			if (!Character.isWhitespace(c)) {
				return Character.toUpperCase(c) + str.substring(1);
			}
		}
		return str;
	}

	/**
	 * Concatenate by delimiter.
	 */
	public static String concatenateByDelimeter(String[] strs, Character delim) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < strs.length; i++) {
			if (null == delim) {
				buf.append(strs[i]);
			} else
				buf.append(strs[i]).append(delim);
		}
		return buf.toString();

	}

	/**
	 * @return true, if successful
	 */
	public static boolean contains(String str, char searchChar) {
		if (isEmpty(str)) {
			return false;
		}
		return str.indexOf(searchChar) >= 0;
	}

	/**
	 * Removes the vowel from strings if length > 3, excluding first and last char
	 * 
	 * @param word
	 *            , the word to search
	 */
	public static String disemVowel(String word) {

		if (word == null || word.length() < 3) {
			return word;

		}

		// exclude first and last char
		char firstLetter = word.charAt(0);
		char lastLetter = word.charAt(word.length() - 1);
		String sub = word.substring(1, word.length() - 1);
		return firstLetter + sub.replaceAll("[aeiou]\\B", "") + lastLetter;

	}

	/**
	 * Checks for double char.
	 * 
	 * @param s
	 *            , the string to search
	 * @return true, if successful
	 */
	public static boolean hasDoubleChar(String s) {

		return indexOfDoubleLetters(s.toCharArray()) != -1;
	}

	/**
	 * Returns the first index of a double letter in word returns -1 if none found
	 * 
	 * @param word
	 *            the word
	 * @return the int
	 */
	public static int indexOfDoubleLetters(char[] word) {

		if (word == null)
			return -1;

		if (word.length < 2)
			return -1;

		if (word[0] == word[1])
			return 0;

		for (int i = 1; i < word.length; i++) {
			if (i != word.length - 1) {
				if (word[i] == word[i + 1]) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * Index of punctuation.
	 * 
	 * @param s
	 *            the s
	 * @return the int
	 */
	public static int indexOfPunctuation(String s) {
		for (char c : PUNCTUATION_CHAR) {
			if (contains(s, c)) {
				return s.indexOf(c);
			}
		}

		return -1;
	}

	/**
	 * Checks if is empty.
	 * 
	 * @param str
	 *            the str
	 * @return true, if is empty
	 */
	public static boolean isEmpty(String str) {

		return str == null || str.length() <= 0; // a string with zero length is also empty

		//return TextUtils.isEmpty(str);

	}

	/**
	 * Checks if is empty.
	 * 
	 * @param strings
	 *            the strings
	 * @return true, if is empty
	 */
	public static boolean isEmpty(String... strings) {
		for (String s : strings) {
			if (isEmpty(s))
				return true;
		}
		return false;
	}

	// public static boolean hasReplaceAbleDoubleVowels(String word) {
	// boolean contains = false;
	// for (String str : iReplaceable) {
	// if( word.contains(str)){
	// contains =true;
	// }
	// }
	//
	// for (String str : uReplaceable) {
	// if( word.contains(str)){
	// contains =true;
	// }
	// }
	// return contains;
	//
	// }

	/**
	 * Removes the punctions.
	 * 
	 * @param s
	 *            the s
	 * @return the string
	 */
	// TODO make recursive
	public static String removePunctions(String s) {

		int idx = indexOfPunctuation(s);
		if (idx != -1) {

			return skipCharAtIndex(s, idx);
		}

		return s;

	}

	/**
	 * Replace double chars.
	 * 
	 * @param word
	 *            the word
	 * @return the string
	 */
	public static String replaceDoubleChars(String word) {

		if (isEmpty(word) || word.length() < 2)
			return word;

		char[] words = word.toCharArray();
		StringBuffer buf = new StringBuffer();
		if (words[0] == words[1]) {
			buf.append(words[0]);
		} else {
			buf.append(words[0]);
			buf.append(words[1]);

		}
		buf.append(replaceDoubleChars(word.substring(2, word.length())));

		String evenChecked = buf.toString();
		if (hasDoubleChar(evenChecked)) {
			char[] charArray = evenChecked.toCharArray();
			System.out.println("StringHelper.replaceDoubleChars() ");
			String rest = new String(evenChecked.substring(1, evenChecked.length()));
			return charArray[0] + replaceDoubleChars(rest);
		}
		return evenChecked;

	}

	/**
	 * Replace double vowels.
	 * 
	 * @param word
	 *            the word
	 * @param replaceables
	 *            the replaceables
	 * @return the string
	 */
	public static String replaceDoubleVowels(String word, Replaceable... replaceables) {

		String outword = word;
		for (Replaceable replaceable : replaceables) {

			for (String str : replaceable.getReplaceAble()) {
				if (word.contains(str)) {
					outword = word.replaceAll(str, replaceable.getToReplace());
				}
			}

		}

		return outword;

	}

	/**
	 * @return the same string without the letter at index
	 */
	public static String skipCharAtIndex(String word, int index) {
		if (isEmpty(word))
			return word;

		char[] words = word.toCharArray();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < word.length(); i++) {
			if (i != index) {
				buf.append(words[i]);
			}
		}

		return buf.toString();

	}

	/**
	 * Checks if is vowel.
	 * 
	 * @param ch
	 *            the ch
	 * @return true, if is vowel
	 */
	private static boolean isVowel(char ch) {
		char c = Character.toLowerCase(ch);
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}

	public static Map<String, String> CURRENCY_TO_SYMBOL = getSymbolToCurrencyMap();
	
   private static Map<String, String> getSymbolToCurrencyMap (){
		
		Map<String, String> currToSymbolMap = new HashMap<String, String>();
		currToSymbolMap.put("pound", "£");
		currToSymbolMap.put("dollar", "$");
		currToSymbolMap.put("euro", "$");
		
		return Collections.unmodifiableMap(currToSymbolMap);
		
	}

	
	//FIXME UNTESTED

	public static String replaceWithCurrencySymbol(String word) {
		if (isEmpty(word))
			return word;
		String str;
		if (word.endsWith("S") || word.endsWith("s"))
			str = word.substring(0, word.length() - 1);
		else
			str = word;
		str=str.toLowerCase();
		if (CURRENCY_TO_SYMBOL.containsValue(str)) {
			System.out.println("StringHelper.replaceWithCurrencySymbol() " + str);
			return CURRENCY_TO_SYMBOL.get(str);
		}
		return word;
	}
	//FIXME UNTESTED
	public static String replaceAllIn(String rawString, Map<String,String> stringToReplacementMap) {
		rawString = rawString.toLowerCase();
		for (String key : stringToReplacementMap.keySet()) {
			 rawString.replaceAll(key, stringToReplacementMap.get(key));
		}
		return rawString;
	}

}

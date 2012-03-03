package com.lawal.logic;


public class BasicLogics {

	public static final Transformer<String[], String[]> CapitalizeFirstLetters = new Transformer<String[], String[]>() {

		@Override
		public String[] apply(String[] inWord) {

			String[] out = new String[inWord.length];

			for (int i = 0; i < inWord.length; i++) {

				out[i] = StringHelper.capitalizeFirstChar(inWord[i]);
			}
			return out;

		}
	};

	public static final Transformer<String[], String[]> Disemvowel = new Transformer<String[], String[]>() {
		
		@Override
		public String[] apply(String[] inWord) {
			
			String[] out = new String[inWord.length];

			for (int i = 0; i < inWord.length; i++) {
				String word = inWord[i];
				out[i] = StringHelper.disemVowel(word);
				
			}

			return out;
		}
	};

	public static final Transformer<String[], String[]> CurrencySubstitute = new Transformer<String[], String[]>() {
		
		@Override
		public String[] apply(String[] word) {
			
			String[] out = new String[word.length];

			for (int i = 0; i < word.length; i++) {
				out[i] = StringHelper.replaceWithCurrencySymbol(word[i]);
			}

			return out;
		}
	};

	public static Transformer<String[], String[]> IngLogic = new Transformer<String[], String[]>() {

		@Override
		public String[] apply(String[] word) {

			String[] out = new String[word.length];

			for (int i = 0; i < word.length; i++) {
				out[i] = StringHelper.replaceDoubleVowels(word[i], StringHelper.INGReplaceable);
			}

			return out;

		}
	};

	public static Transformer<String[], String[]> DoubleVowelLogic = new Transformer<String[], String[]>() {

		@Override
		public String[] apply(String[] word) {

			String[] out = new String[word.length];

			for (int i = 0; i < word.length; i++) {
				
				out[i] = StringHelper.replaceDoubleVowels(word[i], StringHelper.UReplaceable, StringHelper.IReplaceable, StringHelper.AReplaceable);

			}

			return out;

		}
	};

	public static Transformer<String[], String[]> CommonCharGroups = new Transformer<String[], String[]>() {

		@Override
		public String[] apply(String[] word) {

			String[] out = new String[word.length];

			for (int i = 0; i < word.length; i++) {
				out[i] = StringHelper.replaceDoubleVowels(word[i], StringHelper.GHTReplaceable);

			}

			return out;

		}
	};
	
	public static Transformer<String[], String[]> RemovePunctuactions = new Transformer<String[], String[]>() {

		@Override
		public String[] apply(String[] word) {

			String[] out = new String[word.length];

			for (int i = 0; i < word.length; i++) {
				out[i] = StringHelper.removePunctions(word[i]);

			}

			return out;

		}
	};
	
	

	public static Transformer<String[], String[]> DoubleCharLogic = new Transformer<String[], String[]>() {

		@Override
		public String[] apply(String[] word) {

			String[] out = new String[word.length];

			for (int i = 0; i < word.length; i++) {
				out[i] = StringHelper.replaceDoubleChars(word[i]);
			}

			return out;

		}
	};

}

package com.edward.mcn.util;

/**
 * A class used for converting a number into a custom type that can display the number's value in a specified format.
 * 
 * @author Edward
 *
 */
public class Digit {

	// The base units for numbers that are less than 20, and those numbers in the hundreds and thousands position
	public static String[] BASE_UNITS = { "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve",
			"thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
	// The units for numbers in the tens position
	public static String[] TENS_UNITS = { "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };
	// Roman numerals
	public static String[] ROMAN_ONES = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
	public static String[] ROMAND_TENS = { "", "X", "XX", "XXX", "XL", "L", " LX", "LXX", "LXXX", "XC" };
	public static String[] ROMAN_HUNDREDS = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
	public static String[] ROMAN_THOUSANDS = { "", "M", "MM", "MMM" };
	// The numeric value of the number that will be represented
	private int number;
	// Integers used to hold the value of each number in the specified position
	private int one, ten, hundred, thousand;
	// The English words for the number in the specified position
	private String englishOne = "", englishTen = "", englishHundred = "", englishThousand = "";
	// The Roman numeral for the number in the specified position
	private String romanOne = "", romanTen = "", romanHundred = "", romanThousand = "";
	// The full English word for the Digit
	private String englishWord;
	// The full Roman numeral for the Digit
	private String romanNumeral;
	// If the number is less than 0 or greater than 3999, it is out of bounds
	private boolean outOfBounds = false;

	public Digit() {
	}

	/**
	 * Converts a number to a Digit.
	 * 
	 * @param number
	 *            The number to be converted.
	 */
	public Digit(int number) {
		this.number = number;
		if (number < 1 || number > 3999) {
			outOfBounds = true;
			return;
		} else {
			outOfBounds = false;
		}
		findDigitPositions(number);
		convertToEnglish();
		convertToRomanNumeral();
	}

	/**
	 * Sorts the individual digits for the number.
	 * 
	 * @param number
	 *            The number to be converted.
	 */
	private void findDigitPositions(int number) {
		int length = String.valueOf(number).length(); // The length of the number - 1234 would be length of 4
		// The position of the number farthest to the left. That is the first number that needs to be sorted.
		// Found be taking the power of 10 of the length of the number minus 1
		// 1234 has length 4, so 10^3 = 1000, find the number in the thousand position
		int position = (int) Math.pow(10, length - 1);
		for (int x = 0; x < length; x++) {
			int digitToAdd = number / position; // 1234 / 1000 = 1 - 1 is in the thousand position
			addNumber(digitToAdd, position);
			number %= position; // 1234 % 1000 = 234
			position /= 10; // 1000 / 10 = 100 - that is the new position
		}
	}

	/**
	 * Sorts a number and sets the English and Roman numeral values.
	 * 
	 * @param number
	 *            The single digit that relates to the position.
	 * @param position
	 *            The position that the number is in (ex. hundreds, tens)
	 */
	public void addNumber(int number, int position) {
		switch (position) {
		case 1: // Ones
			this.one = number;
			this.englishOne = BASE_UNITS[number];
			this.romanOne = ROMAN_ONES[number];
			break;
		case 10: // Tens
			this.ten = number;
			this.englishTen = TENS_UNITS[number];
			this.romanTen = ROMAND_TENS[number];
			break;
		case 100: // Hundreds
			this.hundred = number;
			this.englishHundred = BASE_UNITS[number];
			this.romanHundred = ROMAN_HUNDREDS[number];
			break;
		case 1000: // Thousands
			this.thousand = number;
			this.englishThousand = BASE_UNITS[number];
			this.romanThousand = ROMAN_THOUSANDS[number];
		}
	}

	/**
	 * Converts the number to English and formats the word properly.
	 */
	private void convertToEnglish() {
		String word = "";
		if (ten == 1) {
			word = BASE_UNITS[(one + 10)];
		} else if (ten == 0) {
			word = englishOne;
		} else {
			word = one == 0 ? englishTen : englishTen + "-" + englishOne;
		}
		if (hundred != 0) {
			word = englishHundred + " hundred " + word;
		}
		if (thousand != 0) {
			word = word == "" ? englishThousand + " thousand" : englishThousand + " thousand, " + word;
		}
		this.englishWord = word.trim();
	}

	/**
	 * Converts the number to Roman numeral.
	 */
	private void convertToRomanNumeral() {
		this.romanNumeral = (this.romanThousand + this.romanHundred + this.romanTen + this.romanOne).trim();
	}

	/**
	 * Returns the Roman numeral for this Digit.
	 * 
	 * @return The Roman numeral.
	 */
	public String toRomanNumeral() {
		return this.romanNumeral;
	}

	/**
	 * Returns the English word for this Digit.
	 * 
	 * @return The English word.
	 */
	@Override
	public String toString() {
		return this.englishWord.toUpperCase();
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the outOfBounds
	 */
	public boolean isOutOfBounds() {
		return outOfBounds;
	}

	/**
	 * @param outOfBounds
	 *            the outOfBounds to set
	 */
	public void setOutOfBounds(boolean outOfBounds) {
		this.outOfBounds = outOfBounds;
	}

}

package com.edward.mcn.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.edward.mcn.util.Digit;

public class BaseTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSomeNumbers() {
		String firstNumber = Digit.BASE_UNITS[1];
		String eleventhNumber = Digit.BASE_UNITS[11];
		assertThat(firstNumber, is("one"));
		assertThat(eleventhNumber, is("eleven"));

		String twenty = Digit.TENS_UNITS[2];
		String ninety = Digit.TENS_UNITS[9];
		assertThat(twenty, is("twenty"));
		assertThat(ninety, is("ninety"));
	}

	@Test
	public void testEnglishNumbersLessThan100() {
		for (int x = 1; x < 1232; x++) {
			Digit digit = new Digit(x);
			System.out.println(digit.toString());
			String word = digit.toString();
			if (x == 12) {
				assertThat(word, is("TWELVE"));
			}
			if (x == 33) {
				assertThat(word, is("THIRTY-THREE"));
			}
			if (x == 58) {
				assertThat(word, is("FIFTY-EIGHT"));
			}
			if (x == 84) {
				assertThat(word, is("EIGHTY-FOUR"));
			}
		}
	}

	@Test
	public void testEnglistNumberInThousands() {
		Digit thousandNum1 = new Digit(1234);
		Digit thousandNum2 = new Digit(1538);
		Digit thousandNum3 = new Digit(2371);
		Digit thousandNum4 = new Digit(3642);
		assertThat(thousandNum1.toString(), is("ONE THOUSAND, TWO HUNDRED THIRTY-FOUR"));
		assertThat(thousandNum2.toString(), is("ONE THOUSAND, FIVE HUNDRED THIRTY-EIGHT"));
		assertThat(thousandNum3.toString(), is("TWO THOUSAND, THREE HUNDRED SEVENTY-ONE"));
		assertThat(thousandNum4.toString(), is("THREE THOUSAND, SIX HUNDRED FORTY-TWO"));
	}

	@Test
	public void testSomeRomanNumeralsLessThan100() {
		for (int x = 0; x < 100; x++) {
			Digit digit = new Digit(x);
			System.out.println(digit.toRomanNumeral());
			String romanNumeral = digit.toRomanNumeral();
			if (x == 12) {
				assertThat(romanNumeral, is("XII"));
			}
			if (x == 33) {
				assertThat(romanNumeral, is("XXXIII"));
			}
			if (x == 58) {
				assertThat(romanNumeral, is("LVIII"));
			}
			if (x == 84) {
				assertThat(romanNumeral, is("LXXXIV"));
			}
		}
	}

	@Test
	public void testRomanNumeralsInThousands() {
		Digit thousandNum1 = new Digit(1234);
		Digit thousandNum2 = new Digit(1538);
		Digit thousandNum3 = new Digit(2371);
		Digit thousandNum4 = new Digit(3642);
		assertThat(thousandNum1.toRomanNumeral(), is("MCCXXXIV"));
		assertThat(thousandNum2.toRomanNumeral(), is("MDXXXVIII"));
		assertThat(thousandNum3.toRomanNumeral(), is("MMCCCLXXI"));
		assertThat(thousandNum4.toRomanNumeral(), is("MMMDCXLII"));
	}

	@Test
	public void testForOutOfBounds() {
		Digit digit = new Digit(0);
		Digit digit2 = new Digit(4000);
		Digit digit3 = new Digit(-50);
		Digit digit4 = new Digit(234569);
		assertThat(digit.isOutOfBounds(), is(true));
		assertThat(digit2.isOutOfBounds(), is(true));
		assertThat(digit3.isOutOfBounds(), is(true));
		assertThat(digit4.isOutOfBounds(), is(true));
		assertThat(digit.isOutOfBounds(), is(true));
		assertThat(digit2.isOutOfBounds(), is(true));
		assertThat(digit3.isOutOfBounds(), is(true));
		assertThat(digit4.isOutOfBounds(), is(true));
	}
}

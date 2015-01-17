/**
 * 
 */
package com.edward.mcn;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.component.html.HtmlOutputText;

import com.edward.mcn.util.Digit;

/**
 * The Managed Bean for the Number Printer web application. Used to take an input from the user and convert it to a Digit that can be used to display
 * the desired format.
 * 
 * @author Edward
 *
 */
@ManagedBean
public class NumberPrinterWeb {

	public String input; // Takes the input from the user
	public String display; // Displays the outcome to the user
	// The span used to display text
	private HtmlOutputText txtDisplay;

	@PostConstruct
	public void init() {
	}

	/**
	 * Attempts to convert a number to it's equivalent English word
	 * 
	 * @return Empty String
	 */
	public String toString() {
		Digit digit = convertNumber(input);
		if (digit != null)
			display = digit.toString();
		return "";
	}

	/**
	 * Attempts to convert a number to it's equivalent Roman Numeral
	 * 
	 * @return Empty String
	 */
	public String toRomanNumeral() {
		Digit digit = convertNumber(input);
		if (digit != null)
			display = digit.toRomanNumeral();
		return "";
	}

	/**
	 * Converts a number to a Digit
	 * 
	 * @param input
	 *            The number that needs to be converted.
	 * @return Null if there was an error during conversion, or the Digit if it was successfully converted.
	 */
	private Digit convertNumber(String input) {
		Digit digit = null;
		try {
			digit = new Digit(Integer.valueOf(input));
		} catch (NumberFormatException e) {
			txtDisplay.setStyle("color: firebrick");
			display = "HEY! YOU NEED TO ENTER A WHOLE NUMBER...";
			return null;
		}
		if (digit != null && digit.isOutOfBounds()) {
			txtDisplay.setStyle("color: firebrick");
			display = "THAT NUMBER IS OUT OF BOUNDS!";
			return null;
		}
		txtDisplay.setStyle("color: black");
		return digit;
	}

	/**
	 * @return the input
	 */
	public String getInput() {
		return input;
	}

	/**
	 * @param input
	 *            the input to set
	 */
	public void setInput(String input) {
		this.input = input;
	}

	/**
	 * @return the display
	 */
	public String getDisplay() {
		return display;
	}

	/**
	 * @param display
	 *            the display to set
	 */
	public void setDisplay(String display) {
		this.display = display;
	}

	/**
	 * @return the txtDisplay
	 */
	public HtmlOutputText getTxtDisplay() {
		return txtDisplay;
	}

	/**
	 * @param txtDisplay
	 *            the txtDisplay to set
	 */
	public void setTxtDisplay(HtmlOutputText txtDisplay) {
		this.txtDisplay = txtDisplay;
	}

}

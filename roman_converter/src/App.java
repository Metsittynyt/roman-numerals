import java.util.List;
import java.util.Scanner;

public class App {

	public static int decodeOne(char letter) {
		switch (letter) {
		case 'M':
			return 1000;
		case 'D':
			return 500;
		case 'C':
			return 100;
		case 'L':
			return 50;
		case 'X':
			return 10;
		case 'V':
			return 5;
		case 'I':
			return 1;
		default:
			return 0;
		}
	}

	public static boolean validate(String roman) {

		// list of valid letters
		List<String> validLetters = List.of("M", "D", "C", "L", "X", "V", "I");
		List<String> validOrder = List.of("IV", "IX", "XL", "XC", "CD", "CM");

		// check that string has valid letters
		for (int i = 0; i < roman.length(); i++) {

			if (validLetters.contains(String.valueOf(roman.charAt(i))) == false) {
				System.out.print("Invalid input: string contains invalid characters." + "\n");
				return false;
			}
		}
		
		// check that string has valid order
		for (int i = 0; i < roman.length() - 1; i++) {
			if (decodeOne(roman.charAt(i)) < decodeOne(roman.charAt(i + 1))) {
				String s = String.valueOf(roman.charAt(i)) + String.valueOf(roman.charAt(i + 1));
				System.out.print(s + "\n");
				if (validOrder.contains(s) == false) {
					System.out.print("Invalid input: wrong order" + "\n");
					return false;
				}
			}
		}
		
		// check for too many same characters
		if (roman.contains("IIII") || roman.contains("XXXX") || roman.contains("CCCC") || roman.contains("VV")
				|| roman.contains("LL") || roman.contains("DD")) {
			System.out.print("Invalid input: too many same characters in a row." + "\n");
			return false;
		}

		return true;
	}

	public static int convert(String roman) {
		// initialize result and change given string to uppercase
		int result = 0;

		// loop through string and get result by comparing current and next letter
		for (int i = 0; i < roman.length() - 1; i++) {
			if (decodeOne(roman.charAt(i)) < decodeOne(roman.charAt(i + 1))) {
				// subtract
				result -= decodeOne(roman.charAt(i));
			} else {
				// add
				result += decodeOne(roman.charAt(i));
			}

		}
		// add last numeral
		result += decodeOne(roman.charAt(roman.length() - 1));
		return result;
	}

	public static void main(String args[]) {
		// TODO: convert roman numerals to decimal

		Scanner sc = new Scanner(System.in);
		System.out.print("Do you want to convert roman numeral to decimal? (y / n): ");

		while (true) {

			String answer = sc.nextLine();

			if (answer.equals("y")) {
				// ask numeral
				System.out.print("\n" + "Enter roman numeral: ");
				// save given string
				String input = sc.nextLine();
				// input to uppercase
				String roman = input.toUpperCase();

				if (validate(roman) == true) {
					// print converted number
					System.out.print("Decimal of " + roman + " is " + convert(roman) + "\n");
					System.out.print("\n" + "Do you want to convert another one? (y / n): ");
				} else {
					System.out.print("\n" + "Try again? (y / n): ");
				}

			} else if (answer.equals("n")) {
				System.out.print("Ending the program...");
				break;

			} else {
				System.out.print("Please answer 'y' for yes or 'n' for no: ");
			}
		}
	}
}
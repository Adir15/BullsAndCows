import java.util.Random;
import javax.swing.JOptionPane;

public class LogicGame {

	private final int NUM_DIGIT_IN_RANDOM_NUMBER = 9000;
	private final int NUM_OF_DIGITS_IN_GUESS_NUM = 4;
	int guessCount = 0; // A global variable because the number of guesses is not reset, and the number
						// of hits and correct answers is updated according to the user's
						// answer
	int secret_num; // number that the user need to guess

	Random random = new Random();

	public LogicGame() {
		secret_num = validNum(random.nextInt(NUM_DIGIT_IN_RANDOM_NUMBER) + 1000); // create random number between 1000
																					// to 9999
	}

	private int validNum(int num) { // check that have no the same digit in the number that user need to guess
		int temp = num;
		int num1[] = new int[NUM_OF_DIGITS_IN_GUESS_NUM];
		int num2[] = new int[NUM_OF_DIGITS_IN_GUESS_NUM];
		int i = 0;
		while (temp != 0) { // Creating two separate arrays that break down the number the user chooses and
							// the number the computer chooses into single digits.
			num1[i] = temp % 10;
			num2[i] = temp % 10;
			temp = temp / 10;
			i++;
		}
		for (int j = 0; j < NUM_OF_DIGITS_IN_GUESS_NUM; j++) {
			for (int z = j + 1; z < NUM_OF_DIGITS_IN_GUESS_NUM; z++) {
				if (num1[j] == num2[z]) { // If there is a number that repeats itself more than once, a new number will be generated
					return validNum(random.nextInt(NUM_DIGIT_IN_RANDOM_NUMBER) + 1000);
				}
				if (z == 3 && j == 3 - 1) {
					if (num1[j] != num2[z]) {
						return num;
					}
				}
			}
		}
		return num;
	}

	public boolean isValid(int num) { // check if the number is valid or not. if not get error pop up
		if (num < 1000) {
			JOptionPane.showMessageDialog(null,
					"The number is too small, you need to enter a number with 4 digits from 1000 to 9999", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (num > 9999) {
			JOptionPane.showMessageDialog(null,
					"The number is too big, you need to enter a number with 4 digits from 1000 to 9999", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public boolean isNumeric(String str) { // Returns true if the entered input is a number, and false if the input is
											// not a number
		if (str == null) {
			return false;
		}
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Enter a number! not Enter non numeric input", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

	}

	public String cowBullCount(int num) { //
		int userNumber = num;
		int originalNumber = secret_num;
		int cowCount = 0;
		int bullCount = 0;

		int orginalNum[] = new int[NUM_OF_DIGITS_IN_GUESS_NUM];
		int userNum[] = new int[NUM_OF_DIGITS_IN_GUESS_NUM]; // check that no number is repeated twice by comparing one
																// array with the other
		int i = 0;

		while (userNumber != 0) { // Creating two separate arrays that break down the number the user chooses and
									// the number the computer chooses into single digits.
			orginalNum[i] = originalNumber % 10;
			userNum[i] = userNumber % 10;
			userNumber = userNumber / 10;
			originalNumber = originalNumber / 10;
			i++;
		}

		for (int j = 0; j < NUM_OF_DIGITS_IN_GUESS_NUM; j++) { // If there are two numbers in the same column in the
																// array and they are both equal, this is a stamp and we
																// will increase the counter by 1
			if (orginalNum[j] == userNum[j]) {
				bullCount++;
			}
			for (int z = j; z < NUM_OF_DIGITS_IN_GUESS_NUM; z++) { // If there are two equal numbers that are in
																	// different arrays, but are not in the same
																	// position in the array, this is a hit and we will
																	// lock the appropriate counter respectively
				if (z != j && orginalNum[j] == userNum[z]) {
					cowCount++;
				}
			}
		}
		guessCount++;
		return "Your Number is :\t " + num + "\n" + "The number of Cows is: " + cowCount + "\n"
				+ "The number of Bulls is: " + bullCount + "\n" + "The number of guess is : " + guessCount;
	}

	public boolean theGameIsOver(int num) { // If we correctly guessed all the digits in the number, it will return
											// true, otherwise it will return false
		if (secret_num == num) {
			return true;
		} else {
			return false;
		}
	}

}




















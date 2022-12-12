import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.oracle.xmlns.internal.webservices.jaxws_databinding.ExistingAnnotationsType;

import javafx.scene.paint.Stop;

public class Main {

	public static void main(String[] args) {
		String numberString;
		int num;
		ArrayList<String> historyArrayList = new ArrayList<String>();

		LogicGame logicGame = new LogicGame();
		System.out.println(logicGame.secret_num); // The drawn number appears on the console to enable comparison and verify correctness

		JOptionPane.showMessageDialog(null, "In front of you is the Bulls and Cows game.\r\n" // Initial message, introducing the Game instructions for the user                                                                            
				+ "You need to choose a 4-digit number (that does not start with 0!).\r\n"
				+ "In this game you need to guess a random number that the computer chooses.\r\n"
				+ "If the number you chose has a digit that is exactly in the same place as the number you guess, it is a Bull.\r\n"
				+ "If the number you chose has a digit, which is in the number, but is not in exactly the same place, it is a Cow.\r\n"
				+ "If you find the number in full, you win and the game is over.");
		numberString = JOptionPane.showInputDialog("Enter number");
		while (numberString != null) {
			while (!logicGame.isNumeric(numberString)) { // If the input is non-numeric, we will ask the user to enter a number again
				numberString = JOptionPane.showInputDialog("Enter number");
				if (numberString == null) {        //If you click on the CANCLE option in the dialog box the program exit
					System.exit(0);
				}
			}
			num = Integer.parseInt(numberString); // Change from String number to integer number
			while (!logicGame.isValid(num)) { // the number is not valid , ask to set new valid number
				numberString = JOptionPane.showInputDialog("Enter number");
				if (numberString == null) {
					System.exit(0);
				}
				num = Integer.parseInt(numberString);
			}
			historyArrayList.add(numberString);
			while (!logicGame.theGameIsOver(num)) { // The numbers does not the same
				JOptionPane.showMessageDialog(null, logicGame.cowBullCount(num) + "\n"
						+ "History of number you chosess: " + "\n" + historyArrayList + "\n");  // message to user with
																								// count of
																								// cows,bulls,guess,number
			        																			// history
				numberString = JOptionPane.showInputDialog("Enter number");
				while (!logicGame.isNumeric(numberString)) {
					numberString = JOptionPane.showInputDialog("Enter number");
					if (numberString == null) {
						System.exit(0);
					}
				}
				num = Integer.parseInt(numberString);
				while (!logicGame.isValid(num)) {
					numberString = JOptionPane.showInputDialog("Enter number");
					if (numberString == null) {
						System.exit(0);
					}
					num = Integer.parseInt(numberString);
				}
				historyArrayList.add(numberString);
			}

			if (logicGame.theGameIsOver(num)) {
				JOptionPane.showMessageDialog(null,
						"Well done, the number you chose matches exactly the number of the computer ", "succeeded!",
						JOptionPane.PLAIN_MESSAGE);
				int continueQustion = JOptionPane.showConfirmDialog(null, "Do you want to play again? ");
				if (continueQustion == JOptionPane.YES_OPTION) {
					main(args);
				}
				if (continueQustion == JOptionPane.NO_OPTION) {
					System.exit(continueQustion);
				}
			}
		}
		System.exit(0); // If chosen CANCEL option - exit from the program
	}

}

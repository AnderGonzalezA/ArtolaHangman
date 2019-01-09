import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import com.zubiri.hangman.*;
public class ArtolaHangman {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to the famous HANGMAN game.");
		System.out.println("You will have to guess which surname of the people in class is hidden between the voids.");
		System.out.println(
				"For this, you will be able to enter 3 letters that may appear in the surname, or not. After this, you will only have a chance to guess the surname and win the game.");
		String[] surnames = {"lazkano","intxausti","artola","alberdi","lekubide","ortiz","gonzalez"};
		boolean playAgain = true;
		while (playAgain == true) {
			String randomSurname = surnames[new Random().nextInt(surnames.length)];
			Word surname = new Word(randomSurname);
			for (int i=0;i<surname.getWord().length();i++) {
				System.out.print("_ ");
			}
			int tries=0;
			while (tries < 3) {
				String entered = sc.nextLine().toLowerCase();
				if (surname.letterMatch(entered)) {
					System.out.println("You were right!");
					ArrayList<Integer> positions = surname.matchPositions(entered);
					for (int i=0; i<positions.size();i++) {
						System.out.println("It is in the position " + positions.get(i));
					}
				}else {
					System.out.println("Ups");
				}
				tries++;
				if (tries == 1) {
					System.out.println("You have two letters left.");
				} else if (tries == 2) {
					System.out.println("You have just a letter left.");
				}
			}
			System.out.println("It's the time, you have to guess the word.");
			boolean wordEntered = false;
			while (wordEntered == false) {
				String[] playerWordArray = sc.nextLine().split(" ");
				String playerWord = playerWordArray[0];
				boolean isWord=true;
				for (int i=0;i<playerWord.length();i++) {
					if (!Character.isLetter(playerWord.charAt(i))) {
						isWord=false;
						break;
					}
				}
				if (isWord) {
					wordEntered=true;
					if (surname.getWord().equals(playerWord)) {
						System.out.println("Congrats, you won the game.");
					} else {
						System.out.println("I'm sorry, you lost the game.");
					}
				}else {
					System.out.println("Please, enter a word.");
				}
			}
			System.out.println("Would you like to play again?(y/n)");
			boolean askAgain = true;
			while (askAgain == true) {
				String back = sc.next().toLowerCase();
				sc.nextLine();
				switch (back) {
				case "y":
					askAgain = false;
					break;
				case "n":
					playAgain = false;
					askAgain = false;
					break;
				default:
					System.out.println("Please, select a possible value(y/n)");
					break;
				}
			}
		}
		sc.close();
	}
}

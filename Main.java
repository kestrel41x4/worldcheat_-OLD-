import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception{
		
		//Creates file object to be used later in the reader.
		File classFile = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		String path = classFile.getAbsolutePath().toString();
		path = path.substring(0, path.length()-3) + "\\wordleAnswers.txt";
		File file = new File(path);
		System.out.println("Drawing from:\r\n" + path + "\r\n");
		
		//Creates a list of words to be transmitted back and forth between the Dictionary class.
		ArrayList<String> wordList = new ArrayList<String>();
		
		Boolean wordFound = false;
		
		Dictionary dict = new Dictionary(null, "", "", "", null, null);
		
		ArrayList<String> guessList = new ArrayList<String>();

        Probability prob = new Probability();

        Map<String, Double> hashBrown = new HashMap<String, Double>();

        ArrayList<String> probableWordList;
	
		//Main while loop.
		while(!wordFound) 
		{
				Scanner guessCheck = new Scanner(System.in);
				
				System.out.println("Type in the word that you guessed.");
				guessList.add(guessCheck.nextLine().toLowerCase());
				
				//Scanner to see what letters ARE NOT included in the word.
				System.out.println("\r\nAccepting grey letters... (ex: aeiou)");
				String nullLetters = guessCheck.nextLine().toLowerCase();
				if (!nullLetters.equals(""))
				{
					System.out.println(" ");
				}
				
				//Scanner to see if there are any yellow letters and what positions they are in.
				System.out.println("Accepting yellow letters in the format that README.txt specifies... (if the word you entered was 'raise' and the yellows were r, s, and e, you would type it as r__se.)");
				String yellowLetters = guessCheck.nextLine().toLowerCase();
				if (!yellowLetters.equals(""))
				{
					System.out.println(" ");
				}
				
				//Scanner to see if there are any green letters and what positions they are in.
				System.out.println("Accepting green letters in the format that README.txt specifies... (if the word you entered was 'table' and the greens were t, e, and l, you would type it as t__le.)");
				String greenLetters = guessCheck.nextLine().toLowerCase();
				if (!greenLetters.equals(""))
				{
					System.out.println(" ");
				}
				
				ArrayList<String> runningNull = dict.totNullLetters;
				String[] runningGreen = dict.totGreenLetters;
				ArrayList<String[]> runningYellow = dict.totYellowLetters;
				Boolean greenFound = dict.greenFound;
				String[] d1 = dict.d1;
				String[] d2 = dict.d2;

				dict = new Dictionary(file, nullLetters, yellowLetters, greenLetters, wordList, greenFound);
				dict.confirmLetters(runningNull, runningGreen, runningYellow, d1, d2);
				wordList = dict.makeWordList(guessList);

                
                hashBrown = prob.makeList(wordList);
                //System.out.println(hashBrown);

                probableWordList = new ArrayList<String>(hashBrown.keySet());
				
				System.out.println("-----------\r\n");
				
				//for(int i = 0; i < wordList.size() && i < 5; i++)
				//{
					//System.out.println(wordList.get(i));
				//}
				
				try {
				System.out.println(probableWordList.get(probableWordList.size()-1));
				System.out.println(probableWordList.get(probableWordList.size()-2));
				System.out.println(probableWordList.get(probableWordList.size()-3));
				System.out.println(probableWordList.get(probableWordList.size()-4));
				System.out.println(probableWordList.get(probableWordList.size()-5));
				}
				catch (Exception e)
				{
					System.out.println("\r\nNo more words were found.");
					break;
				}

				System.out.print("\r\n");
				
				Scanner foundCheck = new Scanner(System.in);
				System.out.println("Was the word you typed in correct? (y/n)");
				String foundResponse = foundCheck.nextLine().toLowerCase();
				if (foundResponse.equals("y"))
				{
					wordFound = true;
					break;
				}

				System.out.println("\r\nPick a word and type it into 'Wordle.'\r\n");
				
				/*Checks to see if user has found word.
				Scanner foundCheck = new Scanner(System.in);
				System.out.println("Was the word you typed in correct? (y/n)");
				String foundResponse = foundCheck.nextLine().toLowerCase();
				if (foundResponse.equals("y"))
				{
					wordFound = true;
				}*/
			}
	}
}

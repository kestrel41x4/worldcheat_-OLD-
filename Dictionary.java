import java.io.*;
import java.util.*;

public class Dictionary{
	
	//Wordle answers file to be used in the BufferedReader later on.
	private File answers;

	//ArrayList of strings to hold all letters that have been found to not be a part of the word.
	public ArrayList<String> totNullLetters = new ArrayList<String>();
	
	public String nullLetters;

	//Array to hold all green letters that have been found in order.
	public String[] totGreenLetters = new String[]{"", "", "", "", ""};
	
	public String greenLetters;

	//ArrayList of arrays of strings to hold all instances of letters that are in the word but in the wrong place.
	public ArrayList<String[]> totYellowLetters = new ArrayList<String[]>();
	
	public String yellowLetters;
	
	public String allYellows = "";

	//ArrayList of strings that holds all the words that the target word could be.
	public ArrayList<String> wordList;

	//Boolean to let the program know whether or not a green letter has been found.
	public Boolean greenFound = false;
	
	//Double letter counters;
	public String[] d1 = new String[]{"f", ""};
	public String[] d2 = new String[]{"f", ""};

	//Constructor for Dictionary class.
	public Dictionary(File f, String nl, String yl, String gl, ArrayList<String> wl, Boolean gf)
	{
		this.answers = f;
		this.nullLetters = nl;
		this.yellowLetters = yl;
		this.greenLetters = gl;
		this.wordList = wl;
		if (gf!=null) 
		{
			this.greenFound = gf;
		}
  	}
	
	//Method to be used by the Main class after receiving input from the user regarding the different types of letters.
	public void confirmLetters(ArrayList<String> nl, String[] gl, ArrayList<String[]> yl, String[] d, String[] D)
	{	
		totYellowLetters = yl;
		allYellows = allYellows + yellowLetters;
		String[] currYellowLetters = yellowLetters.split("");
		totYellowLetters.add(currYellowLetters);

		totGreenLetters = gl;
		String[] currGreenLetters = greenLetters.split("");			
		for (int i = 0; i < currGreenLetters.length; i++)
		{
			if (!currGreenLetters[i].equals("_") & !currGreenLetters[i].equals(null) & !currGreenLetters[i].equals("")) 
			{
				greenFound = true;
				totGreenLetters[i] = currGreenLetters[i];
			}
		}
		
		d1 = d;
		d2 = D;
		
		for (String yLetter : currYellowLetters)
		{
			for (String gLetter : currGreenLetters)
			{
				if (yLetter.equals(gLetter) & !yLetter.equals("_") & !yLetter.equals(""))
				{
					if (d1[0].equals("f"))
					{
						d1[0] = "t";
						d1[1] = yLetter;
					}
					else
					{
						d2[0] = "t";
						d2[1] = yLetter;
					}
				}
			}
		}
		
		totNullLetters = nl;
		String[] currNullLetters = nullLetters.split("");
		for (int i = 0; i < currNullLetters.length; i++)
		{	
			Boolean goodNull = true;
			String nullLetter = currNullLetters[i];
			if (allYellows.contains(nullLetter))
			{
				goodNull = false;
			}
			if (goodNull) {
				for (int k = 0; k < totGreenLetters.length; k++)
				{
					if (nullLetter.equals(totGreenLetters[k]))
					{
						String[] newYellow = new String[]{nullLetter, nullLetter, nullLetter, nullLetter, nullLetter};
						newYellow[k] = "_";
						totYellowLetters.add(newYellow);
						goodNull = false;
					}
				}
			}
			if (goodNull) 
			{
			totNullLetters.add(currNullLetters[i]);
			}
		}
	}

	//Method to be used after confirming all information with the user. Goes through the previously established answers File variable and removes words that do not fit the criteria.
	public ArrayList<String> makeWordList(ArrayList<String> gl)
	{
		ArrayList<String> tempWordList = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(answers));
			String line = reader.readLine();
			while(line!=null)
			{
				Boolean goodWord = true;
				
				for (String guess : gl)
				{
					if (line.equals(guess))
					{
						goodWord = false;
					}
				}
				
				String[] lineLetters = line.split("");
				
				if (d1[0].equals("t")) 
				{
					int counter = 0;
					for (String letter : lineLetters)
					{
						if (letter.equals(d1[1]))
						{
							counter++;
						}
					}
					if (counter < 2)
					{
						goodWord = false;
					}
				}
				
				if (d2[0].equals("t")) 
				{
					int counter = 0;
					for (String letter : lineLetters)
					{
						if (letter.equals(d2[1]))
						{
							counter++;
						}
					}
					if (counter < 2)
					{
						goodWord = false;
					}
				}
				
				//Null letters.
				for (int i = 0; i < totNullLetters.size(); i++) 
				{
					if (!totNullLetters.get(i).equals("") & line.contains(totNullLetters.get(i))) 
					{
						goodWord = false;
					}
				}
				
				//Yellow letters.
				if (goodWord)
				{
					for (int i = 0; i < totYellowLetters.size(); i++)
					{
						String[] yellowTest = totYellowLetters.get(i);
						for (int k = 0; k < yellowTest.length; k++)
						{
							if (!yellowTest[k].equals("_"))
							{
								if (line.contains(yellowTest[k]) & !yellowTest[k].equals(lineLetters[k]))
								{
									
								} else
								{
									goodWord = false;
								}
							}
						}
					}
				}
				
				//Green letters.
				if (greenFound & goodWord)
				{
					for (int i = 0; i < lineLetters.length; i++)
					{
						if (!totGreenLetters[i].equals("") & !lineLetters[i].equals(totGreenLetters[i]))
						{
							goodWord = false;
						}
					}
				}
				
				//Adds words that pass all tests to the wordList.
				if (goodWord) 
				{
					tempWordList.add(line);
				}
				line = reader.readLine();
			}
			reader.close();
			wordList = tempWordList;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
			return wordList;
		}
}

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Testing {
  static char[] lettersT = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
      'S', 'T',
      'U', 'V', 'W', 'X', 'Y', 'Z' };

  // The total probability of the word

  static double addedProbability = 0;
  // Probability of each letter occuring in the entire list
  static double[] probabilityT = { 0.085, 0.024, 0.041, 0.033, 0.116, 0.019, 0.025, 0.033, 0.057, 0.002, 0.018, 0.062,
      0.027,
      0.049, 0.065, 0.032, 0.003, 0.077, 0.057, 0.062, 0.040, 0.013, 0.017, 0.003, 0.037, 0.003 };

  public static void main(String args[]) throws FileNotFoundException {
    String s = "I am writing this letter to inform you about my revisions that I would like to make on the Onramples Retheoric of American Identity College couse in canvas The revision would fall into four many categories Kairos making a more clear argument linking reasons why to trust the paper in the synthesis and being more accurate for the paperThe Kairos of the essay wasnt urgent enough nor did it pressaud the audience to believe that the issue needed to be addressed quickly In the essay issues spread out throughout the 20th century A way to fix this would be to use diction to show how theSecondly the main argument of the paper was not explicitly stated The paper the main argument was that transgender atheletes do not statistically pose a threat to women in causal and completive sports fields However though this arugment is generaly stated throughout the paper it is not explicitly stated Therefore I will solve this by explicitly stating the argument at the end of the introduction in the thesisNext the synthesis did not have any rethoritcal appeals to perssuad the audience or reasons to trust the author To fix these issues I would add appeals such as an analogy comparing the injustice against trans atheltes to racial segeration or a ";
    // Scanner s = new Scanner(new File("wordleAnswers.txt"));
    // ArrayList<String> list = new ArrayList<String>();
    // while (s.hasNext()) {
    // list.add(s.next());
    // }
    // s.close();

    // String stuff;
    // char temp;
    // int count = 0;
    // ArrayList<Character> singleList = new ArrayList<Character>();
    // for (int i = 0; i < list.size(); i++) {
    // stuff = list.get(i);
    // singleList.add(stuff.charAt(3));
    // }

    // for (int i = 0; i < 26; i++) {
    // count = 0;
    // for (int j = 0; j < singleList.size(); j++) {
    // temp = Character.toUpperCase(singleList.get(j));
    // if (lettersT[i] == temp) {
    // count++;
    // }

    // }
    // System.out.println(lettersT[i] + " : " + count + ",");
    // }

    Probability p = new Probability();
    p.getProbability(s.toUpperCase());

  }

  // public static double testing(char input) {
  // // char[] stringArray = input.toCharArray();
  // // for (char i : lettersT) {
  // // if (input == lettersT[i]) {
  // // addedProbability += probabilityT[i];
  // // }
  // // }

  // // System.out.println(addedProbability);
  // // return addedProbability;
  // }
}
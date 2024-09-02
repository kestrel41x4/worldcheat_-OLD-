import java.util.ArrayList;
import java.util.stream.DoubleStream;
import java.util.*;
import java.lang.Math;

public class Probability {

  // List of all letters used for comparasion
  char[] letters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
      'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

  // The total probability of the word
  double addedProbability;

  // Probability of each letter occuring in the entire list
  double[] probability = { 0.085, 0.024, 0.041, 0.033, 0.116, 0.019, 0.025, 0.033, 0.057, 0.002, 0.018, 0.062,
      0.027,
      0.049, 0.065, 0.032, 0.003, 0.077, 0.057, 0.062, 0.040, 0.013, 0.017, 0.003, 0.037, 0.003, 0.034 };

  ArrayList<Double> holdingList = new ArrayList<Double>();

  public double roundedAddedProbability;

  private int positionCount;

  private PositionProbability PP = new PositionProbability();

  /**
   * Probability Class finds the total probabiliy of a letter occuring in a word
   */
  public Probability() {

  }

  /**
   * Finds the rounded value of decimals
   * 
   * @param num
   * @param decimalpoint
   * @return
   */
  private static double roundDecimal(double num, int decimalpoint) {
    // Use the math power value to cut off decimal place
    num = num * Math.pow(10, decimalpoint);
    num = Math.floor(num);
    num = num / Math.pow(10, decimalpoint);
    return num;
  }

  public Map<String, Double> makeList(ArrayList<String> wordList) {

    HashMap<String, Double> totalArr = new HashMap<>();
    String word;
    double wordProbability;
    for (int i = 0; i < wordList.size(); i++) {
      word = wordList.get(i).toUpperCase();
      wordProbability = getProbability(wordList.get(i).toUpperCase());
      totalArr.put(word, wordProbability);
    }

    Map<String, Double> sortedMap = hashMapSorter(totalArr);

    return sortedMap;
  }

  public HashMap<String, Double> hashMapSorter(HashMap<String, Double> hm) {
    List<Map.Entry<String, Double>> list = new LinkedList<Map.Entry<String, Double>>(hm.entrySet());

    // Sort the list
    Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
      public int compare(Map.Entry<String, Double> o1,
          Map.Entry<String, Double> o2) {
        return (o1.getValue()).compareTo(o2.getValue());
      }
    });

    // put data from sorted list to hashmap
    HashMap<String, Double> temp = new LinkedHashMap<String, Double>();
    for (Map.Entry<String, Double> aa : list) {
      temp.put(aa.getKey(), aa.getValue());
    }
    return temp;
  }

  /**
   * Would find the probabiliy of the word entered
   * 
   * @param input
   */
  public double getProbability(String input) {

    addedProbability = 0;

    char[] charArray = input.toCharArray();

    positionCount = 0;

    while (positionCount < 5) {
      switch (positionCount) {
        case 0:
          //System.out.println("position 0 reached");
          for (int j = 0; j < letters.length; j++) {
            if (charArray[0] == letters[j]) {
              addedProbability += PP.positionZeroProbability[j];
              positionCount += 1;
            }
          }
          break;

        case 1:
          //System.out.println("position 1 reached");
          for (int j = 0; j < letters.length; j++) {
            if (charArray[1] == letters[j]) {
              addedProbability += PP.positionOneProbability[j];
              positionCount += 1;
            }
          }
          break;

        case 2:
          //System.out.println("position 2 reached");
          for (int j = 0; j < letters.length; j++) {
            if (charArray[2] == letters[j]) {
              addedProbability += PP.positionTwoProbability[j];
              positionCount += 1;
            }
          }
          break;

        case 3:
          //System.out.println("position 3 reached");
          for (int j = 0; j < letters.length; j++) {
            if (charArray[3] == letters[j]) {
              addedProbability += PP.positionThreeProbability[j];
              positionCount += 1;
            }
          }
          break;

        case 4:
          //System.out.println("position 4 reached");
          for (int j = 0; j < letters.length; j++) {
            if (charArray[4] == letters[j]) {
              addedProbability += PP.positionFourProbability[j];
              positionCount += 1;
            }
          }
          break;
      }
    }

    // for (int i = 0; i < 5; i++) {
    // for (int j = 0; j < letters.length; j++) {
    // if (charArray[i] == letters[j]) {
    // addedProbability += probability[j];
    // }
    // }

    // }

   /* for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (charArray[i] == charArray[j]) {
          addedProbability -= 0.025;
        }

      }
    }*/

    roundedAddedProbability = roundDecimal(addedProbability, 4);
    return roundedAddedProbability;
  }

}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * The ScrabbleScorer class is used to calculate the amount of points a string would count
 * for in the game of SCRABBLE. This class verifies that a word exists in the scrabble
 * dictionary and then prints the score of the word (if it is valid)
 *
 * @author 24wilber
 * @version 1/24/2022
 */
@SuppressWarnings("FieldMayBeFinal")
public class ScrabbleScorer {
    private ArrayList<String> Dict;
    private static int[] points = {0, 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};

    /**
     * Creates the ScrabbleScorer object and calls `buildDictionary()`
     */
    public ScrabbleScorer() {
        Dict = new ArrayList<>();
        buildDictionary();
    }


    /**
     * takes the dictionary file named SCRABBLE_WORDS.txt and creates a list of all the words in the file.
     * It then sorts the file
     */
    public void buildDictionary() {
        try {
            //find the file to make things easier
            //file can be in any of these places
            File scrabbleWords =new File("./SCRABBLE_WORDS.txt");

            FileReader reader = new FileReader(scrabbleWords);
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                Dict.add(word.toUpperCase());//uppercase just in case
            }
            Collections.sort(Dict);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * does a binary search for the word in the dictionary to see if it is a valid scrabble word.
     * @param word the word to be tested
     * @return a boolean; true if valid, false if invalid
     */
    public boolean isValidWord(String word) {
        return Collections.binarySearch(Dict, word.toUpperCase())>=0;
    }

    /**
     * this function takes each character in the string word and calulates the character's place in the alphabet.
     * (a -> 1; b -> 2; etc.) It then gets the points value in the array points at the number. blah blah
     * @implNote *basically* this turns characters into numbers and asks points how many points for this.
     * for each character in the string `word`
     * @implSpec its pretty fast (^◡^)
     * @param word the word want to know the points for
     * @return the total scrabble ~imaginary~ points for said word
     */
    public int getWordScore(String word) {
        byte DecapitalizeAndShift = 0x1F; // in binary: `0001 1111`
        // capital letters look like this: 010x xxxx
        // and lowercase 011xx xxxx
        // so if i did an AND bitwise operator on any character with 0x1F
        //     0001 1111 (31)
        // AND 0100 0011 (C | 67)
        // -------------
        // --> 0000 0011 (3)
        // this can then be used for an index for points

        int sum = 0;
        // parse word one letter at a time

        byte currentCharacter;
        for (int i = 0; i < word.length(); i++) { // i use this so i dont allocate a char[] of the contents of word
            sum += points[DecapitalizeAndShift & word.charAt(i)];
        }
        // find index of each letter
        // add the corresponding to a sum score
        return sum;
    }
//    public int getWordScoreSlow(String word) {
//        ArrayList<Character> alphabet = new ArrayList<>(List.of('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'));
//        ArrayList<Integer> slowPoints = new ArrayList<>(List.of(1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10));
//        int sum = 0;
//        for (Character i : word.toCharArray()) {
//            sum += slowPoints.get(alphabet.indexOf(Character.toUpperCase(i)));
//        }
//
//        // find index of each letter
//        // add the corresponding to a sum score
//        return sum;
//    }

    /**
     * This initiates the program for the user to start scoring their scrabble words.
     * @param args literally nothing, you dont have to put anything in here but you can!
     */
    public static void main(String[] args) {
        ScrabbleScorer app = new ScrabbleScorer();
        System.out.println("* Welcome to the Scrabble Word Scorer app *");
        String userWord;
        Scanner userIn = new Scanner(System.in);
        try {
            while (true) {
                System.out.print("Enter a word to score or 0 to quit: ");
                userWord = userIn.nextLine();
                if (userWord.equals("0"))
                    break;
                else if (app.isValidWord(userWord))
                    System.out.println(userWord+" = "+app.getWordScore(userWord));
                else
                    System.out.println(userWord+" is not a valid word in the dictionary");
            }
            System.out.println("Exiting the program thanks for playing");
            userIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
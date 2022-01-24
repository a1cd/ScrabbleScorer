import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.List;

public class ScrabbleScorer {
    public ArrayList<String> Dict;
    public ArrayList<ArrayList<String>> SuperDict = new ArrayList<>(27);
    /**
     * The scores for all of the characters
     * element 0 is 0 because it makes it more efficient
     */
    private static int[] points = {0, 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};

    public ScrabbleScorer() {
        this.Dict = new ArrayList<String>();
        this.SuperDict = new ArrayList<ArrayList<String>>();
    }

    @Override
    public void buildDictionary() {
        try {
            FileReader reader = new FileReader(new File("src/main/resources/SCRABBLE_WORDS.txt"));
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                Dict.add(word);
            }
            Arrays.sort(points);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void buildBetterDictionary() {
        byte DecapitalizeAndShift = 0x1F;
        for (int i = 0; i < 27; i++) {
            SuperDict.add(new ArrayList<String>());
        }
        try {
            FileReader reader = new FileReader(new File("src/main/resources/SCRABBLE_WORDS.txt"));
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                SuperDict.get(word.charAt(0) & DecapitalizeAndShift).add(word);
            }
            for (int i = 0; i < SuperDict.size(); i++) {
                Collections.sort(SuperDict.get(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isValidWord(String word) {
        return Collections.binarySearch(Dict, word)>=0;
    }
    public boolean isValidWordFast(String word) {
        byte DecapitalizeAndShift = 0x1F;
        return Collections.binarySearch(SuperDict.get(word.charAt(0)&DecapitalizeAndShift), word)>=0;
    }
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
//        for (char i: word.toCharArray()) { // i use this so i dont allocate a char[] of the contents of word
//            sum += points[DecapitalizeAndShift & i];
//        }
        // find index of each letter
        // add the corresponding to a sum score
        return sum;
    }
    public int getWordScoreSlow(String word) {
        ArrayList<Character> alphabet = new ArrayList<>(List.of('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'));
        ArrayList<Integer> slowPoints = new ArrayList<>(List.of(1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10));
        int sum = 0;
        for (Character i : word.toCharArray()) {
            sum += slowPoints.get(alphabet.indexOf(Character.toUpperCase(i)));
        }

        // find index of each letter
        // add the corresponding to a sum score
        return sum;
    }

    public static void main(String[] args) {
        ScrabbleScorer app = new ScrabbleScorer();
        System.out.println("* Welcome to the Scrabble Word Scorer app *");
        String userWord;
        Scanner userIn = new Scanner(System.in);
        try {
            while (true) {
                System.out.print("Enter a word to score");
                userWord = userIn.nextLine();
                if (app.isValidWord(userWord)) System.out.println(app.getWordScore(userWord));
                else {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
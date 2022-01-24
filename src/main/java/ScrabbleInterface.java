import java.util.ArrayList;

public interface ScrabbleInterface {
    ArrayList<String> dictionary = null;

    abstract void buildDictionary();
    abstract boolean isValidWord(String word);
    abstract int getWordScore(String word);
}

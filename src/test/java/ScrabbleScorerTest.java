import org.junit.jupiter.api.Test;

import java.nio.MappedByteBuffer;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class ScrabbleScorerTest {
    @Test
    void buildDictionary() {
    }

    @Test
    void isValidWord() {
        ScrabbleScorer app = new ScrabbleScorer();
        app.buildDictionary();
        app.buildBetterDictionary();
        int trials = 10000;
        Random random = new Random();
        ArrayList<String> testWords = new ArrayList<>();
        for (int i = 0; i < trials; i++) {
            testWords.add(app.Dict.get(random.nextInt(app.Dict.size())));
        }
        String[] quickTestWords = new String[testWords.size()];
        testWords.toArray(quickTestWords);

        //test 1
        long before1 = System.nanoTime();
        for (int i = 0; i < trials; i++) {
            app.isValidWordFast(quickTestWords[i]);
        }
        long after1 = System.nanoTime();

        //test 2
        long before2 = System.nanoTime();
        for (int i = 0; i < trials; i++) {
            app.isValidWord(quickTestWords[i]);
        }
        long after2 = System.nanoTime();

        double fast = ((double) (before1-after1))/trials;
        double slow = ((double) (before2-after2))/trials;
        System.out.println(fast);
        System.out.println(slow);
        System.out.println("" + (fast/slow)*100 + "% (" + (slow/fast) + " times faster)");
    }

    @Test
    void getWordScore() {
        ScrabbleScorer app = new ScrabbleScorer();
        int trials = 1000000;
        long before1 = System.nanoTime();
        for (int i = 0; i < trials; i++) {
            app.getWordScore("HELLozie");
        }
        long after1 = System.nanoTime();
        long before2 = System.nanoTime();
        for (int i = 0; i < trials; i++) {
            app.getWordScoreSlow("HELLozie");
        }
        long after2 = System.nanoTime();
        double fast = ((double) (before1-after1))/trials;
        double slow = ((double) (before2-after2))/trials;
        System.out.println(fast);
        System.out.println(slow);
        System.out.println("" + (fast/slow)*100 + "% (" + (slow/fast) + " times faster)");
    }

    @Test
    void main() {
    }
}
package model.generators;

import java.util.ArrayList;
import java.util.Random;

/**
 * Класс-генератор слова
 */
public class WordGenerator {

    /**
     * Сгенерировать слово
     * @param alphabet алфавит
     * @param wordLength длина слова
     * @return слово
     */
    public String generateWord(ArrayList<String> alphabet, int wordLength) {
        StringBuilder strb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < wordLength; ++i) {
            int randIndex = rand.nextInt(alphabet.size());
            strb.append(alphabet.get(randIndex));
            alphabet.remove(randIndex);
        }
        return strb.toString();
    }
}

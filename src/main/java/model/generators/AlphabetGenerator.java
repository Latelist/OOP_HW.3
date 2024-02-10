package model.generators;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Класс-генератор алфавита.
 */
public class AlphabetGenerator {
    public ArrayList<String> symbols;
    public Alphabets alphabets;

    public AlphabetGenerator(Alphabets alphabets) {
        this.alphabets = alphabets;
    }

    public ArrayList<String> generateAlphabet() {
        switch (alphabets) {
            case NUM -> symbols = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0"));
            case RUS -> symbols = new ArrayList<>(Arrays.asList("а", "б", "в", "г", "д", "е", "ё", "ж", "з", "и",
                    "й", "к", "л", "м", "н", "о", "п", "р", "с", "т", "у", "ф", "х", "ц", "ч", "ш", "щ", "ъ", "ы", "ь",
                    "э", "ю", "я"));
            default -> symbols = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                    "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"));
        }
        return symbols;
    }
}

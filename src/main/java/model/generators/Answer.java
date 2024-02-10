package model.generators;

import model.GameStatus;

/**
 * Класс-генератор ответа.
 */
public class Answer {
    /**
     * Слово пользователя
     */
    public String userValue;

    /**
     * Слово компьютера
     */
    public String computerWord;

    /**
     * Массив угаданных букв: на 0 — быки (угадан символ и его положение), на 1 — коровы (угадан символ, но положение
     * неверное.
     */
    public int[] bullsAndCows = {0,0};

    /**
     * Конструктор класса ответа
     * @param userValue слово пользователя
     * @param computerWord слово компьютера
     */
    public Answer(String userValue, String computerWord) {
        this.userValue = userValue;
        this.computerWord = computerWord;
    }

    /**
     * Сгенерировать ответ
     */
    public void generateAnswer() {
        for (int i = 0; i < computerWord.length(); ++i) {
            if (userValue.charAt(i) == computerWord.charAt(i)) {
                ++bullsAndCows[0];
                ++bullsAndCows[1];
            } else if (computerWord.contains(String.valueOf(userValue.charAt(i)))) {
                ++bullsAndCows[1];
            }
        }

    }
}

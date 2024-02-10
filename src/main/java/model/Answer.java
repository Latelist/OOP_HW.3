package model;

import model.GameStatus;

public class Answer {

    public String userValue;
    public String computerWord;
    public int[] bullsAndCows = {0,0};
    public Answer(String userValue, String computerWord) {
        this.userValue = userValue;
        this.computerWord = computerWord;
    }

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

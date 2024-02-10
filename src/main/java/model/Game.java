package model;

import Logger.Logger;
import model.generators.AlphabetGenerator;
import model.generators.Alphabets;
import model.generators.WordGenerator;
import view.UserView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    public GameStatus gameStatus;
    public Alphabets alphabets;
    public int tryCount;
    public int wordLength;
    public String computerWord;
    public String userValue;
    public AlphabetGenerator alphabetGenerator;
    public WordGenerator wordGenerator;
    public ArrayList<String> alphabet;
    public UserView userView;
    public Scanner sc;
    public int bulls;
    public int cows;
    public boolean restart = true;
    public Logger logger;
    public String infoToLog;

    public Game() throws IOException {
        gameStatus = GameStatus.INIT;
        alphabets = Alphabets.ENG;
        userView = new UserView();
        sc = new Scanner(System.in);
        wordGenerator = new WordGenerator();
        logger = new Logger();
    }
    public void start() throws IOException {
        if (gameStatus == GameStatus.INIT) {
            gameStatus = GameStatus.START;
            userView.startText();
            logger.newEntry("Игра началась. ");
        } else {
            userView.gameAlreadyStartedText();
        }
    }

    public void settings() throws IOException {
        userView.alphabetSettingText();
        String alphType = sc.nextLine();
        switch (alphType) {
            case "1" -> alphabets = Alphabets.RUS;
            case "2" -> alphabets = Alphabets.ENG;
            case "3" -> alphabets = Alphabets.NUM;
        }
        alphabetGenerator = new AlphabetGenerator(alphabets);
        alphabet = alphabetGenerator.generateAlphabet();
        System.out.println(alphabet);
        userView.wordLengthSettingText();
        wordLength = sc.nextInt();
        userView.tryCountSettingText();
        tryCount = sc.nextInt();
        sc.nextLine();
        computerWord = wordGenerator.generateWord(alphabet, wordLength);
        userView.completeSettingsText();
        infoToLog = "Пользовательские настройки: алфавит — " + alphabets + ", длина слова — " + wordLength + ", " +
                "количество попыток — " + tryCount + "\nКомпьютер загадал слово: " + computerWord;
        logger.newEntry(infoToLog);
    }

    public void cycle() throws IOException {
        while (tryCount > 0) {
            userView.inputValueText();
            userValue = sc.nextLine();
            Answer computerAnswer = new Answer(userValue, computerWord);
            computerAnswer.generateAnswer();
            bulls = computerAnswer.bullsAndCows[0];
            cows = computerAnswer.bullsAndCows[1];
            --tryCount;
            if (bulls == computerWord.length()) {
                gameStatus = GameStatus.WIN;
            } else if (tryCount == 0) {
                gameStatus = GameStatus.LOSE;
            }
            userView.computerAnswerText(bulls, cows);
            infoToLog = "Предположение игрока: " + userValue + "\nКоров: " + cows + "\nБыков: " + bulls + "\n" +
                    "Осталось попыток: " + tryCount ;
            logger.newEntry(infoToLog);
            if (gameStatus == GameStatus.WIN) {
                userView.winText();
                infoToLog = "Игрок победил.";
                logger.newEntry(infoToLog);
                break;
            } else if (gameStatus == GameStatus.LOSE) {
                userView.loseText();
                infoToLog = "Игрок проиграл.";
                logger.newEntry(infoToLog);
            }
        }
    }

    public void end() throws IOException {
        userView.endText();
        int playAgain = sc.nextInt();
        sc.nextLine();
        switch (playAgain) {
            case 1 -> {
                restart = true;
                gameStatus = GameStatus.INIT;
                infoToLog = "Игрок решил поиграть еще.";
                logger.newEntry(infoToLog);
            }
            default -> {
                restart = false;
                sc.close();
                userView.goodByeText();
                infoToLog = "Игрок закончил игру.";
                logger.newEntry(infoToLog);
            }
        }
    }

}

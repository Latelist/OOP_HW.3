package model;

import Logger.Logger;
import model.generators.AlphabetGenerator;
import model.generators.Alphabets;
import model.generators.Answer;
import model.generators.WordGenerator;
import view.UserView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс, представляющий игровой процесс.
 */
public class Game {

    /** Текущее состояние игры. */
    public GameStatus gameStatus;
    /** Тип алфавита для игры. */
    public Alphabets alphabets;
    /** Количество попыток. */
    public int tryCount;
    /** Длина загадываемого слова. */
    public int wordLength;
    /** Загаданное компьютером слово. */
    public String computerWord;
    /** Слово, введенное пользователем. */
    public String userValue;
    /** Генератор алфавита. */
    public AlphabetGenerator alphabetGenerator;
    /** Генератор слов. */
    public WordGenerator wordGenerator;
    /** Список символов алфавита. */
    public ArrayList<String> alphabet;
    /** Представление для пользователя. */
    public UserView userView;
    /** Объект для чтения ввода пользователя. */
    public Scanner sc;
    /** Количество быков в ответе компьютера. */
    public int bulls;
    /** Количество коров в ответе компьютера. */
    public int cows;
    /** Флаг перезапуска игры. */
    public boolean restart = true;
    /** Логгер для записи событий игры. */
    public Logger logger;
    /** Информация для записи в лог. */
    public String infoToLog;

    /**
     * Конструктор класса Game.
     *
     * @throws IOException если возникают проблемы с логгером
     */
    public Game() throws IOException {
        gameStatus = GameStatus.INIT;
        alphabets = Alphabets.ENG;
        userView = new UserView();
        sc = new Scanner(System.in);
        wordGenerator = new WordGenerator();
        logger = new Logger();
    }

    /**
     * Запустить игру.
     *
     * @throws IOException если возникают проблемы с логгером
     */
    public void start() throws IOException {
        if (gameStatus == GameStatus.INIT) {
            gameStatus = GameStatus.START;
            userView.startText();
            logger.newEntry("Игра началась. ");
        } else {
            userView.gameAlreadyStartedText();
        }
    }

    /**
     * Настроить параметры игры.
     *
     * @throws IOException если возникают проблемы с логгером
     */
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

    /**
     * Цикл игры.
     *
     * @throws IOException если возникают проблемы с логгером
     */
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

    /**
     * Завершить игру.
     *
     * @throws IOException если возникают проблемы с логгером
     */
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
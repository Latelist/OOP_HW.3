package view;

import java.util.Scanner;

public class UserView {
    public void startText() {
        System.out.println("Добро пожаловать в игру.");
    }

    public void alphabetSettingText() {
        System.out.println("Пожалуйста, выберите алфавит: \n" +
                "1 — русский,\n" +
                "2 — английский, \n" +
                "3 — числа.");
    }
    public void wordLengthSettingText() {
        System.out.println("Введите длину слова, которое загадает компьютер: ");
    }

    public void tryCountSettingText() {
        System.out.println("Сколько попыток вы хотите? ");
    }

    public void completeSettingsText() {
        System.out.println("Условия приняты. Компьютер загадал слово.");
    }

    public void inputValueText() {
            System.out.println("Введите догадку: ");

    }

    public void computerAnswerText(int bulls, int cows) {
        System.out.println(cows + " коровы, " + bulls + " быка.");
    }
    public void winText() {
        System.out.println("Вы угадали слово. Респект.");
    }
    public void loseText() {
        System.out.println("У вас закончились попытки. Компьютер победил.");
    }

    public void gameAlreadyStartedText() {
        System.out.println("Игра уже идет, дурачок.");
    }

    public void endText() {
        System.out.println("Игра окончена. Хотите попробовать еще? \n" +
                "1 — да, \n" +
                "2 — нет.\n");
    }
    public void goodByeText() {
        System.out.println("Спасибо за игру. Живите и процветайте. ");
    }
}

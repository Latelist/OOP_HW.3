package Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Класс для логирования событий.
 */
public class Logger {

    /** Путь к файлу лога. */
    public String filePath = "BullsAndCowsLog.txt";
    /** Файл лога. */
    public File log;
    /** Поток для записи в файл. */
    public FileWriter writer;

    /** Текущая дата и время. */
    public Date currentDate;

    /**
     * Конструктор создает файл лога.
     * @throws IOException если возникает ошибка ввода/вывода при создании файла лога.
     */
    public Logger() throws IOException {
        log = createFile();
    }

    /**
     * Создать файл лога.
     * @return созданный файл лога.
     */
    public File createFile() {
        try {
            File file = new File(filePath);
            boolean isFileCreated = file.createNewFile();
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Записать новые данные в лог.
     * @param line строка для записи в лог.
     * @throws IOException если возникает ошибка ввода/вывода при записи в файл лога.
     */
    public void newEntry(String line) throws IOException {
        writer = new FileWriter(filePath, true);
        currentDate = new Date();
        writer.write(currentDate.toString() + ": " + line + "\n \n");
        writer.flush();
        writer.close();
    }
}

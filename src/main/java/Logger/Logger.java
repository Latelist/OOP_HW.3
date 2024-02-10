package Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {
    public String filePath = "BullsAndCowsLog.txt";
    public File log;
    public FileWriter writer;

    public Date currentDate;
    public Logger() throws IOException {
        log = createFile();
    }

    public File createFile() {
        try {
            File file = new File(filePath);
            boolean isFileCreated = file.createNewFile();
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void newEntry(String line) throws IOException {
        writer = (new FileWriter(filePath, true));
        currentDate = new Date();
        writer.write(currentDate.toString() + ": " + line + "\n \n");
        writer.flush();
        writer.close();
    }
}

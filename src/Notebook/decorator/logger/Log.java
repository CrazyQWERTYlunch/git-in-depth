package Notebook.decorator.logger;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;

public class Log{
    private static final String fileName = "logs.txt";

    public Log() {}

    public static void createLog(){
        try {
            File db = new File(fileName);
            if (db.createNewFile()) {
                System.out.println("logs created");
            }
            else {
                System.out.println("logs already exists");
            }
        }
        catch (Exception e) {
            System.err.println(e);
        }

    }

    public void writerLog(String text) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            text = "INFO:" + new Date() + ":"+ text +"\n";
            writer.write(text);
            writer.close();
        } catch (Exception e){
            System.out.println("Файла нет");
        }
    }

}

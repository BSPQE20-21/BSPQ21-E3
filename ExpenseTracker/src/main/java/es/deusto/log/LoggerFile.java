package es.deusto.log;

import org.slf4j.*;
import java.util.logging.*; 
import java.io.IOException; 
import javax.swing.*; 
import java.io.*;
import java.util.logging.Logger;

public class LoggerFile {
   

    static Logger logger;
    public Handler fileHandler;
    Formatter plainText;

    private LoggerFile() throws IOException{
        //instance the logger
        logger = Logger.getLogger(LoggerFile.class.getName());
        //instance the filehandler
        fileHandler = new FileHandler("myLog.txt",true);
        //instance formatter, set formatting, and handler
        plainText = new SimpleFormatter();
        fileHandler.setFormatter(plainText);
        logger.addHandler(fileHandler);

    }
    private static Logger getLogger(){
        if(logger == null){
            try {
                new LoggerFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return logger;
    }
    public static void log(Level level, String msg){
        getLogger().log(level, msg);
        System.out.println(msg);
    }
    
}

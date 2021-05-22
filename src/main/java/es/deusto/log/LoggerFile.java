package es.deusto.log;

import java.util.logging.*; 
import java.io.IOException; 
import java.util.logging.Logger;

/**
 * This is a logger class that systematically records events in a log to keep track of changes and communication happening in the execution of the program.
 */

public class LoggerFile {
   
	/**
	 * Complete constructor.\n
	 * @param Logger - composed of body text
	 * @param fileHandler - writes logging records into a specific file
	*/

    static Logger logger;
    public Handler fileHandler;
    Formatter plainText;

    private LoggerFile() throws IOException{
    	/**
    	 * Instantiation of the logger
    	 */
        logger = Logger.getLogger(LoggerFile.class.getName());
        
        /**
    	 * Instantiation of the fileHandler
    	 */
        fileHandler = new FileHandler("myLog.txt",true);
        //Instance formatter, set formatting and handler
        plainText = new SimpleFormatter();
        fileHandler.setFormatter(plainText);
        logger.addHandler(fileHandler);

    }
    /**
	 * Method that selects specified file to dump logs into; In case the file is not found by the method, or doesn't exit, it will create one.
	 */
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
    /**
	 * Method to call the log and write in it categorising the entry. The method receives two inputs:\n
	 * @param level - used to specify by urgency the category of the log entry. 
	 * @param msg - the body in form of text of the log
	 */
    public static void log(Level level, String msg){
        getLogger().log(level, msg);
        //System.out.println(msg);
    }
    
}

package ru.spbau.sorokin.task1;

import java.util.List;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FileMessageWriter implements MessageWriter {
    private PrintWriter printWriter = null;    

    public FileMessageWriter(String filename) throws FileNotFoundException, IOException { 
        printWriter = new PrintWriter(new BufferedWriter(new FileWriter(filename)), true);
    }
    
    public void writeMessage(Message message) {
        List<String> stringList = message.getLines();
        printWriter.println(stringList.size());

        for(String str : stringList) {
            printWriter.println(str);
        }
    }

    public void close() {
        if(printWriter != null) {
            printWriter.close();
        }
    }

}

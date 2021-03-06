/*
 * Класс Message task1.
 */
package ru.spbau.sorokin.task1;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Message class aggregate ArrayList<String>
 * @author Sorokin Artyom
 * @version 1.0
 */
public class Message {
    
    private List<String> messageLines = new ArrayList<String>();
	
    /**
     * Appends obtained message to the end.
     * @param message The appended message
     */    
    public void append(Message message) {
        messageLines.addAll(message.getLines());
	}
		
    /**
     * Returns the list containing lines from this message
     * @return the list containing lines from this message
     */
    public List<String> getLines() {
        return Collections.unmodifiableList(messageLines);
    }
        
    /**
     * Adds the single line to the end of this message
     * @param line The added line
     */
    public void add(String line) {
        messageLines.add(line);
    }
    
    /**
     * Returns the number of lines
     * @return the number of lines
     */
    public int size() {
        return messageLines.size();
    }
    
    /**
     * Removes all lines from this message 
     */
    public void clear() {
        messageLines.clear();                
    }
}

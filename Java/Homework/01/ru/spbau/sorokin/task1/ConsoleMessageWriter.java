package ru.spbau.sorokin.task1;

import java.util.List; 

public class ConsoleMessageWriter implements MessageWriter {

    private int messageCount = 1;

    public void writeMessage(Message message) {
        List<String> stringList = message.getLines();

        System.out.printf("Message %d%n", messageCount);

        for(int i = 0; i < stringList.size(); i++) {
            System.out.printf("%d.%d ", messageCount, i+1);
            System.out.println(stringList.get(i));        
        }
        
        messageCount++;
    } 

    public void close() {
        //just do nothing
    }

}

package ru.spbau.sorokin.task1;

public interface MessageWriter {

    void writeMessage(Message message); 
    
    void close();
} 


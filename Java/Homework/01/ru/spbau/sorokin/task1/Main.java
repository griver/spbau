package ru.spbau.sorokin.task1;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try{
            /*
            Message msg = new Message();
            msg.add("Это моё первое сообщение");
            msg.add("я сейчас очень сильно вонлуюсь");
            msg.add("В общем удачи всем!!");
            Message msg2 = new Message();
            msg2.add("Это моё первое сообщение");
            msg2.add("я сейчас очень сильно вонлуюсь");
            msg2.add("Очень странные вещи");
            //*/
            FileMessageReader mr = new FileMessageReader("text.txt");
            Message msg = mr.readMessage();
            Message msg2 = mr.readMessage();
            Message msg3 = mr.readMessage();
            MessageWriter mw = new ConsoleMessageWriter(); //FileMessageWriter("Messages.txt");
            MessageWriter cmw = new CompressingMessageWriter(mw);
            cmw.writeMessage(msg);
            cmw.writeMessage(msg2);
            cmw.writeMessage(msg3);
            cmw.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Ну блин не смогли найти файл");
        }// catch (IOException  ioe) {
        /*    ioe.printStackTrace(System.err);
        }//*/
       
    }
}

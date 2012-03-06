/*
 * Some block comment
 */
package ru.spbau.sorokin.task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import ru.spbau.sorokin.task1.Message;

public class FileMessageReader {
        private BufferedReader bufferReader = null;
        public FileMessageReader(String fileName) { 
             bufferReader = new BufferedReader(new FileReader(fileName));
        }

        public Message readMessage() {
            Message msg = new Message();

            if (bufferReader == null) {
                return msg;                            
            }   
            
            try {   //Не забудь добавить проверку на то что строка  не пуста
                    String line = bufferReader.readLine();
                    int linesNumber = Integer.parseInt(line);

                    for(int i = 0; i < linesNumber; i++) {
                        msg.add(bufferReader.readLine());                    
                    }
            } catch (IOException e) {
                System.out.println("IOException: " + e);
            }
        }
}

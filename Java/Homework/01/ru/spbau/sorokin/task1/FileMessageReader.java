/*
 * Some block comment
 */
package ru.spbau.sorokin.task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;



public class FileMessageReader {
        private BufferedReader bufferReader = null;
        public FileMessageReader(String fileName) throws FileNotFoundException { 
            bufferReader = new BufferedReader(new FileReader(fileName));
        }

        public Message readMessage() {
            Message msg = new Message();
            
            try {  
                    String line = bufferReader.readLine();
                    System.out.println(line);
                    int linesNumber = Integer.parseInt(line);

                    for(int i = 0; i < linesNumber; i++) {
                        msg.add(bufferReader.readLine());                    
                    }

                    return msg;
            } catch (IOException e) {
                System.out.println("IOException: " + e);
            } catch (NumberFormatException e) {
                
            }	
            return msg; 
        }
        
        public Message readLines() {
        if()
        }
}

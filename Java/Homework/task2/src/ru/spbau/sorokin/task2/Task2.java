package ru.spbau.sorokin.task2;


import java.io.FileNotFoundException;
import java.io.PrintStream;


/**
 * "Main" class for second java homework.
 *
 * @author Sorokin Artyom
 */
public class Task2 {

    static public void main(String[] args) {
        if(args.length == 0) return;

        try{
            PrintStream stream = new PrintStream("output.txt");
            //PrintStream does not throw an exception in println(String) and close() methods.
            FilesystemWalker walker = new FilesystemWalker(stream);
            walker.startWalk(args[0], new PatternFilter());
            stream.close();

        } catch (FileNotFoundException e) {
            System.err.println("Couldn't find file: " + args[0]);
        } catch (SecurityException e) {
            System.err.println("Sorry, you are not allowed to write to " + args[0]);
        }

    }
}

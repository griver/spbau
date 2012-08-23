package ru.spbau.sorokin.task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: griver
 * Date: 13.08.12
 * Time: 0:58
 * To change this template use File | Settings | File Templates.
 */
public class Task2 {
    static public void main(String[] args) {
        File root = new File(args[0]);

        try{

            PrintStream stream = new PrintStream("output.txt");
            FilesystemWalker walker = new FilesystemWalker(stream);
            walker.startWalk2(args[0], new PatternFilter());

        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}

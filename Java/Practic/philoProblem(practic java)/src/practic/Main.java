package practic;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 04.04.12
 * Time: 12:52
 * To change this template use File | Settings | File Templates.
 *///To change body of implemented methods use File | Settings | File Templates.

import java.io.BufferedReader;
import java.util.Scanner;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in );
        String inputString = scanner.nextLine();
        int number = Integer.parseInt(inputString);

        ArrayList<Fork> table = new ArrayList<Fork>();
        ArrayList<Philosoph> philosophs = new ArrayList<Philosoph>();
        ArrayList<Thread> threads = new ArrayList<Thread>();

        for(int i = 0; i < number; ++i) {
            table.add(new Fork(i));
        }

        for(int i = 0; i < number; ++i) {
            philosophs.add(new Philosoph(table.get((i + 1) % number), table.get(i), i) );

        }

        for(Philosoph philosoph : philosophs) {
            threads.add(new Thread(philosoph) );
        }


        for(Thread thread : threads) {
            thread.start();
        }
    }
}

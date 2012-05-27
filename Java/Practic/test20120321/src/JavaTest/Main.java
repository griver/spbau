package JavaTest;

import java.nio.channels.Selector;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 21.03.12
 * Time: 12:23
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main( String[] args) {
        ArrayList<Integer> array = new ArrayList<Integer>();
        array.add(4);
        array.add(3);
        array.add(2);

    }

    public<T extends Selector> void  printer(T selector) {
        while (!selector.end()) {
            System.out.print(sel.current() + " ");
            sel.next();
        }

    }
}



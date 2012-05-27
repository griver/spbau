package Sample;

import Sample.items.Bottle;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 07.04.12
 * Time: 14:14
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    static public void main(String[] args)  {
        IFieldObject object = new Bottle(7);
        object.setCell(6);
        System.out.println(object.getCell());
        object.processColliding("Artem");
        object.processViewing("Lera");

    }
}

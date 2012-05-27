package Test.InstanceOf;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 16.05.12
 * Time: 12:00
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        BaseObject base = createObjectB();

        if(base instanceof Lantern) {
            System.out.println("It's a Lantern!!");
            base.write();
        } else if(base instanceof BaseObject) {
            System.out.println("It's on of implementations of BaseObject interface");
            base.write();
        } else {
            System.out.println("I don't know what it can be");
        }
    }

    static public BaseObject createObjectA() {
        return new Lantern();
    }

    static public BaseObject createObjectB() {
       return new Wombat();
    }
}

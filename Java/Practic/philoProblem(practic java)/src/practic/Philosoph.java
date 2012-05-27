package practic;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 04.04.12
 * Time: 12:41
 * To change this template use File | Settings | File Templates.
 */


import java.util.Random;

public class Philosoph implements Runnable {
    private Fork left;
    private Fork right;
    private final Random random = new Random();
    private int index;

    public Philosoph(Fork left, Fork right, int index) {
        this.left = left;
        this.right = right;
        this.index = index;
    }

    public Fork getLeft() {
        return left;
    }

    public void setLeft(Fork left) {
        this.left = left;
    }

    public Fork getRight() {
        return right;
    }

    public void setRight(Fork right) {
        this.right = right;
    }

    @Override
    public void run() {
        try {
            while(true) {
                synchronized (left) {
                    synchronized (right){
                        System.out.println("Philosoph number " +Integer.toString(index) + " start eating");
                        Thread.sleep(1000 + random.nextInt(2000));
                        System.out.println("Philosoph number " +Integer.toString(index) + " start sleeping");
                    }
                }
                Thread.sleep(10+ random.nextInt(20));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

package Sample;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 07.04.12
 * Time: 13:45
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractFieldObject implements  IFieldObject{
    public int cell;

    @Override
    public void processColliding(String object) {
        System.out.println("AbstractFieldObject.processColliding( " + object + " )");
    }

    @Override
    public void processViewing(String object) {
        System.out.println("AbstractFieldObject.processViewing( " + object + " )");
    }

    @Override
    public int getCell() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setCell(int cell) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

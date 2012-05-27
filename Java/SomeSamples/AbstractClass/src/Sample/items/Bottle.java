package Sample.items;

import Sample.AbstractFieldObject;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 07.04.12
 * Time: 14:11
 * To change this template use File | Settings | File Templates.
 */
public class Bottle extends AbstractFieldObject {
    
    public Bottle(int cell) {
        super.cell = cell;
    }

    @Override
    public void processViewing(String object) {
        System.out.println("Bottle.processColliding( " +object + " )");
    }

    @Override
    public int getCell() {
        return super.cell;
    }

    @Override
    public void setCell(int cell) {
        this.cell = cell;

    }
}

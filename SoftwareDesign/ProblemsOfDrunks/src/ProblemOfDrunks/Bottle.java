package ProblemOfDrunks;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 18.03.12
 * Time: 3:11
 * To change this template use File | Settings | File Templates.
 */
public class Bottle implements IFieldObject {
    private ICell cell;

    public Bottle() {
    }

    @Override
    public void processColliding(IMovingObject object) {
        object.processColliding(this);
    }

    public ICell getCell() {
        return cell;
    }

    public void setCell(ICell cell) {
        this.cell = cell;
    }
}

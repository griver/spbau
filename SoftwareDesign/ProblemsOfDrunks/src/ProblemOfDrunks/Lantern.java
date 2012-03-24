package ProblemOfDrunks;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 18.03.12
 * Time: 3:24
 * To change this template use File | Settings | File Templates.
 */
public class Lantern implements IFieldObject{
    private ICell cell;


    public Lantern() {
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

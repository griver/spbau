package ProblemOfDrunks;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 18.03.12
 * Time: 18:50
 * To change this template use File | Settings | File Templates.
 */
public class Column implements IFieldObject {
    private ICell cell;

    public Column() {
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

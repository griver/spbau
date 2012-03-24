package ProblemOfDrunks;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 18.03.12
 * Time: 2:57
 * To change this template use File | Settings | File Templates.
 */
public interface IFieldObject{
    void processColliding(IMovingObject object);

    ICell getCell();

    void setCell(ICell cell);
}

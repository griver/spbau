package Sample;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 07.04.12
 * Time: 13:43
 * To change this template use File | Settings | File Templates.
 */

public interface IFieldObject{

    void processColliding(String object);
    void processViewing(String object);

    int getCell();

    void setCell(int cell);
}

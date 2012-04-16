package ProblemOfDrunks.objects.things;

import ProblemOfDrunks.exeption.InvalidCoordinateException;
import ProblemOfDrunks.field.ICell;
import ProblemOfDrunks.objects.IGameObject;
import ProblemOfDrunks.objects.IMovingObject;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 18.03.12
 * Time: 3:24
 * To change this template use File | Settings | File Templates.
 */
public class Lantern extends AFieldObject {
    private int brightness;

    public Lantern( int brightness ) {
        this.brightness = brightness;
    }

    public boolean isLighted(ICell fieldCell) {
        try {

            if(getField().distance(this.getCell(), fieldCell) <= brightness)
                return true;

        } catch (InvalidCoordinateException e) {
            e.printStackTrace(System.err);
            System.exit(0);
        }

        return false;
    }

    @Override
    public void getColliding(IMovingObject object) {
        object.processColliding(this);
    }

    @Override
    public void processViewing(IGameObject object) {
        object.viewFieldObject(this);
    }

    @Override
    public char getSymbol() {
        return '|';
    }
}

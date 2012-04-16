package ProblemOfDrunks.objects;

import ProblemOfDrunks.objects.IFieldObject;
import ProblemOfDrunks.objects.moving.Drunk;
import ProblemOfDrunks.objects.things.Bottle;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 07.03.12
 * Time: 21:42
 * To change this template use File | Settings | File Templates.
 */
public interface IGameObject {
    void makeAction();
    void viewFieldObject(IFieldObject object);
    void viewFieldObject(Drunk object);
    void viewFieldObject(Bottle object);
}

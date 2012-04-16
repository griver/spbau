package ProblemOfDrunks.objects.buildings;

import ProblemOfDrunks.objects.IFieldObject;
import ProblemOfDrunks.objects.IGameObject;
import ProblemOfDrunks.objects.moving.Drunk;
import ProblemOfDrunks.objects.things.Bottle;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 07.04.12
 * Time: 15:28
 * To change this template use File | Settings | File Templates.
 */
public class AGameObject implements IGameObject {
    @Override
    public void makeAction() {
        return;
    }

    @Override
    public void viewFieldObject(IFieldObject object) {
        return;
    }

    public void viewFieldObject(Drunk object) {
        return;
    }

    public void viewFieldObject(Bottle object) {
        return;
    }
}

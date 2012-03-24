package ProblemOfDrunks;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 18.03.12
 * Time: 18:45
 * To change this template use File | Settings | File Templates.
 */
public interface IMovingObject extends IFieldObject, IGameObject {
    void processColliding(Drunk object);
    void processColliding(Policeman object);
    void processColliding(Lantern object);
    void processColliding(Bottle object);
    void processColliding(Column object);
}

package ProblemOfDrunks;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 14.03.12
 * Time: 13:15
 * To change this template use File | Settings | File Templates.
 */
public interface IGame {
    void setField(IField field);
    void addGameObject(IGameObject obj);
    boolean nextSteep();
}

package ProblemOfDrunks;

import ProblemOfDrunks.field.IField;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 07.04.12
 * Time: 19:34
 * To change this template use File | Settings | File Templates.
 */
public interface IGameBuilder {
    IGame buildGame();
    void setGame(IGame game);
    void setField(IField field);
}
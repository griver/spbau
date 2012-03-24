package ProblemOfDrunks;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 19.03.12
 * Time: 3:07
 * To change this template use File | Settings | File Templates.
 */
public interface IPathAlgorithm {
    List<ICell>  findPath(ICell startCell, ICell endCell );

    IField getField();

    void setField(IField field);
}

package JavaTest;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 21.03.12
 * Time: 10:48
 * To change this template use File | Settings | File Templates.
 */

public class TreeNode<T> {
    public TreeNode<T> left;

    public TreeNode<T> right;

    public TreeNode<T> parent;

    public T value;

    public TreeNode(T value) {
        this.value = value;
    }
}

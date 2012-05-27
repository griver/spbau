package JavaTest;



import java.lang.Comparable;
/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 21.03.12
 * Time: 10:50
 * To change this template use File | Settings | File Templates.
 */
public class BinaryTree<T extends Comparable<T> > {
     private TreeNode<T> root;
     void Insert(T value) {
         TreeNode<T> node = root;
         while (node != null) {
             if(value.compareTo(node.value) < 0) {
                node = node.left;
             }
             else {
                node = node.right;
             }
         }

         node = node
     }
}

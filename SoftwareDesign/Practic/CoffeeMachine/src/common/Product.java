package common;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 27.04.12
 * Time: 18:07
 * To change this template use File | Settings | File Templates.
 */
public class Product {
    private String text;
    
    public Product(String text) {
        this.text = text;    
    }

    void addText(String text) {
        this.text = this.text + text;
    } 

    String getText() {
        return text;
    }
}

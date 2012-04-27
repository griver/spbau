package common;

import common.exception.InvalidResourceData;

import java.util.HashMap;


/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 27.04.12
 * Time: 3:03
 * To change this template use File | Settings | File Templates.
 */
public class ProductData {
    private ProductType type;
    private HashMap<String, Integer> requiredResources = new HashMap<String, Integer>();


    public ProductData(ProductType type) {
        this.type = type;
    }

    public void addResource(String resourceName, int portionNumber) throws InvalidResourceData {
        if(portionNumber > 0)
            requiredResources.put(resourceName, portionNumber);
        else
            throw new InvalidResourceData();
    }    

    public ProductType getType() {
        return type;
    }
    
    public HashMap<String, Integer> getResources() {
        return requiredResources;
    }
}

package common;

import common.exception.InvalidResourceData;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: griver
 * Date: 27.04.12
 * Time: 18:36
 * To change this template use File | Settings | File Templates.
 */
public class ProductMaker {
    HashMap<String, ResourceData> resources;

    public void setResources(HashMap<String, ResourceData> resources) {
        this.resources = resources;
    }

    public boolean canMake(ProductData data) {
       
        for(Map.Entry<String, Integer> res : data.getResources().entrySet()){
            if(resources.containsKey(res.getKey()))
                if(resources.get(res.getKey()).hasPortions() > res.getValue())
                    continue;
                else return false;        
            else return false;      
        }
        return true;
    }
    public Product make(String name, ProductData data) throws InvalidResourceData {
        for(Map.Entry<String, Integer> res : data.getResources().entrySet()){
            ResourceData current = resources.get(res.getKey());
            current.removePortions(res.getValue());
        }
        return new Product(name);
    }
    
    public int getCost(ProductData data) {
        int sum = 0;
        for(Map.Entry<String, Integer> res : data.getResources().entrySet()){
            sum += res.getValue()*resources.get(res.getKey()).getCost();    
        }
        return sum;
    }
}

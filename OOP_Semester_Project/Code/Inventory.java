package final_project;

import java.io.Serializable;

// Invertory class having attribute speific to inventory items with getter and setter methods.

public class Inventory implements Serializable {
    
    private String itemName;
    private int numOfItems;

    public Inventory(String itemName, int numOfItems) {
        this.itemName = itemName;
        this.numOfItems = numOfItems;
    }

    public String getName() {
        return itemName;
    }

    public int getNumOfItems() {
        return numOfItems;
    }

    public void setName(String itemName) {
        this.itemName = itemName;
    }

    public void setNumOfItems(int numOfItems) {
        this.numOfItems = numOfItems;
    }

    @Override
    public String toString() {
        return "Item Name: " + itemName + "\nNumber Of Items: " + numOfItems;
    }
}
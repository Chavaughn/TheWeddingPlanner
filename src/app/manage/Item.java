package app.manage;

import java.util.Arrays;
import app.util.Spreadsheet;

public class Item {
    private String name;
    private int quantity;
    private String itemType;

    /**Constants*/
    public final static String[] ITEM_TYPES = {"Chairs", "Cushions", "Couches & Misc Chairs", "Chargers & Vases", "Tables", "Fabrics", "Misc Items"};

    /**Default Constructor */
    public Item(){}

    /**Constructor */
    public Item( String name, int quantity, String itemType) {
        try {
            new Spreadsheet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        this.name=name;
        this.quantity= quantity;
        setItemType(itemType);
    } 



    /**RETURNS the name of the Item object */
    public String getName(){
        return this.name;
    } 

    /**RETURNS the quantity of the Item object */
    public int getQuantity(){
        return this.quantity;
    }

     /**RETURNS the quantity of the Item object */
     public String getItemType(){
        return this.itemType;
    }

    /**sets location of the venue object */
    public void setItemType(String type){
        if(isValidType(type)){
            this.itemType = type;
        }else{
            this.itemType = "INVALID_INPUT";
        }
    }
     /**Checks if the type entered matches the types in the system */
     private boolean isValidType(String type){
        return Arrays.stream(ITEM_TYPES).anyMatch(type::contains);
    }
}

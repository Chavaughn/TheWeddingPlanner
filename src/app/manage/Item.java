package app.manage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import app.util.Spreadsheet;

public class Item {

    private Spreadsheet sp;
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
            sp = new Spreadsheet();
        } catch (InvalidFormatException | IOException| NullPointerException e) {
            e.printStackTrace();}
        
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

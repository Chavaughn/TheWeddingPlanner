package app.manage;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import app.util.Spreadsheet;

public class Inventory{
    private Item item;
    private Spreadsheet spreadsheet;

    public Item createItem( String name, int quantity, String itemType){
        item = new Item(name,quantity,itemType);
        //write ItemSheet() needs to be inplemented before removing comment
        try {
            spreadsheet = new Spreadsheet();
            spreadsheet.writeItemSheet(item);
        } catch (InvalidFormatException | IOException| NullPointerException e) {
            e.printStackTrace();
        }
        return this.item;
       
    }

    public ArrayList<String[]> viewAllItems(){
        try {
            spreadsheet = new Spreadsheet();
        } catch (InvalidFormatException | IOException| NullPointerException e) {
            e.printStackTrace();
        }
        return spreadsheet.readSheet("Inventory");
    }

    public void editItem(String ItemId){

    }

    public void removeItem(String ItemId){

    }
}

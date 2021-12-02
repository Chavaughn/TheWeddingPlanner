package app.manage;

import java.util.ArrayList;


import app.util.Spreadsheet;

public class Inventory{
    private Item item;
    private Spreadsheet sp;

    public Item createItem( String name, int quantity, String itemType){
        item = new Item(name,quantity,itemType);
        //write ItemSheet() needs to be inplemented before removing comment
        try {
            sp = new Spreadsheet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.item;
       
    }

    public ArrayList<String[]> viewAllItems(){
        try {
            sp = new Spreadsheet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sp.readSheet("Inventory");
    }

    public void editItem(String ItemId){

    }

    public void removeItem(String ItemId){

    }
}

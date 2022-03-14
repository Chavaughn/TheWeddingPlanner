package app.Management;

import java.util.ArrayList;

public class Inventory{
    private Item item;

    public Item createItem( String name, int quantity, String itemType){
        item = new Item(name,quantity,itemType);
        return this.item;
       
    }

    public ArrayList<String[]> viewAllItems(){
        return null;
    }


    public void editItem(String ItemId){

    }

    public void removeItem(String ItemId){

    }
}

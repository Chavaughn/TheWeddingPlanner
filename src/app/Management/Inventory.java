package app.Management;

import java.util.ArrayList;

import app.Utility.DatabaseMng;

public class Inventory{
    private DatabaseMng dbm;
    private Item item;

    public void createItem( String name, int quantity, String itemType){
        item = new Item(name,quantity,itemType);
        dbm = new DatabaseMng();
        dbm.AddToItemsTable(item);
       
    }

    public ArrayList<String[]> viewAllItems(){
        dbm = new DatabaseMng();
        return dbm.viewInventory();
    }


    public void editItem(Item item, int ItemId){
        dbm = new DatabaseMng();
        dbm.updateItemsTable(item, ItemId);
    }

    public void removeItem(String record){
        dbm = new DatabaseMng();
        int Id = Integer.parseInt(record.split(",")[0].replace("[", ""));
        dbm.removeFromItemTable(Id);
    }
}

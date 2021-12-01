package app.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


import com.aspose.cells.*;


import app.auth.User;
import app.manage.Client;
import app.manage.Venue;


public class Test  {

    private File file = new File("src/res/sheets/The_Wedding_Planner.xlsx");

    private FileInputStream fis = new FileInputStream(file);
   

    Workbook workbook = new Workbook(fis);
    WorksheetCollection worksheets = workbook.getWorksheets();


    // private Worksheet userSheet = worksheets.add("User");
    private Worksheet venueSheet = worksheets.add("Venue");
    private Worksheet inventorySheet = worksheets.add("Inventory");
    private Worksheet clientSheet = worksheets.add("Client");
    private Worksheet reservsationSheet = worksheets.add("Reservation");
    private Worksheet itemSheet = worksheets.add("Items");
    

    public Test()throws  Exception, FileNotFoundException{
       worksheets.get("Sheet1").setName("User");

        if (!filecheck()){
            workbook = new Workbook(fis);
        }

        
      
        
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[] {"ID", "NAME", "AccessLevel"});
        data.put("2", new Object[] {"VID", "Venue Name", "Date", "Venue Type", "Location"});
        data.put("3", new Object[] {"RID", "Wedding Date", "Reservsation Date", "Approximate Price"});
        data.put("4", new Object[] {"CID", "Name", "Date of Birth", "Email", "Phone Numbers"});
        data.put("5", new Object[] {"Chairs"});
        data.put("6", new Object[] {"Name", "Quantity"});
        Set<String> keyset = data.keySet();
    
     
        for (String key : keyset)
        {   
            Worksheet currsheet = null;
           
           
            for(int i=0; i<workbook.getWorksheets().getCount(); i++){
                currsheet = workbook.getWorksheets().get(i);
                
                
                if (Integer.parseInt(key) == (i+1)){
                    
                    Object [] objArr = data.get(key);
                    int cellnum = 0;
                    for (Object obj : objArr)
                    {
                        
                        Cell cell = currsheet.getCells().get(0, cellnum++);
                        if(obj instanceof String)
                            cell.setValue((String)obj);
                        else if(obj instanceof Integer)
                            cell.setValue((Integer)obj);
                        
                    }
                }
                
                 
            }  
        }
        try
        {
            //Write the workbook in file system
            
            

            workbook.save("src/res/sheets/The_Wedding_Planner.xlsx");
        
      
            fis.close();
           
            System.out.println("The_Wedding_Planner.xlsx written successfully on disk.");
            
        } 

      



        catch (Exception e) 
        {
            e.printStackTrace();
        }

        User user = new User("Richard", "12","pass",2);
        writeUserSheet(user);
        user = new User("Simon", "13","pass",1);
        writeUserSheet(user);
        System.out.println(readSheet("User"));
	}

    public void writeUserSheet(User user) throws Exception ,FileNotFoundException{
        Worksheet sheet = worksheets.get("User");
        int rowCount =  sheet.getCells().getMaxDataRow();

        Cell cell = sheet.getCells().get(rowCount+1, 0);
        cell.setValue(user.getUseriD()+"");

        cell = sheet.getCells().get(rowCount+1, 1);
        cell.setValue(user.getUserName());

        cell = sheet.getCells().get(rowCount+1, 2);
        cell.setValue(user.getAuthLevel());

        try
        {
            workbook.save("src/res/sheets/The_Wedding_Planner.xlsx");
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }

    }

    public void writeVenueSheet(Venue venue) throws Exception ,FileNotFoundException{
        Worksheet sheet = worksheets.get("Venue");
        int rowCount = sheet.getCells().getMaxDataRow();

        Cell cell = sheet.getCells().get(rowCount+1, 0);
        cell.setValue(venue.getVenueId()+"");

        cell = sheet.getCells().get(rowCount+1, 1);
        cell.setValue(venue.getVenueName());

        cell = sheet.getCells().get(rowCount+1, 2);
        cell.setValue(venue.getDate().toString());

        cell = sheet.getCells().get(rowCount+1, 3);
        cell.setValue(venue.getVenueType());

        cell = sheet.getCells().get(rowCount+1, 4);
        cell.setValue(venue.getLocation());

        try
        {
            workbook.save("src/res/sheets/The_Wedding_Planner.xlsx");
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }

    }

    public void writeClientSheet(Client client) throws Exception ,FileNotFoundException{
        Worksheet sheet = worksheets.get("Client");
        int rowCount = sheet.getCells().getMaxDataRow();

        Cell cell = sheet.getCells().get(rowCount+1, 0);
        cell.setValue(client.getClientId()+"");

        cell = sheet.getCells().get(rowCount+1, 1);
        cell.setValue(client.getClientName());

        cell = sheet.getCells().get(rowCount+1, 2);
        cell.setValue(client.getDateOfBirth().toString());

        cell = sheet.getCells().get(rowCount+1, 3);
        cell.setValue(client.getEmail());

        cell = sheet.getCells().get(rowCount+1, 4);
        cell.setValue(client.getPhoneNumber());

        try
        {
            workbook.save("src/res/sheets/The_Wedding_Planner.xlsx");
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }

    }

    public ArrayList<String[]> readSheet(String sheetName){

        
        Worksheet sheet = worksheets.get(sheetName);
        

        ArrayList<String> cellval = new ArrayList<>() ;
        ArrayList<String[]> vallist = new ArrayList<String[]>() ;
        
        


        for (int i = 0; i < sheet.getCells().getRows().getCount(); i++){
            for(int x = 0; x < sheet.getCells().getMaxDataColumn()+1; x++){
                cellval.add(sheet.getCells().get(i,x).getStringValue());
            }
            vallist.add(cellval.toArray(new String[0]));
            cellval.clear();
        }
      
        System.out.println(vallist);
        return vallist;
    }
    

	private boolean filecheck(){
    
        if (file.length() == 0)
            return true;
        else
            return false;

    }
	public static void main(String[] args) throws  Exception, FileNotFoundException {
		try {
          
            File daf = new File("src/res/sheets/The_Wedding_Planner.xlsx");
          
            if(!daf.exists() ) {
                daf.createNewFile();
             }
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();}

		Test test = new Test();


        
        

	}
}

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

// import org.apache.poi.hssf.usermodel.HSSFCell;
// import org.apache.poi.hssf.usermodel.HSSFRow;
// import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
// import org.apache.poi.sl.usermodel.Sheet;
// import org.apache.poi.ss.usermodel.Cell;
// import org.apache.poi.ss.usermodel.CellStyle;
// import org.apache.poi.ss.usermodel.Row;
// // import org.apache.poi.ss.usermodel.Workbook;
// import org.apache.poi.xssf.usermodel.XSSFCell;
// import org.apache.poi.xssf.usermodel.XSSFRow;
// import org.apache.poi.xssf.usermodel.XSSFSheet;
// import org.apache.poi.xssf.usermodel.XSSFWorkbook;
// import com.spire.xls.ExcelVersion;
// import com.spire.xls.Workbook;
// import com.spire.xls.Worksheet;
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

        // GridWorksheet workSheet = GridWeb1.WorkSheets[GridWeb1.ActiveSheetIndex];
      
        
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[] {"ID", "NAME", "AccessLevel"});
        data.put("2", new Object[] {"VID", "Venue Name", "Date", "Venue Type", "Location", "Estimated Items Needed"});
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
        int rowCount =  worksheets.get("User").getCells().getMaxDataRow();

        Cell cell = worksheets.get("User").getCells().get(rowCount+1, 0);
        cell.setValue(user.getUseriD()+"");

        cell = worksheets.get("User").getCells().get(rowCount+1, 1);
        cell.setValue(user.getUserName());

        cell = worksheets.get("User").getCells().get(rowCount+1, 2);
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

    public String readSheet(String sheetName){

        StringBuilder stringBuilder = new StringBuilder();
        Worksheet sheet = worksheets.get(sheetName);
        

        ArrayList<String> cellval = new ArrayList<>() ;
        ArrayList<String> vallist = new ArrayList<String>() ;
        
        Iterator rowIter = sheet.getCells().getRows().iterator();


        for (int i = 0; i < sheet.getCells().getRows().getCount(); i++){
            for(int x = 0; x < sheet.getCells().getMaxDataColumn()+1; x++){
                cellval.add(sheet.getCells().get(i,x).getStringValue());
            }
            vallist.add(Arrays.toString(cellval.toArray(new String[0])));
            cellval.clear();
        }
      

        // while (rowIter.hasNext()) {
        //     Row myRow = (Row) rowIter.next();
        //     Iterator cellIter = myRow.iterator();
        //     while (cellIter.hasNext()) {
        //         Cell cell = (Cell) cellIter.next();
        //         cellval.add(cell.getStringValue());
                
        //     }

        // }
        // vallist.add(Arrays.toString(cellval.toArray(new String[0])));
        // cellval.clear();
        System.out.println(vallist);
        return worksheets.get(sheetName).getCells().getRows().spliterator().toString();
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

package app.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Spreadsheet {

    File file = new File("The_Wedding_Planner.xlsx");
    FileInputStream fis = new FileInputStream(file);
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet userSheet = workbook.createSheet("User");
    XSSFSheet venueSheet = workbook.createSheet("Venue");
    XSSFSheet inventorySheet = workbook.createSheet("Inventory");
    XSSFSheet clientSheet = workbook.createSheet("Client");
    XSSFSheet reservsationSheet = workbook.createSheet("Reservation");
    

    public Spreadsheet()throws InvalidFormatException, IOException{
        if (!filecheck()){
            workbook = new XSSFWorkbook(fis);
        }
        
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[] {"ID", "AccessLevel", "Password"});
        data.put("2", new Object[] {"Date", "Venue Type", "Parish", "Estimated Items Needed"});
        data.put("3", new Object[] {"ReservationID", "Wedding Date", "Reservsation Date", "Approximate Price"});
        data.put("4", new Object[] {"ReservationID", "Name", "Date of Birth", "Age", "Email", "Phone Numbers"});
        data.put("5", new Object[] {"Chairs"});
        data.put("6", new Object[] {"Name", "Quantity"});
        Set<String> keyset = data.keySet();
    
        int rownum = 0;
        for (String key : keyset)
        {   
            XSSFSheet currsheet = null;
            Row row = null;
           
            for(int i=0; i<workbook.getNumberOfSheets(); i++){
                currsheet = workbook.getSheetAt(i);  
                
                    if (Integer.parseInt(key) == (i+1)){
                        row = currsheet.createRow(rownum);
                        System.out.println(Integer.parseInt(key)+"  "+(i+1));
                    Object [] objArr = data.get(key);
                    int cellnum = 0;
                    for (Object obj : objArr)
                    {
                        
                        System.out.println((String)obj);
                       
                            Cell cell = row.createCell(cellnum++);
                            if(obj instanceof String)
                                cell.setCellValue((String)obj);
                            else if(obj instanceof Integer)
                                cell.setCellValue((Integer)obj);
                        
                    }
                    for(int x=0; x<7 ; x++){
                        currsheet.autoSizeColumn(x);
                    } 
                    }
            
               
            }
            
           
            
        }
        try
        {
            //Write the workbook in file system
            
            FileOutputStream out = new FileOutputStream(file);

            workbook.write(out);
            // workbook.close();
            out.close();
            fis.close();
           

            System.out.println("Tropichem Labs.xlsx written successfully on disk.");
            
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
       
    }




    private boolean filecheck(){
    
        if (file.length() == 0)
            return true;
        else
            return false;

    }
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NumberFormatException, InvalidFormatException, IOException {
        try {
            File daf = new File("The_Wedding_Planner.xlsx");
            
            if(!daf.exists() ) {
                daf.createNewFile();
             }
           
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();}

        Spreadsheet spreadsheet = new Spreadsheet();
    }
}



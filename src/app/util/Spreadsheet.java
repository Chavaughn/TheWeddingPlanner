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

import app.auth.User;
import app.manage.Client;
import app.manage.Venue;


public class Spreadsheet {

<<<<<<< Updated upstream
    private File file = new File("src/res/sheets/The_Wedding_Planner.xlsx");
    private FileInputStream fis = new FileInputStream(file);
    private XSSFWorkbook workbook = new XSSFWorkbook();
    private XSSFSheet userSheet = workbook.createSheet("User");
    private XSSFSheet venueSheet = workbook.createSheet("Venue");
    private XSSFSheet inventorySheet = workbook.createSheet("Inventory");
    private XSSFSheet clientSheet = workbook.createSheet("Client");
    private XSSFSheet reservsationSheet = workbook.createSheet("Reservation");
    private XSSFSheet itemSheet = workbook.createSheet("Items");
=======
    File file = new File("src/res/sheets/The_Wedding_Planner.xlsx");
    FileInputStream fis = new FileInputStream(file);
    static XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet userSheet = workbook.createSheet("User");
    XSSFSheet venueSheet = workbook.createSheet("Venue");
    XSSFSheet inventorySheet = workbook.createSheet("Reservation");
    XSSFSheet clientSheet = workbook.createSheet("Client");
    XSSFSheet reservsationSheet = workbook.createSheet("Inventory");
    XSSFSheet itemSheet = workbook.createSheet("Items");
>>>>>>> Stashed changes
    

    public Spreadsheet()throws InvalidFormatException, IOException{
       

        if (!filecheck()){
            workbook = new XSSFWorkbook(fis);
        }
        
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[] {"ID", "NAME", "AccessLevel"});
        data.put("2", new Object[] {"VID", "Venue Name", "Date", "Venue Type", "Location", "Estimated Items Needed"});
        data.put("3", new Object[] {"RID", "Wedding Date", "Reservsation Date", "Approximate Price"});
        data.put("4", new Object[] {"CID", "Name", "Date of Birth", "Email", "Phone Numbers"});
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
                    Object [] objArr = data.get(key);
                    int cellnum = 0;
                    for (Object obj : objArr)
                    {
                        
                        Cell cell = row.createCell(cellnum++);
                        if(obj instanceof String)
                            cell.setCellValue((String)obj);
                        else if(obj instanceof Integer)
                            cell.setCellValue((Integer)obj);
                        
                    }
                }
                for(int x=0; x<7 ; x++){
                    currsheet.autoSizeColumn(x);
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
           

            System.out.println("The_Wedding_Planner.xlsx written successfully on disk.");
            
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        System.out.println(getLastId()+1);
        // User user = new User("Richard", "12","pass",2);
        // writeUserSheet(user);
        // user = new User("Simon", "13","pass",1);
        // writeUserSheet(user);

        // int[] intArray = new int[]{2021,2,27}; 
        // Venue venue = new Venue("Long Mountain", "25",intArray,"Kingston");
        // writeVenueSheet(venue);

        // Client client =  new Client("Roger", intArray, 19, "roger@gmail.com", "1235555555");
        // writeClientSheet(client);
       
    }

    public void writeUserSheet(User user) throws FileNotFoundException, IOException{

        int rowCount = workbook.getSheet("User").getLastRowNum();
        Row row =  workbook.getSheet("User").createRow(++rowCount);
        Cell cell = row.createCell(0);
        cell.setCellValue(user.getUseriD()+"");

        cell = row.createCell(1);
        cell.setCellValue(user.getUserName());

        cell = row.createCell(2);
        cell.setCellValue(user.getAuthLevel());

        for(int x=0; x<7 ; x++){
            workbook.getSheet("User").autoSizeColumn(x);
        } 

        try (FileOutputStream outputStream = new FileOutputStream(new File("src/res/sheets/The_Wedding_Planner.xlsx"))) {
            workbook.write(outputStream);
        }

    }

    public void writeVenueSheet(Venue venue) throws FileNotFoundException, IOException{

        int rowCount = workbook.getSheet("Venue").getLastRowNum();
        Row row =  workbook.getSheet("Venue").createRow(++rowCount);
        Cell cell = row.createCell(0);
        cell.setCellValue(venue.getVenueId()+"");

        cell = row.createCell(1);
        cell.setCellValue(venue.getVenueName());

        cell = row.createCell(2);
        cell.setCellValue(venue.getDate().toString());
        
        cell = row.createCell(3);
        cell.setCellValue(venue.getVenueType());

        cell = row.createCell(4);
        cell.setCellValue(venue.getLocation());

        for(int x=0; x<7 ; x++){
            workbook.getSheet("Venue").autoSizeColumn(x);
        } 

        try (FileOutputStream outputStream = new FileOutputStream(new File("src/res/sheets/The_Wedding_Planner.xlsx"))) {
            workbook.write(outputStream);
        }

    }

    public void writeClientSheet(Client client) throws FileNotFoundException, IOException{

        int rowCount = workbook.getSheet("Client").getLastRowNum();
        Row row =  workbook.getSheet("Client").createRow(++rowCount);
        Cell cell = row.createCell(0);
        cell.setCellValue(client.getClientId()+"");

        cell = row.createCell(1);
        cell.setCellValue(client.getClientName());
        
        cell = row.createCell(2);
        cell.setCellValue(client.getDateOfBirth().toString());

        cell = row.createCell(3);
        cell.setCellValue(client.getEmail());

        cell = row.createCell(4);
        cell.setCellValue(client.getPhoneNumber());

        for(int x=0; x<7 ; x++){
            workbook.getSheet("Client").autoSizeColumn(x);
        } 

        try (FileOutputStream outputStream = new FileOutputStream(new File("src/res/sheets/The_Wedding_Planner.xlsx"))) {
            workbook.write(outputStream);
        }

    }


    public ArrayList<String[]> readSheet(String sheetName){
        Iterator<Row> rowIter = workbook.getSheet(sheetName).iterator();

        ArrayList<String> cellval = new ArrayList<>() ;

        ArrayList<String[]> vallist = new ArrayList<String[]>() ;
            while (rowIter.hasNext()) {
                Row myRow = rowIter.next();
                Iterator<Cell> cellIter = myRow.cellIterator();
                while (cellIter.hasNext()) {
                    Cell myCell = cellIter.next();
                    switch (myCell.getCellType()) {
                        case STRING:
                          cellval.add(myCell.getStringCellValue());
                          break;
                        case NUMERIC:
                          cellval.add(""+myCell.getNumericCellValue());
                          break;
                        default:
                        }
                }
               
                vallist.add(cellval.toArray(new String[0]));
                cellval.clear();
               
            }
        return vallist;
    }

    public int getLastId(){
        if (workbook.getSheet("Client").getRow(workbook.getSheet("Client").getFirstRowNum()+1) == null){
            return 0;

        }
            return (int) Double.parseDouble(workbook.getSheet("Client").getRow(workbook.getSheet("Client").getLastRowNum()).getCell(0).getStringCellValue());
    }


    private boolean filecheck(){
    
        if (file.length() == 0)
            return true;
        else
            return false;

    }
<<<<<<< Updated upstream
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NumberFormatException, InvalidFormatException, IOException 
    {
        new Spreadsheet();
=======
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NumberFormatException, InvalidFormatException, IOException {
        try {
            File daf = new File("src/res/sheets/The_Wedding_Planner.xlsx");
            
            if(!daf.exists() ) {
                daf.createNewFile();
             }
           
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();}

        Spreadsheet spreadsheet = new Spreadsheet();

        // User user = new User("Richard", "12","pass",2);
        // writeUserSheet(user);
        // user = new User("Simon", "13","pass",1);
        // writeUserSheet(user);

        // int[] intArray = new int[]{2021,2,27}; 
        // Venue venue = new Venue("Long Mountain", "25",intArray,"Kingston");
        // writeVenueSheet(venue);

        // Client client =  new Client("Roger", intArray, 19, "roger@gmail.com", "1235555555");
        // writeClientSheet(client);
    
        // System.out.println(readUserSheet());
        // System.out.println(readVenueSheet());
        // System.out.println(readClientSheet());


       
>>>>>>> Stashed changes
    }
}



package app.manage;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import app.util.Spreadsheet;

public class Client{
    private int cId;
    private String name;
    private LocalDate dateOfBirth;
    private String email; //Validate using regex
    private String phoneNumber;
    Spreadsheet sp;

    /**REGEX for email check */
    private static final String EMAIL_PATTERN= "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    /**Default Constructor */
    public Client(){}

    /**Before calling this constructor make sure to check if the email they are giving is correct and validated using the isEmailValid() method */
    public Client(String name, int[] dOB, String email, String phoneNumber){
        
        try {
            sp = new Spreadsheet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.cId = sp.getLastId() +1;
        this.name = name;
        this.dateOfBirth = LocalDate.of(dOB[0],dOB[1],dOB[2]);
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


    /**RETURNS the id of the CLient object */
    public int getClientId(){
        return this.cId;
    }
    /**RETURNS the name of the client object */
    public String getClientName(){
        return this.name;
    }

    /**RETURNS the Date of Birth of the client object */
    public LocalDate getDateOfBirth(){
        return this.dateOfBirth;
    }

    /**RETURNS the Email of the client object */
    public String getEmail(){
        return this.email;
    }

    /**RETURNS the Phone Number of the client object */
    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    /**Returns a boolean value of if the Email is correct */
    public static boolean isEmailValid(final String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}

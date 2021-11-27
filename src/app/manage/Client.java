package app.manage;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client extends Reservation{
    String name;
    LocalDate dateOfBirth;
    int age;
    String email; //Validate using regex
    String phoneNumber;

    /**REGEX for email check */
    private static final String EMAIL_PATTERN= "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    /**Default Constructor */
    public Client(){}

    /**Before calling this constructor make sure to check if the email they are giving is correct and validated using the isEmailValid() method */
    public Client(String name, int[] dOB, int age, String email, String phoneNumber){
        this.name = name;
        this.dateOfBirth = LocalDate.of(dOB[0],dOB[1],dOB[2]);
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


    /**RETURNS the client object */
    public Client getClient(){
        return this;
    }
    /**RETURNS the name of the client object */
    public String getClientName(){
        return this.name;
    }

    /**RETURNS the Date of Birth of the client object */
    public LocalDate getDateOfBirth(){
        return this.dateOfBirth;
    }

    /**RETURNS the Age of the client object */
    public int getAge(){
        return this.age;
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

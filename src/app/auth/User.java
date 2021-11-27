package app.auth;

import java.io.UnsupportedEncodingException;

public class User {
    private String name;
    private String iD;
    protected String password;
    private String authlevel;

    //TODO consider the validity of making this an enum of 3 levels
    public enum hierarchy{
        unset("unset"),
        low("low"),
        medium("medium"),
        high("high"); //Software creator and, Key Stakeholder

        String level;

        hierarchy(String level){
            this.level = level;
        }
    }

    /**Default constructor */
    public User(){
    }

    /**Constructor Create a user object */
    public User(String name, String iD, String password, int level){
        this.name = name;
        this.iD = iD;
        this.password = password;
        this.authlevel = hierarchy.unset.level;
        setHierarchy(level);
    }

    /**Sets the level of authority of the user object */
    private int setHierarchy(int level){
        switch (level) {
            case 1:
                this.authlevel = hierarchy.low.level;
                break;
            case 2:
                this.authlevel = hierarchy.medium.level;
                break;
            case 3:
                this.authlevel = hierarchy.high.level;
                break;
            default:
                return 0; //Returns that the change was unsuccessful
        }
        return 1; //Returns that the change was successful
    }

    /**RETURNS the entire user Object */
    public User getUser(){
        return this;
    }

    /**RETURNS the User Name of the User object */
    public String getUserName(){
        return this.name;
    }

    /**RETURNS the iD of the user Object */
    public String getUseriD(){
        return this.iD;
    }

    /**RETURNS the Authority Level of the user Object*/
    public String getAuthLevel(){
        return this.authlevel;
    }
}

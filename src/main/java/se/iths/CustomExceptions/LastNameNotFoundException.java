package se.iths.CustomExceptions;


public class LastNameNotFoundException extends Exception {
    public LastNameNotFoundException(String column){
        super("last name:"+column+", was not found in database");
    }
}

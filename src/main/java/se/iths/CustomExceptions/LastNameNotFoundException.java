package se.iths.CustomExceptions;


import javax.ws.rs.WebApplicationException;

public class LastNameNotFoundException extends WebApplicationException {
    public LastNameNotFoundException(String column){
        super("last name:"+column+", was not found in database");
    }
}

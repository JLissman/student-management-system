package se.iths.CustomExceptions;


public class TableNotFoundException extends Exception {
    public TableNotFoundException(String column){
        super("column name:"+column+", was not found in database");
    }
}

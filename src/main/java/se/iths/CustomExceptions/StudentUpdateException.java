package se.iths.CustomExceptions;

import se.iths.entity.Student;

import javax.ws.rs.WebApplicationException;

public class StudentUpdateException extends WebApplicationException {
    public StudentUpdateException(Student student){
        super("Something went wrong during confirmation of "+student.getFirstName()+" "+student.getLastName());
    }
}

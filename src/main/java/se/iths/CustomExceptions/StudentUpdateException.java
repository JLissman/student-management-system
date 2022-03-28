package se.iths.CustomExceptions;

import se.iths.entity.Student;

public class StudentUpdateException extends Exception{
    public StudentUpdateException(Student student){
        super("Something went wrong during confirmation of "+student.getFirstName()+" "+student.getLastName());
    }
}

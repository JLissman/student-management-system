package se.iths.rest;


import se.iths.CustomExceptions.LastNameNotFoundException;
import se.iths.CustomExceptions.StudentUpdateException;
import se.iths.entity.Student;
import se.iths.service.StudentService;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {


    @Inject
    StudentService studentService;
    //create
    @POST
    @Path("add")
    public Response createStudent(Student student){
        Student createdStudent = studentService.create(student);
        if(createdStudent != null){
        return Response.ok(student).build();
        }else{
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Email already exists - please log in.").type(MediaType.TEXT_PLAIN_TYPE).build());
        }
    }
    //query
    @GET
    @Path("query")
    public Response queryDatabase(@QueryParam("name") String name) throws LastNameNotFoundException {
        List<Student> foundStudents = studentService.queryDB(name);
        System.out.println(foundStudents);
        if(foundStudents!=null) {
            return Response.ok(foundStudents).build();
        }else{
            throw new LastNameNotFoundException(name);
        }
    }
    //extra för debugging
    @GET
    @Path("all")
    public Response getAll(){
        List<Student> foundStudents = studentService.getAll();
        if(foundStudents!=null){
            return Response.ok(foundStudents).build();
        }else{
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("No students founds, Create some!").type(MediaType.TEXT_PLAIN_TYPE).build());
        }
    }


    //update
    @PUT
    @Path("update")
    public Response updateStudent(Student student) throws StudentUpdateException {
        Student updatedStudent = studentService.update(student);
        if(updatedStudent!=null){
        return Response.ok(student).build();
        }else{
            throw new StudentUpdateException(student);
        }
    }



    //delete
    @DELETE
    @Path("delete/{id}")
    public Response deleteStudent(@PathParam("id") Long id){
        Student deletedStudent = studentService.delete(id);
        return Response.ok(deletedStudent).build();

    }

    //read
    @GET
    @Path("get/{id}")
    public Response readStudent(@PathParam("id") Long id){
        Student foundStudent = studentService.read(id);
        if(foundStudent!=null){
            return Response.ok(foundStudent).build();
        }else{
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Student with id "+id+" not found").type(MediaType.TEXT_PLAIN_TYPE).build());
        }

    }


}

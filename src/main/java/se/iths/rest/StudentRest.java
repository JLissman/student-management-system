package se.iths.rest;


import se.iths.CustomExceptions.LastNameNotFoundException;
import se.iths.CustomExceptions.StudentUpdateException;
import se.iths.EXTRA.Generator;
import se.iths.entity.Student;
import se.iths.service.StudentService;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
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
        if(student.getFirstName() == null|| student.getLastName() == null){
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"Response Status\" :\"BAD_REQUEST\", \"Message\":\"If a girl says her name, a man will let her sleep under a roof tonight\"}").type(MediaType.APPLICATION_JSON).build());
        }
        else{
        Student createdStudent = studentService.create(student);
        if(createdStudent != null){
        return Response.ok(student).build();
        }else{
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"Response Status\" :\"BAD_REQUEST\", \"Message\":\"Email already exists - please log in.\"}").type(MediaType.APPLICATION_JSON).build());
        }
    }}
    //query
    @GET
    @Path("query")
    public Response queryDatabase(@QueryParam("name") String name) throws LastNameNotFoundException {
        List<Student> foundStudents = studentService.queryDB(name);
        System.out.println(foundStudents);
        if(foundStudents.isEmpty()) {
            throw new LastNameNotFoundException(name);
        }else{
            return Response.ok(foundStudents).build();
        }
    }
    //extra för debugging
    @GET
    @Path("all")
    public Response getAll(){
        List<Student> foundStudents = studentService.getAll();
        if(foundStudents.isEmpty()){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"Response Status\" :\"404 NOT FOUND\", \"Message\":\"Couldn't find any students\"}").type(MediaType.APPLICATION_JSON).build());

        }else{
            return Response.ok(foundStudents).build();
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
                    .entity("{\"Response Status\" :\"404 NOT FOUND\", \"Message\":\"Couldn't find the student\"}").type(MediaType.APPLICATION_JSON).build());
        }

    }

    @PUT
    @Path("addSubject")
    public Response addSubject(@QueryParam("studentid") Long studentId, @QueryParam("subjectid") Long subjectId){
        if(studentId == null || subjectId == null){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"Response Status\" :\"404 NOT FOUND\", \"Message\":\"Could not find either student by id:"+studentId+" or subject by id:"+subjectId+" \"}").type(MediaType.APPLICATION_JSON).build());
        }
        else
        {
            studentService.addSubjectToStudent(studentId, subjectId);
            return Response.status(Response.Status.OK).entity("{\"Response Status\" :\"200 ok\", \"Message\":\"Student with id:"+studentId+" Added to subject with id:"+subjectId+" \"}").type(MediaType.APPLICATION_JSON).build();
        }

    }

    @GET
    @Path("generate/{antal}")
    public Response generateStudents(@PathParam("antal") int antal) throws FileNotFoundException, URISyntaxException {
        List<Student> studentList = Generator.generateStudentsNullConnections(antal);
        for (Student student:studentList
             ) {
            studentService.create(student);
        }
        return Response.status(Response.Status.OK)
                .entity("{\"Response Status\" :\"200 OK DUDE\", \"Message\":\"Generated "+antal+" new random students\"}").type(MediaType.APPLICATION_JSON).build();



    }
}

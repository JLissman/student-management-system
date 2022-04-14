package se.iths.rest;


import se.iths.CustomExceptions.LastNameNotFoundException;
import se.iths.CustomExceptions.StudentUpdateException;
import se.iths.EXTRA.Generator;
import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import se.iths.service.StudentService;
import se.iths.service.SubjectService;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService subjectService;
    //create
    @POST
    @Path("add")
    public Response createSubject(Subject subject){
        if(subject.getSubjectName() == null){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"Response Status\" :\"404 NOT FOUND\", \"Message\":\"A girl cannot tell a man when exactly he must do a thing. A man cannot make a thing happen before it is time. A subject needs a name doofus.\"}").type(MediaType.APPLICATION_JSON).build());
        }else{
        Subject createdSubject = subjectService.create(subject);
        if(createdSubject != null){
            return Response.ok(subject).build();
        }else{
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"Response Status\" :\"404 NOT FOUND\", \"Message\":\"Something went wrong during creation, check your info.\"}").type(MediaType.APPLICATION_JSON).build());

        }
    }}

    @GET
    @Path("all")
    public Response getAll(){
        List<Subject> foundSubjects = subjectService.getAll();
        if(foundSubjects!=null){
            return Response.ok(foundSubjects).build();
        }else{
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"Response Status\" :\"404 NOT FOUND\", \"Message\":\"No subjects found. create some!\"}").type(MediaType.APPLICATION_JSON).build());
        }
    }


    //update
    @PUT
    @Path("update")
    public Response updateSubject(Subject subject){
        Subject updatedSubject = subjectService.update(subject);
        if(updatedSubject!=null){
            return Response.ok(subject).build();
        }else{
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"Response Status\" :\"404 NOT FOUND\", \"Message\":\"Couldn't update subject - check body info\"}").type(MediaType.APPLICATION_JSON).build());
        }
    }



    //delete
    @DELETE
    @Path("delete/{id}")
    public Response deleteSubject(@PathParam("id") Long id){
        Subject deletedSubject = subjectService.delete(id);
        return Response.ok(deletedSubject).build();

    }

    //read
    @GET
    @Path("get/{id}")
    public Response readSubject(@PathParam("id") Long id){
        Subject foundSubject = subjectService.read(id);
        if(foundSubject!=null){
            return Response.ok(foundSubject).build();
        }else{
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"Response Status\" :\"404 NOT FOUND\", \"Message\":\"Couldn't find subject with id:"+id+" - check body info\"}").type(MediaType.APPLICATION_JSON).build());
        }

    }
    @GET
    @Path("generate/{antal}")
    public Response generateSubjects(@PathParam("antal") int antal) throws FileNotFoundException, URISyntaxException {
        List<Subject> subjectList = Generator.generateSubjectsNullConnections(antal);
        for (Subject subject:subjectList
        ) {
            subjectService.create(subject);
        }
        return Response.status(Response.Status.OK)
                .entity("{\"Response Status\" :\"200 OK DUDE\", \"Message\":\"Generated "+antal+" new random subjects\"}").type(MediaType.APPLICATION_JSON).build();



    }
}

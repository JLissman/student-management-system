package se.iths.rest;


import se.iths.EXTRA.Generator;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;

@Path("teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {

    @Inject
    TeacherService teacherService;
    //create
    @POST
    @Path("add")
    public Response createTeacher(Teacher teacher){
        if(teacher.getFirstName() == null || teacher.getLastName() == null){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"Response Status\" :\"404 NOT FOUND\", \"Message\":\"Speak two names and the man will do the rest.\"}").type(MediaType.APPLICATION_JSON).build());
        }
        else{
        Teacher createdTeacher = teacherService.create(teacher);
        if(createdTeacher != null){
            return Response.ok(teacher).build();
        }else{
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"Response Status\" :\"404 NOT FOUND\", \"Message\":\"Couldn't add teacher - check body info\"}").type(MediaType.APPLICATION_JSON).build());
        }}
    }

    @GET
    @Path("all")
    public Response getAll(){
        List<Teacher> foundTeachers = teacherService.getAll();
        if(foundTeachers!=null){
            return Response.ok(foundTeachers).build();
        }else{
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"Response Status\" :\"404 NOT FOUND\", \"Message\":\"Couldn't find any teachers - create some\"}").type(MediaType.APPLICATION_JSON).build());
        }
    }


    //update
    @PUT
    @Path("update")
    public Response updateTeacher(Teacher teacher){
        Teacher updatedTeacher = teacherService.update(teacher);
        if(updatedTeacher!=null){
            return Response.ok(teacher).build();
        }else{
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"Response Status\" :\"404 NOT FOUND\", \"Message\":\"Couldn't update Teacher. check your ID \"}").type(MediaType.APPLICATION_JSON).build());
        }
    }



    //delete
    @DELETE
    @Path("delete/{id}")
    public Response deleteTeacher(@PathParam("id") Long id){
        Teacher deletedTeacher = teacherService.delete(id);
        return Response.ok(deletedTeacher).build();

    }

    //read
    @GET
    @Path("get/{id}")
    public Response readTeacher(@PathParam("id") Long id){
        Teacher foundTeacher = teacherService.read(id);
        if(foundTeacher!=null){
            return Response.ok(foundTeacher).build();
        }else{
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"Response Status\" :\"404 NOT FOUND\", \"Message\":\"Teacher with id "+id+" not found\"}").type(MediaType.APPLICATION_JSON).build());
        }

    }


    @PUT
    @Path("addSubject")
    public Response addSubjectToTeacher(@QueryParam("teacherid") Long teacherId, @QueryParam("subjectid") Long subjectId){
        if(teacherId != null || subjectId != null) {

            teacherService.addSubjectToTeacher(teacherId, subjectId);

            return Response.status(Response.Status.OK).entity("{\"Response Status\" :\"200 OK\", \"Message\":\"Added subject with id:"+subjectId+" to teacher with id:"+teacherId+"\"}").type(MediaType.APPLICATION_JSON).build();
        }
        else{
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"Response Status\" :\"404 NOT FOUND\", \"Message\":\"Teacher with id:"+teacherId+" or subject with id:"+subjectId+" not found\"}").type(MediaType.APPLICATION_JSON).build());

        }
    }

    @GET
    @Path("generate/{antal}")
    public Response generateTeachers(@PathParam("antal") int antal) throws FileNotFoundException, URISyntaxException {
        List<Teacher> teacherList = Generator.generateTeachersNullConnections(antal);
        for (Teacher teacher:teacherList
        ) {
            teacherService.create(teacher);
        }
        return Response.status(Response.Status.OK)
                .entity("{\"Response Status\" :\"200 OK DUDE\", \"Message\":\"Generated "+antal+" new random teachers\"}").type(MediaType.APPLICATION_JSON).build();



    }
}

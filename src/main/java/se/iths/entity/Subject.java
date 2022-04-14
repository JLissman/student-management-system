package se.iths.entity;

import se.iths.service.TeacherService;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Subject {

    @Id
    @SequenceGenerator(name = "subject_id", sequenceName = "seq_id2")
    @GeneratedValue(generator = "subject_id")
    private Long id;

    @OneToOne
    private Teacher teacher;
    @Size(min=2)
    private String subjectName;
    @ManyToMany
    private List<Student> studentList = new ArrayList<>();


    public Subject(Teacher teacher, String subjectName, List<Student> studentList){
        this.teacher = teacher;
        this.subjectName = subjectName;
        this.studentList = studentList;
    }

    public Subject(String subjectName) {
        this.subjectName = subjectName;
    }

    public Subject() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public void addStudent(Student student){
        this.studentList.add(student);
    }

    @Override
    public String toString() {
        List<String> studentNames = new ArrayList<>();
        for (Student student:studentList
             ) {
            studentNames.add(student.getFirstName()+" "+student.getLastName());
        }
        return "Subject{" +
                "id=" + id +
                ", teacher=" + teacher.getFirstName()+" "+teacher.getLastName() +
                ", subjectName='" + subjectName + '\'' +
                ", studentList=" + studentNames +
                '}';
    }
}

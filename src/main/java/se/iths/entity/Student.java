package se.iths.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Student {
    @Id
    @SequenceGenerator(name = "student_id", sequenceName = "seq_id")
    @GeneratedValue(generator = "student_id")
    private Long id;

    @Size(min=2)
    private String firstName;

    @Size(min=2)
    private String lastName;

    private String email;
    private String phoneNumber;

    @ManyToMany
    @JsonbTransient
    private List<Subject> subjects;

    public Student(String firstName, String lastName, String email, String phoneNumber, List<Subject> subjects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.subjects = subjects;
    }

    public Student(String firstName, String lastName, String email,List<Subject> subjects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = null;
        this.subjects = subjects;
    }

    public Student() {

    }


    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addSubject(Subject subject){
        subjects.add(subject);
        subject.addStudent(this);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}

package se.iths.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Teacher {

    @Id
    @SequenceGenerator(name = "teacher_id", sequenceName = "seq_id3")
    @GeneratedValue(generator = "teacher_id")
    private Long id;
    @Size(min=2)
    private String firstName;
    @Size(min=2)
    private String lastName;
    private String email;
    private String phoneNumber;
    @OneToMany
    @JsonbTransient
    private List<Subject> teachesSubject = new ArrayList<>();

    public Teacher(String firstName, String lastName, String email, List<Subject> teachesIn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.teachesSubject = teachesIn;
    }

    public Teacher(String firstName, String lastName, String email, String phoneNumber, List<Subject> teachesIn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.teachesSubject = teachesIn;
    }

    public Teacher(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Teacher() {

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

    public List<Subject> getTeachesSubject() {
        return teachesSubject;
    }

    public void setTeachesSubject(List<Subject> teachesSubjec) {
        this.teachesSubject = teachesSubject;
    }

    public void addSubject(Subject subject){
        teachesSubject.add(subject);
        subject.setTeacher(this);
    }
}


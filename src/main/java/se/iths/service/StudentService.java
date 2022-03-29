package se.iths.service;

import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentService {
    @PersistenceContext
    EntityManager entityManager;

    public Student create(Student student) {
            List<Student> emailCheck = entityManager.createQuery("SELECT s FROM Student s WHERE s.email = :email", Student.class)
                    .setParameter("email",student.getEmail()).getResultList();
            System.out.println(emailCheck);
            if(emailCheck.isEmpty()){
            entityManager.persist(student);
            return student;
            }
            else{
                return null;
            }
        }

    public Student read(Long id) {
        return entityManager.find(Student.class, id);
    }

    public Student update(Student student) {
        entityManager.merge(student);
        return entityManager.find(Student.class, student.getId());
    }

    public Student delete(Long id) {
        Student foundStudent = entityManager.find(Student.class, id);
        entityManager.remove(foundStudent);
        return foundStudent;
    }
    public List<Student> getAll(){
        return entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();

    }
    public List<Student> queryDB(String name) {

        String query = ("SELECT s FROM Student s WHERE s.lastName = :name");
        List<Student> result = entityManager.createQuery(query).setParameter("name", name).getResultList();//
        if(result.size()!=0){
        return result;}
        else{
        return null;//entityManager.createQuery("SELECT s FROM Student s WHERE s."+field +" = '"+value+"'", Student.class).getResultList();
        }

    }
}


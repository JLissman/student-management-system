package se.iths.service;

import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class TeacherService {
    @PersistenceContext
    EntityManager entityManager;

    public Teacher create(Teacher teacher) {
        entityManager.persist(teacher);
        return teacher;

    }

    public Teacher read(Long id) {
        return entityManager.find(Teacher.class, id);
    }

    public Teacher update(Teacher teacher) {
        entityManager.merge(teacher);
        return entityManager.find(Teacher.class, teacher.getId());
    }

    public Teacher delete(Long id) {
        Teacher foundTeacher = entityManager.find(Teacher.class, id);
        entityManager.remove(foundTeacher);
        return foundTeacher;
    }
    public List<Teacher> getAll(){
        return entityManager.createQuery("SELECT s FROM Teacher s", Teacher.class).getResultList();

    }
    public Teacher addSubjectToTeacher(Long teacherId, Long subjectId){
        Teacher teacher = entityManager.find(Teacher.class, teacherId);
        Subject subject = entityManager.find(Subject.class, subjectId);

        teacher.addSubject(subject);
        entityManager.merge(teacher);

        return teacher;




    }
}